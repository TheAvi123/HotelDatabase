package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.Assists;
import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.Room;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.TerminalTransactions;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class Bank implements LoginWindowDelegate, TerminalTransactionsDelegate {
	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public Bank() {
		dbHandler = new DatabaseConnectionHandler();
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
    	Room[] models = dbHandler.getRoomInfo();
    	
    	for (int i = 0; i < models.length; i++) {
    		Room model = models[i];
    		// Room(int roomNumber, int roomFloor, String roomType, String needsCleaning, int numberOfBeds, String hotelAddress)
    		System.out.printf("%-10.10s", model.getRoomNumber());
    		System.out.printf("%-20.20s", model.getRoomFloor());
    		if (model.getRoomType() == null) {
    			System.out.printf("%-20.20s", " ");
    		} else {
    			System.out.printf("%-20.20s", model.getRoomType());
    		}
    		if (model.getNeedsCleaning() == null) {
				System.out.printf("%-15.15s", " ");
			} else {
				System.out.printf("%-15.15s", model.getNeedsCleaning());
			}
    		System.out.printf("%-15.15s", model.getNumberOfBeds());
			System.out.printf("%-20.20s", model.getHotelAddress());
			System.out.println();
    	}
    }

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
    
	/**
	 * Main method called at launch time
	 */
	public static void main(String args[]) {
		Bank bank = new Bank();
		bank.start();
	}
}
