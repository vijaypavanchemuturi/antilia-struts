/**
 * 
 */
package com.antilia.struts2.jquery.model;

import java.io.Serializable;

/**
 * This interface is inspired by a similar interface on Wicket framework...
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IDataProvider<B> extends Serializable {
		
	/**
	 * Returns an Iterable over the records starting at first and ending at firt+count.
	 * 
	 * @param first
	 * @param count
	 * @return
	 */
	Iterable<? extends B> getData(int first, int count);
	
	/**
	 * 
	 * @return returns the number of records.
	 */
	int getSize(); 

}
