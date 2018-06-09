package com.offers;

import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for saving, returning offers
 * 
 * @author dave
 *
 */
public class OffersDao {
	
	private Map<String, Offer> offerMap = new HashMap<>();
	private static OffersDao instance;
	
	private OffersDao() {
	}
	
	public static OffersDao getInstance() {
		if (instance == null) {
			instance = new OffersDao();
		}
		
		return instance;
	}
	
	public Map<String, Offer> getModel() {
		return offerMap;
	}
}
