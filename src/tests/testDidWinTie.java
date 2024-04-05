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
        assertTrue(temp.isOver());
    }

}
