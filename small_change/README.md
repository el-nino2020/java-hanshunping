## 项目：零钱通（SmallChange）

1. 源代码：

	https://github.com/el-nino2020/java-hanshunping/tree/main/small_change

​	

2. 需求相关：

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202110709642.png" alt="屏幕截图(237)" style="zoom: 33%;" />

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202110709130.png" alt="屏幕截图(238)" style="zoom:33%;" />



3. 分析：

- 虽然这个**小**项目是在学完面向对象（中级）后学的，但知识点只覆盖继承之前，故写起来很轻松。
- 但在追求细节方面，由于掌握的知识点不够多，还是有不少缺点：
	- 不使用异常机制无法处理`nextDouble()`方法的非法输入问题；
	- `Date`和`SimpleDateFormat`类只是抄来用的，并没有学过
	- 没有文件I/O，所有操作都是在JVM上运行
	- `details`应该是动态数组，而非一个`String`，但前者也没有学

