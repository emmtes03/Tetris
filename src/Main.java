import processing.core.PApplet;
import Controller.Controller;
import Model.Model;
import View.View;
/**
 * The main class for starting the Tetris game.
 *
 * <p>
 * The Main class initializes the Model, View, and Controller classes, connects them,
 * and starts the Processing application to run the Tetris game by running the main()-method.

 */
public class Main {
    /**
     * Main method to start the Tetris game.
     * Initializes the Model, View, and Controller, connects them, and starts the Processing application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args){
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();

        // Connect M, V and C
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);


        //Starts the processing application
        PApplet.runSketch(new String[]{"View"}, view);
    }
}