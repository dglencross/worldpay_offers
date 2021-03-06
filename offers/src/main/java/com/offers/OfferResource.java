package com.offers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class OfferResource {

	@Context
    UriInfo uriInfo;
	
	public OfferResource() {
	}
	
	private Offer getOffer(String id) {
		return  OffersDao.getInstance().getModel().get(id);
	}
	
	@GET
	@Path("/offer/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getXML(@PathParam("id") String id) {
		Offer result = getOffer(id);
		
		if (null == result) {
			String error = "<error>No Offer exists with id " + id + "</error>";
			return Response.status(404).entity(error).build();
		}
		
		return Response.ok(result, MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("/offer/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getJSON(@PathParam("id") String id) {
		Offer result = getOffer(id);
		
		if (null == result) {
			String error = "{ \"error\" : \"No Offer exists with id " + id + "\"}";
			return Response.status(404).entity(error).build();
		}
		
		return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
	
	@GET
	@Path("/offer/{id}")
    @Produces({ MediaType.TEXT_XML })
    public Response getHTML(@PathParam("id") String id) {
		Offer result = getOffer(id);
		
		if (null == result) {
			String error = "No Offer exists with id " + id;
			return Response.status(404).entity(error).build();
		}
		
		return Response.ok(result, MediaType.TEXT_XML).build();
    }

	@GET
	@Path("/allOffers")
	@Produces(MediaType.APPLICATION_XML)
	public List<Offer> getAllOffersAsXml() {
		return  OffersDao.getInstance().getModel().values().stream().collect(Collectors.toList());
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = OffersDao.getInstance().getModel().size();
		return String.valueOf(count);
	}
	
	@POST
	@Path("/new")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newOffer(@QueryParam("description") String description,
    		@QueryParam("expiryInHours") int hours,
    		@QueryParam("expiryInMinutes") int minutes,
    		@QueryParam("expiryInSeconds") int seconds,
    		@QueryParam("currency") String currency,
            @Context HttpServletResponse servletResponse) throws IOException {
		
		Date expiryDate = null;
		if (hours + minutes + seconds > 0) { // i.e. if any of these fields are set - negative makes no sense so won't check for it
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.HOUR_OF_DAY, hours);
			cal.add(Calendar.MINUTE, minutes);
			cal.add(Calendar.SECOND, seconds);
			
			expiryDate = cal.getTime();
		}
		
		Offer offer = new Offer(description, expiryDate, currency);
		addNewOffer(offer);
		
		return Response.ok("Offer created with ID: " + offer.getId(), MediaType.TEXT_HTML).build();
    }
	
	@PUT
    @Consumes({MediaType.TEXT_XML, MediaType.TEXT_HTML})
	@Path("/cancel/{id}")
    public Response cancelOffer(@PathParam("id") String id) throws IOException {
		Offer offer = getOffer(id);
		
		if (null == offer) {
			String error = "No Offer exists with id " + id;
			return Response.status(404).entity(error).build();
		}
		
		offer.cancel();
		
		return Response.ok("Offer cancelled with ID: " + offer.getId(), MediaType.TEXT_HTML).build();
    }
	
	// IDs are just incremented
	private String getNextId() {
		return String.valueOf(OffersDao.getInstance().getModel().size());
	}
	
	public void addNewOffer(Offer offer) {
		String id = getNextId();
		offer.setId(id);
		OffersDao.getInstance().getModel().put(id, offer);
	}

	public void deleteAllOffers() {
		OffersDao.getInstance().getModel().clear();
	}
	
}
