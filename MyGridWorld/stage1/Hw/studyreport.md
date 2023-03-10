# Vi,Java,Ant和Junit的自学报告

## Vi

Vi/Vim是Linux下最常见的文本编辑器（当然有时我们会用nano，甚至gedit）。Vim是从Vi发展而来的，Vim支持多级撤销、语法加亮等功能，它们在模式上也有区别，但一般我们可以认为它们有命令模式、输入模式、底线命令模式三种模式。三种模式简单介绍如下：

#### 1.命令模式

用户刚刚进入时（比如我们在命令行输入vi myfile后）即为命令模式，我们的敲击键盘会被认为是输入命令。比如，敲击 i 会切换到输入模式；敲击 : 会切换到底线命令模式。

#### 2.输入模式

切换到此模式时，我们可以在文件中进行输入、删除等操作。

还记得我很久以前第一次使用时不知，如何退出输入模式，多次按Esc都无法退出vim。这里按Esc其实就是回到了命令模式。

#### 3.底线命令模式

在命令模式下，我们敲击 : 即可切换到底线命令模式。

然后输入wq并回车即可退出vi（保存并退出）。



上面只提到了一些我们用到的最为**常用的操作**。对于vim中的各项操作，很有必要列出来自菜鸟教程（https://www.runoob.com/linux/linux-vim.html）的这张vi/vim键盘图：

![](vim_keyboard.jpg)





## Java

关于Java，最为著名的一句口号是“一次编写，到处运行” (Write Once, Run Anywhere)  。这是因为，Java的源程序(.java) 在编译成Java字节码文件(.class)之后，可以在任何安装了Java虚拟机(JVM)的系统上执行。  

#### Java的特点

它简单（简化了C++的功能、没有指针、自动释放空间），易于移植（字节码程序在Java虚拟机中运行，Java虚拟机可以优化程序执行效率，部分场景可以优于C/C++），而且面向对象（Java里一切都是对象）。

#### JRE和JDK

这是Java平台最为主要的两个产品Java Runtime Environment(JRE)和Java Development Kit(JDK) 。

JRE提供了Java核心库、Java虚拟机以及运行Java应用程序所需的其它组件，我们的字节码文件需要在JRE下运行。

JDK是JRE的超集，它是开发Java应用程序的开发工具，在JRE的基础上加入了编译器和调试器等工具。

目前2014年发布的JDK1.8使用仍然非常广泛（尽管已经Oracle已经发布了JDK19）。

#### "Hello World"程序

```java
public class Main {
  public static void main(String[] args) {
    System.out.print("Hello World");
  }
}
```

文件名需要与类目一致，上述代码应当写在Main.java文件中。程序中，通过调用System.out中的print方法，打印了字符串"Hello World"。

我们在命令行下输入javac Main.java即可编译生成Main.class字节码文件，然后输入java Main即可看到"Hello World"字样的输出。

这也即是我们实训过程用Java做的第一项任务。

#### Java的缺点

1.占用内存更大（需要靠虚拟机运行）。

2.无法与操作系统底层交互（也是因为需要靠虚拟机运行）。

3.不支持指针使其在部分场景下不够灵活。





## Ant

Ant是由Apache软件基金会提供的Java的构建工具，它可以将编译、运行、测试等步骤联系起来，实现其自动化。

我个人感觉作用上Ant与cmake类似，都是构建工具，它的构建文件build.xml类似于cmake的makefile文件。

`<project>`中需要包含name（项目的名称）、basedir（比如.表示当前目录），还可以包含default（可以指定输入ant默认附加的traget）等。在它里面可以包含下述其他元素。

`<target>`是Ant的基本执行单元，需要在`<project>`与`</project>`之间出现，它的name就是对应的target。如果给它附加上depends，就可以实现运行前先构建这样的操作（比如下面我们的例子，ant run会包含构建-运行过程）。

`<property>`可以用于定义参数，比如下面我们定义src的值为“src”。

另外，Ant中还有一些内置属性。比如ant.file可以获取到构建文件build.xml的绝对路径。

实训过程，我们为Helloworld项目所写的build.xml文件如下：

```xml
<project name="HelloWorld" basedir="." default="run">
<property name="src" value="src"/>
<property name="classes" value="classes"/>
<path id="buildpath">
<pathelement location="lib/junit4.jar"/>
<pathelement location="lib/hamcrest-core-1.3.jar"/>
<pathelement location="src"/>
<pathelement location="classes"/>
</path>
<target name="build" description="build">
<delete dir="${classes}"/>
<mkdir dir="${classes}"/>
<javac srcdir="${src}" destdir="${classes}" includeantruntime="false" classpathref="buildpath"> </javac>
</target>
<target name="run" depends="build">
<java classname="Helloworld">
<classpath>
<pathelement path="${classes}"/>
</classpath>
</java>
</target>
<target name="test" depends="build">
<junit>
<classpath>
<pathelement location="lib/junit4.jar"/>
<pathelement location="lib/hamcrest-core-1.3.jar"/>
<pathelement location="${classes}"/>
</classpath>
<formatter type="brief" usefile="false"/>
<test name="HelloworldTest"/>
</junit>
</target>
<target name="clean">
<delete dir="${classes}"/>
</target>
</project>
```

上面我们有delete（删除）、mkdir（创建新目录）、javac（编译）、java（运行）、junit（单元测试）等任务。

此外，copy（拷贝）、jar（打包）、echo（输出）等任务也是很常用的。





## Junit

JUnit 是 Java 语言的单元测试框架，目前大多数的Java集成开发环境都已将Junit集成，作为其单元测试工具。

它可以支持我们进行测试驱动的开发，在极限编程中，我们由此先写测试代码，然后再写程序代码。这样可以帮助我们更好地思考程序的逻辑与功能，写一点测试然后写一点代码的增量方式也可以及时发现存在的问题。

在上面Ant部分我们给出的构建文件build.xml中，我们在编译和运行测试时都引入了junit4.jar这个包。我们给出Helloworld项目的测试类如下：

```java
import org.junit.Test;

public class HelloworldTest {

    @Test
    public void main() {
        Helloworld.test("Lisa");
    }
}
```

首先在测试类文件中，我们应该包含必要的包，比如上面`import org.junit.Test;`。

我们给定了一个注释**@Test**，它可以将一个普通方法修饰为测试方法。另外比如还有**@Before**可以让一个方法在每个测试用例前执行一次，**@BeforeClass**可以让一个方法在所有测试用例的前面执行一次等等。更多注释可参考https://www.guru99.com/junit-annotations-api.html。

Junit提供了一些类，来为测试方法提供一些可调用的测试工具，比如Assert、TestCase、TestResult、TestSuite等类，可参考：https://www.tutorialspoint.com/junit/junit_api.htm。

Junit提供了时间测试和异常测试：

时间测试——即@Test(timeout)，如果测试用例花费的时间超过了指定的毫秒数，那么 JUnit 会自动将其标记为失败。

异常测试——即@Test(expected)，测试代码是否引发所需的异常。

可参考：https://www.tutorialspoint.com/junit/junit_time_test.htm与https://www.tutorialspoint.com/junit/junit_exceptions_test.htm。
