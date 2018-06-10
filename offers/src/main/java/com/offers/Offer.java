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
	
	public Offer(String description, Date offerExpiry, String currency) {
		super();
		this.description = description;
		this.timeCreated = Date.from(Instant.now());
		this.status = EStatus.LIVE;
		this.expiryDate = offerExpiry;
		this.currency = currency;
	}
	
	// Items exposed to the end user
	
	@XmlElement
	public String getDescription() {
		return this.description;
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
	
	@XmlElement
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	@XmlElement
	public String getCurrency() {
		return currency;
	}
	
	// End of items exposed to the end user
	
	public void setId(String id) {
		this.id = id;
	}

	public void cancel() {
		if (getStatus().equals(EStatus.LIVE.name())) {
			setStatus(EStatus.CANCELLED);
		}
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStatus(EStatus status) {
		this.status = status;
	}

}
