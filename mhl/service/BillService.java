package mhl.service;

import mhl.dao.BillDAO;
import mhl.domain.Bill;

import java.util.List;
import java.util.UUID;

public class BillService {
    private BillDAO billDAO = new BillDAO();
    private DiningTableService diningTableService = new DiningTableService();


    /**
     * 点餐时需要生成相应账单，
     * 同时tableId餐桌的状态应该变为“就餐中”
     *
     * @param menuId
     * @param num
     * @param money   num * 菜品的单价
     * @param tableId
     * @return 生成账单成功返回true，否则返回false
     */
    public boolean createBill(int menuId, int num, double money, int tableId) {
        String billId = UUID.randomUUID().toString();//使用该方法生成随机字符串
        String sql = "insert into bill values(null,?,?,?,?,?,now(),'未结账');";
        if (billDAO.update(sql, billId, menuId, num, money, tableId) <= 0) {
            return false;
        }
        if (!diningTableService.changeTableState(tableId, "就餐中")) {
            return false;
        }

        return true;
    }

    public List<Bill> getAllBill() {
        String sql = "select * from bill;";
        return billDAO.queryMultiRow(sql, Bill.class);
    }

    /**
     * 查看某个餐桌是否有未结账的账单
     *
     * @param tableId 餐桌号
     * @return
     */
    public boolean needToPayBill(int tableId) {
        //只要查询到一条记录，就表示该餐桌需要付账单，故使用 limit 0,1 很巧妙
        String sql = "select * from bill where tableId = ? and state='未结账' limit 0,1;";
        Bill bill = billDAO.querySingleRow(sql, Bill.class, tableId);
        return bill != null;
    }

    /**
     * (1) 将bill表中tableId的未结账的记录设置为已结账
     * (2) 将dining_table表中tableId的餐桌状态初始化
     *
     * @param tableId 需要结账的餐桌号
     * @return (1)和(2)都成功才返回true,否则返回false
     */
    public boolean payBill(int tableId) {
        String sql = "update bill set state = '已结账' where tableId = ?";

        //这里应该使用事务将两条更新语句包起来，但涉及别的知识，就先不用了(这个项目的知识点不在此)
        if (billDAO.update(sql, tableId) <= 0) {//更新失败
            return false;
        }
        return diningTableService.initializeTable(tableId);
    }
}
