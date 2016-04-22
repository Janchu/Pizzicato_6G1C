<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pizzicato.model.Juoma"%>
<jsp:useBean id="juomat" type="java.util.ArrayList<pizzicato.model.Juoma>" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Lisää juoma</title>
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
Avoinna: Ma-La 11-21, Su 12-18<br>
+358 40 666 666<br>
Kuusitie 66<br>
Meilahti, 00270</p>
</div>
<div id="lootaoikea">

<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
</div>
</div>

<div id="otsikkoloota">
	<p>Lisää juoma</p>
</div>

<div id="loota2">

			<form method="post">


			<legend>Syötä juoman tiedot:</legend>
			<table>
				<tr>
					<td><label>Juoman nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="juomaNimi" placeholder="Juoman nimi" maxlength="20" title="Juoman nimi saa olla max 20 merkkiä pitkä"  value="${uusiJuoma.nimi}" required>
					 <%
            %></td>
					<td>Max 20 merkkiä</td>
				</tr>
				
				<tr>
					<td><label>Englanninkielinen nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="juomaNimi_eng" placeholder="Juoman eng nimi" maxlenght="20" title="Juoman nimi saa olla max 20 merkkiä pitkä" value="${uusiJuoma.nimi_eng}" required>
					<%
				%></td>
						<td>Max 20 merkkiä</td>
				</tr>

				<tr>
					<td><label>Hinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="juomaHinta" placeholder="Juoman hinta" maxlength="5" step="any" min="2.00" max="20.00"  value="${uusiJuoma.hinta}" required>
					<%
            %></td>
					<td>Hinnan pitää olla 2,00 - 10.00</td>
				</tr>
				<tr>
				<td><label>Koko: <span class="pakollinen">*</span></label></td>
				<td><input type="text" name="juomaKoko" placeholder="Juoman koko" maxlenght="4" step="any" value="${uusiJuoma.koko}" required>
				<%
				%>
				</tr>
			</table>

			<input type="submit" class="button" value="Juoma valmis!"> <a
				href="MuokkaaJuomalistaServlet" class="button">Peruuta</a>
				
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

