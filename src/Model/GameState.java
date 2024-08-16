package Model;
/**
 * Enum representing the possible states of the game.
 * The states include START (initial state), PLAYING (during gameplay), and GAMEOVER (game has ended).
 *
 *  <p> </p>
 *  <strong> Example: </strong>
 *  <p> GameState state= GameState.Start; </p>
 *
 */
public enum GameState {
    /**
     * Initial state when the game is started.
     */
    START,

    /**
     * State during gameplay when the game is actively being played.
     */
    PLAYING,

    /**
     * State indicating that the game has ended.
     */
    GAMEOVER;

}
