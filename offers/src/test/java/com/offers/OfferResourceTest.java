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
		this.offers.deleteAllOffers();
		
		Offer offer = new Offer("my description", null, null);
		
		this.offers.addNewOffer(offer);
	}
	
	@Test
	public void offer_Is_Returned() {
		Offer xml = (Offer) this.offers.getXML("0").getEntity();
		
		Assert.assertEquals("my description", xml.getDescription());
	}
	
	@Test
	public void offer_Is_Returned_As_HTML() {
		Offer html = (Offer) this.offers.getXML("0").getEntity();
		
		Assert.assertEquals("my description", html.getDescription());
	}
	
	@Test
	public void all_offers_are_returned() {
		this.offers = new OfferResource();
		this.offers.deleteAllOffers();
		
		Offer offer = new Offer("my description", null, null);
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
		
		this.offers.newOffer("testApi", 0, 0, 0, null, null);
		
		Assert.assertEquals("testApi", ((Offer)this.offers.getXML("0").getEntity()).getDescription());
	}
	
	@Test
	public void expiry_gets_set() throws IOException {
		this.offers.deleteAllOffers();
		
		this.offers.newOffer("testApi", 1, 2, 3, null, null);
		
		Assert.assertNotNull(((Offer)this.offers.getXML("0").getEntity()).getExpiryDate());
	}
	
	@Test
	public void currency_gets_set() throws IOException {
		this.offers.deleteAllOffers();
		
		this.offers.newOffer("testApi", 1, 2, 3, "USD", null);
		
		Assert.assertEquals("USD", ((Offer)this.offers.getXML("0").getEntity()).getCurrency());
	}
	
	@Test
	public void offer_can_be_cancelled() throws IOException {
		this.offers.deleteAllOffers();
		this.offers.newOffer("testApi", 1, 2, 3, null, null);
		
		Assert.assertEquals(EStatus.LIVE.name(), ((Offer)this.offers.getXML("0").getEntity()).getStatus());
		this.offers.cancelOffer("0", null);
		Assert.assertEquals(EStatus.CANCELLED.name(), ((Offer)this.offers.getXML("0").getEntity()).getStatus());
	}
	
}