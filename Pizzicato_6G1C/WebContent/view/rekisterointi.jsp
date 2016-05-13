<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pizzicato.model.Kayttaja"%>
<jsp:useBean id="kayttajat" type="java.util.ArrayList<pizzicato.model.Kayttaja>" scope="request" />
<jsp:useBean id="errors" scope="request" type="java.util.HashMap" class="java.util.HashMap" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Rekisteröinti</title>
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
			<p3>
				Avoinna: Ma-La 11-21, Su 12-18<br> +358 40 666 666<br>
				Kuusitie 66<br> Meilahti, 00270
			</p3>
		</div>
		<div id="lootaoikea">

			<a href="LoginServlet" class="button2">Login</a>

		</div>
	</div>

	<div id="otsikkoloota">
		<p2>Rekisteröidy</p2>
	</div>

	<div id="loota1">

		<form method="post">
			<fieldset>
				<legend>Syötä tietosi:</legend>

				<%
					if (!errors.isEmpty()) {
				%>
				<div class="pakollinenLoota())">
					<%
						if (errors.containsKey("etunimi")) {
								out.println("<span class=\"error\">"
										+ errors.get("etunimi") + "</span><br>");
							}
							if (errors.containsKey("sukunimi")) {
								out.println("<span class=\"error\">"
										+ errors.get("sukunimi") + "</span><br>");
							}
							if (errors.containsKey("salasana")) {
								out.println("<span class=\"error\">"
										+ errors.get("salasana") + "</span><br>");
							}
							if (errors.containsKey("puh")) {
								out.println("<span class=\"error\">" + errors.get("puh")
										+ "</span><br>");
							}
							if (errors.containsKey("osoite")) {
								out.println("<span class=\"error\">" + errors.get("osoite")
										+ "</span><br>");
							}
							if (errors.containsKey("postinro")) {
								out.println("<span class=\"error\">"
										+ errors.get("postinro") + "</span><br>");
							}
							if (errors.containsKey("postitmp")) {
								out.println("<span class=\"error\">"
										+ errors.get("postitmp") + "</span><br>");
							}
							if (errors.containsKey("email")) {
								out.println("<span class=\"error\">"
										+ errors.get("email") + "</span><br>");
							}
					%>
				</div><br>
				<%
				}
				%>
				<p>
				<label>Etunimi: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaEtunimi" placeholder="Etunimi"
					maxlength="30"
					value="${uusiKayttaja.etunimi}" required>   Max 30 merkkiä 
				</p>
				<p>
				<label>Sukunimi: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaSukunimi" placeholder="Sukunimi"
					maxlength="30"
					value="${uusiKayttaja.sukunimi}" required>   Max 30 merkkiä 
				</p>
				<p>
				<label>Salasana: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaSalasana" placeholder="Salasana"
					maxlength="30"
					value="${uusiKayttaja.salasana}" required>   Max 30 merkkiä 
				</p>
				<p>
				<label>Puhelin: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaPuh" placeholder="Puh"
					maxlength="10"
					value="${uusiKayttaja.puh}" required>   Max 10 merkkiä 
				</p>
				<p>
				<label>Katuosoite: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaOsoite" placeholder="Osoite"
					maxlength="30"
					value="${uusiKayttaja.osoite}" required>   Max 30 merkkiä 
				</p>
				<p>
				<label>Postinro: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaPostinro" placeholder="Postinro"
					maxlength="5"
					value="${uusiKayttaja.postinro}" required>   Max 5 merkkiä 
				</p>
				<p>
				<label>Postitmp: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaPostitmp" placeholder="Postitmp"
					maxlength="30"
					value="${uusiKayttaja.postitmp}" required>   Max 30 merkkiä 
				</p>
				<p>
				<label>Email: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaEmail" placeholder="Email"
					maxlength="60"
					value="${uusiKayttaja.email}" required>   Max 60 merkkiä 
				</p>
				
				<br><input type="submit" class="button" value="Valmis!">
				 <a href="ListaaPizzatServlet" class="button">Peruuta</a>
			</fieldset>
		</form>
	</div>
	
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