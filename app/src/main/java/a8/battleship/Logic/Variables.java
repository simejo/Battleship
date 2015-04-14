package a8.battleship.Logic;

import android.media.AudioManager;
import android.media.MediaPlayer;

import a8.battleship.Adapter.OwnBoardGridAdapter;
import a8.battleship.Adapter.ShootingBoardGridAdapter;
import a8.battleship.Models.AiPlayer;
import a8.battleship.Models.Player;

//This is a class which holds 'variables'
public class Variables {

    //Keeps track of the board size
    public static int boardSize = 10;

    //Keeps track of which layout that is selected
    public static String gameLayout = "boats";


    //public static int screenHeight;
    //public static int screenWidth;

    //Keeps track of the players that is created
    public static Player playerOne;
    public static Player playerTwo;

    public static Player winner;

    public static AiPlayer playerAI;
    //Keeps track of the AIPlayer level
    public static String level = "easy";

    //Keeps track of the sound - enabled or not
    public static boolean cbBooleanSound = true;
    //Keeps track of the music - enabled or not
    public static boolean cbBooleanMusic = true;

    //Keeps track of the last shot - missed or hit
    public static String stringStatus;

    //Keeps track of the opponent
    public static Player opponent;

    //Selected game mode - onePlayer or twoPlayer
    public static String gameMode;

    //Keeps track of which player that is playing - playerOne, playerTwo or playerAI
    public static String turn;

    //Sounds and music
    public static MediaPlayer hit;
    public static MediaPlayer miss;
    public static MediaPlayer backgroundMusic;
    public static AudioManager amSound;
<<<<<<< HEAD
<<<<<<< HEAD
    public static AudioManager amMusic;*/
=======
>>>>>>> 137201eef556d8a4c037c78218fae7fb069689ff

    public static AudioManager amMusic;


    public static ShootingBoardGridAdapter shootingBoardGridAdapter;
    public static OwnBoardGridAdapter ownBoardGridAdapter;
<<<<<<< HEAD

=======
>>>>>>> 137201eef556d8a4c037c78218fae7fb069689ff
}

