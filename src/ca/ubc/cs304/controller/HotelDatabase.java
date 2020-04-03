package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.Room;
import ca.ubc.cs304.modelInterface.Entity;
import ca.ubc.cs304.modelInterface.TableHelper;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.TerminalTransactions;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class HotelDatabase implements LoginWindowDelegate, TerminalTransactionsDelegate {

	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public HotelDatabase() {
		dbHandler = new DatabaseConnectionHandler();
	}

	// Entry Point To The Program
	public static void main(String args[]) {
		HotelDatabase hotelDatabase = new HotelDatabase();
		hotelDatabase.start();
	}

	private void start() {
		loginWindow = new LoginWindow();
		loginWindow.showFrame(this);
	}

	/**
	 * LoginWindowDelegate Implementation
	 *
	 * connects to Oracle database with supplied username and password
	 */
	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);
		if (didConnect) {
			// Once connected, remove login window and start text transaction flow
			loginWindow.dispose();
			TerminalTransactions transaction = new TerminalTransactions();
			transaction.showMainMenu(this);
		} else {
			loginWindow.handleLoginFailed();
			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}

	/**
	 * TerminalTransactionsDelegate Implementation
	 *
	 * The TerminalTransaction instance tells us that it is done with what it's
	 * doing so we are cleaning up the connection since it's no longer needed.
	 */
	public void terminalTransactionsFinished() {
		dbHandler.close();
		dbHandler = null;
		System.exit(0);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	public void insertTable(Entity table) {
		dbHandler.insertTable(table);
	}

	public void deleteTable(TableHelper tableHelper, JSONObject primaryKey) {
		dbHandler.deleteTable(tableHelper, primaryKey);
	}

	public void updateTable(TableHelper tableHelper, JSONObject setKeys, JSONObject whereKeys) {
		dbHandler.updateTable(tableHelper, setKeys, whereKeys);
	}

	public void showTable(String tableName) {
		Entity[] tuples = dbHandler.getTableInfo(tableName);
		for (int i = 0; i < tuples.length; i++) {
			Entity tuple = tuples[i];
			//tuple.getTableHelper().printTupleInfo(tuple);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Insert a branch with the given info
	 */
	public void insertBranch(BranchModel model) {
		dbHandler.insertBranch(model);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Delete branch with given branch ID.
	 */
	public void deleteBranch(int branchId) {
		dbHandler.deleteBranch(branchId);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Update the branch name for a specific ID
	 */
	public void updateBranch(int branchId, String name) {
		dbHandler.updateBranch(branchId, name);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Displays information about varies bank branches.
	 */
	public void showBranch() {
		BranchModel[] models = dbHandler.getBranchInfo();

		for (int i = 0; i < models.length; i++) {
			BranchModel model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getId());
			System.out.printf("%-20.20s", model.getName());
			if (model.getAddress() == null) {
				System.out.printf("%-20.20s", " ");
			} else {
				System.out.printf("%-20.20s", model.getAddress());
			}
			System.out.printf("%-15.15s", model.getCity());
			if (model.getPhoneNumber() == 0) {
				System.out.printf("%-15.15s", " ");
			} else {
				System.out.printf("%-15.15s", model.getPhoneNumber());
			}

			System.out.println();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Insert a room with the given info
	 */
	public void insertRoom(Room model) {
		dbHandler.insertRoom(model);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Delete room with given room number and room floor.
	 */
	public void deleteRoom(int roomNumber, int roomFloor) {
		dbHandler.deleteRoom(roomNumber, roomFloor);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Update the room type for a specific room
	 */
	public void updateRoom(int roomNumber, int roomFloor, String type) {
		dbHandler.updateRoom(roomNumber, roomFloor, type);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Displays information about various rooms.
	 */
	public void showRoom() {
		ArrayList<Room> models = dbHandler.getRoomInfo();

		for (int i = 0; i < models.size(); i++) {
			Room model = models.get(i);
			System.out.printf("%-10.10s", model.getNumber());
			System.out.printf("%-20.20s", model.getFloor());
			if (model.getType() == null) {
				System.out.printf("%-20.20s", " ");
			} else {
				System.out.printf("%-20.20s", model.getType());
			}
			System.out.printf("%-15.15s", model.getNumberOfBeds());
			System.out.printf("%-20.20s", model.getHotelAddress());
			System.out.println();
		}
	}
}
