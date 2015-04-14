package a8.battleship.View;

        import android.content.Context;
        import android.media.AudioManager;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.content.Intent;
        import android.support.v7.app.ActionBarActivity;
        import a8.battleship.Logic.Constants;
        import a8.battleship.R;

public class IntroView extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_view);

        //Initiate the sounds and music for the rest of the game
        Constants.hit = MediaPlayer.create(this, R.raw.hit);
        Constants.miss = MediaPlayer.create(this, R.raw.miss);
        Constants.backgroundMusic = MediaPlayer.create(this, R.raw.music);

        Constants.amSound = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Constants.amSound.setStreamVolume(AudioManager.STREAM_MUSIC, Constants.amSound.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        Constants.amMusic = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Constants.amMusic.setStreamVolume(AudioManager.STREAM_MUSIC, Constants.amMusic.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        Constants.backgroundMusic.start();
        Constants.backgroundMusic.setLooping(true);

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
