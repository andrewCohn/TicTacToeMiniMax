
package views_controllers;

import model.*;

import java.util.Scanner;

public class consoleView {
    private static TicTacToeGame curGame = new TicTacToeGame();
    private static IntermediateAI intermediateAI = new IntermediateAI();
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        while (!(curGame.tied()||curGame.didWin('X')||curGame.didWin('O'))){
            //System.out.println(curGame.toString());
            System.out.println("Enter a row and column");

            String[] split = s.nextLine().split(" ");
            int row;
            int col;
            try {
                row = Integer.parseInt(split[0]);
                col = Integer.parseInt(split[1]);
            }
            catch( NumberFormatException e){
                System.out.println("That's not a number, silly goose!");
                continue;
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Make sure you seperate the integers by whitespace!");
                continue;
            }

            if (row > 2 || row < 0 || col > 2 || col < 0){
                System.out.print("You had a space out of bounds!");
                continue;
            }
            if (curGame.available(row,col)){
                try {
                    curGame.humanMove(row, col, false);
                }
                catch (IGotNowhereToGoException e){
                    System.out.println(curGame.toString());
                    continue;
                }
                System.out.println(curGame.toString());

            }
            else{
                System.out.println("Space is occupied");
                continue;
            }


        }
        if (curGame.tied()){
            System.out.println("You managed to tie!");
        }
        if (curGame.didWin('X')){
            System.out.println("Congrats Human! You managed to fend off the AI revolution!");
        }
        if (curGame.didWin('O')){
            System.out.println("The computer wins... Bow to your new mechanical overlords.");
        }




    }
}
