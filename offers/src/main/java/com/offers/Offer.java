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
	private String id;
	private EStatus status;
	private String currency;
	private Date expiryDate;
	
	public Offer() {
		this(null, null);
	}
	
	public Offer(String description, Date offerExpiry) {
		this.description = description;
		this.timeCreated = Date.from(Instant.now());
		this.status = EStatus.LIVE;
		this.expiryDate = offerExpiry;
	}
	
	@XmlElement
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement
	public Date getTimeCreated() {
		return this.timeCreated;
	}
	
	@XmlElement
	public String getId() {
		return this.id;
	}
	
	@XmlElement
	public String getStatus() {
		// check if we have expired first
		if (null != expiryDate && expiryDate.before(Date.from(Instant.now()))) {
			this.status = EStatus.EXPIRED;
		}
		
		return this.status.name();
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void cancel() {
		if (getStatus().equals(EStatus.LIVE.name())) {
			setStatus(EStatus.CANCELLED);
		}
	}
	
	public void setStatus(EStatus status) {
		this.status = status;
	}

	@XmlElement
	public Date getExpiryDate() {
		return this.expiryDate;
	}
	
}
