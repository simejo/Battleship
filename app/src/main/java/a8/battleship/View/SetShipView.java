package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import a8.battleship.Adapter.OwnBoardGridAdapter;
import a8.battleship.Logic.Variables;
import a8.battleship.Models.Board;
import a8.battleship.Models.Player;
import a8.battleship.R;

/**
 * This is the view where the player can place his/hers boats.
 */
public class SetShipView extends ActionBarActivity implements View.OnClickListener{

    private Button buttonStartGame, buttonRandomizeShips;
    private TextView tvHeader;

    //Need to know which Player is playing.
    Player player;

    GridView setShipGridView;

    /**
     * onCreate is called when the class is shown. Here we initialize all objects and references and set listeners to the widgets.
     * @param savedInstanceState If you save the state of the application in a bundle, it can be passed
     *                           back to onCreate if the activity needs to be recreated so that you don't
     *                           lose this prior information. If no data was supplied, savedInstanceState is null.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ship_view);

        initiateWidgets();
    }

    /**
     * A method that is called when a button is pushed.
     * @param v a reference to the clicked button.
     */
    public void onClick(View v){
        if(v.getId() == R.id.buttonDone){
            //If the game mode is one player, the ai player gets a new board.
            if (Variables.gameMode.equals("onePlayer")){
                Variables.playerAI.setBoard(new Board(Variables.boardSize));
                Variables.turn = "playerOne";
                startActivity(new Intent(SetShipView.this, BattleView.class));
            }
            //If the game mode is two player, each player set ships on their .
            else if(Variables.gameMode.equals("twoPlayer")){
                if(Variables.turn.equals("playerTwo")) {
                    Variables.turn = "playerOne";
                    startActivity(new Intent(SetShipView.this, SetShipView.class));
                }
                else {
                    startActivity(new Intent(SetShipView.this, BattleView.class));
                }
            }
        }
        //The randomize button returns a new randomized board.
        else if(v.getId() == R.id.buttonRandomize){
            if(Variables.turn.equals("playerOne")){
                Variables.playerOne.setBoard(new Board(Variables.boardSize));
                setShipGridView.setAdapter(new OwnBoardGridAdapter(this, player.getBoard()));
            }
            else{
                Variables.playerTwo.setBoard(new Board(Variables.boardSize));
                setShipGridView.setAdapter(new OwnBoardGridAdapter(this, player.getBoard()));
            }
        }
    }

    /**
     * Connecting with the XML-objects.
     */
    public void initiateWidgets(){

        tvHeader = (TextView) findViewById(R.id.switchViewHeader);

        buttonStartGame = (Button) findViewById(R.id.buttonDone);
        buttonRandomizeShips = (Button) findViewById(R.id.buttonRandomize);

        buttonStartGame.setOnClickListener(this);
        buttonRandomizeShips.setOnClickListener(this);

        setShipGridView = (GridView) findViewById(R.id.setShipGridView);

        setShipGridView.setNumColumns(Variables.boardSize);


        //Check who is playing, so we give the right parameter to the setAdapter-method.
        if (Variables.turn.equals("playerTwo")){
            tvHeader.setText("Place boats for " + Variables.playerTwo.getName());
            player = Variables.playerTwo;
        }
        else{
            tvHeader.setText("Place boats for " + Variables.playerOne.getName());
            player = Variables.playerOne;
        }
        setShipGridView.setAdapter(new OwnBoardGridAdapter(this, player.getBoard()));
    }
}
