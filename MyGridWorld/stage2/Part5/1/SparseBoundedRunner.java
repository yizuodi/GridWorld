import info.gridworld.actor.*;
import info.gridworld.grid.*;

/**
 * This class runs a world with additional grid choices.
 */
public class SparseBoundedRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}