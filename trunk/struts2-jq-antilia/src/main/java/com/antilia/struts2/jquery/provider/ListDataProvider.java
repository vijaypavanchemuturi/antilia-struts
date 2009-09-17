/**
 * 
 */
package com.antilia.struts2.jquery.provider;

import java.io.Serializable;
import java.util.List;

import com.antilia.common.query.IQuery;
import com.antilia.common.util.QueryUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ListDataProvider<B extends Serializable> implements IDataProvider<B> {

	private static final long serialVersionUID = 1L;
	
	private List<B> list;
	
	private List<B> tempList;
	
	public ListDataProvider(List<B> list) {
		if (list == null)
		{
			throw new IllegalArgumentException("argument [collection] cannot be null");
		}
		this.list = list;
	}
	
	
	public Iterable<? extends B> getData(IQuery<B> query) {
		if(tempList == null) {
			doQuery(query);
		}
		int toIndex = query.getFirstResult() + query.getMaxResults();
		if (toIndex > tempList.size())
		{
			toIndex = tempList.size();
		}
		return tempList.subList(query.getFirstResult(), toIndex);
	}
	
	private void doQuery(IQuery<B> query) {		
		tempList = QueryUtils.sortList(list, query);
	}
	
	
	public int getSize(IQuery<B> query) {
		if(tempList == null) {
			doQuery(query);
		}
		return tempList.size();
	}
	
	public void detach() {
		tempList = null;
	}
}
