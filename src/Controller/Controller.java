package Controller;
import View.IView;
import Model.*;

/**
 * Controller class responsible for handling user input and coordinating interactions between the model and view.
 * Implements the IController interface.
 *
 * <p> </p>
 *  <strong>Example:</strong>
 *  <p> Controller controller= new Controller(); </p>
 *
 *  <strong>Managing the input data from the view</strong>
 *  <p> controller.handleInput()</p>
 *
 *  <strong>Sending the input data (here: falling) to the model:</strong>
 *  <p> controller.model.fall() </p>
 *  @author Emmelie Tessema
 */
public class Controller implements IController{


    //MVC components
    /**
     * Reference to the model component.
     */
    private IModel model;

    /**
     * Reference to the view component.
     */
    private IView view;


    //Initializing  View and Controller

    /**
     * Initializes the view component of the controller.
     *
     * @param view The view to be associated with the controller.
     */
    public void setView(IView view){
        this.view= view;
    }

    /**
     * Initializes the model component of the controller.
     *
     * @param model The model to be associated with the controller.
     */
    public void setModel(IModel model){
        this.model= model;
    }

    /**
     * Retrieves the current game state from the associated model.
     *
     * @return The current game state.
     */
    public GameState getGameState(){
        return model.getGameState();
    }


    /**
     * Retrieves the current game field, represented as an array of BasicBlock objects, from the associated model.
     *
     * @return The array of BasicBlock objects representing the current game field.
     */
    public BasicBlock[] getGameField(){return model.getGameField();}


    /**
     * Retrieves the current high score from the associated model.
     *
     * @return The current high score.
     */
    public int getHigh_Score(){return model.getHigh_score();}


    /**
     * Retrieves the next falling form from the associated model.
     *
     * @return The next falling form.
     */
    public BasicForm getNextForm(){return model.getNext_form();}

    /**
     * Retrieves the current game score from the associated model.
     *
     * @return The current game score.
     */
    public int getGame_Score(){return model.getGame_score();}


    /**
     * Counter used for managing delays in game events.
     */
    private int counter_delay=0;

    /**
     * Leads the game to the next frame based on the current game state.
     * Manages drawing screens and the triggering game events.
     */
    public void nextFrame(){
        if(getGameState()== GameState.START){
            view.drawStartScreen();
        }
        if(getGameState() == GameState.PLAYING){
            view.drawGameField();
            if(counter_delay==2){
                model.fall();
                counter_delay=0;
            }else counter_delay++;

        }
        if(getGameState() == GameState.GAMEOVER){
            view.drawGameOverScreen();
        }
    }

    /**
     * Handles user input and triggers corresponding actions based on the current game state.
     *
     * <p>
     * This method creates a new InputThread, sets the provided key as the input,
     * and starts the thread to handle the user input in the game model.
     * </p>
     *
     * @param keyPressed The key pressed by the user.
     */
    @Override
    public void handleInput(char keyPressed) {
        // Create a new InputThread and set the provided key as the input
       InputThread input=new InputThread(model);
       input.setInput(keyPressed);

       // Start the thread to handle the user input in the game model
       input.start();
    }


}
