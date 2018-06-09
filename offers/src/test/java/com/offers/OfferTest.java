package com.offers;

import java.time.Instant;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OfferTest {
	
	private Offer offer;
	
	@Before
	public void SetUp() {
		this.offer = new Offer();
	}
	
	@Test
	public void test_instantiation() {
		Assert.assertNotNull(this.offer);
	}
	
	@Test
	public void test_description_set_on_instantiation() {
		this.offer = new Offer("test", null);
		Assert.assertEquals("test", this.offer.getDescription());
	}
	
	@Test
	public void test_timestamp_set_on_instantiation() {
		Assert.assertNotNull(this.offer.getTimeCreated());
	}
	
	@Test
	public void set_and_get_id() {
		this.offer.setId("123");
		Assert.assertEquals("123", this.offer.getId());
	}
	
	@Test
	public void new_offer_is_live() {
		Assert.assertEquals(EStatus.LIVE.name(), this.offer.getStatus());
	}
	
	@Test
	public void canceled_offer_has_correct_status() {
		this.offer.cancel();
		
		Assert.assertEquals(EStatus.CANCELLED.name(), this.offer.getStatus());
	}
	
	@Test
	public void offer_expires_after_expiry_date() {
		// pick a time in the past
		this.offer = new Offer("desc", Date.from(Instant.parse("2007-12-03T10:15:30.00Z")));
		
		Assert.assertEquals(EStatus.EXPIRED.name(), this.offer.getStatus());
	}
	
}
