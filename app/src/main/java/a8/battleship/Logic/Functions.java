package a8.battleship.Logic;

/**
 * Created by majakirkerod on 11.03.15.
 */
public class Functions {

    /* This method is used when we need to find the position to a ship
     * If we hit a south-part/middle_vertical-part, we need to get to the north-part
     * If we hit a east-part/middle_horizontal-part, we need to get to the west-part
     * -- and then find the ship based on the x and y coordinate, to update the partsLeft variable to the ship
     *
     */
    /*public int[] findShipPosition(int x, int y, BoardValues value){
        if (value == BoardValues.EAST){
            findShipPosition(x-1, y, value);
        } else if (value == BoardValues.MIDDLE_HORIZONTAL){
            findShipPosition(x-1, y, value);
        }
        else if (value == BoardValues.MIDDLE_VERTICAL){
            findShipPosition(x, y-1, value);
        }
        else if (value == BoardValues.SOUTH | value == BoardValues.SOUTH_DESTROYED){
            findShipPosition(x, y-1, value);
        }
        else if (value == BoardValues.NORTH )



    }*/
}
