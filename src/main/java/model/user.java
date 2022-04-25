package model;
import java.sql.*;
public class user
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/electro", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertItem(String number, String name, String address, String phone, String email)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into user (`userID`,`userNumber`,`name`,`address`,`phoneNumber`, `email`)"
 + " values (?, ?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, number);
 preparedStmt.setString(3, name);
 preparedStmt.setString(4, address);
 preparedStmt.setString(5, phone);
 preparedStmt.setString(6, email);
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
public String readItems()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output ="<table border='1'>"+
			"<tr><th>User ID</th><th>User Number</th>"+
			"<th>Name</th><th>Address</th>"+
			"<th>Phone Number</th><th>Email</th></tr>";

 String query = "select * from user";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String userID = Integer.toString(rs.getInt("userID"));
 String userNumber = rs.getString("userNumber");
 String name = rs.getString("name");
 String address = rs.getString("address");
 String phoneNumber = rs.getString("phoneNumber");
 String email = rs.getString("email");
 // Add into the html table
 output += "<tr><td>" + userID + "</td>";
 output += "<td>" + userNumber + "</td>";
 output += "<td>" + name + "</td>";
 output += "<td>" + address + "</td>";
 output += "<td>" + phoneNumber + "</td>";
 output += "<td>" + email + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='itemID' type='hidden' value='" + userID
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