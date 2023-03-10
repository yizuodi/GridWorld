import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.util.*;
/**
 * A SparseBoundedGrid is a rectangular grid with a finite number of rows and
 * columns. <br />
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    // using TreeMap to store the grid
    private TreeMap<Location, E> occupantMap;
    private int rows;
    private int cols;

    /**
     * Constructs an empty bounded grid with the given dimensions. (Precondition:
     * <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        this.rows = rows;
        this.cols = cols;
        occupantMap = new TreeMap<Location, E>();
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
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet())
            a.add(loc);
        return a;
    }

    public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }

    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.remove(loc);
    }
}