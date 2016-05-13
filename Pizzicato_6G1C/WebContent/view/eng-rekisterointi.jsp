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
<title>Registration</title>
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
				Open: Mon-Sat 11-21, Sun 12-18<br> +358 40 666 666<br>
				Kuusitie 66<br> Meilahti, 00270
			</p3>
		</div>
		<div id="lootaoikea">

			<a href="LoginServlet" class="button2">Login</a>

		</div>
	</div>

	<div id="otsikkoloota">
		<p2>Register</p2>
	</div>

	<div id="loota1">

		<form method="post">
			<fieldset>
				<legend>Fill you information:</legend>

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
				<label>First name: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaEtunimi" placeholder="First name"
					maxlength="30"
					value="${uusiKayttaja.etunimi}" required>   Maximum 30 characters 
				</p>
				<p>
				<label>Last name: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaSukunimi" placeholder="Last name"
					maxlength="30"
					value="${uusiKayttaja.sukunimi}" required>   Maximum 30 characters 
				</p>
				<p>
				<label>Password: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaSalasana" placeholder="Password"
					maxlength="30"
					value="${uusiKayttaja.salasana}" required>   Maximum 30 characters 
				</p>
				<p>
				<label>Phone: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaPuh" placeholder="Phone"
					maxlength="10"
					value="${uusiKayttaja.puh}" required>   Maximum 10 characters  
				</p>
				<p>
				<label>Address: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaOsoite" placeholder="Address"
					maxlength="30"
					value="${uusiKayttaja.osoite}" required>  Maximum 30 characters 
				</p>
				<p>
				<label>Zip code: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaPostinro" placeholder="Zip code"
					maxlength="5"
					value="${uusiKayttaja.postinro}" required>   Maximum 5 characters 
				</p>
				<p>
				<label>Postal area: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaPostitmp" placeholder="Postal area"
					maxlength="30"
					value="${uusiKayttaja.postitmp}" required>  Maximum 30 characters 
				</p>
				<p>
				<label>Email: <span class="pakollinen">*</span></label> <input
					type="text" name="kayttajaEmail" placeholder="Email"
					maxlength="60"
					value="${uusiKayttaja.email}" required>   Maximum 60 characters  
				</p>
				
				<br><input type="submit" class="button" value="Confirm">
				 <a href="ListaaPizzatServlet" class="button">Cancel</a>
			</fieldset>
		</form>
	</div>
	
	<div id="footer">
		<div id="footer_sisältö">
			<p>
				Pizzeria Pizzicato<br> Phone: +358 40 666 666<br>
				Email: pizzeria.pizzicato@gmail.com<br> Street address:
				Kuusitie  66 <br> Postal area: Meilahti, 00270 <br>
			</p>
		</div>
	</div>

</body>
</html>