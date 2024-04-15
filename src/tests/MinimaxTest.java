package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import model.*;


public class MinimaxTest {
    @Test
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
    @Test
    public void testDiagWin(){
        char[][] diagWin = {{'O', 'X', '_'},{'_', 'X', 'X'},{'O', '_', 'O'}};

        /*
        X X O
        _ _ _
        O _ X
         */
        TTTGame temp = new TTTGame(); // make the game
        temp.setBoard(diagWin); // set the board to the predefined position
        System.out.println(temp);
        GameTree tempTree = new GameTree(temp); // make a game tree
        tempTree.buildTree('O'); // build the game tree, from the current player's move
        Point ourValue = tempTree.getBestMove('O'); // decide what the current players best move is
        temp.makeMove('O',ourValue);
        Point actualBest = new Point(1,1);
        assertEquals(actualBest,ourValue);
    }
    @Test
    public void testRowWin(){
        char[][] rowWin = {{'O', 'X', 'X'},{'X', 'X', '_'},{'O', 'O', '_'}};

        /*
        O X X
        X X _
        O O _
         */
        TTTGame temp = new TTTGame(); // make the game
        temp.setBoard(rowWin); // set the board to the predefined position
        System.out.println(temp);
        GameTree tempTree = new GameTree(temp); // make a game tree
        tempTree.buildTree('O'); // build the game tree, from the current player's move
        Point ourValue = tempTree.getBestMove('O'); // decide what the current players best move is
        temp.makeMove('O',ourValue);
        Point actualBest = new Point(2,2);
        assertEquals(actualBest,ourValue);
    }




}
