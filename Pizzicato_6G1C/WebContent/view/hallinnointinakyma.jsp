<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Hallinnointinäkymä</title>
</head>
<body>
<div id="otsikkoloota3">
<a href="OmistajaListaaPizzatServlet"><input type="button" class="button5" value="Edelliseen"></a>
<p2 style="margin-left:30%;"><u>Hallinnointinäkymä</u></p2>
<div id="hal_kirjautuminen">Tervetuloa, Outi  </span><a href="LogoutServlet" class="button2">Kirjaudu Ulos</a></div>
		
</div>
<div id="hal_loota">

<a href="KokkiServlet" class="button3">Kokki</a>

<a href="KuskiServlet" class="button3">Kuski</a>

<a href="AvoimettilauksetServlet" class="button3">Avoimet tilaukset</a>

<a href="ValmiittilauksetServlet" class="button3">Valmiit tilaukset</a>
			
	</div>
</body>
</html>