package database;

import java.awt.peer.LabelPeer;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import controller.HotelController;
import model.tables.Room;
import model.Table;
import model.TableHelper;
import model.tables.RoomCost;
import org.json.JSONException;
import org.json.JSONObject;
import tools.Condition;

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
	public ArrayList<JSONObject> getTableTuples(String tableName) {
		ArrayList<JSONObject> tuples = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
			while(resultSet.next()) {
				JSONObject tuple = createEntityTuple(tableName, resultSet);
				tuples.add(tuple);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuples;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ArrayList<JSONObject> selectionQuery(TableHelper helper, Condition condition) {
		ArrayList<JSONObject> tuples = new ArrayList<>();
		try {
			StringBuilder sqlCommand = new StringBuilder("SELECT * FROM " + helper.getTableName() + " WHERE ");
			sqlCommand.append(condition.getAttributeName()).append(" ");
			sqlCommand.append(condition.getComparator()).append(" ");
			sqlCommand.append(condition.getValue());
			PreparedStatement ps = connection.prepareStatement(sqlCommand.toString());
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				JSONObject tuple = createEntityTuple("roomCost", resultSet);
				tuples.add(tuple);
			}
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuples;
	}

	public ArrayList<JSONObject> naturalJoinQuery(TableHelper helper1, TableHelper helper2) {
		ArrayList<JSONObject> table1Tuples = this.getTableTuples(helper1.getTableName());
		ArrayList<JSONObject> table2Tuples = this.getTableTuples(helper2.getTableName());
		ArrayList<String> commonAttributes = this.getCommonAttributes(helper1, helper2);
		ArrayList<JSONObject> joinedTuples = new ArrayList<>();
		for (JSONObject tuple1 : table1Tuples) {
			for (JSONObject tuple2 : table2Tuples) {
				for (String commonAttribute : commonAttributes) {
					try {
						if (tuple1.get(commonAttribute) == tuple2.get(commonAttribute)) {
							JSONObject combinedTuple = new JSONObject();
							Iterator<String> keys = tuple1.keys();
							while (keys.hasNext()) {
								String key = keys.next();
								combinedTuple.put(key, tuple1.get(key));
							}
							keys = tuple2.keys();
							while (keys.hasNext()) {
								String key = keys.next();
								if (!commonAttribute.contains(key)) {
									combinedTuple.put(key, tuple2.get(key));
								}
							}
							joinedTuples.add(combinedTuple);
						}
					} catch (JSONException e) {
						System.out.println(EXCEPTION_TAG + " " + e.getMessage());
					}
				}
			}
		}
		return joinedTuples;
	}

	public ArrayList<String> getCommonAttributes(TableHelper helper1, TableHelper helper2) {
		ArrayList<String> commonAttributes = new ArrayList<>();
		for (String attribute1 : helper1.getAttributes()) {
			for (String attribute2 : helper2.getAttributes()) {
				if (attribute1.equals(attribute2)) {
					commonAttributes.add(attribute1);
				}
			}
		}
		return commonAttributes;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	private JSONObject createEntityTuple(String tableName, ResultSet rs) {
		JSONObject tuple = new JSONObject();
		try {
			switch (tableName) {
				case "room":
					tuple.put("roomNumber", rs.getInt("roomNumber"));
					tuple.put("roomFloor", rs.getInt("roomFloor"));
					tuple.put("roomType", rs.getString("roomType"));
					tuple.put("numberOfBeds", rs.getInt("numberOfBeds"));
					tuple.put("hotelAddress", rs.getString("hotelAddress"));
					break;
				case "roomCost":
					tuple.put("roomNumber", rs.getInt("roomNumber"));
					tuple.put("roomFloor",rs.getInt("roomFloor"));
					tuple.put("roomCost", rs.getDouble("roomCost"));
					break;
				default:
					System.out.println("Invalid Table Name. Please try again.");
					System.out.println("");
					throw new Error("Invalid Table Name");
			}
		} catch (JSONException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuple;
	}
}
