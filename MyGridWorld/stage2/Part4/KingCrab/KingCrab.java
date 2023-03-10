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

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;
import java.util.ArrayList;

/**
 * A KingCrab
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter
{
    public void processActors(ArrayList<Actor> actors)
    {
        Grid gr = getGrid();
        for (Actor a : actors)
        {
            Location a_loc = a.getLocation();
            ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(a.getLocation());
            if (locs.size() == 0)
            {
                a.removeSelfFromGrid();
            }
            else
            {
                for (Location loc : locs)
                {
                    int x1 = getLocation().getRow();
                    int y1 = getLocation().getCol();
                    int x2 = loc.getRow();
                    int y2 = loc.getCol();
                    if (Math.sqrt((double)((x1 - x2)*(x1 - x2)) + (double)((y1 - y2)*(y1 - y2))) >= 2)
                    {
                        a.moveTo(loc);
                        break;
                    }
                }
            }
            if(a.getLocation().equals(a_loc))
            {
                a.removeSelfFromGrid();
            }
        }
    }
}
