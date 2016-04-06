<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pizzicato.model.Juoma"%>
<jsp:useBean id="juomat" type="java.util.ArrayList<pizzicato.model.Juoma>" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div id="logoloota">
<div id="lootavasen">
<div class="logo">
<a href="ListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogo2.jpg" height="110%" width="110%"></a>
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
<a href="" class="button2">Kirjaudu Sisään</a>
<a href="EngListaaPizzatServlet"><img alt="lib" src="images/uklib3.jpg" width="32" height="32"></a>
</div>
</div>
</div>

<div id="loota1">


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
					<td><label>Hinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="juomaHinta" placeholder="Juoman hinta" maxlength="5" step="any" min="6" max="99.99"  value="${uusiJuoma.hinta}" required>
					<%
            %></td>
					<td>Hinnan pitää olla 6,00 - 99,99</td>
				</tr>
				<tr>
				<td><label>Koko: <span class="pakollinen">*</span></label></td>
				<td><input type="text" name="juomanKoko" placeholder="Juoman koko" maxlength="5" value="${uusiJuoma.koko}" required>
				<%
				%></td>
			</table>

			<input type="submit" class="button" value="Juoma valmis!"> <a
				href="MuokkaaJuomalistaServlet" class="button">Peruuta</a>
				
</form>
</div>

</body>
</html>

