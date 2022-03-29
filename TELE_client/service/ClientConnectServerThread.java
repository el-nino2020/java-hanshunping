package TELE_client.service;

import TELE_common.Message;
import TELE_common.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    private Socket socket;
    private String userID;


    public ClientConnectServerThread(Socket socket, String userID) {
        this.socket = socket;
        this.userID = userID;
    }

    public Socket getSocket() {
        return socket;
    }

    /**
     * 该方法不断接收来自服务端的消息(如果有的话)
     */
    @Override
    public void run() {
        try {

            while (true) {
                //System.out.println("等待来自服务端的消息");

                //获得对象流的语句如果放在循环外面，程序貌似会阻塞？
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();

                //输出当前用户列表
                if (message.getMesType().equals(MessageType.MESSAGE_RETURN_ONLINE_FRIENDS)) {
                    System.out.println("当前在线用户列表：");
                    String[] users = message.getContent().split(" ");
                    for (String s : users) {
                        System.out.println("用户: " + s);
                    }
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMMON_MESSAGE)) {
                    //打印收到的私聊消息
                    String receiver = message.getReceiver();
                    String sender = message.getSender();
                    String content = message.getContent();
                    System.out.println("\n########### NEW MESSAGE #################");
                    System.out.println(sender + " 对你(" + receiver + ")说：" + content);
                    System.out.println("############################################");
                }else if (message.getMesType().equals(MessageType.MESSAGE_PUBLIC_MESSAGE)) {
                    //打印收到的私聊消息
                    String sender = message.getSender();
                    String content = message.getContent();
                    System.out.println("\n########### NEW MESSAGE #################");
                    System.out.println(sender + " 对大家说：" + content);
                    System.out.println("############################################");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
