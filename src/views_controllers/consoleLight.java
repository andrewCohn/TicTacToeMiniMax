package views_controllers;

import model.IGotNowhereToGoException;
import model.IntermediateAI;
import model.Point;
import model.TTTGame;

import java.util.Scanner;

public class consoleLight {
    private static TTTGame game;
    private static IntermediateAI compPlayer = new IntermediateAI();

    public static void main(String[] args) {
        game = new TTTGame(); // create the game
        System.out.println(game.toString()); // print a blank board
        Scanner s = new Scanner(System.in); // read user input
        while (!game.isOver()) { // game loop
            System.out.println("Enter a move:\n"); // prompt for user input
            int row; // instantiate user input moves
            int col;
            try {
                // get the user input, split on whitespace, convert str -> int
                String usrInput = s.nextLine();
                row = Integer.valueOf(usrInput.split(" ")[0]);
                col = Integer.valueOf(usrInput.split(" ")[1]);
            // handle some errors
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Make sure you enter two integers seperated by whitespace.");
                continue;
            }
            catch (NumberFormatException e){
                System.out.println("Are you sure you entered integers?");
                continue;
            }
            // did the user enter a valid move?
            if (!game.available(row,col)){
                System.out.println("Space played!");
                continue;
            }
            // try to make a move
            try {
                game.makeMove('X', new Point(row, col));
            }
            // catch a cheeky user trying to go to a space out of bounds
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Make sure you enter an integer between 0 and 2 (inclusive)");
            }
            // computer makes a move
            try {
                Point aiPoint = compPlayer.desiredMove(game);
                game.makeMove('O', aiPoint);
            }
            // if the computer can't move, the loop should end on the next iteration.
            catch (IGotNowhereToGoException e){
                continue;
            }
            System.out.println(game); // show the new game state

        }
        s.close(); // prevents computer from exploding


    }
}
