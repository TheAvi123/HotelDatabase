package userInterface.showAll;

import model.tables.Room;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RoomTableModel extends AbstractTableModel {
    private ArrayList<Room> rooms;
    private String[] columns ;

    public RoomTableModel(ArrayList<Room> roomArr){
        super();
        rooms = roomArr ;
        columns = new String[]{"Room No.", "Room Floor", "Room Type", "No. Of Beds", "Hotel Address"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return rooms.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        Room room = rooms.get(row);
        switch(col) {
            case 0:
                return room.getNumber();
            case 1:
                return room.getFloor();
            case 2:
                return room.getType();
            case 3:
                return room.getNumberOfBeds();
            case 4:
                return room.getHotelAddress();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}