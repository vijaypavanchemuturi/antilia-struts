<#--
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
-->

<#if parameters.customBasepath?if_exists == "">
  <#assign basePath="struts/ant-themes">
<#else>
  <#assign basePath="${parameters.customBasepath?string}">
</#if>
<#if parameters.compressed?default(true)>
  <#assign jqueryFile="jquery-1.3.2.min.js">
  <#assign jqueryCookieFile="jquery.cookie.min.js">
  <#assign jqueryUIFile="jquery-ui-1.7.2.min.js">
  <#assign jqueryBGIFile="jquery.bgiframe.min.js">
<#else>
  <#assign jqueryFile="jquery-1.3.2.js">
  <#assign jqueryCookieFile="jquery.cookie.js">
  <#assign jqueryUIFile="jquery-ui-1.7.2.js">
  <#assign jqueryBGIFile="jquery.bgiframe.js">
</#if>

<#if parameters.jqgrid?default(false)>
	<link rel="stylesheet" type="text/css" media="screen" href="${base}/${basePath}/redmond/jquery-ui-1.7.1.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${base}/${basePath}/ui.jqgrid.css" />
	<script type="text/javascript" src="${base}/struts/ant-js/${jqueryFile}"></script>
	<script src="${base}/struts/ant-js/jquery-ui-1.7.2.min.js" type="text/javascript"></script>
	<script src="${base}/struts/ant-js/jquery.layout.js" type="text/javascript"></script>	
	<script type="text/javascript" src="${base}/struts/ant-js/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="${base}/struts/ant-js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript"src="${base}/struts/ant-js/jquery.tablednd.js"></script>
	<script type="text/javascript" src="${base}/struts/ant-js/jquery.contextmenu.js" ></script>
</#if>
  