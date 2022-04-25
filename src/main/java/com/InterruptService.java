package com;
import java.sql.*;

import model.Interrupt;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Interrupt")
public class InterruptService{
	Interrupt itemObj = new Interrupt();


	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems(){
		return itemObj.readInterrupt();
	}
	
@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertInterrupt(@FormParam("interruptDate") String interruptDate, 
	 @FormParam("interruptSubject") String interruptSubject, 
	 @FormParam("interruptDesc") String interruptDesc)
	
	{ 
	 String output = itemObj.insertInterrupt(interruptDate, interruptSubject, interruptDesc); 
	return output; 
	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData){
		
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String ID = itemObject.get("interruptID").getAsString();
	 String date = itemObject.get("interruptDate").getAsString();
	 String sub = itemObject.get("interruptSubject").getAsString();
	 String desc = itemObject.get("interruptDesc").getAsString();

	 
	 String output = itemObj.updateItem(ID,date, sub, desc);
	 return output;
	 
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInterrupt(String interruptData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(interruptData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String itemID = doc.select("itemID").text();
	 String output = itemObj.deleteInterrupt(itemID);
	return output;
	}

	
	
	
	
	
	
	
}
