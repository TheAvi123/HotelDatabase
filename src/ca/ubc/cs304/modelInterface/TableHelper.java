package ca.ubc.cs304.modelInterface;

import org.json.JSONObject;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Abstract Class to Identify A Database Table
public abstract class TableHelper {

    public abstract String getTableName();
    public abstract String[] getPrimaryAttributes();
    public abstract void printTupleInfo(Table tuple);
    public abstract void setPrimaryStatementParameters(PreparedStatement ps, JSONObject primaryKey) throws SQLException;
}
