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

//Insert method
public String insertBill(String bName, String bDate, String accNo,double prereading,double currentreading)
	 {
	 String output = "";
	 double preReading;
	 double currentReading;
	 double bUnits;
	 double bTotal;
	 try
	 {
	 Connection con = connect();
	 //assigned to variable
	 preReading = prereading;
	 currentReading = currentreading;
	 //calculation
	 bUnits = (currentReading-preReading);
	 
	// IF ELSE statement
	 if(bUnits<=80) {
		 bTotal = (bUnits*5)+300;
	 }
	 else {
		 bTotal = (bUnits*12)+300;
	 } 
	 
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into bills (`billID`,`bName`,`bDate`,`accNo`,`preReading`,`currentReading`,`bUnits`,`bTotal`)"
	 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, bName);
	 preparedStmt.setString(3, bDate);
	 preparedStmt.setString(4, accNo);
	 preparedStmt.setDouble(5, prereading);
	 preparedStmt.setDouble(6, currentreading);
	 preparedStmt.setDouble(7, bUnits);
	 preparedStmt.setDouble(8, bTotal);

	 

	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 
		 e.printStackTrace();
		 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	
//Read from the database	
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

//Update
public String updateBill(String ID, String bName, String bDate, String accNo, String prereading, String curreading)
	{
		String output = "";
		 try
		 {
			 //assigned to variables 
			 Double preReading = Double.parseDouble(prereading);
			 Double currentReading = Double.parseDouble(curreading);
			 //Calculation
			 Double bUnits = (currentReading-preReading);
			 Double bTotal = null;
			 
			 //IF ELSE statement
			 if(bUnits<=80) {
				 bTotal = (bUnits*5)+300;
			 }
			 else {
				 bTotal = (bUnits*12)+300;
			 }
			 
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE bills SET bName=?,bDate=?,accNo=?,preReading=?,currentReading=?,bUnits=?,bTotal=?  WHERE billID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, bName);
		 preparedStmt.setString(2, bDate);
		 preparedStmt.setString(3, accNo);
		 preparedStmt.setDouble(4, Double.parseDouble(prereading));
		 preparedStmt.setDouble(5, Double.parseDouble(curreading));
		 preparedStmt.setDouble(6, bUnits);
		 preparedStmt.setDouble(7, bTotal);
		  preparedStmt.setInt(8, Integer.parseInt(ID));
		 
		
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
//Delete
	public String deleteBill(String billID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from bills where billID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(billID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
}