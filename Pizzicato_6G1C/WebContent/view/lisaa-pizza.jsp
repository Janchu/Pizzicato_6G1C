<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pizzicato.model.Tayte"%>
<%@page import="pizzicato.model.Pizza"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<pizzicato.model.Tayte>" scope="request" />
<jsp:useBean id="pizzat" type="java.util.ArrayList<pizzicato.model.Pizza>" scope="request" />
<jsp:useBean id="errors" scope="request" type="java.util.HashMap" class="java.util.HashMap" />
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Lisää pizza</title>
</head>
<body>

<div id="logoloota">
<div id="lootavasen">
<div class="logo">

<a href="OmistajaListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>

</div>
</div>
<div id="lootakeski">
<p style= "margin-top:100px; font-size:170%; font-family:Kozuka Gothic Pro EL;">
Avoinna: Ma-La 11-21, Su 12-18<br>
+358 40 666 666<br>
Kuusitie 66<br>
Meilahti, 00270</p>
</div>
<div id="lootaoikea">

<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>

</div>
</div>

<div id="otsikkoloota">
	<p>Lisää pizza</p>
</div>

<div id="loota2">

		<form method="post">

			<legend="color:white">Syötä pizzan tiedot:</legend>
			
			<%-- Validointi --%>
			<% if (!errors.isEmpty()) { %>
			<div class="pakollinenLoota">
				<p class="pakollinen">
				<%
               if (errors.containsKey("nimi")) {
                  out.println("<span class=\"error\">" + errors.get("nimi") + "</span><br>");
               }
               if (errors.containsKey("hinta")) {
                  out.println("<span class=\"error\">" + errors.get("hinta") + "</span><br>");
               }
               if (errors.containsKey("taytteet")) {
                   out.println("<span class=\"error\">" + errors.get("taytteet") + "</span><br>");
               }
            %>
			</div>
			<%} %>
			<table>
				<tr>
					<td><label>Pizzan nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="pizzaNimi" placeholder="Pizzan nimi" maxlength="20" title="Pizzan nimi saa olla max 20 merkkiä pitkä"  value="${uusiPizza.nimi}" required>
					 </td>
					<td>Max 20 merkkiä</td>
				</tr>

				<tr>
					<td><label>Hinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="pizzaHinta" placeholder="Pizzan hinta" maxlength="5" step="any" min="6" max="99.99"  value="${uusiPizza.hinta}" required>
					</td>
					<td>Hinnan pitää olla 6,00 - 99,99</td>
				</tr>
			</table>

			<h3>Täytteet:</h3><%-- checkboxeilla valitaan täytteet --%>
			<br>
			<%
				for (int i = 0; i < taytteet.size(); i++) {
			%>
			<input type="checkbox" name="tayte"
				value="<%=taytteet.get(i).getId()%>">
			<%=taytteet.get(i).getNimi()%><br>
			<%
				}
			%>

			<input type="submit" class="button" value="Pizza valmis!"> 
			<a href="MuokkaaPizzalistaServlet" class="button">Peruuta</a>
		</form>
	</div>
	
	<div id="footer">
		<div id="footer_sisältö">
			<p>
				Pizzeria Pizzicato<br> Puhelin: +358 40 666 666<br>
				Sähköpostiosoite: pizzeria.pizzicato@gmail.com<br> Katuosoite: Kuusitie 66 <br>
				Postitoimipaikka: Meilahti, 00270 <br>
			</p>
		</div>
	</div>
</body>
</html>
