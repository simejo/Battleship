package a8.battleship.Logic;

import android.util.Log;

import java.util.ArrayList;

import a8.battleship.Models.Board;
import a8.battleship.Models.Player;
import a8.battleship.Tokens.Ship;

/**
 * This class contains essential methods that is useful for one or several classes.
 */
public class Functions {

    //TODO: FINISH THIS, OR REMOVE?????
    /**
     * findAndUpdateShip(int x, int y, Player opponent) takes a coordinate (x,y) and Player.
     * This method is recursive until it finds the WEST-part or the NORTH-part of a boat
     * and then it will decrease parts left. This is useful when we only want to show the boat when
     * the whole boat has been shot. In that case - partsLeft will be zero.
     */
    public static void findAndUpdateShip(int x, int y, Player opponent){
        BoardValues value = opponent.getBoard().getContentInACell(x,y);
        if (value == BoardValues.EAST | value == BoardValues.EAST_HIT){
            findAndUpdateShip(x - 1, y,  opponent);
        } else if (value == BoardValues.MIDDLE_HORIZONTAL | value == BoardValues.MIDDLE_HORIZONTAL_HIT){
            findAndUpdateShip(x - 1, y, opponent);
        }
        else if (value == BoardValues.MIDDLE_VERTICAL | value == BoardValues.MIDDLE_VERTICAL_HIT){
            findAndUpdateShip(x, y - 1, opponent);
        }
        else if (value == BoardValues.SOUTH | value == BoardValues.SOUTH_HIT){
            findAndUpdateShip(x, y - 1, opponent);
        }
        else if (value == BoardValues.NORTH | value == BoardValues.NORTH_HIT){
            //Gets the shipList to the opponent
            ArrayList<Ship> shipsList = opponent.getBoard().getShipArray();
            for (int i = 0; i < shipsList.size(); i++){
                //Checking the ship
                Ship ship = shipsList.get(i);
                if (x == ship.getX() && y == ship.getY()){
                    ship.decreasePartsLeft(x,y, opponent.getBoard());
                }
            }
        }
        else if (value == BoardValues.WEST | value == BoardValues.WEST_HIT ){
            //Gets the shipList to the opponent
            Log.i("FUNCTIONS", "IN the START");
            ArrayList<Ship> shipsList = opponent.getBoard().getShipArray();
            Log.i("FUNCTIONS", "Shiplist: " + shipsList.toString());
            for (int i = 0; i < shipsList.size(); i++){
                //Checking the ship
                Ship ship = shipsList.get(i);
                if (x == ship.getX() && y == ship.getY()){
                    Log.i("FUNCTIONS", "BEFORE DECREASE");
                    //DECREASING PARTS LEFT
                    ship.decreasePartsLeft(x,y,opponent.getBoard());
                }
            }
        }
    }

    /**
     * endGame(Board board) checks if a player has won the game.
     * In that case there will be no WEST, EAST, NORTH, SOUTH, MIDDLE_HORIZONTAL or MIDDLE_VERTICAL,
     * only DESTROYED, MISSED and EMPTY parts will be on the board.
     * @param board The board to the current player, to check if the player who has this board has won or not.
     * @return true if the player has won, false if not
     */
    public static boolean endGame(Board board){
        for (ArrayList<BoardValues> rows : board.getBoard()){
            for(BoardValues cell : rows){
                if(cell == BoardValues.WEST || cell == BoardValues.EAST || cell == BoardValues.NORTH || cell == BoardValues.SOUTH
                        || cell == BoardValues.MIDDLE_HORIZONTAL || cell == BoardValues.MIDDLE_VERTICAL){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * findY(int position) finds the y position which depends on the given position in the gridView.
     * @param position is the position in the gridView.
     * @return int position. This will be the correct y coordinate.
     */
    public static int findY(int position){
        return position/ Variables.boardSize;
    }

    /**
     * findX(int position)finds the x position which depends on the given position in the gridView.
     * @param position is the position in the gridView.
     * @return int position. This will be the correct x coordinate.
     */
    public static int findX(int position){
        return position% Variables.boardSize;
    }

    /**
     * findPos(int x, int y) returns the given position to be used in the gridView.
     * @param x is the x position in the board.
     * @param y is the y position in the board.
     * @return int position for the position to be used in the gridView.
     */
    public static int findPos(int x, int y){
        return y* Variables.boardSize + x;
    }
}
