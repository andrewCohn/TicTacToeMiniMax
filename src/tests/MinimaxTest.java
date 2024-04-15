package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

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
        char[][] diagWin = {{'O', 'X', 'O'},{'X', '_', '_'},{'O', '_', '_'}};

        /*
        X X O
        X _ _
        O _ _
         */
        TTTGame temp = new TTTGame(); // make the game
        temp.setBoard(diagWin); // set the board to the predefined position
        System.out.println(temp);
        GameTree tempTree = new GameTree(temp); // make a game tree
        tempTree.buildTree('O'); // build the game tree, from the current player's move
        Point ourValue = tempTree.getBestMove('O'); // decide what the current players best move is
        temp.makeMove('O',ourValue);
        System.out.println(temp);
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
        System.out.println(temp);
        Point actualBest = new Point(2,2);
        assertEquals(actualBest,ourValue);
    }
    @Test
    public void testAgainstItself(){
        int n = 300; // number of games to play
        int oWins = 0;
        int xWins = 0;
        int tied = 0;
        for (int i = 0; i<n; i++) {
            TTTGame game = new TTTGame();
            Random random = new Random();
            Point startingPoint = new Point(random.nextInt(3), random.nextInt(3));
            game.makeMove('X', startingPoint);
            //System.out.println(game);
            while (!game.isOver()) {
                GameTree curTree = new GameTree(game);
                curTree.buildTree('O');
                Point oMove = curTree.getBestMove('O');
                game.makeMove('O', oMove);
                //System.out.println(game);
                if (game.isOver()) {
                    break;
                }
                curTree = new GameTree(game);
                curTree.buildTree('X');
                Point xMove = curTree.getBestMove('X');
                game.makeMove('X', xMove);
                //System.out.println(game);
            }
            if (game.didWin('X')){
                xWins++;
            }
            if (game.didWin('O')){
                oWins++;
            }
            if (game.tied()){
                tied++;
            }
        }
        int sum = xWins + oWins + tied;
        System.out.println("X Wins: " + xWins + " (" + getPercentage(xWins, sum) + "%)");
        System.out.println(generateHashtags(xWins, sum));
        System.out.println("O Wins: " + oWins + " (" + getPercentage(oWins, sum) + "%)");
        System.out.println(generateHashtags(oWins, sum));
        System.out.println("Tied: " + tied + " (" + getPercentage(tied, sum) + "%)");
        System.out.println(generateHashtags(tied, sum));


    }
    private String generateHashtags(int value, int total) {
        int scaledValue = (int) ((double) value / total * 20); // Scale to a maximum of 20 hashtags
        StringBuilder hashtags = new StringBuilder();
        for (int i = 0; i < scaledValue; i++) {
            hashtags.append("#");
        }
        return hashtags.toString();
    }

    private double getPercentage(int value, int total) {
        return ((double) value / total) * 100;
    }




}
