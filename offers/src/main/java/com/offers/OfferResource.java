package com.offers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/offers")
public class OfferResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorldTest() {
		return "Hello World";
	}
	
}