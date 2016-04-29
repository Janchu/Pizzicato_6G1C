<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Muokkaa Pizzalistaa</title>
</head>
<body>

<div id="logoloota">
<div id="lootavasen">
<div class="logo">

<a href="OmistajaListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>

</div>
</div>
<div id="lootakeski">
<p3>
Avoinna: Ma-La 11-21, Su 12-18<br>
+358 40 666 666<br>
Kuusitie 66<br>
Meilahti, 00270</p3>
</div>

<div id="lootaoikea">
<span class="valkoinen">Tervetuloa, Outi  </span><a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
</div>

</div>


<div id="otsikkoloota">
<p2 style="margin-left:15%;">Pizzalista</p2>
</div>

	<div id="loota1">
	<div id="nappulasijainti2">
			<a href="LisaaPizzaServlet" class="button">Lisää pizza</a> 
			<a href="OmistajaListaaPizzatServlet" class="button">Poistu <br>
				muokkaustilasta
			</a>
		</div>
		<div id="tuotelistataulukko">
		<div id="tablescoller">
			<table>
				
				<tr>

					<th>#</th>
					<th>Nimi</th>
					<th>Hinta</th>
					<th>Näkyvillä</th>
					<th>Muokkaa</th>
					<th>Piilota</th>
					<th>Poista</th>
				</tr>

				<%
					DecimalFormat decimal = new DecimalFormat("0.00");
					int pizzanumero = 0;
					for (int i = 0; i < pizzat.size(); i++) {
						pizzanumero++;
						int nakyvyysInt = pizzat.get(i).getNakyvyys();
						String nakyvyys = "";
						if (nakyvyysInt == 1) {
							nakyvyys = "Näkyvillä";
						} else {
							nakyvyys = "Piilossa";
						}
				%>

				<tr>

					<td width="10px"><%=pizzanumero%></td>
					<td><b><%=pizzat.get(i).getNimi()%></b><br> Täytteet: <%
						for (int j = 0; j < pizzat.get(i).getTaytelista().size(); j++) {
					%> <%=pizzat.get(i).getTaytelista().get(j).getNimi()%><%if (j+1 < pizzat.get(i).getTaytelista().size()) {%>, <%}
 	}
 %></td>
					<td width="100px"><%=decimal.format(pizzat.get(i).getHinta())%></td>
					<td width="50px"><%=nakyvyys%></td>
					<td width="50px"><a href="MuokkaaPizzaServlet?PizId=<%=pizzat.get(i).getId()%>"><img alt="lib" src="images/muokkaaicon.png" title="muokkaa" width="32" height="32"></a></td>
					<td width="50px"><a href="PiilotaPizzaServlet?Nakyvyys=<%=pizzat.get(i).getNakyvyys()%>&Id=<%=pizzat.get(i).getId()%>"><img alt="lib" src="images/showicon.png" title="näytä/piilota" width="32" height="32"></a></td>
					<td width="50px"><a href="PoistaPizzaServlet?PizId=<%=pizzat.get(i).getId()%>"
						onclick="return confirm('Haluatko varmasti poistaa Pizzan?')"><img alt="lib" src="images/remove.png" title="poista" width="32" height="32"></a></td>

				</tr>

				<%
					}
				%><tr>
			</table>
		</div>
		</div>
		</div>

		<div id="nappulasijainti">
			<a href="LisaaPizzaServlet" class="button">Lisää pizza</a> <br>
			<a href="OmistajaListaaPizzatServlet" class="button">Poistu <br>
				muokkaustilasta
			</a>
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
