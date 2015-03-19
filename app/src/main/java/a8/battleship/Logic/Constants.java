package a8.battleship.Logic;

import a8.battleship.Models.Board;
import a8.battleship.Models.Player;

/**
 * Created by majakirkerod on 11.03.15.
 */
public class Constants {

    //Why do we have both boardSize and numOfCollumns? I keep using numOfColluns since it's already been used
    public static int boardSize = 10;
    public static int numOfCollumns=10;

    public static int screenHeight;
    public static int screenWidth;

    public static Board boardPlayerOne;
    public static Board boardPlayerTwo;

    public static Player playerOne;
    public static Player playerTwo;
    public static Player playerAI;

    public static Player opponent;

    //Selected game mode

    public static String gameMode;

    //Player one or player two is playing
    public static String turn;

}
