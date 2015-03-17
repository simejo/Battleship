package a8.battleship.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import a8.battleship.Logic.BoardValues;
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

    private Random random = new Random();
    //private a8.battleship.tokens.Ship ship1 = new a8.battleship.tokens.Ship(3,random.nextInt((1 - 0) + 1 ) + 0);
    //private a8.battleship.tokens.Ship ship2 = new a8.battleship.tokens.Ship(3,random.nextInt((1 - 0) + 1 ) + 0);
    //private a8.battleship.tokens.Ship ship3 = new a8.battleship.tokens.Ship(4,random.nextInt((1 - 0) + 1 ) + 0);
    //private a8.battleship.tokens.Ship ship4 = new a8.battleship.tokens.Ship(4,random.nextInt((1 - 0) + 1 ) + 0);
    //private a8.battleship.tokens.Ship ship5 = new a8.battleship.tokens.Ship(5,random.nextInt((1 - 0) + 1 ) + 0);

    //Makes the board initially empty
    public Board(int boardsize){
        boardArray  = new ArrayList<BoardValues[][]>(Arrays.asList(board1, board2));
        this.boardsize = boardsize;
        board = new ArrayList<ArrayList<BoardValues>>();
        for(int i = 0; i < boardsize; i++){
            ArrayList<BoardValues> row = new ArrayList<BoardValues>();
            for(int j = 0; j < boardsize; j++){
                row.set(j, EMPTY);
            }
            board.set(i, row);
        }
    }

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



    /*
        for(int i = 0; i < shipList.size(); i++){
            while(!valid){
                shipList[i].boat

            }
            random.nextInt((boardsize) + 1);
        }
        */
    }

}
