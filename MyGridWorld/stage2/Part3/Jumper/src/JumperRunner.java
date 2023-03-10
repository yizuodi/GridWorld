import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.*;
import java.awt.Color;

/**
 * This class runs a world that contains jumper. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class JumperRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(1, 1), alice);
        world.add(new Location(1, 3), new Rock());
        world.add(new Location(3, 3), new Flower());
        world.show();
    }
}
