## 项目：房屋出租系统

1. ~~源代码~~



2. 需求相关：

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202092006798.png" alt="屏幕截图(240)" style="zoom: 33%;" />

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202121714646.png" alt="屏幕截图(241)" style="zoom: 33%;" />

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202121714248.png" alt="屏幕截图(242)" style="zoom: 33%;" />

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202121714211.png" style="zoom: 33%;" />

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202121714258.png" alt="屏幕截图(244)" style="zoom: 33%;" />

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202121714287.png" alt="屏幕截图(245)" style="zoom: 33%;" />

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202121714755.png" alt="屏幕截图(250)" style="zoom: 33%;" />

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202121714840.png" alt="屏幕截图(247)" style="zoom:50%;" />





3. 程序框架——分层模式

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202121714707.png" alt="屏幕截图(248)" style="zoom: 33%;" />

- 程序框架图的目的：
	- 确定系统有哪些类
	- 明确类与类之间的调用关系
- 房屋出租系统的程序框架图如上图所示
- 工具类：实际开发中公司提供工具类和开发库，以提高开发效率。即，这个类是预先给定的。（在该项目中也是给定的）



4. 代码实现指导：

<img src="https://cdn.jsdelivr.net/gh/el-nino2020/ImageBed/202202121714305.png" alt="屏幕截图(249)" style="zoom: 33%;" />



5. 分析：
	- 这个项目的**重点**是分层模式，而代码实现比较次要。
		- `HouseView`作为界面层，负责全部的I/0，并将接受到的信息传给`HouseService`——业务层。
		- 业务层拥有基本的数据结构——array，负责存储`House`对象。并且，它通过`HouseView`中方法的参数传递，对数据作相应处理，然后将处理结果（`boolean`值，或者`House`对象）返还给`HouseView` 
		- `House`作为实体类，是一个数据单元。其用处更像是C++中的结构体，用来整合不同类型的数据。其方法多为对成员的直接操作，没有复杂的逻辑。
	- 说代码实现次要，有以下几点：
		- `Utility`类是现成的。如果自己写该工具类，代码编写难度将提高——要熟练运用现有I/0函数，还要有异常处理——虽然也没高到哪里去:sweat_smile:
		- 因为还没学异常处理，就没给`houses`设计扩容机制（我也不知道`new`会不会像C++中一样抛出异常），故它只是一个单纯的array
		- 如果不考虑`HouseService.find()`可以用二分查找优化这一点，`houses`的数据结构应该是链表，而非array

