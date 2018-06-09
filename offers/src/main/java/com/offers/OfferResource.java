package com.offers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/offers")
public class OfferResource {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Offer getXML() {
		Offer offer = new Offer("my description");
		
		return offer;
	}
	
}
