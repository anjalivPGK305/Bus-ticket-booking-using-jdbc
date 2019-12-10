package busticketbookingsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Admin {
	ConnectionManager con= new ConnectionManager();

	public void admindetail() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the Username:");
		String user= s.next();
		System.out.println("Enter the password:");
		String pass= s.next();
		Statement s1= (Statement)con.getConnection().createStatement();//callable statement
		ResultSet r= s1.executeQuery("select * from Admin");//for display
		while(r.next())
		{
			String u= r.getString(1);
			String p= r.getString(2);
			if(user.equals(u) && pass.equals(p))
			{
				System.out.println("Successfully verified");
				Busdetail bus= new Busdetail();
				bus.details();
			}
			else
			{
				System.out.println("Invalid username or password");
			}
			Busticket b= new Busticket();
			b.choice();	
			}
		
	}

}
