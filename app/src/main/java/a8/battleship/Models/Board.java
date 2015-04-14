package a8.battleship.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Tokens.Ship;

public class Board{

    private int boardSize;
    private boolean valid = false;
    private BoardValues EMPTY = BoardValues.EMPTY;
    private ArrayList<ArrayList<BoardValues>> board;

    private Random random = new Random();
    private ArrayList<Ship> shipArray = new ArrayList<>(Arrays.asList(
            new Ship(3,random.nextInt(2)),new Ship(3,random.nextInt(2)),
            new Ship(4,random.nextInt(2)),new Ship(4,random.nextInt(2)),
            new Ship(5,random.nextInt(2))));

    //Makes the board initially empty
    public Board(int boardSize){
        //boardArray  = new ArrayList<BoardValues[][]>(Arrays.asList(board1, board2));
        this.boardSize = boardSize;

        //this.boardArrayTest = board2;
        board = new ArrayList<>();
        for(int i = 0; i < boardSize; i++){
            ArrayList<BoardValues> row = new ArrayList<>();
            for(int j = 0; j < boardSize; j++){
                row.add(EMPTY);
            }
            board.add(row);
        }
        randomizeShipPositions();//place ships onto empty board*/
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
    //Getter for the shipArray
    public ArrayList<Ship> getShipArray(){
        return shipArray;
    }

    //Change value in the board
    public void changeBoardValue(int x, int y, BoardValues value){ // remember range for y and x is 0 to n-1 not 1 to n
        ArrayList<BoardValues> tempRow = board.get(y);
        tempRow.set(x, value);
        board.set(y, tempRow);
    }

    public void placeShip(Ship ship, int x, int y){//only send in coordinates that are valid for said boat
        if( ship.getDirection()==0){//vertical
            for (int i=y; i<ship.getShip().size()+y; i++){//goes through all rows that need changing
                ArrayList<BoardValues> tempRow = board.get(i);//makes duplicate of current row
                tempRow.set(x, ship.getShip().get(i-y));//replaces piece with corresponding piece in ship
                board.set(i, tempRow);//places temp row back in board
            }
        }
        else if(ship.getDirection()==1) {//horizontal
            ArrayList<BoardValues> tempRow = board.get(y);// makes a copy of the row you want to work on
            for (int i=x; i<ship.getShip().size()+x; i++){//goes through the temporary row, and then replaces the pieces where you want to place the boat
               tempRow.set(i, ship.getShip().get(i-x));//changes enum values in temporary row, to fit boat enum values
            }
            board.set(y, tempRow); //replaces row in board with temporary row.
        }
    }

    public void randomizeShipPositions(){
        for(int i = 0; i < shipArray.size(); i++){//traverse through the boats you are going to place
            int x = 0;
            int y = 0;
            while(!valid){//while boat not placed
                //make a random x value between the boardSize minus the length of the boat and zero
                x = random.nextInt((boardSize - shipArray.get(i).getShip().size()) + 1);//random place where it is okay to place boat on empty board
                y = random.nextInt((boardSize - shipArray.get(i).getShip().size()) + 1);

                if(shipArray.get(i).getDirection() == 0){//if vertical
                    int counter = 0; //counter that increases if one of coordinates is filled
                    for(int j = y; j< y+shipArray.get(i).getShip().size(); j++){//check if all coordinates are empty
                        ArrayList<BoardValues> tempRow = board.get(j);//retrieve row that you are checking
                        if(tempRow.get(x)!=EMPTY){//check if empty
                            counter++;//if not, increase counter
                        }
                    }
                    if(counter==0){//if counter not increased, place boat
                        placeShip(shipArray.get(i), x, y);
                        valid=true;//exit while loop if boat is placed. 
                    }
                }
                else if(shipArray.get(i).getDirection() == 1){//if horizontal
                    int counter = 0;//counter
                    ArrayList<BoardValues> tempRow = board.get(y);//create temporary row to check
                    for(int j = x; j<x+shipArray.get(i).getShip().size(); j++){//traverse through x coordinates
                        if(tempRow.get(j)!=EMPTY){//check if empty
                            counter++;//if not, increase counter
                        }
                    }
                    if(counter==0){//if counter not increased
                        placeShip(shipArray.get(i), x, y);//place ship
                        valid=true;//boat placed, exit while loop
                    }
                }

            }
            valid=false;//reset valid before continuing on for loop
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
