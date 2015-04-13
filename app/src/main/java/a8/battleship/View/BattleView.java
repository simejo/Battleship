package a8.battleship.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import a8.battleship.Adapter.GridAdapter;
import a8.battleship.Adapter.OwnBoardGridAdapter;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Logic.Constants;
import a8.battleship.Logic.Functions;
import a8.battleship.Models.AiPlayer;
import a8.battleship.Models.Board;
import a8.battleship.Models.Player;
import a8.battleship.R;


/**
 * Created by Kartefull on 11.03.2015.
 * This is the game/boardGridView/battleview.
 * All the logic needed to show the boardGridView on the screen
 */
public class BattleView extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {

    private GridView boardGridView, gridViewOwnBoard;
    private TextView tvBattleTitle, tvScoreCounter;
    private Button buttonNextPlayer, buttonConfirmShot, buttonHome;
    private int currentXPosition, currentYPosition;
    private AiPlayer playerAI = Constants.playerAI;
    private boolean hasShot = false;
    private View selectedCell = null;
    private CheckBox checkBoxSound, checkBoxMusic;
    private AlertDialog.Builder alertDialogBuilder;



    //Need to know which Player is playing
    private Player player;

    //String class name
    private static String className = "BattleView.java";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_view);

        boardGridView = (GridView) findViewById(R.id.boardGridView);
        gridViewOwnBoard = (GridView) findViewById(R.id.gridViewOwnBoard);

        tvBattleTitle = (TextView) findViewById(R.id.textBattleTitle);
        tvScoreCounter = (TextView) findViewById(R.id.textViewScoreCounter);

        buttonNextPlayer = (Button) findViewById(R.id.buttonNextPlayer);
        buttonConfirmShot = (Button) findViewById(R.id.buttonConfirmShot);
        buttonHome = (Button) findViewById(R.id.buttonHome);

        checkBoxMusic = (CheckBox) findViewById(R.id.cbMusic);
        checkBoxSound = (CheckBox) findViewById(R.id.cbSound);

        buttonNextPlayer.setVisibility(Button.INVISIBLE);
        buttonConfirmShot.setVisibility(Button.INVISIBLE);

        boardGridView.setNumColumns(Constants.boardSize);
        boardGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //The small gridView which shows your OWN map
        gridViewOwnBoard.setNumColumns(Constants.boardSize);
        gridViewOwnBoard.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //sounds
        /*Constants.launch = MediaPlayer.create(this, R.raw.launch);
        Constants.hit = MediaPlayer.create(this, R.raw.hit);
        Constants.miss = MediaPlayer.create(this, R.raw.miss);

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        */

        //Check who is playing, so we give the right parameter to the setAdapter-method
        if (Constants.turn.equals("playerOne")){
            if(Constants.gameMode.equals("onePlayer")){
               Constants.opponent = Constants.playerAI;
            }
            else{
                Constants.opponent = Constants.playerTwo;
            }
            player = Constants.playerOne;
            tvBattleTitle.setText(player.getName() + "'s turn to battle");

        }
        else{
            player = Constants.playerTwo;
            Constants.opponent = Constants.playerOne;
            tvBattleTitle.setText(Constants.playerTwo.getName() + "'s turn to battle");

        }
        //TODO: make an else if player is AI (?)

        //Connecting the grids with the adapter
        boardGridView.setAdapter(new GridAdapter(this, Constants.opponent.getBoard()));
        gridViewOwnBoard.setAdapter(new OwnBoardGridAdapter(this, player.getBoard()));


        //Which means that the following lines must be rewritten a bit
        boardGridView.setOnItemClickListener(this);

        buttonNextPlayer.setOnClickListener(this);
        buttonConfirmShot.setOnClickListener(this);
        buttonHome.setOnClickListener(this);

        checkBoxMusic.setOnCheckedChangeListener(this);
        checkBoxSound.setOnCheckedChangeListener(this);

        checkBoxMusic.setChecked(Constants.cbBooleanMusic);
        checkBoxSound.setChecked(Constants.cbBooleanSound);

        tvScoreCounter.setText("Score: " + player.getScore());

    }


    public void onClick(View v) {

        if(v.getId() == R.id.buttonConfirmShot){
            //This prevents the user to shot more than once
            hasShot = true;
            //Log.i(className, "onClick: buttonConfirmShot was clicked");

            Board opponentBoard;
            //Need to get the opponents board to change the values
            if (Constants.turn == "playerOne") {
                if(Constants.gameMode == "onePlayer"){
                    opponentBoard = Constants.playerAI.getBoard();
                }
                else{
                    opponentBoard = Constants.playerTwo.getBoard();
                }
            } else {
                opponentBoard = Constants.playerOne.getBoard();
            }
            BoardValues value = opponentBoard.getValue(currentXPosition,currentYPosition);
            buttonNextPlayer.setVisibility(Button.VISIBLE);
            buttonConfirmShot.setVisibility(Button.INVISIBLE);
            doAction(value, opponentBoard, currentXPosition, currentYPosition);

            boardGridView.setAdapter(new GridAdapter(this, opponentBoard));

            if(Functions.endGame(opponentBoard)){
                Constants.winner = player;
                startActivity(new Intent(BattleView.this, EndGameView.class));
            }

        }
        else if(v.getId() == R.id.buttonNextPlayer){

            if(Constants.gameMode.equals("twoPlayer")){
                if(Constants.turn.equals("playerOne")){
                    Constants.turn = "playerTwo";
                }
                else{
                    Constants.turn = "playerOne";
                }
                Log.i("Battleview", "\n playerOnes board \n " + Constants.playerOne.getBoard().toString() + "\n playerTwos board " + Constants.playerTwo.getBoard().toString());

            }
            else if(Constants.gameMode.equals("onePlayer")){
                if(Constants.turn.equals("playerOne")){
                Log.i("BattleView","turn is " + Constants.turn);
                    //logic - AI MAKES A MOVE
                    Constants.turn = "playerAI";
                    if (playerAI.getLevel().equals("low")){
                        int nextMove = playerAI.aiNextMoveLow();
                        int x = nextMove % Constants.boardSize;
                        int y = Functions.findY(nextMove, Constants.boardSize);
                        BoardValues value = Constants.playerOne.getBoard().getContentInACell(x, y);
                        doAction(value, Constants.playerOne.getBoard(), x, y);
                    }
                    else if (playerAI.getLevel().equals("medium")){
                        int nextMove = playerAI.aiNextMoveMedium();
                        int x = nextMove % Constants.boardSize;
                        int y = Functions.findY(nextMove, Constants.boardSize);
                        BoardValues value = Constants.playerOne.getBoard().getContentInACell(x, y);
                        doAction(value, Constants.playerOne.getBoard(), x, y);
                        Log.i("BattleView","MEDIUM AI");

                    }
                    else if (playerAI.getLevel().equals("hard")){
                        int nextMove = playerAI.aiNextMoveHard();
                        int x = nextMove % Constants.boardSize;
                        int y = Functions.findY(nextMove, Constants.boardSize);
                        BoardValues value = Constants.playerOne.getBoard().getContentInACell(x, y);
                        doAction(value, Constants.playerOne.getBoard(), x, y);
                    }
                }
                else{
                    Constants.turn = "playerOne";
                }
                startActivity(new Intent(BattleView.this, SwitchView.class));
                Log.i("Battleview", "\n playerOnes board \n " + Constants.playerOne.getBoard().toString() + "\n playerAis board " + Constants.playerAI.getBoard().toString());
            }
        }
        else if(v.getId() == R.id.buttonHome){
            alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Back to main menu");
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    startActivity(new Intent(BattleView.this, MainMenuView.class));
                }
            });
            alertDialogBuilder.setNegativeButton("Cancel", null);
            alertDialogBuilder.show();

        }
    }

    //Method to use when a cell is clicked
    //TODO: Implement this, to make changes on the board when clicked
    /*
        parent	    The AdapterView where the click happened.
        view	    The view within the AdapterView that was clicked (this will be a view provided by the adapter)
        position	The position of the view in the adapter.
        id	        The row id of the item that was clicked.
     */
    public void onItemClick(AdapterView parent, View v, int position, long id) {

        /*Board opponentBoard;*/
        if(!(selectedCell == null)){
            selectedCell.setAlpha(1);
        }


        //Checks whether the player has shot or not
        if(!hasShot){
            selectedCell = (View) v;
            selectedCell.setAlpha(0.5f);
            buttonConfirmShot.setVisibility(Button.VISIBLE);
        }
        int boardSize = Constants.boardSize;
        //Finding y
        currentYPosition = Functions.findY(position, boardSize);
        //Finding x
        currentXPosition = position%boardSize;
        Log.i(className, "onItemClick: currentYPosition = " + currentYPosition + ", and currentXPosition = " + currentXPosition);

        /*
        //Need to get the opponents board
        if (Constants.turn == "playerOne") {
            if(Constants.gameMode == "onePlayer"){
                opponentBoard = Constants.playerAI.getBoard();
            }
            else{
                opponentBoard = Constants.playerTwo.getBoard();
            }
        } else {
            opponentBoard = Constants.playerOne.getBoard();
        }
        //Check if this player hit a boat, and execute correct action
        BoardValues value = opponentBoard.getValue(currentXPosition,currentYPosition);
        //Checks what value it is, and performs the correct action
        doAction(value, opponentBoard, currentXPosition, currentYPosition);*/

    }
    //Help method to onItemClick() - Performs the correct action, and changes the Board-model
    //CHANGES THE MODEL

    public void doAction(BoardValues value, Board opponentBoard, int x, int y){
        //Log.i(className, Constants.playerTwo.getBoard().toString());    //Printing board for player 2
        Log.i(className, "X: " + Integer.toString(x) + ", Y: " + Integer.toString(y));

        if(Constants.gameMode =="onePlayer"){
            Constants.stringStatus = "Mor di hit one of your boats \n Mor di has score " + (Constants.playerAI.getScore() + 10);
        }
        else{
            Constants.stringStatus = player.getName() + " hit one of your boats";
        }

        if (value == BoardValues.EAST){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);        //Will update partsLeft in the correct ship (hopefully)
            opponentBoard.changeBoardValue(x,y, BoardValues.EAST_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.SOUTH){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.SOUTH_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.WEST){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.WEST_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.NORTH){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.NORTH_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.MIDDLE_HORIZONTAL){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.MIDDLE_HORIZONTAL_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.MIDDLE_VERTICAL){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.MIDDLE_VERTICAL_DESTROYED);
            addPoints();
        }


        else if (value == BoardValues.EMPTY){
            Constants.miss.start();
            Log.i(className, "LOL, you missed");
            opponentBoard.changeBoardValue(x,y,BoardValues.MISSED);
            removePoints();
            Constants.stringStatus = player.getName() + " missed";
            if(Constants.gameMode =="onePlayer"){
                Constants.stringStatus = "Mor di missed \n Mor di has score " + Constants.playerAI.getScore();
            }
            else{
                Constants.stringStatus = player.getName() + " missed";
            }
        }
        //Checks if it was a valid shot
        else{
            buttonNextPlayer.setVisibility(Button.INVISIBLE);
            buttonConfirmShot.setVisibility(Button.VISIBLE);
            hasShot = false;
            Log.i(className, "Nope, you have already shot here");
            Toast popupBox = Toast.makeText(getApplicationContext(), "You have already shot there!", Toast.LENGTH_SHORT);
            popupBox.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            popupBox.show();

        }
        tvScoreCounter.setText("Score: " + player.getScore());
        //Log.i(className, "Inside doAction()");
    }

    //Help method - to increment/decrement the score to the correct player.
    public void addPoints(){
        if(Constants.gameMode == "twoPlayer"){
            player.incrementScore();
        }
        else{
            if(Constants.turn == "playerOne"){
                player.incrementScore();
            }
            else{
                Log.i("BattleView", "AI ADD POINTS");
                playerAI.incrementScore();
            }
        }
    }
    public void removePoints(){
        if(Constants.gameMode == "twoPlayer"){
            player.decrementScore();
        }
        else{
            if(Constants.turn == "playerOne"){
                player.decrementScore();
            }
            else{
                Log.i("BattleView", "AI REMOVE POINTS");
                playerAI.decrementScore();
            }
        }
    }

    //Method which turns the sound and music on/off
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        if (buttonView.getId() == R.id.cbSound) {

                Log.i("BattleView", "Sound change");
                Constants.cbBooleanSound = isChecked;
                Constants.amSound.setStreamMute(AudioManager.STREAM_MUSIC, !isChecked);
                Log.i("BattleView", buttonView.getId()+ "      " + Constants.cbBooleanMusic + ", " + Constants.cbBooleanSound);
        }
        else if(buttonView.getId() == R.id.cbMusic){


                Log.i("BattleView", "Music change");
                Constants.cbBooleanMusic = isChecked;
                Constants.amMusic.setStreamMute(AudioManager.STREAM_MUSIC, !isChecked);
                Log.i("BattleView", buttonView.getId()+ "      " + Constants.cbBooleanMusic + "," + Constants.cbBooleanSound);

        }

    }

}
