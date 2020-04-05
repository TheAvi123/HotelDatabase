package model.tables;

import model.tableHelpers.ServiceHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Service extends Table {

    private ServiceHelper helper;

    private final String id;
	private final String minTierLevel;
	private final double cost;
	private final String hotelAddress;

	public Service(String id, String minTierLevel, double cost, String hotelAddress) {
		this.id = id;
		this.minTierLevel = minTierLevel;
		this.cost = cost;
		this.hotelAddress = hotelAddress;

        this.helper = new ServiceHelper();
    }

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getId() {
		return id;
	}

	public String getMinTierLevel() {
		return minTierLevel;
	}

	public double getCost() {
		return cost;
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
        ps.setString(1, this.getId());
        ps.setString(2, this.getMinTierLevel());
        ps.setDouble(3, this.getCost());
        ps.setString(4, this.getHotelAddress());

    }
}
