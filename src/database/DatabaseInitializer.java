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

    private String databaseSetupSQL = "DROP TABLE booking;\n" +
            "DROP TABLE customer;\n" +
            "DROP TABLE employee;\n" +
            "DROP TABLE manager;\n" +
            "DROP TABLE hotel;\n" +
            "DROP TABLE roomCost;\n" +
            "DROP TABLE roomTier;\n" +
            "DROP TABLE room;\n" +
            "DROP TABLE service;\n" +
            " \n" +
            " \n" +
            " \n" +
            "CREATE TABLE booking (\n" +
            "    bookingID VARCHAR2(20) PRIMARY KEY,\n" +
            "    startDate DATE NOT NULL,\n" +
            "    endDate DATE NOT NULL,\n" +
            "    roomFloor INTEGER NOT NULL,\n" +
            "    roomNumber INTEGER NOT NULL,\n" +
            "    customerID VARCHAR2(20) NOT NULL,\n" +
            "    FOREIGN KEY (roomFloor, roomNumber) REFERENCES room,\n" +
            "    FOREIGN KEY (customerID) REFERENCES customer\n" +
            ");\n" +
            " \n" +
            "GRANT SELECT ON booking TO PUBLIC;\n" +
            " \n" +
            "INSERT INTO booking VALUES ('B1000', 1999-03-03, 1999-03-04, 1, 101, 'C100');\n" +
            "INSERT INTO booking VALUES ('B1001', 1999-06-20, 1999-06-25, 2, 201, 'C101');\n" +
            "INSERT INTO booking VALUES ('B1002', 2000-03-16, 2000-03-20, 2, 213, 'C101');\n" +
            "INSERT INTO booking VALUES ('B1003', 2019-12-25, 2020-01-03, 4, 413, 'C101');\n" +
            "INSERT INTO booking VALUES ('B1004', 2020-02-03, 2020-02-03, 2, 201, 'C103');\n" +
            " \n" +
            " \n" +
            " \n" +
            "CREATE TABLE customer (\n" +
            "    customerID VARCHAR2(20) PRIMARY KEY,\n" +
            "    customerName VARCHAR2(40) NOT NULL,\n" +
            "    customerAge INTEGER NOT NULL,\n" +
            "    paymentInformation VARCHAR2(100) NOT NULL,\n" +
            "    phoneNumber VARCHAR2(15) NOT NULL\n" +
            ");\n" +
            " \n" +
            "GRANT SELECT ON customer TO PUBLIC;\n" +
            " \n" +
            "INSERT INTO customer VALUES ('C100', 'Charlie', 12, 'Credit Card', '604-834-1231');\n" +
            "INSERT INTO customer VALUES ('C101', 'Amy',     20, 'Credit Card', '604-329-2342');\n" +
            "INSERT INTO customer VALUES ('C102', 'Adam',    30, 'Debit Card', '604-789-2334');\n" +
            "INSERT INTO customer VALUES ('C103', 'Eve',     45, 'Credit Card', '604-239-2023');\n" +
            "INSERT INTO customer VALUES ('C104', 'Satan',   66, 'Cash Payment', '604-123-1245');\n" +
            " \n" +
            " \n" +
            " \n" +
            "CREATE TABLE employee (\n" +
            "    employeeStaffID VARCHAR2(20) PRIMARY KEY,\n" +
            "    employeeName VARCHAR2(40) NOT NULL,\n" +
            "    payrollAccountNumber INTEGER NOT NULL,\n" +
            "    salary FLOAT NULL,\n" +
            "    workShift VARCHAR2(50) NOT NULL,\n" +
            "    managerStaffID VARCHAR2(20) NOT NULL,\n" +
            "    FOREIGN KEY (managerStaffID) REFERENCES manager\n" +
            ");\n" +
            " \n" +
            "GRANT SELECT ON employee TO PUBLIC;\n" +
            " \n" +
            "INSERT INTO employee VALUES ('S101', 'Akshat T', 00042, 10000.00, 'Fridays 5-9 PM',  'S100');\n" +
            "INSERT INTO employee VALUES ('S124', 'Bernie S', 00045, 16000.00, 'Mondays 5-9 PM',  'S100');\n" +
            "INSERT INTO employee VALUES ('S194', 'Donald T', 00086, 18000.00, 'Tuesdays 5-9 PM', 'S100');\n" +
            "INSERT INTO employee VALUES ('S285', 'Cathy R',  00098, 12000.00, 'Fridays 5-9 PM',  'S100');\n" +
            "INSERT INTO employee VALUES ('S304', 'Hazra I',  00099, 99000.00, 'Fridays 5-9 PM',  'S100');\n" +
            " \n" +
            " \n" +
            " \n" +
            "CREATE TABLE manager (\n" +
            "    managerStaffID VARCHAR2(20) PRIMARY KEY,\n" +
            "    managerName VARCHAR2(40) NOT NULL,\n" +
            "    hotelAddress VARCHAR2(40) NOT NULL,\n" +
            "    FOREIGN KEY (hotelAddress) REFERENCES hotel\n" +
            ");\n" +
            " \n" +
            "GRANT SELECT ON manager TO PUBLIC;\n" +
            " \n" +
            "INSERT INTO manager VALUES ('S100', 'Aviral S', '304 Brock St, Vancouver');\n" +
            "INSERT INTO manager VALUES ('S102', 'John S',   '304 Brock St, Vancouver');\n" +
            "INSERT INTO manager VALUES ('S120', 'Zach A',   '304 Brock St, Vancouver');\n" +
            "INSERT INTO manager VALUES ('S204', 'Paul G',   '304 Brock St, Vancouver');\n" +
            "INSERT INTO manager VALUES ('S254', 'Ronny M',  '304 Brock St, Vancouver');\n" +
            " \n" +
            " \n" +
            " \n" +
            "CREATE TABLE hotel (\n" +
            "    hotelAddress VARCHAR2(40) PRIMARY KEY,\n" +
            "    hotelName VARCHAR2(40) NOT NULL,\n" +
            "    capacity INTEGER NOT NULL\n" +
            ");\n" +
            " \n" +
            "GRANT SELECT ON hotel TO PUBLIC;\n" +
            " \n" +
            "INSERT INTO hotel VALUES ('304 Brock St, Vancouver', 'Radison Blu',     7500);\n" +
            "INSERT INTO hotel VALUES ('103 Sicilia St, Surrey',  'Radison Red',     1000);\n" +
            "INSERT INTO hotel VALUES ('3202 Fir St, Burnaby',    'Radison Green',   2500);\n" +
            "INSERT INTO hotel VALUES ('768 Bernard St, Toronto', 'Radison Yellow',  10000);\n" +
            "INSERT INTO hotel VALUES ('9632 Cairn St, Calgary',  'Radison Pink',    7500);\n" +
            " \n" +
            " \n" +
            " \n" +
            "CREATE TABLE roomCost (\n" +
            "    roomNumber VARCHAR2(20) PRIMARY KEY,\n" +
            "    roomFloor VARCHAR2(40) NOT NULL,\n" +
            "    roomCost FLOAT NOT NULL,\n" +
            "    FOREIGN KEY (roomNumber, roomFloor) REFERENCES room\n" +
            ");\n" +
            " \n" +
            "GRANT SELECT ON roomCost TO PUBLIC; \n" +
            " \n" +
            "INSERT INTO roomCost VALUES (1, 101, 12.50);\n" +
            "INSERT INTO roomCost VALUES (2, 201, 20.00);\n" +
            "INSERT INTO roomCost VALUES (2, 213, 20.00);\n" +
            "INSERT INTO roomCost VALUES (4, 413, 35.00);\n" +
            "INSERT INTO roomCost VALUES (5, 563, 100.00);\n" +
            " \n" +
            " \n" +
            " \n" +
            "CREATE TABLE roomTier (\n" +
            "    roomType VARCHAR2(30) PRIMARY KEY,\n" +
            "    tierLevel VARCHAR2(30) NOT NULL\n" +
            ");\n" +
            " \n" +
            "GRANT SELECT ON roomCost TO PUBLIC; \n" +
            " \n" +
            "INSERT INTO roomTier VALUES (\"Single Rooom\", \"Regular\");\n" +
            "INSERT INTO roomTier VALUES (\"Double Rooom\", \"Silver\");\n" +
            "INSERT INTO roomTier VALUES (\"Small Suite\", \"Gold\");\n" +
            "INSERT INTO roomTier VALUES (\"Large Suite\", \"Gold\");\n" +
            "INSERT INTO roomTier VALUES (\"Penthouse\", \"Platinum\");\n" +
            " \n" +
            " \n" +
            " \n" +
            "CREATE TABLE room (\n" +
            "    roomNumber INTEGER NOT NULL,\n" +
            "    roomFloor INTEGER NOT NULL,\n" +
            "    roomType VARCHAR2(30) NOT NULL,\n" +
            "    numberOfBeds INTEGER NULL,\n" +
            "    hotelAddress VARCHAR2(40) NOT NULL,\n" +
            "    PRIMARY KEY (roomNumber, roomFloor),\n" +
            "    FOREIGN KEY (roomType) REFERENCES roomTier,\n" +
            "    FOREIGN KEY (hotelAddress) REFERENCES hotel\n" +
            ");\n" +
            " \n" +
            "GRANT SELECT ON room TO PUBLIC;\n" +
            " \n" +
            "INSERT INTO room VALUES (101, 1, 'Single Room', 1, '304 Brock St, Vancouver');\n" +
            "INSERT INTO room VALUES (201, 2, 'Double Room', 2, '304 Brock St, Vancouver');\n" +
            "INSERT INTO room VALUES (213, 2, 'Double Room', 2, '304 Brock St, Vancouver');\n" +
            "INSERT INTO room VALUES (413, 4, 'Large Suite', 4, '304 Brock St, Vancouver');\n" +
            "INSERT INTO room VALUES (563, 5, 'Penthouse',   7, '304 Brock St, Vancouver');\n" +
            " \n" +
            " \n" +
            "     \n" +
            "CREATE TABLE service (\n" +
            "    serviceID VARCHAR2(20) PRIMARY KEY,\n" +
            "    minTierLevel VARCHAR2(30) NOT NULL,\n" +
            "    serviceCost FLOAT NULL,\n" +
            "    hotelAddress VARCHAR2(40) NOT NULL,\n" +
            "    FOREIGN KEY (hotelAddress) REFERENCES hotel\n" +
            ");\n" +
            " \n" +
            "GRANT SELECT ON service TO PUBLIC;\n" +
            " \n" +
            "INSERT INTO service VALUES ('SWIMPOOL',     'Silver',     0, '304 Brock St, Vancouver');\n" +
            "INSERT INTO service VALUES ('GYMNASIUM',    'Platinum', 100, '304 Brock St, Vancouver');\n" +
            "INSERT INTO service VALUES ('FREEWIFI',     'Silver',     0, '304 Brock St, Vancouver');\n" +
            "INSERT INTO service VALUES ('EXTRAKETCHUP', 'Regular',    0, '304 Brock St, Vancouver');\n" +
            "INSERT INTO service VALUES ('SPA',          'Gold',       0, '304 Brock St, Vancouver');";


    public void initializeDatabase(Connection connection) {
        try {
            Statement ps = connection.createStatement();
            ps.executeUpdate(databaseSetupSQL);
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
