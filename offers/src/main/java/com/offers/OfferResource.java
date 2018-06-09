package com.offers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.servlet.http.HttpServletResponse;

@Path("/offers")
public class OfferResource {

	@Context
    UriInfo uriInfo;
	
	public OfferResource() {
	}
	
	private Offer getOffer(String id) {
		Offer offer = OffersDao.getInstance().getModel().get(id);
		
		if (offer == null) {
			throw new RuntimeException("Get: Offer with id " + id +  " not found");
		}
		
		return offer;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Offer getXML(@PathParam("id") String id) {
		return getOffer(id);
	}
	
	@GET
	@Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Offer getJSON(@PathParam("id") String id) {
		return getOffer(id);
    }
	
	@GET
	@Path("{id}")
    @Produces({ MediaType.TEXT_XML })
    public Offer getHTML(@PathParam("id") String id) {
		return getOffer(id);
    }

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Offer> getAllOffersAsXml() {
		return  OffersDao.getInstance().getModel().values().stream().collect(Collectors.toList());
	}
	
	@POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newOffer(@FormParam("id") String id,
            @FormParam("description") String description,
            @Context HttpServletResponse servletResponse) throws IOException {
		Offer offer = new Offer(description);
		offer.setId(id);
        OffersDao.getInstance().getModel().put(id, offer);

        servletResponse.sendRedirect("../create_offer.html");
    }
	
	private Response putAndGetResponse(Offer offer) {
        Response res;
        if(OffersDao.getInstance().getModel().containsKey(offer.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        OffersDao.getInstance().getModel().put(offer.getId(), offer);
        return res;
    }

	public void addNewOffer(Offer offer) {
		String id = String.valueOf(OffersDao.getInstance().getModel().size());
		OffersDao.getInstance().getModel().put(id, offer);
	}

	public void deleteAllOffers() {
		OffersDao.getInstance().getModel().clear();
	}
	
}
