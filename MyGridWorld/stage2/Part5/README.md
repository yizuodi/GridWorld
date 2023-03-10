# Stage2 - Part5

### 1

编译运行：

```
javac -cp .:./../gridworld.jar SparseGridNode.java SparseBoundedGrid.java SparseBoundedRunner.java
java -cp .:./../gridworld.jar SparseBoundedRunner
```



### 2

编译运行：

```
javac -cp .:./../gridworld.jar SparseBoundedGrid.java SparseBoundedRunner.java
java -cp .:./../gridworld.jar SparseBoundedRunner
```



### 3

编译运行：

```
javac -cp .:./../gridworld.jar UnboundedGrid1.java UnboundedGrid1Runner.java
java -cp .:./../gridworld.jar UnboundedGrid1Runner
```



## Coding Exercises

1. 这里，我们实现了SparseGridNode类。然后在SparseBoundedGrid类（基于BoundedGrid类改写）中，我们用链表代替矩阵实现了一个有界的网格。  

   这是一个比 BoundedGrid 更省时的实现。对于一个r行c列的网格，我们的稀疏数组长度为r，其中每个链表最大长度可能为c，在`getOccupiedLocations`方法中，我们的时间复杂度为O(n+r)；对于普通的矩阵存储，在`getOccupiedLocations`方法中，我们的时间复杂度为O(r*c)。

2. 我们使用TreeMap来实现SparseBoundedGrid类。在UnboundedGrid类的基础上，我们改用TreeMap，然后沿用`getOccupiedLocations`、`get`、`put`和`remove`方法，修改构造函数、`getNumRows`、`getNumCols`、`isValid`方法即可。

   Let r = number of rows, c = number of columns, and n = number of occupied locations

|            Methods             | `SparseGridNode` version | `LinkedList<OccupantInCol>` version | `HashMap` version | `TreeMap` version |
   | :----------------------------: | :----------------------: | :---------------------------------: | :---------------: | :---------------: |
   |         `getNeighbors`         |           O(c)           |                O(c)                 |       O(1)        |     O(log n)      |
   |  `getEmptyAdjacentLocations`   |           O(c)           |                O(c)                 |       O(1)        |     O(log n)      |
   | `getOccupiedAdjacentLocations` |           O(c)           |                O(c)                 |       O(1)        |     O(log n)      |
   |     `getOccupiedLocations`     |          O(r+n)          |               O(r+n)                |       O(n)        |       O(n)        |
   |             `get`              |           O(c)           |                O(c)                 |       O(1)        |     O(log n)      |
   |             `put`              |           O(c)           |                O(c)                 |       O(1)        |     O(log n)      |
   |            `remove`            |           O(c)           |                O(c)                 |       O(1)        |     O(log n)      |

3. 对于`get`方法时间复杂度为O(1)。对于`put`方法，如果不需要扩充网格，则时间复杂度为O(1)；需要扩充网格则时间复杂度为O(r*c)。

   