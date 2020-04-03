package ca.ubc.cs304.model;


import ca.ubc.cs304.modelInterface.Entity;
import ca.ubc.cs304.modelInterface.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Service extends Entity {

    private ServiceHelper helper = null;

    private final String serviceID;
	private final String minTierLevel;
	private final int cost;
	private final String hotelAddress;

	public Service(String serviceID, String minTierLevel, int cost, String hotelAddress) {
		this.serviceID = serviceID;
		this.minTierLevel = minTierLevel;
		this.cost = cost;
		this.hotelAddress = hotelAddress;
        this.helper = new ServiceHelper();
    }

	public String getServiceID() {
		return serviceID;
	}

	public String getMinTierLevel() {
		return minTierLevel;
	}

	public int getCost() {
		return cost;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

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
        ps.setInt(3, this.getCost());
        ps.setString(4, this.getHotelAddress());

    }
}
