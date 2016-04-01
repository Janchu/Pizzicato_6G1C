<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>"
	scope="request" />
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
		<div id="pizzalistataulukko">

			<table>
				<caption>Täytelista</caption>
				<tr>

					<th>Numero</th>
					<th>Nimi</th>
					<th>Englanninkielinen nimi</th>
					<th>Hinta</th>
					<th>Kilohinta</th>
					<th>Muokkaa</th>
					<th>Poista</th>
				</tr>

				<%
				DecimalFormat decimal = new DecimalFormat("0.00");
				int taytenumero = 0;
				for (int i = 0; i <taytteet.size(); i++) {
					taytenumero++;
	
				%>

				<tr>

					<td width="50px"><%=taytenumero%></td>
					
					
					
					<td><b><%=taytteet.get(i).getNimi()%></b>
 	
					<td><%=taytteet.get(i).getNimi_eng()%></td>
					<td width="100px"><%=decimal.format(taytteet.get(i).getHinta())%></td>
					<td width="100px"><%=decimal.format(taytteet.get(i).getKilohinta())%></td>
					<td width="50px"><a href="MuokkaaTayteServlet?TId=<%=taytteet.get(i).getId()%>"><input
							type="submit" value="Muokkaa täytettä"></a></td>
					<td width="50px"><a href="PoistaTayteServlet?TayId=<%=taytteet.get(i).getId()%>"
						onclick="return confirm('Haluatko varmasti poistaa Täytteen?')"><input
							type="submit" value="Poista täyte"></a></td>
				</tr>

				<%
					}
				%><tr>
			</table>
		</div>

		<div id="nappulasijainti">
			<a href="LisaaTayteServlet" class="button">Lisää täyte</a>
			<br> <a href="ListaaPizzatServlet" class="button">Poistu <br> muokkaustilasta</a>
		</div>
	</div>


</body>
</html>
