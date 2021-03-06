<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pizzicato.model.Tayte"%>
<%@page import="pizzicato.model.Pizza"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<pizzicato.model.Tayte>" scope="request" />
<jsp:useBean id="uusiPizza" type="pizzicato.model.Pizza" scope="request" />
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

				<a href="OmistajaListaaPizzatServlet"><img alt="Pizzerian logo"
					src="images/pizzalogofin.png" height="100%" width="100%"></a>

			</div>
		</div>
		<div id="lootakeski">
			<p3>
				Avoinna: Ma-La 11-21, Su 12-18<br> +358 40 666 666<br>
				Kuusitie 66<br> Meilahti, 00270
			</p>
		</div>
		<div id="lootaoikea">

			<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>

		</div>
	</div>

	<div id="otsikkoloota">
		<p2>Lisää pizza</p2>
	</div>

	<div id="loota1">

		<form method="post">
			<fieldset>
				<legend>Syötä pizzan tiedot:</legend>

				<%-- Validointi --%>
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
					
				</div><br>
				<%
					}
				%>
				<p>
				<label>Pizzan nimi: <span class="pakollinen">*</span></label> <input
					type="text" name="pizzaNimi" placeholder="Pizzan nimi"
					maxlength="20" title="Pizzan nimi saa olla max 20 merkkiä pitkä"
					value="${uusiPizza.nimi}" required>   Max 20 merkkiä 
				</p>
				<p>	
				<label>Hinta:
					<span class="pakollinen">*</span>
				</label> <input type="text" name="pizzaHinta" placeholder="Pizzan hinta"
					maxlength="5" step="any" min="6" max="99.99"
					value="${uusiPizza.hinta}" required>   Hinnan pitää olla 6,00 - 99,99
				</p>


				<legend>Täytteet:</legend>
				<%-- checkboxeilla valitaan täytteet --%>
				<%
					for (int i = 0; i < taytteet.size(); i++) {						
				%>
				<input type="checkbox" name="tayte" <%if (uusiPizza.getTaytelista() != null) { for (int j = 0; j < uusiPizza.getTaytelista().size(); j++) { 
					if (taytteet.get(i).getId() == uusiPizza.getTaytelista().get(j).getId()) {
						%> checked <%
					}
				}
				} %>
					value="<%=taytteet.get(i).getId()%>">
				<%=taytteet.get(i).getNimi()%><br>
				<%
					}
				%>
					

				<br><a href="MuokkaaPizzalistaServlet"><input type="button" class="button" value="Peruuta"></a><input type="submit" class="button" value="Tallenna">

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
