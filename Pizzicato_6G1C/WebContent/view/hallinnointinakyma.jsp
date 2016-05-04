<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<%@ page import="pizzicato.model.Mauste"%>
<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.Kayttaja"%>
<%@ page import="pizzicato.model.Tilausrivi"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>" scope="request" />
<jsp:useBean id="ostoskori" type="java.util.ArrayList<Tilausrivi>" scope="request" />
<jsp:useBean id="mausteet" type="java.util.ArrayList<Mauste>" scope="request" />
<jsp:useBean id="kayttaja" class="pizzicato.model.Kayttaja" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>

<div id="lootaoikea">
		<% if (kayttaja.getTyyppi() != null) { %>
			<span class="valkoinen">Tervetuloa, <%=kayttaja.getEtunimi() %></span>
			<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
		<% } else { %>
			<a href="RekisterointiServlet" class="button2">Rekisteröidy</a>
			<a href="LoginServlet" class="button2">Kirjaudu Sisään</a>
		<% } %>

			<br>
			<div id="ostoskoributton2">
			<form method="get">
				<input type="hidden" name="ostoskori" value="<%=ostoskori%>">
				<img src="images/ostoskoriicon.png" width="40" height="40"><%=ostoskori.size()%>
				kpl, yht. 
				<div id="ostoskoributton1">
					<a href="OstoskoriServlet">Ostoskoriin</a>
				</div>
				</form>
			</div>
		</div>
	</div>
	
	<loota2>
	
	
	
	
	
	</loota2>
	
</body>
</html>