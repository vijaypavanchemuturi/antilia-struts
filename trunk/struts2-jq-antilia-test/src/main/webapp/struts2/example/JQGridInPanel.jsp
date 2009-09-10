<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="antsj" uri="/struts-ant-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>	
    <title><s:text name="HelloWorld.message"/></title>
   	<antsj:head locale="es" jqgrid="true"/>   
   	<style type="text/css">
   		.ui-dialog-title {
   			font-size: 12px;
   		}
   	</style>	
   			
</head>

<body>
<s:url id="url" action="Index">
</s:url>
<s:a href="%{url}">Back to examples</s:a>

<p>
	Display Tag
</p>
<sj:dialog id="mydialog1" title="Local Dialog" width="800" height="300">
	<div style="width: 700px;">
		<antsj:grid id="list1" url="XMLData.action" gridModel="gridModel"/>
	</div>
</sj:dialog>

          
</body>
</html>
