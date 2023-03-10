# 阶段2 问题和练习

## Part2

### **Set 2**

The source code for the BoxBug class can be found in the boxBug directory.

1. What is the role of the instance variable sideLength?

   sideLength用于记录BoxBug在一个方向上最多能走的步数，它的值为正方形边长-1，当BoxBug在一个方向上行走了sideLength步或者在此方向无法继续行走时，它就会执行两次`turn`方法转向90度。

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 45-55
    if (steps < sideLength && canMove())
    {
        move();
        steps++;
    }
    else
    {
        turn();
        turn();
        steps = 0;
    }
   ```

   

2. What is the role of the instance variable steps?

   steps记录了BoxBug在方格的当前边移动的距离，也是用于判断BoxBug是否需要转向的一个依据。

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 45-55
    if (steps < sideLength && canMove())
    {
        move();
        steps++;
    }
    else
    {
        turn();
        turn();
        steps = 0;
    }
   ```

   

3. Why is the turn method called twice when steps becomes equal to sideLength?

   `turn`方法被调用一次只会让BoxBug转向45度，而我们要做的是让它转向90度，所以得调用两次。

   ```cpp
   // @file: gridworld/actor/Bug.java
   // @line: 62
   setDirection(getDirection() + Location.HALF_RIGHT);
   
   // @file: gridworld/grid/Location.java
   // @line: 48
   public static final int HALF_RIGHT = 45;
   ```

   

4. Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?

   BoxBug类是Bug类的子类，继承了Bug类的`public void move()`方法。

   ```java
   // @file: projects/boxBug/boxBug.java
   // @line: 25
   public class BoxBug extends Bug
   
   // @file: gridworld/actor/Bug.java
   // @line: 71
   public void move()
   ```

   

5. After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?

   方形图案的大小应该总是相同的，因为构造 BoxBug时已经给定了这个大小sideLength，后续我们没有进行修改的操作，也没有进行修改的能力。

   ```java
   // @file: projects/boxBug/boxBug.java
   // @line: 34-38
   public BoxBug(int length)
   {
   	steps = 0;
   	sideLength = length;
   }
   ```

   

