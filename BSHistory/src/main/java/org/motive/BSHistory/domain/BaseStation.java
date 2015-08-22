package org.motive.BSHistory.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;


@Entity
@Table(name = "base_station")
public class BaseStation implements Serializable {
	private static final long serialVersionUID = -8859952724057130630L;

	private Long id;
	private int version;
	private String title;
	private Date creationDate;
	private String description;
	private byte[] application;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name = "BS_TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name = "APPLICATION")
	public byte[] getApplication() {
		return application;
	}

	public void setApplication(byte[] application) {
		this.application = application;
	}

	@Transient
	public String getCreateDateString() {
		String createDateString = "";
		if (this.getCreationDate() != null) {
			createDateString = SimpleDateFormat
					.getDateInstance(SimpleDateFormat.MEDIUM)
					.format(getCreationDate());
		}
		return createDateString;
	}

	public String toString() {
		return "Base Station - Id: " + getId() + ", title: " + getTitle()
				+ ", creation date: "
				+ getCreateDateString() + ", Description: " + (getDescription()==null?"-":getDescription());
	}

}
