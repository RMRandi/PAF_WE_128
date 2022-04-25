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
	
	
	
	
}
