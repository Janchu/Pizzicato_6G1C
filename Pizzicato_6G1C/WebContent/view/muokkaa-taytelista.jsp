<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>"
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
<a href="OmistajaListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogo2.jpg" height="110%" width="110%"></a>
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

<a href="" class="button2">Rekisteröidy</a>
<a href="" class="button2">Kirjaudu Sisään</a>
<a href="EngListaaPizzatServlet"><img alt="lib" src="images/uklib3.jpg" width="32" height="32"></a>
</div>
</div>
</div>

	<div id="loota1">
		<div id="pizzalistataulukko">

			<table>
				<caption>Täytelista</caption>
				<tr>

					<th>Numero</th>
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
					<td width="100px"><%=decimal.format(taytteet.get(i).getKilohinta())%></td>
					<td width="50px"><a href="MuokkaaTayteServlet?TId=<%=taytteet.get(i).getId()%>"><img alt="lib" src="images/muokkaaicon.png" width="32" height="32"></a></td>
					<td width="50px"><a href="PoistaTayteServlet?TayId=<%=taytteet.get(i).getId()%>"
						onclick="return confirm('Haluatko varmasti poistaa Täytteen?')"><img alt="lib" src="images/remove.png" width="32" height="32"></a></td>
				</tr>

				<%
					}
				%><tr>
			</table>
		</div>

		<div id="nappulasijainti">
			<a href="LisaaTayteServlet" class="button">Lisää täyte</a>
			<br> <a href="OmistajaListaaPizzatServlet" class="button">Poistu <br> muokkaustilasta</a>
		</div>
	</div>


</body>
</html>
