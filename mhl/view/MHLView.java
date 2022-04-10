package mhl.view;

import mhl.domain.DiningTable;
import mhl.domain.Employee;
import mhl.service.DiningTableService;
import mhl.service.EmployeeService;
import mhl.utils.ScanningUtility;

import java.util.List;

/**
 * 满汉楼界面
 */
public class MHLView {
    private boolean loop = true;
    private EmployeeService employeeService = new EmployeeService();
    private DiningTableService diningTableService = new DiningTableService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    private void mainMenu() {
        while (loop) {
            System.out.println("===============满汉楼===================");
            System.out.println("\t\t\t1 登录满汉楼");
            System.out.println("\t\t\t2 退出满汉楼");
            System.out.print("请输入你的选择: ");
            String choice = ScanningUtility.readString(1);
            if ("1".equals(choice)) {
                System.out.print("请输入员工号：");
                String empId = ScanningUtility.readString(30);
                System.out.print("请输入密  码：");
                String pwd = ScanningUtility.readString(50);

                Employee employee = employeeService.logIn(empId, pwd);

                if (employee != null) {//登录成功
                    System.out.println("==============登录成功==================");
                    System.out.println("欢迎！" + employee.getName());
                    while (loop) {//二级菜单
                        System.out.println("===============满汉楼二级菜单===================");
                        System.out.println("\t\t\t1 显示餐桌状态");
                        System.out.println("\t\t\t2 预定餐桌");
                        System.out.println("\t\t\t3 显示所有菜品");
                        System.out.println("\t\t\t4 点餐服务");
                        System.out.println("\t\t\t5 查看账单");
                        System.out.println("\t\t\t6 结账");
                        System.out.println("\t\t\t9 退出满汉楼");

                        System.out.print("请输入你的选择: ");
                        choice = ScanningUtility.readString(1);

                        switch (choice) {
                            case "1":
                                showTableInfo();
                                break;
                            case "2":
                                orderDiningTable();
                                break;
                            case "3":
                                System.out.println("\t\t\t3 显示所有菜品");
                                break;
                            case "4":
                                System.out.println("\t\t\t4 点餐服务");
                                break;
                            case "5":
                                System.out.println("\t\t\t5 查看账单");
                                break;
                            case "6":
                                System.out.println("\t\t\t6 结账");
                                break;
                            case "9":
                                loop = false;
                                break;
                            default:
                                System.out.println("输入有误，请重新输入");
                        }
                        System.out.print("按任意键继续");
                        ScanningUtility.readString(20, " ");
                    }
                } else {
                    System.out.println("==============登录失败==================");
                }
            } else if ("2".equals(choice)) {
                loop = false;
            } else {
                System.out.println("输入有误，请重新输入");
            }
        }
        System.out.println("你退出了满汉楼");
    }

    private void showTableInfo() {
        List<DiningTable> tables = diningTableService.getTablesInfo();
        System.out.println("餐桌编号\t餐桌状态");
        for (DiningTable table : tables) {
            System.out.println(table.getId() + "\t\t" + table.getState());
        }
        System.out.println("===============显示完毕===================");
    }

    private void orderDiningTable() {
        System.out.print("请输入要预定的餐桌编号(-1退出)：");
        int id = ScanningUtility.readInt();
        if (id == -1) {
            System.out.println("===============退出预定===================");
            return;
        }
        char c = ScanningUtility.readConfirmSelection();

        if (c == 'N') {
            System.out.println("===============退出预定===================");
            return;
        }

        //查询餐桌是否存在、是否被预定
        DiningTable table = diningTableService.getTable(id);
        if (table == null) {
            System.out.println("===============餐桌不存在，退出预定===================");
            return;
        }

        if (!("空".equals(table.getState()))) {
            System.out.println("===============该餐桌当前无法预定===================");
            return;
        }

        System.out.print("预订人姓名：");
        String orderName = ScanningUtility.readString(50);
        System.out.print("预订人电话：");
        String orderTel = ScanningUtility.readString(20);

        if (diningTableService.orderTable(id, orderName, orderTel)) {
            System.out.println("===============预定成功===================");
        } else {
            System.out.println("===============预定失败===================");
        }

    }
}
