####################### 创建数据库
CREATE DATABASE mhl;
USE mhl;

####################### 创建employee(员工)表
CREATE TABLE employee(
     id INT PRIMARY KEY AUTO_INCREMENT, -- 员工表的序号
     emp_id VARCHAR(30) UNIQUE NOT NULL , -- 员工的id
     pwd CHAR(32) NOT NULL, -- 员工密码，经过MD5加密
     `name` VARCHAR(20) NOT NULL, -- 员工名
     job VARCHAR(20) NOT NULL -- 工作岗位
);

INSERT INTO employee VALUES
	(NULL, '1', MD5('123457'), 'Morgan', '社长'),
	(NULL, '2', MD5('1234'), 'Lilith', '经理'),
	(NULL, '3', MD5('666'),  'Arthur', '前台');
	
SELECT * FROM employee;

#################### 创建dining_table(餐桌)表

#drop table dining_table;
CREATE TABLE dining_table(
	id INT PRIMARY KEY AUTO_INCREMENT, -- 餐桌号
	state VARCHAR(20) NOT NULL ,-- 餐桌状态
	orderName VARCHAR(50) NOT NULL ,-- 预订人姓名
	orderTel VARCHAR(20) NOT NULL -- 预订人电话
);

INSERT INTO dining_table VALUES
	(NULL, '空',' ',' '),
	(NULL, '空',' ',' '),
	(NULL, '空',' ',' ');
	
	
SELECT * FROM dining_table;


####################### 创建menu(菜单)表
#drop table menu;
CREATE TABLE menu(
      id INT PRIMARY KEY AUTO_INCREMENT, -- 菜品编号
      `name` VARCHAR(25) NOT NULL, -- 菜品名称
      `type` VARCHAR(10) NOT NULL, -- 菜品种类
      price DOUBLE NOT NULL -- 价格
	);
	
INSERT INTO menu VALUES
	(NULL, '蛋包饭','主食',25),
	(NULL, '寿司','主食',20),
	(NULL, '荞麦面','主食',20),
	(NULL, '芝士蛋糕','甜食',30),
	(NULL, '萨赫蛋糕','甜食',30),
	(NULL, '红茶','饮料',5),
	(NULL, '咖啡','饮料',5);
	
SELECT * FROM menu;