package a8.battleship.Adapter;

/**
 * Created by simen on 09.04.15.
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
import a8.battleship.Logic.Functions;
import a8.battleship.Models.Board;
import a8.battleship.R;
import a8.battleship.Tokens.Ship;


//The adapter is  a description of how the cell will look like (in this case in the gridView)


public class OwnBoardGridAdapter extends BaseAdapter {
    private Context context;
    private Board board;
    private int x, y;


    //The constructor
    public OwnBoardGridAdapter(Context context, Board board) {
        this.context = context;
        this.board = board;
    }

    //What to show, with the parameters so we can change the layout based on the different parameters
    //Position is the number in the array based on what cell you clicked

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            gridView = inflater.inflate(R.layout.grid_adapter_layout, null); //A reference to the adapter xml file

            ImageView cell = (ImageView) gridView.findViewById(R.id.cell);

            cell.setAdjustViewBounds(true); //Added this to remove the extra space between the cells in the grid


            //converting position to coordinates

            x = Functions.findX(position, Constants.boardSize);
            y = Functions.findY(position, Constants.boardSize);

            //Getting the BoardValue depending on the coordinates

            BoardValues cellView = board.getContentInACell(x,y);

            //Converting BoardValues to images

            if(Constants.gameLayout.equals("boats")){

                if (cellView.equals(BoardValues.EAST)) {
                    cell.setImageResource(R.drawable.east);
                } else if (cellView.equals(BoardValues.WEST)) {
                    cell.setImageResource(R.drawable.west);
                } else if (cellView.equals(BoardValues.MIDDLE_HORIZONTAL)) {
                    cell.setImageResource(R.drawable.middle);
                } else if (cellView.equals(BoardValues.MIDDLE_VERTICAL)) {
                    cell.setImageResource(R.drawable.middle);
                } else if (cellView.equals(BoardValues.NORTH)) {
                    cell.setImageResource(R.drawable.north);
                } else if (cellView.equals(BoardValues.SOUTH)){
                    cell.setImageResource(R.drawable.south);
                } else if (cellView.equals(BoardValues.SOUTH_DESTROYED)) {
                    cell.setImageResource(R.drawable.southdestroyed);
                } else if (cellView.equals(BoardValues.EAST_DESTROYED)) {
                    cell.setImageResource(R.drawable.eastdestroyed);
                } else if (cellView.equals(BoardValues.WEST_DESTROYED)) {
                    cell.setImageResource(R.drawable.westdestroyed);
                } else if (cellView.equals(BoardValues.MIDDLE_DESTROYED)) {
                    cell.setImageResource(R.drawable.middledestroyed);
                } else if (cellView.equals(BoardValues.MIDDLE_HORIZONTAL_DESTROYED)) {
                    cell.setImageResource(R.drawable.middledestroyed);
                } else if (cellView.equals(BoardValues.MIDDLE_VERTICAL_DESTROYED)) {
                    cell.setImageResource(R.drawable.middledestroyed);
                } else if (cellView.equals(BoardValues.NORTH_DESTROYED)) {
                    cell.setImageResource(R.drawable.northdestroyed);
                } else if(cellView.equals(BoardValues.MISSED)){
                    cell.setImageResource(R.drawable.missedshot);
                } else {
                    cell.setImageResource(R.drawable.empty);
                }

            } else if(Constants.gameLayout.equals("girls")){
                if (cellView.equals(BoardValues.EAST)) {
                    cell.setImageResource(R.drawable.eastpink);
                } else if (cellView.equals(BoardValues.WEST)) {
                    cell.setImageResource(R.drawable.westpink);
                } else if (cellView.equals(BoardValues.MIDDLE_HORIZONTAL)) {
                    cell.setImageResource(R.drawable.middlepinkhorizontal);
                } else if (cellView.equals(BoardValues.MIDDLE_VERTICAL)) {
                    cell.setImageResource(R.drawable.middlepinkvertical);
                } else if (cellView.equals(BoardValues.NORTH)) {
                    cell.setImageResource(R.drawable.northpink);
                } else if (cellView.equals(BoardValues.SOUTH)){
                    cell.setImageResource(R.drawable.southpink);
                } else if (cellView.equals(BoardValues.SOUTH_DESTROYED)) {
                    cell.setImageResource(R.drawable.southpinkdestroyed);
                } else if (cellView.equals(BoardValues.EAST_DESTROYED)) {
                    cell.setImageResource(R.drawable.eastpinkdestroyed);
                } else if (cellView.equals(BoardValues.WEST_DESTROYED)) {
                    cell.setImageResource(R.drawable.westpinkdestroyed);
                } else if (cellView.equals(BoardValues.MIDDLE_DESTROYED)) {
                    cell.setImageResource(R.drawable.middlepinkverticaldestroyed);
                } else if (cellView.equals(BoardValues.MIDDLE_HORIZONTAL_DESTROYED)) {
                    cell.setImageResource(R.drawable.middlepinkhorizontaldestroyed);
                } else if (cellView.equals(BoardValues.MIDDLE_VERTICAL_DESTROYED)) {
                    cell.setImageResource(R.drawable.middlepinkverticaldestroyed);
                } else if (cellView.equals(BoardValues.NORTH_DESTROYED)) {
                    cell.setImageResource(R.drawable.northpinkdestroyed);
                } else if(cellView.equals(BoardValues.MISSED)){
                    cell.setImageResource(R.drawable.emptypinkmissed);
                } else {
                    cell.setImageResource(R.drawable.emptypink);
                }
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
