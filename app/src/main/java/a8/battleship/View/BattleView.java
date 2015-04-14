package a8.battleship.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import a8.battleship.Adapter.ShootingBoardGridAdapter;
import a8.battleship.Adapter.OwnBoardGridAdapter;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Logic.Variables;
import a8.battleship.Logic.Functions;
import a8.battleship.Models.AiPlayer;
import a8.battleship.Models.Board;
import a8.battleship.Models.Player;
import a8.battleship.R;


/**
 * This is the game/boardGridView/battleview.
 * All the logic needed to show the boardGridView on the screen
 */
public class BattleView extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {

    private GridView boardGridView, gridViewOwnBoard;
    private TextView tvBattleTitle, tvScoreCounter;
    private Button buttonNextPlayer, buttonConfirmShot, buttonHome;
    private int currentXPosition, currentYPosition;
    private AiPlayer playerAI = Variables.playerAI;
    private boolean hasShot = false;
    private View selectedCell = null;
    private CheckBox checkBoxSound, checkBoxMusic;
    private AlertDialog.Builder alertDialogBuilder;
    private boolean hit;

    //Need to know which Player is playing
    private Player player;

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

        boardGridView.setNumColumns(Variables.boardSize);
        boardGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //The small gridView which shows your OWN map
        gridViewOwnBoard.setNumColumns(Variables.boardSize);
        gridViewOwnBoard.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //Sound effects are created
        Variables.hit = MediaPlayer.create(this, R.raw.hit);
        Variables.miss = MediaPlayer.create(this, R.raw.miss);


        //Check who is playing, so we give the right parameter to the setAdapter-method
        Log.i("BattleView", "before if Constants.turn.equals(playerOne)");
        if (Variables.turn.equals("playerOne")){
            Log.i("BattleView", "after if Constants.turn.equals(playerOne)");

            if(Variables.gameMode.equals("onePlayer")){
               Variables.opponent = Variables.playerAI;
            }
            else{
                Variables.opponent = Variables.playerTwo;
            }
            player = Variables.playerOne;
            tvBattleTitle.setText(player.getName() + "'s turn to battle");

        }
        else{
            player = Variables.playerTwo;
            Variables.opponent = Variables.playerOne;
            tvBattleTitle.setText(Variables.playerTwo.getName() + "'s turn to battle");

        }

        //Connecting the grids with the adapter
        boardGridView.setAdapter(new ShootingBoardGridAdapter(this, Variables.opponent.getBoard()));
        gridViewOwnBoard.setAdapter(new OwnBoardGridAdapter(this, player.getBoard()));


        //Which means that the following lines must be rewritten a bit
        boardGridView.setOnItemClickListener(this);

        buttonNextPlayer.setOnClickListener(this);
        buttonConfirmShot.setOnClickListener(this);
        buttonHome.setOnClickListener(this);

        checkBoxMusic.setOnCheckedChangeListener(this);
        checkBoxSound.setOnCheckedChangeListener(this);

        checkBoxMusic.setChecked(Variables.cbBooleanMusic);
        checkBoxSound.setChecked(Variables.cbBooleanSound);

        if(checkBoxMusic.isChecked()){
            Variables.cbBooleanMusic = true;
            Variables.amMusic.setStreamMute(AudioManager.STREAM_MUSIC, false);
        }
        else{
            Variables.cbBooleanMusic = false;
            Variables.amMusic.setStreamMute(AudioManager.STREAM_MUSIC, true);
        }

