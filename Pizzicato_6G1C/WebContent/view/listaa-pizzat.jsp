<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pizzalista</title>
</head>
<body>
<h1>Pizzat</h1>


<div id="pizzalistataulukko">

<table>
<tr>
<th>Pizza</th>
<th>Täytteet</th>
<th>Hinta</th>
</tr>

<tr>
<td>Julia</td>
<td>Kinkku, katkarapu, aurajuusto, ananas</td>
<td>7,90€</td>
</tr>

</table>
</div>


<div id="nappulat">
 <input type="submit" value="Muokkaa pizzalistaa">
 </div>
</body>
</html>