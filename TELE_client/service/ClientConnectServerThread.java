package TELE_client.service;

import TELE_client.TELEcommon.Message;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    /**
     *该方法不断接收来自服务端的消息(如果有的话)
     */
    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (true){
                System.out.println("等待来自服务端的消息");
                Message message = (Message) ois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
