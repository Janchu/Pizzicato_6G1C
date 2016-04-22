<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tilausrivi"%>
<%@ page import="pizzicato.model.Tilaus"%>
<jsp:useBean id="ostoskori" type="java.util.ArrayList<Tilausrivi>" scope="request" />




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table>

<tr><td>Nimi</td><td>Hinta</td>

<% for (int i = 0; i < ostoskori.size(); i++) { %>

<tr><td><%=ostoskori.get(i).getTilattuTuote().getNimi() %></td><td><%=ostoskori.get(i).getTilattuTuote().getHinta() %></td><td> <form method="post"><input type="hidden" value="<%= i%>" name="id"><input type="submit" value="Poista korista" name="poista"></form></td></tr>

<% } %>

</table>

</body>
</html>