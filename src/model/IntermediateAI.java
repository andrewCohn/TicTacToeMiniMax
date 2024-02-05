/**
 * Rick suggests, the IntermediateAI first check to stop a win of the opponent, 
 * then look for its own win. If neither is found, select any other open
 * spot randomly. You may use any other strategy as long as it beats RandomAI.
 * 
 * @authors Rick Mercer and YOUR NAME
 */
package model;

import java.util.Random;

public class IntermediateAI  implements TicTacToeStrategy {
  private Random generator = new Random();

  @Override
  public OurPoint desiredMove(TicTacToeGame theGame) {
    boolean canBlock = false;
    char[][] gameBoard = theGame.getTicTacToeBoard();
    Boolean set = false;
    while (!set) {
      char team = 'O';


     // I wrote the code for the defensive strategy, and then realized that the offensive strategy is largely the same as blocking the other person for winning
     // To avoid repeating code, I iterate over the list twice: first, to see if the other player has a win I can block
     // And again, to see if I have a win that can be made
     for (int k=0; k < 2; k++) {
       // this is where I iterate over the board
       for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
           // if the piece we are examining is for the team we are examining
           if (gameBoard[i][j] == team) {

             // Can we block/play horizontally?
             if (j == 0 && gameBoard[i][j + 1] == team) {
               if (theGame.available(i, 2)) {
                 return new OurPoint(i, 2);
               }
             }
             if (j == 1 && gameBoard[i][j + 1] == team) {
               if (theGame.available(i, 0)) {
                 return new OurPoint(i, 0);
               }
             }

             // Can we block/win vertically?
             if (i == 0 && gameBoard[1][j] == team) {
               if (theGame.available(2, j)) {
                 return new OurPoint(2, j);
               }
             }
             if (i == 1 && gameBoard[2][j] == team) {
               if (theGame.available(0, j)) {
                 return new OurPoint(0, j);
               }
             }

             // Can we block/win diagonally?
             if (i == 0 && j == 0 && gameBoard[1][1] == team) {
               if (theGame.available(2, 2)) {
                 return new OurPoint(2, 2);
               }
             }

             if (i == 0 && j == 2 && gameBoard[1][1] == team) {
               if (theGame.available(2, 0)) {
                 return new OurPoint(2, 0);
               }
             }
             if (i == 2 && j == 0 && gameBoard[1][1] == team) {
               if (theGame.available(0, 2)) {
                 return new OurPoint(0, 2);
               }
             }
             if (i == 2 && j == 2 && gameBoard[1][1] == team) {
               if (theGame.available(0, 0)) {
                 return new OurPoint(0, 0);
               }
             }

             // check if there is a sneaky win via a middle square (X _ X or similar positions)
             if (i == 0 && j == 0 && gameBoard[2][0] == team) {
               if (theGame.available(1, 0)) {
                 return new OurPoint(1, 0);
               }
             }
             if (i == 0 && j == 0 && gameBoard[0][2] == team) {
               if (theGame.available(0, 1)) {
                 return new OurPoint(0, 1);
               }
             }
             if (i == 0 && j == 2 && gameBoard[2][2] == team) {
               if (theGame.available(1, 2)) {
                 return new OurPoint(1, 2);
               }
             }
             if (i == 2 && j == 0 && gameBoard[2][2] == team) {
               if (theGame.available(2, 1)) {
                 return new OurPoint(2, 1);
               }
             }

           }
         }
       }
     team = 'X';
     }
      int row = generator.nextInt(3);
      int col = generator.nextInt(3);
      if (theGame.available(row, col)) {
        set = true;
        return new OurPoint(row, col);
      }
    }
    return null;
  }
}

