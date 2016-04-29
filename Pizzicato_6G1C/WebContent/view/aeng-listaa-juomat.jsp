<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Kayttaja"%>
<%@ page import="pizzicato.model.Juoma"%>
<%@ page import="pizzicato.model.Tuote"%>
<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma>"
	scope="request" />
<jsp:useBean id="kayttajat" type="java.util.ArrayList<Kayttaja>" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Drink menu</title>
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
			<p3>
				Open: Mon-Sat 11-21, Sun 12-18<br> +358 40 666 666<br>
				Kuusitie 66<br> Meilahti, 00270
			</p3>
		</div>

		<div id="lootaoikea">
		<%
		for(int i = 0; i < kayttajat.size(); i++) {
		%>
		
		<td>Tervetuloa: <%=kayttajat.get(i).getEtunimi()%></td>
		<%
		}
		%>
		
			<a href="LogoutServlet" class="button2">Log Out</a> <a
				href="EngListaaJuomatServlet"><img alt="lib"
				src="images/uklib4.png" width="32" height="32"></a> <a
				href="ListaaJuomatServlet"><img alt="lib"
				src="images/finlib.png" width="32" height="32"></a>
		
		</div>
	</div>
	</div>
	
	<div id="otsikkoloota">
		<%-- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat --%>
		<a href="AEngListaaPizzatServlet" class="pizzalistabutton"><p2>Pizza
			menu</p2></a> <a href="AEngListaaJuomatServlet" class="juomabutton"><p2><u>Drinks</u></p2></a>
	</div>

	<div id="loota1">
		<div id="tuotelistataulukko">
			<%-- juomalista on toteutettu taulukkona --%>
			<div id="tablescoller">

				<table>

					<tr>
						<th>#</th>
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
						<td width="10px"><%=juomanumero%></td>
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