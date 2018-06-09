package com.offers;

import java.time.Instant;
import java.util.Date;

/**
 * Class to represent one offer
 * @author dave
 *
 */
public class Offer {

	public String description;
	public Date timeCreated;
	
	public Offer() {
		this(null);
	}
	
	public Offer(String description) {
		this.description = description;
		this.timeCreated = Date.from(Instant.now());
	}
	
	public String GetDescription() {
		return this.description;
	}
	
	public Date GetTimeCreated() {
		return this.timeCreated;
	}
	
}
