package Model;
import java.util.Random;
/**
 * Represents a basic block form in the game.
 * Each form consists of four blocks, and the class provides methods for movement, rotation, and deactivation.
 *
 *  <p> </p>
 *  <strong>Example:</strong>
 *  <p> BasicForm form= new BasicForm('c'); </p>
 *
 *  <strong>Making the form fall:</strong>
 *  <p>  form.fall() </p>
 *
 */
public class BasicForm {
    /** Array of BasicBlock instances representing the blocks of the form. */
    private BasicBlock[] blocks = new BasicBlock[4];

    /** Array of indices representing the position of the form blocks. */
    private int[] index = new int[4];

    /** Type of the form ('I', 'L', 'T', 'Z', or 'O'). */
    private char form;

    /** Current state of the form (0, 90, 180, 270 degrees). */
    private int state; //(0, 90, 180, 270)

    /** Offsets for each state of the form, defining the relative positions of blocks during rotation. */
    private int[][] STATE_OFFSETS;

    /** Flag indicating whether the form is currently active or not. */
    private boolean active;

    /** Score value associated with the form. */
    private int score;

    /** Value for the color of the form. */
    private String color;

    /**
     * Constructs a BasicForm with the specified block type.
     * Every form gets its individual Offset parameters.
     * @param form The type of block ('I', 'L', 'T', 'Z', or 'O').
     */
    BasicForm(char form){
        if(form =='I'){
            STATE_OFFSETS = new int[][]{
                    {-14, 0, +14, +28},    //Für State =0-> 90
                    {+14, 0, -14, -28},    // Für state = 90 -> 180
                    {-14, 0, +14, +28},   // Für state = 180 -> 270
                    {+14, 0, -14, -28},    //Für State = 270-> 0
            };
            this.index= new int[]{20,21,22,23};
            this.score=3;

        } else if (form == 'L') {
            STATE_OFFSETS= new int[][]{
                    {+16, 0, -16, -2},   // Für State = 0 -> 90
                    {+14, 0, -14, -30},    // Für State = 90 -> 180
                    {-16, 0, +16, +2},   // Für State = 180 -> 270
                    {-14, 0, +14, +30}     // Für State = 270 -> 0
            };
            this.index= new int []{22, 37, 52, 53};
            this.score=3;

        } else if (form == 'T') {
            STATE_OFFSETS= new int[][] {
                    {-14, 0, +14, -16},    //Für State =0-> 90
                    {+16, 0, -16, -14},    // Für state = 90 -> 180
                    {+14, 0, -14, +16},   // Für state = 180 -> 270
                    {-16, 0, +16, +14},    //Für State = 270-> 0
            };
            this.index= new int[]{20, 21,22, 36};
            this.score=5;

        } else if (form == 'Z') {
            STATE_OFFSETS= new int[][] {
                    {+2,+16, 0, +14},    //Für State =0-> 90
                    {+30, +14, 0, -16 },    // Für state = 90 -> 180
                    {-2, -16, 0, -14},   // Für state = 180 -> 270
                    {-30, -14, 0, +16},    //Für State = 270-> 0
            };
            this.index= new int[]{22, 23, 38,39};
            this.score=5;
        }
        else if(form== 'O'){
            STATE_OFFSETS= new int[][]{
                    {0,0,0,0},    //Für State =0-> 90
                    {0,0,0,0},    // Für state = 90 -> 180
                    {0,0,0,0},   // Für state = 180 -> 270
                    {0,0,0,0},    //Für State = 270-> 0
            };
            this.index=new int[]{22,23, 37, 38};
            this.score=2;
        }
        //Every Form get its char and the blocks-Array is generated here + every Form starts with state 0
        this.form=form; //Form char is initialized
        String random_color=generate_random_Color(); //generating random color
        this.color=random_color; //a random color for the whole form
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new BasicBlock(random_color, index[i]);
        }
        this.state=0;
        this.active=true;
    }

    /**
     * Generates a random color for the form block.
     *
     * @return A randomly selected color for the form.
     */
    public String generate_random_Color () {
        Random rnd = new Random();
        String[] color_Types = {"darkblue", "darkpurple", "lightblue", "lightgreen", "orange", "purple", "red", "türkis", "yellow"}; //all the available colors for each form
        int random_index=rnd.nextInt(color_Types.length);
        return color_Types[random_index];
    }

    /**
     * Moves the form down by one row (+15) in the game field.
     */
    public void fall() {
        for (int i = 0; i < blocks.length; i++) {
            index[i]+= 15;
            blocks[i].setIndex(blocks[i].getIndex() + 15);
        }
    }
    /**
     * Retrieves the fall by one row (-15) in the game field.
     */
    public void reverse_fall() {
        for (int i = 0; i < blocks.length; i++) {
            index[i]-= 15;
            blocks[i].setIndex(blocks[i].getIndex() - 15);
        }
    }

    /**
     * Rotates the form by 90 degrees in the game field.
     */
    public void  turn() {
        for (int i = 0; i < 4; i++) {
            index[i] = index[i] + STATE_OFFSETS[state / 90][i];
            blocks[i].setIndex(blocks[i].getIndex() + STATE_OFFSETS[state / 90][i]);
        }
        if(state==270)state=0;
        else state+=90;
    }

    /**
     * Moves the form to the left (-1) by one column in the game field.
     */
    public void move_left(){
        for(int i=0; i<4; i++){
            index[i]-= 1;
            blocks[i].setIndex(blocks[i].getIndex()-1);
        }
    }

    /**
     * Moves the form to the right (+1) by one column in the game field.
     */

    public void move_right(){
        for(int i=0; i<4; i++){
            index[i]+= 1;
            blocks[i].setIndex(blocks[i].getIndex()+1);
        }
    }

    /**
     * Deactivates the blocks of the form that cannot fall further in the game field.
     */
    public void deactivate(){
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].deactivate();
        }
    }
    //Getter and Setter Methods
    //1.Index of form
    /**
     * Gets the array of indices representing the position of the form blocks.
     *
     * @return An array of indices for the form blocks.
     */
    public int[] getIndex(){return this.index;}

    //2.BasicBlock
    /**
     * Gets the array of BasicBlock instances representing the blocks of the form.
     *
     * @return An array of BasicBlock instances for the form blocks.
     */
    public BasicBlock[] get_blocks(){
        return this.blocks;
    }

    //3.Form
    /**
     * Gets the type of the form.
     *
     * @return The type of the form ('I', 'L', 'T', 'Z', or 'O').
     */
    public char get_form(){return form;}

    //4.Score
    /**
     * Gets the score associated with the form.
     *
     * @return The score value for the form.
     */
    public int get_score(){return score;}


}
