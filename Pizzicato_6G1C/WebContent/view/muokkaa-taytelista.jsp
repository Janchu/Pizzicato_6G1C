<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>" scope="request" />
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
<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
</div>
</div>


<div id="otsikkoloota">
<p2 style="margin-left:15%;">Täytelista</p2>
</div>
	<div id="loota1">
	<div id="nappulasijainti2">
			<a href="LisaaTayteServlet" class="button">Lisää täyte</a>
			<a href="OmistajaListaaPizzatServlet" class="button">Poistu <br> muokkaustilasta</a>
		</div>
		<div id="tuotelistataulukko">
		<div id="tablescoller">

			<table>
				
				<tr>

					<th>#</th>
					<th>Nimi</th>
					<th>Englanninkielinen nimi</th>
					<th>Hinta</th>
					<th>Kilohinta</th>
					<th>Muokkaa</th>
					<th>Poista</th>
				</tr>

				<%
				DecimalFormat decimal = new DecimalFormat("0.00");
				int taytenumero = 0;
				for (int i = 0; i <taytteet.size(); i++) {
					taytenumero++;
	
				%>

				<tr>

					<td width="50px"><%=taytenumero%></td>
					
					
					
					<td><b><%=taytteet.get(i).getNimi()%></b>
 	
					<td><%=taytteet.get(i).getNimi_eng()%></td>
					<td width="100px"><%=decimal.format(taytteet.get(i).getHinta())%></td>
					<td width="100px"><%=decimal.format(taytteet.get(i).getKilohinta())%> €/kg</td>
					<td width="50px"><a href="MuokkaaTayteServlet?TId=<%=taytteet.get(i).getId()%>"><img alt="lib" src="images/muokkaaicon.png" title="muokkaa" width="32" height="32"></a></td>
					<td width="50px"><a href="PoistaTayteServlet?TayId=<%=taytteet.get(i).getId()%>"
						onclick="return confirm('Haluatko varmasti poistaa täytteen?')"><img alt="lib" src="images/remove.png" title="poista" width="32" height="32"></a></td>
				</tr>

				<%
					}
				%><tr>
			</table>
			</div>
		</div>

		<div id="nappulasijainti">
			<a href="LisaaTayteServlet" class="button">Lisää täyte</a>
			<br> <a href="OmistajaListaaPizzatServlet" class="button">Poistu <br> muokkaustilasta</a>
		</div>
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
