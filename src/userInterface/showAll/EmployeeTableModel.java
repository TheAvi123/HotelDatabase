package userInterface.showAll;

import model.Table;
import model.tables.Employee;
import model.tables.Service;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class EmployeeTableModel extends AbstractTableModel {
    private ArrayList<Table> employees;
    private String[] columns ;

    public EmployeeTableModel(ArrayList<Table> employeeArr){
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
        Employee em = (Employee) employees.get(row);
        switch(col) {
            case 0:
                return em.getEmployeeStaffID();
            case 1:
                return em.getEmployeeName();
            case 2:
                return em.getPayrollAccountNumber();
            case 3:
                return em.getSalary();
            case 4:
                return em.getWorkShift();
            case 5:
                return em.getManagerStaffID();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}