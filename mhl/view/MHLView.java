package mhl.view;

import mhl.domain.Employee;
import mhl.service.EmployeeService;
import mhl.utils.ScanningUtility;

/**
 * 满汉楼界面
 */
public class MHLView {
    private boolean loop = true;
    private EmployeeService employeeService = new EmployeeService();

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
                                System.out.println("\t\t\t1 显示餐桌状态");
                                break;
                            case "2":
                                System.out.println("\t\t\t2 预定餐桌");
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
}
