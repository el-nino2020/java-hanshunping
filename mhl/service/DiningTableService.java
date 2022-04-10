package mhl.service;

import mhl.dao.DiningTableDAO;
import mhl.domain.DiningTable;

import java.util.List;

public class DiningTableService {
    DiningTableDAO diningTableDAO = new DiningTableDAO();

    public List<DiningTable> getTablesInfo() {
        String sql = "select id, state from dining_table;";
        return diningTableDAO.queryMultiRow(sql, DiningTable.class);
    }

    /**
     * 如果该餐桌存在，返回DiningTable对象；
     * 否则返回null
     *
     * @param id 餐桌id
     * @return null 或 DiningTable对象
     */
    public DiningTable getTable(int id) {
        String sql = "select * from dining_table where id = ?;";
        return diningTableDAO.querySingleRow(sql, DiningTable.class, id);
    }

    /**
     * @param id        餐桌id
     * @param orderName 预定人姓名
     * @param orderTel  预订人电话
     * @return 预定成功则为true，否则为false
     */
    public boolean orderTable(int id, String orderName, String orderTel) {
        String sql = "update dining_table set state = '已预订', orderName = ?," +
                "orderTel = ? where id =?";
        int row = diningTableDAO.update(sql, orderName, orderTel, id);
        return row > 0;
    }

    /**
     * @param id    餐桌id
     * @param state 新的状态
     * @return 修改成功返回true，否则返回false
     */
    public boolean changeTableState(int id, String state) {
        String sql = "update dining_table set state = ? where id = ?";
        return diningTableDAO.update(sql, state, id) > 0;
    }

    /**
     * 将某个餐桌的信息初始化，即state为'空'，orderName和orderTel为' '
     *
     * @param tableId
     * @return 成功初始化则返回true, 否则返回false
     */
    public boolean initializeTable(int tableId) {
        String sql = "update dining_table set state='空', " +
                "orderName = ' ',orderTel = ' ' where id = ? ;";
        return diningTableDAO.update(sql, tableId) > 0;
    }


}
