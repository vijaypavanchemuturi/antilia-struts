/**
 * 
 */
package com.antilia.struts2.jquery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class GridModel<B extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * The bean class.
	 */
	private Class<B> beanClass;
		
	
	/**
	 * The column models.
	 */
	private List<GridColumnModel<B>> columnModels = new ArrayList<GridColumnModel<B>>();	
	
	/**
	 *   viewrecords defines the view the total records from the query in the pager
	 *   bar. The related tag is: records in xml or json definitions.
	 */
	private boolean viewrecords = true;
	
	
	/**
	 * The caption show by the grid (or the key in a resource bundle).
	 */
	private String caption;
	
	
	/**
	 * Transfer protocol used to communicate with action.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
	 *
	 */
	public static enum TransferProtocol {
		xml,
		json
	}
	
	/**
	 * Flag used to set the transfer protocol used to communicate with the server.
	 */
	private TransferProtocol transferProtocol = TransferProtocol.xml;
	
	/**
	 * Sets the sorting order. Default is asc. This is the sorting order used to sort the
	 * initial sorted column. 
	 */
	private SortOrder sortOrder = SortOrder.asc;
	
	/**
	 * 
	 */
	public GridModel(Class<B> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * @return the beanClass
	 */
	public Class<B> getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<B> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * Adds a column model.
	 * @param model
	 * @return
	 */
	public GridModel<B> addColumnModel(GridColumnModel<B> model) {
		columnModels.add(model);
		model.setGridModel(this);
		return this;
	}
	
	/**
	 * Removes a column model.
	 * @param model
	 * @return
	 */
	public GridModel<B> removeColumnModel(GridColumnModel<B> model) {
		columnModels.remove(model);
		model.setGridModel(null);
		return this;
	}
	
	/**	  
	 * @return Returns  an Iterable over the column models.
	 */
	public Iterable<GridColumnModel<B>> getColumnModels() {
		return this.columnModels;
	}
	
	/**
	 * 
	 * @return Returns the number of columns.
	 */
	public int getColumnCount() {
		return this.columnModels.size();
	}
	
	
	public GridColumnModel<B> getInitialSort() {
		for(GridColumnModel<B> columnModel: columnModels) {
			if(columnModel.isInitialSort())
				return columnModel;
		}
		if(!columnModels.isEmpty())
			return columnModels.get(0);
		return null;
	}

	/**
	 * @return the viewrecords
	 */
	public boolean isViewrecords() {
		return viewrecords;
	}

	/**
	 * @param viewrecords the viewrecords to set
	 */
	public void setViewrecords(boolean viewrecords) {
		this.viewrecords = viewrecords;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the transferProtocol
	 */
	public TransferProtocol getTransferProtocol() {
		return transferProtocol;
	}

	/**
	 * @param transferProtocol the transferProtocol to set
	 */
	public void setTransferProtocol(TransferProtocol transferProtocol) {
		this.transferProtocol = transferProtocol;
	}

	/**
	 * @return the sortOrder
	 */
	public SortOrder getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
}
