<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<%@ page import="pizzicato.model.Mauste"%>
<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.Kayttaja"%>
<%@ page import="pizzicato.model.Tilausrivi"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>" scope="request" />
<jsp:useBean id="ostoskori" class="pizzicato.model.Tilaus" scope="request" />
<jsp:useBean id="mausteet" type="java.util.ArrayList<Mauste>" scope="request" />
<jsp:useBean id="kayttaja" class="pizzicato.model.Kayttaja" scope="request" />
<html>
<head>
<script type="text/javascript">
		function subtractQty(){
			if(document.getElementById("qty").value - 1 < 1)
				return;
			else
				 document.getElementById("qty").value--;
		}
		</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Pizza menu</title>
</head>
<body>

	<%-- Logolootassa on "header" ja se kootuu kolmesta eri lootasta, jotka
 järjestävät logolootan sisällön. Vasemmassa lootassa on logo, keskimmäisessä
 yhteystiedot ja oikeanpuolimmaisessa kirjautuminen ja kielenvaihto --%>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">
				<a href="ListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>
			</div>
		</div>

		<div id="lootakeski">
		<a href="EngListaaJuomatServlet"><img alt="lib" src="images/uklib4.png" width="32" height="32"></a>
			<a href="ListaaJuomatServlet"><img alt="lib" src="images/finlib.png" width="32" height="32"></a><br>
			<p3>
				Open: Mon-Sat 11-21, Sun 12-18<br> +358 40 666 666<br>
				Kuusitie 66<br> Meilahti, 00270
			</p3>
		</div>

		<div id="lootaoikea">
		<% if (kayttaja.getTyyppi() != null) { %>
			<span class="valkoinen">Tervetuloa, <%=kayttaja.getEtunimi() %></span>
			<a href="LogoutServlet" class="button2">Logout</a>
		<% } else { %>
			<a href="RekisterointiServlet" class="button2">Register</a>
			<a href="LoginServlet" class="button2">Login</a>
		<% } %>
		
			<br>
			<div id="ostoskoributton2">
			<form method="get">
			<% DecimalFormat formatter = new DecimalFormat("#0.00"); %>
				<input type="hidden" name="ostoskori" value="<%=ostoskori%>">
				<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.getYhtlkm()%>
				kpl, yht. <%=formatter.format(ostoskori.getYhthinta()) %> €
				<div id="ostoskoributton1">
					<a href="EngOstoskoriServlet">Shopping Cart</a>
				</div>
				</form>
			</div>
		</div>
	</div>

	<%-- tämä tulee näkyville, kun menee 1000px  alle --%>
	<div id="ostoskoributton3">
				<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.getYhtlkm()%>
				kpl, yht. <%=formatter.format(ostoskori.getYhthinta()) %> €
				<div id="ostoskoributton4">
					<a href="OstoskoriServlet">Shopping Cart</a>
				</div>
			</div>


	<%-- Otsikkoloota on ainoastaan sitä varten, että saa taulukon otsikon kokoa ja sijaintia muutettua--%>

	<div id="otsikkoloota">
		<a href="ListaaPizzatServlet" class="pizzalistabutton"><p2><u>Pizza menu</u></p2></a>
		<a href="ListaaJuomatServlet" class="juomabutton"><p2>Drinks</p2></a>
	</div>

	<div id="loota1">
		
		<%-- Lootan sisällä on kaikki itse sisältö --%>
		<div id="tuotelistataulukko">
			<%-- Tuotelista on toteutettu taulukkona --%>
			<div id="tablescoller">
				<%-- Tablescoller on taulukon oikean reunan scollbar --%>
				
				<table>
				
					<tr>
						<th>#</th>
						<th>Name</th>

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
						<td><form action="OstoskoriServlet" method="post"><div class="tuotelistavasen"><b><%=pizzat.get(i).getNimi()%><br> Täytteet: </b>
						 <%
							for (int j = 0; j < pizzat.get(i).getTaytelista()
												.size(); j++) {
						%> <%=pizzat.get(i).getTaytelista().get(j)
									.getNimi()%><%
 	if (j + 1 < pizzat.get(i).getTaytelista().size()) {
 %>, <%
 	}
 				}
 %>
<br> 
						<div class="mausteet"> <% for (int j = 0; j < mausteet.size(); j++) { %>
						<div class="mauste"><input type="checkbox" name="mauste" value="<%=mausteet.get(j).getId()%>">  <%=mausteet.get(j).getNimi() %></div>
						<%} %></div></div>


						
						
						<div class="tuotelistaoikea">
						<b><%=formatter.format(pizzat.get(i).getHinta())%>€</b><br><br>
						
						<div class="tilaasaatana"><input type='text' value="1" name='maara' id='qty' size=2 maxlength="2"/>
						<input type='button' name='subtract' onclick='javascript: subtractQty();' value='-'/> 
						<input type='button' name='add' onclick='javascript: document.getElementById("qty").value++;' value='+'/></div> 
						<input type="hidden" value="<%=pizzat.get(i).getId()%>"
									name="koriin"><input type="hidden" value="pizza"
									name="tyyppi"><input type="submit"
							value='  Add to shopping cart  '></div></form>
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
				Pizzeria Pizzicato<br> Phone: +358 40 666 666<br> 
				Email: pizzeria.pizzicato@gmail.com<br> Address: 
				Kuusitie 66 <br> Zip Code: Meilahti, 00270 <br>
			</p>
		</div>
	</div>
</body>
</html>