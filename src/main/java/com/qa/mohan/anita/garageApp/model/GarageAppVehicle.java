package com.qa.mohan.anita.garageApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vehicle")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate", "lastModified"}, allowGetters = true)
public class GarageAppVehicle implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Enumerated(EnumType.STRING)
	private VehicleType type;
	
	@NotBlank
	private String size;
	
	private String colour;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date creationDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastModified;
	
	
	
	
	public GarageAppVehicle( String vehicletype, String size, String colour) {

		this.type = VehicleType.valueOf(vehicletype.toUpperCase());
		this.size = size;
		this.colour = colour;
		
	}
	
	public GarageAppVehicle() {
		 
	}
	
	public Long getId() { 
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type.toString();
	}
	
	public void setType(String type) {
		VehicleType vehicletype = VehicleType.valueOf(type.toUpperCase());
		this.type = vehicletype;
	}
	
	public String getSize() {
		return size; 
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	 
	public String getColour() {
		return colour;
	}
	
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public Date getCreationDate() {
	return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Date getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}


}
