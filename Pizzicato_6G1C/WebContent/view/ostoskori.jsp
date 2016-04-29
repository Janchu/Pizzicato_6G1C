<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.Tilausrivi"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>" scope="request" />
<jsp:useBean id="ostoskori" type="java.util.ArrayList<Tilausrivi>" scope="request" />

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
			<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
		</div>

	</div>

	<div id="otsikkoloota">
		<p style="margin-left: 15%;">Ostoskori</p>

	</div>

	<%--- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat --%>

	<div id="loota1">





		<div id="tuotelistataulukko">
			<div id="tablescoller">



				<%
					if (ostoskori.size() < 1) {
						String korityhja = "Ostoskorisi on tyhjä! :(";
				%>
				<%=korityhja%>
				<a href="ListaaPizzatServlet" class="button2">Etusivulle</a>

				<table>

					<tr>
						<td>Nimi</td>
						<td>Hinta</td>
						<td>Lkm</td>
						<td>Mausteet</td>

						<%
							} else {
								
								ArrayList<Tayte> taytteet = new ArrayList<Tayte>();

								for (int i = 0; i < ostoskori.size(); i++) {
									for (int j = 0; j < pizzat.size(); j++) {
										if (ostoskori.get(i).getTilattuTuote().getId() == pizzat.get(j).getId()) {
											taytteet = pizzat.get(j).getTaytelista();
									}
								}
						%>
					
					<tr>
						<td><%=ostoskori.get(i).getTilattuTuote().getNimi()%> <% for (int j = 0; j < taytteet.size(); j++) {%><%=taytteet.get(j).getNimi() %><%if (j + 1 < pizzat.get(i).getTaytelista().size()) {%>, <% }} %></td>
						<td><%=ostoskori.get(i).getTilattuTuote().getHinta()%></td>
						<td><%=ostoskori.get(i).getLkm()%></td>
						<td><% for (int j = 0; j < ostoskori.get(i).getMaustelista().size(); j++) {%> <%=ostoskori.get(i).getMaustelista().get(j).getNimi() %> <% } %></td>
						<td>
							<form action="PoistaKoristaServlet" method="post">
								<input type="hidden" value="<%=i%>" name="id"><input
									type="submit" value="Poista korista" name="poista">
							</form>
						</td>
					</tr>



				</table>



				<%
					}
				%><a href="ListaaPizzatServlet" class="button2">Etusivulle</a> <a
					href="TeeTilausServlet" class="button2">Jatka tilausta</a>
				<%
					}
				%>

			</div>
		</div>
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