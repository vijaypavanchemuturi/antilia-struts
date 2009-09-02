/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.antilia.struts2.jquery.views.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.antilia.struts2.jquery.components.Grid;
import com.antilia.struts2.jquery.components.Head;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @see Head
 */
public class GridTag extends AbstractUITag {

  private static final long serialVersionUID = 6876765769175246030L;

  protected String          id;
  protected String          url;
  protected String 			gridModel;

  public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res)
  {
    return new Grid(stack, req, res);
  }

  protected void populateParams()
  {
    super.populateParams();

    Grid grid = (Grid) component;
    grid.setId(id);
    grid.setUrl(url);    
    grid.setGridModel(gridModel);
  }

 
/**
 * @return the url
 */
public String getUrl() {
	return url;
}

/**
 * @param url the url to set
 */
public void setUrl(String url) {
	this.url = url;
}

/**
 * @return the id
 */
public String getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(String id) {
	this.id = id;
}

/**
 * @return the gridModel
 */
public String getGridModel() {
	return gridModel;
}

/**
 * @param gridModel the gridModel to set
 */
public void setGridModel(String gridModel) {
	this.gridModel = gridModel;
}

}
