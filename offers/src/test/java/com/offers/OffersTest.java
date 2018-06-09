package com.offers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OffersTest {

	private Offers offers;
	
	@Before
	public void setUp() {
		this.offers = new Offers();
	}
	
	@Test
	public void Hello_World_Is_Returned() {
		Assert.assertEquals("Hello World", this.offers.helloWorldTest());
	}
	
}