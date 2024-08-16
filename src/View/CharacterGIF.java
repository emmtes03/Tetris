package View;
/**
 * The CharacterGIF class is responsible for drawing character GIFs in a separate thread.
 *
 * <p>
 * This thread is intended for asynchronously rendering character GIFs in the associated View.
 * It calls the draw_characters() method of the parent View to display the animated characters.
 * </p>
 *
 * <p> </p>
 *  <strong>Example:</strong>
 *  <p> CharacterGIF thread= new CharacterGIF(); </p>
 *
 *  <strong>Running the CharacterGIF:</strong>
 *  <p> thread.start(); </p>
 *
 * @see View
 */
public class CharacterGIF extends Thread {

    /** The parent View associated with this thread. */
    private View parent;

    /**
     * Constructs a CharacterGIF thread with the specified parent View.
     *
     * @param parent The parent View for rendering character GIFs.
     */
    CharacterGIF(View parent){
        this.parent=parent;
    }


    /**
     * Overrides the run method to execute the drawing of character GIFs in the parent View.
     */
    @Override
    public void run(){
        parent.draw_characters();
    }

}
