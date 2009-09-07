/**
 * 
 */
package com.antilia.struts2.jquery.provider;

import java.io.Serializable;

import com.antilia.common.query.IQuery;

/**
 * This interface is inspired by a similar interface on Wicket framework...
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IDataProvider<B extends Serializable> extends Serializable {
		
	/**
	 * Returns an Iterable over the records starting at first and ending at firt+count.
	 * 
	 * @param first
	 * @param count
	 * @return
	 */
	Iterable<? extends B> getData(IQuery<B> query);
	
	/**
	 * 
	 * @return returns the number of records.
	 */
	int getSize(IQuery<B> query); 
	
	
	/**
	 * Can be used to detach any heavy resources that where created while
	 * finding the data produced by the query. Detach will always be called  
	 * after getData() and getSize() methods have been both executed.
	 */
	void detach();

}
