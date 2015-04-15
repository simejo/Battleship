package a8.battleship.Tokens;
//Class to instanciate ships
import android.app.Activity;
import java.util.ArrayList;
import a8.battleship.Logic.BoardValues;


public class Ship extends Activity{
    //arraylist will contain enumvalues of the ship, from top to bottom, or from left to right
    private ArrayList<BoardValues> ship;
    //ex North, middle, middle, south

    //X coordinate of either topmost, or leftmost piece of ship. Coordinates are used in setShipView.java
    private int x;
    //Y coordinate of either topmost or leftmost piece of ship
    private int y;
    //if the direction is north to south(symbolized by 0), or west to east(1)
    private int direction;

    private int partsLeft; //TODO: implement function that hides what part of a boat is shot until entire ship is sunk
    //indicates how many ship pieces there are left that aren't hit

    //Constructor of a ship, it takes in what length the ship is going to have, and what direction it is supposed to be in
    public Ship(int shipSize, int direction){
        ship = new ArrayList<>();
        this.direction=direction;
        //standard value for ship placement
        this.x = 0;
        this.y = 0;
        //parts left are the same as shipsize
        this.partsLeft = shipSize;
        //TODO: partsleft
        //adding pictures to array if vertical
        if(direction==0){
            ship.add(BoardValues.NORTH);
            //add middle pieces
            for(int i=1; i<shipSize-2; i++){
                ship.add(BoardValues.MIDDLE_VERTICAL);
            }
            ship.add(BoardValues.SOUTH);
        }
        //if horizontal add west and east image
        else{
            ship.add(BoardValues.WEST);
            //add middle pieces
            for(int i=1; i<shipSize-2; i++){
                ship.add(BoardValues.MIDDLE_HORIZONTAL);
            }
            ship.add(BoardValues.EAST);
        }

    }

    //Sets the ship's position
    public void setShipPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    //returns ship's x coordinate
    public int getX(){
        return this.x;
    }
    //returns ship's y coordinate
    public int getY(){
        return this.y;
    }

    //Decreasing partsLeft - e.g when the ship is hit
    public void decreasePartsLeft(){ //TODO: implement partsleft
        this.partsLeft--;
    }

    //returns entire ship
    public ArrayList<BoardValues> getShip(){
        return this.ship;
    }

    //returns direction of ship
    public int getDirection(){
        return this.direction;

    }


}
