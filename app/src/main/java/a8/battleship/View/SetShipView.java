package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
 * This is the view where the player can place his/hers boats
 */
public class SetShipView extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener{



    /*
    Is it in this class we should instantiate the board?

    Probably not. But we should call the Board object here (Constants.boardPlayerOne)
    The board should be instantiate in the Board class (I think).
    */

    public Board boardModel;

    private Button buttonStartGame;

    //Need to know which Player is playing
    Player player;

    GridView setShipGridView;

    //Test input
    static final String[] letters = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    static final BoardValues[] images = new BoardValues[] {
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ship_view);

        initiateWidgets();
    }

    public void onClick(View v){
        if(v.getId() == R.id.buttonDone){
            startActivity(new Intent(SetShipView.this, BattleView.class));
        }
    }

    //Method to use when a cell is clicked
    //TODO: Implement this, to make changes on the board when clicked
    public void onItemClick(AdapterView parent, View v, int position, long id) {

    }

    //Connecting with the XML-objects
    public void initiateWidgets(){
        buttonStartGame = (Button) findViewById(R.id.buttonDone);
        buttonStartGame.setOnClickListener(this);

        setShipGridView = (GridView) findViewById(R.id.setShipGridView);

        setShipGridView.setNumColumns(Constants.numOfCollumns);


        //Check who is playing, so we give the right parameter to the setAdapter-method
        if (Constants.turn == "playerOne"){
            player = Constants.playerOne;
        }
        else{
            player = Constants.playerTwo;
        }
        setShipGridView.setAdapter(new GridAdapter(this, letters, player.getBoard()));
        //setShipGridView.setAdapter(new GridAdapter(this, letters));

        //Gives the adapter onItemClickListener
        setShipGridView.setOnItemClickListener(this);
    }
}
