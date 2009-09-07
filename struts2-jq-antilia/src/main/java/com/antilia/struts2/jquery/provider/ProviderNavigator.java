/**
 * 
 */
package com.antilia.struts2.jquery.provider;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.antilia.common.query.IOrder;
import com.antilia.common.query.IQuery;
import com.antilia.common.query.Order;
import com.antilia.common.query.Query;
import com.antilia.struts2.jquery.model.GridColumnModel;
import com.antilia.struts2.jquery.model.GridModel;
import com.antilia.struts2.jquery.model.SortOrder;
import com.antilia.struts2.jquery.utils.ReflectionUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public  class ProviderNavigator<B extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private IDataProvider<B> dataProvider;
	
	private GridModel<B> gridModel;
	
	private IQuery<B> query;
	
	/**
	 * 
	 * @param dataProvider
	 * @param columnModel
	 */
	public ProviderNavigator(IDataProvider<B> dataProvider, GridModel<B> gridModel) {
		this.dataProvider = dataProvider;
		this.gridModel = gridModel;
		this.query = new Query<B>(gridModel.getBeanClass());
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public final void renderData() throws Exception {
		configureProvider(dataProvider);
		if(this.gridModel.getTransferProtocol().equals(GridModel.TransferProtocol.xml))
			renderXML(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		else {
			renderJson(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		}
	}
	
	/**
	 * Give a chance to configure the provider.
	 * 
	 * @param dataProvider
	 */
	protected void configureProvider(IDataProvider<B> dataProvider) {
		SortOrder sortOrder = getSortOrder(ServletActionContext.getRequest());
		String propertyPath = getSortColumn(ServletActionContext.getRequest());
		getQuery().clearOrders();
		IOrder<B> order = Order.des(propertyPath);
		if(sortOrder.equals(SortOrder.asc)) {
			order = Order.asc(propertyPath);			
		}
		getQuery().addOrder(order);
		int rows = getNumberOfRows(ServletActionContext.getRequest());
		int page = getCurrentPage(ServletActionContext.getRequest());
		getQuery().setFirstResult(rows*(page-1));
		getQuery().setMaxResults(rows);
	}
	
	protected String renderJson(HttpServletRequest request, HttpServletResponse response) throws Exception {    	   
		response.setContentType("text/json-comment-filtered"); 
		PrintWriter writer = response.getWriter(); 
		int rows = getNumberOfRows(request);
		int page = getCurrentPage(request);
		int records = totalRecords(getQuery());
		int totalPages = (records/rows)+1;
		writer.print("{\"page\":\"");
		writer.print(page);		
		writer.print("\",\"total\":\"");
		writer.print(totalPages);
		writer.print("\",\"records\":\"");
		writer.print(records);
		writer.print("\",");
		writer.print("\"rows\":[");
		int row = 1;
		Iterator<? extends B> it = getRows(getQuery()).iterator();
		while(it.hasNext()) {
			B bean = it.next();
			writer.print("{");
			writer.print("\"id\":\""+row);
			writer.print("\", \"cell\":[");
		    int column = 1;
		    Iterator<GridColumnModel<B>> it1 = this.gridModel.getColumnModels().iterator();		    
		    while(it1.hasNext()) {
		    	GridColumnModel<B> columnModel = it1.next();
		    	writer.print("\"");	
		    	//TODO: do escaping for JSON format.
		    	//if(columnModel.getCellRenderer() != null)
		    	//	writer.print(columnModel.getCellRenderer().renderCell(bean, columnModel.getPropertyPath(), column, row));		    		
		    	///else
		    	writer.print(createCellContent(row, column, columnModel.getPropertyPath(), bean));		    	
		    		
		    	writer.print("\"");
		    	if(it1.hasNext())
		    		writer.print(",");
		    }
		    writer.print("]}");
		    if(it.hasNext())
		    	writer.print(",");
		    row++;
		}						
		writer.print("]}"); 		
		writer.flush();		
		return null;
    }
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	protected void renderXML(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/xml"); 
		PrintWriter writer = response.getWriter();
		int rows = getNumberOfRows(request);
		int page = getCurrentPage(request);
		int records = totalRecords(getQuery());
		int totalPages = (records/rows)+1;
		writer.println("<?xml version='1.0' encoding='utf-8'?>");
		writer.println("<rows>");
		writer.print("<page>");
		writer.print(page);
		writer.println("</page>");
		writer.print("<total>");
		writer.print(totalPages);
		writer.println("</total>");
		writer.print("<records>");
		writer.print(records);;		
		writer.println("</records>");		
		int row = 1;
		for(B bean: getRows(getQuery()) ) {
			writer.print("<row id=\"");
			writer.print("row"+row);
			writer.println("\">");
		    int column = 1;
		    for(GridColumnModel<B> columnModel: this.gridModel.getColumnModels()) {
		    	writer.print("<cell><![CDATA[");	
		    	if(columnModel.getCellRenderer() != null)
		    		writer.print(columnModel.getCellRenderer().renderCell(bean, columnModel.getPropertyPath(), column, row));		    		
		    	else
		    		writer.print(createCellContent(row, column, columnModel.getPropertyPath(), bean));		    	
		    		
		    	writer.println("]]></cell>");			     
		    }
		    writer.println("</row>");
		    row++;
		}				
		writer.println("</rows>");
		this.dataProvider.detach(); 
		writer.flush();
	}
	
	protected String createCellContent(int row, int column, String propertyPath, B bean) throws NoSuchFieldException {
		//return propertyPath+"_"+column;
		Object object = ReflectionUtils.getPropertyValue(bean, propertyPath);
		if(object != null) {
			return object.toString();
		}
		return "";
	}	
	
	private Iterable<? extends B> getRows(IQuery<B> query) {
		return this.dataProvider.getData(query);
	}
	
	private int totalRecords(IQuery<B> query) {
		return this.dataProvider.getSize(query);
	}
	
	private int getCurrentPage(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			return 10;
		}
	}
	
	private int getNumberOfRows(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("rows"));
		} catch (Exception e) {
			return 10;
		}
	}
	
	
	private SortOrder getSortOrder(HttpServletRequest request) {
		try {
			return SortOrder.valueOf(request.getParameter("sord"));
		} catch (Exception e) {
			return SortOrder.asc;
		}
	}
	
	private String getSortColumn(HttpServletRequest request) {
		try {
			return request.getParameter("sidx");
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * @return the query
	 */
	public IQuery<B> getQuery() {
		return query;
	}


	/**
	 * @param query the query to set
	 */
	public void setQuery(IQuery<B> query) {
		this.query = query;
	}
	
		
}
