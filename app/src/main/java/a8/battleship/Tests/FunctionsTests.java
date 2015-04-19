package a8.battleship.Tests;


import android.test.InstrumentationTestCase;

import a8.battleship.Logic.Functions;
import a8.battleship.Logic.Variables;
import a8.battleship.Models.Board;

/**
 * This class is made for testing some of the methods in the Functions class
 */
public class FunctionsTests extends InstrumentationTestCase {

    /**
     * This test tests if the method returns the correct Y coordinate depending on a given position
     * @throws Exception
     */
    public void testFindY() throws Exception {
        final int expectedY = 3;
        final int positionToChangeToY = 39;
        assertEquals(Functions.findY(positionToChangeToY), expectedY);
    }

    /**
     * This test tests if the method returns the correct X coordinate depending on a given position
     * @throws Exception
     */
    public void testFindX() throws Exception {
        final int expectedX = 9;
        final int positionToChangeToX = 39;
        assertEquals(Functions.findX(positionToChangeToX), expectedX);
    }

    /**
     * This test tests if a new board will end a game (which it should not)
     * @throws Exception
     */
    public void testEndGameFalse() throws Exception {
        final boolean expectedBoolean = false;
        final Board boardToTest = new Board(Variables.boardSize);
        assertEquals(Functions.endGame(boardToTest), expectedBoolean);
    }
}
