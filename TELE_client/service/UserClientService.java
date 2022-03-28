package TELE_client.service;

import TELE_client.TELEcommon.Message;
import TELE_client.TELEcommon.MessageType;
import TELE_client.TELEcommon.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class UserClientService {
    private static final String serverIP = "127.0.0.1";
    private static final int serverPort = 9999;

    private User user;
    private Socket socket;

    public boolean checkUser(String UserID, String UserPwd) {
        user = new User(UserID, UserPwd);

        try {
            socket = new Socket(InetAddress.getByName(serverIP), serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);//给服务端发送User对象

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();//服务端回传Message对象

            if (message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)) {
                //登录成功，启动线程
                ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
                ccst.start();

                ManageCCST.addCCST(UserID, ccst);

                return true;
            } else {
                socket.close();//释放socket资源

                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}

