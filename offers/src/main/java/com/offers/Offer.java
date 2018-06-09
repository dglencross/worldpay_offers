package com.offers;

import java.time.Instant;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
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
	
	@XmlElement
	public String getDescription() {
		return this.description;
	}
	
	@XmlElement
	public Date getTimeCreated() {
		return this.timeCreated;
	}
	
}
