package Controller;

import Model.BasicBlock;
import Model.BasicForm;
import Model.GameState;

/**
 * Interface representing the controller in the Model-View-Controller (MVC) design pattern for the game.
 * Defines methods for managing game frames, handling user input, and retrieving game-related information.
 *
 *  <p> </p>
 *  <strong>Example:</strong>
 *  <p> IController iController= new IController(); </p>
 */
public interface IController {

    /**
     * This method advances to the next frame. For more details, see {@link Controller#nextFrame()}
     */
    public void nextFrame();

    /**
     * This method handles user input. For more details, see {@link Controller#handleInput(char)}.
     */
    public void handleInput(char keyPressed);

    /**
     * This method returns the game field. For more details, see {@link Controller#getGameField()}.
     *
     * @return game field
     */
    public BasicBlock[] getGameField();


    /**
     * This method returns the current game state. For more details, see {@link Controller#getGameState()}.
     *
     * @return game state
     */
    public GameState getGameState();

    /**
     * This method returns the high score. For more details, see {@link Controller#getHigh_Score()}.
     *
     * @return  high score
     */

    public int getHigh_Score();

    /**
     * This method returns the next form. For more details, see {@link Controller#getNextForm()}.
     *
     * @return  next form
     */

    public BasicForm getNextForm();

    /**
     * This method returns the current game score. For more details, see {@link Controller#getGame_Score()}.
     *
     * @return game score
     */
    public int getGame_Score();


}
