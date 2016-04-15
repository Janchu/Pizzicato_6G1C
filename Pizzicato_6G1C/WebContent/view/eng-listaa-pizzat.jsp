<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"scope="request" />
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Pizzamenu</title>
</head>
<body>

<div id="logoloota">
<div id="lootavasen">
<div class="logo">
<a href="EngListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>
</div>

</div>
<div id="lootakeski">
<p style= "margin-top:100px; font-size:170%; font-family:Kozuka Gothic Pro EL;">
Open: Mon-Sat 11-21, Sun 12-18<br>
+358 40 666 666<br>
Kuusitie 66<br>
Meilahti, 00270</p>
</div>

<div id="lootaoikea">
<a href="LoginServlet" class="button2">Log In</a>
<a href="EngListaaPizzatServlet"><img alt="lib" src="images/uklib4.png" width="32" height="32"></a>
<a href="ListaaPizzatServlet"><img alt="lib" src="images/finlib.png" width="32" height="32"></a>
</div>
</div>
</div>

<div id="otsikkoloota"> <%-- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat --%>
<p style="margin-left:15%;">Pizzamenu</p>
</div>

<div id="loota1">
		<div id="pizzalistataulukko"> <%-- pizzalista on toteutettu taulukkona --%>
		<div id="tablescoller">
			
			<table>
				
				<tr>
					<th>Number</th>
					<th>Name</th>
					<th>Price</th>
				</tr>

				<%
					DecimalFormat decimal = new DecimalFormat("0.00");
					int pizzanumero = 0;
					for (int i = 0; i < pizzat.size(); i++) {

						if (pizzat.get(i).getNakyvyys() == 1) {
							pizzanumero++;
				%>

				<tr>
					<td width="100px"><%=pizzanumero%></td>
					<td><b><%=pizzat.get(i).getNimi()%><br> Toppings:</b> <%
 					for (int j = 0; j < pizzat.get(i).getTaytelista().size(); j++) { //kukkuu %>
 					 <%=pizzat.get(i).getTaytelista().get(j).getNimi_eng()%><%if (j+1 < pizzat.get(i).getTaytelista().size()) {%>, <%}
 					} %>
 					</td>
					<td width="50px"><%=decimal.format(pizzat.get(i).getHinta())%></td>
				</tr>
				<%
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