package a8.battleship.View;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;

import a8.battleship.R;


/**
 * Created by Kartefull on 11.03.2015.
 * This is the game/board/battleview.
 * All the logic needed to show the board on the screen
 */
public class BattleView extends ActionBarActivity{

    GridView board;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_view);

        board = (GridView) findViewById(R.id.boardGridView);
    }
}
