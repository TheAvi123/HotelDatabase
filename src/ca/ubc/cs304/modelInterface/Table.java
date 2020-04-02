package ca.ubc.cs304.modelInterface;

import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//Abstract Class to Identify A Database Table
public abstract class Table {

    public abstract int getAttributeCount();
    public abstract void setAllStatementParameters(PreparedStatement ps) throws SQLException;
}
