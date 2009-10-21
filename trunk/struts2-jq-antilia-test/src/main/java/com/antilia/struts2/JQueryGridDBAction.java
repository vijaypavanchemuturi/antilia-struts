package com.antilia.struts2;

import java.util.Locale;

import org.apache.struts2.ServletActionContext;

import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.context.RequestContext;
import com.antilia.struts2.data.CountriesProvider;
import com.antilia.struts2.entities.Country;
import com.antilia.struts2.entities.HSQLDBPersistenceUnit;
import com.antilia.struts2.entities.InsertData;
import com.antilia.struts2.jquery.model.GridColumnModel;
import com.antilia.struts2.jquery.model.GridModel;
import com.antilia.struts2.jquery.model.IGridCellRenderer;
import com.antilia.struts2.jquery.model.SortOrder;
import com.antilia.struts2.jquery.model.GridModel.TransferProtocol;
import com.antilia.struts2.jquery.provider.ProviderNavigator;
import com.opensymphony.xwork2.ActionContext;


/**
 * <p>
 * Action to test JQuery functionality.
 * </p>
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class JQueryGridDBAction extends ExampleSupport {

    private static final long serialVersionUID = 1L;

	
	private GridModel<Country>  gridModel;
  
    private String name;
    
    private String domain;
	
	public String execute() throws Exception {    	   
    	setRequest_locale((String)ServletActionContext.getRequest().getParameter("request_locale"));
        setMessage(getText(MESSAGE));                
        innitModel();

        return SUCCESS;
    }
	
	private void innitModel() {
		gridModel = new GridModel<Country>(Country.class);
		gridModel.setCaption("Countries");
		// transfer protocol will determine how the tag communicates with the back end.
		gridModel.setTransferProtocol(getTransferProtocol());
		gridModel.setAutowidth(true);
		gridModel.setShrinkToFit(false);
		gridModel.setRownumbers(true);
		gridModel.setHidegrid(false);
		gridModel.setMultiselect(true);
		gridModel.setSortOrder(SortOrder.asc);
		GridColumnModel<Country> columnModel = new GridColumnModel<Country>("name", 100);
        columnModel.setInitialSort(true);
        columnModel.setCellRenderer(new IGridCellRenderer<Country>() {
        	private static final long serialVersionUID = 1L;

			public String renderCell(Country bean, String propertyPath, int column, int row) {
        		return "<span style=\"color: red;\">"+bean.getName()+"</span>";
        	}
        });
        gridModel.addColumnModel(columnModel);
        
        columnModel = new GridColumnModel<Country>("id", 100);
        columnModel.setSortable(false);
        columnModel.setResizable(false);
        gridModel.addColumnModel(columnModel);
        
        columnModel = new GridColumnModel<Country>("domain", 200);
        gridModel.addColumnModel(columnModel);
               
	}
	
	protected abstract TransferProtocol getTransferProtocol();
       
	
	public String getData() throws Exception {
		IPersistenceUnit persistenceUnit = HSQLDBPersistenceUnit.getInstance();		
		try {
			RequestContext requestContext = RequestContext.get();
			requestContext.setPersistenceUnit(persistenceUnit);		
			requestContext.setUser("test");
			
			innitModel();
			
			try {
				InsertData.checkData();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			} 
			
			Country country = new Country();
			country.setName(getName());
			country.setDomain(getDomain());
			ProviderNavigator<Country> navigator = new ProviderNavigator<Country>(new CountriesProvider(), gridModel, country, "name", "domain");
			navigator.renderData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			RequestContext.unget();
		} 
		return null;
	}	
	
	
    /**
     * Provide default valuie for Message property.
     */
    public static final String MESSAGE = "HelloWorld.message";

    /**
     * Field for Message property.
     */
    private String message;

    /**
     * 
     */
    private String request_locale;
    
    /**
     * Return Message property.
     *
     * @return Message property
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set Message property.
     *
     * @param message Text to display on HelloWorld page.
     */
    public void setMessage(String message) {
        this.message = message;
    }

	/**
	 * @return the request_locale
	 */
	public String getRequest_locale() {
		return request_locale;
	}

	/**
	 * @param request_locale the request_locale to set
	 */
	public void setRequest_locale(String request_locale) {
		this.request_locale = request_locale;
		if(this.request_locale != null && this.request_locale.equals("en")) {
			ActionContext.getContext().setLocale(Locale.ENGLISH);
		}
	}

	/**
	 * @return the gridModel
	 */
	public GridModel<Country> getGridModel() {
		return gridModel;
	}

	/**
	 * @param gridModel the gridModel to set
	 */
	public void setGridModel(GridModel<Country> gridModel) {
		this.gridModel = gridModel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
