package TELE_server.service;

import TELE_common.Message;

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
                System.out.println("等待来自客户端" + userID + "的消息");
                //获得两个对象流的语句如果放在循环外面，程序貌似会阻塞？
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Message message = (Message) ois.readObject();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
