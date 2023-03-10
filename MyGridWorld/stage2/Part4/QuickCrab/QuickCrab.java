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
 * A <code>QuickCrab</code> looks at a limited set of neighbors when it eats and moves.
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
    /**
     * @return list of locations immediately to the right and to the left
     */

    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        locs.addAll(getMoveLocations_2(getDirection() + Location.LEFT));
        locs.addAll(getMoveLocations_2(getDirection() + Location.RIGHT));
        if (locs.size() == 0)
            return super.getMoveLocations();
        return locs;
    }

    /**
     * @return list of locations immediately to the right or to the left
     */
    private ArrayList<Location> getMoveLocations_2(int dir)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid grid = getGrid();
        Location tmp = getLocation().getAdjacentLocation(dir);
        if(grid.isValid(tmp) && grid.get(tmp) == null)
        {
            Location loc = tmp.getAdjacentLocation(dir);
            if(grid.isValid(loc) && grid.get(loc)== null)
                locs.add(loc);
        }
        return locs;
    }
}
