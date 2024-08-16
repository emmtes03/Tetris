package View;

/**
 * TetrisThread class represents a thread used to display the next form in the Tetris game.
 *
 *  <p> </p>
 *  <strong>Example:</strong>
 *  <p> TetrisThread thread= new TetrisThread; </p>
 *
 *  <strong>Running the TetrisThread:</strong>
 *  <p> thread.start(); </p>
 */
public class TetrisThread extends Thread{

    /**
     * The View object that controls the display.
     */
    private View parent;

    /**
     * Constructor for TetrisThread.
     *
     * @param parent The View object that controls the display.
     */

    TetrisThread(View parent){
        try {
            this.parent = parent;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Run method to be executed when the thread is started.
     * Calls the nextForm method in the parent View to display the next form.
     */
    @Override
    public void run(){
        parent.nextForm();
    }


}
