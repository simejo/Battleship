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
public class EndGameView extends ActionBarActivity implements View.OnClickListener{

    private Button buttonMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_view);

        buttonMainMenu = (Button)findViewById(R.id.buttonMainMenu);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.buttonMainMenu){
            startActivity(new Intent(EndGameView.this, MainMenuView.class));
        }
    }
}
