package userInterface.showAll;

import model.Table;
import model.tables.Room;
import model.tables.RoomCost;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RoomCostTableModel extends AbstractTableModel {
    private ArrayList<Table> roomCosts;
    private String[] columns ;

    public RoomCostTableModel(ArrayList<Table> roomArr){
        super();
        roomCosts = roomArr ;
        columns = new String[]{"Room No.", "Room Floor", "Room Cost"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return roomCosts.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        RoomCost roomCost = (RoomCost) roomCosts.get(row);
        switch(col) {
            case 0:
                return roomCost.getRoomNumber();
            case 1:
                return roomCost.getRoomFloor();
            case 2:
                return roomCost.getRoomCost();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}