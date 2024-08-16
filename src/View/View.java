package View;
import Controller.IController;
import Model.BasicBlock;
import Model.BasicForm;
import controlP5.*;
import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.util.HashMap;

import static controlP5.ControlP5Constants.ACTION_RELEASE;

/**
 * The View class represents the graphical user interface of the Tetris game.
 * It extends the PApplet class from the Processing library and implements the IView interface.
 * This class handles the visualization of the game, including start screens, play screens, and game over screens.
 * It also manages various images, animations, buttons, and the game field.
 * <p> </p>
 *  <strong>Example:</strong>
 *  <p> View view=new View(); </p>
 *
 *  <strong>Drawing the start screen:</strong>
 *  <p> view.drawStartScreen() </p>
 *
 *  <strong>Drawing the Game Over Screen:</strong>
 *  <p> view.drawGameOverScreen(); </p>
 */
public class View extends PApplet implements IView{

    //MVC Components
    /**
     * The controller responsible for handling user input and updating the game state.
     */
    private IController controller;

    //all the images
    /**
     * The image representing the start screen of the Tetris game.
     */
    private PImage start_Screen; //Start Screen

    /**
     * The image representing the play screen during an active game.
     */
    private PImage play_Screen; //Play Screen

    /**
     * The image representing the game over screen displayed at the end of a game.
     */
    private PImage game_Over_Screen; //Game Over Screen

    /**
     * The image representing the title "TETRIS" displayed in the game.
     */
    private PImage title; //Title "TETRIS"

    /**
     * The image representing a little instruction of the game.
     */
    private PImage instruction;

    /**
     * An array of images representing the single blocks. The final form is created based on these blocks.
     */
    private PImage[] blocks = new PImage[10];

    /**
     * The imageNameToIndex variable is a HashMap used to associate image names with their corresponding PImage objects.
     * */
    private HashMap<String, PImage> imageNameToIndex = new HashMap<>();

    /**
     * The GIF image displayed when the game is over.
     */
    private Gif gameover;

    //different animated figures
    /**
     * The GIF image representing blue flames.
     */
    private Gif blue_flame;

    /**
     * The GIF image representing a witch with purple hair.
     */
    private Gif witch_with_purple_hair;

    /**
     * The GIF image representing a little witch walking.
     */
    private Gif little_witch_walking;

    /**
     * The GIF image representing a little witch.
     */
    private Gif little_witch;

    /**
     * The GIF image representing a little bat.
     */
    private Gif little_bat;

    /**
     * The GIF image representing flying hats.
     */
    private Gif flying_hats;

    /**
     * The GIF image representing four potions.
     */
    private Gif four_potions;

    //GameField variable
    /**
     * The array of BasicBlock objects representing the game field.
     */
    private BasicBlock[] gamefield;

    //ControlP5
    /**
     * The global ControlP5 variable for creating GUI elements.
     */
    private ControlP5 cp5; //global ControlP5 Variable

    //Buttons
    /**
     * The button for starting a new game.
     */
    private Button start_game;

    /**
     * The button for restarting the game.
     */
    private Button restart_game;
    /**
     * The button for restarting the game after a game over.
     */
    private Button restart_game_game_over;

    /**
     * A font used for text rendering.
     */
    private PFont font;



    /**
     * Sets the controller for the view.
     *
     * @param controller The controller to be set.
     */
    public void setController(IController controller){
        this.controller= controller;
    }

    @Override
    public void settings() {
        size(1000, 800); //size of the gamefield
        pixelDensity(2);

    }

