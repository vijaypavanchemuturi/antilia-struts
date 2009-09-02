/**
 * 
 */
package com.antilia.struts2.jquery.model;

import java.util.List;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ListDataProvider<B> implements IDataProvider<B> {

	private static final long serialVersionUID = 1L;
	
	private List<B> list;
	
	public ListDataProvider(List<B> list) {
		if (list == null)
		{
			throw new IllegalArgumentException("argument [collection] cannot be null");
		}
		this.list = list;
	}
	
	
	public Iterable<? extends B> getData(int first, int count) {
		int toIndex = first + count;
		if (toIndex > list.size())
		{
			toIndex = list.size();
		}
		return list.subList(first, toIndex);
	}
	
	
	public int getSize() {
		return list.size();
	}
}
