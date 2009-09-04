/**
 * 
 */
package com.antilia.struts2.data;

import com.antilia.hibernate.dao.impl.HibernateQuerableUpdatableDao;
import com.antilia.struts2.entities.Country;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class CountriesDao extends HibernateQuerableUpdatableDao<Country> {

	private static final long serialVersionUID = 1L;

	public CountriesDao() {
	}
}