6. Can the path a BoxBug travels ever change? Why or why not?

   可以改变，当它无法前进时（遇到石头，遇到其他Bug，碰到边界等情况），它就会立即转向90度。

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 45-55
    if (steps < sideLength && canMove())
    {
        move();
        steps++;
    }
    else
    {
        turn();
        turn();
        steps = 0;
    }
   ```

   

7. When will the value of steps be zero?

   BoxBug被构造出来时，steps的值为0。

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 36
   steps = 0;
   ```

   当BoxBug发生转向时，steps的值也会变为0。

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 54
   steps = 0;
   ```

   

### **Set 3** 

Assume the following statements when answering the following questions.

```
Location loc1 = new Location(4, 3);
Location loc2 = new Location(3, 4);
```

1. How would you access the row value for loc1?

   `loc1.getRow()`，即调用Location类的`getRow`方法，该方法有如下实现：

   ```java
   // @file: gridworld/grid/Location.java
   // @line: 110-113
   public int getRow()
   {
   	return row;
   }
   ```

   

2. What is the value of b after the following statement is executed?

   ```
    boolean b = loc1.equals(loc2);
   ```

   `False`。Location类的`equaks`方法有如下实现：

   ```java
   // @file: gridworld/grid/Location.java
   // @line: 205-212
   public boolean equals(Object other)
   {
   	if (!(other instanceof Location))
   		return false;
   
   	Location otherLoc = (Location) other;
   	return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
   }
   ```

   

3. What is the value of loc3 after the following statement is executed?

   ```
    Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);
   ```

   `(4, 4)`。Location类的`getAdjacentLocation`方法中对SOUTH情况处理为：

   ```java
   // @file: gridworld/grid/Location.java
   // @line: 147-148
   else if (adjustedDirection == SOUTH)
   	dr = 1;
   ```

   最后dr的值作用体现在：

   ```java
   // @file: gridworld/grid/Location.java
   // @line: 168
   return new Location(getRow() + dr, getCol() + dc);
   ```

   

4. What is the value of dir after the following statement is executed?

   ```
    int dir = loc1.getDirectionToward(new Location(6, 5));
   ```

   `135`。loc1坐标为`(4, 3)`，对于坐标`(6, 5)`，dx = 2且dy = 2，计算知方向为东南（即135度）。参考`getDirectionToward`方法的实现如下：

   ```java
   // @file: gridworld/grid/Location.java
   // @line: 168
   public int getDirectionToward(Location target)
   {
   	int dx = target.getCol() - getCol();
   	int dy = target.getRow() - getRow();
   	// y axis points opposite to mathematical orientation
   	int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));
   
   	// mathematical angle is counterclockwise from x-axis,
   	// compass angle is clockwise from y-axis
   	int compassAngle = RIGHT - angle;
   	// prepare for truncating division by 45 degrees
   	compassAngle += HALF_RIGHT / 2;
   	// wrap negative angles
   	if (compassAngle < 0)
   		compassAngle += FULL_CIRCLE;
   	// round to nearest multiple of 45
   	return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
   }
   ```

   

5. How does the getAdjacentLocation method know which adjacent location to return?

   `getAdjacentLocation`方法对于给定的角度进行处理，找到与给定角度方向最为相邻的角度，并得到与当前坐标的坐标差值dr和dc，最后得到相邻的位置的坐标，如下：

   ```java
   // @file: gridworld/grid/Location.java
   // @line: 168
   return new Location(getRow() + dr, getCol() + dc);
   ```

   

### **Set 4**

1. How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

   我们设当前网格对象为grid。如下可以获知网格中对象的数量：

   ```java
   int objectCount = grid.getOccupiedLocations().count();
   ```

   然后如下可以获知有界的网格中空位置的数量：

   ```java
   int emptyLocationCount = grid.getNumRows() * grid.getNumCols() - objectCount;
   ```

   上面我们调用了三个Grid类中的方法：

   ```java
   // @file: gridworld/grid/Grid.java
   // @line: 35 41 85
   int getNumRows();
   int getNumCols();
   ArrayList<Location> getOccupiedLocations();
   ```

2. How can you check if location (10,10) is in a grid?

   创建一个Location实例并调用`isValid`方法。如：

   ```java
   boolean result = grid.isValid(new Location(10,10));
   ```

   对于`isValid`方法，Grid类中有定义如下：

   ```java
   // @file: gridworld/grid/Grid.java
   // @line: 50
   boolean isValid(Location loc);
   ```

3. Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?

   Grid只是一个接口类，需要其他类来继承并实现其中定义的方法。从它的定义我们就可以看出它是接口类：

   ```java
   // @file: gridworld/grid/Grid.java
   // @line: 29
   public interface Grid<E>
   ```

   在抽象类AbstractGrid实现了一些方法。

   ```java
   // @file: gridworld/grid/AbstractGrid.java
   // @line: 26
   public abstract class AbstractGrid<E> implements Grid<E>
   ```

   然后BoundedGrid类和UnboundedGrid类又继承了抽象类，并在中实现了剩下的部分。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 29
   public class BoundedGrid<E> extends AbstractGrid<E>
   ```

   ```java
   // @file: gridworld/grid/UnboundedGrid.java
   // @line: 31
   public class UnboundedGrid<E> extends AbstractGrid<E>
   ```

   

4. All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

   我认为使用ArrayList是比使用普通的数组更好的选择。ArrayList的初始化和拷贝更加方便，比如它不要求我们先确定大小再向里面填充对象。它提供的一些方法，比如`add()`之类有时也更为便捷。

   

### **Set 5**

1. Name three properties of every actor.

   位置(location)、方向(direction)、颜色(color)。这个答案来自Actor类中的定义：

   ```java
   // @file: gridworld/actor/Actor.java
   // @line: 32-34
   private Location location;
   private int direction;
   private Color color;
   ```

   

2. When an actor is constructed, what is its direction and color?

   方向为北，颜色为蓝色。参考Actor类中的默认构造函数：

   ```java
   // @file: gridworld/actor/Actor.java
   // @line: 32-34
   color = Color.BLUE;
   direction = Location.NORTH;
   ```

   

