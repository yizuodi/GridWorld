import static org.junit.Assert.*;

import org.junit.Test;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class JumperTest {

    public ActorWorld world = new ActorWorld();
    public Jumper jumper = new Jumper();
    public Rock rock = new Rock();
    public Flower flower = new Flower();

    public JumperTest(){
        world.add(new Location(1, 1), jumper);
        world.add(new Location(1, 2), rock);
        world.add(new Location(1, 3), flower);
    }
    
    // 测试Jumper是否能正常移动2格，答案是YES
    @Test
    public void testMove() {
        jumper.moveTo(new Location(6, 8));
        jumper.act();
        Location loc = jumper.getLocation();
        Location destination = new Location(4, 8);
        assertEquals(loc, destination);
    }

    // 测试Jumper是否能正常移动2格，答案是YES
    @Test
    public void testMove2() {
        jumper.moveTo(new Location(6, 1));
        jumper.setDirection(Location.SOUTHEAST);
        jumper.act();
        Location loc = jumper.getLocation();
        Location destination = new Location(8, 3);
        assertEquals(loc, destination);
    }

    // 测试jumper是否能跳到一个Rock上，答案是NO
    @Test
    public void testJumpToRock() {
        jumper.moveTo(new Location(6, 8));
        rock.moveTo(new Location(4, 8));
        assertEquals(false, jumper.canMove());
    }

    // 测试Jumper是否能跳到一个Flower上，答案是YES
    @Test
    public void testJumpToFlower() {
        jumper.moveTo(new Location(6, 6));
        flower.moveTo(new Location(4, 6));
        assertEquals(true, jumper.canMove());
    }

    // 测试Jumper是否能跳到一个越界位置，答案是NO
    @Test
    public void testOutOfGrid() {
        jumper.moveTo(new Location(9, 9));
        jumper.setDirection(Location.EAST);
        assertEquals(false, jumper.canMove());
    }

    // 测试Jumper是否能跳过一个石头，答案是YES
    @Test
    public void testJumpOverRock() {
        jumper.moveTo(new Location(6, 4));
        jumper.setDirection(Location.EAST);
        rock.moveTo(new Location(6, 5));
        assertEquals(true, jumper.canMove());
    }

    // 测试Jumper是否能跳到一个Jumper上，答案是NO
    @Test
    public void testJumpToJumper() {
        jumper.moveTo(new Location(6, 2));
        Jumper jumper2 = new Jumper();
        world.add(new Location(4, 2), jumper2);
        assertEquals(false, jumper.canMove());
    }

}
