package TELE_online_communication.TELE_client.service;

import TELE_online_communication.TELE_client.utils.IOUtility;
import TELE_online_communication.TELE_common.Message;
import TELE_online_communication.TELE_common.MessageType;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;

/**
 * 该类处理用户发文件的功能
 */
public class FileClientService {
    /**
     * @param sender
     * @param receiver
     * @param srcPath  发送文件路径
     * @param destPath 对方接收文件的路径
     */
    public void sendFile(String sender, String receiver, String srcPath, String destPath) {
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setMesType(MessageType.MESSAGE_FILE_MESSAGE);
        message.setSrcPath(srcPath);
        message.setDestPath(destPath);

        try {

            FileInputStream fis = new FileInputStream(srcPath);
            byte[] bytes = IOUtility.inputStreamToByteArray(fis);
            message.setFileBytes(bytes);
            fis.close();

            ObjectOutputStream oos = new ObjectOutputStream(
                    ManageCCST.getCCST(sender).getSocket().getOutputStream());

            oos.writeObject(message);
            oos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
