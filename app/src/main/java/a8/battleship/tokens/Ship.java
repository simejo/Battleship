package a8.battleship.Tokens;

import android.app.Activity;
import java.util.ArrayList;
import a8.battleship.Logic.BoardValues;


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

    private int partsLeft;

    public Ship(int shipSize, int direction){//direction, 0 equals vertical, 1 equals horizontal
        ship = new ArrayList<>();
        this.direction=direction;
        this.x = 0;
        this.y = 0;
        this.partsLeft = shipSize;

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

    public void decreasePartsLeft(){ //Decreasing partsLeft - e.g when the ship is hit
        this.partsLeft--;
    }


    public ArrayList<BoardValues> getShip(){
        return this.ship;
    }

    public int getDirection(){
        return this.direction;

    }


}
