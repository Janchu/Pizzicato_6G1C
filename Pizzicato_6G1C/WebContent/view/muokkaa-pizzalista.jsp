<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
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
Avoinna: 07-23<br>
Puhelin: +358 0123456<br>
Katuosoite: Katu2<br>
Postitoimipaikka: Helsinki, 010101</p>
</div>
<div id="lootaoikea">

<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a> <!--  -->
<a href="EngListaaPizzatServlet"><img alt="lib" src="images/uklib3.jpg" width="32" height="32"></a>
</div>
</div>
</div>

	<div id="loota1">
		<div id="pizzalistataulukko">

			<table>
				<caption>Pizzalista</caption>
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

					<td width="50px"><%=pizzanumero%></td>
					<td><b><%=pizzat.get(i).getNimi()%></b><br> Täytteet: <%
						for (int j = 0; j < pizzat.get(i).getTaytelista().size(); j++) {
					%> <%=pizzat.get(i).getTaytelista().get(j).getNimi()%><%if (j+1 < pizzat.get(i).getTaytelista().size()) {%>, <%}
 	}
 %></td>
					<td width="100px"><%=decimal.format(pizzat.get(i).getHinta())%></td>
					<td width="50px"><%=nakyvyys%></td>
					<td width="50px"><a href="MuokkaaPizzaServlet?PizId=<%=pizzat.get(i).getId()%>"><img alt="lib" src="images/muokkaaicon.png" width="32" height="32"></a></td>
					<td width="50px"><a href="PiilotaPizzaServlet?Nakyvyys=<%=pizzat.get(i).getNakyvyys()%>&Id=<%=pizzat.get(i).getId()%>"><img alt="lib" src="images/naytaicon.jpg" width="32" height="32"></a></td>
					<td width="50px"><a href="PoistaPizzaServlet?PizId=<%=pizzat.get(i).getId()%>"
						onclick="return confirm('Haluatko varmasti poistaa Pizzan?')"><img alt="lib" src="images/remove.png" width="32" height="32"></a></td>

				</tr>

				<%
					}
				%><tr>
			</table>
		</div>
		</div>

		<div id="nappulasijainti">
			<a href="LisaaPizzaServlet" class="button">Lisää pizza</a> <br>
			<a href="OmistajaListaaPizzatServlet" class="button">Poistu <br>
				muokkaustilasta
			</a>
		</div>
	


</body>
</html>
