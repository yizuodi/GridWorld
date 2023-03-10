# Stage2 - Part2

## 1.CircleBug

编译运行：

```
javac -cp .:./../gridworld.jar CircleBug.java CircleBugRunner.java
java -cp .:./../gridworld.jar CircleBugRunner
```

## 2.SpiralBug

编译运行：

```
javac -cp .:./../gridworld.jar SpiralBug.java SpiralBugRunner.java
java -cp .:./../gridworld.jar SpiralBugRunner
```

## 3.ZBug

编译运行：

```
javac -cp .:./../gridworld.jar ZBug.java ZBugRunner.java
java -cp .:./../gridworld.jar ZBugRunner
```

## 4.DancingBug

编译运行：

```
javac -cp .:./../gridworld.jar DancingBug.java DancingBugRunner.java
java -cp .:./../gridworld.jar DancingBugRunner
```

## 5.向网格中添加另一个BoxBug角色的步骤

首先创建一个BoxBug对象（`BoxBug boxbug2 = new BoxBug(num)`），然后为它设置颜色等属性（也可不设置），最后创建一个 Location 实例，将BoxBug对象添加到之前创建的ActorWorld实例中（ `world.add(new Location(num1, num2), boxbug2)`）。