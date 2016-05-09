<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.Kayttaja"%>
<jsp:useBean id="tilaus" scope="request" class="pizzicato.model.Tilaus" />
<jsp:useBean id="tilaaja" scope="request" class="pizzicato.model.Kayttaja" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<title>Kuski</title>
</head>
<body>
<div id="otsikkoloota">

	<p2>Sami_kuski</p2>
</div>
<div id="hal_loota">
<div id="hal_kirjautuminen">Tervetuloa, Kuski  </span><a href="" class="button2">Kirjaudu Ulos</a></div>




<table class="kuski">

<tr>
<th>Tilauksen tiedot</th>
<th>Nappulat</th>

</tr>

<tr>
<% for (int i = 0; i < tilaus.getTilausrivit().size(); i++) { %>
<td width="400px"><label><input type="checkbox" /><div class="content">ID: <%=pizzanumero%>
<br>Tuotteet: <%=tilaus.getTilausrivit().get(i).getTilattuTuote().getNimi() %>
<br>Aika:
<br>Osoite:  <%=tilaaja.getOsoite() %>, <%=tilaaja.getPostinro() %> <%=tilaaja.getPostitmp() %>
<br>Tilaaja:  <%=tilaaja.getEtunimi() %> <%=tilaaja.getSukunimi() %>
<br>Puhelin: <%=tilaaja.getPuh() %>
<br>Hinta: <%=tilaus.getTilausrivit().get(i).getTilattuTuote().getHinta() %>

</div></label>
</td>

<td>
<form><select>
  <option value="kuljetukseen">Odottaa</option>
  <option value="kuljetuksessa">Kuljetuksessa</option>
  <option value="valmis">Valmis</option>
</select>
<input type="submit" value="Tallenna">
</form>

</td>

</tr>
	</table>		
	</div>

</body>
</html>