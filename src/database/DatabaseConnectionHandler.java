package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import controller.HotelController;
import model.Table;
import model.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;
import tools.Condition;

public class DatabaseConnectionHandler {
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";

	private static Connection connection = null;

	public DatabaseConnectionHandler(HotelController controller) {
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

	public ArrayList<JSONObject> projectionHotel(Boolean[] attributesToShow) {
		ArrayList<JSONObject> tuples = new ArrayList<JSONObject>();
		try {
			String attributesToJoin = "";
			if (attributesToShow[0] == true && attributesToJoin.equals("")) {
				attributesToJoin = attributesToJoin + "hotelAddress";
			} else if (attributesToShow[0] == true) {
				attributesToJoin = attributesToJoin + ", hotelAddress";
			}
			if (attributesToShow[1] == true && attributesToJoin.equals("")) {
				attributesToJoin = attributesToJoin + "hotelName";
			} else if (attributesToShow[1] == true) {
				attributesToJoin = attributesToJoin + ", hotelName";
			}
			if (attributesToShow[2] == true && attributesToJoin.equals("")) {
				attributesToJoin = attributesToJoin + "capacity";
			} else if (attributesToShow[2] == true) {
				attributesToJoin = attributesToJoin + ", capacity";
			}

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT " + attributesToJoin + " FROM hotel");
			while (resultSet.next()) {
				JSONObject tuple = createEntityTuple("hotel", resultSet);
				tuples.add(tuple);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuples;
	}

	public ArrayList<JSONObject> aggregationMaxHotel(Boolean[] attributesInSelect) {
		ArrayList<JSONObject> tuples = new ArrayList<JSONObject>();
		try {
			String attributesToJoin = "";
			if (attributesInSelect[2] == true && attributesToJoin.equals("")) {
				attributesToJoin = attributesToJoin + " MAX(capacity)";
			} else if (attributesInSelect[2] == true) {
				attributesToJoin = attributesToJoin + ", MAX(capacity)";
			}

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT " + attributesToJoin + " FROM hotel");
			while (resultSet.next()) {
				JSONObject tuple = createEntityTuple("hotel", resultSet);
				tuples.add(tuple);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuples;
	}

	public ArrayList<JSONObject> aggregationGroupHotel(Boolean[] attributesInSelect, Boolean[] attributesInGroup) {
		ArrayList<JSONObject> tuples = new ArrayList<JSONObject>();
		try {
			String attributesToJoin = "";
			String groupByToJoin = "";
			// address, name, capacity, max(capacity)
			if (attributesInSelect[0] == true && attributesToJoin.equals("")) {
				attributesToJoin = attributesToJoin + "hotelAddress";
			} else if (attributesInSelect[0] == true && !attributesToJoin.equals("")) {
				attributesToJoin = attributesToJoin + ", hotelAddress";
			}
			if (attributesInSelect[1] == true && attributesToJoin.equals("")) {
				attributesToJoin = attributesToJoin + "hotelName";
			} else if (attributesInSelect[1] == true && !attributesToJoin.equals("")) {
				attributesToJoin = attributesToJoin + ", hotelName";
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

			// address, name, capacity
			if (attributesInGroup[0] == true && groupByToJoin.equals("")) {
				groupByToJoin = groupByToJoin + "hotelAddress";
			} else if (attributesInGroup[0] == true && !groupByToJoin.equals("")) {
				groupByToJoin = groupByToJoin + ", hotelAddress";
			}
			if (attributesInGroup[1] == true && groupByToJoin.equals("")) {
				groupByToJoin = groupByToJoin + "hotelName";
			} else if (attributesInGroup[1] == true && !groupByToJoin.equals("")) {
				groupByToJoin = groupByToJoin + ", hotelName";
			}
			if (attributesInGroup[2] == true && groupByToJoin.equals("")) {
				groupByToJoin = groupByToJoin + "capacity";
			} else if (attributesInGroup[2] == true && !groupByToJoin.equals("")) {
				groupByToJoin = groupByToJoin + ", capacity";
			}


			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT " + attributesToJoin + " FROM hotel " + "GROUP BY "
					+ groupByToJoin);
			while (resultSet.next()) {
				JSONObject tuple = createEntityTuple("hotel", resultSet);
				tuples.add(tuple);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuples;
	}

	public ArrayList<JSONObject> division(String table1, String table2) {
		ArrayList<JSONObject> tuples = new ArrayList<JSONObject>();
		if (table1.equals("customer") && table2.equals("room")) {

			try {
				String attributesInSelect = "c.customerName";
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT " + attributesInSelect + " FROM customer c " + "WHERE NOT EXISTS " +
						"(SELECT r.roomfloor, r.roomNumber from room r EXCEPT SELECT b.roomfloor, b.roomnumber FROM Booking b " +
						"WHERE b.customerid = c.customerid)");
				while (resultSet.next()) {
					JSONObject tuple = createEntityTuple("hotel", resultSet);
					tuples.add(tuple);
				}
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			}
			return tuples;
		}
		return tuples;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

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

	private JSONObject createEntityTuple(String tableName, ResultSet rs) {
		JSONObject tuple = new JSONObject();
		try {
			switch (tableName) {
				case "room":
					if (hasColumn(rs, "roomNumber")) {
						int roomNum = rs.getInt("roomNumber");
						tuple.put("roomNumber", roomNum);

					}
					if (hasColumn(rs, "roomFloor")) {
						int roomFloor = rs.getInt("roomFloor");
						tuple.put("roomFloor", roomFloor);

					}
					if (hasColumn(rs, "roomType")) {
						String roomType = rs.getString("roomType");
						tuple.put("roomType", roomType);

					}
					if (hasColumn(rs, "numberOfBeds")) {
						int numberOfBeds = rs.getInt("numberOfBeds");
						tuple.put("numberOfBeds", numberOfBeds);

					}
					if (hasColumn(rs, "hotelAddress")) {
						String hotelAddress = rs.getString("hotelAddress");
						tuple.put("hotelAddress", hotelAddress);
					}
					break;
				case "hotel":
					if (hasColumn(rs, "hotelAddress")) {
						String hotelAddress = rs.getString("hotelAddress");
						tuple.put("hotelAddress", hotelAddress);
					}
					if (hasColumn(rs, "hotelName")) {
						String hotelName = rs.getString("hotelName");
						tuple.put("hotelName", hotelName);
					}
					if (hasColumn(rs, "capacity")) {
						int hotelCapacity = rs.getInt("capacity");
						tuple.put("capacity", hotelCapacity);
					}
					if (hasColumn(rs, "MAX(capacity)")) {
						int hotelMaxCapacity = rs.getInt("MAX(capacity)");
						tuple.put("MAX(capacity)", hotelMaxCapacity);
					}
					break;
				case "booking":
					if (hasColumn(rs, "bookingID")) {
						String bookingID = rs.getString("bookingID");
						tuple.put("bookingID", bookingID);
					}
					if (hasColumn(rs, "startDate")) {
						Date startDate = rs.getDate("startDate");
						tuple.put("startDate", startDate);
					}
					if (hasColumn(rs, "endDate")) {
						Date endDate = rs.getDate("endDate");
						tuple.put("endDate", endDate);
					}
					if (hasColumn(rs, "roomFloor")) {
						int roomFloor = rs.getInt("roomFloor");
						tuple.put("roomFloor", roomFloor);
					}
					if (hasColumn(rs, "roomNumber")) {
						int roomNumber = rs.getInt("roomNumber");
						tuple.put("roomNumber", roomNumber);
					}
					if (hasColumn(rs, "customerID")) {
						String customerID = rs.getString("customerID");
						tuple.put("customerID", customerID);
					}
					break;
				case "customer":
					if (hasColumn(rs, "customerID")) {
						String customerID = rs.getString("customerID");
						tuple.put("customerID", customerID);
					}
					if (hasColumn(rs, "customerName")) {
						String customerName = rs.getString("customerName");
						tuple.put("customerName", customerName);
					}
					if (hasColumn(rs, "customerAge")) {
						int customerAge = rs.getInt("customerAge");
						tuple.put("customerAge", customerAge);
					}
					if (hasColumn(rs, "paymentInformation")) {
						String paymentInformation = rs.getString("paymentInformation");
						tuple.put("paymentInformation", paymentInformation);
					}
					if (hasColumn(rs, "phoneNumber")) {
						String phoneNumber = rs.getString("phoneNumber");
						tuple.put("phoneNumber", phoneNumber);
					}
					break;
				case "employee":
					if (hasColumn(rs, "employeeStaffID")) {
						String staffID = rs.getString("employeeStaffID");
						tuple.put("employeeStaffID", staffID);
					}
					if (hasColumn(rs, "employeeName")) {
						String employeeName = rs.getString("employeeName");
						tuple.put("employeeName", employeeName);
					}
					if (hasColumn(rs, "payrollAccountNumber")) {
						int accountNumber = rs.getInt("payrollAccountNumber");
						tuple.put("payrollAccountNumber", accountNumber);
					}
					if (hasColumn(rs, "salary")) {
						double salary = rs.getDouble("salary");
						tuple.put("salary", salary);
					}
					if (hasColumn(rs, "workShift")) {
						String workShift = rs.getString("workShift");
						tuple.put("workShift", workShift);
					}
					if (hasColumn(rs, "managerStaffID")) {
						String managerStaffID = rs.getString("managerStaffID");
						tuple.put("managerStaffID", managerStaffID);
					}
					break;
				case "manager":
					if (hasColumn(rs, "managerStaffID")) {
						String staffID = rs.getString("managerStaffID");
						tuple.put("managerStaffID", staffID);
					}
					if (hasColumn(rs, "managerName")) {
						String managerName = rs.getString("managerName");
						tuple.put("managerName", managerName);
					}
					if (hasColumn(rs, "hotelAddress")) {
						String hotelAddress = rs.getString("hotelAddress");
						tuple.put("hotelAddress", hotelAddress);
					}
					break;
				case "roomCost":
					if (hasColumn(rs, "roomNumber")) {
						int roomNum = rs.getInt("roomNumber");
						tuple.put("roomNumber", roomNum);
					}
					if (hasColumn(rs, "roomFloor")) {
						int roomFloor = rs.getInt("roomFloor");
						tuple.put("roomFloor", roomFloor);
					}
					if (hasColumn(rs, "roomCost")) {
						double roomCost = rs.getDouble("roomCost");
						tuple.put("roomCost", roomCost);
					}
					break;
				case "roomTier":
					if (hasColumn(rs, "roomType")) {
						String roomType = rs.getString("roomType");
						tuple.put("roomType", roomType);
					}
					if (hasColumn(rs, "tierLevel")) {
						String roomTier = rs.getString("tierLevel");
						tuple.put("tierLevel", roomTier);
					}
					break;
				case "service":
					if (hasColumn(rs, "serviceID")) {
						String serviceID = rs.getString("serviceID");
						tuple.put("serviceID", serviceID);
					}
					if (hasColumn(rs, "minTierLevel")) {
						String minTierLevel = rs.getString("minTierLevel");
						tuple.put("minTierLevel", minTierLevel);
					}
					if (hasColumn(rs, "serviceCost")) {
						double serviceCost = rs.getDouble("serviceCost");
						tuple.put("serviceCost", serviceCost);
					}
					if (hasColumn(rs, "hotelAddress")) {
						String hotelAddress = rs.getString("hotelAddress");
						tuple.put("hotelAddress", hotelAddress);
					}
					break;
				default:
					System.out.println("Invalid Table Name. Please try again.");
					throw new Error("Invalid Table Name");
			}
		} catch (SQLException | JSONException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuple;
	}

	public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		for (int x = 1; x <= columns; x++) {
			String rsmdc = rsmd.getColumnName(x);
			if (columnName.equalsIgnoreCase(rsmdc)) {
				return true;
			}
		}
		return false;
	}
}
