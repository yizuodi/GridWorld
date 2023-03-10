public class SparseGridNode {
    private Object occupant;
    private int col;
    private SparseGridNode next;

    /**
     * Constructor
     * @param occupant the actor
     * @param col the column number in grid
     * @param next the pointer to next occupied grid node
     */
    public SparseGridNode(Object occupant, int col, SparseGridNode next) {
        this.occupant = occupant;
        this.col = col;
        this.next = next;
    }
    /**
     * Gets the occupant of this node.
     * @return the occupant of this node
     */
    public Object getOccupant() {
        return occupant;
    }
    /**
     * Sets the occupant of this node.
     * @param occupant the occupant of this node
     */
    public void setOccupant(Object occupant) {
        this.occupant = occupant;
    }
    /**
     * Gets the column of this node.
     * @return the column of this node
     */
    public int getCol() {
        return col;
    }
    /**
     * Sets the column of this node.
     * @param col the column of this node
     */
    public void setCol(int col) {
        this.col = col;
    }
    /**
     * Gets the next node.
     * @return the next node
     */
    public SparseGridNode getNext() {
        return next;
    }
    /**
     * Sets the next node.
     * @param next the next node
     */
    public void setNext(SparseGridNode next) {
        this.next = next;
    }
}
