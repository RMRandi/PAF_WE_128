package model;
import java.sql.*;

public class Interrupt {

	private Connection connect()
	 {
	 Connection con = null;
	 		try{
	 			Class.forName("com.mysql.jdbc.Driver");

	 			//Provide the correct details: DBServer/DBName, Username, password
	 			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", "");
	 		}catch (Exception e){
	 			e.printStackTrace();
	 			}
	 return con;
	 }
	
	
	
	
	
	public String readInterrupt(){
		
		String output = "";
		
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for reading.";
			}
	 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Interrupt ID</th><th>Interrupt Date</th>" + "<th>Interrupt Subject</th>" + "<th>Interrupt Description</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from interrupt";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()){
				String interruptID = Integer.toString(rs.getInt("interruptID"));
				String interruptDate = rs.getString("interruptDate");
				String interruptSubject = rs.getString("interruptSubject");
				String interruptDesc = rs.getString("interruptDesc");
	 
				// Add into the html table
				output += "<tr><td>" + interruptID + "</td>";
				output += "<td>" + interruptDate + "</td>";
				output += "<td>" + interruptSubject + "</td>";
				output += "<td>" + interruptDesc + "</td>";
	 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"+"<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + interruptID
						+ "'>" + "</form></td></tr>";
			}
	 
			con.close();
	 
			// Complete the html table
			output += "</table>";
	 
		}catch (Exception e){
		output = "Error while reading the items.";
		System.err.println(e.getMessage());
	 	}
		
		return output;
	}
	
	
}
