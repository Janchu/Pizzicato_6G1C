<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />
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
<a href="ListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>
</div>
</div>
<div id="lootakeski">
<p style= "margin-top:100px; font-size:170%; font-family:Kozuka Gothic Pro EL;">
Open: 07-23<br>
Phone: +358 0123456<br>
Address: Katu2<br>
Zip Code: Helsinki, 010101</p>
</div>
<div id="lootaoikea">

<a href="" class="button2">Register</a>
<a href="" class="button2">Log In</a>
<a href="EngListaaPizzatServlet"><img alt="lib" src="images/uklib3.jpg" width="32" height="32"></a>
</div>
</div>
</div>

<!-- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat -->

<div id="otsikkoloota">
<h1>Pizza menu</h1>

</div>	
	
<!-- pizzalista on toteutettu taulukkona -->
	
		<div id="pizzalistataulukko">
			
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
 	for (int j = 0; j < pizzat.get(i).getTaytelista().size(); j++) { //kukkuu
 %> <%=pizzat.get(i).getTaytelista().get(j)
								.getNimi_eng()%><%if (j+1 < pizzat.get(i).getTaytelista().size()) {%>, <%}
 	}
 %></td>
					<td width="50px"><%=decimal.format(pizzat.get(i).getHinta())%></td>
				</tr>
				<%
					}
					}
				%>
			</table>
		</div>
		
		<div id="nappulasijainti">
			<a href="MuokkaaPizzalistaServlet" class="button">Siirry
				pizzalistan <br> muokkaukseen</a>
				<br>
			<a href="MuokkaaTayteListaServlet" class="button">Siirry
				täytelistan <br> muokkaukseen</a>	
		</div>
	
	
<!-- footer_sisältö divillä pystyy liikuttamaan sisällön sijaintia ym. -->

	<div id="footer">
		<div id="footer_sisältö">

			<p>
				Pizzeria Pizzicato<br> Phone: 01245678<br>
				Email: pizzicato@xxx.fi<br> Address: katu21 <br>
				City: Helsinki, Zip Code: 010101 <br>
			</p>
		</div>

	</div>
</body>
</html>