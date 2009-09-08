package com.antilia.struts2.entities;

// Generated Apr 23, 2008 5:11:37 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EmployeeRole generated by hbm2java
 */
@Entity
@Table(name = "employee_role")
public class EmployeeRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "role", column = @Column(name = "role", nullable = false)),
			@AttributeOverride(name = "employee", column = @Column(name = "employee", nullable = false)) })
	private EmployeeRoleId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role", nullable = false, insertable = false, updatable = false)
	private Role role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee", nullable = false, insertable = false, updatable = false)
	private Employee employee;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "startdate", nullable = false, length = 13)
	private Date startdate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "enddate", length = 13)
	private Date enddate;

	public EmployeeRole() {
	}

	public EmployeeRole(EmployeeRoleId id, Role role, Employee employee,
			Date startdate) {
		this.id = id;
		this.role = role;
		this.employee = employee;
		this.startdate = startdate;
	}

	public EmployeeRole(EmployeeRoleId id, Role role, Employee employee,
			Date startdate, Date enddate) {
		this.id = id;
		this.role = role;
		this.employee = employee;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	
	public EmployeeRoleId getId() {
		return this.id;
	}

	public void setId(EmployeeRoleId id) {
		this.id = id;
	}

	
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

}