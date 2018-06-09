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
	public void Hello_World_Is_Returned() {
		Assert.assertEquals("Hello World", this.offers.helloWorldTest());
	}
	
}