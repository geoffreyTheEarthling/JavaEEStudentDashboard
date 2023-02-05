import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqliteConnection 
{
	public static Connection dbConnection()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Studentdb.db");
			JOptionPane.showMessageDialog(null, "Connection Successfully Established ...");
			return conn;
		} 
		catch (Exception e) 
		{		
			JOptionPane.showMessageDialog(null, "Error: No Connection ...");
			return null;
		}
	}
}
