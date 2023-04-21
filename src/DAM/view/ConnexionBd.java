package DAM.view;

import java.sql.*;

public class ConnexionBd {
	public static Connection databaseLink;

	public static Connection getConnection(){
		String dbName = "projetapparchive";
		String UserName = "root";
		String dbPassword = "";
		String url = "jdbc:mysql://localhost/"+dbName;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url,UserName,dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return databaseLink;

	}
}
