<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<%@ page import="pizzicato.model.Mauste"%>
<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.Kayttaja"%>
<%@ page import="pizzicato.model.Tilausrivi"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>" scope="request" />
<jsp:useBean id="ostoskori" type="java.util.ArrayList<Tilausrivi>" scope="request" />
<jsp:useBean id="mausteet" type="java.util.ArrayList<Mauste>" scope="request" />
<jsp:useBean id="kayttaja" class="pizzicato.model.Kayttaja" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Pizza menu</title>
</head>
<body>

<div id="logoloota">
<div id="lootavasen">
<div class="logo">
<a href="EngListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>
</div>

</div>
<div id="lootakeski">
<p3>
Open: Mon-Sat 11-21, Sun 12-18<br>
+358 40 666 666<br>
Kuusitie 66<br>
Meilahti, 00270
</p3>
</div>

<div id="lootaoikea">
<% if (kayttaja.getTyyppi() != null) { %>
			<span class="valkoinen">Welcome, <%=kayttaja.getEtunimi() %></span>
			<a href="LogoutServlet" class="button2">Log out</a>
		<% } else { %>
			<a href="RekisterointiServlet" class="button2">Register</a>
			<a href="LoginServlet" class="button2">Log in</a>
		<% } %>
<a href="EngListaaPizzatServlet"><img alt="lib" src="images/uklib4.png" width="32" height="32"></a>
<a href="ListaaPizzatServlet"><img alt="lib" src="images/finlib.png" width="32" height="32"></a>
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


<div id="otsikkoloota"> <%-- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat --%>
<a href="EngListaaPizzatServlet" class="pizzalistabutton"><p2><u>Pizza menu</u></p2></a>
<a href="EngListaaJuomatServlet" class="juomabutton"><p2>Drinks</p2></a>
</div>

<div id="loota1">
		<div id="tuotelistataulukko"> <%-- pizzalista on toteutettu taulukkona --%>
		<div id="tablescoller">
			
			<table>
				
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Price</th>
					<th>Spices</th>
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
					<td><b><%=pizzat.get(i).getNimi()%><br> Toppings:</b> <%
 					for (int j = 0; j < pizzat.get(i).getTaytelista().size(); j++) { //kukkuu %>
 					 <%=pizzat.get(i).getTaytelista().get(j).getNimi_eng()%><%if (j+1 < pizzat.get(i).getTaytelista().size()) {%>, <%}
 					} %>
 					</td>
					<td width="50px"><%=decimal.format(pizzat.get(i).getHinta())%></td>
					<td width="400px"><form action="OstoskoriServlet" method="post">
						<div class="mausteet">
						<% for (int j = 0; j < mausteet.size(); j++) { %>
							
							
							<div class="mauste"><input type="checkbox" name="mauste" value="<%=mausteet.get(j).getId()%>"><%=mausteet.get(j).getNimi_eng() %></div>
							
						<%} %></div>
						<div class="lisaakoriin"><input type="text" size=2 maxlength="2"
							name="maara" value="1"> pc<input type="hidden" value="<%=pizzat.get(i).getId()%>"
									name="koriin"><input type="submit"
							value='  Add to cart  '></div></form>
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

	<div id="footer"> <%-- footer_sisältö divillä pystyy liikuttamaan sisällön sijaintia ym. --%>
		<div id="footer_sisältö">
			<p>
				Pizzeria Pizzicato<br> Phone: +358 40 666 666<br>
				Email: pizzeria.pizzicato@gmail.com<br> Address: Kuusitie 66 <br>
				Zip Code: Meilahti, 00270 <br>
			</p>
		</div>
	</div>
</body>
</html>
