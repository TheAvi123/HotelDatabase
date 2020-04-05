package database;

import java.sql.*;
import java.util.ArrayList;

import controller.HotelController;
import model.tables.Hotel;
import model.tables.Room;
import model.Table;
import model.TableHelper;
import org.json.JSONObject;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";

	private HotelController controller;
	private static Connection connection = null;

	public DatabaseConnectionHandler(HotelController controller) {
		this.controller = controller;
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public boolean login(String username, String password) {
		try {
			if (connection != null) {
				connection.close();
			}

			connection = DriverManager.getConnection(ORACLE_URL, username, password);
			connection.setAutoCommit(false);

			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return false;
		}
	}

	public void setupDatabase() {
		DatabaseInitializer initializer = new DatabaseInitializer(controller);
		initializer.initializeDatabase(connection);
	}

	private void rollbackConnection() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	//SQL INSERT COMMAND ROUTER
	public void insertTable(Table table) {
		try {
			StringBuilder sqlCommand = new StringBuilder("INSERT INTO ");
			sqlCommand.append(table.getTableHelper().getTableName()).append(" VALUES (");
			for (int i = 0; i < table.getAttributeCount() - 1; i++) {
				sqlCommand.append("?,");
			}
			sqlCommand.append("?)");
			PreparedStatement ps = connection.prepareStatement(sqlCommand.toString());
			table.setTupleParametersToStatement(ps);
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	//SQL DELETE COMMAND ROUTER
	public void deleteTable(TableHelper tableHelper, JSONObject primaryKey) {
		try {
			StringBuilder sqlCommand = new StringBuilder("DELETE FROM " + tableHelper.getTableName() + " WHERE ");
			String[] keyNames = JSONObject.getNames(primaryKey);
			if (keyNames.length == 1) {
				sqlCommand.append(keyNames[0]).append(" = ?");
			} else {
				for (int i = 0; i < keyNames.length - 1; i++) {
					sqlCommand.append(keyNames[i]).append(" = ? AND ");
				}
				sqlCommand.append(keyNames[keyNames.length - 1]).append(" = ?");
			}
			PreparedStatement ps = connection.prepareStatement(sqlCommand.toString());
			for (int i = 1; i <= keyNames.length; i++) {
				tableHelper.setStatementParameter(ps, i, keyNames[i - 1], primaryKey);
			}
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + tableHelper.getTableName() + " " + primaryKey.toString() + " does not exist!");
			}
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	//SQL UPDATE COMMAND ROUTER
	public void updateTable(TableHelper tableHelper, JSONObject setKeys, JSONObject whereKeys) {
		try {
			StringBuilder sqlCommand = new StringBuilder("UPDATE " + tableHelper.getTableName() + " SET ");
			String[] setKeyNames = JSONObject.getNames(setKeys);
			if (setKeyNames.length == 1) {
				sqlCommand.append(setKeyNames[0]).append(" = ? WHERE ");
			} else {
				for (int i = 0; i < setKeyNames.length - 1; i++) {
					sqlCommand.append(setKeyNames[i]).append(" = ?, ");
				}
				sqlCommand.append(setKeyNames[setKeyNames.length - 1]).append(" = ? WHERE ");
			}
			String[] whereKeyNames = JSONObject.getNames(whereKeys);
			if (whereKeyNames.length == 1) {
				sqlCommand.append(whereKeyNames[0]).append(" = ?");
			} else {
				for (int i = 0; i < whereKeyNames.length - 1; i++) {
					sqlCommand.append(whereKeyNames[i]).append(" = ? AND ");
				}
				sqlCommand.append(whereKeyNames[whereKeyNames.length - 1]).append(" = ?");
			}
			PreparedStatement ps = connection.prepareStatement(sqlCommand.toString());
			for (int i = 1; i <= setKeyNames.length; i++) {
				tableHelper.setStatementParameter(ps, i, setKeyNames[i - 1], setKeys);
			}
			for (int i = 1; i <= whereKeyNames.length; i++) {
				tableHelper.setStatementParameter(ps, i + setKeyNames.length, whereKeyNames[i - 1], whereKeys);
			}
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + tableHelper.getTableName() + " specified does not exist!");
			}
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	//SQL SHOW COMMAND ROUTER
	public ArrayList<Table> getTableTuples(String tableName) {
		ArrayList<Table> tuples = new ArrayList<Table>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
			while (resultSet.next()) {
				Table tuple = createEntityTuple(tableName, resultSet);
				tuples.add(tuple);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuples;
	}

	//HELPER METHODS
	private Table createEntityTuple(String tableName, ResultSet rs) {
		Table tuple = null;
		try {
			switch (tableName) {
				case "room":
					tuple = new Room(
							rs.getInt("room_number"),
							rs.getInt("room_floor"),
							rs.getString("room_type"),
							rs.getInt("room_numberOfBeds"),
							rs.getString("room_hotelAddress"));
					break;
				case "hotel":
					tuple = new Hotel(
							rs.getString("hotel_address"),
							rs.getString("hotel_name"),
							rs.getInt("hotel_capacity"));
					break;
				default:
					System.out.println("Invalid Table Name. Please try again.");
					System.out.println("");
					throw new Error("Invalid Table Name");
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuple;
	}

	// Projection Hotel
	public ArrayList<Table> projectionHotel(Boolean[] attributesToShow) {
		ArrayList<Table> tuples = new ArrayList<Table>();
		try {
			String attributesToJoin = "";
			for (int i = 0; i < attributesToShow.length; i++) {
				if (attributesToShow[0] == true && attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + "address";
				} else if (attributesToShow[0] == true) {
					attributesToJoin = attributesToJoin + ", address";
				}
				if (attributesToShow[1] == true && attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + "name";
				} else if (attributesToShow[1] == true) {
					attributesToJoin = attributesToJoin + ", name";
				}
				if (attributesToShow[2] == true && attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + "capacity";
				} else if (attributesToShow[2] == true) {
					attributesToJoin = attributesToJoin + ", capacity";
				}
			}
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT " + attributesToJoin + " FROM hotel");
			while (resultSet.next()) {
				Table tuple = createEntityTuple("hotel", resultSet);
				tuples.add(tuple);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuples;
	}


	//  Aggregation Hotel
	public ArrayList<Table> aggregationMaxHotel(Boolean[] attributesInSelect) {
		ArrayList<Table> tuples = new ArrayList<Table>();
		try {
			String attributesToJoin = "";
			for (int i = 0; i < attributesInSelect.length; i++) {
				if (attributesInSelect[2] == true && attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + " MAX(capacity)";
				} else if (attributesInSelect[2] == true) {
					attributesToJoin = attributesToJoin + ", MAX(capacity)";
				}
			}
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT " + attributesToJoin + " FROM hotel");
			while (resultSet.next()) {
				Table tuple = createEntityTuple("hotel", resultSet);
				tuples.add(tuple);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuples;
	}

	//  AggregationWithGroup Hotel
	public ArrayList<Table> aggregationGroupHotel(Boolean[] attributesInSelect, Boolean[] attributesInGroup) {
		ArrayList<Table> tuples = new ArrayList<Table>();
		try {
			String attributesToJoin = "";
			String groupByToJoin = "";
			// address, name, capacity, max(capacity)
			for (int i = 0; i < attributesInSelect.length; i++) {
				if (attributesInSelect[0] == true && attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + "address";
				} else if (attributesInSelect[0] == true && !attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + ", address";
				}
				if (attributesInSelect[1] == true && attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + "name";
				} else if (attributesInSelect[1] == true && !attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + ", name";
				}
				if (attributesInSelect[2] == true && attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + " (capacity)";
				} else if (attributesInSelect[2] == true && !attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + ", (capacity)";
				}
				if (attributesInSelect[3] == true && attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + " MAX(capacity)";
				} else if (attributesInSelect[3] == true && !attributesToJoin.equals("")) {
					attributesToJoin = attributesToJoin + ", MAX(capacity)";
				}
			}
			// address, name, capacity
			for (int i = 0; i < attributesInGroup.length; i++) {
				if (attributesInGroup[0] == true && groupByToJoin.equals("")) {
					groupByToJoin = groupByToJoin + "address";
				} else if (attributesInGroup[0] == true && !groupByToJoin.equals("")) {
					groupByToJoin = groupByToJoin + ", address";
				}
				if (attributesInGroup[1] == true && groupByToJoin.equals("")) {
					groupByToJoin = groupByToJoin + "name";
				} else if (attributesInGroup[1] == true && !groupByToJoin.equals("")) {
					groupByToJoin = groupByToJoin + ", name";
				}
				if (attributesInGroup[2] == true && groupByToJoin.equals("")) {
					groupByToJoin = groupByToJoin + "capacity";
				} else if (attributesInGroup[2] == true && !groupByToJoin.equals("")) {
					groupByToJoin = groupByToJoin + ", capacity";
				}
			}

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT " + attributesToJoin + " FROM hotel " + "GROUP BY "
					+ groupByToJoin);
			while (resultSet.next()) {
				Table tuple = createEntityTuple("hotel", resultSet);
				tuples.add(tuple);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuples;
	}

}

