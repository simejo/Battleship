package a8.battleship.Tests;


import android.test.InstrumentationTestCase;

import a8.battleship.Logic.Functions;
import a8.battleship.Logic.Variables;
import a8.battleship.Models.Board;

public class FunctionsTests extends InstrumentationTestCase {

    public void testFindY() throws Exception {
        final int expectedY = 3;
        final int positionToChangeToY = 39;
        assertEquals(Functions.findY(positionToChangeToY), expectedY);
    }

    public void testFindX() throws Exception {
        final int expectedX = 9;
        final int positionToChangeToX = 39;
        assertEquals(Functions.findX(positionToChangeToX), expectedX);
    }

    public void testEndGameFalse() throws Exception {
        final boolean expectedBoolean = false;
        final Board boardToTest = new Board(Variables.boardSize);
        assertEquals(Functions.endGame(boardToTest), expectedBoolean);
    }
}
