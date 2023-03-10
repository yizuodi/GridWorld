/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.*;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains QuickCrab. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrabRunner
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
        world.add(new Location(1, 5), new KingCrab());
        world.add(new Location(5, 5), new KingCrab());

        world.show();
    }
}
