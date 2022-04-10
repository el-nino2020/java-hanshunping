package mhl.domain;


//CREATE TABLE dining_table(
//        id INT PRIMARY KEY AUTO_INCREMENT, -- 餐桌号
//        state VARCHAR(20) NOT NULL ,-- 餐桌状态
//        orderName VARCHAR(50) NOT NULL ,-- 预订人姓名
//        orderTel VARCHAR(20) NOT NULL -- 预订人电话
//        );

/**
 * 对应mhl.dining_table表
 */
@SuppressWarnings({"unused"})
public class DiningTable {
    private Integer id;
    private String state;
    private String orderName;
    private String orderTel;

    public DiningTable() {
    }


    public DiningTable(Integer id, String state, String orderName, String orderTel) {
        this.id = id;
        this.state = state;
        this.orderName = orderName;
        this.orderTel = orderTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }
}
