package com.offers;

import java.time.Instant;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class to represent one offer
 * @author dave
 */
@XmlRootElement
public class Offer {

	private String description;
	private Date timeCreated;
	
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
