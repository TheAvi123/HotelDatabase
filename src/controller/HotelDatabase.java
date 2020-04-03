package controller;

import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import delegates.TransactionsHandlerDelegate;
import model.Table;
import model.TableHelper;
import userInterface.LoginWindow;
import userInterface.TransactionsHandler;
import org.json.JSONObject;

import java.util.ArrayList;

public class HotelDatabase implements LoginWindowDelegate, TransactionsHandlerDelegate {

	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public HotelDatabase() {
		dbHandler = new DatabaseConnectionHandler();
	}

	// Entry Point To The Program
	public static void main(String args[]) {
		HotelDatabase hotelDatabase = new HotelDatabase();
		hotelDatabase.startProgram();
	}

	private void startProgram() {
		loginWindow = new LoginWindow();
		loginWindow.showFrame(this);
	}

	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);
		if (didConnect) {
			// Once connected, remove login window and start text transaction flow
			loginWindow.dispose();
			TransactionsHandler transactionsHandler = new TransactionsHandler();
			transactionsHandler.showMainMenu(this);
		} else {
			loginWindow.handleLoginFailed();
			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}

	public void transactionsComplete() {
		dbHandler.close();
		dbHandler = null;
		System.exit(0);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void insertTable(Table table) {
		dbHandler.insertTable(table);
	}

	public void deleteTable(TableHelper tableHelper, JSONObject primaryKey) {
		dbHandler.deleteTable(tableHelper, primaryKey);
	}

	public void updateTable(TableHelper tableHelper, JSONObject setKeys, JSONObject whereKeys) {
		dbHandler.updateTable(tableHelper, setKeys, whereKeys);
	}

	public void showTable(String tableName) {
		ArrayList<Table> tuples = dbHandler.getTableTuples(tableName);
		for (int i = 0; i < tuples.size(); i++) {
			Table tuple = tuples.get(i);
			//tuple.getTableHelper().printTupleInfo(tuple);
		}
	}
}
