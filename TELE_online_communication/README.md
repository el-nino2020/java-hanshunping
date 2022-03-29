# 第19章：多用户即时通讯系统



## 源代码

https://github.com/el-nino2020/java-hanshunping/tree/main/TELE_online_communication



## 设计相关

1. 项目开发流程
	- ![image-20220327145849314](https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202203271458905.png)



2. 需求分析
	- 用户登录
	- 拉取在线用户列表
	- 无异常退出（客户端、服务端）
	- 私聊
	- 群聊
	- 发文件
	- 服务器推送新闻



3. 界面设计
	- 用户登录
		- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202203271511635.png" alt="image-20220327151103520" style="zoom: 50%;" />
	- 拉取在线用户列表
		- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202203291803857.png" alt="image-20220327151201907" style="zoom: 50%;" />
	- 私聊
		- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202203291803242.png" alt="image-20220327151246103" style="zoom:33%;" />
	- 群聊
		- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202203271513827.png" alt="image-20220327151314373" style="zoom:33%;" />
	- 发文件
		- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202203291803277.png" alt="image-20220327151414687" style="zoom:33%;" />
	- 文件服务器推送新闻
		- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202203291804509.png" alt="image-20220327151501079" style="zoom:33%;" />



4. 项目思路分析
	- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202203291804095.png" alt="image-20220327153132740" style="zoom: 33%;" />
	- 客户端与服务端间使用对象流进行数据传输 
	- 由于网络通信可以说是Socket间的IO，对于服务端，每当有一个客户端与它连接，都需要创建一个`Socket`对象。
	- 又因为多台客户端对服务端的通信是同时进行的，需要使用线程。在这里，我们让线程持有`Socket`对象
	- 为了更好地管理线程，我们把线程放到集合中
	- 一个客户端可能同时有多个`Socket`对象（即创建多个线程）与服务端连接，故也需要集合来管理线程



## 功能实现

1. 用户登录（同时搭建程序主架构）
	- 设计`User`和`Message`类，这两个类被客户端和服务端共享
	- 设计`TELEView`类，显示菜单（主菜单和二级菜单）。这里使用到了房屋出租系统项目中的`Utility`工具类
	- 判断用户是否登录成功：设计`UserClientService`类，其中一个方法用于返回登录是否成功。方法的实现为：向服务端发送`User`对象，服务端验证后，回发`Message`对象。客户端根据`MessageType`来判断。
	- 如果登录成功，需要启动线程，该线程持有`Socket`对象，用于接收来自服务端的消息。故设计`ClientConnectServerThread`类
	- 为了管理客户端的多个线程，设计`ManageCCST`类，其中有`HashMap`对象，用于存放线程。
	- 设计`TELEServer`类，不断监听端口，接收客户端发来的`User`对象。如果登录成功，创建相应线程，并使用`HashMap`存储线程
	- 在`TELEServer`类中使用`HashMap`模拟数据库



2. 显示在线用户列表
	- 客户端向服务端发送请求，服务端直接返回即可



3. 无异常退出
	- 客户端要退出时，给服务端发送消息，然后使用`System.exit(0)`，以关闭进程（这样做，就关闭了两个线程）
	- 服务端收到消息后，释放线程的`Socket`对象，并退出线程



4. 私聊功能
	- 假设客户端A给客户端B发送私聊消息
	- A把消息发给服务端，服务端找到对应的与B通行的`Socket`对象，并转发消息
	- 在这里，设计`MessageClientService`类，处理客户端发送消息的功能



5. 群发功能
	- 思路基本和私聊一致 



6. 发文件
	- 思路基本和私聊一致，但要拓展`Message`类的属性
	- 设计`FileClientService`类，用来实现发文件的功能



7. 服务器推送新闻
	- 本质还是群发
	- 需要在服务端另外启动一条线程，接收控制台的输入。



## 总结：

1. 程序架构

	- 网络通讯分为服务端和客户端。客户端的行为都需要服务端处理。如，客户端要发送私聊消息，该消息得由服务端中转

	- 每有一个客户端与服务端连接，就需要创建一对`Socket`对象。为了多个客户端的同步运行，需要把`Socket`对象放入线程

2. 多线程

	- 这个项目能够加深对于多线程和面向对象的理解
	- 客户端发送请求，由对应的`XxxxClientService`类完成，随后`ServerConnectClientThread`线程对象接收请求，并把消息回发给`ClientConnectServerThread`线程对象，由该对象输出信息。尤其是私聊消息时，`ServerConnectClientThread`要区分消息发送者和接受者对应的`Socket`对象。
	- 故每增加一个新功能，增添代码的顺序往往是`TeleView` $\rightarrow$ `XxxClientService` $\rightarrow$  `ServerConnectClientThread` $\rightarrow$ `ClientConnectServerThread`
	- 对于客户端，主线程和`ClientConnectServerThread`线程共享控制台，分别打印菜单和消息
	- 而对于服务端，主线程和所有`ServerConnectClientThread`线程共享控制台，分别打印用户登录情况和用户发送消息。之后实现的服务器群发新闻，又新建了一个线程，接收来自控制台输入的内容。

3. IO知识

	- 传输中使用对象流能提高大大效率
	- 使用对象，传输的内容的拓展性也大大加强——当然，这属于面向对象的知识

