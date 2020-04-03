package database;

import controller.HotelController;
import model.tables.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private HotelController controller;

    public DatabaseInitializer(HotelController controller) {
        this.controller = controller;
    }

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private String[] entities = {
            "booking",
            "customer",
            "employee",
            "hotel",
            "manager",
            "room",
            "roomCost",
            "service"
    };
    private String[] entityCommands = {
            "CREATE TABLE booking (" +
                    "booking_id varchar2(20) PRIMARY KEY, " +
                    "booking_startDate date not null, " +
                    "booking_endDate date not null, " +
                    "booking_roomFloor integer not null, " +
                    "booking_roomNumber integer not null, " +
                    "booking_customerID varchar2(20) not null, " +
                    "FOREIGN KEY (booking_roomFloor, booking_roomNumber) REFERENCES room(room_floor, room_number))",
            "CREATE TABLE customer (" +
                    "customer_id varchar2(20) PRIMARY KEY, " +
                    "customer_name varchar2(20) not null, " +
                    "customer_age integer not null, " +
                    "customer_paymentInformation varchar2(20) not null, " +
                    "customer_phoneNumber varchar2(20) not null)",
            "CREATE TABLE employee (" +
                    "employee_staffID varchar2(20) PRIMARY KEY, " +
                    "employee_name varchar2(20) not null, " +
                    "employee_payrollAccountNumber integer not null, " +
                    "employee_salary float not null, " +
                    "employee_workShift varchar2(20) not null, " +
                    "employee_managerStaffID varchar2(20) not null)",
            "CREATE TABLE hotel (" +
                    " varchar2(20), " +
                    " varchar2(20) not null, " +
                    " integer not null, " +
                    " varchar2(20) not null, " +
                    " varchar2(20) not null, " +
                    "PRIMARY KEY ())",
            "CREATE TABLE manager (" +
                    " varchar2(20), " +
                    " varchar2(20) not null, " +
                    " integer not null, " +
                    " varchar2(20) not null, " +
                    " varchar2(20) not null, " +
                    "PRIMARY KEY ())",
            "CREATE TABLE room (" +
                    " varchar2(20), " +
                    " varchar2(20) not null, " +
                    " integer not null, " +
                    " varchar2(20) not null, " +
                    " varchar2(20) not null, " +
                    "PRIMARY KEY ())",
            "CREATE TABLE roomCost (" +
                    " varchar2(20), " +
                    " varchar2(20) not null, " +
                    " integer not null, " +
                    " varchar2(20) not null, " +
                    " varchar2(20) not null, " +
                    "PRIMARY KEY ())",
            "CREATE TABLE service (" +
                    " varchar2(20), " +
                    " varchar2(20) not null, " +
                    " integer not null, " +
                    " varchar2(20) not null, " +
                    " varchar2(20) not null, " +
                    "PRIMARY KEY ())",
    };


    public void initializeDatabase(Connection connection) {

        try {
            /*
            for (String entity : entities) {
                StringBuilder sqlCommand = new StringBuilder("CREATE TABLE " + entity + " ");
                sqlCommand.append("LOL");
                PreparedStatement ps = connection.prepareStatement(sqlCommand.toString());
            }
             */
            String sqlCommand = "CREATE TABLE room " +
                    "(room_number integer, " +
                    "room_floor integer, " +
                    "room_type varchar2(20), " +
                    "room_numberOfBeds integer, " +
                    "room_hotelAddress varchar2(20) not null, " +
                    "PRIMARY KEY(room_number, room_floor))";
            Statement ps = connection.createStatement();
            ps.executeUpdate(sqlCommand);
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        Room room1 = new Room(101, 1, "single room", 1, "Vancouver");
        Room room2 = new Room(304, 3, "medium suite", 2, "Vancouver");
        Room room3 = new Room(500, 5, "penthouse", 7, "Vancouver");
        controller.insertTuple(room1);
        controller.insertTuple(room2);
        controller.insertTuple(room3);
    }
}
