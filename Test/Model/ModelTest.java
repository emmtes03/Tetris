package Model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    Model tetris;
    BasicBlock block;
    BasicForm form,form_2;

    @BeforeEach
    void setup(){
        tetris= new Model();
        block= new BasicBlock("yellow", 3);
        form= new BasicForm('Z');
        form_2= new BasicForm('T');
    }
    @AfterEach

    @Test
    void testForSettingUptheModel(){
        Model testModel=new Model();
        assertEquals(GameState.START, testModel.getGameState());
    }

    @Test
    void startingANewGameTest(){
        tetris.start_new_Game();

        //Movement Methods
        assertTrue(tetris.fall());
        assertTrue(tetris.move_left());
        assertTrue(tetris.move_right());


        //Comparing 2 Objects
        Model test= new Model();
        test.start_new_Game();
        assertNotEquals(test,tetris);


        //Game State
        assertSame(GameState.PLAYING, tetris.getGameState());
        assertNotSame(GameState.START, tetris.getGameState());
        assertNotSame(GameState.GAMEOVER, tetris.getGameState());


        //Game Score && High Score
        assertEquals(0, tetris.getGame_score());
        assertEquals(0,tetris.getHigh_score());

        //NextForm
        assertNotNull(tetris.getNext_form());

        //Game Field
        BasicBlock[] testGameField= new BasicBlock[375];
        assertNotEquals(testGameField, tetris.getGameField());

        //toString
        assertNotEquals(" ", tetris.toString());
    }


    @Test
    void moveRight() {
        tetris.start_new_Game();
        assertTrue(tetris.move_right());
        assertEquals(GameState.PLAYING, tetris.getGameState());

       tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        tetris.move_right();
        assertFalse(tetris.move_right());
    }

    @Test
    void moveLeft() {
        tetris.start_new_Game();
        assertTrue(tetris.move_left());
        assertEquals(GameState.PLAYING, tetris.getGameState());

        tetris.move_left();
        tetris.move_left();
        tetris.move_left();
        tetris.move_left();
        tetris.move_left();
        tetris.move_left();
        tetris.move_left();
        tetris.move_left();
        tetris.move_left();
        assertFalse(tetris.move_left());
    }

    @Test
    void turn() {
        tetris.start_new_Game();
        tetris.fall();
        tetris.fall();
        assertTrue(tetris.turn());
        assertEquals(GameState.PLAYING, tetris.getGameState());
    }


    @Test
    void gameOver() {
        tetris.start_new_Game();
        // Let's move the current form until the game is over
        while (tetris.getGameState()==GameState.PLAYING) {
            tetris.fall();
        }
        assertEquals(GameState.GAMEOVER, tetris.getGameState());
        assertTrue(tetris.getHigh_score() >= 0); // High score should be non-negative
    }

    @Test
    void testBasicBlock (){
        assertEquals("yellow", block.getColor());
        assertEquals(3, block.getIndex());
        assertEquals(true, block.isActive());
        block.deactivate();
        assertEquals(false, block.isActive());
        block.setIndex(4);
        assertEquals(4,block.getIndex());
        assertEquals("yellow",block.getColor());
    }


    @Test
    void testingBasicForm(){
        assertEquals('Z', form.get_form());
        assertNotNull(form.get_blocks());
        assertArrayEquals(new int[]{22, 23, 38,39}, form.getIndex());
        form.fall();
        assertArrayEquals(new int[]{22+15, 23+15, 38+15,39+15}, form.getIndex());
        form.move_right();
        assertArrayEquals(new int[]{22+15+1, 23+15+1, 38+15+1,39+15+1}, form.getIndex());
        form.move_left();
        assertArrayEquals(new int[]{22+15, 23+15, 38+15,39+15}, form.getIndex());
        form.turn();
        assertArrayEquals(new int[]{22+15+2,23+15+16,38+15+0, 39+15+14}, form.getIndex());


        assertEquals('T', form_2.get_form());
        assertArrayEquals(new int[]{20, 21,22, 36}, form_2.getIndex());
        form_2.fall();
        assertArrayEquals(new int[]{20+15, 21+15,22+15, 36+15}, form_2.getIndex());


        //Score
        assertEquals(5, form_2.get_score());
        assertNotEquals(-1,form.get_score());

        //Deactivate
        form.deactivate();
        BasicBlock[] test_blocks= form.get_blocks();
        assertEquals(false, test_blocks[0].isActive());

        assertNotEquals(form, form_2);

    }

    @Test
    void testingBasicFormfall() {
        int[] initialIndices = form.getIndex().clone();
        form.fall();
        int[] newIndices = form.getIndex();

        for (int i = 0; i < 4; i++) {
            assertEquals(initialIndices[i] + 15, newIndices[i]);
        }
    }

    @Test
    void testingBasicFormturn() {
        int[] initialIndices = form.getIndex().clone();
        int initialScore = form.get_score();
       form.turn();
        int[] newIndices = form.getIndex();

        // Check if the form rotated
        assertNotEquals(initialIndices, newIndices);

        // Check if the score remained the same after rotation
        assertEquals(initialScore, form.get_score());
    }

    @Test
    void testingBasicFormdeactivate() {
        form.deactivate();
        for (BasicBlock block : form.get_blocks()) {
            assertFalse(block.isActive());
        }
    }
}
