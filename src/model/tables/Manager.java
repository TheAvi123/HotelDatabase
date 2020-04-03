package model.tables;

import model.tableHelpers.ManagerHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Manager extends Table {

    private ManagerHelper helper;

    private final String staffID;
	private final String name;
	private final String hotelAddress;

	public Manager(String staffID, String name, String hotelAddress) {
		this.staffID = staffID;
		this.name = name;
		this.hotelAddress = hotelAddress;

        this.helper = new ManagerHelper();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getStaffID() {
		return staffID;
	}

	public String getName() {
		return name;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        ps.setString(2, this.getName());
        ps.setString(3, this.getHotelAddress());
    }
}
