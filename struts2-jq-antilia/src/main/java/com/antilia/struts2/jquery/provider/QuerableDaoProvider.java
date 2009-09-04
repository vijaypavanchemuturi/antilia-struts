/**
 * 
 */
package com.antilia.struts2.jquery.provider;

import java.io.Serializable;

import com.antilia.common.dao.IQuerableDao;
import com.antilia.common.query.IQuery;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class QuerableDaoProvider<B extends Serializable> implements IDataProvider<B> {

	private static final long serialVersionUID = 1L;
	
	private IQuerableDao<B>  querableDao;
	
	/**
	 * 
	 */
	public QuerableDaoProvider(IQuerableDao<B>  querableDao) {
		this.querableDao = querableDao;
	}

	@Override
	public Iterable<? extends B> getData(IQuery<B> query) {
		return querableDao.findAll(query);
	}
	
	@Override
	public int getSize(IQuery<B> query) {
		Long size = querableDao.count(query);
		if(size != null)
			return size.intValue();
		return 0;
	}
	
	
	@Override
	public void detach() {
		// do nothing as I'm not caching anything
	}
}
