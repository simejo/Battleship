package a8.battleship.Tokens;

import android.app.Activity;
import android.widget.ImageView;
import android.view.View;

import java.util.ArrayList;

import a8.battleship.Logic.BoardValues;
import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class Ship extends Activity{
    private ArrayList<BoardValues> boat;
    private int direction;

    public Ship(int shipSize, int direction){//direction, 0 equals vertical, 1 equals horizontal
        boat = new ArrayList<BoardValues>();
        this.direction=direction;
        if(direction==0){//adding pictures to array if vertical
            boat.add(BoardValues.NORTH);
        }
        else{//if horizontal add west image
            boat.add(BoardValues.WEST);
        }
        for(int i=1; i<shipSize-2; i++){//add middlepieces
            boat.add(BoardValues.MIDDLE);
        }
        if(direction==0){//add endpiece
            boat.add(BoardValues.SOUTH);
        }
        else{
            boat.add(BoardValues.EAST);
        }

    }

    public void destroyPiece(int placement){
        if (boat.get(placement)==BoardValues.NORTH){
            boat.set(placement, BoardValues.NORTH_DESTROYED);
        }
        else if(boat.get(placement) == BoardValues.SOUTH){
            boat.set(placement, BoardValues.SOUTH_DESTROYED);
        }
        else if(boat.get(placement) == BoardValues.WEST){
            boat.set(placement, BoardValues.WEST_DESTROYED);
        }
        else if(boat.get(placement) == BoardValues.EAST){
            boat.set(placement, BoardValues.EAST_DESTROYED);
        }
        else{
            boat.set(placement, BoardValues.MIDDLE_DESTROYED);
        }

    }

    public ArrayList<BoardValues> getBoat(){
        return this.boat;
    }

    public int getDirection(){
        return this.direction;
    }

    /*
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

    this.southImage = (ImageView) findViewById(R.id.south);
    this.southDestroyedImage = (ImageView) findViewById(R.id.southdestroyed);
    this.northImage = (ImageView) findViewById(R.id.north);
    this.northDestroyedImage = (ImageView) findViewById(R.id.northdestroyed);
    this.eastImage = (ImageView) findViewById(R.id.east);
    this.eastDestroyedImage = (ImageView) findViewById(R.id.eastdestroyed);
    this.westImage = (ImageView) findViewById(R.id.west);
    this.westDestroyedImage = (ImageView) findViewById(R.id.westdestroyed);
    this.middleImage = (ImageView) findViewById(R.id.middle);
    this.middleDestroyedImage = (ImageView) findViewById(R.id.middledestroyed);*/




}
