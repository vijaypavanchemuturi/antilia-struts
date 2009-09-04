package com.antilia.struts2.entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.antilia.common.util.StringUtils;
import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.command.DefaultCommander;
import com.antilia.hibernate.context.RequestContext;

public class InsertCountries {

	
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
		
		List<Country> countries = new ArrayList<Country>();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(InsertCountries.class.getResourceAsStream("Counties.txt")));
			String line = null;
			do {
				line =reader.readLine();
				Country country = new Country();				
				if(!StringUtils.isEmpty(line)) {
					line = line.trim();
					country.setName(line);
					country.setDomain(line.substring(0, 2).toLowerCase());
					countries.add(country);
				}
			} while(line != null);
			
			for(Country country: countries) {
				System.err.println(country.getName()+","+country.getDomain());
			}
			
			for(Country country: countries) {
				DefaultCommander.persist(country);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
