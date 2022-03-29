package TELE_online_communication.TELE_client.service;

import java.util.HashMap;

/**
 * HashMap对象的键是用户ID，对于一个客户端，岂不是只能保存一个线程？
 */
public class ManageCCST {
    //不允许实例化
    private ManageCCST() {
    }

    private static HashMap<String, ClientConnectServerThread> threads =
            new HashMap<>();

    public static void addCCST(String UserId, ClientConnectServerThread ccst) {
        threads.put(UserId, ccst);
    }

    public static ClientConnectServerThread getCCST(String UserID) {
        return threads.get(UserID);
    }
}
