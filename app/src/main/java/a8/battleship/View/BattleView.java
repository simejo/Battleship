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
 * This class implements all controlling elements used while playing the game.
 * It is the class that connects the board model with the graphics
 * This is the game/gridViewShootingBoard/battleview.
 * All the logic needed to show the gridViewShootingBoard on the screen
 */
public class BattleView extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {

    private GridView gridViewShootingBoard, gridViewOwnBoard;
    private TextView tvBattleTitle, tvScoreCounter;
    private Button buttonNextPlayer, buttonConfirmShot, buttonHome;
    private int currentXPosition, currentYPosition;
    private AiPlayer playerAI = Variables.playerAI;
    private boolean hasShot = false;
    private View selectedCell = null;
    private CheckBox checkBoxSound, checkBoxMusic;
    private AlertDialog.Builder alertDialogBuilder;
    private boolean hit;
    private Player player;

    /**
     * onCreate is called when the class is shown. Here we initialize all objects and references and set listeners to the widgets
     * @param savedInstanceState If you save the state of the application in a bundle, it can be passed
     *                           back to onCreate if the activity needs to be recreated so that you don't
     *                           lose this prior information. If no data was supplied, savedInstanceState is null.
     */

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //instantiates variables
        setContentView(R.layout.activity_battle_view);

        //instantiates boards
        gridViewShootingBoard = (GridView) findViewById(R.id.boardGridView);
        gridViewOwnBoard = (GridView) findViewById(R.id.gridViewOwnBoard);

        //instantiates text views
        tvBattleTitle = (TextView) findViewById(R.id.textBattleTitle);
        tvScoreCounter = (TextView) findViewById(R.id.textViewScoreCounter);

        //instantiates buttons
        buttonNextPlayer = (Button) findViewById(R.id.buttonNextPlayer);
        buttonConfirmShot = (Button) findViewById(R.id.buttonConfirmShot);
        buttonHome = (Button) findViewById(R.id.buttonHome);

        //instantiates checkboxes
        checkBoxMusic = (CheckBox) findViewById(R.id.cbMusic);
        checkBoxSound = (CheckBox) findViewById(R.id.cbSound);

        //sets initial visibility of buttons
        buttonNextPlayer.setVisibility(Button.INVISIBLE);
        buttonConfirmShot.setVisibility(Button.INVISIBLE);

        //sets initial value to variables
        gridViewShootingBoard.setNumColumns(Variables.boardSize);
        gridViewShootingBoard.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //The small gridView which shows your OWN map
        gridViewOwnBoard.setNumColumns(Variables.boardSize);
        gridViewOwnBoard.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        //Sound effect is created
        Variables.miss = MediaPlayer.create(this, R.raw.miss);


