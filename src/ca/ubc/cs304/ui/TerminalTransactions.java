package ca.ubc.cs304.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.Room;

/**
 * The class is only responsible for handling terminal text inputs. 
 */
public class TerminalTransactions {
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	private static final int INVALID_INPUT = Integer.MIN_VALUE;
	private static final int EMPTY_INPUT = 0;
	
	private BufferedReader bufferedReader = null;
	private TerminalTransactionsDelegate delegate = null;

	public TerminalTransactions() {
	}

	/**
	 * Displays simple text interface
	 */ 
	public void showMainMenu(TerminalTransactionsDelegate delegate) {
		this.delegate = delegate;
		
	    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = INVALID_INPUT;
		
		while (choice != 5) {
			System.out.println();
			System.out.println("1. Insert branch");
			System.out.println("2. Delete branch");
			System.out.println("3. Update branch name");
			System.out.println("4. Insert room");
			System.out.println("5. Delete room");
			System.out.println("6. Update room type");
			System.out.println("7. Show room");
			System.out.println("8. Quit");
			System.out.print("Please choose one of the options above: ");

			choice = readInteger(false);

			System.out.println(" ");

			if (choice != INVALID_INPUT) {
				switch (choice) {
				case 1:  
					handleInsertOption(); 
					break;
				case 2:  
					handleDeleteOption(); 
					break;
				case 3: 
					handleUpdateOption();
					break;
				case 4:
					handleInsertRoomOption();
					break;
				case 5:
					handleDeleteRoomOption();
					break;
				case 6:
					handleUpdateRoomOption();
					break;
				case 7:
					delegate.showBranch(); 
					break;
				case 8:
					handleQuitOption();
					break;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
					break;
				}
			}
		}		
	}
	
	private void handleDeleteOption() {
		int branchId = INVALID_INPUT;
		while (branchId == INVALID_INPUT) {
			System.out.print("Please enter the branch ID you wish to delete: ");
			branchId = readInteger(false);
			if (branchId != INVALID_INPUT) {
				delegate.deleteBranch(branchId);
			}
		}
	}

	private void handleDeleteRoomOption() {
		int roomNumber = INVALID_INPUT;
		int roomFloor = INVALID_INPUT;
		while (roomNumber == INVALID_INPUT) {
			System.out.print("Please enter the room number you wish to delete: ");
			roomNumber = readInteger(false);
			while (roomFloor == INVALID_INPUT) {
				System.out.print("Please enter the room floor you wish to delete: ");
				roomFloor = readInteger(false);
				if (roomNumber != INVALID_INPUT && roomFloor != INVALID_INPUT) {
					delegate.deleteRoom(roomNumber, roomFloor);
				}
			}
		}
	}
	
	private void handleInsertOption() {
		int id = INVALID_INPUT;
		while (id == INVALID_INPUT) {
			System.out.print("Please enter the branch ID you wish to insert: ");
			id = readInteger(false);
		}
		
		String name = null;
		while (name == null || name.length() <= 0) {
			System.out.print("Please enter the branch name you wish to insert: ");
			name = readLine().trim();
		}
		
		// branch address is allowed to be null so we don't need to repeatedly ask for the address
		System.out.print("Please enter the branch address you wish to insert: ");
		String address = readLine().trim();
		if (address.length() == 0) {
			address = null;
		}
		
		String city = null;
		while (city == null || city.length() <= 0) {
			System.out.print("Please enter the branch city you wish to insert: ");
			city = readLine().trim();
		}
		
		int phoneNumber = INVALID_INPUT;
		while (phoneNumber == INVALID_INPUT) {
			System.out.print("Please enter the branch phone number you wish to insert: ");
			phoneNumber = readInteger(true);
		}
		
		BranchModel model = new BranchModel(address,
											city,
											id,
											name,
											phoneNumber);
		delegate.insertBranch(model);
	}

	private void handleInsertRoomOption() {
		int roomNumber = INVALID_INPUT;
		while (roomNumber == INVALID_INPUT) {
			System.out.print("Please enter the room number you wish to insert: ");
			roomNumber = readInteger(false);
		}

		int roomFloor = INVALID_INPUT;
		while (roomFloor == INVALID_INPUT) {
			System.out.print("Please enter the room floor you wish to insert: ");
			roomFloor = readInteger(false);
		}

		String hotelAddress = null;
		while (hotelAddress == null || hotelAddress.length() <= 0) {
			System.out.print("Please enter the hotel address you wish to insert: ");
			hotelAddress = readLine().trim();
		}

		// roomType, needsCleaning, numberOfBeds is allowed to be null so we don't need to repeatedly ask for it
		System.out.print("Please enter the room type you wish to insert: ");
		String roomType = readLine().trim();
		if (roomType.length() == 0) {
			roomType = null;
		}
		System.out.print("Please enter if the room needs cleaning; say True or False: ");
		String needsCleaning = readLine().trim();
		if (needsCleaning.length() == 0) {
			needsCleaning = null;
		}

		System.out.print("Please enter the number of beds: ");
		int numberOfBeds = readInteger(true);

		Room model = new Room(roomNumber,
				roomFloor,
				roomType,
				needsCleaning,
				numberOfBeds,
				hotelAddress);
		delegate.insertRoom(model);
	}
	
	private void handleQuitOption() {
		System.out.println("Good Bye!");
		
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("IOException!");
			}
		}
		
		delegate.terminalTransactionsFinished();
	}
	
	private void handleUpdateOption() {
		int id = INVALID_INPUT;
		while (id == INVALID_INPUT) {
			System.out.print("Please enter the branch ID you wish to update: ");
			id = readInteger(false);
		}
		
		String name = null;
		while (name == null || name.length() <= 0) {
			System.out.print("Please enter the branch name you wish to update: ");
			name = readLine().trim();
		}

		delegate.updateBranch(id, name);
	}


	// Room(int roomNumber, int roomFloor, String roomType, String needsCleaning, int numberOfBeds, String hotelAddress)
	private void handleUpdateRoomOption() {
		int roomNumber = INVALID_INPUT;
		int roomFloor = INVALID_INPUT;
		while (roomNumber == INVALID_INPUT) {
			System.out.print("Please enter the room number you wish to update: ");
			roomNumber = readInteger(false);
		}
		while (roomFloor == INVALID_INPUT) {
			System.out.print("Please enter the room floor you wish to update: ");
			roomFloor = readInteger(false);
		}
		String roomType = null;
		while (roomType == null || roomType.length() <= 0) {
			System.out.print("Please enter the room type you wish to update: ");
			roomType = readLine().trim();
		}

		delegate.updateRoom(roomNumber, roomFloor, roomType);
	}

	
	private int readInteger(boolean allowEmpty) {
		String line = null;
		int input = INVALID_INPUT;
		try {
			line = bufferedReader.readLine();
			input = Integer.parseInt(line);
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		} catch (NumberFormatException e) {
			if (allowEmpty && line.length() == 0) {
				input = EMPTY_INPUT;
			} else {
				System.out.println(WARNING_TAG + " Your input was not an integer");
			}
		}
		return input;
	}
	
	private String readLine() {
		String result = null;
		try {
			result = bufferedReader.readLine();
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result;
	}
}
