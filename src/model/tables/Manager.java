package model.tables;

import model.tableHelpers.ManagerHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Manager extends Table {

    private ManagerHelper helper;

    private final String managerStaffID;
	private final String managerName;
	private final String hotelAddress;

	public Manager(String managerStaffID, String managerName, String hotelAddress) {
		this.managerStaffID = managerStaffID;
		this.managerName = managerName;
		this.hotelAddress = hotelAddress;

        this.helper = new ManagerHelper();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getManagerStaffID() {
		return managerStaffID;
	}

	public String getManagerName() {
		return managerName;
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
        ps.setString(1, this.getManagerStaffID());
        ps.setString(2, this.getManagerName());
        ps.setString(3, this.getHotelAddress());
    }
}
