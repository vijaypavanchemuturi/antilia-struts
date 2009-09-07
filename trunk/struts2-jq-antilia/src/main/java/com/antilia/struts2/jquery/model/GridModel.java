/**
 * 
 */
package com.antilia.struts2.jquery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.antilia.common.util.StringUtils;

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
	 * Set a zebra-striped grid
	 */
	private boolean alternateRows = true;
	
	
	/**
	 * The height of the grid
	 */
	private String height = null;
	
	/**
	 * The width of the table (only in pixels).
	 */
	private Integer width = null;
	
	/**
	 * See http://www.secondpersonplural.ca/jqgriddocs/index.htm
	 */
	private boolean shrinkToFit = true;
	
	/**
	 * When autowidth is set to true the grid fits to the width of the parent container.
	 */
	private boolean autowidth = false;
	
	/**
	 * The optin rownumbers add additional column which count the rows 
	 */
	private boolean rownumbers = false;
	
	/**
	 * Enables or disables the show/hide grid button, 
	 * which appears on the right side of the Caption layer. 
	 * Takes effect only if the caption property is not an empty string. 
	 */
	private boolean hidegrid = true;
	
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
	 * The page sizes that are displayed on the corresponding select at the navigation bar.
	 * Setting it to <code>null</code> will disable the select.
	 */
	private int[] rowList = {10,20,30};
	
	
	/**
	 * Assigns a class to columns that are resizable so that we can show a resize handle only for ones that are resizable.
	 * 
	 */
	private String resizeclass;
	
	/**
	 * List of rows sizes that will appear on the select box.
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
		if(StringUtils.isEmpty(caption)) {
			return this.beanClass.getSimpleName();
		}
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

	/**
	 * @return the alternateRows
	 */
	public boolean isAlternateRows() {
		return alternateRows;
	}

	/**
	 * @param alternateRows the alternateRows to set
	 */
	public void setAlternateRows(boolean alternateRows) {
		this.alternateRows = alternateRows;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the shrinkToFit
	 */
	public boolean isShrinkToFit() {
		return shrinkToFit;
	}

	/**
	 * @param shrinkToFit the shrinkToFit to set
	 */
	public void setShrinkToFit(boolean shrinkToFit) {
		this.shrinkToFit = shrinkToFit;
	}

	/**
	 * @return the autowidth
	 */
	public boolean isAutowidth() {
		return autowidth;
	}

	/**
	 * @param autowidth the autowidth to set
	 */
	public void setAutowidth(boolean autowidth) {
		this.autowidth = autowidth;
	}

	/**
	 * @return the rownumbers
	 */
	public boolean isRownumbers() {
		return rownumbers;
	}

	/**
	 * @param rownumbers the rownumbers to set
	 */
	public void setRownumbers(boolean rownumbers) {
		this.rownumbers = rownumbers;
	}

	/**
	 * @return the hidegrid
	 */
	public boolean isHidegrid() {
		return hidegrid;
	}

	/**
	 * @param hidegrid the hidegrid to set
	 */
	public void setHidegrid(boolean hidegrid) {
		this.hidegrid = hidegrid;
	}

	/**
	 * @return the rowList
	 */
	public int[] getRowList() {
		return rowList;
	}

	/**
	 * @param rowList the rowList to set
	 */
	public void setRowList(int[] rowList) {
		this.rowList = rowList;
	}

	/**
	 * @return the resizeclass
	 */
	public String getResizeclass() {
		return resizeclass;
	}

	/**
	 * @param resizeclass the resizeclass to set
	 */
	public void setResizeclass(String resizeclass) {
		this.resizeclass = resizeclass;
	}
}
