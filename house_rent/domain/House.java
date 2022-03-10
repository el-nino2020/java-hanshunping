package house_rent.domain;

//数据层，数据的实际存在形式
public class House {
    private int id = -1;//将由HouseService来赋值
    private String name;
    private String phone;
    private String address;
    private int rent;
    private String status;

    public House(String name, String phone, String address, int rent, String status) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.rent = rent;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  id +
                "\t\t" + name  +
                "\t\t" + phone +
                "\t\t" + address +
                "\t\t" + rent +
                "\t\t" + status;
    }
}
