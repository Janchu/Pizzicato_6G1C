<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>" scope="request" />
<jsp:useBean id="muokattavaTayteId" scope="request"	type="java.lang.Integer" />
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
	</div>

	<div id="otsikkoloota">
		<p2 style="margin-left: 15%;">Täytelista</p2>
	</div>
	<div id="loota1">

		<%
			int id = muokattavaTayteId;
			double tayteHinta = 0;
			double tayteKilohinta = 0;
			String tayteNimi = "";
			String tayteNimi_eng = "";

			ArrayList<Tayte> vanhatTaytteet = new ArrayList<Tayte>();

			for (int i = 0; i < taytteet.size(); i++) {
				if (id == taytteet.get(i).getId()) {
					tayteNimi = taytteet.get(i).getNimi();
					tayteNimi_eng = taytteet.get(i).getNimi_eng();
					tayteHinta = taytteet.get(i).getHinta();
					tayteKilohinta = taytteet.get(i).getKilohinta();

				}
			}
		%>
		<form method="post">
			<fieldset>

				<legend>Syötä täytteen tiedot:</legend>

				<%
					if (!errors.isEmpty()) {
				%>
				<div class="pakollinenLoota">
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
								if (errors.containsKey("kilohinta")) {
									out.println("<span class=\"error\">"
											+ errors.get("kilohinta") + "</span><br>");
								}
						%>
					
				</div><br>
				<%
					}
				%>

				<p>
					<label>Täytteen nimi: <span class="pakollinen">*</span></label> <input
						type="text" name="tayteNimi" maxlength="20"
						title="Täytteen nimi saa olla max 20 merkkiä pitkä" required
						value="<%=tayteNimi%>"> Max 20 merkkiä
				</p>

				<p>
					<label>Englanninkielinen nimi: <span
						class="pakollinen">*</span></label> <input type="text"
						name="tayteNimi_eng" maxlength="20"
						title="Täytteen nimi saa olla max 20 merkkiä pitkä" required
						value="<%=tayteNimi_eng%>"> Max 20 merkkiä
				</p>

				<p>
					<label>Hinta: <span class="pakollinen">*</span></label> <input
						type="text" name="tayteHinta" maxlength="5" step="any" min="0.50"
						max="10.00" required value="<%=tayteHinta%>"> Hinta muotoa
					0,00 ja hinnan pitää olla 0,50 - 10,00 euroa
				</p>

				<p>
					<label>Kilohinta: <span class="pakollinen">*</span></label> <input
						type="text" name="tayteKilohinta" maxlength="5" step="any"
						min="0.50" max="99.99" required value="<%=tayteKilohinta%>">
					Hinta muotoa 0,00 ja hinnan pitää olla 0,50 - 99,99 euroa
				</p>



				<input type="hidden" name="tayteId" value="<%=id%>">
				<a href="MuokkaaTaytelistaServlet"><input type="button" class="button" value="Peruuta"></a>
				<input type="submit" class="button" value="Valmis">
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