        tvScoreCounter.setText("Score: " + player.getScore());

    }


    public void onClick(View v) {

        if(v.getId() == R.id.buttonConfirmShot){
            //This prevents the user to shot more than once
            hasShot = true;
            //Log.i(className, "onClick: buttonConfirmShot was clicked");


            Board opponentBoard;
            //Need to get the opponents board to change the values
            if (Variables.turn.equals("playerOne")) {
                if(Variables.gameMode.equals("onePlayer")){
                    opponentBoard = Variables.playerAI.getBoard();
                }
                else{
                    opponentBoard = Variables.playerTwo.getBoard();
                }
            } else {
                opponentBoard = Variables.playerOne.getBoard();
            }
            BoardValues value = opponentBoard.getValue(currentXPosition,currentYPosition);
            buttonNextPlayer.setVisibility(Button.VISIBLE);
            buttonConfirmShot.setVisibility(Button.INVISIBLE);
            doAction(value, opponentBoard, currentXPosition, currentYPosition);

            boardGridView.setAdapter(new ShootingBoardGridAdapter(this, opponentBoard));

            if(Functions.endGame(opponentBoard)){
                Variables.winner = player;
                startActivity(new Intent(BattleView.this, EndGameView.class));
            }

        }
        else if(v.getId() == R.id.buttonNextPlayer){
            //release of sound garbage disposal
            if(hit){
                Log.i("BattleView.java", "HIT RELEASE");
                Variables.miss.start();
                Variables.miss.release();
                Variables.hit.release();
            }
            else {
                Log.i("BattleView.java", "MISS RELEASE");
                Variables.hit.start();
                Variables.hit.release();
                Variables.miss.release();
            }

            if(Variables.gameMode.equals("twoPlayer")){
                if(Variables.turn.equals("playerOne")){
                    Variables.turn = "playerTwo";
                }
                else{
                    Variables.turn = "playerOne";
                }
                Log.i("Battleview", "\n playerOnes board \n " + Variables.playerOne.getBoard().toString() + "\n playerTwos board " + Variables.playerTwo.getBoard().toString());

            }
            else if(Variables.gameMode.equals("onePlayer")){
                if(Variables.turn.equals("playerOne")){
                Log.i("BattleView","turn is " + Variables.turn);
                    //logic - AI MAKES A MOVE
                    Variables.turn = "playerAI";
                    if (playerAI.getLevel().equals("low")){
                        int nextMove = playerAI.aiNextMoveEasy();
                        int x = Functions.findX(nextMove);
                        int y = Functions.findY(nextMove);
                        BoardValues value = Variables.playerOne.getBoard().getContentInACell(x, y);
                        doAction(value, Variables.playerOne.getBoard(), x, y);
                    }
                    else if (playerAI.getLevel().equals("medium")){
                        int nextMove = playerAI.aiNextMoveMedium();
                        int x = Functions.findX(nextMove);
                        int y = Functions.findY(nextMove);
                        BoardValues value = Variables.playerOne.getBoard().getContentInACell(x, y);
                        doAction(value, Variables.playerOne.getBoard(), x, y);
                        Log.i("BattleView","MEDIUM AI");

                    }
                    else if (playerAI.getLevel().equals("hard")){
                        int nextMove = playerAI.aiNextMoveHard();
                        int x = Functions.findX(nextMove);
                        int y = Functions.findY(nextMove);
                        BoardValues value = Variables.playerOne.getBoard().getContentInACell(x, y);
                        doAction(value, Variables.playerOne.getBoard(), x, y);
                    }
                }
                else{
                    Variables.turn = "playerOne";
                }

            }
            startActivity(new Intent(BattleView.this, SwitchView.class));
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
    public void onItemClick(AdapterView parent, View v, int position, long id) {

        /*Board opponentBoard;*/
        if(!(selectedCell == null)){
            selectedCell.setAlpha(1);
        }


        //Checks whether the player has shot or not
        if(!hasShot){
            selectedCell = v;
            selectedCell.setAlpha(0.5f);
            buttonConfirmShot.setVisibility(Button.VISIBLE);
        }
        //Finding y
        currentYPosition = Functions.findY(position);
        //Finding x
        currentXPosition = Functions.findX(position);

    }

    //Help method to onItemClick() - Performs the correct action, and changes the Board-model
    public void doAction(BoardValues value, Board opponentBoard, int x, int y){

        if(Variables.gameMode.equals("onePlayer")){
            Variables.stringStatus = Variables.playerAI.getName() + " hit one of your boats \n" + Variables.playerAI.getName() + " has score " + (Variables.playerAI.getScore() + 10);
        }
        else{
            Variables.stringStatus = player.getName() + " hit one of your boats \n " + player.getName() + " has score " + (player.getScore() + 10) ;
        }

        if (value == BoardValues.EAST){

            Variables.hit.start();
            hit=true;
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y, Variables.opponent);        //Will update partsLeft in the correct ship (hopefully)
            opponentBoard.changeBoardValue(x,y, BoardValues.EAST_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.SOUTH){

            Variables.hit.start();
            hit=true;
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y, Variables.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.SOUTH_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.WEST){

            Variables.hit.start();
            hit=true;
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y, Variables.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.WEST_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.NORTH){

            Variables.hit.start();
            hit=true;
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y, Variables.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.NORTH_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.MIDDLE_HORIZONTAL){

            Variables.hit.start();
            hit=true;
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y, Variables.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.MIDDLE_HORIZONTAL_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.MIDDLE_VERTICAL){

            Variables.hit.start();
            hit=true;
            //Log.i(className, "hit noise" );
            Functions.findAndUpdateShip(x,y, Variables.opponent);
            opponentBoard.changeBoardValue(x, y, BoardValues.MIDDLE_VERTICAL_DESTROYED);
            addPoints();
        }
        else if (value == BoardValues.EMPTY){

            Variables.miss.start();
            hit=false;
            opponentBoard.changeBoardValue(x,y,BoardValues.MISSED);
            removePoints();
            Variables.stringStatus = player.getName() + " missed";
            if(Variables.gameMode.equals("onePlayer")){
                Variables.stringStatus = Variables.playerAI.getName() + " missed\n" + Variables.playerAI.getName() + " has score " + Variables.playerAI.getScore();
            }
            else{
                Variables.stringStatus = player.getName() + " missed\n" + Variables.opponent.getName() + " has score " + Variables.opponent.getScore();
            }
        }
        //Checks if it was a valid shot
        else{
            buttonNextPlayer.setVisibility(Button.INVISIBLE);
            buttonConfirmShot.setVisibility(Button.VISIBLE);
            hasShot = false;
            Toast popupBox = Toast.makeText(getApplicationContext(), "You have already shot there!", Toast.LENGTH_SHORT);
            popupBox.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            popupBox.show();

        }
        tvScoreCounter.setText("Score: " + player.getScore());
        //Log.i(className, "Inside doAction()");
    }

    //Help method - to increment/decrement the score to the correct player.
    public void addPoints(){
        if(Variables.gameMode.equals("twoPlayer")){
            player.incrementScore();
        }
        else{
            if(Variables.turn.equals("playerOne")){
                player.incrementScore();
            }
            else{
                playerAI.incrementScore();
            }
        }
    }
    public void removePoints(){
        if(Variables.gameMode.equals("twoPlayer")){
            player.decrementScore();
        }
        else{
            if(Variables.turn.equals("playerOne")){
                player.decrementScore();
            }
            else{
                playerAI.decrementScore();
            }
        }
    }

    //Method which turns the sound and music on/off
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        if (buttonView.getId() == R.id.cbSound) {
            Variables.cbBooleanSound = isChecked;
            Variables.amSound.setStreamMute(AudioManager.STREAM_MUSIC, !isChecked);
        }
        else if(buttonView.getId() == R.id.cbMusic){
            Variables.cbBooleanMusic = isChecked;
            Variables.amMusic.setStreamMute(AudioManager.STREAM_MUSIC, !isChecked);
        }

    }

}
