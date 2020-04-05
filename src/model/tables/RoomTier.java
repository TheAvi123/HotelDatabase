package model.tables;

import model.Table;
import model.TableHelper;
import model.tableHelpers.RoomTierHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomTier extends Table {

    private RoomTierHelper helper = null;

    private final String roomType;
	private final String tierLevel;

	public RoomTier(String roomType, String tierLevel) {
		this.roomType = roomType;
		this.tierLevel = tierLevel;
        this.helper = new RoomTierHelper();
    }

	public String getRoomType() {
		return roomType;
	}

	public String getTierLevel() {
		return tierLevel;
	}


    @Override
    public int getAttributeCount() {
        return 2;
    }

    @Override
    public TableHelper getTableHelper() {
        return this.helper;
	}

    @Override
    public void setTupleParametersToStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.getRoomType());
        ps.setString(2, this.getTierLevel());
    }
}
