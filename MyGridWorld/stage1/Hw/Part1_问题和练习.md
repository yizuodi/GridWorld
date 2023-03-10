# Part1：Observing and Experimenting with GridWorld问题和练习

## Step1：Running the Demo

1. Does the bug always move to a new location? Explain.

   一只bug不总是会向新的位置移动。它只能移动到一个合法的位置（在我们10×10的范围内），而且那个位置需要为空，或者有花。

2. In which direction does the bug move?

   bug会朝它当前面向的方向向前移动，而它的基类Actor中属性direction描述了它当前面向的方向（单位为度数，正上方为0度）。

3. What does the bug do if it does not move?

   它会转向来调整自己面向的方向（每次调整会顺时针转45度）。

4. What does a bug leave behind when it moves?

   它会在bug之前的位置留下一朵和bug相同颜色的花。

5. What happens when the bug is at an edge of the grid? (Consider whether the bug is facing the edge as well as whether the bug is facing some other direction when answering this question.)

   当我们不加以干预的时候（通过按钮run让其自行移动），如果bug面向的方向执行move会让到达一个非法的位置（10×10的范围外），那么bug会调整自己的方向尝试找可以move的位置（位置合法、为空或者有花）。如果它面向的方向move后的位置合法，则和其他非边缘位置的处理方案一致。

   如果是我们人工干预使其move，那么面向边缘就会让其到达非法的位置从而消失，面向其他方向就会让其强制移动。

6. What happens when a bug has a rock in the location immediately in front of it?

   那么bug会认为这个方向是不可向前移动的，它会转向。

7. Does a flower move?

   花不会在step或者run的情况下自行移动，但我们可以人工干预使其“move”。

8. What behavior does a flower have?

   一朵花会随着时间推移变暗（变枯萎）。

9. Does a rock move or have any other behavior?

   石头在step或者run的情况不会有任何行为，但我们可以人工干预移动它的位置。

10. Can more than one actor (bug, flower, rock) be in the same location in the grid at the same time?

    不可以。

## Step2：Exploring Actor State and Behavior

1. Test the setDirection method with the following inputs and complete the table, giving the compass direction each input represents.

   | Degrees | Compass Direction |
   | :-----: | :---------------: |
   |    0    |       North       |
   |   45    |     NorthEast     |
   |   90    |       East        |
   |   135   |     SouthEast     |
   |   180   |       South       |
   |   225   |     SouthWest     |
   |   270   |       West        |
   |   315   |     NorthWest     |
   |   360   |       North       |

2. Move a bug to a different location using the moveTo method. In which directions can you move it? How far can you move it? What happens if you try to move the bug outside the grid?

   moveTo方法可以让我们将bug移到任何方向的任何合法位置（10×10的范围内的位置），最远可以移到边界。如果移动到的位置上有任何其他Actor，它会把那个Actor销毁取而代之。而且移动不会改变它的朝向位置。

   如果我们尝试将它移动到方格之外，移动不会成功，我们会收到报错提示。

3. Change the color of a bug, a flower, and a rock. Which method did you use?

   它们使用的都是从它们的基类Actor而来的setColor()方法。

4. Move a rock on top of a bug and then move the rock again. What happened to the bug?

   bug不见了，正如上面第2个小问题使用moveTo方法移动bug一样，移动一个Actor到另一个Actor的位置上，会将这个位置原先的Actor销毁掉从而取而代之。