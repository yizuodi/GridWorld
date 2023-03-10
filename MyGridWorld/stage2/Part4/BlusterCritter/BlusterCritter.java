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
 * A BlusterCritter looks at all neighbors within two steps of its current
 * location. If there are fewer than c critters, the BlusterCritter's color
 * gets brighter (color values increase). If there are c or more critters,
 * the BlusterCritter's color gets darker (color values decrease).
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    private static final double DARKENING_FACTOR = 0.05;
    private int c = 1;

    /**
     * Constructs a bluster critter.
     */
    public BlusterCritter(int c)
    {
        setColor(Color.RED);
        this.c = c;
    }

    /**
     * Gets the actors for processing. Implemented to return the actors within
     * two steps of the current location.
     * @return a list of actors within two steps of the current location
     */
    public ArrayList<Actor> getActors()
    {
        int row = getLocation().getRow();
        int col = getLocation().getCol();
        Grid gr = getGrid();
        ArrayList<Actor> actors = new ArrayList<Actor>();

        for (int r = row - 2; r <= row + 2; r++)
        {
            for (int c = col - 2; c <= col + 2; c++)
            {
                Location loc = new Location(r, c);
                if (gr.isValid(loc))
                {
                    Actor a = (Actor)gr.get(loc);
                    if (a != null && a != this)
                    {
                        actors.add(a);
                    }
                }
            }
        }
        return actors;
    }

    /**
     * Processes the actors. Implemented to darken the color of the critter
     * if there are more than c critters in the neighborhood, and to brighten
     * the color if there are fewer than c critters.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        Color color = getColor();
        int red;
        int green;
        int blue;
        if(n < c)
        {
            red = (int) (color.getRed() * (1 + DARKENING_FACTOR));
            green = (int) (color.getGreen() * (1 + DARKENING_FACTOR));
            blue = (int) (color.getBlue() * (1 + DARKENING_FACTOR));
            if(red > 255)
            {
                red = 255;
            }
            if(green > 255)
            {
                green = 255;
            }
            if(blue > 255)
            {
                blue = 255;
            }
        }
        else
        {
            red = (int) (color.getRed() * (1 - DARKENING_FACTOR));
            green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
            blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
        }
        setColor(new Color(red, green, blue));
    }
}
