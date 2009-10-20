/**
 * 
 */
package com.antilia.struts2.provider;

import java.io.Serializable;
import java.util.List;

import com.antilia.common.query.IOrder;
import com.antilia.common.query.IQuery;
import com.antilia.common.query.Order;
import com.antilia.common.query.Query;
import com.antilia.common.util.QueryUtils;
import com.antilia.struts2.jquery.model.SortInfo;
import com.antilia.struts2.jquery.model.SortOrder;
import com.antilia.struts2.jquery.provider.IDataProvider;

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
	
	@SuppressWarnings("unchecked")
	public Iterable<? extends B> getData(int start, int size, B searchBean, SortInfo sortInfo) {
		IQuery<B> query = new Query<B>((Class<B>) searchBean.getClass());
		if(sortInfo != null) {
			IOrder<B> order = Order.des(sortInfo.getProperty());
			if(sortInfo.getSortOrder().equals(SortOrder.asc)) {
				order = Order.asc(sortInfo.getProperty());			
			}
			query.addOrder(order);
		}
		
		query.setFirstResult(start);
		query.setMaxResults(size);	
		return getData(query);
	};
	
	@SuppressWarnings("unchecked")
	public int getSize(B searchBean, SortInfo sortInfo) {
		IQuery<B> query = new Query<B>((Class<B>) searchBean.getClass());
		if(sortInfo != null) {
			IOrder<B> order = Order.des(sortInfo.getProperty());
			if(sortInfo.getSortOrder().equals(SortOrder.asc)) {
				order = Order.asc(sortInfo.getProperty());			
			}
			query.addOrder(order);
		}
		return getSize(query);
	};
	
	
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
