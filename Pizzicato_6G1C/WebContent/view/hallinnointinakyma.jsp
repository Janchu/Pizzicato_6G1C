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
<title>Hallinnointinäkymä</title>
</head>
<body>
<div id="otsikkoloota">
<p2 style="margin-left:5%;"><u>Hallinnointinäkymä</u></p2>
		<a href="OmistajaListaaPizzatServlet" class="juomabutton"><p2>Edelliseen</p2></a>
</div>
<div id="hal_loota">
<div id="hal_kirjautuminen">Tervetuloa, Outi  </span><a href="" class="button2">Kirjaudu Ulos</a></div>
<a href="KokkiServlet" class="button3">Kokki</a>

<a href="KuskiServlet" class="button3">Kuski</a>

<a href="AvoimettilauksetServlet" class="button3">Avoimet tilaukset</a>

<a href="ValmiittilauksetServlet" class="button3">Valmiit tilaukset</a>
			
	</div>
</body>
</html>