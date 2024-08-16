package View;

/**
 * GifThread class represents a thread used to load and set images for various GIF animations in the Tetris game.
 *
 *  <p> </p>
 *  <strong>Example:</strong>
 *  <p> GifThread thread= new GifThread(); </p>
 *
 *  <strong>Running the GifThread:</strong>
 *  <p> thread.start(); </p>
 */
public class GifThread extends Thread{
    /**
     * The View object that controls the display.
     */
    View parent;
    /**
     * Array of GIF names to be loaded.
     */
    String[] gif_names= new String[]{"blueflame", "witch_with_purple_hair","little_witch_walking","littlebat","flying_hats","little_witch","fourpotions","gameover"};

    /**
     * Constructor for GifThread.
     *
     * @param parent The View object that controls the display.
     */
     public GifThread(View parent){
        this.parent=parent;
    }


    /**
     * Run method to be executed when the thread is started.
     * Iterates through the array of GIF names and calls the setImages method in the parent View to load and set images.
     */
    @Override
    public void run(){
            for(String name:gif_names){
                parent.setImages(name);
            }
         }

}
