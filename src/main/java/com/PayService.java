package com;

import model.Pay;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Payment")
public class PayService {
	
Pay p = new Pay();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments() {
		return p.readPayment();
	}
	
	
@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("fullName")String fullName,
								@FormParam("NIC")String NIC,
								@FormParam("amount")String amount,
								@FormParam("date")String date,
								@FormParam("bankName")String bankName,
								@FormParam("debitCard")String debitCard,
								@FormParam("otpNumber")String otpNumber)
{
	String output = p.insertPayment(fullName,NIC,amount,date,bankName,debitCard,otpNumber);
	return output;
}


}
