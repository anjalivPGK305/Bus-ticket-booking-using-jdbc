package busticketbookingsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer {

	ConnectionManager con= new ConnectionManager();
	public void customerdetail() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the Username:");
		String user1= s.next();
		System.out.println("Enter the password:");
		String pass1= s.next();
		Statement s1= (Statement)con.getConnection().createStatement();//callable statement
		ResultSet r= s1.executeQuery("select * from Customer");//for display
		while(r.next())
		{
			String u1= r.getString(1);
			String p1= r.getString(2);
			if(user1.equals(u1) && pass1.equals(p1))
			{
				System.out.println("Successfully verified");
				Custdetail cust= new Custdetail();
				cust.details();
			}
			else
			{
				System.out.println("Invalid username or password");
			}
			Custdetail cust= new Custdetail();
			cust.details();
				
				
			}
		
		
	}

}
