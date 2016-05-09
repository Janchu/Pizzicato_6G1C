<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.Kayttaja"%>
<jsp:useBean id="tilaus" scope="request" class="pizzicato.model.Tilaus" />
<jsp:useBean id="tilaaja" scope="request" class="pizzicato.model.Kayttaja" />
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<title>Tilauksen yhteenveto</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">
				<a href="ListaaPizzatServlet"><img alt="Pizzerian logo"
					src="images/pizzalogofin.png" height="100%" width="100%"></a>
			</div>
		</div>
		<div id="lootakeski">
			<p3>
				Avoinna: Ma-La 11-21, Su 12-18<br> +358 40 666 666<br>
				Kuusitie 66<br> Meilahti, 00270
			</p3>
		</div>

		<div id="lootaoikea">
			<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
		</div>

	</div>

	<div id="otsikkoloota">
		<p2 style="margin-left: 15%;">Tilauksen yhteenveto</p2>

	</div>

	<%--- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat --%>

	<div id="loota1">
		
		<fieldset>
		<legend>Tilatut tuotteet:</legend>
		<% DecimalFormat formatter = new DecimalFormat("#0.00"); %>
		<% for (int i = 0; i < tilaus.getTilausrivit().size(); i++) { %>
		<p><b><%=tilaus.getTilausrivit().get(i).getTilattuTuote().getNimi() %></b>
		<%=formatter.format(tilaus.getTilausrivit().get(i).getTilattuTuote().getHinta()) %> <b>€</b> x
		<%=tilaus.getTilausrivit().get(i).getLkm() %>
		<br>
		<%for (int j = 0; j < tilaus.getTilausrivit().get(i).getMaustelista().size(); j++) {%><%=tilaus.getTilausrivit().get(i).getMaustelista().get(j).getNimi() %><%if (j+1 < tilaus.getTilausrivit().get(i).getMaustelista().size()) {%>,<%} %>
		
		
		<% }
		%></p><%
		}
		 %><br><p><b>Yhteensä: <%=formatter.format(tilaus.getYhthinta()) %> €</b></p><br>
		 
		<legend>Tilaajan tiedot:</legend>
		<p>
		<label>Tilaaja: </label> <%=tilaaja.getEtunimi() %> <%=tilaaja.getSukunimi() %>
		</p>
		<p>
		<label>Osoite: </label> <%=tilaaja.getOsoite() %>, <%=tilaaja.getPostinro() %> <%=tilaaja.getPostitmp() %>
		</p>
		<p>
		<label>Puhelin: </label> <%=tilaaja.getPuh() %>
		</p>
		<p>
		<label>Email: </label> <%=tilaaja.getEmail() %>
		</p>
		<br>
		
		<legend>Tilauksen tiedot:</legend>
		<p>
		<label>Toimitustapa: </label> <%=tilaus.getToimitus()%>
		</p>
		<p>
		<label>Maksutapa: </label> <%=tilaus.getMaksutapa() %>
		</p>
		<p>
		<label>Lisätiedot: </label> <%=tilaus.getLisatiedot() %>
		<br>
		</p>
		
		
		</fieldset>
		
		<form method="post">
		
		<input type="submit" value="Tee tilaus" class="button2" onclick="return confirm('Kiitos tilauksesta!')"> <a href="ListaaPizzatServlet" class="button">Peruuta</a>
		</form>
	</div>

	<%-- footer_sisältö divillä pystyy liikuttamaan sisällön sijaintia ym. --%>

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
