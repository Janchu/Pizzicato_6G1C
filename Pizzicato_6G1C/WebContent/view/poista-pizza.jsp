
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pizzicato.model.Pizza"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />
<jsp:useBean id="poistettavapizzaid" scope="request"
	type="java.lang.Integer" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Poista pizza</title>
</head>
<body>

	<%
	int poistaId = 0;
	for (int i = 0; i < pizzat.size(); i++) { //kukkuu
			int id = pizzat.get(i).getId();
			if (id == poistettavapizzaid) {
				poistaId = id;%>
				<%=pizzat.get(i).getNimi()%>
			<% } %>
			
		<%}%>
	
	<form method="post">
	<input type="hidden" name="pid" value="<%=poistaId %>">
	<input type="submit" value="Poista"></form>

</body>
</html>