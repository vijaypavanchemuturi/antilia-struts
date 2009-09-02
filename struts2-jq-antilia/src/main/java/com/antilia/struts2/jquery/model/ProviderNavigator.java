/**
 * 
 */
package com.antilia.struts2.jquery.model;

import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public  class ProviderNavigator<B> implements Serializable {

	private static final long serialVersionUID = 1L;

	private IDataProvider<B> dataProvider;
	
	private GridModel<B> gridModel;
	
	/**
	 * 
	 * @param dataProvider
	 * @param columnModel
	 */
	public ProviderNavigator(IDataProvider<B> dataProvider, GridModel<B> gridModel) {
		this.dataProvider = dataProvider;
		this.gridModel = gridModel;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public void getXml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/xml"); 
		PrintWriter writer = response.getWriter();
		int rows = getNumberOfRows(request);
		int page = getCurrentPage(request);
		int records = totalRecords();
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
		for(B bean: getRows(rows, page) ) {
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
		writer.flush();
	}
	
	protected String createCellContent(int row, int column, String propertyPath, B bean) {
		return propertyPath+"_"+column;
	}	
	
	private Iterable<? extends B> getRows(int rows, int page) {
		return this.dataProvider.getData(rows*(page-1), rows);
	}
	
	private int totalRecords() {
		return this.dataProvider.getSize();
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
	
	
		
}
