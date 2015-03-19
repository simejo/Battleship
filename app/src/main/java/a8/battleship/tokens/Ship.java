package a8.battleship.Tokens;

import android.app.Activity;

import java.util.ArrayList;

import a8.battleship.Logic.BoardValues;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class Ship extends Activity{
    private ArrayList<BoardValues> ship;


    /*  Need to save where the boats will be located at the board.
    *   x and y is the top end/left end at the boat
    *   These values will be set during the
    *   public void randomizeShipPositions() method in Board.java
    */
    private int x;
    private int y;
    private int direction;
    private int positionInArray; //Will be added later, in the Board.java class. Makes it easier to find the ship (?)

    /*  Need to control if the boat has sunk or not. If partsLeft == 0, the boat has sunk
     *  TODO: this should be updated in BattleView.java
     *
     */
    private int partsLeft;

    public Ship(int shipSize, int direction){//direction, 0 equals vertical, 1 equals horizontal
        ship = new ArrayList<BoardValues>();
        this.direction=direction;
        this.x = 0;
        this.y = 0;
        this.partsLeft = shipSize;
        this.positionInArray = 0;

        if(direction==0){//adding pictures to array if vertical
            ship.add(BoardValues.NORTH);
            for(int i=1; i<shipSize-2; i++){//add middle pieces
                ship.add(BoardValues.MIDDLE_VERTICAL);
            }
        }
        else{//if horizontal add west image
            ship.add(BoardValues.WEST);
            for(int i=1; i<shipSize-2; i++){//add middle pieces
                ship.add(BoardValues.MIDDLE_HORIZONTAL);
            }
        }
        if(direction==0){//add end piece
            ship.add(BoardValues.SOUTH);
        }
        else{
            ship.add(BoardValues.EAST);
        }
    }

    public void setShipPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    //the position in - private ArrayList<Ship> shipArray in Board.java
    public int getPositionInArray(){
        return this.positionInArray;
    }

    //the position in - private ArrayList<Ship> shipArray in Board.java
    public void setPositionInArray(int i){
        this.positionInArray = i;
    }

    //NOT IN USE!!!!!!!! need to fix it if we use it
    //Placement is the position to the destroyed part in the boat - NOT IN USE, not updated with the two different MIDDLE_HORIZONTAL/VERTICAL
    public void destroyPiece(int placement){
        if (ship.get(placement)==BoardValues.NORTH){
            ship.set(placement, BoardValues.NORTH_DESTROYED);
        }
        else if(ship.get(placement) == BoardValues.SOUTH){
            ship.set(placement, BoardValues.SOUTH_DESTROYED);
        }
        else if(ship.get(placement) == BoardValues.WEST){
            ship.set(placement, BoardValues.WEST_DESTROYED);
        }
        else if(ship.get(placement) == BoardValues.EAST){
            ship.set(placement, BoardValues.EAST_DESTROYED);
        }
        else{
            ship.set(placement, BoardValues.MIDDLE_DESTROYED);
        }

    }

    public ArrayList<BoardValues> getShip(){

        return this.ship;

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
