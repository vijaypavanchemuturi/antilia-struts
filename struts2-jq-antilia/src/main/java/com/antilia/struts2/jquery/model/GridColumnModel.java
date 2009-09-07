/**
 * 
 */
package com.antilia.struts2.jquery.model;

import java.io.Serializable;

import com.antilia.common.util.StringUtils;

/**
 * Allows to configure a jqrid column.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class GridColumnModel<B extends Serializable> {

	/**
	 * The parent GridModel.
	 */
	private GridModel<B> gridModel;
	
	/**
	 * The width of the column (in pixels),
	 */
	private int width = 100;
	
	/**
	 * The property path used by the column to retrieve a cell value.
	 */
	private String propertyPath;
	
	/**
	 *  Flag to set column sortability. True by default. 
	 */
	private boolean sortable = true;
	
	/**
	 * Flag to set column resizability. True by default.
	 */
	private boolean resizable = true;
	
	
	/**
	 * The key to be used by the tag to get columnName
	 */
	private String name;
	
	
	
	/**
	 * A special CellRenderer for cells of this column
	 */
	private IGridCellRenderer<B> cellRenderer;

	
	/**
	 * Supported alignments.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
	 *
	 */
	public static enum Alignment {
		RIGHT,
		CENTER,
		LEFT,
	}
	
	/**
	 * Cell alignment.
	 */
	private Alignment alignment = Alignment.LEFT;
	
	/**
	 * If this column is used to initially sort the table.
	 */
	private boolean initialSort = false;
	
	/**
	 * 
	 */
	public GridColumnModel(String propertyPath, int width) {		
		this.propertyPath = propertyPath;
		this.width = width;
	}

	/**
	 * @return the gridModel
	 */
	public GridModel<B> getGridModel() {
		return gridModel;
	}

	/**
	 * @param gridModel the gridModel to set
	 */
	public void setGridModel(GridModel<B> gridModel) {
		this.gridModel = gridModel;
	}

	/**
	 * @return the propertyPath
	 */
	public String getPropertyPath() {
		return propertyPath;
	}

	/**
	 * @param propertyPath the propertyPath to set
	 */
	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the cellRenderer
	 */
	public IGridCellRenderer<B> getCellRenderer() {
		return cellRenderer;
	}

	/**
	 * @param cellRenderer the cellRenderer to set
	 */
	public void setCellRenderer(IGridCellRenderer<B> cellRenderer) {
		this.cellRenderer = cellRenderer;
	}

	/**
	 * @return the sortable
	 */
	public boolean isSortable() {
		return sortable;
	}

	/**
	 * @param sortable the sortable to set
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	/**
	 * @return the alignment
	 */
	public Alignment getAlignment() {
		return alignment;
	}

	/**
	 * @param alignment the alignment to set
	 */
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	/**
	 * @return the initialSort
	 */
	public boolean isInitialSort() {
		return initialSort;
	}

	/**
	 * @param initialSort the initialSort to set
	 */
	public void setInitialSort(boolean initialSort) {
		this.initialSort = initialSort;
	}

	/**
	 * If name is not empty returns name. Otherwise will return: BeanName.propertyPath
	 * @return the name
	 */
	public String getName() {
		if(StringUtils.isEmpty(name))
			return getGridModel().getBeanClass().getSimpleName() + "." + getPropertyPath();
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the resizable
	 */
	public boolean isResizable() {
		return resizable;
	}

	/**
	 * @param resizable the resizable to set
	 */
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

}
