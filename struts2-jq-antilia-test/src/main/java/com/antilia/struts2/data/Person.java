package com.antilia.struts2.data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;		
	
	private String lastnames;
		
	private String ssNumber;	
	private Date ssNumberDate;
	
	private String address;
	private String town;	
	private String nationality;
	private String postalCode;
	private String phoneNumber;
	private String email;	
	private String phoneNumber2;	
	private Date birthday;
	private String birthPlace;
	private Sex sex;
	private String profesion;

	
	public Person() {
	}

	/**
	 * @return the apellidos
	 */
	public String getLastnames() {
		return lastnames;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setLastnames(String apellidos) {
		this.lastnames = apellidos;
	}

	/**
	 * @return the nif
	 */
	public String getSsNumber() {
		return ssNumber;
	}

	/**
	 * @param nif the nif to set
	 */
	public void setSsNumber(String nif) {
		this.ssNumber = nif;
	}

	/**
	 * @return the domicilio
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param domicilio the domicilio to set
	 */
	public void setAddress(String domicilio) {
		this.address = domicilio;
	}

	/**
	 * @return the localidad
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param localidad the localidad to set
	 */
	public void setTown(String localidad) {
		this.town = localidad;
	}

	/**
	 * @return the nacionalidad
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nacionalidad the nacionalidad to set
	 */
	public void setNationality(String nacionalidad) {
		this.nationality = nacionalidad;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setPostalCode(String codigoPostal) {
		this.postalCode = codigoPostal;
	}

	/**
	 * @return the telefono
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setPhoneNumber(String telefono) {
		this.phoneNumber = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefono2
	 */
	public String getTelefono2() {
		return phoneNumber;
	}

	/**
	 * @param telefono2 the telefono2 to set
	 */
	public void setTelefono2(String telefono2) {
		this.phoneNumber = telefono2;
	}

	/**
	 * @return the nombre
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setName(String nombre) {
		this.name = nombre;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setBirthday(Date fechaNacimiento) {
		this.birthday = fechaNacimiento;
	}

	/**
	 * @return the sexo
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSex(Sex sexo) {
		this.sex = sexo;
	}

	/**
	 * @return the profesion
	 */
	public String getProfesion() {
		return profesion;
	}

	/**
	 * @param profesion the profesion to set
	 */
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lastnames == null) ? 0 : lastnames.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (lastnames == null) {
			if (other.lastnames != null)
				return false;
		} else if (!lastnames.equals(other.lastnames))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @return the fechaNif
	 */
	public Date getFechaNifDate() {
		return ssNumberDate;
	}

	/**
	 * @param fechaNif the fechaNif to set
	 */
	public void setFechaNifDate(Date fechaNif) {
		this.ssNumberDate = fechaNif;
	}


	/**
	 * @return the lugarNacimiento
	 */
	public String getBirthPlace() {
		return birthPlace;
	}

	/**
	 * @param lugarNacimiento the lugarNacimiento to set
	 */
	public void setBirthPlace(String lugarNacimiento) {
		this.birthPlace = lugarNacimiento;
	}

	/**
	 * @return the phoneNumber2
	 */
	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	/**
	 * @param phoneNumber2 the phoneNumber2 to set
	 */
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}
}
