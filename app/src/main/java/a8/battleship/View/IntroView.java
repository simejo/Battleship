package a8.battleship.View;

/**
 * Created by simen on 14.04.15.
 */


        import android.os.Bundle;
        import android.app.Activity;
        import android.content.Intent;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.LinearLayout;

        import a8.battleship.R;

public class IntroView extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_view);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    startActivity(new Intent(IntroView.this, MainMenuView.class));
                }
            }
        };
        timer.start();
    }

}
