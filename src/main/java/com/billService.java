package com;

import model.bill;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Bills")

public class billService {
	
	bill itemObj = new bill();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return itemObj.readBills(); 
	 }

	//Insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("bName") String bname,
	 @FormParam("bDate") String bdate,
	 @FormParam("accNo") String accno,
	 @FormParam("preReading") double prereading,
	 @FormParam("currentReading") double curreading)
	{
	 String output = itemObj.insertBill(bname, bdate, accno, prereading,curreading);
	return output;
	}	

}