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
<jsp:useBean id="muokattavaPizzaId" scope="request"
	type="java.lang.Integer" />
<jsp:useBean id="errors" scope="request" type="java.util.HashMap"
	class="java.util.HashMap" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Muokkaa pizzaa</title>
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
</div>

<div id="otsikkoloota">
<p style="margin-left:15%;">Muokkaa pizza</p>
</div>

	<div id="loota1">





		<%
			int id = muokattavaPizzaId;
			double pizzaHinta = 0;
			String pizzaNimi = "";
			int taytemaara = 0;
			ArrayList<Tayte> vanhatTaytteet = new ArrayList<Tayte>();
			for (int i = 0; i < pizzat.size(); i++) {
				if (pizzat.get(i).getId() == muokattavaPizzaId) {
					pizzaNimi = pizzat.get(i).getNimi();
					pizzaHinta = pizzat.get(i).getHinta();
					vanhatTaytteet = pizzat.get(i).getTaytelista();
				}
			}
		%>
		<form method="post">


			<legend>Syötä pizzan tiedot:</legend>
			
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
					<td><input type="text" name="pizzaNimi" value="<%=pizzaNimi%>" required maxlength="20">
						<%
							if (errors.containsKey("nimi")) {
								out.println("<span class=\"error\">" + errors.get("nimi")
										+ "</span>");
							}
						%></td>
					<td>Max 20 merkkiä</td>
				</tr>
				<tr>
					<td><label>Hinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="pizzaHinta" maxlength="5"
						step="any" min="6" max="99.99" value="<%=pizzaHinta%>" required> <%
 	if (errors.containsKey("hinta")) {
 		out.println("<span class=\"error\">" + errors.get("hinta")
 				+ "</span>");
 	}
 %></td>
					<td>Hinta muotoa 0,00 ja hinnan pitää olla 6,00 - 99,00 euroa</td>
				</tr>
			</table>

			<h3>Täytteet:</h3>
			<br>
			<%
				for (int i = 0; i < taytteet.size(); i++) {				
					for (int j = 0; j < vanhatTaytteet.size(); j++) {
						
							if (vanhatTaytteet.get(j).getId() == taytteet.get(i).getId()) {
			%>
								<input type="checkbox" name="tayte" checked
									value="<%=taytteet.get(i).getId()%>">
								<%=taytteet.get(i).getNimi()%><br>
								
			<%
			taytemaara++;}
							
						}
						
						
					
					if (taytemaara <= i) { %>
					<input type="checkbox" name="tayte"
							value="<%=taytteet.get(i).getId()%>">
							<%=taytteet.get(i).getNimi()%><br><%
							taytemaara++;}
				}
					
				
			%>



			<input type="hidden" name="pizzaId" value="<%=id%>"> <input
				type="submit" class="button" value="Pizza valmis!"> <a
				href="MuokkaaPizzalistaServlet" class="button">Peruuta</a>

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
