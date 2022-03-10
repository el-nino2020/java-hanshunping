package house_rent.view;

import house_rent.domain.House;
import house_rent.service.HouseService;
import house_rent.utils.Utility;

//界面层，用来与用户交互，以及调用业务层
public class HouseView {
    private HouseService houseService;

    public HouseView() {
        houseService = new HouseService();
    }

    public void mainMenu() {
        char choice;

        while (true) {
            System.out.println("-------------房屋出租系统---------------");
            System.out.println("\t\t\t1.新 增 房 屋");
            System.out.println("\t\t\t2.查 找 房 屋");
            System.out.println("\t\t\t3.删 除 房 屋");
            System.out.println("\t\t\t4.修 改 房 屋 信 息");
            System.out.println("\t\t\t5.房 屋 列 表");
            System.out.println("\t\t\t6.退 出");
            System.out.print("请选择(1-6) :");

            //类的静态方法可以直接调用，不需要先新建类对象
            choice = Utility.readChar();

            if (choice == '1') {
                addHouse();
            } else if (choice == '2') {
                findHouse();
            } else if (choice == '3') {
                delHouse();
            } else if (choice == '4') {
                updateHouse();
            } else if (choice == '5') {
                listHouse();
            } else if (choice == '6') {
                if (quit())
                    break;
            } else {
                System.out.println("输入有误，请重新输入");
            }


        }

    }

    private void addHouse() {
        System.out.println("-------------添加房屋---------------");
        System.out.print("姓名: ");
        String name = Utility.readString(10);

        System.out.print("电话: ");
        String phone = Utility.readString(15);

        System.out.print("地址: ");
        String address = Utility.readString(20);

        System.out.print("月租: ");
        int rent = Utility.readInt();

        System.out.print("状态(未出租/已出租): ");
        String status = Utility.readString(3);

        House house = new House(name, phone, address, rent, status);

        if (houseService.add(house)) {
            System.out.println("-------------添加完成---------------");
        } else {
            System.out.println("-------------空间已满，添加失败---------------");
        }
        System.out.println();
    }

    private void findHouse() {
        System.out.print("请输入你要查找的id： ");
        int id = Utility.readInt();

        House target = houseService.find(id);
        if (target == null) {
            System.out.println("-------------没有该房屋---------------\n");
            return;
        }
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        System.out.println(target);
        System.out.println();
    }

    private void delHouse() {
        System.out.println("-------------删除房屋---------------");
        System.out.print("请选择待删除房屋编号(-1退出)：");
        int id = Utility.readInt();
        if (id == -1) {
            System.out.println();
            return;
        }
        char c = Utility.readConfirmSelection();
        if (c == 'N') {
            System.out.println();
            return;
        }
        if (houseService.del(id)) {
            System.out.println("-------------删除完成---------------");
        } else {
            System.out.println("-------------没有该房间，删除失败---------------");
        }

        System.out.println();
    }

    private void updateHouse() {
        System.out.println("-------------修改房屋---------------");
        System.out.print("请选择待修改房屋编号(-1退出)：");
        int id = Utility.readInt();
        if (id == -1) {
            System.out.println();
            return;
        }
        House target = houseService.find(id);
        if (target == null) {
            System.out.println("-------------没有该房屋，修改失败---------------\n");
            return;
        }

        System.out.print("姓名(" + target.getName() + ")：");
        String name = Utility.readString(10, "");
        if (name != "") {
            target.setName(name);
        }

        System.out.print("电话(" + target.getPhone() + ")：");
        String phone = Utility.readString(10, "");
        if (phone != "") {
            target.setPhone(phone);
        }

        System.out.print("地址(" + target.getAddress() + ")：");
        String address = Utility.readString(10, "");
        if (address != "") {
            target.setAddress(address);
        }

        System.out.print("租金(" + target.getRent() + ")：");
        int rent = Utility.readInt(-1);
        if (rent != -1) {
            target.setRent(rent);
        }

        System.out.print("状态(" + target.getStatus() + ")：");
        String status = Utility.readString(3, "");
        if (status != "") {
            target.setStatus(status);
        }

        System.out.println("-------------修改完成---------------");
        System.out.println();
    }


    private void listHouse() {
        System.out.println("-------------房屋列表---------------");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");

        House[] houses = houseService.list();
        int n = houseService.getSize();

        for (int i = 0; i < n; ++i) {
            System.out.println(houses[i]);
        }
        System.out.println();
    }

    private boolean quit() {
        char choice;
        System.out.println("为防止误触，请再确认");
        choice = Utility.readConfirmSelection();
        if (choice == 'Y') {
            System.out.println("你退出了程序~~");
            return true;
        }
        return false;
    }

}
