package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.Entity;
import ca.ubc.cs304.modelInterface.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Manager extends Entity {

    private ManagerHelper helper = null;

    private final String staffID;
	private final String managerName;
	private final String hotelAddress;

	public Manager(String staffID, String managerName, String hotelAddress) {
		this.staffID = staffID;
		this.managerName = managerName;
		this.hotelAddress = hotelAddress;
        this.helper = new ManagerHelper();

    }

	public String getStaffID() {
		return staffID;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

    @Override
    public int getAttributeCount() {
        return 3;
    }

    @Override
    public TableHelper getTableHelper() {
        return this.helper;
    }

    @Override
    public void setTupleParametersToStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.getStaffID());
        ps.setString(2, this.getManagerName());
        ps.setString(3, this.getHotelAddress());
    }
}
