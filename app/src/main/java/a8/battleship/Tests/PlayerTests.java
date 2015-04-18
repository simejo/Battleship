package a8.battleship.Tests;

import android.test.InstrumentationTestCase;

import java.util.ArrayList;
import java.util.Collections;

import a8.battleship.Logic.Functions;
import a8.battleship.Models.AiPlayer;
import a8.battleship.Models.Player;

public class PlayerTests extends InstrumentationTestCase {

    public void testSetName() throws Exception{
        final String expectedName = "Ulf";
        final Player playerToTest = new Player();
        playerToTest.setName("Ulf");
        assertEquals(playerToTest.getName(), expectedName);
    }

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
