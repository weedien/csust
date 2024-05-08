### 长理JAVA作业合集

目前上过的和Java相关的课程：

- [x] Java程序设计
- [x] Java EE 编程基础
- [x] 软件设计模式
- [x] Java 框架技术
- [x] 编译原理（上机作业使用Java）

本仓库将用于记录学习过程中的代码，主要是完成老师布置的作业。

docs目录下记录的是作业的要求：

- [docs/Java程序设计](docs/Java程序设计) ==> Java程序设计 ==> basic模块

- [docs/J2EE](docs/J2EE) ==> Java EE 编程基础 ==> intermediate下的core和web模块
- [docs/Java框架技术](docs/Java框架技术) ==> Java框架技术 ==> advanced模块

> 项目中使用的Java版本主要为21，当于8版本语法上应该是兼容的，在一些依赖项上版本会不兼容
>
> web编程时需要注意，java8之后，servlet位于jakarta包下，而不是javax



### 项目结构

- 使用basic、intermediate、advanced三个模块对应不同学期（阶段）的课程
- 所有项目的包名都以cn.weedien.csust开头，其下分为basic、medium、advanced三个子包
- 尽量将所有和课程相关的代码都放在本项目中，所以采用多模块的结构
- 每个模块都是一个独立的项目，不存在对项目父项目的依赖，可以独立运行