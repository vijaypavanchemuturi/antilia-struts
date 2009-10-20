/**
 * 
 */
package com.antilia.struts2.data;

import com.antilia.struts2.entities.Country;
import com.antilia.struts2.provider.QuerableDaoProvider;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class CountriesProvider extends QuerableDaoProvider<Country> {

	private static final long serialVersionUID = 1L;

	public CountriesProvider() {
		super(new CountriesDao());
	}
	
	
}
