package userInterface.showAll;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;

public class DynamicTableModel extends AbstractTableModel {
    private ArrayList<String> columns;
    private ArrayList<JSONObject> tuples;

    public DynamicTableModel(ArrayList<JSONObject> tuples){
        super();
        columns = new ArrayList<String>();
        this.tuples = new ArrayList<JSONObject>();
        if (tuples.size() > 0) {
            JSONObject tuple = tuples.get(0);
            Iterator<String> iterator = tuple.keys();
            while (iterator.hasNext()) {
                columns.add(iterator.next());
            }
        } else {
            columns.add("No Valid Results");
        }
        addColumnHeaders();
        this.tuples.addAll(tuples);
    }

    private void addColumnHeaders() {
        //Add Column Headers
        try {
            JSONObject columnNames = new JSONObject();
            for (String col : columns) {
                columnNames.put(col, col);
            }
            this.tuples.add(columnNames);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    // Number of column of your table
    public int getColumnCount() {
        return columns.size();
    }

    // Number of row of your table
    public int getRowCount() {
        return tuples.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        JSONObject tuple = tuples.get(row);
        String columnKey = this.columns.get(col);
        try {
            return tuple.get(columnKey);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getColumnName(int col) {
        return columns.get(col);
    }
}