<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="antsj" uri="/struts-ant-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>	
    <title><s:text name="HelloWorld.message"/></title>
   	<antsj:head locale="es" jqgrid="true"/>   
</head>

<body>
<s:url id="url" action="Index">
</s:url>
<s:a href="%{url}">Back to examples</s:a>
       
<h2>Accessing a DATABase, communicating with the server via JSON </h2>

<antsj:grid id="list1" url="JSONDataDB.action" gridModel="gridModel"/>
    
</body>
</html>
