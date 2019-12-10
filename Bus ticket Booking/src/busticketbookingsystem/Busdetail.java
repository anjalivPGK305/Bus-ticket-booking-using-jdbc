package busticketbookingsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Busdetail {

	ConnectionManager con= new ConnectionManager();
	public void details() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("1)Add details\n2)View\n3)Update\n4)Logout\n");
		System.out.println("Enter the choice");
		int n= s.nextInt();
		switch(n)
		{
		case 1:System.out.println("Enter the bus ID:");
		int id= s.nextInt();
		System.out.println("Enter the type of bus:");
		String type= s.next();
		System.out.println("Enter the starting place:");
		String start= s.next();
		System.out.println("Enter the ending place:");
		String end= s.next();
		System.out.println("Enter the busfare:");
		int fare= s.nextInt();
		System.out.println("Enter the number of seats:");
		int seat= s.nextInt();
		System.out.println("Enter the date:");
		String date=s.next();
		System.out.println("Enter the Time:");
		int time= s.nextInt();
		PreparedStatement st= con.getConnection().prepareStatement("insert into Busdetail(BUS_ID,BUS_TYPE,FROM_LOCATION,TO_LOCATION,FARE,NO_OF_SEATS,DATE,Time) values(?,?,?,?,?,?,?,?);");//admin add details of bus to busdetail table
		st.setInt(1,id);//store the value to database.first one is the index and second is the variable for bus id
		st.setString(2, type);
		st.setString(3, start);
		st.setString(4, end);
		st.setInt(5, fare);
		st.setInt(6, seat);
		st.setString(7,date);
		st.setInt(8, time);
		st.executeUpdate();
		System.out.println("Bus details added successfully");
		Busdetail bus= new Busdetail();
		bus.details();
		break;
		case 2:Statement s1= (Statement)con.getConnection().createStatement();//callable statement
		ResultSet r= s1.executeQuery("select * from Busdetail");//for display
		while(r.next())//to read the database elements one by one
		{
			System.out.println("##############################\n");
			System.out.println("BUS ID->"+r.getInt(1));//get busid from database
			System.out.println("BUS TYPE->"+r.getString(2));
			System.out.println("Starting place->"+r.getString(3));
			System.out.println("End place->"+r.getString(4));
			System.out.println("BUS FARE->"+r.getInt(5));
			System.out.println("NO. OF SEATS AVAILABLE->"+r.getInt(6));
			System.out.println("DATE->"+r.getString(7));
			System.out.println("Time->\n"+r.getInt(8));
			System.out.println("###############################");
		}
		Busdetail bus1= new Busdetail();
		bus1.details();
		break;
		case 3:Statement s2= (Statement)con.getConnection().createStatement();//callable statement
		ResultSet r1= s2.executeQuery("select * from Busdetail");//for display
		while(r1.next())//to read the database elements one by one
		{
			System.out.println("********************************");
			System.out.println("BUS ID->"+r1.getInt(1));//get busid from database
			System.out.println("BUS TYPE->"+r1.getString(2));
			System.out.println("********************************");
		}
		Statement s3= (Statement)con.getConnection().createStatement();//callable statement
		ResultSet r3= s3.executeQuery("select * from Busdetail");//for display busdetails
		String upfrom="",upto="";
		int cost=0;
		System.out.println("Do you want to update:(yes/no)");
		String str=s.next();
		if(str.contentEquals("yes"))
		{
		System.out.println("Enter the BUS ID:");
		int number= s.nextInt();
		System.out.println("Enter the start location to update:");
		String startloc= s.next();
		System.out.println("Enter the end location to update:");
		String endloc= s.next();
		System.out.println("Enter the Bus fare to update:");
		int bfare= s.nextInt();
		while(r3.next()) //to read the database elements one by one
		{
			if(number==r3.getInt(1))
			{
				upfrom=r3.getString(3);//getting startplace from busdetail table
				upto=r3.getString(4);//getting endplace from busdetail table
				cost=r3.getInt(5);//getting cost from busdetail table
			}
		}
		PreparedStatement st2= (PreparedStatement) con.getConnection().prepareStatement("update Busdetail SET FROM_LOCATION=?,TO_LOCATION=?,FARE=? WHERE BUS_ID=?");//to update startplace,endplace,busfare
		st2.setString(1, startloc);//new startplace
		st2.setString(2, endloc);//new endplace
		st2.setInt(3,bfare);//new cost
		st2.setInt(4, number);//bus id
		st2.executeUpdate();//execute the update query
		System.out.println("Database updated successfully");
		}
		Busdetail bus2= new Busdetail();
		bus2.details();
		break;
		case 4:System.out.println("Admin logout successfully");
		Busticket b= new Busticket();
		b.choice();
		
		
		}
		
		
		
	}

}
