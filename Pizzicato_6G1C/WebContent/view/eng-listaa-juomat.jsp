<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Juoma"%>
<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.Tilausrivi"%>
<%@ page import="pizzicato.model.Kayttaja"%>
<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma>" scope="request" />
<jsp:useBean id="ostoskori" class="pizzicato.model.Tilaus" scope="request" />
<jsp:useBean id="kayttaja" class="pizzicato.model.Kayttaja" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		
<title>Drink menu</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">
				<a href="EngListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>
			</div>
		</div>
		<div id="lootakeski">
		<a href="EngListaaJuomatServlet"><img alt="lib" src="images/uklib4.png" width="32" height="32"></a>
		<a href="ListaaJuomatServlet"><img alt="lib" src="images/finlib.png" width="32" height="32"></a><br>
		<p3> Open: Mon-Sat 11-21, Sun 12-18<br> 
		+358 40 666 666<br>
		Kuusitie 66<br> 
		Meilahti, 00270 </p3>
		</div>

		<div id="lootaoikea">
			<% 
				if (kayttaja.getTyyppi() != null) { 
			%>
			<span class="valkoinen">Welcome, <%=kayttaja.getEtunimi() %></span>
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
			<form method="get">
			<% DecimalFormat formatter = new DecimalFormat("#0.00"); %>
				<input type="hidden" name="ostoskori" value="<%=ostoskori%>">
				<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.getYhtlkm()%>
				pcs, in total: <%=formatter.format(ostoskori.getYhthinta()) %> €
				<div id="ostoskoributton1">
					<a href="OstoskoriServlet">Shopping cart</a>
				</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<div id="ostoskoributton3">
		<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.getYhtlkm()%>
		pcs, in total:
		<%=formatter.format(ostoskori.getYhthinta())%>
		€
		<div id="ostoskoributton4">
			<a href="EngOstoskoriServlet">Shopping cart</a>
		</div>
	</div>

	<div id="otsikkoloota">

		<a href="EngListaaPizzatServlet" class="pizzalistabutton"><p2>Pizza menu</p2></a>
		<a href="EngListaaJuomatServlet" class="juomabutton"><u><p2>Drink menu</p2></u></a>

	</div>

	<div id="loota1">
		<div id="tuotelistataulukko">
			<%-- juomalista on toteutettu taulukkona --%>
			<div id="tablescoller">

				<table>

					<tr>
						<th>#</th>
						<th>Name</th>

					</tr>

					<%
						DecimalFormat decimal = new DecimalFormat("0.00");
						int juomanumero = 0;
						for (int i = 0; i < juomat.size(); i++) {

							if (juomat.get(i).getTyyppi().equalsIgnoreCase("juoma")) {

								if (juomat.get(i).getNakyvyys() == 1) {
									juomanumero++;
					%>

					<tr>
						<td width="10px"><%=juomanumero%></td>
						<td><form action="EngOstoskoriServlet" method="post"><div class="tuotelistavasen"><b><%=juomat.get(i).getNimi_eng()%></b>
						</div>
						<div class="tuotelistaoikea">
						<b><%=decimal.format(juomat.get(i).getHinta())%>€</b><br><br>
						<div class="tilaasaatana"><input type='text' value="1" name='maara' id='qty<%=i %>' size=2 maxlength="2"/>
						<input type='button' name='subtract' onclick='javascript: document.getElementById("qty<%=i %>").value--;' value='-'/> 
						<input type='button' name='add' onclick='javascript: document.getElementById("qty<%=i %>").value++;' value='+'/></div> 
						<input type="hidden" value="<%=juomat.get(i).getId()%>"
									name="koriin"><input type="hidden" value="juoma"
									name="tyyppi"><input type="submit"
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

	<div id="footer">
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