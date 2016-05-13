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
		
<title>Juomalista</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">
				<a href="ListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>
			</div>
		</div>
		<div id="lootakeski">
			<a href="EngListaaJuomatServlet"><img alt="lib" src="images/uklib4.png" width="32" height="32"></a>
			<a href="ListaaJuomatServlet"><img alt="lib" src="images/finlib.png" width="32" height="32"></a><br>
			<p3> Avoinna: Ma-La 11-21, Su 12-18<br>
			+358 40 666 666<br>
			Kuusitie 66<br>
			Meilahti, 00270 </p3>
		</div>

		<div id="lootaoikea">
			<%
				if (kayttaja.getTyyppi() != null) {
			%>
			<span class="valkoinen">Tervetuloa, <%=kayttaja.getEtunimi()%></span>
			<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
			<%
				} else {
			%>
			<a href="RekisterointiServlet" class="button2">Rekisteröidy</a> <a
				href="LoginServlet" class="button2">Kirjaudu Sisään</a>
			<%
				}
			%>
			<br>
			<div id="ostoskoributton2">
			<form method="get">
			<% DecimalFormat formatter = new DecimalFormat("#0.00"); %>
				<input type="hidden" name="ostoskori" value="<%=ostoskori%>">
				<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.getYhtlkm()%>
				kpl, yht. <%=formatter.format(ostoskori.getYhthinta()) %> €
				<div id="ostoskoributton1">
					<a href="OstoskoriServlet">Ostoskoriin</a>
				</div>
				</form>
			</div>
		</div>
	</div>
	

	<div id="ostoskoributton3">
		<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.getYhtlkm()%>
		kpl, yht.
		<%=formatter.format(ostoskori.getYhthinta())%>
		€
		<div id="ostoskoributton4">
			<a href="OstoskoriServlet">Ostoskoriin</a>
		</div>
	</div>

	<div id="otsikkoloota">

		<a href="ListaaPizzatServlet" class="pizzalistabutton"><p2>Pizzalista</p2></a>
		<a href="ListaaJuomatServlet" class="juomabutton"><u><p2>Juomat</p2></u></a>

	</div>

	<div id="loota1">
		<div id="tuotelistataulukko">
			<%-- juomalista on toteutettu taulukkona --%>
			<div id="tablescoller">

				<table>

					<tr>
						<th>#</th>
						<th>Nimi</th>

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
						<td><form action="OstoskoriServlet" method="post"><div class="tuotelistavasen"><b><%=juomat.get(i).getNimi()%></b>
						</div>
						<div class="tuotelistaoikea">
						<b><%=decimal.format(juomat.get(i).getHinta())%>€</b><br><br>
						<div class="tilaasaatana"><input type='text' value="1" name='maara' id='qty<%=i %>' size=2 maxlength="2"/>
						<input type='button' name='subtract' onclick='javascript: if(document.getElementById("qty<%=i %>").value > 1) {document.getElementById("qty<%=i %>").value--}' value='-'/> 
						<input type='button' name='add' onclick='javascript: document.getElementById("qty<%=i %>").value++;' value='+'/></div> 
						<input type="hidden" value="<%=juomat.get(i).getId()%>"
									name="koriin"><input type="hidden" value="juoma"
									name="tyyppi"><input type="submit"
							value='  Lisää ostoskoriin  '></div></form>
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
				Pizzeria Pizzicato<br> Puhelin: +358 40 666 666<br>
				Sähköpostiosoite: pizzeria.pizzicato@gmail.com<br> Katuosoite:
				Kuusitie 66 <br> Postitoimipaikka: Meilahti, 00270 <br>
			</p>
		</div>
	</div>
</body>
</html>