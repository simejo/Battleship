package a8.battleship.View;

        import android.content.Context;
        import android.media.AudioManager;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.content.Intent;
        import android.support.v7.app.ActionBarActivity;
        import a8.battleship.Logic.Variables;
        import a8.battleship.R;

/**
 * This class shows the logo in 2 seconds when the game starts.
 */
public class IntroView extends ActionBarActivity {
    /**
     * In this method is the IntroView created and the music is initialized
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_view);

        //Initialize the sounds and music for the rest of the game

        Variables.backgroundMusic = MediaPlayer.create(this, R.raw.music);


        Variables.amSound = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Variables.amSound.setStreamVolume(AudioManager.STREAM_MUSIC, Variables.amSound.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        Variables.amMusic = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Variables.amMusic.setStreamVolume(AudioManager.STREAM_MUSIC, Variables.amMusic.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        Variables.backgroundMusic.start();
        Variables.backgroundMusic.setLooping(true);

        //Timer to start MainMenuView after 2 seconds
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