3. Why do you think that the Actor class was created as a class instead of an interface?

   对于Actor除了行为之外，它还有一些属性，对于不同种类的Actor这些属性是共有的，一些方法也是共有的，而接口类不允许声明实例变量和实现方法，所以我们创建一个基类更符合我们的需求。

   一些共有的属性：

   ```java
   // @file: gridworld/actor/Actor.java
   // @line: 32-34
   private Location location;
   private int direction;
   private Color color;
   ```

   

4. Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

   一个actor不能在没有先移除自己的情况下再次将自己放入一个grid中，会有报错。

   ```java
   // @file: gridworld/actor/Actor.java
   // @line: 117-119
   if (grid != null)
   	throw new IllegalStateException(
   		"This actor is already contained in a grid.");
   ```

   一个actor也不能将自己从一个grid中移除两次。

   ```java
   // @file: gridworld/actor/Actor.java
   // @line: 135-137
   if (grid == null)
   	throw new IllegalStateException(
   		"This actor is not contained in a grid.");
   ```

   一个actor可以把自己放到一个grid中，然后移除，之后再将它放回去。因为这一系列操作不会受上述限制影响。

   

5. How can an actor turn 90 degrees to the right?

   可以使用`setDirection`方法进行转向，如下：

   ```java
   actor.setDirection(getDirection() + Location.RIGHT);
   ```

   或者：

   ```java
   actor.setDirection(getDirection() + 90);
   ```

   二者是等效的：

   ```java
   // @file: gridworld/grid/Grid.java
   // @line: 40
   public static final int RIGHT = 90;
   ```

   

### **Set 6**

1. Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

   下面的代码使得，如果下一步的位置非法，那么一只bug不会如此移动。

   ```java
   // @file: gridworld/actor/Bug.java
   // @line: 98-99
   if (!gr.isValid(next))
   	return false;
   ```

   

2. Which statement(s) in the canMove method determines that a bug will not walk into a rock?

   只有下一个位置为空或者为花，`canmove()`方法才返回True。

   ```java
   // @file: gridworld/actor/Bug.java
   // @line: 100-101
   Actor neighbor = gr.get(next);
   return (neighbor == null) || (neighbor instanceof Flower);
   ```

   

3. Which methods of the Grid interface are invoked by the canMove method and why?

   `isValid()`和`get()`方法。分别用于判断Bug的下一个位置是否合法和获取对应位置存在的Actor。

   ```java
   // @file: gridworld/actor/Bug.java
   // @line: 98-99
   if (!gr.isValid(next))
   	return false;
   ```

   ```java
   // @file: gridworld/actor/Bug.java
   // @line: 100
   Actor neighbor = gr.get(next);
   ```

   

4. Which method of the Location class is invoked by the canMove method and why?

   `getAdjacentLocation()`方法。用于获取Bug按某一方向前进到达的下一个位置。

   ```java
   // @file: gridworld/actor/Bug.java
   // @line: 97
   Location next = loc.getAdjacentLocation(getDirection());
   ```

   

5. Which methods inherited from the Actor class are invoked in the canMove method?

   `getGrid()`、`getLocation()`和`getDirection()`方法。

   ```java
   // @file: gridworld/actor/Bug.java
   // @line: 93 96 97
   Grid<Actor> gr = getGrid();
   Location loc = getLocation();
   Location next = loc.getAdjacentLocation(getDirection());
   ```

   

6. What happens in the move method when the location immediately in front of the bug is out of the grid?

   Bug会将自己从Grid中移除掉。

   ```java
   // @file: gridworld/actor/Bug.java
   // @line: 78-81
   if (gr.isValid(next))
   	moveTo(next);
   else
   	removeSelfFromGrid();
   ```

   

7. Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

   loc是在`move`方法中是需要的。它存储了Bug移动前的位置，当Bug移动到新的位置后，我们可以仍然可以知道应该插入一朵花在grid的哪个位置。

   ```java
   // @file: gridworld/actor/Bug.java
   // @line: 77
   Location next = loc.getAdjacentLocation(getDirection());
   ```

   ```java
   // @file: gridworld/actor/Bug.java
   // @line: 83
   flower.putSelfInGrid(gr, loc);
   ```

   如果多次调用`getLocation()`方法可能要更耗费资源。

   

