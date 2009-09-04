package com.antilia.struts2.entities;

import java.util.List;

import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.command.DefaultCommander;
import com.antilia.hibernate.context.RequestContext;

public class ListCountries {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// net.sf.cglib.core.DebuggingClassWriter@1854b38
		// net.sf.cglib.core.DebuggingClassWriter@40627c
		IPersistenceUnit persistenceUnit = DerbyPersistenceUnit.getInstance();		
		RequestContext requestContext = RequestContext.get();
		requestContext.setPersistenceUnit(persistenceUnit);		
		requestContext.setUser("test");
		
		List<Country> contries = DefaultCommander.loadAll(Country.class);
		for(Country country: contries) {
			System.err.println(country.getName());
		}
	}

}
