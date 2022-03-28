package TELE_server.frame;

import TELE_server.service.TELEServer;

/**
 * 服务端依靠这个类启动
 */
public class TELEFrame {
    public static void main(String[] args) {
        new TELEServer();
    }
}
