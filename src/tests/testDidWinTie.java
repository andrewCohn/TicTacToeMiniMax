package tests;
import static org.junit.Assert.*;

import model.*;
import org.junit.Test;
public class testDidWinTie {
    @Test
    public void testDidWin(){
        char[][] colWin = {{'_', 'X', '_'},{'_', 'X', '_'},{'_', 'X', '_'}};
        TTTGame temp = new TTTGame();
        temp.setBoard(colWin);
        assertTrue(temp.didWin('X'));
        assertFalse(temp.tied());
        assertTrue(temp.isOver());
        char[][] rowWin = {{'X','X','X'},{'_','_','_'},{'_','_','_'}};
        temp = new TTTGame();
        temp.setBoard(rowWin);
        assertTrue(temp.didWin('X'));
        assertFalse(temp.tied());
        assertTrue(temp.isOver());
        char[][] diagWin = {{'X','_','_'},{'_','X','_'},{'_','_','X'}};
        temp = new TTTGame();
        temp.setBoard(diagWin);
        assertTrue(temp.didWin('X'));
        assertFalse(temp.tied());
        assertTrue(temp.isOver());



        }


    }


