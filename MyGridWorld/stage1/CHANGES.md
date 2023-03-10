# 变动说明

此版本（Stage1 v2）较前一版本（Stage1 v1）有如下两项变动：

1.所有的构建文件（build.xml）中junit组件添加了fork属性，值为"yes"。

即`<Junit>`  ->  `<Junit fork="yes">`

**变动原因**：在云桌面环境下，测试时会缺少依赖。



2.Calculator类的main方法中，创建新对象的方法变动。

由`new Calculator();`变为`Calculator calculator = new Calculator();`

**变动原因**：在使用云桌面环境的SonarCube进行代码分析时，检测结果会出现一个critical的issue。