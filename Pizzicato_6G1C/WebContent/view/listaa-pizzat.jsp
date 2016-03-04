<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<title>Pizzalista</title>
</head>
<body>
<h1>Pizzat</h1>

<nav id="Paavalikko">

<ul>
					<li><a class="tamanhetkinen" href="index.html">Kotisivu</a>
					<li><a href="yhteystiedot.html">Yhteystiedot</a>
</ul>



<div id="pizzalistataulukko">

<table>
<tr>
<th>Numero</th>
<th>Nimi</th>
<th>Hinta</th>
</tr>

<%
			int pizzanumero = 0;
			for (int i = 0; i < pizzat.size(); i++) {
				pizzanumero++;
		%>

<tr>

<td><%=pizzanumero%></td>
<td><%=pizzat.get(i).getNimi()%></td>
<td><%=pizzat.get(i).getHinta()%></td>
</tr>
<%  } %>
</table>
</div>


<div id="nappulat">
 <input type="submit" value="Muokkaa pizzalistaa"><a href="MuokkaaPizzaListaaServlet">
 </div>
</body>
</html>