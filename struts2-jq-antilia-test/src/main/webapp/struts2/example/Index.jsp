<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>	
    <title><s:text name="HelloWorld.message"/></title>
</head>

<body>
<h2><s:property value="message"/></h2>

<h3>Languages</h3>
<ul>
    <li>
        <s:url id="url" action="Index">
            <s:param name="request_locale">en</s:param>
        </s:url>
        <s:a href="%{url}">English</s:a>
    </li>
    <li>
        <s:url id="url" action="Index">
            <s:param name="request_locale">es</s:param>
        </s:url>
        <s:a href="%{url}">Espanol</s:a>
    </li>    
</ul>

<h3>List of Examples</h3>

<ul>	
	<li><a href="/struts2/example/AntiliaJQuery">JQuery based GRID (for a static list)</a></li>
	<li><a href="/struts2/example/AntiliaJQueryDB">JQuery based GRID (accessing a database)</a></li>		
</ul>
</body>
</html>
