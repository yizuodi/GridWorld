import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.Color;

public class QuickCrabRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();

        // Create flowers
        for(int i = 0; i < 10; i++){
        	world.add(new Location(0, i), new Flower());
        }
        world.add(new Location(4, 4), new Flower());
        world.add(new Location(4, 5), new Flower());
        world.add(new Location(4, 6), new Flower());
        world.add(new Location(5, 4), new Flower());
        world.add(new Location(6, 4), new Flower());
        world.add(new Location(6, 5), new Flower());
        world.add(new Location(6, 6), new Flower());
        // Create rocks
        world.add(new Location(1, 4), new Rock());
        world.add(new Location(5, 6), new Rock());
        // Create KingCrabs
        world.add(new Location(1, 5), new QuickCrab());
        world.add(new Location(5, 5), new QuickCrab());

        world.show();
    }
}