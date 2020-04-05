package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import controller.HotelController;
import model.tables.Hotel;
import model.tables.Room;
import model.Table;
import model.TableHelper;
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
	public ArrayList<JSONObject> getTableTuples(String tableName) {
		ArrayList<JSONObject> tuples = new ArrayList<JSONObject>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
			while (resultSet.next()) {
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

	//HELPER METHODS
//	private Table createEntityTuple(String tableName, ResultSet rs) {
//		Table tuple = null;
//		try {
//			switch (tableName) {
//				case "room":
//					tuple = new Room(
//							rs.getInt("room_number"),
//							rs.getInt("room_floor"),
//							rs.getString("room_type"),
//							rs.getInt("room_numberOfBeds"),
//							rs.getString("room_hotelAddress"));
//					break;
//				case "hotel":
//					tuple = new Hotel(
//							rs.getString("hotel_address"),
//							rs.getString("hotel_name"),
//							rs.getInt("hotel_capacity"));
//					break;
//				default:
//					System.out.println("Invalid Table Name. Please try again.");
//					System.out.println("");
//					throw new Error("Invalid Table Name");
//			}
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//		return tuple;
//	}

	private JSONObject createEntityTuple(String tableName, ResultSet rs) {
		JSONObject tuple = new JSONObject();
		tuple = null;
		try {
			switch (tableName) {
				case "room":
					if (hasColumn(rs, "room_number") == true) {
						int roomNum = rs.getInt("room_number");
						tuple.put("room_number", roomNum);

					}
					if (hasColumn(rs, "room_floor") == true) {
						int roomFloor = rs.getInt("room_floor");
						tuple.put("room_floor", roomFloor);

					}
					if (hasColumn(rs, "room_type") == true) {
						String roomType = rs.getString("room_type");
						tuple.put("room_type", roomType);

					}
					if (hasColumn(rs, "room_numberOfBeds") == true) {
						int numberOfBeds = rs.getInt("room_numberOfBeds");
						tuple.put("room_numberOfBeds", numberOfBeds);

					}
					if (hasColumn(rs, "room_hotelAddress") == true) {
						String hotelAddress = rs.getString("room_hotelAddress");
						tuple.put("room_hotelAddress", hotelAddress);
					}
					break;
				case "hotel":
					if (hasColumn(rs, "hotel_address") == true) {
						String hotelAddress = rs.getString("hotel_address");
						tuple.put("hotel_address", hotelAddress);
					}
					if (hasColumn(rs, "hotel_name") == true) {
						String hotelName = rs.getString("hotel_name");
						tuple.put("hotel_name", hotelName);
					}
					if (hasColumn(rs, "hotel_capacity") == true) {
						int hotelCapacity = rs.getInt("hotel_capacity");
						tuple.put("hotel_capacity", hotelCapacity);
					}
					break;
				case "booking":
					if (hasColumn(rs, "booking_bookingID") == true) {
						String bookingID = rs.getString("booking_bookingID");
						tuple.put("hotel_address", bookingID);
					}
					if (hasColumn(rs, "booking_startDate") == true) {
						Date startDate = rs.getDate("booking_startDate");
						tuple.put("hotel_name", startDate);
					}
					if (hasColumn(rs, "booking_endDate") == true) {
						Date endDate = rs.getDate("booking_endDate");
						tuple.put("hotel_capacity", endDate);
					}
					if (hasColumn(rs, "booking_roomFloor") == true) {
						int roomFloor = rs.getInt("booking_roomFloor");
						tuple.put("hotel_capacity", roomFloor);
					}
					if (hasColumn(rs, "booking_roomNumber") == true) {
						int roomNumber = rs.getInt("booking_roomNumber");
						tuple.put("hotel_capacity", roomNumber);
					}
					if (hasColumn(rs, "booking_customerID") == true) {
						String customerID = rs.getString("booking_customerID");
						tuple.put("hotel_capacity", customerID);
					}
					break;
				case "customer":
					if (hasColumn(rs, "customer_customerID") == true) {
						String customerID = rs.getString("customer_customerID");
						tuple.put("customer_customerID", customerID);
					}
					if (hasColumn(rs, "customer_customerName") == true) {
						String customerName = rs.getString("customer_customerName");
						tuple.put("customer_customerName", customerName);
					}
					if (hasColumn(rs, "customer_customerAge") == true) {
						int customerAge = rs.getInt("customer_customerAge");
						tuple.put("customer_customerAge", customerAge);
					}
					if (hasColumn(rs, "customer_paymentInformation") == true) {
						String paymentInformation = rs.getString("customer_paymentInformation");
						tuple.put("customer_paymentInformation", paymentInformation);
					}
					if (hasColumn(rs, "customer_phoneNumber") == true) {
						String phoneNumber = rs.getString("customer_phoneNumber");
						tuple.put("customer_phoneNumber", phoneNumber);
					}
					break;
				case "employee":
					if (hasColumn(rs, "employee_employeeStaffID") == true) {
						String staffID = rs.getString("employee_employeeStaffID");
						tuple.put("employee_employeeStaffID", staffID);
					}
					if (hasColumn(rs, "employee_employeeName") == true) {
						String employeeName = rs.getString("employee_employeeName");
						tuple.put("employee_employeeName", employeeName);
					}
					if (hasColumn(rs, "employee_payrollAccountNumber") == true) {
						int accountNumber = rs.getInt("employee_payrollAccountNumber");
						tuple.put("employee_payrollAccountNumber", accountNumber);
					}
					if (hasColumn(rs, "employee_salary") == true) {
						double salary = rs.getDouble("employee_salary");
						tuple.put("employee_salary", salary);
					}
					if (hasColumn(rs, "employee_workShift") == true) {
						String workShift = rs.getString("employee_workShift");
						tuple.put("employee_workShift", workShift);
					}
					if (hasColumn(rs, "employee_managerStaffID") == true) {
						String managerStaffID = rs.getString("employee_managerStaffID");
						tuple.put("employee_managerStaffID", managerStaffID);
					}
					break;
				case "manager":
					if (hasColumn(rs, "manager_managerStaffID") == true) {
						String staffID = rs.getString("manager_managerStaffID");
						tuple.put("manager_managerStaffID", staffID);
					}
					if (hasColumn(rs, "manager_managerName") == true) {
						String managerName = rs.getString("manager_managerName");
						tuple.put("manager_managerName", managerName);
					}
					if (hasColumn(rs, "manager_hotelAddress") == true) {
						String hotelAddress = rs.getString("manager_hotelAddress");
						tuple.put("manager_hotelAddress", hotelAddress);
					}
					break;
				case "roomCost":
					if (hasColumn(rs, "roomCost_roomNumber") == true) {
						int roomNum = rs.getInt("roomCost_roomNumber");
						tuple.put("roomCost_roomNumber", roomNum);
					}
					if (hasColumn(rs, "roomCost_roomFloor") == true) {
						int roomFloor = rs.getInt("roomCost_roomFloor");
						tuple.put("roomCost_roomFloor", roomFloor);
					}
					if (hasColumn(rs, "roomCost_roomCost") == true) {
						double roomCost = rs.getDouble("roomCost_roomCost");
						tuple.put("roomCost_roomCost", roomCost);
					}
					break;
				case "roomTier":
					if (hasColumn(rs, "roomTier_roomType") == true) {
						String roomType = rs.getString("roomTier_roomType");
						tuple.put("roomTier_roomType", roomType);
					}
					if (hasColumn(rs, "roomTier_tierLevel") == true) {
						String roomTier = rs.getString("roomTier_tierLevel");
						tuple.put("roomTier_tierLevel", roomTier);
					}
					break;
				case "service":
					if (hasColumn(rs, "service_serviceID") == true) {
						String serviceID = rs.getString("service_serviceID");
						tuple.put("service_serviceID", serviceID);
					}
					if (hasColumn(rs, "service_minTierLevel") == true) {
						String minTierLevel = rs.getString("service_minTierLevel");
						tuple.put("service_minTierLevel", minTierLevel);
					}
					if (hasColumn(rs, "service_serviceCost") == true) {
						double serviceCost = rs.getDouble("service_serviceCost");
						tuple.put("service_serviceCost", serviceCost);
					}
					if (hasColumn(rs, "service_hotelAddress") == true) {
						String hotelAddress = rs.getString("service_hotelAddress");
						tuple.put("service_hotelAddress", hotelAddress);
					}
					break;
				default:
					System.out.println("Invalid Table Name. Please try again.");
					System.out.println("");
					throw new Error("Invalid Table Name");
			}
		} catch (SQLException | JSONException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return tuple;
	}

	// Projection Hotel
	public ArrayList<JSONObject> projectionHotel(Boolean[] attributesToShow) {
		ArrayList<JSONObject> tuples = new ArrayList<JSONObject>();
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


	//  Aggregation Hotel
	public ArrayList<JSONObject> aggregationMaxHotel(Boolean[] attributesInSelect) {
		ArrayList<JSONObject> tuples = new ArrayList<JSONObject>();
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

	//  AggregationWithGroup Hotel
	public ArrayList<JSONObject> aggregationGroupHotel(Boolean[] attributesInSelect, Boolean[] attributesInGroup) {
		ArrayList<JSONObject> tuples = new ArrayList<JSONObject>();
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

//			if (attributesInSelect[3] == true && attributesInSelect[0] == true && attributesInGroup[0] == false) {
//				System.out.println("error, if aggregate function present, all non-aggregate values must be in Group-By");
//			}
//			if (attributesInSelect[3] == true && attributesInSelect[1] == true && attributesInGroup[1] == false) {
//				System.out.println("error, if aggregate function present, all non-aggregate values must be in Group-By");
//			}
//			if (attributesInSelect[3] == true && attributesInSelect[2] == true && attributesInGroup[2] == false) {
//				System.out.println("error, if aggregate function present, all non-aggregate values must be in Group-By");
//			}


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

	// https://stackoverflow.com/questions/3599861/how-can-i-determine-if-the-column-name-exist-in-the-resultset
	public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		for (int x = 1; x <= columns; x++) {
			if (columnName.equals(rsmd.getColumnName(x))) {
				return true;
			}
		}
		return false;
	}

	// find customers that booked all rooms
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

	//CASE 1:
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

}


