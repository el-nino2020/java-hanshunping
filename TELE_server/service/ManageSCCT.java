package TELE_server.service;

import java.util.HashMap;

public class ManageSCCT {
    private ManageSCCT() {
    }

    private static HashMap<String, ServerConnectClientThread> threads =
            new HashMap<>();

    public static void addThread(String userId, ServerConnectClientThread scct) {
        threads.put(userId, scct);
    }

    public static ServerConnectClientThread getThread(String userId) {
        return threads.get(userId);
    }
}
