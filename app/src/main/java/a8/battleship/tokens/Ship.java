package a8.battleship.Tokens;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Models.Board;


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
    private int shipSize;
    private int partsLeft;

    public Ship(int shipSize, int direction){//direction, 0 equals vertical, 1 equals horizontal
        ship = new ArrayList<>();
        this.direction=direction;
        this.x = 0;
        this.y = 0;
        this.shipSize = shipSize;
        this.partsLeft = shipSize - 1;

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

    public int getShipSize(){
        return this.shipSize;
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

    public void decreasePartsLeft(int x, int y, Board board){ //Decreasing partsLeft - e.g when the ship is hit
        Log.i("PARTS LEFT", "PARTS LEFT BEFORE" + partsLeft);
        this.partsLeft--;
        Log.i("PARTS LEFT", "PARTS LEFT AFTER" + partsLeft);
        if(this.partsLeft == 0){
            SetShipSunken(x,y, board);
        }
    }


    public ArrayList<BoardValues> getShip(){
        return this.ship;
    }

    public int getDirection(){
        return this.direction;

    }

    private void SetShipSunken(int x, int y, Board board){

        if(this.ship.get(0) ==  BoardValues.NORTH){//adding pictures to array if vertical
            board.changeBoardValue(x,y,BoardValues.NORTH_DESTROYED);
            int i = 1;
            while (this.ship.get(i) != BoardValues.SOUTH){
                y++;
                board.changeBoardValue(x,y,BoardValues.MIDDLE_VERTICAL_DESTROYED);
                i++;
                Log.i("SHIP", "i after: " + i + " ship size: " + getShipSize() + this.ship.get(i).toString());
            }
            y++;
            board.changeBoardValue(x,y,BoardValues.SOUTH_DESTROYED);

        }
        else if(this.ship.get(0) ==  BoardValues.WEST){//if horizontal add west image
            board.changeBoardValue(x,y,BoardValues.WEST_DESTROYED);
            int i = 1;
            while (this.ship.get(i) != BoardValues.EAST){
                x ++;
                board.changeBoardValue(x,y,BoardValues.MIDDLE_HORIZONTAL_DESTROYED);
                i++;
                Log.i("SHIP", "i after: " + i + " ship size: " + getShipSize() + this.ship.get(i).toString());
            }
            x++;
            board.changeBoardValue(x,y,BoardValues.EAST_DESTROYED);
        }

    }


}
