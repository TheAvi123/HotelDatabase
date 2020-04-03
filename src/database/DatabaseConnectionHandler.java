package database;

import java.sql.*;
import java.util.ArrayList;

import controller.HotelController;
import model.tables.BranchModel;
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
		try  {
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
				sqlCommand.append("(");
				for (int i = 0; i < keyNames.length - 1; i++) {
					sqlCommand.append(keyNames[i]).append(", ");
				}
				sqlCommand.append(keyNames[keyNames.length - 1]).append(") = (");
				for (int i = 0; i < keyNames.length; i++) {
					sqlCommand.append("?,");
				}
				sqlCommand.append("?)");
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
					sqlCommand.append(whereKeyNames[i]).append(" = ?, ");
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
			while(resultSet.next()) {
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

	private Table createEntityTuple(String tableName, ResultSet rs) {
		Table tuple = null;
		try {
			switch (tableName) {
				case "branch":
					tuple = new BranchModel(rs.getString("branch_addr"),
							rs.getString("branch_city"),
							rs.getInt("branch_id"),
							rs.getString("branch_name"),
							rs.getInt("branch_phone"));
				case "room":
					tuple = new Room(rs.getInt("room_number"),
							rs.getInt("room_floor"),
							rs.getString("room_type"),
							rs.getInt("number_of_beds"),
							rs.getString("hotel_address"));
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

	//TODO DELETE FUNCTION
	public ArrayList<Room> setRooms() {
		ArrayList<Room> result = new ArrayList<Room>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM room");
			while (rs.next()) {
				Room model = new Room(rs.getInt("room_number"),
						rs.getInt("room_floor"),
						rs.getString("room_type"),
						rs.getInt("room_numberOfBeds"),
						rs.getString("room_hotelAddress"));
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result;
	}
}
