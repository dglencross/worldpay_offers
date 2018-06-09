package com.offers;

import org.junit.Assert;
import org.junit.Test;


public class OfferTest {
	
	private Offer offer;
	
	@Test
	public void test_instantiation() {
		this.offer = new Offer();
		Assert.assertNotNull(this.offer);
	}
	
	@Test
	public void test_description_set_on_instantiation() {
		this.offer = new Offer("test");
		Assert.assertEquals("test", this.offer.getDescription());
	}
	
	@Test
	public void test_timestamp_set_on_instantiation() {
		this.offer = new Offer();
		Assert.assertNotNull(this.offer.getTimeCreated());
	}
	
}
