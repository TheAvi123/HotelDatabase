package delegates;

import model.Table;
import model.TableHelper;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TransactionsHandlerDelegate {
	//Transactions Functions
	public void transactionsComplete();
	//SQL DDL Functions
	public void insertTuple(Table table);
	public void deleteTuple(TableHelper table, JSONObject primaryKey);
	public void updateTuples(TableHelper tableHelper, JSONObject setKeys, JSONObject whereKeys);
	public ArrayList<JSONObject> getTuples(String tableName);
}
