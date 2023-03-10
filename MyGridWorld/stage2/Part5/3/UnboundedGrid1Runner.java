import info.gridworld.actor.*;
import info.gridworld.grid.*;

/**
 * This class runs a world that contains unbounded grids. <br />
 * This class is not tested on the AP CS AB exam.
 */
public class UnboundedGrid1Runner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("UnboundedGrid1");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}
