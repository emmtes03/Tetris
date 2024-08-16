package Model;
import java.util.Random;

/**
 * The Model class represents the game model for a Tetris-like game.
 * It handles the game field, different block forms, game state, and scoring.
 *
 * <p> </p>
 * <strong>Example:</strong>
 * <p> Model tetris=new Model(); </p>
 *
 * <strong>Starting a new game:</strong>
 * <p> tetris.start_new_Game(); </p>
 *
 * <strong>Moving the current Form:</strong>
 * <p> tetris.fall(); </p>
 * <p> tetris.move_right(); </p>
 * @author Emmelie Tessema
 */

public class Model implements IModel {


    /**
     * Represents the game field using an array of BasicBlock instances.
     */
    private BasicBlock[] gameField= new BasicBlock[375];

    /**
     * Represents the size of the game field along the x-axis.
     */
    private int size_x= 15; //x-Axis
    /**
     * Represents the size of the game field along the y-axis.
     */
    private int size_y= 25; //y-Axis


    /**
     * Array of characters representing different block types used to generate a random form.
     */
    private char[] form_Types = {'L', 'T', 'Z', 'O', 'I'}; //all the available form types, is used to generate a random form

    /**
     * Represents the currently falling and active block form.
     */
    private BasicForm current_form; //form that is currently falling and active
    /**
     * Represents the next block form that will appear in the game.
     */
    private BasicForm next_form; //next form


    /**
     * Random variable used to generate a random block form.
     */
    private Random rnd; //is used to generate a random form

    /**
     * Represents the current game state using the GameState enumeration.
     */
    private GameState gameState; // returns the current Game State

    /**
     * Counter variable for counting the game score of each form that has been placed.
     */
    private int game_score;  //counter variable for counting the game score of each form that has been placed

    /**
     * Variable for saving the high score achieved in the game.
     */
    private int high_score=0;    //variable for saving the highscore



    /**
     * Constructor for initializing the game model.
     * It initializes the random variable, sets the game state to START,
     * and can be used to perform other initializations if needed.
     */
    public Model(){
        rnd=new Random();
        gameState=GameState.START;
    }


    /**
     * Starts a new game by setting the game state to PLAYING,
     * initializing the game field with a new array of BasicBlock instances,
     * generating a new current form and next form, and placing the current form into the game field.
     */
    public void start_new_Game(){
        try{
            gameState = GameState.PLAYING;
            this.gameField = new BasicBlock[375];
            current_form = generate_new_Form();
            next_form = generate_new_Form();
            game_score=0;
            place_Form();}
        catch(Exception e){
            System.out.println("Please start the game first.");
        }
    }

    /**
     * Moves the current falling form down in the game field.
     * If the move is valid, the current form is removed, moved forward, and placed again in the game field.
     * If the move is not valid, the current form is deactivated. If the game is over, the game state is set to GAMEOVER,
     * and the high score is updated if necessary. If the game is not over, the current form is replaced with the next form,
     * the next form is generated, and the current form is placed in the game field.
     *
     * @return True if the move is valid, false if the game is over or the move is not valid.
     */
    public boolean fall () {
        remove_Form();
        current_form.fall(); //form is moved forward
        if (is_Valid(current_form)) { //Fall is valid
            place_Form(); //form is placed again in the game field
            return true;
        }
        else {
            current_form.deactivate();
            if(isGameOver()) {
                if(game_score> high_score){
                    high_score=game_score;
                    System.out.println("The new highscore ist:" + high_score);
                }
                //current_form.deactivate(); // The form has reached its end
                gameState=GameState.GAMEOVER; //The gameState is set to GameOver
                System.out.println("The game is over");
                return false;
            }
            else {
                current_form.reverse_fall();
                place_Form();
                game_score+=current_form.get_score();
                //current_form.deactivate(); //The form has reached its end
                current_form = next_form; //the current_form gets the next form, which already has been generated
                next_form = generate_new_Form(); //the next form is generated
                place_Form(); //current_form is firstly placed into the game field
                System.out.println("The current_form has reached its end.");
                return false;
            }
        }
    }

    /**
     * Moves the current falling form to the right in the game field.
     * If the move is valid, the current form is removed, moved to the right, and placed again in the game field.
     * If the move is not valid, an error message is printed, and the method returns false.
     *
     * @return True if the move is valid, false otherwise.
     */
    public boolean move_right(){     //moving the current form right
        remove_Form();
        current_form.move_right();
        if(is_Valid(current_form)){
            place_Form();
            return true;
        }else{
            current_form.move_left();
            place_Form();
            System.out.println("This (moving right) was an invalid move. Try again.");
            return false;
        }
    }
    /**
     * Moves the current falling form to the right in the game field.
     * If the move is valid, the current form is removed, moved to the right, and placed again in the game field.
     * If the move is not valid, an error message is printed, and the method returns false.
     *
     * @return True if the move is valid, false otherwise.
     */
    public boolean move_left(){     //moving the current form left
        remove_Form();
        current_form.move_left();
        if(is_Valid(current_form)){
            place_Form();
            return true;
        }else{
            current_form.move_right();
            place_Form();
            System.out.println("This (moving left) was an invalid move. Try again.");
            return false;
        }
    }

