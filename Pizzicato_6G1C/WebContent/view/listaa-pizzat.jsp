<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<%@ page import="pizzicato.model.Mauste"%>
<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.Tilausrivi"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>" scope="request" />
<jsp:useBean id="ostoskori" type="java.util.ArrayList<Tilausrivi>" scope="request" />
<jsp:useBean id="mausteet" type="java.util.ArrayList<Mauste>" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Listaa pizzat</title>
</head>
<body>

	<%-- Logolootassa on "header" ja se kootuu kolmesta eri lootasta, jotka
 järjestävät logolootan sisällön. Vasemmassa lootassa on logo, keskimmäisessä
 yhteystiedot ja oikeanpuolimmaisessa kirjautuminen ja kielenvaihto --%>

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
			<a href="RekisterointiServlet" class="button2">Rekisteröidy</a>
			<a href="LoginServlet" class="button2">Kirjaudu Sisään</a> <a
				href="EngListaaPizzatServlet"><img alt="lib"
				src="images/uklib4.png" width="32" height="32"></a> <a
				href="ListaaPizzatServlet"><img alt="lib"
				src="images/finlib.png" width="32" height="32"></a><br>
			<div id="ostoskoributton2">
			<form method="get">
				<input type="hidden" name="ostoskori" value="<%=ostoskori%>">
				<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.size()%>
				kpl, yht. 
				<div id="ostoskoributton1">
					<a href="OstoskoriServlet">Ostoskoriin</a>
				</div>
				</form>
			</div>
		</div>
	</div>
	
	<%-- tämä tulee näkyville, kun menee 1000px alle --%>
	<div id="ostoskoributton3">
			<form method="get">
				<input type="hidden" name="ostoskori" value="<%=ostoskori%>">
				<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.size()%>
				kpl, yht. 
				<div id="ostoskoributton4">
					<a href="OstoskoriServlet">Ostoskoriin</a>
				</div>
				</form>
			</div>


	<%-- Otsikkoloota on ainoastaan sitä varten, että saa taulukon otsikon kokoa ja sijaintia muutettua--%>

	<div id="otsikkoloota">
		<a href="ListaaPizzatServlet" class="pizzalistabutton"><p2><u>Pizzalista</u></p2></a>
		<a href="ListaaJuomatServlet" class="juomabutton"><p2>Juomat</p2></a>
	</div>

	<div id="loota1">
		<%-- Lootan sisällä on kaikki itse sisältö --%>
		<div id="tuotelistataulukko">
			<%-- Pizzalista on toteutettu taulukkona --%>
			<div id="tablescoller">
				<%-- Tablescoller on taulukon oikean reunan scollbar --%>
				
				<table>
					<tr>
						<th>#</th>
						<th>Nimi</th>
						<th>Hinta</th>
						<th>Mausteet</th>
						
					</tr>

					<%
						DecimalFormat decimal = new DecimalFormat("0.00");
						int pizzanumero = 0;
						for (int i = 0; i < pizzat.size(); i++) {
							if (pizzat.get(i).getTyyppi().equalsIgnoreCase("pizza")
									|| pizzat.get(i).getTyyppi()
											.equalsIgnoreCase("fantasia")) {
								if (pizzat.get(i).getNakyvyys() == 1) {
									pizzanumero++;
					%>
					<tr>
						<td width="10px"><%=pizzanumero%></td>
						<td><b><%=pizzat.get(i).getNimi()%></b><br> Täytteet: <%
							for (int j = 0; j < pizzat.get(i).getTaytelista()
												.size(); j++) {
						%> <%=pizzat.get(i).getTaytelista().get(j)
									.getNimi()%><%
 	if (j + 1 < pizzat.get(i).getTaytelista().size()) {
 %>, <%
 	}
 				}
 %></td>

						<td width="50px"><%=decimal.format(pizzat.get(i).getHinta())%>

						</td>
						
						<td width="400px"><form action="OstoskoriServlet" method="post">
						<div class="mausteet">
						<% for (int j = 0; j < mausteet.size(); j++) { %>
							
							
							<div class="mauste"><input type="checkbox" name="mauste" value="<%=mausteet.get(j).getId()%>"><%=mausteet.get(j).getNimi() %></div>
							
						<%} %></div>
						<div class="lisaakoriin"><input type="text" size=2 maxlength="2"
							name="maara" value="1"> Määrä<input type="hidden" value="<%=pizzat.get(i).getId()%>"
									name="koriin"><input type="submit"
							value='  Lisää ostoskoriin  '></div></form>
							</td>

					</tr>
					<%
						}
							}
						}
					%>
				</table>
				
			</div>
		</div>
	</div>


	<div id="footer">
		<%-- Footerin sisällöllä voi siirtää footerin sisältöä --%>
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
