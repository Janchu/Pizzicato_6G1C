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

<title>Ostoskori</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">
				<a href="OmistajaListaaPizzatServlet"><img alt="Pizzerian logo"
					src="images/pizzalogofin.png" height="100%" width="100%"></a>
			</div>
		</div>
		<div id="lootakeski">
			<p
				style="margin-top: 100px; font-size: 170%; font-family: Kozuka Gothic Pro EL;">
				Avoinna: Ma-La 11-21, Su 12-18<br> +358 40 666 666<br>
				Kuusitie 66<br> Meilahti, 00270
			</p>
		</div>

		<div id="lootaoikea">
			<% if (kayttaja.getTyyppi() != null) { %>
			<span class="valkoinen">Tervetuloa, <%=kayttaja.getEtunimi() %></span>
			<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
		<% } else { %>
			<a href="RekisterointiServlet" class="button2">Rekisteröidy</a>
			<a href="LoginServlet" class="button2">Kirjaudu Sisään</a>
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
					
					
					<legend>Tilaajan tiedot</legend>
					
					<%  %>
					
					
						<p>
							<label>Toimitustapa:</label><input type="radio" value="nouto" name="toimitus" >Nouto <input type="radio" value="kotiinkuljetus" name="toimitus" >Kotiinkuljetus
						</p>
						<p>
							<label>Maksutapa:</label><input type="radio" value="kateinen" name="maksutapa" >Käteinen <input type="radio" value="kortti" name="maksutapa" >Kortti
						</p>
						<p>
							<label>Etunimi: </label><input type="text" name="etunimi" value="<%if (request.getAttribute("etunimi") != null) { %><%=request.getAttribute("etunimi")%><%}%>"><%if (errors.get("etunimi") != null) {%><span class="error"><%=errors.get("etunimi") %></span><%} %>
						</p>
						<p>
							<label>Sukunimi: </label><input type="text" name="sukunimi" value="<%if (request.getAttribute("sukunimi") != null) { %><%=request.getAttribute("sukunimi")%><%}%>"><%if (errors.get("sukunimi") != null) {%><span class="error"><%=errors.get("sukunimi") %></span><%} %>
						</p>
						<p>
							<label>Puh: </label><input type="text" name="puh" value="<%if (request.getAttribute("puh") != null) { %><%=request.getAttribute("puh")%><%}%>"><%if (errors.get("puh") != null) {%><span class="error"><%=errors.get("puh") %></span><%} %>
						</p>
						<p>
							<label>Email: </label><input type="text" name="email" value="<%if (request.getAttribute("email") != null) { %><%=request.getAttribute("email")%><%}%>"><%if (errors.get("email") != null) {%><span class="error"><%=errors.get("email") %></span><%} %>
						</p>
						<p>
							<label>Osoite: </label><input type="text" name="osoite" value="<%if (request.getAttribute("osoite") != null) { %><%=request.getAttribute("osoite")%><%}%>"><%if (errors.get("osoite") != null) {%><span class="error"><%=errors.get("osoite") %></span><%} %>
						</p>
						<p>
							<label>Postinumero: </label><input type="text" name="postinro" value="<%if (request.getAttribute("postinro") != null) { %><%=request.getAttribute("postinro")%><%}%>"><%if (errors.get("postinro") != null) {%><span class="error"><%=errors.get("postinro") %></span><%} %>
						</p>
						<p>
							<label>Postitoimipaikka: </label><input type="text"
								name="postitmp" value="<%if (request.getAttribute("postitmp") != null) { %><%=request.getAttribute("postitmp")%><%}%>"><%if (errors.get("postitmp") != null) {%><span class="error"><%=errors.get("postitmp") %></span><%} %>
						</p>
						<p>
							<label>Lisätiedot: </label><textarea rows="4" cols="50" value="<%if (request.getAttribute("lisatiedot") != null) { %><%=request.getAttribute("lisatiedot")%><%}%>"><%if (errors.get("lisatiedot") != null) {%><span class="error"><%=errors.get("lisatiedot") %></span><%} %>
						</textarea></p>
						
						<a href="ListaaPizzatServlet" class="button2">Etusivulle</a> <input type="submit" value="Jatka tilausta" class="button2">
						
					</fieldset>
				</form>

				

			</div>
		</div>
	</div>

	<!-- footer_sisältö divillä pystyy liikuttamaan sisällön sijaintia ym. -->

	<div id="footer">
		<div id="footer_sisältö">

			<p>
				Pizzeria Pizzicato<br> Puhelin: +358 40 666 666<br>
				Sähköpostiosoite: pizzeria.pizzicato@gmail.com<br> Katuosoite:
				Kuusitie 66 <br> Postitoimipaikka: Meilahti, 00270 <br>
			</p>
		</div>

	</div>
</body>
</html>
