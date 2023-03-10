import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.actor.Rock;
import java.awt.Color;
import info.gridworld.maze.*;

/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class MazeBugRunner
{
    public static void main(String[] args)
    {
        // Create a new world
        ActorWorld world = new ActorWorld();
        // Add a new MazeBug to the world
        world.add(new Location(0,0), new MazeBug());
        // Add a new Rock to the world
        world.add(new Location(1,1),new Rock());
        // Show the world
        world.show();
    }
}