8. Why do you think the flowers that are dropped by a bug have the same color as the bug?

   我们在插入一朵花时，指定了它的颜色为Bug当前的颜色。

   ```java
   // @file: gridworld/actor/Bug.java
   // @line: 82
   Flower flower = new Flower(getColor());
   ```

   

9. When a bug removes itself from the grid, will it place a flower into its previous location?

   不会。只有移动时它会放一朵花在原来的位置，直接移除是不会放花的。

   ```java
   // @file: gridworld/actor/Actor.java
   // @line: 143-145
   grid.remove(location);
   grid = null;
   location = null;
   ```

   

10. Which statement(s) in the move method places the flower into the grid at the bug’s previous location?

    `flower.putSelfInGrid(gr, loc);`

    ```java
    // @file: gridworld/actor/Bug.java
    // @line: 82-83
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
    ```

    

11. If a bug needs to turn 180 degrees, how many times should it call the turn method?

    4次。因为每次调用`turn`方法会转动45度。

    ```java
    // @file: gridworld/grid/Location.java
    // @line: 48
    public static final int HALF_RIGHT = 45;
    ```

    ```java
    // @file: gridworld/actor/Bug.java
    // @line: 64
    setDirection(getDirection() + Location.HALF_RIGHT);
    ```

    

## Part4

### Set 7

The source code for the Critter class is in the critters directory

1. What methods are implemented in Critter?

   `act`,`getActors`,`processActors`,`getMoveLocations`,`selectMoveLocation`,`makeMove `六个方法。

   ```java
   // @file: gridworld/actor/Critter.java
   // @line: 38 56 71 88 104 125
   public void act()
   public ArrayList<Actor> getActors()
   public void processActors(ArrayList<Actor> actors)
   public ArrayList<Location> getMoveLocations()
   public Location selectMoveLocation(ArrayList<Location> locs)
   public void makeMove(Location loc)
   ```

   

2. What are the five basic actions common to all critters when they act?

   5个共同的动作是`getActors`,`processActors`,`getMoveLocations`,`selectMoveLocation`,`makeMove `。

   

3. Should subclasses of Critter override the getActors method? Explain.

   必须重载`getActors`方法。因为子类里对于获取actor的规则或者条件都有可能会不同。源代码注释中亦有提到：

   ```java
   // @file: gridworld/actor/Critter.java
   // @line: 50-52
   * Gets the actors for processing. Implemented to return the actors that
   * occupy neighboring grid locations. Override this method in subclasses to
   * look elsewhere for actors to process.<br />
   ```

   

4. Describe the way that a critter could process actors.

   一个critter可以对一些actor进行处理，比如移除指定的actors，改变它们的方向、位置等等，可以参考`processActors`方法的注释如下：

   ```java
   // @file: gridworld/actor/Critter.java
   // @line: 62-65
   * Processes the elements of <code>actors</code>. New actors may be added
   * to empty locations. Implemented to "eat" (i.e. remove) selected actors
   * that are not rocks or critters. Override this method in subclasses to
   * process actors in a different way. <br />
   ```

   

5. What three methods must be invoked to make a critter move? Explain each of these methods.

   `getMoveLocations`,`selectMoveLocation`和`makeMove `。

   首先调用`getMoveLocations`方法来获取当前能够移动到的位置（组成的数组），然后调用`selectMoveLocation`方法从`getMoveLocations`方法的返回结果中选择移动的目标位置，最后调用`makeMove `方法将critter移动到目标位置。

   ```java
   // @file: gridworld/actor/Critter.java
   // @line: 88 104 125
   public ArrayList<Location> getMoveLocations()
   public Location selectMoveLocation(ArrayList<Location> locs)
   public void makeMove(Location loc)
   ```

   

6. Why is there no Critter constructor?

   Critter是Actor的子类，而Actor中已经定义了构造函数，所以Java会为Critter提供一个构造函数，它调用Critter的父类Actor的构造函数。我们不需要再写一个Critter的构造函数，即会默认创建一个方向朝北的蓝色Critter。

   ```java
   // @file: gridworld/actor/Actor.java
   // @line: 39-45
   public Actor()
   {
   	color = Color.BLUE;
   	direction = Location.NORTH;
   	grid = null;
   	location = null;
   }
   ```

   

