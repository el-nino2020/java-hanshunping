package TELE_server.service;

import java.util.HashMap;
import java.util.Set;

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

    public static void removeThread(String userID) {
        threads.remove(userID);
    }

    public static String getOnlineUsers() {
        StringBuilder sb = new StringBuilder();

        Set<String> strings = threads.keySet();

        for (String s : strings) {
            sb.append(s);
            sb.append(" ");
        }

        return sb.toString();
    }
}
