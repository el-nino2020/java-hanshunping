package TELE_client.view;

import TELE_client.service.UserClientService;
import TELE_client.utils.Utility;

public class TELEView {
    private boolean loop = true;
    private UserClientService userClientService = new UserClientService();

    /**
     * 启动客户端
     *
     * @param args
     */
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

                //向服务端发送申请，判断用户信息是否合法
                if (userClientService.checkUser(id, pwd)) {//登录成功，进入二级菜单
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
                                userClientService.showOnlineFriends();
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
                                userClientService.clientExit();
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
