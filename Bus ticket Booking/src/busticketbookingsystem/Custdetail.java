package busticketbookingsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Custdetail {

	ConnectionManager con= new ConnectionManager();
	public void details() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("1)View busdetails\n2)Book Ticket\n3)View Booking\n4)Cancel\n5)Logout\n");
		System.out.println("Enter the choice");
		int num= s.nextInt();

		switch(num)
		{
		
		case 1:Statement s1= (Statement)con.getConnection().createStatement();//callable statement
		ResultSet r= s1.executeQuery("select * from Busdetail");//for display budetail table
		while(r.next())
		{
			System.out.println("##############################\n");
			System.out.println("BUS ID->"+r.getInt(1));//to get the value of busid from database
			System.out.println("BUS TYPE->"+r.getString(2));
			System.out.println("Starting place->"+r.getString(3));
			System.out.println("End place->"+r.getString(4));
			System.out.println("BUS FARE->"+r.getInt(5));
			System.out.println("NO. OF SEATS AVAILABLE->"+r.getInt(6));
			System.out.println("DATE->"+r.getString(7));
			System.out.println("Time->"+r.getInt(8));
			System.out.println("###############################");
		}
		Custdetail cu1= new Custdetail();
		cu1.details();
		break;
		case 2:

			Statement s2= (Statement)con.getConnection().createStatement();//callable statement
			ResultSet r1= s2.executeQuery("select * from Busdetail");//for display
			System.out.println("Enter the Booking ID:");
			int b= s.nextInt();
			System.out.println("Enter the BUS ID:");
			int id1=s.nextInt();			
		    System.out.println("Enter the start location:");
		    String astart= s.next();
		    System.out.println("Enter the end location:");
		    String aend=s.next();
		    System.out.println("Enter the date of booking:");
			String da= s.next();
			System.out.println("Enter the time:");
			int time= s.nextInt();
			System.out.println("Enter the no. of customers:");
		    int n1=s.nextInt();
		    int cost2=0;
		    int seat1=0;
		    while(r1.next())//to read the database elements one by one
		    {
		    	if(id1==r1.getInt(1)) {//check given id and bus id equal or no
		    	cost2=r1.getInt(5)*n1;//total cost for n1 no. of persons
		    	seat1=r1.getInt(6);//to get no. of seats
		    	}
		    	
		    }
		    int avail=0;
		    System.out.println("Busfare->"+cost2);
			System.out.println("Enter 1 to confirm booking(1)");//ask the customer for confirmation
			int a= s.nextInt();
			if(a==1)
			{
				System.out.println("Your booking is confirmed");
				System.out.println("Thank you....!!!");
			}
			PreparedStatement st1= con.getConnection().prepareStatement("insert into Booking(BOOKING_ID,BUS_ID,START,END,DATE,TIME,NO_OF_PERSON,COST) values(?,?,?,?,?,?,?,?)");//booking details added to booking table
			st1.setInt(1, b);//store the value in database
			st1.setInt(2, id1);
			st1.setString(3, astart);
			st1.setString(4, aend);
			st1.setString(5,da);
			st1.setInt(6, time);
			st1.setInt(7, n1);
			st1.setInt(8,cost2);
			st1.executeUpdate();
			System.out.println("Booking details added successfully");
		    avail=seat1-n1;
		    PreparedStatement st= (PreparedStatement) con.getConnection().prepareStatement("update Busdetail SET NO_OF_SEATS=? WHERE BUS_ID=?");//update the no. of seats in busdetail
		    st.setInt(1,avail);//store the value to database
		    st.setInt(2, id1);
		    st.executeUpdate();
		    Custdetail ct= new Custdetail();
		    ct.details();
		    break;
		case 3:Statement s3= (Statement)con.getConnection().createStatement();//callable statement
		ResultSet r2= s3.executeQuery("select * from Booking");//to display booking details
		while(r2.next())
		{
			System.out.println("##############################\n");
			System.out.println("BOOKING_ID->"+r2.getInt(1));//get the busid from database
			System.out.println("BUS ID->"+r2.getInt(2));
			System.out.println("START->"+r2.getString(3));
			System.out.println("END->"+r2.getString(4));
			System.out.println("DATE->"+r2.getString(5));
			System.out.println("TIME->"+r2.getInt(6));
			System.out.println("NO. OF PERSON->"+r2.getInt(7));
			System.out.println("COST->\n"+r2.getString(8));
			System.out.println("###############################");
		}
		Custdetail cu= new Custdetail();
	    cu.details();
	    break;
		case 4:System.out.println("Enter the booking ID");
		int book= s.nextInt();
		System.out.println("Enter the time of cancellation:");
		int t= s.nextInt();
		int refund=0;
		Statement s4= (Statement)con.getConnection().createStatement();//callable statement
		ResultSet r3= s4.executeQuery("select * from Booking");//for display
		Statement s5= (Statement)con.getConnection().createStatement();//callable statement
		ResultSet r4= s5.executeQuery("select * from Busdetail");//for display
		String can="";
		System.out.println("Enter bus ID:");
		int bi= s.nextInt();
		int t1=0;
		while(r4.next())//to read the database elements one by one
		{
			if(bi==r4.getInt(1))
			{
				t1=r4.getInt(8);
			}
		}
		while(r3.next())//to read the database elements one by one
		{
			if(book==r3.getInt(1))//check given id and booking id equal or not
			{
				if(t1-t>=2)
				{
					refund=r3.getInt(8)-(r3.getInt(7)*100);
					System.out.println("Refund Amount->"+refund);
				}
				else
				{
					System.out.println("Time over. No refund..");
				}
				
			}
		}
		System.out.println("Do you want to cancel?(yes/no)");
		can=s.next();
		if(can.contentEquals("yes"))
		{
			PreparedStatement st4= (PreparedStatement) con.getConnection().prepareStatement("delete from Booking where BOOKING_ID=?");//to cancel the ticket
			st4.setInt(1, book);
			st4.executeUpdate();
			System.out.println("Ticket cancelled successfully");
		}
		Custdetail cu2= new Custdetail();
	    cu2.details();
		break;
		case 5: System.out.println("Customer logout successfully");
			Busticket b1= new Busticket();
			b1.choice();		
		}
		
	}

}
