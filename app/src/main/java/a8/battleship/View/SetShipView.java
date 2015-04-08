package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import a8.battleship.Adapter.GridAdapter;
import a8.battleship.Logic.Constants;
import a8.battleship.Models.Board;
import a8.battleship.Models.Player;
import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 * This is the view where the player can place his/hers boats
 */
public class SetShipView extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener{



    /*
    Is it in this class we should instantiate the board?

    Probably not. But we should call the Board object here (Constants.boardPlayerOne)
    The board should be instantiate in the Board class (I think).
    */

    public Board boardModel;

    private Button buttonStartGame, buttonRandomizeShips;
    private TextView tvHeader;

    //Need to know which Player is playing
    Player player;

    GridView setShipGridView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ship_view);

        initiateWidgets();
    }


    public void onClick(View v){
        if(v.getId() == R.id.buttonDone){
            if (Constants.gameMode == "onePlayer"){
                Constants.playerAI.setBoard(new Board(Constants.boardSize));
                Constants.turn = "playerOne";
                startActivity(new Intent(SetShipView.this, BattleView.class));
            }
            else if(Constants.gameMode == "twoPlayer"){
                if(Constants.turn == "playerTwo") {
                    Constants.turn = "playerOne";
                    startActivity(new Intent(SetShipView.this, BattleView.class));
                }
                else {
                    Constants.turn = "playerTwo";
                    startActivity(new Intent(SetShipView.this, SetShipView.class));
                }
            }
        }
        else if(v.getId() == R.id.buttonRandomize){
            if(Constants.turn == "playerOne"){
                Constants.playerOne.setBoard(new Board(Constants.boardSize));
                setShipGridView.setAdapter(new GridAdapter(this, player.getBoard()));
            }
            else{
                Constants.playerTwo.setBoard(new Board(Constants.boardSize));
                setShipGridView.setAdapter(new GridAdapter(this, player.getBoard()));

            }
        }
    }

    //Method to use when a cell is clicked
    //TODO: Implement this, to make changes on the board when clicked
    public void onItemClick(AdapterView parent, View v, int position, long id) {

    }

    //Connecting with the XML-objects
    public void initiateWidgets(){

        tvHeader = (TextView) findViewById(R.id.switchViewHeader);

        buttonStartGame = (Button) findViewById(R.id.buttonDone);
        buttonRandomizeShips = (Button) findViewById(R.id.buttonRandomize);

        buttonStartGame.setOnClickListener(this);
        buttonRandomizeShips.setOnClickListener(this);

        setShipGridView = (GridView) findViewById(R.id.setShipGridView);

        setShipGridView.setNumColumns(Constants.numOfCollumns);


        //Check who is playing, so we give the right parameter to the setAdapter-method
        if (Constants.turn == "playerTwo"){
            tvHeader.setText("Place boats for " + Constants.playerTwo.getName());
            player = Constants.playerTwo;
        }
        else{
            tvHeader.setText("Place boats for " + Constants.playerOne.getName());
            player = Constants.playerOne;
        }
        setShipGridView.setAdapter(new GridAdapter(this, player.getBoard()));

        //Gives the adapter onItemClickListener
        setShipGridView.setOnItemClickListener(this);
    }
}
