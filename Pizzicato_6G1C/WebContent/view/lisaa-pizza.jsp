<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pizzicato.model.Tayte"%>
<%@page import="pizzicato.model.Pizza"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<pizzicato.model.Tayte>"
	scope="request" />
<jsp:useBean id="pizzat" type="java.util.ArrayList<pizzicato.model.Pizza>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ebin</h1>
	<%  %>
	<form method="post">
		Pizzan nimi: 	<input type="text" name="vastausteksti"><br>
		Täytteet:<br> 		<% for (int i = 0; i < taytteet.size(); i++) { %>
							<input type="checkbox" name="tayte" value="<%=taytteet.get(i).getId()%>"><%=taytteet.get(i).getNimi()%><br>
						<% } %>
						<input type="submit" value="Pizza valmis!">
		
	</form>
</body>
</html>