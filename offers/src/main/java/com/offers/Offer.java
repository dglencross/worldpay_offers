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
	
	public Offer() {
		this(null);
	}
	
	public Offer(String description) {
		this.description = description;
		this.timeCreated = Date.from(Instant.now());
		this.status = EStatus.LIVE;
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
		return this.status.name();
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void cancel() {
		setStatus(EStatus.CANCELLED);
	}
	
	public void setStatus(EStatus status) {
		this.status = status;
	}
	
}
