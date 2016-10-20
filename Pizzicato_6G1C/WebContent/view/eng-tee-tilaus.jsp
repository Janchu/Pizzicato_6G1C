<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="pizzicato.model.Kayttaja"%>
<jsp:useBean id="kayttaja" class="pizzicato.model.Kayttaja" scope="request" />
<jsp:useBean id="errors" scope="request" type="java.util.HashMap" class="java.util.HashMap" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<title>Shopping Cart</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">
				<a href="EngListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>
			</div>
		</div>
		<div id="lootakeski">
			<p3>
				Open: Mon-Sat 11-21, Sun 12-18<br>
				+358 40 666 666<br>
				Kuusitie 66<br>
				Meilahti, 00270
			</p3>
		</div>

		<div id="lootaoikea">
			<% if (kayttaja.getTyyppi() != null) { %>
			<span class="valkoinen">Welcome, <%=kayttaja.getEtunimi() %></span>
			<a href="LogoutServlet" class="button2">Logout</a>
		<% } else { %>
			<a href="LoginServlet" class="button2">Login</a>
		<% } %>

		</div>

	</div>

	<div id="otsikkoloota">
		<p2> </p>

	</div>

	<!-- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat -->

	<div id="loota1">

		<div id="tuotelistataulukko">
			<div id="tablescoller">
			

				<form method="post">
					<fieldset>
					
					
					<legend>Order Information</legend>
					
					<%  %>
					
					
						<p>
							<label>Delivery Method: </label><input type="radio" value="Pickup" name="toimitus" >Pickup <input type="radio" value="Delivery" name="toimitus" >Delivery <%if (errors.get("toimitus") != null) {%><span class="error"><%=errors.get("toimitus") %></span><%} %>
						</p>
						<p>
							<label>Payment Method: </label><input type="radio" value="Cash" name="maksutapa" >Cash <input type="radio" value="Payment card" name="maksutapa" >Payment Card <%if (errors.get("maksutapa") != null) {%><span class="error"><%=errors.get("maksutapa") %></span><%} %>
						</p>
						<p>
							<label>Name: </label><input type="text" name="etunimi" value="<%if (request.getAttribute("etunimi") != null) { %><%=request.getAttribute("etunimi")%><%}%>"><%if (errors.get("etunimi") != null) {%><span class="error"><%=errors.get("etunimi") %></span><%} %>
						</p>
						<p>
							<label>Last Name: </label><input type="text" name="sukunimi" value="<%if (request.getAttribute("sukunimi") != null) { %><%=request.getAttribute("sukunimi")%><%}%>"><%if (errors.get("sukunimi") != null) {%><span class="error"><%=errors.get("sukunimi") %></span><%} %>
						</p>
						<p>
							<label>Phone: </label><input type="text" name="puh" value="<%if (request.getAttribute("puh") != null) { %><%=request.getAttribute("puh")%><%}%>"><%if (errors.get("puh") != null) {%><span class="error"><%=errors.get("puh") %></span><%} %>
						</p>
						<p>
							<label>Email: </label><input type="text" name="email" value="<%if (request.getAttribute("email") != null) { %><%=request.getAttribute("email")%><%}%>"><%if (errors.get("email") != null) {%><span class="error"><%=errors.get("email") %></span><%} %>
						</p>
						<p>
							<label>Address: </label><input type="text" name="osoite" value="<%if (request.getAttribute("osoite") != null) { %><%=request.getAttribute("osoite")%><%}%>"><%if (errors.get("osoite") != null) {%><span class="error"><%=errors.get("osoite") %></span><%} %>
						</p>
						<p>
							<label>Zip Code: </label><input type="text" name="postinro" value="<%if (request.getAttribute("postinro") != null) { %><%=request.getAttribute("postinro")%><%}%>"><%if (errors.get("postinro") != null) {%><span class="error"><%=errors.get("postinro") %></span><%} %>
						</p>
						<p>
							<label>Postal Area: </label><input type="text"
								name="postitmp" value="<%if (request.getAttribute("postitmp") != null) { %><%=request.getAttribute("postitmp")%><%}%>"><%if (errors.get("postitmp") != null) {%><span class="error"><%=errors.get("postitmp") %></span><%} %>
						</p>
						<p>
							<label>Additional Details: </label><textarea name="lisatiedot" rows="4" cols="50"><%if (request.getAttribute("lisatiedot") != null) { %><%=request.getAttribute("lisatiedot")%><%}%></textarea><%if (errors.get("lisatiedot") != null) {%><span class="error"><%=errors.get("lisatiedot") %></span><%} %></p>
						
						<a href="EngListaaPizzatServlet"><input type="button" class="button" value="Front Page"></a> <input type="submit" value="Continue" class="button">
						
					</fieldset>
				</form>

				

			</div>
		</div>
	</div>

	<!-- footer_sisältö divillä pystyy liikuttamaan sisällön sijaintia ym. -->

	<div id="footer">
		<div id="footer_sisältö">

			<p>
				Pizzeria Pizzicato<br> Phone: +358 40 666 666<br>
				Email: pizzeria.pizzicato@gmail.com<br> Address: Kuusitie 66 <br>
				Zip Code: Meilahti, 00270 <br>
			</p>
		</div>

	</div>
</body>
</html>