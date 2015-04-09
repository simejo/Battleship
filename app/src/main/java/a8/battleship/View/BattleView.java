package a8.battleship.View;

import android.content.Context;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
public class BattleView extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private GridView boardGridView, gridViewOwnBoard;
    private TextView tvBattleTitle;
    private Button buttonNextPlayer, buttonConfirmShot;
    private int currentXPosition, currentYPosition;
    private AiPlayer playerAI = Constants.playerAI;
    private boolean hasShot = false;
    private View selectedCell = null;


    //Need to know which Player is playing
    Player player;

    //String class name
    private static String className = "BattleView.java";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_view);

        boardGridView = (GridView) findViewById(R.id.boardGridView);
        gridViewOwnBoard = (GridView) findViewById(R.id.gridViewOwnBoard);

        tvBattleTitle = (TextView) findViewById(R.id.textBattleTitle);
        buttonNextPlayer = (Button) findViewById(R.id.buttonNextPlayer);
        buttonConfirmShot = (Button) findViewById(R.id.buttonConfirmShot);

        buttonNextPlayer.setVisibility(Button.INVISIBLE);
        buttonConfirmShot.setVisibility(Button.INVISIBLE);


        boardGridView.setNumColumns(Constants.boardSize);
        boardGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //The small gridView which shows your OWN map
        gridViewOwnBoard.setNumColumns(Constants.boardSize);
        gridViewOwnBoard.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //sounds
        Constants.launch = MediaPlayer.create(this, R.raw.launch);
        Constants.hit = MediaPlayer.create(this, R.raw.hit);
        Constants.miss = MediaPlayer.create(this, R.raw.miss);

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);


        //Check who is playing, so we give the right parameter to the setAdapter-method
        if (Constants.turn.equals("playerOne")){
            if(Constants.gameMode.equals("onePlayer")){
               Constants.opponent = Constants.playerAI;

            }
            else{
                Constants.opponent = Constants.playerTwo;
            }
            player = Constants.playerOne;
            tvBattleTitle.setText(Constants.playerOne.getName() + "'s turn to battle");

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

    }


    public void onClick(View v) {

        if(v.getId() == R.id.buttonConfirmShot){
            //This prevents the user to shot more than once
            hasShot = true;
            //Log.i(className, "onClick: buttonConfirmShot was clicked");

            /*Constants.launch.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Constants.launch.isPlaying()){
                Log.i(className, "launch noise" );
            }
            */

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

        }
        if(v.getId() == R.id.buttonNextPlayer){
            //Log.i(className, "onClick: buttonNextPlayer was clicked");


            startActivity(new Intent(BattleView.this, SwitchView.class));
            if(Constants.gameMode.equals("twoPlayer")){
                if(Constants.turn.equals("playerOne")){
                    Constants.turn = "playerTwo";
                }
                else{
                    Constants.turn = "playerOne";
                }
            }
            else if(Constants.gameMode.equals("onePlayer")){
                if(Constants.turn.equals("playerOne")){
                    //Constants.turn = "playerAI";
                    //logic - AI MAKES A MOVE
                    if (playerAI.getLevel().equals("low")){
                        int nextMove = playerAI.aiNextMoveLow();
                        int x = nextMove % Constants.boardSize;
                        int y = findY(nextMove, Constants.boardSize);
                        BoardValues value = playerAI.getBoard().getContentInACell(x, y);
                        doAction(value, Constants.playerOne.getBoard(), x, y);
                    }
                    else if (playerAI.getLevel().equals("medium")){

                    }
                    else if (playerAI.getLevel().equals("hard")){

                    }


                }
                else{
                    Constants.turn = "playerOne";
                }
            }
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
        currentYPosition = findY(position, boardSize);
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
    //Help method to onItemClick() - find y
    public int findY(int position, int boardSize){
        int tempPos = position;
        int y = 0;
        for (int i = 0; i < boardSize; i++){
            if(tempPos > boardSize){
                y++;                    //Y is not in this row, need to update the temporary y-value
                tempPos-= boardSize;    //Minus boardSize, so we can check if y is in the next row.
            }
            else{
                break;
            }
        }
        return y;
    }
    //Help method to onItemClick() - Performs the correct action, and changes the Board-model
    //CHANGES THE MODEL

    public void doAction(BoardValues value, Board opponentBoard, int x, int y){
        //Log.i(className, Constants.playerTwo.getBoard().toString());    //Printing board for player 2
        Log.i(className, "X: " + Integer.toString(x) + ", Y: " + Integer.toString(y));
        if (value == BoardValues.EAST){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);        //Will update partsLeft in the correct ship (hopefully)
            opponentBoard.changeBoardValue(x,y, BoardValues.EAST_DESTROYED);

        }
        else if (value == BoardValues.SOUTH){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.SOUTH_DESTROYED);

        }
        else if (value == BoardValues.WEST){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.WEST_DESTROYED);
        }
        else if (value == BoardValues.NORTH){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.NORTH_DESTROYED);
        }
        else if (value == BoardValues.MIDDLE_HORIZONTAL){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.MIDDLE_HORIZONTAL_DESTROYED);
        }
        else if (value == BoardValues.MIDDLE_VERTICAL){
            Constants.hit.start();
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.MIDDLE_VERTICAL_DESTROYED);
        }
        else if (value == BoardValues.EMPTY){
            Constants.miss.start();
            Log.i(className, "LOL, you missed");
            opponentBoard.changeBoardValue(x,y,BoardValues.MISSED);

        }
        //TODO: It is possible to fire a shot at the MISSED enum, this must be fixed
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
        //Log.i(className, "Inside doAction()");


    }

    /*public void initiateWidgets() {

    }*/
}
