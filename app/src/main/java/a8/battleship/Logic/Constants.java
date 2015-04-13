package a8.battleship.Logic;

import android.media.AudioManager;
import android.media.MediaPlayer;

import a8.battleship.Models.AiPlayer;
import a8.battleship.Models.Board;
import a8.battleship.Models.Player;

/**
 * Created by majakirkerod on 11.03.15.
 */
public class Constants {

    public static int boardSize = 10;

    public static String gameLayout = "boats";

    public static int screenHeight;
    public static int screenWidth;

    public static Board boardPlayerOne;
    public static Board boardPlayerTwo;

    public static Player playerOne;
    public static Player playerTwo;

    public static Player winner;

    public static AiPlayer playerAI;
    public static String level = "low";

    public static boolean cbBooleanSound = true;
    public static boolean cbBooleanMusic = true;


    //This string will keep track of the last shot (missed or hit)
    public static String stringStatus;

    //This will always be the players opponent
    public static Player opponent;

    //Selected game mode
    //onePlayer or twoPlayer
    public static String gameMode;

    //Player one or player two is playing
    //playerOne or playerTwo or playerAI
    public static String turn;

    //public static MediaPlayer launch;
    public static MediaPlayer hit;
    public static MediaPlayer miss;
    public static MediaPlayer backgroundMusic;
    public static AudioManager amSound;
    public static AudioManager amMusic;
}
