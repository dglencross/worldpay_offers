package com.offers;

import org.junit.Assert;
import org.junit.Test;

public class OffersDaoTest {

	@Test
	public void test_Getting_Instance_Of_Dao() {
		Assert.assertNotNull(OffersDao.getInstance());
	}
	
	@Test
	public void test_Getting_Offer_Map() {
		Assert.assertNotNull(OffersDao.getInstance().getModel());
	}
	
}
