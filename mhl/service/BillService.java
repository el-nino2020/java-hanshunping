package mhl.service;

import mhl.dao.BillDAO;

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
}
