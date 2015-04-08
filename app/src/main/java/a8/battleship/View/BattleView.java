package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import a8.battleship.Adapter.GridAdapter;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Logic.Constants;
import a8.battleship.Logic.Functions;
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

    //Need to know which Player is playing
    Player player;

    //String class name
    private String className = "BattleView.java";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_view);

        boardGridView = (GridView) findViewById(R.id.boardGridView);
        gridViewOwnBoard = (GridView) findViewById(R.id.gridViewOwnBoard);

        tvBattleTitle = (TextView) findViewById(R.id.textBattleTitle);
        buttonNextPlayer = (Button) findViewById(R.id.buttonNextPlayer);
        buttonConfirmShot = (Button) findViewById(R.id.buttonConfirmShot);


        boardGridView.setNumColumns(Constants.boardSize);
        boardGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //The small gridView which shows your OWN map
        gridViewOwnBoard.setNumColumns(Constants.boardSize);
        gridViewOwnBoard.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //Check who is playing, so we give the right parameter to the setAdapter-method
        if (Constants.turn == "playerOne"){
            if(Constants.gameMode == "onePlayer"){
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
        gridViewOwnBoard.setAdapter(new GridAdapter(this, player.getBoard()));


        //Which means that the following lines must be rewritten a bit
        boardGridView.setOnItemClickListener(this);

        buttonNextPlayer.setOnClickListener(this);
        buttonConfirmShot.setOnClickListener(this);

    }

    //TODO: Need to add a button in the activity_battle_view.xml file
    //(So we can get to the "change player" screen)
    public void onClick(View v) {
        /*if(v.getId() == R.id.buttonDone){
            startActivity(new Intent(SetShipView.this, BattleView.class));
        }*/
        if(v.getId() == R.id.buttonConfirmShot){
            //Log.i(className, "onClick: buttonConfirmShot was clicked");

            Board opponentBoard;
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
            BoardValues value = opponentBoard.getValue(currentXPosition,currentYPosition);
            if(value == BoardValues.EMPTY){
                value = BoardValues.MISSED;
            }
            doAction(value, opponentBoard, currentXPosition, currentYPosition);

        }
        if(v.getId() == R.id.buttonNextPlayer){
            //Log.i(className, "onClick: buttonNextPlayer was clicked");
            startActivity(new Intent(BattleView.this, SwitchView.class));
            if(Constants.gameMode == "twoPlayer"){
                if(Constants.turn == "playerOne"){
                    Constants.turn = "playerTwo";
                }
                else{
                    Constants.turn = "playerOne";
                }
            }
            else if(Constants.gameMode == "onePlayer"){
                if(Constants.turn == "playerOne"){
                    Constants.turn = "playerAI";
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
        Log.i(className, Constants.playerTwo.getBoard().toString());    //Printing board for player 2
        Log.i(className, "X: " + Integer.toString(x) + ", Y: " + Integer.toString(y));
        if (value == BoardValues.EAST){
            Functions.findAndUpdateShip(x,y,Constants.opponent);        //Will update partsLeft in the correct ship (hopefully)
            opponentBoard.changeBoardValue(x,y, BoardValues.EAST_DESTROYED);
            printSuccess();

        }
        else if (value == BoardValues.SOUTH){
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x,y,BoardValues.SOUTH_DESTROYED);
            printSuccess();

        }
        else if (value == BoardValues.WEST){
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x,y,BoardValues.WEST_DESTROYED);
            printSuccess();
        }
        else if (value == BoardValues.NORTH){
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x,y,BoardValues.NORTH_DESTROYED);
            printSuccess();
        }
        else if (value == BoardValues.MIDDLE_HORIZONTAL){
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x,y,BoardValues.MIDDLE_HORIZONTAL_DESTROYED);
            printSuccess();
        }
        else if (value == BoardValues.MIDDLE_VERTICAL){
            Functions.findAndUpdateShip(x,y,Constants.opponent);
            opponentBoard.changeBoardValue(x,y,BoardValues.MIDDLE_VERTICAL_DESTROYED);
            printSuccess();
        }
        else if (value == BoardValues.EMPTY){
            Log.i(className, "LOL, you missed");
            Toast toast = Toast.makeText(getApplicationContext(), "You missed!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            opponentBoard.changeBoardValue(x,y,BoardValues.MISSED);

        }
        //Checks if it was a valid shot
        else if (value == BoardValues.MIDDLE_HORIZONTAL_DESTROYED |value == BoardValues.MIDDLE_VERTICAL_DESTROYED |
                value == BoardValues.NORTH_DESTROYED | value == BoardValues.WEST_DESTROYED |
                value == BoardValues.SOUTH_DESTROYED | value == BoardValues.EAST_DESTROYED | value == BoardValues.MISSED){
            Log.i(className, "Nope, you have already shot here");
            Toast toast = Toast.makeText(getApplicationContext(), "You have already shot here!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

        }
        //Log.i(className, "Inside doAction()");


    }

    /* If success - this method should be called
     * Printing to logcat and makes a toast (Pop-up text on the screen)
    */
    public void printSuccess(){
        Log.i(className, "Wohooo, you hit a boat!");
        Toast toast = Toast.makeText(getApplicationContext(), "YOU HIT A BOAT!",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
    public void initiateWidgets() {

    }
}
