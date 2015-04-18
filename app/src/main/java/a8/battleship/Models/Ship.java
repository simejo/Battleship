package a8.battleship.Models;
//Class to instanciate ships
import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Models.Board;


public class Ship{
    //arraylist will contain enumvalues of the ship, from top to bottom, or from left to right
    private ArrayList<BoardValues> ship;
    //ex North, middle, middle, south

    //X coordinate of either topmost, or leftmost piece of ship. Coordinates are used in setShipView.java
    private int x;
    //Y coordinate of either topmost or leftmost piece of ship
    private int y;
    //if the direction is north to south(symbolized by 0), or west to east(1)
    private int direction;
    private int shipSize;
    //indicates how many ship pieces there are left that aren't hit
    private int partsLeft;

    //Constructor of a ship, it takes in what length the ship is going to have, and what direction it is supposed to be in
    public Ship(int shipSize, int direction){
        ship = new ArrayList<>();
        this.direction=direction;
        //standard value for ship placement
        this.x = 0;
        this.y = 0;
        this.shipSize = shipSize;
        this.partsLeft = shipSize - 1;

        //parts left are the same as shipsize
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

    public int getShipSize(){
        return this.shipSize;
    }

    //Sets the ship's position
    public void setShipPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    //returns ship's x WEST/NORTH coordinate
    public int getX(){
        return this.x;
    }
    //returns ship's y WEST/NORTH coordinate
    public int getY(){
        return this.y;
    }


    //Decreasing partsLeft - e.g when the ship is hit
    public void decreasePartsLeft(int x, int y, Board board){
        this.partsLeft--;
        Log.i("PARTS LEFT", "PARTS LEFT AFTER" + partsLeft);
        if(this.partsLeft == 0){
            SetShipSunken(x,y, board);
        }
    }

    //returns entire ship
    public ArrayList<BoardValues> getShip(){
        return this.ship;
    }

    //returns direction of ship
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
