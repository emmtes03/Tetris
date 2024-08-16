package View;


/**
 * IView interface defines methods for displaying different screens in the Tetris game.
 *
 *  <p> </p>
 *  <strong>Example:</strong>
 *  <p> IView iView= new IView(); </p>
 */
public interface IView {

    /**
     * Displays the start screen of the game.For more details, see {@link View#drawStartScreen()}
     */
    public void drawStartScreen();

    /**
     * Displays the game field during active gameplay. For more details, see {@link View#drawGameField()}
     */
    public void drawGameField();

    /**
     * Displays the game over screen. For more details, see {@link View#drawGameOverScreen()}
     */
    public void drawGameOverScreen();



}