### **Set 8** 

The source code for the ChameleonCritter class is in the critters directory

1. Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

   `act`方法调用了`processActors`和`makeMove`两个方法，而这两个方法在ChameleonCritter中被重写了，产生的结果就是运动的效果不同了。

   ```java
   // @file: projects/critter/ChameleonCritter.java
   // @line: 36 50
   public void processActors(ArrayList<Actor> actors)
   public void makeMove(Location loc)
   ```

   

2. Why does the makeMove method of ChameleonCritter call super.makeMove?

   ChameleonCritter类中重写的`makeMove`方法在基类的此方法的基础上，增加了设置其面向的方向的语句。而其他部分与基类中的`makeMove`方法相同，所以这样写来让代码更为简洁。

   ```java
   // @file: projects/critter/ChameleonCritter.java
   // @line: 50-54
   public void makeMove(Location loc)
   {
   	setDirection(getLocation().getDirectionToward(loc));
   	super.makeMove(loc);
   }
   ```

   

3. How would you make the ChameleonCritter drop flowers in its old location when it moves?

   这需要我们参照Bug类的`move`方法来对ChameleonCritter类的`makeMove`方法进行修改。修改后的参考代码如下：

   ```java
   public void makeMove(Location loc)
   {
       Grid<Actor> gr = getGrid();
   		if (gr == null)
   			return;
       Location loc2 = getLocation();
   	setDirection(getLocation().getDirectionToward(loc));
   	super.makeMove(loc);
       if(!loc.equals(loc2)){
           Flower flower = new Flower(getColor());
           flower.putSelfInGrid(gr, loc2);
       }
   }
   ```

   

4. Why doesn’t ChameleonCritter override the getActors method?

   ChameleonCritter类重写的`processActors`的方法中，使用了`act`方法中调用`getActors`方法得到的相邻的Actor列表，使用的方式不过是随机选择一个相邻的Actor的颜色作为自己的颜色，在不重写的前提下已经能够满足需求，所以没有对`getActors`方法进行重新重写。

   ```java
   // @file: gridworld/actor/Critter.java
   // @line: 42-43
   ArrayList<Actor> actors = getActors();
   processActors(actors);
   ```

   

5. Which class contains the getLocation method?

   Actor类。

   ```java
   // @file: gridworld/actor/Actor.java
   // @line: 102
   public Location getLocation()
   ```

   

6. How can a Critter access its own grid?

   调用其基类Actor中的`getGrid`方法即可。

   ```java
   // @file: gridworld/actor/Actor.java
   // @line: 92
   public Grid<Actor> getGrid()
   ```

   

### **Set 9**

The source code for the CrabCritter class is reproduced at the end of this part of GridWorld.

1. Why doesn’t CrabCritter override the processActors method?

   CrabCritter被设计为吃掉指定范围的Flower，父类Critter提供的`processActors`方法的处理是，对于给定的Actor，如果不为Rock，亦不为Critter，则建轻轨i移除。而这和我们的需求一致，所以无需重写。

   ```java
   // @file: gridworld/actor/Critter.java
   // @line: 71-78
   public void processActors(ArrayList<Actor> actors)
   {
   	for (Actor a : actors)
   	{
   		if (!(a instanceof Rock) && !(a instanceof Critter))
   			a.removeSelfFromGrid();
   	}
   }
   ```

   

2. Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

   CarbCritter会先在`getActors`方法中获取目标范围（正前方、右前方和左前方）的Actor，然后通过`processAcors` 方法继续处理（吃掉满足条件的Actor）这些Actors。

   ```java
   // @file: gridworld/actor/CarbCritter.java
   // @line: 44-57
   public ArrayList<Actor> getActors()
   {
   	ArrayList<Actor> actors = new ArrayList<Actor>();
   	int[] dirs =
   	{ Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
   	for (Location loc : getLocationsInDirections(dirs))
   	{
   		Actor a = getGrid().get(loc);
   		if (a != null)
   			actors.add(a);
   	}
   
   	return actors;
   }
   ```

   它不会吃掉所有相邻的actors，它的目标只是前方（比如左后方上如果有一朵Flower就不会被吃掉）。

   

