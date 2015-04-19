package a8.battleship.Models;

import java.util.ArrayList;
import a8.battleship.Logic.BoardValues;

//Class to instanciate ships

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

    /**
     * Constructor of the ship
     * @param shipSize The size of the ship
     * @param direction The direction of the ship. 0 = Vertical, 1 = Horizontal
     */
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

    /**
     * Sets the position of the ship
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public void setShipPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * Returns ship's x WEST/NORTH coordinate
     */
    public int getX(){
        return this.x;
    }

    /**
     * Returns ship's y WEST/NORTH coordinate
     */
    public int getY(){
        return this.y;
    }


    /**
     * Decreasing partsLeft - e.g when the ship is hit
     */
    public void decreasePartsLeft(int x, int y, Board board){
        this.partsLeft--;
        if(this.partsLeft == 0){
            SetShipSunken(x,y, board);
        }
    }

    /**
     * Returns the entire ship
     */
    public ArrayList<BoardValues> getShip(){
        return this.ship;
    }

    /**
     * Returns direction of ship
     */
    public int getDirection(){
        return this.direction;
    }

    /**
     * A method that change BoardValues from HIT to DESTROYD, which means that the
     * entire destroyed ship becomes visible for the player
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param board The corresponding board
     */
    private void SetShipSunken(int x, int y, Board board){

        //adding pictures to array if vertical
        if(this.ship.get(0) ==  BoardValues.NORTH){
            board.changeBoardValue(x,y,BoardValues.NORTH_DESTROYED);
            int i = 1;
            while (this.ship.get(i) != BoardValues.SOUTH){
                y++;
                board.changeBoardValue(x,y,BoardValues.MIDDLE_VERTICAL_DESTROYED);
                i++;
            }
            y++;
            board.changeBoardValue(x,y,BoardValues.SOUTH_DESTROYED);
        }
        //adding pictures to array if horizontal
        else if(this.ship.get(0) ==  BoardValues.WEST){//if horizontal add west image
            board.changeBoardValue(x,y,BoardValues.WEST_DESTROYED);
            int i = 1;
            while (this.ship.get(i) != BoardValues.EAST){
                x ++;
                board.changeBoardValue(x,y,BoardValues.MIDDLE_HORIZONTAL_DESTROYED);
                i++;
            }
            x++;
            board.changeBoardValue(x,y,BoardValues.EAST_DESTROYED);
        }

    }


}
