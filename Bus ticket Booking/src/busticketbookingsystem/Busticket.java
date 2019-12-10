package busticketbookingsystem;

import java.sql.SQLException;
import java.util.Scanner;



public class Busticket {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Busticket b= new Busticket();
		b.choice();
		ConnectionManager con= new ConnectionManager();

	}

	 void choice() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("1)Admin\n2)Customer\n3)Exit\n");
		System.out.println("Enter the choice");
		int ch= s.nextInt();
		switch(ch)
		{
		case 1: Admin ad= new Admin();
		ad.admindetail();
		break;
		case 2: Customer c= new Customer();
		c.customerdetail();
		break;
		case 3: System.out.println("Exited successfully");
			return;
		}
		
		
	}

}
