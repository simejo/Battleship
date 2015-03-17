package a8.battleship.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class StartUpView extends ActionBarActivity implements View.OnClickListener{

    //Where to place the boats

    private Button buttonStartGame;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);

        buttonStartGame = (Button) findViewById(R.id.buttonDone);

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.buttonDone){
            startActivity(new Intent(StartUpView.this, BattleView.class));
        }
    }
}
