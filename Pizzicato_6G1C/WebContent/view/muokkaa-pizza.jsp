<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>"
	scope="request" />
<jsp:useBean id="muokattavapizzaid" scope="request"
	type="java.lang.Integer" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Muokkaa pizzaa</title>
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
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					Rekisteröidy</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
					Kirjaudu sisään</a></li>
		</ul>
	</div>
	</nav>

	<div id="loota1">





		<%
			int id = muokattavapizzaid;
			double pizzaHinta = 0;
			String pizzaNimi = "";
			ArrayList<Tayte> vanhatTaytteet = new ArrayList<Tayte>();
		
			for (int i = 0; i < pizzat.size(); i++) {
				if (id == pizzat.get(i).getId()) {
					pizzaNimi = pizzat.get(i).getNimi();
					pizzaHinta = pizzat.get(i).getHinta();
					vanhatTaytteet = pizzat.get(i).getTaytelista();
				}
			}
		%>
		<form method="post">
		

			<legend>Syötä pizzan tiedot:</legend>
			<table>
				<tr>
					<td><label>Pizzan nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="pizzaNimi" value="<%=pizzaNimi%>"
						required><br>
					<td>
				</tr>
				<tr>
					<td><label>Hinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="pizzaHinta"
						value="<%=pizzaHinta%>" required><br></td>
				</tr>
			</table>





			<input type="hidden" name="pizzaId" value="<%=id %>">
			<input type="submit" class="button" value="Pizza valmis!"> <a
				href="ListaaPizzatServlet" class="button">Peruuta</a>

		</form>
	</div>
</body>
</html>