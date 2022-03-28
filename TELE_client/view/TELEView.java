package TELE_client.view;

import TELE_client.utils.Utility;

public class TELEView {
    private boolean loop = true;

    public static void main(String[] args) {
        new TELEView().mainMenu();
        System.out.println("\n你退出了系统");
    }

    public void mainMenu() {
        while (loop) {//一级菜单
            System.out.println("=============欢迎登录网络通信系统==============");
            System.out.println("\t\t\t1 登录系统");
            System.out.println("\t\t\t9 退出系统");
            System.out.print("请输入你的选择：");
            String choice1 = Utility.readString(1);

            if ("1".equals(choice1)) {
                System.out.print("请输入用户ID:");
                String id = Utility.readString(30);
                System.out.print("请输入密  码:");
                String pwd = Utility.readString(30);

                //向服务端发送申请，判断用户ID是否存在，以及是否与密码匹配

                if (true) {//登录成功，进入二级菜单
                    System.out.println("==============登录成功==============");
                    while (loop) {//二级菜单
                        System.out.println("============网络通信系统二级菜单(用户" +
                                id + ")=============");
                        System.out.println("\t\t\t1 显示在线用户列表");
                        System.out.println("\t\t\t2 群发消息");
                        System.out.println("\t\t\t3 私聊消息");
                        System.out.println("\t\t\t4 发送文件");
                        System.out.println("\t\t\t9 退出系统");
                        System.out.print("请输入你的选择：");
                        String choice2 = Utility.readString(1);

                        switch (choice2) {
                            case "1":
                                System.out.println("用户列表");
                                break;
                            case "2":
                                System.out.println("群发消息");
                                break;
                            case "3":
                                System.out.println("私聊消息");
                                break;
                            case "4":
                                System.out.println("发送文件");
                                break;
                            case "9":
                                loop = false;
                                break;
                            default:
                                System.out.println("============输入不正确，请重新输入===========");
                        }

                    }
                } else {
                    System.out.println("==============登录失败==================");
                }

            } else if ("9".equals(choice1)) {
                loop = false;
            } else {
                System.out.println("============输入不正确，请重新输入===========");
            }
        }
    }
}
