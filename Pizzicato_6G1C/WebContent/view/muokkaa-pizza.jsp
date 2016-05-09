<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>" scope="request" />
<jsp:useBean id="muokattavaPizza" scope="request" class="pizzicato.model.Pizza" />
<jsp:useBean id="errors" scope="request" type="java.util.HashMap" class="java.util.HashMap" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Muokkaa pizzaa</title>
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
			<span class="valkoinen">Tervetuloa, Outi  </span><a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
		</div>

	</div>
	

	<div id="otsikkoloota">
		<p2>Muokkaa pizzaa</p2>
	</div>

	<div id="loota1">
		<form method="post">
			<fieldset>

				<legend>Syötä pizzan tiedot:</legend>

				<%
					if (!errors.isEmpty()) {
				%>
				<div class="pakollinenLoota">

					<%
						if (errors.containsKey("nimi")) {
								out.println("<span class=\"error\">" + errors.get("nimi")
										+ "</span><br>");
							}
							if (errors.containsKey("hinta")) {
								out.println("<span class=\"error\">" + errors.get("hinta")
										+ "</span><br>");
							}
							if (errors.containsKey("taytteet")) {
								out.println("<span class=\"error\">"
										+ errors.get("taytteet") + "</span><br>");
							}
					%>
					
				</div>
				<br>
				<%
					}
				%>

				<p>
					<label>Pizzan nimi: <span class="pakollinen">*</span></label>
					<input type="text" name="pizzaNimi" value="<%=muokattavaPizza.getNimi()%>" 
					required maxlength="20">
					<%
						if (errors.containsKey("nimi")) {
							out.println("<span class=\"error\">" + errors.get("nimi")
									+ "</span>");
						}
					%>
					Max 20 merkkiä
				</p>
				<p>
					<label>Hinta: <span class="pakollinen">*</span></label> <input
						type="text" name="pizzaHinta" maxlength="5" step="any" min="6"
						max="99.99" value="<%=muokattavaPizza.getHinta()%>" required>
					<%
						if (errors.containsKey("hinta")) {
							out.println("<span class=\"error\">" + errors.get("hinta")
									+ "</span>");
						}
					%>
					Hinta muotoa 0,00 ja hinnan pitää olla 6,00 - 99,00 euroa
				</p>



				<legend>Täytteet:</legend>

				<%
					for (int i = 0; i < taytteet.size(); i++) {						
				%>
				<input type="checkbox" name="tayte" <%if (muokattavaPizza.getTaytelista() != null) { for (int j = 0; j < muokattavaPizza.getTaytelista().size(); j++) { 
					if (taytteet.get(i).getId() == muokattavaPizza.getTaytelista().get(j).getId()) {
						%> checked <%
					}
				}
				} %>
					value="<%=taytteet.get(i).getId()%>">
				<%=taytteet.get(i).getNimi()%><br>
				<%
					}
				%>


				<%-- seuraavassa koodinpätkässä on nappulat jes --%>
				<br><input type="hidden" name="pizzaId" value="<%=muokattavaPizza.getId()%>"> <input
					type="submit" class="button" value="Pizza valmis!"> <a
					href="MuokkaaPizzalistaServlet" class="button">Peruuta</a>
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