        //Check who is playing, so we give the right parameter to the setAdapter-method
        if (Variables.turn.equals("playerOne")){
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
        gridViewShootingBoard.setAdapter(Variables.shootingBoardGridAdapter = new ShootingBoardGridAdapter(this, Variables.opponent.getBoard()));
        gridViewOwnBoard.setAdapter(Variables.ownBoardGridAdapter = new OwnBoardGridAdapter(this, player.getBoard()));

        //adding click and change listeners
        gridViewShootingBoard.setOnItemClickListener(this);

        buttonNextPlayer.setOnClickListener(this);
        buttonConfirmShot.setOnClickListener(this);
        buttonHome.setOnClickListener(this);

        checkBoxMusic.setOnCheckedChangeListener(this);
        checkBoxSound.setOnCheckedChangeListener(this);

        //initial value of checkboxes
        checkBoxMusic.setChecked(Variables.cbBooleanMusic);
        checkBoxSound.setChecked(Variables.cbBooleanSound);

        //sets sound to correct volume based on checkboxes
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

    /**
     * The onClick method is called when one of its observers (buttons) are pushed.
     * @param v The source view which is pushed.
     */
    public void onClick(View v) {

        if(v.getId() == R.id.buttonConfirmShot){
            //once confirm shot button is clicked, this indicates the player has made a shot
            //hasShot prevents the user to shoot more than once
            hasShot = true;

            Board opponentBoard;

            //Need to get the opponents board to change the values
            if (Variables.turn.equals("playerOne")) {
                //checks if you are playing against AI or not, returns correct opponent's board
                if(Variables.gameMode.equals("onePlayer")){
                    opponentBoard = Variables.playerAI.getBoard();
                }
                else{
                    opponentBoard = Variables.playerTwo.getBoard();
                }
            } else {
                opponentBoard = Variables.playerOne.getBoard();
            }

            //returns correct underlying board value based on selected coordinate
            BoardValues value = opponentBoard.getValue(currentXPosition,currentYPosition);

            //Changes visibility of buttons
            buttonNextPlayer.setVisibility(Button.VISIBLE);
            buttonConfirmShot.setVisibility(Button.INVISIBLE);

            //runs correct action on selected coordinate
            doAction(value, opponentBoard, currentXPosition, currentYPosition);

            //sets adapter
            gridViewShootingBoard.setAdapter(Variables.shootingBoardGridAdapter = new ShootingBoardGridAdapter(this, opponentBoard));

            //if game is over, set winner and end game
            if(Functions.endGame(opponentBoard)){
                Variables.winner = player;
                Intent i = new Intent(BattleView.this, EndGameView.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }

        }
        //if button clicked is next player, this indicates current player's turn is over
        else if(v.getId() == R.id.buttonNextPlayer){
            //release of sound used in current turn to prevent memory out of bounds
            if(hit){
                Variables.hit.release();

            }
            else {
                Variables.miss.release();
            }

            //set correct player based on current player, if two player mode
            if(Variables.gameMode.equals("twoPlayer")){
                if(Variables.turn.equals("playerOne")){
                    Variables.turn = "playerTwo";
                }
                else{
                    Variables.turn = "playerOne";
                }

            }

            //if one player mode
            else if(Variables.gameMode.equals("onePlayer")){

                //if next player is AI
                if(Variables.turn.equals("playerOne")){

                    int nextMove, x, y;

                    //logic - AI MAKES A MOVE
                    Variables.turn = "playerAI";

                    //makes move based on difficulty chosen
                    if (playerAI.getLevel().equals("easy")){
                        nextMove = playerAI.aiNextMoveEasy();
                        x = Functions.findX(nextMove);
                        y = Functions.findY(nextMove);
                        BoardValues value = Variables.playerOne.getBoard().getContentInACell(x, y);
                        doAction(value, Variables.playerOne.getBoard(), x, y);
                    }
                    else if (playerAI.getLevel().equals("medium")){
                        nextMove = playerAI.aiNextMoveMedium();
                        x = Functions.findX(nextMove);
                        y = Functions.findY(nextMove);
                        BoardValues value = Variables.playerOne.getBoard().getContentInACell(x, y);
                        doAction(value, Variables.playerOne.getBoard(), x, y);

                    }
                    else if (playerAI.getLevel().equals("hard")){
                        nextMove = playerAI.aiNextMoveHard();
                        x = Functions.findX(nextMove);
                        y = Functions.findY(nextMove);
                        BoardValues value = Variables.playerOne.getBoard().getContentInACell(x, y);
                        doAction(value, Variables.playerOne.getBoard(), x, y);
                    }

                }

                //if playerOne is next player
                else{
                    Variables.turn = "playerOne";
                }
            }

            // set the new task and clear flags
            Intent i;
            if(Functions.endGame(Variables.playerOne.getBoard())){
                Variables.winner = playerAI;
                i = new Intent(BattleView.this, EndGameView.class);
            }
            else{
                i = new Intent(BattleView.this, SwitchView.class);
            }
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        //if home button is pressed, return to main menu
        else if(v.getId() == R.id.buttonHome){

            //pop up window alerting of leaving battle
            alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Back to main menu");
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Intent i = new Intent(BattleView.this, MainMenuView.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                }
            });
            alertDialogBuilder.setNegativeButton("Cancel", null);
            alertDialogBuilder.show();

        }
    }

    /**
     * This method is called when one of its observers (cells in the grid) are pushed or marked.
     * @param parent The parent of the adapterView
     * @param v The source view which is pushed (The cell).
     * @param position The position of the cell in the gridView.
     * @param id The id of the cell.
     */
    public void onItemClick(AdapterView parent, View v, int position, long id) {

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

    /**
     * Help method to onClick() - Performs the correct action, and changes the Board-model
     * @param value The BoardValue of the cell that is selected
     * @param opponentBoard The board the player is shooting at
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public void doAction(BoardValues value, Board opponentBoard, int x, int y){

        //sets correct text to be viewed in switch screen
        if(Variables.gameMode.equals("onePlayer")){
            Variables.stringStatus = Variables.playerAI.getName() + " hit one of your boats \n" + Variables.playerAI.getName() + " has score " + (Variables.playerAI.getScore() + 10);
        }
        else{
            Variables.stringStatus = player.getName() + " hit one of your boats \n " + player.getName() + " has score " + (player.getScore() + 10) ;
        }

        //hit = true;

        //if hit cell is east
        if (value == BoardValues.EAST){
            //initialize hit sound
            Variables.hit = MediaPlayer.create(this, R.raw.hit);
            Variables.hit.start();
            opponentBoard.changeBoardValue(x,y, BoardValues.EAST_HIT);
            Functions.findAndUpdateShip(x,y, Variables.opponent);        //Will update partsLeft in the correct ship (hopefully)
            addPoints();
        }
        else if (value == BoardValues.SOUTH){

            Variables.hit = MediaPlayer.create(this, R.raw.hit);
            Variables.hit.start();
            opponentBoard.changeBoardValue(x, y, BoardValues.SOUTH_HIT);
            Functions.findAndUpdateShip(x,y, Variables.opponent);
            addPoints();
        }
        else if (value == BoardValues.WEST){

            Variables.hit = MediaPlayer.create(this, R.raw.hit);
            Variables.hit.start();
            opponentBoard.changeBoardValue(x, y, BoardValues.WEST_HIT);
            Functions.findAndUpdateShip(x,y, Variables.opponent);
            addPoints();
        }
        else if (value == BoardValues.NORTH){

            Variables.hit = MediaPlayer.create(this, R.raw.hit);
            Variables.hit.start();
            opponentBoard.changeBoardValue(x, y, BoardValues.NORTH_HIT);
            Functions.findAndUpdateShip(x,y, Variables.opponent);
            addPoints();
        }
        else if (value == BoardValues.MIDDLE_HORIZONTAL){

            Variables.hit = MediaPlayer.create(this, R.raw.hit);
            Variables.hit.start();
            opponentBoard.changeBoardValue(x, y, BoardValues.MIDDLE_HORIZONTAL_HIT);
            Functions.findAndUpdateShip(x,y, Variables.opponent);
            addPoints();
        }
        else if (value == BoardValues.MIDDLE_VERTICAL){
            Variables.hit = MediaPlayer.create(this, R.raw.hit);
            Variables.hit.start();
            opponentBoard.changeBoardValue(x, y, BoardValues.MIDDLE_VERTICAL_HIT);
            Functions.findAndUpdateShip(x,y, Variables.opponent);
            addPoints();
        }
        //if shot is a miss
        else if (value == BoardValues.EMPTY){

            //run miss sound
            Variables.miss = MediaPlayer.create(this, R.raw.miss);
            Variables.miss.start();
            //hit=false;
            //change board value to missed
            opponentBoard.changeBoardValue(x,y,BoardValues.MISSED);
            //decrease points
            removePoints();
            //change text string to be viewed in switch screen
            Variables.stringStatus = player.getName() + " missed";

            if(Variables.gameMode.equals("onePlayer")){
                Variables.stringStatus = Variables.playerAI.getName() + " missed\n" + Variables.playerAI.getName() + " has score " + Variables.playerAI.getScore();
            }
            else{
                Variables.stringStatus = player.getName() + " missed\n" + player.getName() + " has score " + player.getScore();
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
    }

    /**
     * Help method to add points of the player if he hit
     */
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

    /**
     * Help method to remove points of the player if he missed
     */
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

    /**
     * Checks if the checkboxes for the background music and the sounds is checked or not.
     * @param buttonView The source view which is pushed
     * @param isChecked A boolean that indicates if the pushed checkBox became checked or not
     */
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
