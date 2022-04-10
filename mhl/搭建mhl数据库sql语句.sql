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

####################