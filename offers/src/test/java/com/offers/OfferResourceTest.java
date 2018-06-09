package com.offers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OfferResourceTest {

	private OfferResource offers;
	
	@Before
	public void setUp() {
		this.offers = new OfferResource();
	}
	
	@Test
	public void Offer_Is_Returned() {
		Offer xml = this.offers.getXML();
		
		Assert.assertEquals("my description", xml.getDescription());
	}
	
	@Test
	public void Offer_Is_Returned_As_HTML() {
		Offer html = this.offers.getHTML();
		
		Assert.assertEquals("my description", html.getDescription());
	}
	
}