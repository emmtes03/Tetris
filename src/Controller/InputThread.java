package Controller;
import Model.IModel;
/**
 * The InputThread class represents a thread responsible for handling user input
 * and triggering corresponding actions in the game model.
 *
 * <p>
 * <strong>Example:</strong>
 * </p>
 * <pre>
 * InputThread inputThread = new InputThread(model);
 * inputThread.setInput('d');
 * inputThread.start();
 * </pre>
 */
public class InputThread extends Thread{

    /** The game model instance to which the user input will be applied. */
    private IModel model;
    /** The current user input received by the thread. */
    private char currentInput;

    /**
     * Constructs an InputThread with the specified game model.
     *
     * @param model The game model to which the user input will be applied.
     */
    public InputThread(IModel model){
        this.model=model;
    }

    /**
     * Sets the current user input for the thread to process.
     *
     * @param input The user input character.
     */
    public void setInput(char input) {
        this.currentInput = input;
    }

    /**
     * Runs the thread, processing the current user input and triggering
     * corresponding actions in the game model.
     */
    @Override
    public void run() {
        synchronized (model.getGameField()) {
            switch (currentInput) {
                case 'd':
                    model.fall();
                    break;
                case 'l':
                    model.move_left();
                    break;
                case 'r':
                    model.move_right();
                    break;
                case 't':
                    model.turn();
                    break;
                case '1':
                    model.start_new_Game();
                case '2':
                    model.start_new_Game();
            }
        }
    }
}
