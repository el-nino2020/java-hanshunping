package TELE_server.service;

import TELE_common.Message;
import TELE_common.MessageType;
import TELE_server.utils.ScanningUtility;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;

public class SendPublicNewsService extends Thread {
    @Override
    public void run() {

        try {
            while (true) {
                String news = ScanningUtility.readString(50);
                Message message = new Message();
                message.setMesType(MessageType.MESSAGE_PUBLIC_MESSAGE);
                message.setSender("服务端");
                message.setContent(news);

                System.out.println("服务端 对大家说：" + news);


                HashMap<String, ServerConnectClientThread> threads = ManageSCCT.getThreads();
                Set<String> strings = threads.keySet();
                for (String s : strings) {
                    ObjectOutputStream oos = new ObjectOutputStream(threads.get(s).getSocket().getOutputStream());
                    oos.writeObject(message);
                    oos.flush();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
