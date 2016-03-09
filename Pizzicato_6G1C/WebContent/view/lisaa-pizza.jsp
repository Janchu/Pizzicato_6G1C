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
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Lisää pizza</title>
</head>
<body>

<nav id="navigaatio">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Pizzeria Pizzicato</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="ListaaPizzatServlet">Etusivu</a></li>
      <li><a href="#">:3</a></li>
      <li><a href="#">Yhteystiedot</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Rekisteröidy</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Kirjaudu sisään</a></li>
    </ul>
  </div>
</nav>

<div id="loota1">
	
	<h1>Syötä pizzan tiedot</h1>
	<form method="post">
		Pizzan nimi: 	<input type="text" name="pizzaNimi"><br>
		Hinta:			<input type="text" name="pizzaHinta"><br>
		Täytteet:<br> 		<% for (int i = 0; i < taytteet.size(); i++) { %>
							<input type="checkbox" name="tayte<%=taytteet.get(i).getId()%>" value="<%=taytteet.get(i).getId()%>"><%=taytteet.get(i).getNimi()%><br>
						<% } %>
						<input type="submit" value="Pizza valmis!">
						<a href="ListaaPizzatServlet" class="nappula">Peruuta</a>
		
	</form>
</body>
</html>