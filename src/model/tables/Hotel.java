package model.tables;

import model.tableHelpers.HotelHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Hotel extends Table {

    private HotelHelper helper;

	private final String hotelAddress;
	private final String hotelName;
	private final int capacity;

	public Hotel(String hotelAddress, String hotelName, int capacity) {
		this.hotelAddress = hotelAddress;
		this.hotelName = hotelName;
		this.capacity = capacity;

        this.helper = new HotelHelper();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getHotelAddress() {
		return hotelAddress;
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getCapacity() {
		return capacity;
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
        ps.setString(1, this.getHotelAddress());
        ps.setString(2, this.getHotelName());
        if (this.getCapacity() == 0) {
            ps.setNull(3, java.sql.Types.INTEGER);
        } else {
            ps.setInt(3, this.getCapacity());
        }
    }
}
