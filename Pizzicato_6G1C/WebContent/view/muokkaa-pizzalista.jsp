<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>
<body>
	
	<nav id="navigaatio">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Pizzeria Pizzicato</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Etusivu</a></li>
      <li><a href="#">:3</a></li>
      <li><a href="#">Yhteystiedot</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>
	
	<div id="loota1">
	<div id="pizzalistataulukko">

		<table>
		<caption>Pizzalista</caption>
			<tr>
				<th>Numero</th>
				<th>Nimi</th>
				<th>Hinta</th>
				<th>Muokkaa</th>
				<th>Piilota</th>
				<th>Poista</th>
			</tr>

			<%
				int pizzanumero = 0;
				for (int i = 0; i < pizzat.size(); i++) {
					pizzanumero++;
			%>

			<tr>

				<td width="50px"><%=pizzanumero%></td>
				<td><%=pizzat.get(i).getNimi()%></td>
				<td width="100px"><%=pizzat.get(i).getHinta()%></td>
				<td width="50px"><a href="MuokkaaPizzaServlet"></a><input type="submit" value="Muokkaa pizzaa">
				<td width="50px"><a href="MuokkaaPizzaServlet"></a><input type="submit" value="Piilota / näytä">
				<td width="50px"><a href="MuokkaaPizzaServlet"></a><input type="submit" value="Poista pizza">
			</tr>
			
			<%
				}
			%><tr>
<td></td>
<td><a href="LisaaPizzaServlet" class="nappula">Lisää pizza</a></td>

</tr>
		</table>
	</div>
	<div id="nappula">
	
	<a href="ListaaPizzatServlet" class="nappula">Tallenna</a>
 	<a href="ListaaPizzatServlet" class="nappula">Peruuta</a>
 
 </div>
 
 
		
</body>
</html>