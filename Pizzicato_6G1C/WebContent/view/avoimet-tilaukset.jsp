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

<title>Avoimet tilaukset</title>
</head>
<body>
<div id="otsikkoloota">
<p2 style="margin-left:5%;"><u>Hallinnointinäkymä</u></p2>
<a href="OmistajaListaaPizzatServlet" class="juomabutton"><p2>Edelliseen</p2></a>
</div>
<div id="hal_loota">
<div id="hal_kirjautuminen">Tervetuloa, Omistaja  </span><a href="" class="button2">Kirjaudu Ulos</a></div>

<table class="kuski">

<tr>
<th>Avoimet tilaukset</th>


</tr>

<tr>
<% for (int i = 0; i < tilaus.getTilausrivit().size(); i++) { %>
<td width="400px"><label><input type="checkbox" /><div class="content">ID: <%=pizzanumero%>
<br>Tuotteet: <%=tilaus.getTilausrivit().get(i).getTilattuTuote().getNimi() %>
<br>Mausteet:Pasket ku näide mausteide pitäis tulostoo jo tuotteista :D 
<br>Aika:
<br>Tilaaja:  <%=tilaaja.getEtunimi() %> <%=tilaaja.getSukunimi() %>
<br>Osoite:  <%=tilaaja.getOsoite() %>, <%=tilaaja.getPostinro() %> <%=tilaaja.getPostitmp() %>
<br>Puhelin: <%=tilaaja.getPuh() %>
<br>Hinta: <%=tilaus.getTilausrivit().get(i).getTilattuTuote().getHinta() %></span>
</div></label>
</td>

</tr>
	</table>		
	</div>
</body>
</html>