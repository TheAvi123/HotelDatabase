package model.tables;

import model.tableHelpers.EmployeeHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee extends Table {

    private EmployeeHelper helper;

    private final String staffID;
    private final String name;
    private final int payrollAccountNumber;
    private final double salary;
    private final String workShift;
    private final String managerStaffID;

    public Employee(String staffID, String name, int payrollAccountNumber, double salary, String workShift, String managerStaffID) {
        this.staffID = staffID;
        this.name = name;
        this.payrollAccountNumber = payrollAccountNumber;
        this.salary = salary;
        this.workShift = workShift;
        this.managerStaffID = managerStaffID;

        this.helper = new EmployeeHelper();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
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
        ps.setString(1, this.getStaffID());
        ps.setString(2, this.getName());
        ps.setInt(3, this.getPayrollAccountNumber());
        ps.setDouble(4, this.getSalary());
        ps.setString(5, this.getWorkShift());
        ps.setString(6, this.getManagerStaffID());
    }
}
