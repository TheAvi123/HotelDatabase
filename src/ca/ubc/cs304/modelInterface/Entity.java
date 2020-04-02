package ca.ubc.cs304.modelInterface;

import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//Abstract Class to Identify A Database Table
public abstract class Entity {

    public abstract int getAttributeCount();
    public abstract TableHelper getTableHelper();
    public abstract void setTupleParametersToStatement(PreparedStatement ps) throws SQLException;
}