    /**
     * Rotates the current falling form by 90 degrees in the game field.
     * If the rotation is valid, the current form is removed, rotated, and placed again in the game field.
     * If the rotation is not valid, the current form is rotated back to its original position,
     * an error message is printed, and the method returns false.
     *
     * @return True if the rotation is valid, false otherwise.
     */
    public boolean turn (){
        remove_Form();
        current_form.turn();
        if(is_Valid(current_form)){
            place_Form();
            return true;
        }
        else{
            current_form.turn();
            current_form.turn();
            current_form.turn();
            System.out.println("This (turing at 90 degrees) was an invalid move. Try again.");
            place_Form();
            return false;
        }
    }
    /**
     * Places the current falling form into the game field after every move.
     * The updated game field is then printed.
     */

    private void place_Form(){    //placing the form into the game field after every move
        //if(isGameOver()==true)return;
        BasicBlock[] placing_blocks=current_form.get_blocks();
        for(BasicBlock block:placing_blocks){
            int blockIndex = block.getIndex();
            gameField[blockIndex] = block;
        }
        System.out.println(this);
    }

    /**
     * Removes the current falling form from the game field before placing a new form.
     */
    private void remove_Form(){
        BasicBlock[] placing_blocks=current_form.get_blocks();
        for(BasicBlock block:placing_blocks){
            int blockIndex = block.getIndex();
            gameField[blockIndex] = null;
        }
    }

    /**
     * Generates a new random block form for the game.
     * The method selects a random block type from the available form types and creates a new BasicForm instance.
     *
     * @return A new BasicForm instance with a randomly chosen block type.
     */
    private BasicForm generate_new_Form(){    //a random form is generated
        int randomIndex = rnd.nextInt(form_Types.length); // Generiere einen zufälligen Index
        char randomBlock = form_Types[randomIndex]; // Wähle das Element an der zufälligen Indexposition aus
        System.out.println(randomBlock);
        return new BasicForm(randomBlock);
    }

    /**
     * Checks whether the current falling form is in a valid position after a move.
     * The method checks if the new position would be valid.
     * Valid positions must be within the game field boundaries and not overlap with other blocks.
     *
     * @param form The BasicForm instance representing the current falling form.
     * @return True if the move is valid, false otherwise.
     */
    private boolean is_Valid(BasicForm form) {
        //boolean valid=true; //contains a bool value which says whether the current form is valid
        int[] current_index = form.getIndex();
        for (int i = 0; i < 4; i++) {
            int check = current_index[i];
            if (check >= gameField.length |check <= 14 | check % 15 == 0 | check >= 360 | (check + 1) % 15 == 0)
                return false;// checking the borders
            if (gameField[check] != null && gameField[check].isActive()==false) return false;
            //checking in the gamefield whether the block is already taken or its own block
        }
        return true;
    }

    /**
     * Checks whether the game field is full or not.
     * It verifies if the position where the next form is supposed to be placed is already taken by another form.
     *
     * @return True if the game is over (field is full), false otherwise.
     */
    private boolean isGameOver(){
        if(is_Valid( next_form)) return false; //checks whether the position where the next form is supposed to be placed is already been taken by another form
        else {
            return true;
        }
    }

    /**
     * Returns a string representation of the game field, showing a 15x25 grid.
     * Empty fields are represented by '_', active blocks by 'o', and inactive blocks by 'x'.
     * The current high score is also included in the output.
     *
     * @return A string representation of the game field and current high score.
     */
    @Override
    public String toString(){
        StringBuilder gameFields = new StringBuilder();
        gameFields.append("\n");
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i] == null) {
                gameFields.append("_ "); // empty blocks
            } else {
                gameFields.append(gameField[i].isActive() ? "o " : "x "); // active blocks or already placed blocks
            }
            if ((i + 1) % size_x == 0) {
                gameFields.append("\n"); //  new row after every row of the gamefield
            }
        }
        gameFields.append("\n"+ "The current highscore ist: "+ high_score);
        return gameFields.toString();
    }

    //Getter und Setter methoden
    /**
     * Gets the current game state.
     *
     * @return The current game state.
     */
    public GameState getGameState(){ //getting current gameState
        return gameState;
    }

   //2. GameField
    /**
     * Gets the current game field represented by an array of BasicBlock instances.
     *
     * @return The current game field.
     */
    public BasicBlock[] getGameField(){ //getting current gamefield
        return gameField;
    }

    //3.Highscore
    /**
     * Gets the current high score achieved in the game.
     *
     * @return The current high score.
     */
    public int getHigh_score(){return high_score; }

    //4.get next Form
    /**
     * Gets the next block form that will appear in the game.
     *
     * @return The next block form.
     */
    public BasicForm getNext_form(){return next_form;}

    //5. get Current score
    /**
     * Gets the current game score, which is the cumulative score of placed forms.
     *
     *
     *
     *
     *
     * @return The current game score.
     */
    public int getGame_score(){return game_score;}


}
