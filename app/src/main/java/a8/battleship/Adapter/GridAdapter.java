package a8.battleship.Adapter;

/**
 * Created by siljechristensen on 17.03.15.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Logic.Constants;
import a8.battleship.Models.Board;
import a8.battleship.R;
import a8.battleship.Tokens.Ship;


//The adapter is  a description of how the cell will look like (in this case in the gridView)


public class GridAdapter extends BaseAdapter {
    private Context context;
    //private final ArrayList<Ship> ships;
    private Board board;
    private String[] test;


    //TODO: Need to decide if we should have BoardValues[][] or ArrayList<ArrayList<BoardValues>>
    //Pros/cons in the agenda document, we are using arrayList

    //The constructor
    public GridAdapter(Context context, Board board) {
        this.context = context;
        this.board = board;
        this.test = test;
    }

    //What to show, with the parameters so we can change the layout based on the different parameters
    //Position is the number in the array based on what cell you clicked

    /*Can we give this method a parameter which is a board/player, and a coordinate?
    So we can get the value in that cell? And then just call the
    cell.setImageResource(R.drawable.XXXXXXX);
    where XXXXXXX depends on what value the board has at a given coordinate?
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            gridView = inflater.inflate(R.layout.grid_adapter_layout, null); //A reference to the adapter xml file

            ImageView cell = (ImageView) gridView.findViewById(R.id.cell);

            cell.setAdjustViewBounds(true); //Added this to remove the extra space between the cells in the grid

            //TODO: implement the board so we can choose the correct pictures to the right enums
            /*  TODO: * Connect a cell to a coordinate in a specific board
                (need to use a reference to a board, because we might have two boards during a game)
            */

            //converting position to coordinates
            int x,y;

            if(position < 10){
                y = 0;
                x=position;
            }
            else{
                y=(int) position/Constants.boardSize;
                x= position % Constants.boardSize;

            }

            //Getting the BoardValue depending on the coordinates

            BoardValues cellView = board.getContentInACell(x,y);

            //Converting BoardValues to images

            if (cellView.equals(BoardValues.EAST)) {
                cell.setImageResource(R.drawable.east);
            } else if (cellView.equals(BoardValues.EAST_DESTROYED)) {
                cell.setImageResource(R.drawable.eastdestroyed);
            } else if (cellView.equals(BoardValues.MIDDLE)) {
                cell.setImageResource(R.drawable.middle);
            } else if (cellView.equals(BoardValues.MIDDLE_DESTROYED)) {
                cell.setImageResource(R.drawable.middledestroyed);
            } else if (cellView.equals(BoardValues.NORTH)) {
                cell.setImageResource(R.drawable.north);
            } else if (cellView.equals(BoardValues.NORTH_DESTROYED)) {
                cell.setImageResource(R.drawable.northdestroyed);
            } else if (cellView.equals(BoardValues.SOUTH)){
                cell.setImageResource(R.drawable.south);
            } else if (cellView.equals(BoardValues.SOUTH_DESTROYED)) {
                cell.setImageResource(R.drawable.southdestroyed);
            } else if (cellView.equals(BoardValues.WEST)) {
                cell.setImageResource(R.drawable.west);
            } else if (cellView.equals(BoardValues.WEST_DESTROYED)) {
                cell.setImageResource(R.drawable.westdestroyed);
            } else {
                cell.setImageResource(R.drawable.empty);
            }

        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }

    //Below, there are basic methods to make the adapter work

    @Override
    public int getCount() {
        return board.getLength(); //ships.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}