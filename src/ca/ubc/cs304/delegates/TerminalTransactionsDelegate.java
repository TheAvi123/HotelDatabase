package ca.ubc.cs304.delegates;

import ca.ubc.cs304.modelInterface.Entity;
import ca.ubc.cs304.modelInterface.TableHelper;
import org.json.JSONObject;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {

	public void terminalTransactionsFinished();

	//SQL DDL Functions
	public void insertTable(Entity table);
	public void deleteTable(TableHelper table, JSONObject primaryKey);
	public void updateTable(TableHelper tableHelper, JSONObject setKeys, JSONObject whereKeys);
	public void showTable(String tableName);

	//Branch Functions
	//public void insertBranch(BranchModel model);
	//public void deleteBranch(int branchId);
	//public void updateBranch(int branchId, String name);
	public void showBranch();

	//Room Functions
	//public void insertRoom(Room model);
	//public void deleteRoom(int roomNumber, int roomFloor);
	//public void updateRoom(int roomNumber, int roomFloor, String roomType);
	public void showRoom();
}
