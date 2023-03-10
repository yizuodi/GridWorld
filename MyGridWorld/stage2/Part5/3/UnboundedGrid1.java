/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;

import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid1<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray; // the array storing the grid elements
    private int rows;
    private int cols;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid1()
    {
        occupantArray = new Object[16][16];
        rows = 16;
        cols = 16;
    }

    public int getNumRows()
    {
        return rows;
    }

    public int getNumCols()
    {
        return cols;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && 0 <= loc.getCol();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    locs.add(loc);
            }
        }

        return locs;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getRow() >= rows || loc.getCol() >= cols)
            return null;
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    /**
     * Puts an object at a given location. Returns the object that was previously at the location.
     * OccupantArray is resized if necessary.
     * @param loc the location to put the object at
     * @param obj the object to put at the location
     * @return the object that was previously at the location (or null if the location is unoccupied)
     */
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getRow() >= rows || loc.getCol() >= cols)
        {
            int newRows = Math.max(rows, loc.getRow() + 1);
            int newCols = Math.max(cols, loc.getCol() + 1);
            Object[][] newOccupantArray = new Object[newRows][newCols];
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    newOccupantArray[r][c] = occupantArray[r][c];
                }
            }
            occupantArray = newOccupantArray;
            rows = newRows;
            cols = newCols;
        }
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getRow() >= rows || loc.getCol() >= cols)
            return null;
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
