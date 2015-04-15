package a8.battleship.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Logic.Variables;
import a8.battleship.Logic.Functions;
import a8.battleship.Models.Board;
import a8.battleship.R;

//The class is an adapter that is used in the gridView that shows the map with boats. The adapter is a description of how the cell will look like in the gridView.
public class OwnBoardGridAdapter extends BaseAdapter {
    private Context context;
    private Board board;
    private int x, y;

    /**
     * The adapter constructor. This is the creator of each reference to the adapter.
     * @param context The context from
     * @param board The board is the model to be set pictures to.
     */
    public OwnBoardGridAdapter(Context context, Board board) {
        this.context = context;
        this.board = board;
    }


    /**
     * This is the most important method in the adapter. Here, we decides how each cell should look like
     * @param position The number of which cell that is viewed.
     * @param convertView The old view to reuse, if possible. Note: We must check that this view is non-null
     *                    and of an appropriate type before using. If it is not possible to convert this view
     *                    to display the correct data, this method can create a new view.
     * @param parent The parent that this view will eventually be attached to Returns
     * @return A View corresponding to the data at the specified position.
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        //Checks whether the view is already created, or we need to create a new one.
        if (convertView == null) {

            //References to xml files
            gridView = inflater.inflate(R.layout.grid_adapter_layout, null);
            ImageView cell = (ImageView) gridView.findViewById(R.id.cell);

            //Removes the extra space between the cells in the grid
            cell.setAdjustViewBounds(true);

            //converting position to coordinates
            x = Functions.findX(position);
            y = Functions.findY(position);

            //Getting the BoardValue depending on the coordinates
            BoardValues cellView = board.getContentInACell(x,y);

            //Converting BoardValues to images
            switch(Variables.gameLayout){
                case "boats":
                    switch(cellView){
                        case EAST: cell.setImageResource(R.drawable.east);break;
                        case WEST: cell.setImageResource(R.drawable.west);break;
                        case NORTH: cell.setImageResource(R.drawable.north);break;
                        case SOUTH: cell.setImageResource(R.drawable.south);break;
                        case MIDDLE_HORIZONTAL: cell.setImageResource(R.drawable.middle);break;
                        case MIDDLE_VERTICAL: cell.setImageResource(R.drawable.middle);break;
                        case SOUTH_DESTROYED: cell.setImageResource(R.drawable.southdestroyed);break;
                        case EAST_DESTROYED: cell.setImageResource(R.drawable.eastdestroyed); break;
                        case WEST_DESTROYED: cell.setImageResource(R.drawable.westdestroyed); break;
                        case MIDDLE_HORIZONTAL_DESTROYED: cell.setImageResource(R.drawable.middledestroyed); break;
                        case MIDDLE_VERTICAL_DESTROYED: cell.setImageResource(R.drawable.middledestroyed); break;
                        case NORTH_DESTROYED: cell.setImageResource(R.drawable.northdestroyed); break;
                        case MISSED: cell.setImageResource(R.drawable.missedshot); break;
                        default: cell.setImageResource(R.drawable.empty); break;
                    }
                    break;
                case "girls":
                    switch(cellView){
                        case EAST: cell.setImageResource(R.drawable.eastpink);break;
                        case WEST: cell.setImageResource(R.drawable.westpink);break;
                        case NORTH: cell.setImageResource(R.drawable.northpink);break;
                        case SOUTH: cell.setImageResource(R.drawable.southpink);break;
                        case MIDDLE_HORIZONTAL: cell.setImageResource(R.drawable.middlepinkhorizontal);break;
                        case MIDDLE_VERTICAL: cell.setImageResource(R.drawable.middlepinkvertical);break;
                        case SOUTH_DESTROYED: cell.setImageResource(R.drawable.southpinkdestroyed); break;
                        case EAST_DESTROYED: cell.setImageResource(R.drawable.eastpinkdestroyed); break;
                        case WEST_DESTROYED: cell.setImageResource(R.drawable.westpinkdestroyed); break;
                        case MIDDLE_HORIZONTAL_DESTROYED: cell.setImageResource(R.drawable.middlepinkhorizontaldestroyed); break;
                        case MIDDLE_VERTICAL_DESTROYED: cell.setImageResource(R.drawable.middlepinkverticaldestroyed); break;
                        case NORTH_DESTROYED: cell.setImageResource(R.drawable.northpinkdestroyed); break;
                        case MISSED: cell.setImageResource(R.drawable.emptypinkmissed); break;
                        default: cell.setImageResource(R.drawable.emptypink); break;
                    }
                    break;
                case "boys":
                    switch(cellView){
                        case EAST: cell.setImageResource(R.drawable.eastgreen);break;
                        case WEST: cell.setImageResource(R.drawable.westgreen);break;
                        case NORTH: cell.setImageResource(R.drawable.northgreen);break;
                        case SOUTH: cell.setImageResource(R.drawable.southgreen);break;
                        case MIDDLE_HORIZONTAL: cell.setImageResource(R.drawable.middlegreenhorizontal);break;
                        case MIDDLE_VERTICAL: cell.setImageResource(R.drawable.middlegreenvertical);break;
                        case SOUTH_DESTROYED: cell.setImageResource(R.drawable.southgreendestroyed); break;
                        case EAST_DESTROYED: cell.setImageResource(R.drawable.eastgreendestroyed); break;
                        case WEST_DESTROYED: cell.setImageResource(R.drawable.westgreendestroyed); break;
                        case MIDDLE_HORIZONTAL_DESTROYED: cell.setImageResource(R.drawable.middlegreenhorizontaldestroyed); break;
                        case MIDDLE_VERTICAL_DESTROYED: cell.setImageResource(R.drawable.middlegreenverticaldestroyed); break;
                        case NORTH_DESTROYED: cell.setImageResource(R.drawable.northgreendestroyed); break;
                        case MISSED: cell.setImageResource(R.drawable.emptygreenmissed); break;
                        default: cell.setImageResource(R.drawable.emptygreen); break;
                    }
                    break;
            }

        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }

    //Below, there are basic methods to make the adapter work.
    @Override
    public int getCount() {
        return board.getLength();
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
