package TELE_client.service;

import TELE_common.Message;
import TELE_common.MessageType;

import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 该类处理与用户发送消息相关的功能
 */
public class MessageClientService {
    /**
     * 将私聊消息发送给服务端
     *
     * @param senderID   消息发送者ID
     * @param receiverID 消息接收者ID
     * @param content    消息内容
     */
    public void sendPrivateMessage(String senderID, String receiverID, String content) {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMMON_MESSAGE);
        message.setReceiver(receiverID);
        message.setSender(senderID);
        message.setContent(content);

        message.setSendTime(new Date().toString());//这个功能暂时没啥用

        try {
            //获取发送者对应的Socket对象
            ObjectOutputStream oos = new ObjectOutputStream(
                    ManageCCST.getCCST(senderID).getSocket().getOutputStream());

            oos.writeObject(message);
            oos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPublicMessage(String sender, String content) {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_PUBLIC_MESSAGE);
        message.setSender(sender);
        message.setContent(content);
        try {
            //获取发送者对应的Socket对象
            ObjectOutputStream oos = new ObjectOutputStream(
                    ManageCCST.getCCST(sender).getSocket().getOutputStream());

            oos.writeObject(message);
            oos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
