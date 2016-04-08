<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pizzicato.model.Tayte"%>
<%@page import="pizzicato.model.Pizza"%>
<jsp:useBean id="errors" scope="request" type="java.util.HashMap" class="java.util.HashMap" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<title>Lisää pizza</title>
</head>
<body>

<div id="logoloota">
<div id="lootavasen">
<div class="logo">

<a href="OmistajaListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>

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

<a href="" class="button2">Rekisteröidy</a>
<a href="" class="button2">Kirjaudu Sisään</a>
<a href="EngListaaPizzatServlet"><img alt="lib" src="images/uklib4.png" width="32" height="32"></a>
<a href="ListaaPizzatServlet"><img alt="lib" src="images/finlib.png" width="32" height="32"></a>
</div>
</div>
</div>

<div id="otsikkoloota">
<p>Lisää täyte</p>
</div>
	
	<div id="loota1">


		<form method="post">


			<legend>Syötä täytteen tiedot:</legend>
			<table>
				<tr>
					<td><label>Täytteen nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteNimi" placeholder="Täytteen nimi" maxlength="20" title="Täytteen nimi saa olla max 20 merkkiä pitkä" required value="${uusiTayte.nimi}">
					 <%
               if (errors.containsKey("nimi")) {
                  out.println("<span class=\"error\">" + errors.get("nimi") + "</span>");
               }
            %>
            </td>
					<td>Max 20 merkkiä</td>
				</tr>

<tr>
					<td><label>Täytteen englanninkielinen nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteNimi_eng" placeholder="Täytteen eng.nimi" maxlength="20" title="Täytteen nimi saa olla max 20 merkkiä pitkä" required value="${uusiTayte.nimi_eng}">
					 <%
               if (errors.containsKey("nimi")) {
                  out.println("<span class=\"error\">" + errors.get("nimi_eng") + "</span>");
               }
            %>
            </td>
					<td>Max 20 merkkiä</td>
				</tr>

				
				
				<tr>
					<td><label>Täytteen hinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteHinta" placeholder="Täytteen hinta" maxlength="5" step="any" min="0.50" max="10.00" required value="${uusiTayte.hinta}">
					<%
               if (errors.containsKey("hinta")) {
                  out.println("<span class=\"error\">" + errors.get("hinta") + "</span>");
               }
            %>
            </td>
					<td>Hinnan pitää olla 0,50 - 10,00</td>
				</tr>
				
				<tr>
					<td><label>Kilohinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteKilohinta" placeholder="Täytteen kilohinta" maxlength="5" step="any" min="0.50" max="99.99" required value="${uusiTayte.kilohinta}">
					<%
               if (errors.containsKey("kilohinta")) {
                  out.println("<span class=\"error\">" + errors.get("kilohinta") + "</span>");
               }
            %>
            </td>
					<td>Hinnan pitää olla 0,50 - 99,99</td>
				</tr>
			</table>

			
			

			<input type="submit" class="button" value="Täyte täydellinen"> <a
				href="MuokkaaTaytelistaServlet" class="button">Peruuta</a>
		</form>
	</div>
	
	<div id="footer">
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
