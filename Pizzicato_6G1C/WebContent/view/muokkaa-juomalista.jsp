<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Juoma"%>
<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Muokkaa Juomalistaa</title>
</head>
<body>

<div id="logoloota">
<div id="lootavasen">
<div class="logo">

<a href="OmistajaListaaJuomatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>

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
		<p style="margin-left: 15%;">Juomalista</p>
	</div>

	<div id="loota1">
		<div id="juomalistataulukko">
			<div id="tablescoller">
				<table>

					<tr>

						<th>Numero</th>
						<th>Nimi</th>
						<th>Hinta</th>
						<th>Näkyvillä</th>
						<th>Muokkaa</th>
						<th>Piilota</th>
						<th>Poista</th>
					</tr>

					<%
						DecimalFormat decimal = new DecimalFormat("0.00");
						int juomanumero = 0;
						for (int i = 0; i < juomat.size(); i++) {
							juomanumero++;
							int nakyvyysInt = juomat.get(i).getNakyvyys();
							String nakyvyys = "";
							if (nakyvyysInt == 1) {
								nakyvyys = "Näkyvillä";
							} else {
								nakyvyys = "Piilossa";
							}
					%>

					<tr>

						<td width="50px"><%=juomanumero%></td>
						<td><b><%=juomat.get(i).getNimi()%></b></td>
						<td width="100px"><%=decimal.format(juomat.get(i).getHinta())%></td>
						<td width="50px"><%=nakyvyys%></td>
						<td width="50px"><a
							href="MuokkaaJuomaServlet?JuoId=<%=juomat.get(i).getId()%>"><img
								alt="lib" src="images/muokkaaicon.png" width="32" height="32"></a></td>
						<td width="50px"><a
							href="PiilotaJuomaServlet?Nakyvyys=<%=juomat.get(i).getNakyvyys()%>&Id=<%=juomat.get(i).getId()%>"><img
								alt="lib" src="images/showicon.png" width="32" height="32"></a></td>
						<td width="50px"><a
							href="PoistaJuomaServlet?JuoId=<%=juomat.get(i).getId()%>"
							onclick="return confirm('Haluatko varmasti poistaa Juoman?')"><img
								alt="lib" src="images/remove.png" width="32" height="32"></a></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
	</div>

	<div id="nappulasijainti">
		<a href="LisaaJuomaServlet" class="button">Lisää juoma</a><br> <a
			href="OmistajaListaaJuomatServlet" class="button">Poistu <br>
			muokkaustilasta
		</a>
	</div>
	<div id="footer">
		<div id="footerin_sisältö">

			<p>
				Pizzeria Pizzicato<br> Puhelin: +358 40 666 666<br>
				Sähköpostiosoite: pizzeria.pizzicato@gmail.com<br> Katuosoite:
				Kuusitie 66 <br> Postitoimipaikka: Meilahti, 00270 <br>
			</p>
		</div>
	</div>

</body>
</html>