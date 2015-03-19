package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import a8.battleship.Adapter.GridAdapter;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Logic.Constants;
import a8.battleship.Models.Board;
import a8.battleship.Models.Player;
import a8.battleship.R;


/**
 * Created by Kartefull on 11.03.2015.
 * This is the game/boardGridView/battleview.
 * All the logic needed to show the boardGridView on the screen
 */
public class BattleView extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    GridView boardGridView;

    //Need to know which Player is playing
    Player player;

    //String class name
    private String className = "BattleView.java";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_view);

        boardGridView = (GridView) findViewById(R.id.boardGridView);

        boardGridView.setNumColumns(Constants.numOfCollumns);

        boardGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //Check who is playing, so we give the right parameter to the setAdapter-method
        if (Constants.turn == "playerOne"){
            player = Constants.playerOne;
        }
        else{
            player = Constants.playerTwo;
        }
        boardGridView.setAdapter(new GridAdapter(this, player.getBoard()));


        //TODO: Implement OnItemClickListener, and write the method in the class. See SetShipView as an example
        //Which means that the following lines must be rewritten a bit
        boardGridView.setOnItemClickListener(this);

    }

    //TODO: Need to add a button in the activity_battle_view.xml file
    //(So we can get to the "change player" screen)
    public void onClick(View v) {
        /*if(v.getId() == R.id.buttonDone){
            startActivity(new Intent(SetShipView.this, BattleView.class));
        }*/
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
        Board opponentBoard;
        int boardSize = Constants.numOfCollumns;

        //Finding y
        int y = findY(position, boardSize);
        //Finding x
        int x = position%boardSize;
        //Need to get the opponents board
        if (Constants.turn == "playerOne") {
            opponentBoard = Constants.playerTwo.getBoard();
        } else {
            opponentBoard = Constants.playerOne.getBoard();
        }
        //Check if this player hit a boat, and execute correct action
        BoardValues value = opponentBoard.getValue(x,y);
        //Checks what value it is, and performs the correct action
        doAction(value, opponentBoard, x, y);

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
    public void doAction(BoardValues value, Board opponentBoard, int x, int y){
        if (value == BoardValues.EAST){
            opponentBoard.changeBoardValue(x,y,BoardValues.EAST_DESTROYED);
        }
        else if (value == BoardValues.SOUTH){
            opponentBoard.changeBoardValue(x,y,BoardValues.SOUTH_DESTROYED);

        }
        else if (value == BoardValues.WEST){
            opponentBoard.changeBoardValue(x,y,BoardValues.WEST_DESTROYED);
        }
        else if (value == BoardValues.NORTH){
            opponentBoard.changeBoardValue(x,y,BoardValues.NORTH_DESTROYED);
        }
        else if (value == BoardValues.MIDDLE){
            opponentBoard.changeBoardValue(x,y,BoardValues.MIDDLE_DESTROYED);
        }
        //Checks if it was a valid shot
        else if (value == BoardValues.MIDDLE_DESTROYED | value == BoardValues.NORTH_DESTROYED |
                value == BoardValues.WEST_DESTROYED | value == BoardValues.SOUTH_DESTROYED
                | value == BoardValues.EAST_DESTROYED){
            Log.i(className, "Nope, you have already shot here");

        }
        else if (value == BoardValues.EMPTY){
            Log.i(className, "LOL, you missed");
            //TODO:

        }

    }
    public void initiateWidgets() {

    }
}
