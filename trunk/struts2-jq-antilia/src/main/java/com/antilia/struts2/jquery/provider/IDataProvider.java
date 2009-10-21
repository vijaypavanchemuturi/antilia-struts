/**
 * 
 */
package com.antilia.struts2.jquery.provider;

import java.io.Serializable;

import com.antilia.struts2.jquery.model.SortInfo;

/**
 * This interface is inspired by a similar interface on Wicket framework...
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IDataProvider<B extends Serializable> extends Serializable {
		
	/**
	 * Returns an Iterable over the records starting at first and ending at first+size.
	 * 
	 * @param start first record to return
	 * @param size number of records to return
	 * @param searchBean Bean to be used for filtering the results.
	 * @param sortInfo  which column should be used for ordering data.
	 * @param searchFields Which fields of the seacrBean should be used to filter data.
	 * @return
	 */
	Iterable<? extends B> getData(int start, int size,  B searchBean, SortInfo sortInfo, String... searchFields);
	
	/**
	 * Returns the number of records.
	 * 
	 * @param searchBean Bean to be used for filtering the results
	 * @param sortInfo which column should be used for ordering data.
	 * @param searchFields Which fields of the seacrBean should be used to filter data.
	 * @return Returns the number of records.
	 */
	int getSize(B searchBean, SortInfo sortInfo, String... searchFields); 
	
	
	/**
	 * Can be used to detach any heavy resources that where created while
	 * finding the data produced by the query. Detach will always be called  
	 * after getData() and getSize() methods have been both executed.
	 */
	void detach();

}
