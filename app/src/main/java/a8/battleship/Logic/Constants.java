package a8.battleship.Logic;

import a8.battleship.Models.Board;
import a8.battleship.Models.Player;

/**
 * Created by majakirkerod on 11.03.15.
 */
public class Constants {

    //Why do we have both boardSize and boardSize? I keep using numOfColluns since it's already been used
    public static int boardSize = 10;

    public static int screenHeight;
    public static int screenWidth;

    public static Board boardPlayerOne;
    public static Board boardPlayerTwo;

    public static Player playerOne;
    public static Player playerTwo;
    public static Player playerAI;

    //This will always be the players opponent
    public static Player opponent;

    //Selected game mode
    //onePlayer or twoPlayer
    public static String gameMode;

    //Player one or player two is playing
    //playerOne or playerTwo or playerAI
    public static String turn;

}
