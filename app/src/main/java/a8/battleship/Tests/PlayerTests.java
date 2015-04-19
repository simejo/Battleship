package a8.battleship.Tests;

import android.test.InstrumentationTestCase;

import java.util.ArrayList;
import java.util.Collections;

import a8.battleship.Logic.Functions;
import a8.battleship.Models.AiPlayer;
import a8.battleship.Models.Player;

/**
 * This class is used for tests on the Player and AiPlayer classes
 */
public class PlayerTests extends InstrumentationTestCase {

    /**
     * This test tests if the given name for the player is correctly set
     * @throws Exception
     */
    public void testSetName() throws Exception{
        final String expectedName = "Ulf";
        final Player playerToTest = new Player();
        playerToTest.setName("Ulf");
        assertEquals(playerToTest.getName(), expectedName);
    }

    /**
     * This test tests if the correct coordinates is added to the prioritizedMoves list
     * @throws Exception
     */
    public void testUpdatePrioritizedMovesMedium() throws Exception{
        final AiPlayer aiPlayerToTest = new AiPlayer();
        aiPlayerToTest.updatePrioritizedMovesMedium(4,5);
        final ArrayList<Integer> expectedList = new ArrayList<Integer>();
        expectedList.add(Functions.findPos(4,6));
        expectedList.add(Functions.findPos(4,4));
        expectedList.add(Functions.findPos(5,5));
        expectedList.add(Functions.findPos(3,5));
        Collections.sort(aiPlayerToTest.getPrioritiedList());
        Collections.sort(expectedList);
        assertEquals(aiPlayerToTest.getPrioritiedList(), expectedList);
    }

}
