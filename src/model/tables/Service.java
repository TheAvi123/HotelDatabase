package model.tables;

import model.tableHelpers.ServiceHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Service extends Table {

    private ServiceHelper helper;

    private final String serviceID;
	private final String minTierLevel;
	private final double serviceCost;
	private final String hotelAddress;

	public Service(String serviceID, String minTierLevel, double serviceCost, String hotelAddress) {
		this.serviceID = serviceID;
		this.minTierLevel = minTierLevel;
		this.serviceCost = serviceCost;
		this.hotelAddress = hotelAddress;

        this.helper = new ServiceHelper();
    }

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getServiceID() {
		return serviceID;
	}

	public String getMinTierLevel() {
		return minTierLevel;
	}

	public double getServiceCost() {
		return serviceCost;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
    public int getAttributeCount() {
        return 4;
    }

    @Override
    public TableHelper getTableHelper() {
        return this.helper;
	}

    @Override
    public void setTupleParametersToStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.getServiceID());
        ps.setString(2, this.getMinTierLevel());
        ps.setDouble(3, this.getServiceCost());
        ps.setString(4, this.getHotelAddress());

    }
}
