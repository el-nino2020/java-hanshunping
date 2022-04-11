# 第23章：满汉楼

## 源代码

https://github.com/el-nino2020/java-hanshunping/tree/main/mhl



## 界面设计

1. 一个完整的项目长这样：
	- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202204092000325.png" alt="image-20220409200030014" style="zoom:40%;" />
	- 但界面设计不是学习的重点，故使用控制台界面
	- 应完成项目的登录、订座、点餐和结账、查看账单等功能



2. 界面预览图：
	- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202204092004950.png" alt="image-20220409200439640" style="zoom:45%;" />
	- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202204092004693.png" alt="image-20220409200457463" style="zoom:45%;" />
	- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202204092005208.png" alt="image-20220409200552092" style="zoom:60%;" />
	- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202204092006926.png" alt="image-20220409200617611" style="zoom:45%;" />
	- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202204092007778.png" alt="image-20220409200758346" style="zoom:45%;" />
	- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202204092008609.png" alt="image-20220409200828273" style="zoom: 33%;" />
	- <img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202204092009861.png" alt="image-20220409200921433" style="zoom:33%;" />



## 项目设计

1. 分层模式
	- View层（界面层）调用service层（业务层）中的各个类，如`EmpService`、`GoodsService`等
	- service层中，每个类调用DAO层相应的类，如`EmpService`类调用`EmpDAO`类
	- DAO层，每个类对相应的表进行操作，如`EmpDAO`类对`emp`表进行操作
	- 每一张表对应一个javabean对象，后者存放在domain层
	- 此外还有工具类的开发，放在`utils`包中
	- 基本和以下这张框架图一致：
	- ![image-20220409110132263](https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202204091101680.png)









## 实现过程

1. 建立相应包，创建工具类
2. 在`MHLView`类中实现显示主菜单、二级菜单和退出系统的功能
3. 实现登录功能：创建`employee`表、`Employee`类和`EmployeeService`类
4. 实现显示餐桌状态功能：创建`dining_table`表以及相应的javabean、DAO和service类
5. 实现餐桌预定功能
6. 实现显示菜品功能：创建`menu`表以及相应的javabean、DAO和service类
7. 实现点餐功能：
	- 点餐时需要检验餐桌号和菜品编号。点餐成功，需要修改餐桌状态
	- 点餐成功，因生成相应账单。设计账单表`bill`以及相应的javabean、DAO和service类
8. 实现查看账单功能
9. 实现结账功能：
	- 对餐桌号进行检验：该桌是否有“未结账”的账单
	- 修改`bill`表相应的记录，以及将`dining_table`相应的记录设置为初始状态
10. 增强查看账单功能：显示账单时显示菜品名：多表查询，设计`BillEnhanced`类作为javabean以及对应的DAO对象，还是使用`BillService`来返回所有记录



## 总结

1. 使用的技术为java、MySQL、JDBC（Druid + Apache-common-dbutils）
2. 在学习了数据库的基础上再次使用分层结构：自底向上，分别为数据层（数据库）+domain层 $\rightarrow$ DAO层 $\rightarrow$ Service层（业务层） $\rightarrow$ View层（界面层）。此外还用工具包`utils`。对分层模式的理解加深了:confetti_ball:
3. 在之前的学习中，完成了`JCBCUtils`和`BasicDAO`类，分别完成了对数据库连接和对数据库操作的封装。这大大简化了该项目。但因此，在写项目时没法对JDBC知识进行巩固和应用，感觉掌握得不是很扎实。（毕竟用的是两个第三方驱动）
4. 满汉楼项目对应的是餐饮行业，包括前台服务和后台订单、报表等。对餐饮行业不是很熟悉，部分影响了我对这个项目要实现哪些功能的理解。需求分析师还是很重要的，他必须熟悉某一行业经营的全部流程:thinking:
5. 各个service类都是用了`select`语句，不过都比较基本，是对于MySQL的简单应用
6. 总的来说，在第3条的基础上完成项目，难度不是很大。






