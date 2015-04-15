package a8.battleship.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Tokens.Ship;

/**
 * This class holds the board and places 5 ships with length: 3,3,4,4,5 , with random vertical or horizontal direction,
 * random on the board. The class also holds a method which can change the cell values in the board.
 */
public class Board{

    private int boardSize;

    //boolean used in randomizeShipPosition to track whether or not the boat position placement is valid or not
    private boolean valid = false;
    private BoardValues EMPTY = BoardValues.EMPTY;
    private ArrayList<ArrayList<BoardValues>> board;

    private Random random = new Random();

    //A list that holds the ship which are going to be placed with random direction
    private ArrayList<Ship> shipArray = new ArrayList<>(Arrays.asList(
            new Ship(3,random.nextInt(2)),new Ship(3,random.nextInt(2)),
            new Ship(4,random.nextInt(2)),new Ship(4,random.nextInt(2)),
            new Ship(5,random.nextInt(2))));

    /**
     * The constructor of the class. In addition to initializing the board size it also
     * creates board with randomized ship positions
     * @param boardSize Size of the board
     */
    public Board(int boardSize){
        this.boardSize = boardSize;

        board = new ArrayList<>();
        for(int i = 0; i < boardSize; i++){
            ArrayList<BoardValues> row = new ArrayList<>();
            for(int j = 0; j < boardSize; j++){
                row.add(EMPTY);
            }
            board.add(row);
        }
        randomizeShipPositions();
    }



    //Getter for the board
    public ArrayList<ArrayList<BoardValues>> getBoard(){
        return board;
    }

    //Getter for a specific value at the board, given x and y coordinates
    public BoardValues getValue(int x, int y){
        ArrayList<BoardValues> row = board.get(y);
        return row.get(x);
    }

    public ArrayList<Ship> getShipArray(){
        return shipArray;
    }

    //Change value in the board
    public void changeBoardValue(int x, int y, BoardValues value){
        ArrayList<BoardValues> tempRow = board.get(y);
        tempRow.set(x, value);
        board.set(y, tempRow);
    }

    /**
     * Helping method
     * Actually replaces the empty spaces with the ships
     * @param ship A ship with fixed length and direction(vertical or horizontal)
     * @param x Columns
     * @param y Rows
     */
    public void placeShip(Ship ship, int x, int y){
        //vertical direction
        if( ship.getDirection()==0){
            //Traverses all rows that needs to be changed, and replaces the ship with current pieces
            for (int i = y; i<ship.getShip().size() + y; i++){
                ArrayList<BoardValues> tempRow = board.get(i);
                tempRow.set(x, ship.getShip().get(i - y));
                board.set(i, tempRow);
            }
        }
        //horizontal direction
        else if(ship.getDirection()==1) {
            //Traverses all columns that needs to be changed, and replaces the ship with current pieces
            ArrayList<BoardValues> tempRow = board.get(y);
            for (int i = x; i<ship.getShip().size() + x; i++){
               tempRow.set(i, ship.getShip().get(i - x));
            }
            board.set(y, tempRow);
        }
    }

    /**
     * Helping Method
     * randomizeShipPositions places the ships random onto the board. It uses placeShip, another helping method in this class
     */
    private void randomizeShipPositions(){
        for(int i = 0; i < shipArray.size(); i++){
            int x = 0;
            int y = 0;
            while(!valid){

                //Make a random x and y value between the boardSize minus the
                //length of the boat and zero to avoid edges
                x = random.nextInt((boardSize - shipArray.get(i).getShip().size()) + 1);
                y = random.nextInt((boardSize - shipArray.get(i).getShip().size()) + 1);

                //Vertical direction
                if(shipArray.get(i).getDirection() == 0){
                    //A counter that tracks how many of the wished places are not empty
                    int counter = 0;
                    //Traverse through wished places and checks if they are empty
                    for(int j = y; j < y + shipArray.get(i).getShip().size(); j++){
                        ArrayList<BoardValues> tempRow = board.get(j);
                        if(tempRow.get(x)!=EMPTY){
                            counter++;
                        }
                    }
                    //Calls placeShip which actually places the boat onto the board if counter is 0
                    if(counter == 0){
                        placeShip(shipArray.get(i), x, y);
                        valid=true;
                    }
                }
                //Horizontal direction
                //Does the same as in vertical direction, but it changes rows instead of columns
                else if(shipArray.get(i).getDirection() == 1){
                    int counter = 0;
                    ArrayList<BoardValues> tempRow = board.get(y);
                    for(int j = x; j < x + shipArray.get(i).getShip().size(); j++){
                        if(tempRow.get(j)!=EMPTY){
                            counter++;
                        }
                    }
                    if(counter==0){
                        placeShip(shipArray.get(i), x, y);
                        valid=true;
                    }
                }

            }
            valid=false;
            shipArray.get(i).setShipPosition(x,y);
        }
    }

    public int getLength(){
        return boardSize * boardSize;
    }

    //Get value in the board
    public BoardValues getContentInACell(int x, int y){
        return board.get(y).get(x);
    }

    public String toString(){
        String holder = "\n";
        for(int i =0;i<10;i++ ){
            for(int j = 0; j < 10; j++){
                holder += fixedLengthString(board.get(i).get(j).toString(), 25);
            }
            holder += "\n";
        }
        return holder;
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

}
