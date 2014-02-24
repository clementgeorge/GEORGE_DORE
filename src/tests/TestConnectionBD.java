package tests;

import java.sql.SQLException;

import bd.DBStatic;
import bd.exceptions.NoSqlConnectionException;

public class TestConnectionBD {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		DBStatic.getMySQLConnection();
	}
}
