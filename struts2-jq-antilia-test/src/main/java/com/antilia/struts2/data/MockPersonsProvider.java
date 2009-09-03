/**
 * 
 */
package com.antilia.struts2.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.antilia.struts2.jquery.model.ListDataProvider;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class MockPersonsProvider extends ListDataProvider<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static MockPersonsProvider instance;
	
	private static final String[] fnames = {
		"Anna", 
		"Maria",
		"Carla",
		"Carlotta",
		"Ulrike",		
		};
	
	private static final String[] mnames = {
		"Frank", 
		"Julius",
		"Michael",
		"Otto", 
		"Roco",
		"Ricard",
		"Roland",
		};
	
	private static final String[] lastnames = {
		"Amor",
		"Anorak",
		"Barreiro",
		"Borroto",
		"Filibustero", 
		"Dominguez",
		"Jubilatus",
		"Kunst",
		"Hunter",
		"Morales",
		"Martinez",
		"Orujo",
		"Pascual",
		"Perez",
		"Reinhart",
		"Ricard"
		};
	
	public static List<Person> createPersonas() {
		List<Person> clientes = new ArrayList<Person>();
		for(String name: fnames) {
			for(String lastName: lastnames) {
				Person persona = new Person();
				persona.setName(name);				
				persona.setLastnames(lastName);
				persona.setBirthday(new Date());
				persona.setSsNumber("CDFSA1");
				persona.setFechaNifDate(new Date());
				persona.setPostalCode("CDFSA1");
				persona.setAddress("Kenedy Allee 166");			
				persona.setSex(Sex.M);
				persona.setPostalCode("DFFD-211");
				persona.setTown("Frankfurt Main");
				persona.setProfesion("Zapatero");
				persona.setEmail(name+"."+lastName+"@web.com");
				persona.setNationality("Alemana");
				persona.setBirthPlace("Alemania");
				persona.setPhoneNumber("131323111");
				clientes.add(persona);
				
			}
		}
		
		for(String name: mnames) {
			for(String lastName: lastnames) {
				Person persona = new Person();
				persona.setName(name);
				persona.setLastnames(lastName);
				persona.setBirthday(new Date());
				persona.setSsNumber("CDFSA1");				
				persona.setFechaNifDate(new Date());
				persona.setPostalCode("CDFSA1");
				persona.setAddress("Bertillon 166");	
				persona.setSex(Sex.M);
				persona.setPostalCode("DFFD-211");									
				persona.setTown("Frankfurt Main");
				persona.setProfesion("Zapatero");
				persona.setEmail(name+"."+lastName+"@web.com");
				persona.setNationality("Alemana");
				persona.setBirthPlace("Alemania");
				persona.setPhoneNumber("131323111");
				clientes.add(persona);
				
			}
		}
		return clientes;
	}
	
	public MockPersonsProvider() {
		super(createPersonas());
	}
	
	/**
	 * @return the instance
	 */
	public static MockPersonsProvider getInstance() {
		if(instance == null)
			instance = new MockPersonsProvider();
		return instance;
	}

}
