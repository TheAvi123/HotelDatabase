package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.Entity;
import ca.ubc.cs304.modelInterface.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// TODO DELETE CLASS
public class BranchModel extends Entity {

	private BranchModelHelper helper = null;

	private final String address;
	private final String city;
	private final int id;
	private final String name;
	private final int phoneNumber;

	public BranchModel(String address, String city, int id, String name, int phoneNumber) {
		this.address = address;
		this.city = city;
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.helper = new BranchModelHelper();
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public int getAttributeCount() {
		return 5;
	}

	@Override
	public TableHelper getTableHelper() {
		return this.helper;
	}

	@Override
	public void setTupleParametersToStatement(PreparedStatement ps) throws SQLException {
		ps.setInt(1, this.getId());
		ps.setString(2, this.getName());
		ps.setString(3, this.getAddress());
		ps.setString(4, this.getCity());
		if (this.getPhoneNumber() == 0) {
			ps.setNull(5, java.sql.Types.INTEGER);
		} else {
			ps.setInt(5, this.getPhoneNumber());
		}
	}
}
