package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

import a8.battleship.Logic.Constants;
import a8.battleship.Models.Player;
import a8.battleship.R;
import android.widget.TextView;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class SwitchView extends ActionBarActivity implements View.OnClickListener{

    private Button buttonSwitchPlayer;
    private TextView tvPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_view);

        buttonSwitchPlayer = (Button)findViewById(R.id.buttonSwitchPlayer);

        buttonSwitchPlayer.setOnClickListener(this);
    }
    public void onClick(View v){

        //Functionality for button
        if(v.getId() == R.id.buttonSwitchPlayer){
            if(Constants.turn){
                tvPlayer.setText("Player Ones Turn");
                startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            else{
                tvPlayer.setText("Player Twos Turn");
                startActivity(new Intent(SwitchView.this, BattleView.class));
            }

        }



    }
}
