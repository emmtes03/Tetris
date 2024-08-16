package Model;

/**
 * Interface representing the model of the game.
 * It defines methods for controlling and querying the game state, as well as accessing game-related information.
 *
 * <p> </p>
 *  <strong>Example:</strong>
 *  <p> IModel iModel= new IModel(); </p>
 */
public interface IModel {
    /**
     * Starts a new game. For more details, see {@link Model#start_new_Game()}
     */
    public void start_new_Game();

    /**
     * Moves the current falling form down in the game field. For more details, see {@link Model#fall()}
     *
     * @return True if the move is valid, false if the game is over or the move is not valid.
     */
    public boolean fall();

    /**
     * Moves the current falling form to the right in the game field. For more details, see  {@link Model#move_right()}
     *
     * @return True if the move is valid, false otherwise.
     */

    public boolean move_right();

    /**
     * Moves the current falling form to the left in the game field. For more details, see  {@link Model#move_left()}
     *
     * @return True if the move is valid, false otherwise.
     */

    public boolean move_left();

    /**
     * Turns the current falling form in the game field. For more details, see  {@link Model#turn()}
     *
     * @return True if the move is valid, false otherwise.
     */
    public boolean turn();


    /**
     * Gets the current game state. For more details, see  {@link Model#getGameState()}
     *
     * @return The current game state.
     */
    public GameState getGameState();

    /**
     * Gets the current game field represented by an array of BasicBlock instances. For more details, see  {@link Model#getGameField()}
     *
     * @return The current game field.
     */
    public BasicBlock[] getGameField();


    /**
     * Gets the current high score achieved in the game.For more details, see  {@link Model#getHigh_score()}
     *
     * @return The current high score.
     */
    public int getHigh_score();

    /**
     * Gets the next block form that will appear in the game. For more details, see  {@link Model#getNext_form()}
     *
     * @return The next block form.
     */
    public BasicForm getNext_form();

    /**
     * Gets the current game score, which is the cumulative score of placed forms. For more details, see  {@link Model#getGame_score()}
     *
     * @return The current game score.
     */
    public int getGame_score();

}
