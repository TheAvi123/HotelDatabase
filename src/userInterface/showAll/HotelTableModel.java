package userInterface.showAll;

import model.Table;
import model.tables.Hotel;
import model.tables.RoomCost;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HotelTableModel extends AbstractTableModel {
    private ArrayList<Table> hotels;
    private String[] columns ;

    public HotelTableModel(ArrayList<Table> roomArr){
        super();
        hotels = roomArr ;
        columns = new String[]{"Hotel Address", "Hotel Name", "Capacity"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return hotels.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        Hotel hotel = (Hotel) hotels.get(row);
        switch(col) {
            case 0:
                return hotel.getHotelAddress();
            case 1:
                return hotel.getHotelName();
            case 2:
                return hotel.getCapacity();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}