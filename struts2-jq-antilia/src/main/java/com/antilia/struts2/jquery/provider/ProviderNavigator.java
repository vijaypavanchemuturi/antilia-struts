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

import com.antilia.struts2.jquery.model.GridColumnModel;
import com.antilia.struts2.jquery.model.GridModel;
import com.antilia.struts2.jquery.model.SortInfo;
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
	
	//private IQuery<B> query;
	
	private B searchBean;
	
	private SortInfo sortInfo;
	
	private String[] searchFields;
	
	/**
	 * 
	 * @param dataProvider
	 * @param columnModel
	 */
	public ProviderNavigator(IDataProvider<B> dataProvider, GridModel<B> gridModel, B searchBean, String... searchFields) {
		this.dataProvider = dataProvider;
		this.gridModel = gridModel;
		this.searchBean = searchBean;		
		this.searchFields = searchFields;
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public final void renderData() throws Exception {
		configureSort();
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
	protected void configureSort() {
		SortOrder sortOrder = getSortOrder(ServletActionContext.getRequest());
		String propertyPath = getSortColumn(ServletActionContext.getRequest());
		if(propertyPath != null) {
			this.sortInfo = new SortInfo(propertyPath, sortOrder);
		} else {
			this.sortInfo = null;
		}
		/*
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
		*/
	}
	
	protected String renderJson(HttpServletRequest request, HttpServletResponse response) throws Exception {    	   
		response.setContentType("text/json-comment-filtered"); 
		PrintWriter writer = response.getWriter(); 
		int rows = getNumberOfRows(request);
		int page = getCurrentPage(request);
		int records = totalRecords(getSearchBean(), this.sortInfo, getSearchFields());
		int start = rows*(page-1);
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
		Iterator<? extends B> it = getRows(start, rows, getSearchBean(), sortInfo, getSearchFields()).iterator();
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
		int records = totalRecords(getSearchBean(),this.sortInfo, getSearchFields());
		int start = rows*(page-1);
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
		for(B bean: getRows(start, rows, getSearchBean(), sortInfo, getSearchFields())) {
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
	
	private Iterable<? extends B> getRows(int start, int rows, B searchBean, SortInfo sortInfo, String[] searchFields) {
		return this.dataProvider.getData(start, rows, searchBean, sortInfo, searchFields);
	}
	
	private int totalRecords(B searchBean, SortInfo sortInfo, String[] searchFields) {
		return this.dataProvider.getSize(searchBean, sortInfo, searchFields);
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


	public B getSearchBean() {
		return searchBean;
	}


	public void setSearchBean(B searchBean) {
		this.searchBean = searchBean;
	}


	public String[] getSearchFields() {
		return searchFields;
	}


	public void setSearchFields(String[] searchFields) {
		this.searchFields = searchFields;
	}		
}
