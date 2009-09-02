/**
 * 
 */
package com.antilia.struts2.jquery.model;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IGridCellRenderer<B> extends Serializable {

	/**
	 * Renders a cell of the grid.
	 * 
	 * @param bean The bean containing grid data.
	 * @param propertyPath The property path of the bean.
	 * @param column
	 * @param row
	 * @return
	 */
	String renderCell(B bean, String propertyPath, int column, int row);
	
}
