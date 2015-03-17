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

import a8.battleship.Logic.BoardValues;
import a8.battleship.Logic.Constants;
import a8.battleship.Models.Board;
import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 * This is the view where the player can place his/hers boats
 */
public class SetShipView extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener{



    //Is it in this class we should instantiate the board?
    public Board boardModel;

    private Button buttonStartGame;

    GridView setShipGridView;

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

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        Toast.makeText(getApplicationContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
    }

    //Connecting with the XML-objects
    public void initiateWidgets(){
        buttonStartGame = (Button) findViewById(R.id.buttonDone);
        buttonStartGame.setOnClickListener(this);

        setShipGridView = (GridView) findViewById(R.id.setShipGridView);

        setShipGridView.setNumColumns(Constants.numOfCollumns);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, letters);

        setShipGridView.setAdapter(adapter);
        //Gives the adapter onItemClickListener
        setShipGridView.setOnItemClickListener(this);
    }
}
