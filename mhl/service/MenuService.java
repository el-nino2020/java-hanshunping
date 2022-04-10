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

    /**
     *
     * @param id 菜品id
     * @return 该菜品存在则返回Menu对象，不然返回null
     */
    public Menu getDish(int id) {
        String sql = "select * from menu where id = ? ;";
        return menuDAO.querySingleRow(sql, Menu.class, id);
    }
}
