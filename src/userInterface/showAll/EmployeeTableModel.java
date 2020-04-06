package userInterface.showAll;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class EmployeeTableModel extends AbstractTableModel {
    private ArrayList<JSONObject> employees;
    private String[] columns ;

    public EmployeeTableModel(ArrayList<JSONObject> employeeArr){
        super();
        employees = employeeArr ;
        columns = new String[]{"employeeStaffID", "employeeName", "payrollAccountNumber", "salary", "workShift", "managerStaffID"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return employees.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        JSONObject employee = employees.get(row);
        switch(col) {
            case 0:
                try {
                    return employee.get("employeeStaffID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 1:
                try {
                    return employee.get("employeeName");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    return employee.get("payrollAccountNumber");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 3:
                try {
                    return employee.get("salary");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 4:
                try {
                    return employee.get("workShift");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 5:
                try {
                    return employee.get("managerStaffID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}