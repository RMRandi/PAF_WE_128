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
	
	public String insertInterrupt(String idate, String isub, String idesc)
	 {
		String output = "";
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for inserting.";
				}
			
			
	 // create a prepared statement
	 String query = " insert into interrupt(`interruptID`,`interruptDate`,`interruptSubject`,`interruptDesc`)" 
	 + " values (?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, idate);
	 preparedStmt.setString(3, isub);
	 preparedStmt.setString(4, idesc);
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";}catch (Exception e){
		 	e.printStackTrace();
		 	//
		 	output = "Error while inserting the item.";
		 	System.err.println(e.getMessage());
	 		}return output;
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
	
//	UPDATE
	public String updateItem(String ID, String date, String sub, String desc){
		
		String output = "";
		
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for updating.";
		}
			
	 
	 // create a prepared statement
	 String query = "UPDATE interrupt SET interruptDate=?,interruptSubject=?,interruptDesc=? WHERE interruptID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setString(1, date);
	 preparedStmt.setString(2, sub);
	 preparedStmt.setString(3, desc);
	 preparedStmt.setInt(4, Integer.parseInt(ID));
	 
	 // execute the statement
	 preparedStmt.execute();
	 
	 con.close();
	 output = "Updated successfully";
	 }catch (Exception e){
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 	}
	 return output;
	 }
	
	
	
	public String deleteInterrupt(String itemID){
		String output = "";
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
			
	 // create a prepared statement
	 String query = "delete from items where itemID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);

	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(itemID));
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }catch (Exception e){
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
}
