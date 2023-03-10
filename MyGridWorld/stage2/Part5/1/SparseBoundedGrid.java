import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;

/**
 * A SparseBoundedGrid is a rectangular grid with a finite number of rows and
 * columns. <br />
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private SparseGridNode[] occupantArray;
    private int rows;
    private int cols;

    /**
     * Constructs an empty bounded grid with the given dimensions. (Precondition:
     * <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        this.rows = rows;
        this.cols = cols;
        occupantArray = new SparseGridNode[rows];
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
        return 0 <= loc.getRow() && loc.getRow() < getNumRows() && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < rows; r++)
        {
            SparseGridNode n = occupantArray[r];
            // If there's an object at this location, put it in the array.
            while (n != null)
            {
                theLocations.add(new Location(r, n.getCol()));
                n = n.getNext();
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                + " is not valid");
        SparseGridNode n = occupantArray[loc.getRow()];
        while (n != null)
        {
            if (n.getCol() == loc.getCol())
                return (E) n.getOccupant();
            n = n.getNext();
        }
        return null;
    }

    public E put(Location loc, E obj){
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        SparseGridNode n = occupantArray[loc.getRow()];
        if (n == null)
        {
            occupantArray[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), null);
        }
        else
        {
            if (n.getCol() == loc.getCol())
            {
                n.setOccupant(obj);
            }
            else
            {
                occupantArray[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), n);
            }
        }
        return oldOccupant;
    }

    public E remove(Location loc){
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                + " is not valid");

        // Remove the object from the grid.
        E r = get(loc);
        if (r == null)
            return null;
        SparseGridNode n = occupantArray[loc.getRow()];
        if (n.getCol() == loc.getCol())
        {
            occupantArray[loc.getRow()] = n.getNext();
        }
        else
        {
            while (n.getNext().getCol() != loc.getCol())
            {
                n = n.getNext();
            }
            n.setNext(n.getNext().getNext());
        }
        return r;
    }

}