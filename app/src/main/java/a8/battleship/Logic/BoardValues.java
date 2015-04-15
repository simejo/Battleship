package a8.battleship.Logic;

/**
 * Enum class which holds the possible board values
 * We differ between the MIDDLE_VERTICAL and MIDDLE_HORIZONTAL to make the logic easier
 */
public enum BoardValues {
    EMPTY,
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTH_DESTROYED,
    SOUTH_DESTROYED,
    EAST_DESTROYED,
    WEST_DESTROYED,
    MISSED,
    MIDDLE_VERTICAL,
    MIDDLE_HORIZONTAL,
    MIDDLE_VERTICAL_DESTROYED,
    MIDDLE_HORIZONTAL_DESTROYED

}
