package mhl.domain;

import java.time.LocalDateTime;

/**
 * 对应mhl.bill表
 */
@SuppressWarnings({"unused"})
public class Bill {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer num;
    private Double money;
    private Integer tableId;
    //这里不能使用Date，会报错，残念です
    private LocalDateTime billDate;
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

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return id + "\t\t" + menuId + "\t\t\t" + num + "\t\t\t" +
                money + "\t\t" + tableId + "\t\t" + billDate + "\t\t" + state;
    }
}
