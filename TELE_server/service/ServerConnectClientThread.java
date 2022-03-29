package TELE_server.service;

import TELE_common.Message;
import TELE_common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userID;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        userID = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {

        try {

            while (true) {
                //https://zhidao.baidu.com/question/250766120.html
                //ObjectOutputStream有锁机制，不能在一个线程中new多次，故不适合声明在这里
                //换句话说，需要的时候再new就行了
                //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Message message = (Message) ois.readObject();

                //客户端请求在线用户列表
                if (message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIENDS)) {
                    System.out.println("用户" + userID + "请求在线用户列表");
                    message = new Message();
                    message.setMesType(MessageType.MESSAGE_RETURN_ONLINE_FRIENDS);
                    String onlineUsers = ManageSCCT.getOnlineUsers();

                    message.setContent(onlineUsers);

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message);
                    oos.flush();
                } else if (message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    //客户端退出
                    System.out.println("用户" + userID + "退出");
                    socket.close();
                    ManageSCCT.removeThread(userID);
                    break;
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMMON_MESSAGE)) {
                    //客户端发送私聊消息
                    String receiver = message.getReceiver();
                    String sender = message.getSender();
                    String content = message.getContent();
                    System.out.println(sender + " 对 " + receiver + " 说：" + content);
                    //消息接收者对应的线程
                    ServerConnectClientThread scct = ManageSCCT.getThread(receiver);
                    if (scct == null) {
                        //该用户不在线，请求非法
                        //这一部分功能先不写
                        System.out.println("该请求非法，" + receiver + " 不在线");
                    } else if (sender.equals(receiver)) {
                        System.out.println("该请求非法：接收者是发送者");
                    } else {
                        System.out.println("请求合法");
                        ObjectOutputStream oos2 = new ObjectOutputStream(scct.getSocket().getOutputStream());
                        oos2.writeObject(message);
                        oos2.flush();
                    }
                } else if (message.getMesType().equals(MessageType.MESSAGE_PUBLIC_MESSAGE)) {
                    //用户要求群发消息
                    System.out.println(message.getSender() + " 对大家说：" + message.getContent());

                    HashMap<String, ServerConnectClientThread> threads = ManageSCCT.getThreads();
                    Set<String> keys = threads.keySet();
                    for (String key : keys) {
                        if (key.equals(message.getSender())) {//不能群发给自己
                            continue;
                        }
                        ObjectOutputStream oos = new ObjectOutputStream(threads.get(key).getSocket().getOutputStream());
                        oos.writeObject(message);
                        oos.flush();
                    }

                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MESSAGE)) {
                    //用户请求发送文件
                    String receiver = message.getReceiver();
                    String sender = message.getSender();

                    System.out.println(sender + "给" + receiver + "发送文件：" +
                            message.getSrcPath() + "到对方设备" + message.getDestPath());
                    //消息接收者对应的线程
                    ServerConnectClientThread scct = ManageSCCT.getThread(receiver);
                    if (scct == null) {
                        //该用户不在线，请求非法
                        //这一部分功能先不写
                        System.out.println("该请求非法，" + receiver + " 不在线");
                    } else if (sender.equals(receiver)) {
                        System.out.println("该请求非法：接收者是发送者");
                    } else {
                        System.out.println("请求合法");
                        ObjectOutputStream oos = new ObjectOutputStream(scct.getSocket().getOutputStream());
                        oos.writeObject(message);
                        oos.flush();
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
