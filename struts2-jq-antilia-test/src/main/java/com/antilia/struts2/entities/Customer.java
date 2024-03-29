package com.antilia.struts2.entities;

// Generated Apr 23, 2008 5:11:37 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = {
		"name", "address" }))
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address", nullable = false)
	private Address address;
	
	@Column(name = "name", nullable = false, length = 500)
	private String name;
	
	@Column(name = "status", length = 20, nullable = false)
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Project> projects = new HashSet<Project>(0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Chargerate> chargerates = new HashSet<Chargerate>(0);

	public Customer() {
	}

	public Customer(long id, Address address, String name) {
		this.id = id;
		this.address = address;
		this.name = name;
	}

	public Customer(long id, Address address, String name, String status,
			Set<Project> projects, Set<Chargerate> chargerates) {
		this.id = id;
		this.address = address;
		this.name = name;
		this.status = status;
		this.projects = projects;
		this.chargerates = chargerates;
	}

	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	
	public Set<Chargerate> getChargerates() {
		return this.chargerates;
	}

	public void setChargerates(Set<Chargerate> chargerates) {
		this.chargerates = chargerates;
	}

}
