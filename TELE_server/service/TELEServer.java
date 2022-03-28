package TELE_server.service;

import TELE_common.Message;
import TELE_common.MessageType;
import TELE_common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


/**
 * 该类负责客户端的登录
 */
public class TELEServer {
    private static final int port = 9999;
    private ServerSocket serverSocket;

    private static HashMap<String, User> data = new HashMap<>();

    //模拟数据库
    static {
        data.put("100", new User("100", "123456"));
        data.put("102", new User("102", "123456"));
        data.put("888", new User("888", "123456"));
        data.put("404小队", new User("404小队", "123456"));
    }

    private boolean checkUser(String userID, String pwd) {
        User user = data.get(userID);
        if (user == null) {//该ID不存在
            return false;
        }
        if (!user.getPassword().equals(pwd)) {//密码不正确
            return false;
        }
        return true;
    }

    public TELEServer() {
        System.out.println("服务端在" + port + "端口监听");
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User user = (User) ois.readObject();//来自客户端的请求登录的User对象

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Message message = new Message();

                //登录成功
                if (checkUser(user.getUserID(), user.getPassword())) {
                    System.out.println("用户" + user.getUserID() + "登录成功");
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCESS);
                    oos.writeObject(message);
                    oos.flush();

                    ServerConnectClientThread scct = new
                            ServerConnectClientThread(socket, user.getUserID());
                    scct.start();

                    ManageSCCT.addThread(user.getUserID(), scct);


                } else {//登录失败
                    System.out.println("登录失败： ID：" + user.getUserID()
                            + " 密码： " + user.getPassword());
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAILURE);
                    oos.writeObject(message);
                    oos.flush();

                    ois.close();
                    oos.close();
                    socket.close();
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
