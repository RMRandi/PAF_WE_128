package com;

import model.User;


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
public class ItemService
{
	Item itemObj = new Item();
	@GET
	@Path("/")
	//@Produces(MediaType.TEXT_HTML)

	public String readItems()
	{
		return itemObj.readItems();
		
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("userNumber") String userNumber,
	 @FormParam("name") String name,
	 @FormParam("address") String address,
	 @FormParam("phoneNumber") String phoneNumber,
	 @FormParam("email") String email)
	{
	 String output = itemObj.insertItem(userNumber, name, address, phoneNumber, email);
	return output;
	}
	
	
}