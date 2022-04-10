package mhl.domain;

import java.util.Date;

/**
 * 对应mhl.bill表
 */
@SuppressWarnings({"unused"})
public class Bill {
//    id INT PRIMARY KEY AUTO_INCREMENT, -- 账单表中的编号
//    billId VARCHAR(80) NOT NULL, -- 订单编号，由系统生成
//    menuId INT NOT NULL, -- 菜品号
//    num INT NOT NULL, -- 菜品数量
//    money DOUBLE NOT NULL, -- 该份账单的价格
//    tableId INT NOT NULL, -- 桌号
//    billDate DATETIME NOT NULL, -- 账单日期
//    state VARCHAR(30) NOT NULL , -- 账单状态,
//    FOREIGN KEY (menuId) REFERENCES menu(id),
//    FOREIGN KEY (tableId) REFERENCES dining_table(id)
//            );
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer num;
    private Double money;
    private Integer tableId;
    private Date billDate;
    private String state;

    public Bill() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
