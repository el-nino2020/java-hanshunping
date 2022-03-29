package TELE_common;

public interface MessageType {
    //使用该类的常量以表示消息类型
    /**
     * 服务端告知客户端：登录成功
     */
    String MESSAGE_LOGIN_SUCCESS = "1";

    /**
     * 服务端告知客户端：登录失败
     */
    String MESSAGE_LOGIN_FAILURE = "2";

    /**
     * 普通信息(私聊)
     */
    String MESSAGE_COMMON_MESSAGE = "3";

    /**
     * 客户端请求获取在线用户列表
     */
    String MESSAGE_GET_ONLINE_FRIENDS = "4";

    /**
     * 服务端返回在线用户列表
     */
    String MESSAGE_RETURN_ONLINE_FRIENDS = "5";

    /**
     * 客户端请求退出
     */
    String MESSAGE_CLIENT_EXIT = "6";

    /**
     * 群发信息
     */
    String MESSAGE_PUBLIC_MESSAGE = "7";

    /**
     * 文件消息
     */
    String MESSAGE_FILE_MESSAGE = "8";
}