3. Why is the getLocationsInDirections method used in CrabCritter?

   CrabCritter类中通过`getLocationsInDirections`方法来获取合法的相邻位置，这个方法要求传入一个相对方向数组，返回的则是根据这些方向找到的合法位置。

   比如寻找吃掉的对象的位置时：

   ```java
   // @file: gridworld/actor/CarbCritter.java
   // @line: 47-49
   int[] dirs =
   	{ Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
   for (Location loc : getLocationsInDirections(dirs))
   ```

   再比如寻找CrabCritter下一个移动的位置时：

   ```java
   // @file: gridworld/actor/CarbCritter.java
   // @line: 65-67
   int[] dirs =
   	{ Location.LEFT, Location.RIGHT };
   for (Location loc : getLocationsInDirections(dirs))
   ```

   这样我们就可以实现复用部分代码。

   

4. If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

   `(4, 4)`、`(4, 3)`和`(4, 5)`。

   ```java
   // @file: gridworld/actor/CarbCritter.java
   // @line: 47-49
   int[] dirs =
   	{ Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
   for (Location loc : getLocationsInDirections(dirs))
   ```

5. What are the similarities and differences between the movements of a CrabCritter and a Critter?

   **相同点：**

   它们移动时不会向它们移动的方向转向。

   ```java
   // @file: gridworld/actor/Critter.java
   // @line: 130
   moveTo(loc);
   ```

   ```java
   // @file: projects/critter/CrabCritter.java
   // @line: 90
   super.makeMove(loc);
   ```

   都是从其可能的移动地点列表中随机选择下一个地点。

   ```java
   // @file: gridworld/actor/Critter.java
   // @line: 109-110
   int r = (int) (Math.random() * n);
   return locs.get(r);
   ```

   ```java
   // @file: projects/critter/CrabCritter.java
   // @line: 79-88
   if (loc.equals(getLocation()))
   {
   	double r = Math.random();
   	int angle;
   	if (r < 0.5)
   		angle = Location.LEFT;
   	else
   		angle = Location.RIGHT;
   	setDirection(getDirection() + angle);
   }
   ```

   **不同点：**

   CrabCritter只能向其左边或右边移动，Critter可能的移动位置是八个相邻位置中的任何一个。

   ```java
   // @file: gridworld/actor/CarbCritter.java
   // @line: 65-67
   int[] dirs =
   	{ Location.LEFT, Location.RIGHT };
   for (Location loc : getLocationsInDirections(dirs))
   ```

   遇到无法移动的情况下，CrabCritter会转向，Critter不会转向。

   ```java
   // @file: projects/critter/CrabCritter.java
   // @line: 79-88
   if (loc.equals(getLocation()))
   {
   	double r = Math.random();
   	int angle;
   	if (r < 0.5)
   		angle = Location.LEFT;
   	else
   		angle = Location.RIGHT;
   	setDirection(getDirection() + angle);
   }
   ```

   

6. How does a CrabCritter determine when it turns instead of moving?

   移动的目标位置等于当前的位置时，CrabCritter就会用转向来代替移动。

   ```java
   // @file: projects/critter/CrabCritter.java
   // @line: 79-88
   if (loc.equals(getLocation()))
   {
   	double r = Math.random();
   	int angle;
   	if (r < 0.5)
   		angle = Location.LEFT;
   	else
   		angle = Location.RIGHT;
   	setDirection(getDirection() + angle);
   }
   ```

   

7. Why don’t the CrabCritter objects eat each other?

   CrabCritter类继承了Critter类的`processActors`方法，这个方法中只会移除（“吃掉”）不是Rock也不是Critter的对象，而一个CrabCritter对象也是一个Critter对象，所以CrabCritter对象不会互相吃掉。

   ```java
   // @file: gridworld/actor/Critter.java
   // @line: 75-76
   if (!(a instanceof Rock) && !(a instanceof Critter))
   	a.removeSelfFromGrid();
   ```

   

## Part 5

### **Set 10**

The source code for the AbstractGrid class is in Appendix D.

