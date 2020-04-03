package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//Abstract Class to Identify A Database Table
public abstract class Table {
    public abstract int getAttributeCount();
    public abstract TableHelper getTableHelper();
    public abstract void setTupleParametersToStatement(PreparedStatement ps) throws SQLException;
}
