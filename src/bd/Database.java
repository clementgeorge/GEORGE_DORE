package bd;

import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.SQLException;

import bd.exceptions.MongoDBConnexionException;
import bd.exceptions.MySqlConnexionException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mysql.jdbc.Connection;

public class Database {

	/**
	 * 
	 * @return une connection ˆ la base MySql. 
	 * @throws MySqlConnexionException si la connexion ne se fait pas correctement
	 */
	public static Connection getMySQLConnection() throws MySqlConnexionException{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return (Connection)DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/" +DBStatic.mysql_db, DBStatic.mysql_username,DBStatic.mysql_password);  
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			throw new MySqlConnexionException();
		}
		

	}
	
	/**
	 * 
	 * @param collection : le nom de la collection qu'on souhaite obtenir
	 * @return une connection sur la collection base
	 * @throws MongoDBConnexionException 
	 */
	public static DBCollection getMongoCollection(String collection) throws MongoDBConnexionException {
		try {
			Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
			DB db = m.getDB(DBStatic.mongo_name);
			DBCollection coll = db.getCollection(collection);
			return coll;
		} catch (UnknownHostException e) {
			throw new MongoDBConnexionException();
		}
	}

}