1. Where is the isValid method specified? Which classes provide an implementation of this method?

   `isValid`方法是在Grid接口中被声明的。

   ```java
   // @file: gridworld/grid/Grid.java
   // @line: 50
   boolean isValid(Location loc);
   ```

   而在BoundedGrid和UnboundedGrid类中提供了这一方法的一种实现。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 60-64
   public boolean isValid(Location loc)
   {
   	return 0 <= loc.getRow() && loc.getRow() < getNumRows()
   		&& 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   ```

   ```java
   // @file: gridworld/grid/UnboundedGrid.java
   // @line: 53-56
   public boolean isValid(Location loc)
   {
   	return true;
   }
   ```

   

2. Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?

   `getValidAdjacentLocations`方法中调用了`isValid`方法。

   ```java
   // @file: gridworld/grid/AbstractGrid.java
   // @line: 44
   if (isValid(neighborLoc))
   ```

   `getEmptyAdjacentLocations`和`getOccupiedAdjacentLocations`没有直接调用`isValid`方法，而是调用了`getValidAdjacentLocations`方法，这样做其实是出于代码重用的考虑。

   

3. Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?

   `getOccupiedAdjacentLocations`和`get`方法被`getNeighbors`方法调用。

   ```java
   // @file: gridworld/grid/AbstractGrid.java
   // @line: 31-32
   for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
   	neighbors.add(get(neighborLoc));
   ```

   AbstractGrid类实现了`getOccupiedAdjacentLocations`方法，BoundedGrid和UnboundedGrid类中实现了`get`方法。

   ```java
   // @file: gridworld/grid/AbstractGrid.java
   // @line: 62
   public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
   ```

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 85
   public E get(Location loc)
   ```

   ```java
   // @file: gridworld/grid/UnboundedGrid.java
   // @line: 66
   public E get(Location loc)
   ```

   

4. Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

   `get`方法在`getEmptyAdjacentLocations`方法被调用，用于测试这个位置是否有对象存在。`get`方法返回一个存储在给定位置的网格中的对象的引用，如果没有对象存在，则返回null。所以如果这个位置没有对象存在（返回null），那么该位置为EmptyAdjacentLocation，就加到ArrayList中去。

   ```java
   // @file: gridworld/grid/AbstractGrid.java
   // @line: 56-57
   if (get(neighborLoc) == null)
   	locs.add(neighborLoc);
   ```

   

5. What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?

   `getValidAdjacentLocations`会变成只考虑东西南北四个方向的位置（不修改前为考虑八个方向）。

   ```java
   // @file: gridworld/grid/AbstractGrid.java
   // @line: 41-47
   for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
   {
   	Location neighborLoc = loc.getAdjacentLocation(d);
   	if (isValid(neighborLoc))
   		locs.add(neighborLoc);
   	d = d + Location.HALF_RIGHT;
   }
   ```

   

### **Set 11**

The source code for the BoundedGrid class is in Appendix D.

1. What ensures that a grid has at least one valid location?

   行数和列数都大于0。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 41-44
   if (rows <= 0)
   	throw new IllegalArgumentException("rows <= 0");
   if (cols <= 0)
   	throw new IllegalArgumentException("cols <= 0");
   ```

   

2. How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

   `occupantArray[0].length  `

   我们规定行数和列数都大于0，所以网格中至少有一行和一列，从而保证了可以取`occupantArray[0].length`为列数。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 53-58
   public int getNumCols()
   {
   	// Note: according to the constructor precondition, numRows() > 0, so
   	// theGrid[0] is non-null.
   	return occupantArray[0].length;
   }
   ```

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 41-44
   if (rows <= 0)
   	throw new IllegalArgumentException("rows <= 0");
   if (cols <= 0)
   	throw new IllegalArgumentException("cols <= 0");
   ```

   

3. What are the requirements for a Location to be valid in a BoundedGrid?

   行的值大于等于0并小于BoundedGrid中行的数量，且列的值大于等于0并小于BoundedGrid中列的数量。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 60-64
   public boolean isValid(Location loc)
   {
   	return 0 <= loc.getRow() && loc.getRow() < getNumRows()
   		&& 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   ```

   

   *In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.*

4. What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

   返回的类型为`ArrayList<Location>`。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 66
   public ArrayList<Location> getOccupiedLocations()
   ```

   时间复杂度为O(r * c)，因为BoundedGrid中的每个位置都需要被访问以获知其是否被占用。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 70-80
   // Look at all grid locations.
   for (int r = 0; r < getNumRows(); r++)
   {
   	for (int c = 0; c < getNumCols(); c++)
   	{
   		// If there's an object at this location, put it in the array.
   		Location loc = new Location(r, c);
   		if (get(loc) != null)
   			theLocations.add(loc);
   	}
   }
   ```

   

5. What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

   返回的类型为`E`。需要的参数是一个Location对象。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 85
   public E get(Location loc);
   ```

   时间复杂度为O(1)，因为操作是访问一个给定行和列值的二维数组。

   

6. What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

   参数Location对象非法（抛出`IllegalArgumentException`）和参数E对象为空（抛出`NullPointerException`）。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 95-99
   if (!isValid(loc))
   	throw new IllegalArgumentException("Location " + loc
   		+ " is not valid");
   if (obj == null)
   	throw new NullPointerException("obj == null");
   ```

   时间复杂度为O(1)。

   

7. What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?

   返回的类型为`E`。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 107
   public E remove(Location loc)
   ```

   试图从一个空的位置移除一个对象时会返回null，不会报错。

   ```java
   // @file: gridworld/grid/BoundedGrid.java
   // @line: 114 116
   E r = get(loc);
   return r;
   ```

   时间复杂度为O(1)。

   

8. Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

   可以认为这是一个高效的实现。除了`getOccupiedLocations`方法的时间复杂度为O(r * c)以外，其他的方法的时间复杂度都为O(1)。不过我们还可以考虑用哈希表来存储已被占用的位置来进一步对这个实现进行优化。



### **Set 12**

The source code for the UnboundedGrid class is in Appendix D.

1. Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?

   `equals`和`hashCode`方法是Location类必须要实现的方法。前者判断两个Location是否相等并给出一个布尔值作为返回值，后者提供了一个哈希编码的方式。对于相等的两个Location应当`hashCode`的返回值也是一样的。

   ```java
   // @file: gridworld/grid/Location.java
   // @line: 205 218
   public boolean equals(Object other){}
   public int hashCode(){}
   ```

   若使用TreeMap取而代之，`comparedTo`方法必须被实现，来让我们可以比较两个Location的大小，因为构建TreeMap，主键需要是能够比较大小的。

   ```java
   // @file: gridworld/grid/Location.java
   // @line: 234
   public int compareTo(Object other)
   ```

   Location类满足这些要求。

   

2. Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?

   UnboundedGrid中`isValid`方法总是返回true，任意一个位置都是合法的。但我们用哈希表作为数据结构来保存网格中的对象，哈希表中`get`、`put`、`remove`等方法没有对于参数为null的情况进行处理，但null并不是一个有效位置，所以我们需要引入对null的额外检查，及时抛出异常。比如下面`get`方法就引入了额外的检查：

   ```java
   // @file: gridworld/grid/UnboundedGrid.java
   // @line: 66-71
   public E get(Location loc)
   {
   	if (loc == null)
   		throw new NullPointerException("loc == null");
   	return occupantMap.get(loc);
   }
   ```

   而对于BoundedGrid类，我们使用一个二维数组作为数据结构来保存网格中的对象，如果Location为空，访问数组就会抛出空指针异常，无需我们引入额外的代码。

   

3. What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?

   对于哈希表，`get`、`put`、`remove`方法的平均时间复杂度都是O(1)。对于TreeMap，平均时间复杂度会变为O(log n)，n是被占用的格子的总数，log n是树的高度。

   

4. How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

   `getOccupiedLocations`方法的返回结果不同，使用哈希表返回的`ArrayList<Location>`是无序的，使用TreeMap返回的`ArrayList<Location>`是有序从小到大排列的。

   因为TreeMap中二叉平衡树本身在遍历后，得到的就是有序的`ArrayList<Location>`（顺序其实是由Location中`compareTo`方法定义）。

   

5. Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?

   可以用Map来实现BoundedGrid。
   
   使用二维数组实现的好处：
   
   数组元素的访问时间复杂度稳定为O(1)，而Map中最坏情况的时间复杂度为O(n)。
   
   在比较满的网格中，Map会存储位置和对象，而二维数组只存储对象，所以空间占用会更节省。