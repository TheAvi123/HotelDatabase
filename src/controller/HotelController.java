package controller;

import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import delegates.TransactionsHandlerDelegate;
import model.Table;
import model.TableHelper;
import tools.Condition;
import userInterface.LoginWindow;
import userInterface.TransactionsHandler;
import org.json.JSONObject;

import java.util.ArrayList;

public class HotelController implements LoginWindowDelegate, TransactionsHandlerDelegate {

	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public HotelController() {
		dbHandler = new DatabaseConnectionHandler(this);
	}

	// Entry Point To The Program
	public static void main(String args[]) {
		HotelController hotelController = new HotelController();
		hotelController.startProgram();
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
			TransactionsHandler transactionsHandler = new TransactionsHandler(this);
			transactionsHandler.showMainMenu();
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

	public void insertTuple(Table table) {
		dbHandler.insertTable(table);
	}

	public void deleteTuple(TableHelper tableHelper, JSONObject primaryKey) {
		dbHandler.deleteTable(tableHelper, primaryKey);
	}

	public void updateTuples(TableHelper tableHelper, JSONObject setKeys, JSONObject whereKeys) {
		dbHandler.updateTable(tableHelper, setKeys, whereKeys);
	}

	public ArrayList<JSONObject> getTuples(String tableName) {
		return dbHandler.getTableTuples(tableName);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ArrayList<JSONObject> selectionQuery(TableHelper helper, Condition condition) {
		return dbHandler.selectionQuery(helper, condition);
	}

	public ArrayList<JSONObject> projectionQueryHotel(Boolean[] attributesToShow) {
		return dbHandler.projectionHotel(attributesToShow);
	}
}
