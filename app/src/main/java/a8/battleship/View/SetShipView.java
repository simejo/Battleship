package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class SetShipView extends ActionBarActivity implements View.OnClickListener{

    //Where to place the boats

    private Button buttonStartGame;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ship_view);

        buttonStartGame = (Button) findViewById(R.id.buttonDone);
        buttonStartGame.setOnClickListener(this);

    }

    public void onClick(View v){
        if(v.getId() == R.id.buttonDone){
            startActivity(new Intent(SetShipView.this, BattleView.class));
        }
    }
}
