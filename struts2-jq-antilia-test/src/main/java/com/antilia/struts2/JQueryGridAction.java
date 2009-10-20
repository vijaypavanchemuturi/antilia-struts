package com.antilia.struts2;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.antilia.struts2.data.MockPersonsProvider;
import com.antilia.struts2.data.Person;
import com.antilia.struts2.jquery.model.GridColumnModel;
import com.antilia.struts2.jquery.model.GridModel;
import com.antilia.struts2.jquery.model.IGridCellRenderer;
import com.antilia.struts2.jquery.model.SortOrder;
import com.antilia.struts2.jquery.model.GridColumnModel.Alignment;
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
public class JQueryGridAction extends ExampleSupport {

    private static final long serialVersionUID = 1L;

	
	private GridModel<Person>  gridModel;
	
  
	private GridModel<Person>  gridModel2;
     
	public String execute() throws Exception {    	   
    	setRequest_locale((String)ServletActionContext.getRequest().getParameter("request_locale"));
        setMessage(getText(MESSAGE));
        
           
        
        innitModel();

        return SUCCESS;
    }
	
	private void innitModel() {
		gridModel = new GridModel<Person>(Person.class);
		gridModel.setCaption("Persons");
		gridModel.setSortOrder(SortOrder.desc);
		gridModel.setHeight("250px");
        gridModel.setWidth(900);
        gridModel.setShrinkToFit(false);        
		GridColumnModel<Person> columnModel = new GridColumnModel<Person>("name", 100);
        columnModel.setInitialSort(true);
        columnModel.setCellRenderer(new IGridCellRenderer<Person>() {
        	private static final long serialVersionUID = 1L;

			public String renderCell(Person bean, String propertyPath, int column, int row) {
        		return "<span style=\"color: red;\">"+bean.getName()+"</span>";
        	}
        });
        gridModel.addColumnModel(columnModel);
        
        columnModel = new GridColumnModel<Person>("lastnames", 100);
        gridModel.addColumnModel(columnModel);
        
        columnModel = new GridColumnModel<Person>("email", 200);
        gridModel.addColumnModel(columnModel);
        
        columnModel = new GridColumnModel<Person>("address", 200);
        columnModel.setSortable(false);
        columnModel.setAlignment(Alignment.CENTER);
        gridModel.addColumnModel(columnModel);
        
        columnModel = new GridColumnModel<Person>("phoneNumber", 100);
        columnModel.setAlignment(Alignment.RIGHT);
        gridModel.addColumnModel(columnModel);
        
        // gridModel2
        
        gridModel2 = new GridModel<Person>(Person.class);
        //gridModel2.setHeight("250px");
        //gridModel2.setWidth(900);
        //gridModel2.setShrinkToFit(false);
        gridModel2.setCaption("Persons");
        gridModel2.setSortOrder(SortOrder.desc);
		columnModel = new GridColumnModel<Person>("name", 100);
        columnModel.setInitialSort(true);
        columnModel.setCellRenderer(new IGridCellRenderer<Person>() {
        	private static final long serialVersionUID = 1L;

			public String renderCell(Person bean, String propertyPath, int column, int row) {
        		return "<span style=\"color: red;\">"+bean.getName()+"</span>";
        	}
        });
        gridModel2.addColumnModel(columnModel);
        
        columnModel = new GridColumnModel<Person>("lastnames", 100);
        gridModel2.addColumnModel(columnModel);
        
        columnModel = new GridColumnModel<Person>("email", 200);
        gridModel2.addColumnModel(columnModel);
        
        columnModel = new GridColumnModel<Person>("address", 200);
        columnModel.setSortable(false);
        columnModel.setAlignment(Alignment.CENTER);
        gridModel2.addColumnModel(columnModel);
        
	}
       
	
	public String xmlData() throws Exception {
		innitModel();
		ProviderNavigator<Person> navigator = new ProviderNavigator<Person>(MockPersonsProvider.getInstance(), gridModel, new Person());				
		navigator.renderData();	
		return null;
	}
	
	public String jsonData() throws Exception {    	   
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json-comment-filtered"); 
		PrintWriter out = response.getWriter(); 
		out.print("{\"page\":\"1\",\"total\":2,\"records\":\"13\",");
		out.print("\"rows\":[");
		out.print("{\"id\":\"13\",\"cell\":[\"13\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}");
		out.print(",{\"id\":\"12\",\"cell\":[\"12\",\"2007-10-06\",\"Client 2\",\"700.00\",\"140.00\",\"840.00\",null]}");
		out.print(",{\"id\":\"11\",\"cell\":[\"11\",\"2007-10-06\",\"Client 1\",\"600.00\",\"120.00\",\"720.00\",null]}");
		out.print(",{\"id\":\"10\",\"cell\":[\"10\",\"2007-10-06\",\"Client 2\",\"100.00\",\"20.00\",\"120.00\",null]}");
		out.print(",{\"id\":\"9\",\"cell\":[\"9\",\"2007-10-06\",\"Client 1\",\"200.00\",\"40.00\",\"240.00\",null]}");
		out.print(",{\"id\":\"8\",\"cell\":[\"8\",\"2007-10-06\",\"Client 3\",\"200.00\",\"0.00\",\"200.00\",null]}");
		out.print(",{\"id\":\"7\",\"cell\":[\"7\",\"2007-10-05\",\"Client 2\",\"120.00\",\"12.00\",\"134.00\",null]}");
		out.print(",{\"id\":\"6\",\"cell\":[\"6\",\"2007-10-05\",\"Client 1\",\"50.00\",\"10.00\",\"60.00\",null]}");
		out.print(",{\"id\":\"5\",\"cell\":[\"5\",\"2007-10-05\",\"Client 3\",\"100.00\",\"0.00\",\"100.00\",\"no tax\"]}");
		out.print(",{\"id\":\"4\",\"cell\":[\"4\",\"2007-10-04\",\"Client 3\",\"150.00\",\"0.00\",\"150.00\",\"no tax\"]}]");
		out.print(",\"userdata\":{\"amount\":3220,\"tax\":342,\"total\":3564,\"name\":\"Totals:\"}}"); 		
		out.flush();		
		return null;
    }
	
	
	
	public Map<String, String> getOptions() throws Exception {    	   
		 Map<String,String> options = new HashMap<String,String>(); 
		 options.put("Florida", "FL"); 
		 options.put("Alabama", "AL"); 
		 return options;
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
	public GridModel<Person> getGridModel() {
		return gridModel;
	}


	/**
	 * @param gridModel the gridModel to set
	 */
	public void setGridModel(GridModel<Person> gridModel) {
		this.gridModel = gridModel;
	}

	/**
	 * @return the gridModel2
	 */
	public GridModel<Person> getGridModel2() {
		return gridModel2;
	}

	/**
	 * @param gridModel2 the gridModel2 to set
	 */
	public void setGridModel2(GridModel<Person> gridModel2) {
		this.gridModel2 = gridModel2;
	}
}
