package a8.battleship.Models;

import java.util.ArrayList;

import a8.battleship.Logic.BoardValues;
import a8.battleship.Tokens.Ship;

/**
 * Created by majakirkerod on 11.03.15.
 */
public class Board {

    private ArrayList<ArrayList<BoardValues>> board;

    //Makes the board initially empty
    public Board(int boardsize){
        board = new ArrayList<ArrayList<BoardValues>>();
        for(int i = 0; i < boardsize; i++){
            ArrayList<BoardValues> row = new ArrayList<BoardValues>();
            for(int j = 0; j < boardsize; j++){
                row.set(j, BoardValues.EMPTY);
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
}
