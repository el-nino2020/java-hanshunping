package TELE_client.service;

import TELE_common.Message;
import TELE_common.MessageType;
import TELE_common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class UserClientService {

    private static final String serverIP = "127.0.0.1";
    private static final int serverPort = 9999;

    /**
     * 一旦登录成功，通过这两个属性，可以获得该客户端的线程
     * 亦即该客户端的控制权
     */
    private User user;
    private Socket socket;


    public boolean checkUser(String UserID, String UserPwd) {
        user = new User(UserID, UserPwd);

        try {
            socket = new Socket(InetAddress.getByName(serverIP), serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);//给服务端发送User对象
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();//服务端回传Message对象

            if (message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)) {
                //登录成功，启动线程
                ClientConnectServerThread ccst = new ClientConnectServerThread(socket, user.getUserID());
                ccst.start();


                ManageCCST.addCCST(UserID, ccst);

                return true;
            } else {
                ois.close();
                oos.close();
                socket.close();//释放socket资源

                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return false;
    }

    /**
     * 向客户端发送请求用户列表的消息,
     * 由对应的CCST线程负责接收客户端的信息，并负责输出
     */
    public void showOnlineFriends() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIENDS);
            oos.writeObject(message);
            oos.flush();

            //为了让对应的CCST线程能够先执行完打印，
            //然后才是菜单的再次打印。具体方法是：
            Thread.sleep(30);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clientExit() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
            oos.writeObject(message);
            oos.flush();

            System.out.println("\n你退出了系统");
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

