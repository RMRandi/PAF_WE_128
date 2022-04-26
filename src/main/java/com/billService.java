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
	
	bill biiObj = new bill();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return biiObj.readBills(); 
	 }

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
		String output = biiObj.insertBill(bname, bdate, accno, prereading,curreading);
		return output;
	}

	//update part
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String billData)
	{
		//Convert the input string to a JSON object
		 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
		//Read the values from the JSON object
		 String billID = billObject.get("billID").getAsString();
		 String bName = billObject.get("bName").getAsString();
		 String bDate = billObject.get("bDate").getAsString();
		 String accNo = billObject.get("accNo").getAsString();
		 String preReading = billObject.get("preReading").getAsString();
		 String currentReading = billObject.get("currentReading").getAsString();
		 String output = biiObj.updateBill(billID, bName, bDate, accNo, preReading,currentReading);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData)
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
	
		//Read the value from the element <itemID>
		 String billID = doc.select("billID").text();
		 String output = biiObj.deleteBill(billID);
		return output;
	}

}