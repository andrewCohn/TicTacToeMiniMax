package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import model.*;


public class MinimaxTest {
	@Test(timeout = 5000)
    public void testBlock(){
        // O TO MOVE

        char[][] colBlock = {{'O', 'X', '_'},{'_', 'X', '_'},{'_', '_', '_'}};
        /*
        O X _
        _ X _
        _ _ _
        */

        TTTGame temp = new TTTGame(); // make the game
        temp.setBoard(colBlock); // set the board to the predefined position
        GameTree tempTree = new GameTree(temp); // make a game tree
        tempTree.buildTree('O'); // build the game tree, from the current player's move
        Point ourValue = tempTree.getBestMove('O'); // decide what the current players best move is
        // does the generated point match what we as humans understand to be the best?
        Point actualBest = new Point(2,1);
        assertEquals(actualBest,ourValue);

    }
}
