package mhl.service;

import mhl.dao.MenuDAO;
import mhl.domain.Menu;

import java.util.List;

public class MenuService {
    MenuDAO menuDAO = new MenuDAO();

    /**
     * @return menu表中所有记录
     */
    public List<Menu> getAllDish() {
        String sql = "select * from menu";
        return menuDAO.queryMultiRow(sql, Menu.class);
    }
}
