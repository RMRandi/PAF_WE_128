package com;

import model.user;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class UserService
{
	User userObj = new User();
	@GET
	@Path("/")
	//@Produces(MediaType.TEXT_HTML)

	public String readUsers()
	{
		return userObj.readUsers();
		
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("userNumber") String userNumber,
	 @FormParam("name") String name,
	 @FormParam("address") String address,
	 @FormParam("phoneNumber") String phoneNumber,
	 @FormParam("email") String email)
	{
	 String output = userObj.insertUser(userNumber, name, address, phoneNumber, email);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String userID = userObject.get("userID").getAsString();
	 String userNumber = userObject.get("userNumber").getAsString();
	 String name = userObject.get("name").getAsString();
	 String address = userObject.get("address").getAsString();
	 String phoneNumber = userObject.get("phoneNumber").getAsString();
	 String email = userObject.get("email").getAsString();
	 String output = userObj.updateUser(userID, userNumber, name, address, phoneNumber, email);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <userID>
	 String userID = doc.select("userID").text();
	 String output = userObj.deleteUser(userID);
	return output;
	}
}