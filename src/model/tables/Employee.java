package model.tables;

import model.tableHelpers.EmployeeHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class Employee extends Table {

    private EmployeeHelper helper;

    private final String employeeStaffID;
    private final String employeeName;
    private final int payrollAccountNumber;
    private final double salary;
    private final String workShift;
    private final String managerStaffID;

    public Employee(String employeeStaffID, String employeeName, int payrollAccountNumber, double salary, String workShift, String managerStaffID) {
        this.employeeStaffID = employeeStaffID;
        this.employeeName = employeeName;
        this.payrollAccountNumber = payrollAccountNumber;
        this.salary = salary;
        this.workShift = workShift;
        this.managerStaffID = managerStaffID;

        this.helper = new EmployeeHelper();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getEmployeeStaffID() {
        return employeeStaffID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getPayrollAccountNumber() {
        return payrollAccountNumber;
    }

    public double getSalary() {
        return salary;
    }

    public String getWorkShift() {
        return workShift;
    }

    public String getManagerStaffID() {
        return managerStaffID;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getAttributeCount() {
        return 6;
    }

    @Override
    public TableHelper getTableHelper() {
        return this.helper;
    }

    @Override
    public void setTupleParametersToStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.getEmployeeStaffID());
        ps.setString(2, this.getEmployeeName());
        ps.setInt(3, this.getPayrollAccountNumber());
        if (this.getSalary() == 0) {
            ps.setDouble(4, Types.DOUBLE);
        } else {
            ps.setDouble(4, this.getSalary());
        }
        ps.setString(5, this.getWorkShift());
        ps.setString(6, this.getManagerStaffID());
    }
}
