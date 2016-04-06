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

<title color="white">Pizzalista</title>
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
<a href="LoginServlet" class="button2">Kirjaudu Sisään</a>
<a href="EngListaaPizzatServlet"><img alt="lib" src="images/uklib3.jpg" width="32" height="32"></a>
</div>
</div>
</div>

<!-- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat -->

	<div id="loota1">
	
	
<!-- pizzalista on toteutettu taulukkona -->
	
		<div id="pizzalistataulukko">
		
	

			<table>
				<caption>Pizzalista</caption>

				<tr>
					<th>Numero</th>
					<th>Nimi</th>
					<th>Hinta</th>
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
					<td><b><%=pizzat.get(i).getNimi()%></b><br> Täytteet: <%
 	for (int j = 0; j < pizzat.get(i).getTaytelista().size(); j++) {
 %> <%=pizzat.get(i).getTaytelista().get(j)
								.getNimi()%><%if (j+1 < pizzat.get(i).getTaytelista().size()) {%>, <%}
 	}%>
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
	
<!-- footer_sisältö divillä pystyy liikuttamaan sisällön sijaintia ym. -->

	<div id="footer">
		<div id="footer_sisältö">

			<p>
				Pizzeria Pizzicato<br> Puhelin: 01245678<br>
				Sähköpostiosoite: pizzicato@xxx.fi<br> Osoite: katu21 <br>
				Postitoimipaikka: Helsinki, 010101 <br>
			</p>
		</div>

	</div>
</body>
</html>
