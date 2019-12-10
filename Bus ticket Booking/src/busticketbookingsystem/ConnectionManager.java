package busticketbookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= null;
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketbooking","root","");
		if(con!=null)
		{
			return con;
		}
		else
		{
			return null;
		}
}
}
