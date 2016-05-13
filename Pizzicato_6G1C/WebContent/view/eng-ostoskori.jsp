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
<%@ page import="pizzicato.model.Kayttaja"%>
<jsp:useBean id="kayttaja" class="pizzicato.model.Kayttaja"
	scope="request" />
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />
<jsp:useBean id="ostoskori" class="pizzicato.model.Tilaus"
	scope="request" />

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<title>Shopping Cart</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">
				<a href="EngListaaPizzatServlet"><img alt="Pizzerian logo"
					src="images/pizzalogofin.png" height="100%" width="100%"></a>
			</div>
		</div>
		<div id="lootakeski">
			<a href="EngOstoskoriServlet"><img alt="lib"
				src="images/uklib4.png" width="32" height="32"></a> <a
				href="OstoskoriServlet"><img alt="lib" src="images/finlib.png"
				width="32" height="32"></a><br>
			<p3> Open: Mon-Sat 11-21, Sun 12-18<br>
			+358 40 666 666<br>
			Kuusitie 66<br>
			Meilahti, 00270 </p3>
		</div>

		<div id="lootaoikea">
			<%
				if (kayttaja.getTyyppi() != null) {
			%>
			<span class="valkoinen"> Welcome, <%=kayttaja.getEtunimi()%></span>
			<a href="LogoutServlet" class="button2">Logout</a>
			<%
				} else {
			%>
			<a href="EngRekisterointiServlet" class="button2">Register</a> <a
				href="LoginServlet" class="button2">Login</a>
			<%
				}
			%>
			<br>
			<div id="ostoskoributton2">
				<%
					DecimalFormat formatter = new DecimalFormat("#0.00");
				%>
				<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.getYhtlkm()%>
				pcs, in total:
				<%=formatter.format(ostoskori.getYhthinta())%>
				€
				<div id="ostoskoributton1">
					<a href="OstoskoriServlet">Shopping Cart</a>
				</div>
			</div>
		</div>
	</div>


	<div id="ostoskoributton3">
		<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.getYhtlkm()%>
		pcs, in total:
		<%=formatter.format(ostoskori.getYhthinta())%>
		€
		<div id="ostoskoributton4">
			<a href="EngOstoskoriServlet">Shopping Cart</a>
		</div>
	</div>

	<div id="otsikkoloota">
		<p2 style="margin-left:15%;">Shopping Cart</p2>
	</div>

	<%--- lootan sisällä on pääsisältö,  kuten pizzalista ja nappulat --%>

	<div id="loota1">
		<div id="tuotelistataulukko">

			<%
				if (ostoskori.getTilausrivit().size() < 1
						|| ostoskori.getTilausrivit() == null) {
					String korityhja = "The shopping cart is empty!";
			%>
			<%=korityhja%>
			<a href="EngListaaPizzatServlet" class="button2">Front Page</a>



			<%
				} else {
			%>
			<table>

				<tr>
					<th>Name</th>
					<th>Price</th>
					<th>Amount</th>
					<th>Condiment</th>
					<th></th>
				</tr>
				<%
					ArrayList<Tayte> taytteet = new ArrayList<Tayte>();

						for (int i = 0; i < ostoskori.getTilausrivit().size(); i++) {
							for (int j = 0; j < pizzat.size(); j++) {
								if (ostoskori.getTilausrivit().get(i).getTilattuTuote()
										.getId() == pizzat.get(j).getId()) {
									taytteet = pizzat.get(j).getTaytelista();
								}
							}
				%>



				<tr>
					<td><b><%=ostoskori.getTilausrivit().get(i)
							.getTilattuTuote().getNimi()%></b><br>
						<%
							if (ostoskori.getTilausrivit().get(i).getTilattuTuote()
											.getTyyppi().equalsIgnoreCase("pizza")) {
						%>
						<%
							for (int j = 0; j < taytteet.size(); j++) {
						%><%=taytteet.get(j).getNimi_eng()%>
						<%
							if (j + 1 < pizzat.get(i).getTaytelista().size()) {
						%>, <%
							}
										}
									}
						%></td>
					<td><%=formatter.format(ostoskori.getTilausrivit()
							.get(i).getTilattuTuote().getHinta())%></td>
					<td><%=ostoskori.getTilausrivit().get(i).getLkm()%></td>
					<td>
						<%
							if (ostoskori.getTilausrivit().get(i).getTilattuTuote()
											.getTyyppi().equalsIgnoreCase("pizza")) {
						%>
						<%
							for (int j = 0; j < ostoskori.getTilausrivit().get(i)
												.getMaustelista().size(); j++) {
						%>
						<%=ostoskori.getTilausrivit().get(i)
									.getMaustelista().get(j).getNimi_eng()%>
						<%
							}
									}
						%>
					</td>
					<td>
						<form action="LisaaKoriinServlet" method="post">
							<input type="hidden" value="<%=i%>" name="id"><input
								type="submit" value=" + " name="lisaa">
						</form>
						<form action="PoistaKoristaServlet" method="post">
							<input type="hidden" value="<%=i%>" name="id"><input
								type="submit" value=" - " name="poista">
						</form>
					</td>
				</tr>

				<%
					}
				%>
				<tr>
					<td bgcolor="#ccffcc"><b>Price total:</b></td>
					<td bgcolor="#ccffcc"><b><%=formatter.format(ostoskori.getYhthinta())%>€</b></td>
					<td bgcolor="#ccffcc"></td>
					<td bgcolor="#ccffcc"></td>
					<td bgcolor="#ccffcc"></td>
				</tr>






			</table><br>
			<div id="ostoskorinabls">
				<a href="EngListaaPizzatServlet"><input type="button"
					class="button" value="Front Page"></a> <a
					href="EngTeeTilausServlet"><input type="button" class="button"
					value="Continue"></a>
			</div>

			<%
				}
			%>

		</div>
	</div>

	<%-- footer_sisältö divillä pystyy liikuttamaan sisällön sijaintia ym --%>

	<div id="footer">
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
