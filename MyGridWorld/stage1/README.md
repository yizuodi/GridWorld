# 2022软件工程中级实训_阶段1代码说明

## 文件目录

```
 ├── Calculator
 │   └── classes 	// 生成类文件夹
 │   └── lib 		// 编译运行所需的jar包
 │   └── src 		// 源代码文件夹
 │   └── build.xml	// Ant 构建文件
 │   └── sonar-project.properties // Sonar 配置文件
 ├── HelloWorld
 │   └── classes 	// 生成类文件夹
 │   └── lib 		// 编译运行所需的jar包
 │   └── src 		// 源代码文件夹
 │   └── build.xml	// Ant 构建文件
 ├── README.md
```

## HelloWrold程序

此程序主要功能为在命令行界面下输出“Hello world!”，src目录下包含Helloworld类与HelloworldTest类。

编译：命令行界面下，在HelloWorld目录下输入ant build即可进行编译。

运行：命令行界面下，在HelloWorld目录下输入ant run即可进行编译、运行。

Junit单元测试：命令行界面下，在HelloWorld目录下输入ant test即可进行编译、运行、单元测试。

## Calculator程序

此程序主要功能为一个简单的拥有GUI的计算器（支持加减乘除四则运算），src目录下包含Calculator类与CalculatorTest类。

编译：命令行界面下，在Calculator目录下输入ant build即可进行编译。

运行：命令行界面下，在Calculator目录下输入ant run即可进行编译、运行。

Junit单元测试：命令行界面下，在Calculator目录下输入ant test即可进行编译、运行、单元测试。