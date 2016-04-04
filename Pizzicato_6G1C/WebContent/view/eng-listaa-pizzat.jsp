<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
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
<title>Pizzamenu</title>
</head>
<body>


	<nav id="navigaatio">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Pizzeria Pizzicato</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="" class="current">Front Page</a></li>
			<li><a href="#">:3</a></li>
			<li><a href="#">Contact Info</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					Sign up</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
					Log in</a></li>
		</ul>
	</div>
	</nav>

<!-- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat -->

	<div id="loota1">
	
<!-- pizzalista on toteutettu taulukkona -->
	
		<div id="pizzalistataulukko">

			<table>
				<caption>Pizza menu</caption>

				<tr>
					<th>Number</th>
					<th>Name</th>
					<th>Price</th>
				</tr>

				<%
					DecimalFormat decimal = new DecimalFormat("0.00");
					int pizzanumero = 0;
					for (int i = 0; i < pizzat.size(); i++) {

						if (pizzat.get(i).getNakyvyys() == 1) {
							pizzanumero++;
				%>


				<tr>
					<td width="100px"><%=pizzanumero%></td>
					<td><b><%=pizzat.get(i).getNimi()%><br> Toppings:</b> <%
 	for (int j = 0; j < pizzat.get(i).getTaytelista().size(); j++) { //kukkuu
 %> <%=pizzat.get(i).getTaytelista().get(j)
								.getNimi_eng()%><%if (j+1 < pizzat.get(i).getTaytelista().size()) {%>, <%}
 	}
 %></td>
					<td width="50px"><%=decimal.format(pizzat.get(i).getHinta())%></td>
				</tr>
				<%
					}
					}
				%>
			</table>
		</div>
		
		<div id="nappulasijainti">
			<a href="MuokkaaPizzalistaServlet" class="button">Siirry
				pizzalistan <br> muokkaukseen</a>
				<br>
			<a href="MuokkaaTayteListaServlet" class="button">Siirry
				täytelistan <br> muokkaukseen</a>	
		</div>
	</div>
	
<!-- footer_sisältö divillä pystyy liikuttamaan sisällön sijaintia ym. -->

	<div id="footer">
		<div id="footer_sisältö">

			<p>
				Pizzeria Pizzicato<br> Phone: 01245678<br>
				Email: pizzicato@xxx.fi<br> Address: katu21 <br>
				City: Helsinki, Zip Code: 010101 <br>
			</p>
		</div>

	</div>
</body>
</html>