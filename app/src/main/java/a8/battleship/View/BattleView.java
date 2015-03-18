package a8.battleship.View;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
public class BattleView extends ActionBarActivity{



    GridView boardGridView;

    //Need to know which Player is playing
    Player player;

    //test data
    static final String[] letters = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_view);

        boardGridView = (GridView) findViewById(R.id.boardGridView);

        boardGridView.setNumColumns(Constants.numOfCollumns);

        boardGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //Check who is playing, so we give the right parameter to the setAdapter-method
        if (Constants.turn){
            player = Constants.playerOne;
        }
        else{
            player = Constants.playerTwo;
        }
        boardGridView.setAdapter(new GridAdapter(this, player.getBoard()));



        //TODO: Implement OnItemClickListener, and write the method in the class. See SetShipView as an example
        //Which means that the following lines must be rewritten a bit
        boardGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                //Toast.makeText(getApplicationContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
