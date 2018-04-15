package hei.caulier.projet;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Helper {
	
	public static void setIntOrNull(PreparedStatement statement, int column, Integer getter) throws SQLException
	{
	    if (getter!=null) {
	        statement.setInt(column, getter);
	    } else {
	        statement.setNull(column, java.sql.Types.INTEGER);
	    }
	}
	
	public static void setStringOrNull(PreparedStatement statement, int column, String getter) throws SQLException
	{
	    if (getter!=null) {
	        statement.setString(column, getter);
	    } else {
	        statement.setNull(column, java.sql.Types.VARCHAR);
	    }
	}
	
	public static void setFloatOrNull(PreparedStatement statement, int column, Float getter) throws SQLException
	{
	    if (getter!=null) {
	        statement.setFloat(column, getter);
	    } else {
	        statement.setNull(column, java.sql.Types.FLOAT);
	    }
	}
	
	public static void setBooleanOrNull(PreparedStatement statement, int column, Boolean getter) throws SQLException
	{
	    if (getter!=null) {
	        statement.setBoolean(column, getter);
	    } else {
	        statement.setNull(column, java.sql.Types.BIT);
	    }
	}
}
