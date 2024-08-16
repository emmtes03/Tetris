package Model;

/**
 * Represents a basic block in the game.
 * Each block has a color, index, and an activation status.
 *
 * <p> </p>
 * <strong>Example:</strong>
 *  <p> BasicBlock block=new BasicBlock("yellow", 4); </p>
 *
 */
public class BasicBlock {

    /** Color assigned to the block. */
    private String color;
    /** Index representing the position of the block. */
    private int index;

    /** Flag indicating whether the block is currently active or not. */
    private boolean active;


    /**
     * Constructs a BasicBlock with the specified color and index.
     *
     * @param color The color of the block. These Strings/colors are available: "darkblue", "darkpurple", "lightblue", "lightgreen", "orange", "purple", "red", "türkis", "yellow".
     * @param index The index representing the position of the block.
     */
    BasicBlock (String color, int index){
        this.color= color;
        this.index= index;
        this.active=true;
    }

    //Getter and Setter Methods

    /**
     * Gets the index representing the position of the block.
     *
     * @return The index of the block.
     */
    public int getIndex(){
        return index;
    }

    /**
     * Sets the index representing the position of the block.
     *
     * @param index The new index value for the block.
     */
    public void setIndex(int index){
        this.index=index;
    }



    /**
     * Checks whether the block is currently active.
     *
     * @return True if the block is active, false otherwise.
     */
    public boolean isActive(){return active;}

    /**
     * Deactivates the block, marking it as not active.
     */

    public void deactivate(){active=false;}

     /**
     * Gets the color of the block.
     *
     * @return The color of the block.
     */
    public String getColor(){return color;}
}
