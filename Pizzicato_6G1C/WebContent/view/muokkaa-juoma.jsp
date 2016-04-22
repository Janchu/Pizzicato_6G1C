<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Juoma"%>
<%@ page import="pizzicato.model.Tuote"%>
<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma>"
	scope="request" />
<jsp:useBean id="muokattavaJuomaId" scope="request"
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
<title>Muokkaa juomaa</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">

				<a href="OmistajaListaaJuomatServlet"><img alt="Pizzerian logo"
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
			<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
		</div>

	</div>
	</div>

	<div id="otsikkoloota">
		<p style="margin-left: 15%;">Muokkaa juoma</p>
	</div>

	<div id="loota1">
		<%
			int id = muokattavaJuomaId;
			String juomaNimi = "";
			String juomaNimi_eng = "";
			double juomaHinta = 0;
			double juomaKoko = 0;
			for(int i = 0; i < juomat.size(); i++) {
				if(juomat.get(i).getId() == muokattavaJuomaId) {
					juomaNimi = juomat.get(i).getNimi();
					juomaNimi_eng = juomat.get(i).getNimi_eng();
					juomaHinta = juomat.get(i).getHinta();
					juomaKoko = juomat.get(i).getKoko();
					
				}
			}
		%>

		<form method="post">

			<legend>Syötä juoman tiedot:</legend>

			<%
				if (!errors.isEmpty()) {
			%>
			<div class="pakollinenLoota">
				<p class="pakollinen">
					<%
						if (errors.containsKey("nimi")) {
								out.println("<span class=\"error\">" + errors.get("nimi")
										+ "</span><br>");
							}
							if (errors.containsKey("nimi_eng")) {
								out.println("<span class=\"error\">"
										+ errors.get("nimi_eng") + "</span><br>");
							}
							if (errors.containsKey("hinta")) {
								out.println("<span class=\"error\">" + errors.get("hinta")
										+ "</span><br>");
							}
							if (errors.containsKey("koko")) {
								out.println("<span class=\"error\">" + errors.get("koko")
										+ "</span><br>");
							}
					%>
				
			</div>
			<%
				}
			%>

			<table>
				<tr>
					<td><label>Juoman nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="juomaNimi" value="<%=juomaNimi%>"
						required maxlength="20"> <%
 	if (errors.containsKey("nimi")) {
 		out.println("<span class=\"error\">" + errors.get("nimi")
 				+ "</span>");
 	}
 %></td>
					<td>Max 20 merkkiä</td>
				</tr>
				<td><label>Juoman englanninkielinen nimi: <span class="pakollinen">*</span></label></td>
				<td><input type="text" name="juomaNimi_eng"
					value="<%=juomaNimi_eng%>" required maxlenght="30"> <%
 	if (errors.containsKey("nimi_eng")) {
 		out.println("<span class=\"error\">" + errors.get("nimi_eng")
 				+ "</span>");
 	}
 %></td>
				<tr>
					<td><label>Hinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="juomaHinta" maxlength="5"
						step="any" min="2.00" max="20.00" value="<%=juomaHinta%>" required>
						<%
							if (errors.containsKey("hinta")) {
								out.println("<span class=\"error\">" + errors.get("hinta")
										+ "</span>");
							}
						%></td>
					<td>Hinta muotoa 0,00 ja hinnan pitää olla 0.50 - 10.00 euroa</td>
				</tr>
				<tr>
					<td><label>Koko: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="juomaKoko" maxlength="4"
					step="any" value="<%=juomaKoko%>" required> <%
					if(errors.containsKey("Koko")) {
					out.println("<span class=\"error\">" + errors.get("koko") + "</span>");
					}
					%></td>
				</tr>
			</table>
			
			

			<%-- seuraavassa koodinpätkässä on nappulat jes --%>
			<input type="hidden" name="juomaId" value="<%=id%>"> <input
				type="submit" class="button" value="Juoma valmis!"> <a
				href="MuokkaaJuomalistaServlet" class="button">Peruuta</a>

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