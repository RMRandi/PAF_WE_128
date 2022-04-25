package model;

import java.sql.*;

public class bill {
	
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }

	
	public String readBills()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table  border='1' ><tr style='background-color:#b30000;color:white;font-size:20px'><th>Bill ID</th>"
				+"<th>Name</th>"
				+ "<th>Date</th>"
				+"<th>Account Number</th>"
				+"<th>Pre Reading</th>"
				+"<th>Currant Reading</th>"
				+"<th>Units</th>"
				+"<th>Total</th>"
				+"<th>Update</th><th>Remove</th></tr>";


	 String query = "select * from bills";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String billID = Integer.toString(rs.getInt("billID"));
		 String bName = rs.getString("bName");
		 String bDate = rs.getString("bDate");
		 String accNo = rs.getString("accNo");
		 String preReading = Double.toString(rs.getDouble("preReading"));
		 String currentReading = Double.toString(rs.getDouble("currentReading"));
		 String bUnits = Double.toString(rs.getDouble("bUnits"));
		 String bTotal = Double.toString(rs.getDouble("bTotal"));
	 // Add into the html table
		 output += "<tr style='background-color:#ffe6e6;color:black;font-size:18px'><td>" + billID + "</td>";
		 output += "<td>" + bName + "</td>";
		 output += "<td>" + bDate + "</td>";
		 output += "<td>" + accNo + "</td>";
		 output += "<td>" + preReading + "</td>";
		 output += "<td>" + currentReading + "</td>";
		 output += "<td>" + bUnits + "</td>";
		 output += "<td>" + bTotal + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"+"<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + billID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	
}