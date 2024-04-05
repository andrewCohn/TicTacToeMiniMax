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
  public Point desiredMove(TTTGame theGame) {
    boolean canBlock = false;
    char[][] gameBoard = theGame.getBoard();
    boolean set = false;
    if (theGame.maxMovesRemaining()!=0){
      while (!set) {
        char team = 'O';


        // I wrote the code for the defensive strategy, and then realized that the offensive strategy is largely the same as blocking the other person from winning
        // To avoid repeating code, I iterate over the list twice: first, to see if the other player has a win I can block
        // And again, to see if I have a win that can be made
        for (int k = 0; k < 2; k++) {
          // this is where I iterate over the board
          for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
              // if the piece we are examining is for the team we are examining
              if (gameBoard[i][j] == team) {

                // Can we block/play horizontally?
                if (j == 0 && gameBoard[i][j + 1] == team) {
                  if (theGame.available(i, 2)) {
                    return new Point(i, 2);
                  }
                }
                if (j == 1 && gameBoard[i][j + 1] == team) {
                  if (theGame.available(i, 0)) {
                    return new Point(i, 0);
                  }
                }

                // Can we block/win vertically?
                if (i == 0 && gameBoard[1][j] == team) {
                  if (theGame.available(2, j)) {
                    return new Point(2, j);
                  }
                }
                if (i == 1 && gameBoard[2][j] == team) {
                  if (theGame.available(0, j)) {
                    return new Point(0, j);
                  }
                }

                // Can we block/win diagonally?
                // bottom right
                if (i == 0 && j == 0 && gameBoard[1][1] == team) {
                  if (theGame.available(2, 2)) {
                    return new Point(2, 2);
                  }
                }
                // bottom left
                if (i == 0 && j == 2 && gameBoard[1][1] == team) {
                  if (theGame.available(2, 0)) {
                    return new Point(2, 0);
                  }
                }
                // top right
                if (i == 2 && j == 0 && gameBoard[1][1] == team) {
                  if (theGame.available(0, 2)) {
                    return new Point(0, 2);
                  }
                }
                // top left
                if (i == 2 && j == 2 && gameBoard[1][1] == team) {
                  if (theGame.available(0, 0)) {
                    return new Point(0, 0);
                  }
                }

                // check if there is a sneaky win via a middle square (X _ X or similar positions)

                // left middle
                if (i == 0 && j == 0 && gameBoard[2][0] == team) {
                  if (theGame.available(1, 0)) {
                    return new Point(1, 0);
                  }
                }
                // top middle
                if (i == 0 && j == 0 && gameBoard[0][2] == team) {
                  if (theGame.available(0, 1)) {
                    return new Point(0, 1);
                  }
                }
                // right middle
                if (i == 0 && j == 2 && gameBoard[2][2] == team) {
                  if (theGame.available(1, 2)) {
                    return new Point(1, 2);
                  }
                }
                //bottom middle
                if (i == 2 && j == 0 && gameBoard[2][2] == team) {
                  if (theGame.available(2, 1)) {
                    return new Point(2, 1);
                  }
                }
                // true middle
                if (i == 1 && j == 0 && gameBoard[1][1] == team) {
                  if (theGame.available(1, 1)) {
                    return new Point(1, 1);
                  }
                }

              }
            }
          }
          team = 'X';
        }
        // we don't have any immediate wins to take or block: play randomly
        // A smarter programmer with more time might write code that plays to a win
        // I am neither!
        int row = generator.nextInt(3);
        int col = generator.nextInt(3);
        if (theGame.available(row, col)) {
          set = true;
          return new Point(row, col);
        }
      }
      return null; // this should never happen. If it does, something will probably break.
    }
    else{
      throw new IGotNowhereToGoException("No moves left!");
    }
  }
}

