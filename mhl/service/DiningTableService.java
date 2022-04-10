package mhl.service;

import mhl.dao.DiningTableDAO;
import mhl.domain.DiningTable;

import java.util.List;

public class DiningTableService {
    DiningTableDAO diningTableDAO = new DiningTableDAO();

    public List<DiningTable> getTableInfo() {
        String sql = "select id, state from dining_table;";
        return diningTableDAO.queryMultiRow(sql, DiningTable.class);
    }
}
