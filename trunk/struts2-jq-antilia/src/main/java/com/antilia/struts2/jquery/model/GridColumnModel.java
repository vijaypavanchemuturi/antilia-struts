/**
 * 
 */
package com.antilia.struts2.jquery.model;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class GridColumnModel<B> {

	private GridModel<B> gridModel;
	
	/**
	 * The width of the column (in pixels),
	 */
	private int width = 100;
	
	/**
	 * The property path.
	 */
	private String propertyPath;
	
	/**
	 *  If the column is sortable
	 */
	private boolean sortable = true;
	
	
	
	/**
	 * A special CellRenderer for cells of this column
	 */
	private IGridCellRenderer<B> cellRenderer;

	
	public static enum Alignment {
		RIGHT,
		CENTER,
		LEFT,
	}
	
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

}
