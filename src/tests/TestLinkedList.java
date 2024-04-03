package tests;
import static org.junit.Assert.*;

import model.*;
import org.junit.Test;

public class TestLinkedList {

    @Test
    public void testAdd() {
        int n = 10;
        int[] testArr = new int[n];
        LinkedList myList = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            testArr[i] = i;
            myList.add(i);
        }
        for (int i = 0; i < n; i++) {

            assertEquals(myList.get(i), n - 1 - testArr[i]);
        }


    }

    @Test
    public void testAddToEnd() {
        int n = 10;
        int[] testArr = new int[n];
        LinkedList myList = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            testArr[i] = i;
            myList.addToEnd(i);
        }
        for (int i = 0; i < n; i++) {

            assertEquals(myList.get(i), testArr[i]);
        }

    }
}
