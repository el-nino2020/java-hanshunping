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
     *
     * @param id 餐桌id
     * @param orderName 预定人姓名
     * @param orderTel  预订人电话
     * @return  预定成功则为true，否则为false
     */
    public boolean orderTable(int id, String orderName, String orderTel) {
        String sql = "update dining_table set state = '已预订', orderName = ?," +
                "orderTel = ? where id =?";
        int row = diningTableDAO.update(sql, orderName, orderTel, id);
        return row > 0;
    }


}
