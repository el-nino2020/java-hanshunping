package TELE_server.service;

import TELE_common.Message;
import TELE_common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userID;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        userID = userId;
    }

    @Override
    public void run() {

        try {

            while (true) {
                //获得两个对象流的语句如果放在循环外面，程序貌似会阻塞？
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Message message = (Message) ois.readObject();

                if (message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIENDS)) {
                    System.out.println("用户" + userID + "请求在线用户列表");
                    message = new Message();
                    message.setMesType(MessageType.MESSAGE_RETURN_ONLINE_FRIENDS);
                    String onlineUsers = ManageSCCT.getOnlineUsers();

                    message.setContent(onlineUsers);

                    oos.writeObject(message);
                    oos.flush();
                }else{

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
