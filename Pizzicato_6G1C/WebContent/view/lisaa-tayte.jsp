<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pizzicato.model.Tayte"%>
<%@page import="pizzicato.model.Pizza"%>
<jsp:useBean id="errors" scope="request" type="java.util.HashMap" class="java.util.HashMap" />
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
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					Rekisteröidy</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
					Kirjaudu sisään</a></li>
		</ul>
	</div>
	</nav>

	<div id="loota1">


		<form method="post">


			<legend>Syötä täytteen tiedot:</legend>
			<table>
				<tr>
					<td><label>Täytteen nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteNimi" placeholder="Täytteen nimi" maxlength="20" title="Täytteen nimi saa olla max 20 merkkiä pitkä" required value="${uusiTayte.nimi}">
					 <%
               if (errors.containsKey("nimi")) {
                  out.println("<span class=\"error\">" + errors.get("nimi") + "</span>");
               }
            %>
            </td>
					<td>Max 20 merkkiä</td>
				</tr>

<tr>
					<td><label>Täytteen englanninkielinen nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteNimi_eng" placeholder="Täytteen eng.nimi" maxlength="20" title="Täytteen nimi saa olla max 20 merkkiä pitkä" required value="${uusiTayte.nimi_eng}">
					 <%
               if (errors.containsKey("nimi")) {
                  out.println("<span class=\"error\">" + errors.get("nimi") + "</span>");
               }
            %>
            </td>
					<td>Max 20 merkkiä</td>
				</tr>

				
				
				<tr>
					<td><label>Täytteen hinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteHinta" placeholder="Täytteen hinta" maxlength="5" step="any" min="0.50" max="10.00" required value="${uusiTayte.hinta}">
					<%
               if (errors.containsKey("hinta")) {
                  out.println("<span class=\"error\">" + errors.get("hinta") + "</span>");
               }
            %>
            </td>
					<td>Hinnan pitää olla 0,50 - 10,00</td>
				</tr>
				
				<tr>
					<td><label>Kilohinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="Kilohinta" placeholder="Täytteen kilohinta" maxlength="5" step="any" min="0.50" max="99.99" required value="${uusiTayte.kilohinta}">
					<%
               if (errors.containsKey("kilohinta")) {
                  out.println("<span class=\"error\">" + errors.get("kilohinta") + "</span>");
               }
            %>
            </td>
					<td>Hinnan pitää olla 0,50 - 99,99</td>
				</tr>
			</table>

			
			

			<input type="submit" class="button" value="Täyte täydellinen! kyl vitus jee"> <a
				href="MuokkaaTaytelistaServlet" class="button">Peruuta</a>
		</form>
	</div>
</body>
</html>
