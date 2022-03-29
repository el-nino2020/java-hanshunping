package TELE_online_communication.TELE_client.view;

import TELE_online_communication.TELE_client.service.FileClientService;
import TELE_online_communication.TELE_client.service.MessageClientService;
import TELE_online_communication.TELE_client.service.UserClientService;
import TELE_online_communication.TELE_client.utils.ScanningUtility;

public class TELEView {
    private boolean loop = true;
    private UserClientService userClientService = new UserClientService();
    private MessageClientService messageClientService = new MessageClientService();
    private FileClientService fileClientService = new FileClientService();

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
            String choice1 = ScanningUtility.readString(1);

            if ("1".equals(choice1)) {
                System.out.print("请输入用户ID:");
                String id = ScanningUtility.readString(30);
                System.out.print("请输入密  码:");
                String pwd = ScanningUtility.readString(30);

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
                        String choice2 = ScanningUtility.readString(1);

                        switch (choice2) {
                            case "1":
                                userClientService.showOnlineFriends();
                                break;
                            case "2":
                                System.out.print("请输入想对大家说的话：");
                                String content = ScanningUtility.readString(100);
                                messageClientService.sendPublicMessage(id, content);
                                break;
                            case "3":
                                System.out.print("请输入聊天对象的ID(在线)：");
                                String receiver = ScanningUtility.readString(30);
                                System.out.print("请输入想说的话：");
                                content = ScanningUtility.readString(100);
                                messageClientService.sendPrivateMessage(id, receiver, content);
                                break;
                            case "4":
                                System.out.print("请输入发送文件的目标用户的ID(在线)：");
                                receiver = ScanningUtility.readString(30);
                                System.out.print("请输入该文件的本地路径：");
                                String srcPath = ScanningUtility.readString(100);
                                System.out.print("请输入对方保存该文件的路径：");
                                String destPath = ScanningUtility.readString(100);
                                fileClientService.sendFile(id, receiver, srcPath, destPath);
                                break;
                            case "9":
                                userClientService.clientExit();
                                break;
                            default:
                                System.out.println("============输入不正确，请重新输入===========");
                        }


                        try {
                            Thread.sleep(30);//等待对应的CCST线程先打印完
                        } catch (InterruptedException e) {
                            e.printStackTrace();
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
