package model;

import org.json.JSONObject;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Abstract Class to Identify A Database Table
public abstract class TableHelper {
    public abstract String getTableName();
    public abstract String[] getPrimaryAttributes();
    public abstract void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject primaryKey) throws SQLException;
}
