<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Listaa pizzat</title>
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
<p style= "margin-top:100px; font-size:170%; font-family:Kozuka Gothic Pro EL;">
Avoinna: Ma-La 11-21, Su 12-18<br>
+358 40 666 666<br>
Kuusitie 66<br>
Meilahti, 00270</p>
</div>

<div id="lootaoikea">
<a href="LoginServlet" class="button2">Kirjaudu Sisään</a>
<a href="EngListaaPizzatServlet"><img alt="lib" src="images/uklib4.png" width="32" height="32"></a>
<a href="ListaaPizzatServlet"><img alt="lib" src="images/finlib.png" width="32" height="32"></a>
</div>
</div>
</div>

<%-- Otsikkoloota on ainoastaan sitä varten, että saa taulukon otsikon kokoa ja sijaintia muutettua--%>

<div id="otsikkoloota">
<p style="margin-left:15%;">Pizzalista</p>
</div>
	
<div id="loota1"> <%-- Lootan sisällä on kaikki itse sisältö --%>
<div id="pizzalistataulukko"><%-- Pizzalista on toteutettu taulukkona --%>
<div id="tablescoller"><%-- Tablescoller on taulukon oikean reunan scrollbar --%>
		<table>
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
 					for (int j = 0; j < pizzat.get(i).getTaytelista().size(); j++) {%>
 					<%=pizzat.get(i).getTaytelista().get(j).getNimi()%><%if (j+1 < pizzat.get(i).getTaytelista().size()) {%>, <%}
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
</div>


	<div id="footer"><%-- Footerin sisällöllä voi siirtää footerin sisältöä --%>
		<div id="footer_sisältö">
			<p>
				Pizzeria Pizzicato<br> Puhelin: +358 40 666 666<br>
				Sähköpostiosoite: pizzeria.pizzicato@gmail.com<br> Katuosoite: Kuusitie 66 <br>
				Postitoimipaikka: Meilahti, 00270 <br>
			</p>
		</div>
	</div>
</body>
</html>
