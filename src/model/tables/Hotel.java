package model.tables;

import model.tableHelpers.HotelHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Hotel extends Table {

    private HotelHelper helper;

	private final String address;
	private final String name;
	private final int capacity;

	public Hotel(String address, String name, int capacity) {
		this.address = address;
		this.name = name;
		this.capacity = capacity;

        this.helper = new HotelHelper();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
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
        ps.setString(1, this.getAddress());
        ps.setString(2, this.getName());
        if (this.getCapacity() == 0) {
            ps.setNull(3, java.sql.Types.INTEGER);
        } else {
            ps.setInt(3, this.getCapacity());
        }
    }
}
