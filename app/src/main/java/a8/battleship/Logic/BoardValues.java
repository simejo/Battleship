package a8.battleship.Logic;

/**
 * Enum class which holds the possible board values
 * We differ between the MIDDLE_VERTICAL and MIDDLE_HORIZONTAL to make the logic easier
 */
public enum BoardValues {
    //Simple enums to tell where the ship is placed (and not)
    NORTH,
    SOUTH,
    EAST,
    WEST,
    MIDDLE_VERTICAL,
    MIDDLE_HORIZONTAL,

    //DESTROY means that the ship is fully sunken
    NORTH_DESTROYED,
    SOUTH_DESTROYED,
    EAST_DESTROYED,
    WEST_DESTROYED,
    MIDDLE_VERTICAL_DESTROYED,
    MIDDLE_HORIZONTAL_DESTROYED,

    //HIT is used when the ship is not fully sunken
    NORTH_HIT,
    SOUTH_HIT,
    EAST_HIT,
    WEST_HIT,
    MIDDLE_VERTICAL_HIT,
    MIDDLE_HORIZONTAL_HIT,

    //EMPTY or MISSED placed in the board (no ships)
    EMPTY,
    MISSED,
}
