package com.offers;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/offers")
public class OfferResource {

	private String id;
	
	public OfferResource() {
	}
	
	public OfferResource(String id) {
		this.id = id;
	}
	
	private Offer getOffer() {
		Offer offer = OffersDao.getInstance().getModel().get(id);
		
		if (offer == null) {
			throw new RuntimeException("Get: Offer with id " + id +  " not found");
		}
		
		return offer;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Offer getXML() {
		return getOffer();
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    public Offer getJSON() {
		return getOffer();
    }
	
	@GET
    @Produces({ MediaType.TEXT_XML })
    public Offer getHTML() {
		return getOffer();
    }

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Offer> getAllOffersAsXml() {
		return  OffersDao.getInstance().getModel().values().stream().collect(Collectors.toList());
	}

	public void addNewOffer(Offer offer) {
		String id = String.valueOf(OffersDao.getInstance().getModel().size());
		OffersDao.getInstance().getModel().put(id, offer);
	}

	public void deleteAllOffers() {
		OffersDao.getInstance().getModel().clear();
	}
	
}
