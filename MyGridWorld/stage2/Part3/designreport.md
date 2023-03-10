# 阶段2Part 3 Jumper设计文档

## Inception: clarify the details of the problem:

a. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

如果前面两个单元格的位置包含的是Rock，那么这一步选择不移动，仅改变方向（调用`turn()`方法）。如果包含的是Flower，那么先移除Flower，然后跳到该位置。

b. What will a jumper do if the location two cells in front of the jumper is out of the grid?

这一步选择不移动，仅改变方向（调用`turn()`方法）。

c. What will a jumper do if it is facing an edge of the grid?

这一步选择不移动，仅改变方向（调用`turn()`方法）。

d. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

这一步选择不移动，仅改变方向（调用`turn()`方法）。

e. What will a jumper do if it encounters another jumper in its path?

这一步选择不移动，仅改变方向（调用`turn()`方法）。

f. Are there any other tests the jumper needs to make?

Jumper能否跳过其他类型的Actor对象等。