    /**
     * Overrides the setup method in PApplet.
     * Initializes various components, loads images, and sets up buttons.
     * GIFs are being initialized in a separate thread.
     */
    @Override
    public void setup() {
        background(255);
        //Initializing the font
        //System.out.println(PFont.list());
        font=createFont("Arial", 10);

        //Start screen image is being loaded into PImage variable
        start_Screen = loadImage("startscreen.jpg");
        start_Screen.resize(1000, 800);

        //Game over Screen image is being loaded into PImage variable
        game_Over_Screen = loadImage("endscreen.jpg");
        game_Over_Screen.resize(1000, 800);

        //Play Screen image is being loaded into PImage variable
        play_Screen = loadImage("playscreen.jpg");
        play_Screen.resize(1000, 800);

        //Title image is bein loaded into PImage variable
        title=loadImage("title.png");
        title.resize(800,300);

        //instruction image is being loaded into PImage variable
        instruction=loadImage("Anleitung.png");
        instruction.resize(300,300);



        //Buttons
        cp5=new ControlP5(this); //Initializing cp5 with current PAapplet Object
        //1.Button for starting a new game
        start_game=cp5.addButton("Start game")
                .setPosition(410, 600)
                .setSize(200,50)
                .setColorBackground(color(200, 100, 200));
        start_game.getCaptionLabel().setFont(font);

        //Adding Callback Listeners
        start_game.addListenerFor(ACTION_RELEASE, theEvent -> controller.handleInput('1'));

        //2.Button to restart the game for Play Screen
        restart_game=cp5.addButton("Restart Game")
            .setPosition(350,700)
            .setSize(200,50)
            .setColorBackground(color(200, 100, 200));
        restart_game.getCaptionLabel().setFont(font);

        //Adding Callback Listener
        restart_game.addListenerFor(ACTION_RELEASE, theEvent -> controller.handleInput('2'));

        //3. Button to restart game for Game Over screen
        restart_game_game_over=cp5.addButton("Play again")
                .setPosition(400,700)
                .setSize(200,50)
                .setColorBackground(color(53,33,0));
        restart_game_game_over.getCaptionLabel().setFont(font);


        //Adding Callback Listener
        restart_game_game_over.addListenerFor(ACTION_RELEASE, new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent theEvent) {
                controller.handleInput('2');
            }
        });


        //Gifs

        //GIFs laden
        GifThread thread=new GifThread(this);
        thread.start();


        //All the blocks are being inintialized and resized

        imageNameToIndex.put("darkblue", loadImage("darkblue.png"));
        imageNameToIndex.get("darkblue").resize(25, 25);
        System.out.println("Bild 1 wurde geladen");

        imageNameToIndex.put("darkpurple",loadImage("darkpurple.png"));
        imageNameToIndex.get("darkpurple").resize(25, 25);
        System.out.println("Bild 2 wurde geladen");


        imageNameToIndex.put("lightblue", loadImage("lightblue.png"));
        imageNameToIndex.get("lightblue").resize(25, 25);
        System.out.println("Bild 3 wurde geladen");


        imageNameToIndex.put("lightgreen", loadImage("lightgreen.png"));
        imageNameToIndex.get("lightgreen").resize(25, 25);
        System.out.println("Bild 4 wurde geladen");

        imageNameToIndex.put("orange", loadImage("orange.png"));
        imageNameToIndex.get("orange").resize(25, 25);
        System.out.println("Bild 5 wurde geladen");


        imageNameToIndex.put("purple", loadImage("purple.png"));
        imageNameToIndex.get("purple").resize(25, 25);
        System.out.println("Bild 6 wurde geladen");


        imageNameToIndex.put("red", loadImage("red.png"));
        imageNameToIndex.get("red").resize(25, 25);
        System.out.println("Bild 7 wurde geladen");

        imageNameToIndex.put("türkis", loadImage("türkis.png"));
        imageNameToIndex.get("türkis").resize(25, 25);
        System.out.println("Bild 8 wurde geladen");

        imageNameToIndex.put("yellow", loadImage("yellow.png"));
        imageNameToIndex.get("yellow").resize(25, 25);
        System.out.println("Bild 9 wurde geladen");

        imageNameToIndex.put("brick", loadImage("brick.png"));
        imageNameToIndex.get("brick").resize(25, 25);
        System.out.println("Bild 10 wurde geladen");

    }




    /**
     * Overrides the draw method in PApplet.
     * Sets the background color to white and calls the nextFrame method in the controller.
     */
    @Override
    public void draw() {
        background(255);
        controller.nextFrame();
    }


    /**
     * Overrides the keyPressed method in PApplet.
     * Handles key presses for falling, turning, moving right, and moving left based on key codes.
     */
    public void keyPressed(){
        if(keyCode== DOWN)controller.handleInput('d'); //Press Space to Fall
        if(keyCode== ENTER) controller.handleInput('t');//Press Enter to Turn
        if(keyCode== RIGHT) controller.handleInput('r'); //Press right to move right
        if(keyCode==LEFT)controller.handleInput('l');//Press left to move left
        else{
            System.out.print("Please select a valid key. Press the arrow keys to move the current form or enter to turn.");
        }
    }


    /**
     * Sets the images based on the provided gifName.
     * Used by the GifThread class to load all the gif images.
     *
     * @param gifName The name of the gif to be loaded.
     */
    public void setImages(String gifName){
        if (gifName.equals("blueflame")) {
            blue_flame = new Gif(this, "blueflame.gif");
            blue_flame.play();
            if (blue_flame.loaded) System.out.println("Gif blue flame was being loaded");
        } else if (gifName.equals("witch_with_purple_hair")) {
            witch_with_purple_hair = new Gif(this, "witch_with_purple_hair.gif");
            witch_with_purple_hair.play();
            if (witch_with_purple_hair.loaded) System.out.println("Gif witch_with_purple_hair was being loaded");
        } else if (gifName.equals("little_witch_walking")) {
            little_witch_walking = new Gif(this, "little_witch_walking.gif");
            little_witch_walking.play();
            if (little_witch_walking.loaded) System.out.println("Gif little_witch_walking was being loaded");
        } else if (gifName.equals("littlebat")) {
            little_bat = new Gif(this, "littlebat.gif");
            little_bat.play();
            if (little_bat.loaded) System.out.println("Gif little_bat was being loaded");
        } else if (gifName.equals("flying_hats")) {
            flying_hats = new Gif(this, "flying_hats.gif");
            flying_hats.play();
            if (flying_hats.loaded) System.out.println("Gif flying_hats was being loaded");
        } else if (gifName.equals("fourpotions")) {
            four_potions = new Gif(this, "fourpotions.gif");
            four_potions.play();
            if (four_potions.loaded) System.out.println("Gif four_potions was being loaded");
        } else if (gifName.equals("little_witch")) {
            little_witch = new Gif(this, "little_witch.gif");
            little_witch.play();
            if (little_witch.loaded) System.out.println("Gif little_witch was being loaded");
        } else if (gifName.equals("gameover")) {
            gameover = new Gif(this, "gameover.gif");
            gameover.play();
            if (gameover.loaded) System.out.println("Gif gameover was being loaded");
        }
    }


    /**
     * Draws the start screen.
     * Displays the start screen image, title, start game button, and an animated figure.
     */
    public void drawStartScreen(){
        image(start_Screen, 0, 0);
        image(title,100,100);
        image(instruction, 650, height/2);
        start_game.show();
        restart_game.hide();
        restart_game_game_over.hide();
        image(little_witch_walking, 410, 400, 200, 200);

    }


    /**
     * Draws the game field.
     * Displays the play screen image, characters, buttons, next form, highscore, and current score.
     * Additionally, it draws the blocks on the game field based on their color.
     */
    public void drawGameField(){
        this.gamefield = controller.getGameField();
        image(play_Screen, 0, 0);

        //Displaying a gray background on the right side of the game field
        fill(51,51,51); // Graue Farbe
        noStroke(); // Kein Rand
        rect(width - 300, 0, 300, height); // Position und Größe des Rechtecks

        //Buttons
        restart_game.show();
        start_game.hide();
        restart_game_game_over.hide();

        //Displaying the character GIFs in a seperate Thread
        CharacterGIF thread_characters= new CharacterGIF(this);
        thread_characters.start();

        //Displaying the next form
        //next Form Thread is being started
        //Thread
        TetrisThread nextFormThread=new TetrisThread(this);
        nextFormThread.start();



        //Zeichnen der Blöcke
        //Option 2 zum Zeichnen der Blöcke
        for (int i = 0; i < gamefield.length; i++) {
            int x = i % 15; // Spaltenindex
            int y = i / 15; // Zeilenindex
            if (gamefield[i] != null) {
                BasicBlock block = gamefield[i];
                String block_color=block.getColor();
                image(imageNameToIndex.get(block_color), x * 25 + 250, y * 25 + 50);
            }
            else{
                // Rand zeichnen
                if (x == 0 || x == 14 || y == 0 || y == 24) {
                    image(imageNameToIndex.get("brick"), x * 25 + 250, y * 25 + 50);
                }
            }
        }
        //Try-catch statements to handle exceptions thrown by the threads
        try {
            nextFormThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            thread_characters.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Draws the game over screen.
     * Displays the game over screen image, game over gif, and flying hats gif.
     * Additionally, it displays the highscore and shows the appropriate buttons.
     */
    public void drawGameOverScreen(){
        image(game_Over_Screen, 0,0);
        image(gameover, 180, 200, 600, 300);
        image (flying_hats,390,280, 200,50);

        //Displaying the highscore
        fill(0); // Textfarbe
        textSize(40); // Textgröße
        textAlign(CENTER); // Ausrichtung des Textes
        textFont(createFont("Arial-BoldItalic", 30));
        text("Highscore: " + controller.getHigh_Score(), 460,450); // Anzeige des Highscores oben links

        start_game.hide();
        restart_game.hide();
        restart_game_game_over.show();
    }

    /**
     * Displays the next form on the screen.
     * Gets the next form from the controller and displays it based on its color and shape.
     * Method is being called by the TetrisThread separately.
     */
    public void nextForm(){
        //Displaying the text

        fill(255); // Textfarbe
        textSize(30); // Textgröße
        textAlign(RIGHT); // Ausrichtung des Textes
        text("NEXT: ", width-200, 160); // Anzeige des Highscores oben links
        text("HIGHSCORE : " + controller.getHigh_Score(), width-50, 100); // Anzeige des Highscores oben links

        //Displaying the current Game Score
        fill(255); // Textfarbe
        textSize(30); // Textgröße
        text("SCORE : " + controller.getGame_Score(), width-70, 400); // Anzeige des Highscores oben links

        //Generating the next form from the controller
        BasicForm next_Form=controller.getNextForm(); //the next form
        String next_color= next_Form.get_blocks()[0].getColor(); //The color


        //Checking which color shall be displayed form the blocks array
        PImage displayed_form;
        displayed_form= imageNameToIndex.get(next_color);

        int xpos=width-200;
        int ypos= 200;

        char next_form=next_Form.get_form();
        switch(next_form){
            case 'I':
                image(displayed_form, xpos, ypos);
                image(displayed_form, xpos+25, ypos);
                image(displayed_form, xpos+50, ypos);
                image(displayed_form, xpos+75, ypos);
                break;
            case 'O':
                image(displayed_form, xpos, ypos);
                image(displayed_form, xpos+25, ypos);
                image(displayed_form, xpos, ypos+25);
                image(displayed_form, xpos+25, ypos+25);
                break;
            case 'L':
                image(displayed_form, xpos, ypos);
                image(displayed_form, xpos, ypos+25);
                image(displayed_form, xpos, ypos+50);
                image(displayed_form, xpos+25, ypos+50);
                break;

            case 'Z':
                image(displayed_form, xpos, ypos);
                image(displayed_form, xpos+25, ypos);
                image(displayed_form, xpos+25, ypos+25);
                image(displayed_form, xpos+50, ypos+25);
                break;

            case 'T':
                image(displayed_form, xpos, ypos);
                image(displayed_form, xpos+25, ypos);
                image(displayed_form, xpos+50, ypos);
                image(displayed_form, xpos+25, ypos+25);
                break;
        }
    }

    /**
     *
     *  Draws character images on the canvas.
     *  <p>
     *  This method displays character images on the canvas at specific positions and with specified dimensions.
     *  The images are drawn using the image() method, and the positions and dimensions are adjusted accordingly.
     *  </p>
     */
    public void draw_characters(){
        //Displaying characters
        image(little_witch, 10, 600, 200, 200);
        image(witch_with_purple_hair, 10,30, 150, 150);
        image(little_bat, 750, 600, 150, 150);
    }
}
