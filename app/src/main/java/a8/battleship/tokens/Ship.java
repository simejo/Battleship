package a8.battleship.Tokens;

import android.app.Activity;
import android.widget.ImageView;
import android.view.View;

import java.util.ArrayList;

import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class Ship extends Activity{

    private ImageView southImage;
    private ImageView southDestroyedImage;
    private ImageView northImage;
    private ImageView northDestroyedImage;
    private ImageView eastImage;
    private ImageView eastDestroyedImage;
    private ImageView westImage;
    private ImageView westDestroyedImage;
    private ImageView middleImage;
    private ImageView middleDestroyedImage;
    private ArrayList<ImageView> boat;

    public Ship(int shipSize, int direction){//direction, 0 equals vertical, 1 equals horizontal
        boat = new ArrayList<ImageView>();
        this.southImage = (ImageView) findViewById(R.id.south);
        this.southDestroyedImage = (ImageView) findViewById(R.id.southDestroyed);
        this.northImage = (ImageView) findViewById(R.id.north);
        this.northDestroyedImage = (ImageView) findViewById(R.id.northDestroyed);
        this.eastImage = (ImageView) findViewById(R.id.east);
        this.eastDestroyedImage = (ImageView) findViewById(R.id.eastDestroyed);
        this.westImage = (ImageView) findViewById(R.id.west);
        this.westDestroyedImage = (ImageView) findViewById(R.id.westDestroyed);
        this.middleImage = (ImageView) findViewById(R.id.middle);
        this.middleDestroyedImage = (ImageView) findViewById(R.id.middleDestroyed);
        if(direction==0){//adding pictures to array if vertical
            boat.add(northImage);
        }
        else{//if horizontal add west image
            boat.add(westImage);
        }
        for(int i=1; i<shipSize-2; i++){//add middlepieces
            boat.add(middleImage);
        }
        if(direction==0){//add endpiece
            boat.add(southImage);
        }
        else{
            boat.add(eastImage);
        }

    }

    public void drawShip(){

    }


}
