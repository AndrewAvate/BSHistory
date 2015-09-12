package org.motive.BSHistory.rest.resources;

import java.util.Date;

import org.motive.BSHistory.core.models.entities.BaseStation;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseStationResource extends ResourceSupport {

	private String title;
	private Date creationDate;
	private String description;
	private byte[] application;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getApplication() {
		return application;
	}

	public void setApplication(byte[] application) {
		this.application = application;
	}
	
    public BaseStation toBaseStation() {
    	BaseStation baseStation = new BaseStation();
    	baseStation.setTitle(title);
    	baseStation.setApplication(application);
    	baseStation.setCreationDate(creationDate);
    	baseStation.setDescription(description);
        return baseStation;
    }

}
