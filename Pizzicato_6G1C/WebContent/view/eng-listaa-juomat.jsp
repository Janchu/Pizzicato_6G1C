<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Juoma"%>
<%@ page import="pizzicato.model.Tuote"%>
<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Drinkmenu</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">
				<a href="EngListaaJuomatServlet"><img alt="Pizzerian logo"
					src="images/pizzalogofin.png" height="100%" width="100%"></a>
			</div>

		</div>
		<div id="lootakeski">
			<p
				style="margin-top: 100px; font-size: 170%; font-family: Kozuka Gothic Pro EL;">
				Open: Mon-Sat 11-21, Sun 12-18<br> +358 40 666 666<br>
				Kuusitie 66<br> Meilahti, 00270
			</p>
		</div>

		<div id="lootaoikea">
			<a href="LoginServlet" class="button2">Log In</a> <a
				href="EngListaaJuomatServlet"><img alt="lib"
				src="images/uklib4.png" width="32" height="32"></a> <a
				href="ListaaJuomatServlet"><img alt="lib"
				src="images/finlib.png" width="32" height="32"></a>
		</div>
	</div>
	</div>

	<div id="otsikkoloota">
		<%-- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat --%>
		<a href="EngListaaPizzatServlet" class="pizzalistabutton">Pizza
			menu</a> <a href="ListaaJuomatServlet" class="juomabutton">Drinks</a>
	</div>

	<div id="loota1">
		<div id="tuotelistataulukko">
			<%-- juomalista on toteutettu taulukkona --%>
			<div id="tablescoller">

				<table>

					<tr>
						<th>Number</th>
						<th>Name</th>
						<th>Price</th>
					</tr>

					<%
						DecimalFormat decimal = new DecimalFormat("0.00");
						int juomanumero = 0;
						for (int i = 0; i < juomat.size(); i++) {

							if (juomat.get(i).getNakyvyys() == 1) {
								juomanumero++;
					%>

					<tr>
						<td width="100px"><%=juomanumero%></td>
						<td><b><%=juomat.get(i).getNimi()%></b></td>
						<td width="50px"><%=decimal.format(juomat.get(i).getHinta())%></td>
					</tr>
					<%
						}
						}
					%>
				</table>
			</div>
		</div>
	</div>

	<div id="footer">
		<%-- footer_sisältö divillä pystyy liikuttamaan sisällön sijaintia ym. --%>
		<div id="footer_sisältö">
			<p>
				Pizzeria Pizzicato<br> Phone: +358 40 666 666<br> Email:
				pizzeria.pizzicato@gmail.com<br> Address: Kuusitie 66 <br>
				Zip Code: Meilahti, 00270 <br>
			</p>
		</div>
	</div>

</body>
</html>