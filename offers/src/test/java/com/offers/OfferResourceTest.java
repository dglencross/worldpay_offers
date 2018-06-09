package com.offers;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OfferResourceTest {

	private OfferResource offers;
	
	@Before
	public void setUp() {
		this.offers = new OfferResource();
		
		Offer offer = new Offer("my description");
		
		this.offers.addNewOffer(offer);
	}
	
	@Test
	public void offer_Is_Returned() {
		Offer xml = this.offers.getXML("0");
		
		Assert.assertEquals("my description", xml.getDescription());
	}
	
	@Test
	public void offer_Is_Returned_As_HTML() {
		Offer html = this.offers.getHTML("0");
		
		Assert.assertEquals("my description", html.getDescription());
	}
	
	@Test
	public void all_offers_are_returned() {
		this.offers = new OfferResource();
		this.offers.deleteAllOffers();
		
		Offer offer = new Offer("my description");
		this.offers.addNewOffer(offer);
		this.offers.addNewOffer(offer);
		this.offers.addNewOffer(offer);
		
		List<Offer> offers = this.offers.getAllOffersAsXml();
		
		Assert.assertNotNull(offers);
		Assert.assertEquals(3, offers.size());
	}
	
	@Test
	public void add_new_offer_via_rest_method() throws IOException {
		this.offers.deleteAllOffers();
		
		this.offers.newOffer("testApi", null);
		
		Assert.assertEquals("testApi", this.offers.getXML("0").getDescription());
	}
	
}