/**
 * 
 */
package com.antilia.struts2;

import com.antilia.struts2.jquery.model.GridModel.TransferProtocol;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class SearchDBXMLAction extends JQueryGridDBAction {


	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.antilia.struts2.JQueryGridDBAction#getTransferProtocol()
	 */
	@Override
	protected TransferProtocol getTransferProtocol() {
		return TransferProtocol.xml;
	}

}
