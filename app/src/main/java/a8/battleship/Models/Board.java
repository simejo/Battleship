package a8.battleship.Models;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Logic.Constants;
import a8.battleship.Tokens.Ship;

/**
 * Created by majakirkerod on 11.03.15.
 */
public class Board{
    private int boardsize;
    private boolean valid = false;
    private BoardValues EMPTY = BoardValues.EMPTY;
    private BoardValues NORTH = BoardValues.NORTH;
    private BoardValues SOUTH = BoardValues.SOUTH;
    private BoardValues WEST = BoardValues.WEST;
    private BoardValues EAST = BoardValues.EAST;
    private BoardValues MIDDLE = BoardValues.MIDDLE;

    private ArrayList<ArrayList<BoardValues>> board;

    private BoardValues[][] boardArrayTest;

    private ArrayList<BoardValues[][]> boardArray;

    //Making 2 standard boards
    public BoardValues[][] board1 = new BoardValues[][]{
            {NORTH,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {MIDDLE,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {SOUTH,EMPTY,WEST,MIDDLE,MIDDLE,MIDDLE,EAST,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,NORTH,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,MIDDLE,EMPTY,EMPTY,WEST,MIDDLE,EAST},
            {EMPTY,EMPTY,EMPTY,EMPTY,MIDDLE,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,SOUTH,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,WEST,MIDDLE,MIDDLE,EAST,EMPTY,EMPTY}};
    public BoardValues[][] board2 = new BoardValues[][]{
            {EMPTY,EMPTY,NORTH,NORTH,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,MIDDLE,MIDDLE,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,MIDDLE,SOUTH,EMPTY,EMPTY,WEST,MIDDLE,MIDDLE,EAST},
            {EMPTY,EMPTY,SOUTH,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,NORTH},
            {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,MIDDLE},
            {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,MIDDLE},
            {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,MIDDLE},
            {EMPTY,EMPTY,EMPTY,EMPTY,NORTH,EMPTY,EMPTY,EMPTY,EMPTY,SOUTH},
            {EMPTY,EMPTY,EMPTY,EMPTY,MIDDLE,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,SOUTH,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY}};

    //Makes the board initially empty
    public Board(int boardsize){
        //boardArray  = new ArrayList<BoardValues[][]>(Arrays.asList(board1, board2));
        this.boardsize = boardsize;

        //this.boardArrayTest = board2;
        board = new ArrayList<ArrayList<BoardValues>>();
        for(int i = 0; i < boardsize; i++){
            ArrayList<BoardValues> row = new ArrayList<BoardValues>();
            for(int j = 0; j < boardsize; j++){
                row.add(EMPTY);
            }
            board.add(row);
        }
        randomizeShipPositions();//place ships onto empty board*/
    }

    private BoardValues[][] getBoardArrayTest(){
        return boardArrayTest;
    }

    private Random random = new Random();

    private ArrayList<Ship> shipArray = new ArrayList<Ship>(Arrays.asList(
            new Ship(3,random.nextInt(2)),new Ship(3,random.nextInt(2)),
            new Ship(4,random.nextInt(2)),new Ship(4,random.nextInt(2)),
            new Ship(5,random.nextInt(2))));

    //Getter
    public ArrayList<ArrayList<BoardValues>> getBoard(){
        return board;
    }

    //Change value in the board
    public void changeBoardValue(int y, int x, BoardValues value){ // remember range for y and x is 0 to n-1 not 1 to n
        ArrayList<BoardValues> tempRow = board.get(y);
        tempRow.set(x, value);
        board.set(y, tempRow);
    }

    public void placeShip(Ship ship, int y, int x){//only send in coordinates that are valid for said boat
        if( ship.getDirection()==0){//vertical
            for (int i=y; i<ship.getBoat().size(); i++){//goes through all rows that need changing
                ArrayList<BoardValues> tempRow = board.get(i);//makes duplicate of current row
                tempRow.set(x, ship.getBoat().get(i-y));//replaces piece with corresponding piece in ship
                board.set(i, tempRow);//places temp row back in board
            }
        }
        else if(ship.getDirection()==1) {//horizontal
            ArrayList<BoardValues> tempRow = board.get(y);// makes a copy of the row you want to work on
            for (int i=x; i<ship.getBoat().size()+x; i++){//goes through the temporary row, and then replaces the pieces where you want to place the boat
               tempRow.set(i, ship.getBoat().get(i-x));//changes enumvalues in temporary row, to fit boat enumvalues
            }
            board.set(y, tempRow); //replaces row in board with temporary row.
        }
    }

    public void randomizeShipPositions(){
        for(int i = 0; i < shipArray.size(); i++){//traverse through the boats you are going to place
            while(!valid){//while boat not placed
                //make a random x value between the boardsize minus the length of the boat and zero
                int x = random.nextInt((boardsize - shipArray.get(i).getBoat().size()));//random place where it is okay to place boat on empty board
                int y = random.nextInt((boardsize - shipArray.get(i).getBoat().size()));

                if(shipArray.get(i).getDirection() == 0){//if vertical
                    int counter = 0; //counter that increases if one of coordinates is filled
                    for(int j = y; j< y+shipArray.get(i).getBoat().size(); j++){//check if all coordinates are empty
                        ArrayList<BoardValues> tempRow = board.get(j);//retrieve row that you are checking
                        if(tempRow.get(x)!=EMPTY){//check if empty
                            counter++;//if not, increase counter
                        }
                    }
                    if(counter==0){//if counter not increased, place boat
                        placeShip(shipArray.get(i), y, x);
                        valid=true;//exit while loop if boat is placed. 
                    }
                }
                else if(shipArray.get(i).getDirection() == 1){//if horizontal
                    int counter = 0;//counter
                    ArrayList<BoardValues> tempRow = board.get(y);//create temporary row to check
                    for(int j = x; j<x+shipArray.get(i).getBoat().size(); j++){//traverse through x coordinates
                        if(tempRow.get(j)!=EMPTY){//check if empty
                            counter++;//if not, increase counter
                        }
                    }
                    if(counter==0){//if counter not increased
                        placeShip(shipArray.get(i), y, x);//place ship
                        valid=true;//boat placed, exit while loop
                    }
                }

            }
            valid=false;//reset valid before continuing on for loop
        }
    }

    public int getLength(){
        return boardsize * boardsize - 10;
    }

    public BoardValues getContentInACell(int x, int y){
        return board.get(y).get(x);
    }

    public String toString(){
        String holder = "";
        for(int i =0;i<10;i++ ){
            for(int j = 0; j < 10; j++){
                holder += board.get(i).get(j).toString() + " ";
            }
            holder += "\n";
        }
        return holder;
    }

}
