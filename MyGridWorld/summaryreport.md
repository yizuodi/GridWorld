# 实训总结报告

“GridWorld案例提供了一个图形化环境用于可视化对象在二维网格中的交互”。

本次实训我们主要围绕这一案例，学习配置相关的实验环境（阶段一），基于Java语言编写不同的代码来实现不同（行为）的Actor对象、网格等（阶段二），并在此基础上继续完成三个拓展任务（阶段三）——ImageReader、MazeBug和N-Puzzle。

## Stage1

在第一个阶段，我们主要进行了一些项目的启动工作。

在之前的实训和其他课程学习的基础上，Java语言的入门和Java JDK的熟悉过程还是比较轻松的，我们很轻松就编译运行了HelloWorld程序。

后面使用Ant实现自动编译（感觉有点像cmake）、使用Junit进行单元测试、使用SonarQube进行代码评测分析的过程，我还是遇到了不少的问题。在我试图在自己PC的Ubuntu虚拟机中配置这些工具时，遇到了包括但不限于jar包中缺少模块、本地端口未开放等问题；一些问题是我自行配置的环境与云桌面环境不一致导致的，比如我配置的环境中SonarQube版本低于云桌面环境中的版本，导致两个环境下SonarQube评测结果存在较大差异。

在这个快速学习的过程中，对于我们需要编写的Ant所使用的build.xml文件、使用Junit框架的单元测试代码和Sonar评测需要的sonar-scanner.properties文件，我不断地参照搜寻到的参考资料进行编写，也不断地收获到了各种各样的报错。

当然，通过不断的查阅相关资料进行学习，上面的各种问题最终都在阶段一顺利得到解决，这也为后面阶段二和阶段三中利用这些工具完成相关任务打下了很好的基础。

另外，我们编写了一个简单的计算器，并初步探索了GridWorld实例，这一过程除了为阶段二编写代码提供铺垫和引导外，也让我们进一步熟悉了Java这门语言（比如我们用到的组件GUI，Actor和Bug、Rock类的继承关系等）。

## Stage2

这个阶段我们围绕GridWorld案例开展了一系列的工作。

在Part2，我们实现了CircleBug、SpiralBug、ZBug、DancingBug这四种Bug；在Part3，我们实现了“Jumper”；在Part4，我们实现了ModifiedChameleonCritter、ChameleonKid、RockHound、BlusterCritter、QuickCrab、KingCrab六种不同的Actor。这些不同的Actor各有特点，它们有着不同的行为，有时候又因为父类的实现，而产生一定的相似性。

Part2中我们还只是围绕“Bug”这类Actor，我们的工作也是在Bug的基础上开展的；而到了Part3，我们就经历了一个从分析再到设计、再到代码的编写和测试的较为完整的过程。

而在Part4中，我们希望有更多更丰富的Actor，所以我们实现了它们；在Part5中，我们更进一步，对这些Actor所在的网格Grid下手，研究网格底部的数据结构，用稀疏矩阵和TreeMap来代替原来有边界的网格的底层数据结构，然后通过动态分配数组的方式来进行实现另一种无边界的网格。

在不同的Part中，穿插了很多问题和练习，在解答这些问题的过程中，我也被引导着不断了解GridWorld案例中的一些实现，并以此为基础编写代码实现我们预期的内容（不同的Actor等等）。

## Stage3

这一阶段主要完成的是三个拓展任务。

第一个是ImageReader，这是一个对图像进行处理的任务。我们通过学习位图的基本结构，实现代码通过二进制流从位图文件中读取图像，并实现解析图像的三个颜色通道和灰度图，最后要调用Java API提供图片的导出保存功能。作为对Java进一步加深了解的一个任务，我在此任务中最大的感受是要对可能的异常保持关注——比如我们读写图像的过程都有可能发生异常，我目前实现的代码中是将异常捕捉写日志，但在实际的业务中，我们可能还要实现对异常进行处理的部分。

第二个是MazeBug，是一个基于GridWorld的拓展任务，我们要让Bug来走迷宫。这个任务中我们基于DFS深度优先搜索，为我们Bug的行动方式编写代码（重写移动的相关方法、提供回溯的相关方法等等）。在这一任务的进阶部分，我们参照实训文档给出的建议，统计行走正确路径时，选择4个方向的次数，从而让其在选择行走方向时，之前的“选择”可以影响到后面的决策。

第三个是Jigsaw，这里我们要解决N数码问题。我们通过使用盲目搜索和启发式搜索两种类型的搜索策略来解决问题，我们要实现的是盲目搜索中使用广度优先策略的搜索方法和启发式搜索中使用A*算法的估价函数。在估价函数部分，我一开始引入了欧几里得距离作为估价值的一部分。后来考虑，曼哈顿距离更符合这里我们移动时“横平竖直”的情况，遂将估价值中曼哈顿距离的权重加大，移除了欧几里得距离，搜索的效果果然好了很多。

## 总结

这次的实训过程对我来说是充满挑战的。快速学习和代码编写的训练给我带来了一定的压力，疫情防控的因素也让我们的实训受到了一定的影响。

但实训内容的安排上给了我们比较多的引导，对于实训中遇到的一些问题，课程群中TA和同学也给出了一些热心解答；而实训的内容也和我们的专业其他课程密切相关——比如BFS、DFS、A*等算法在人工智能、数据结构与算法等课程学习过，“软件测试”是我们即将学习到的专业课之一等等。

总的来说，收获满满。