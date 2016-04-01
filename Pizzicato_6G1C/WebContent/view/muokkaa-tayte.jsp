<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>"
	scope="request" />
<jsp:useBean id="muokattavaTayteId" scope="request"
	type="java.lang.Integer" />
<jsp:useBean id="errors" scope="request" type="java.util.HashMap" class="java.util.HashMap" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Muokkaa pizzaa</title>
</head>
/** jeejee**/
<body>
	<nav id="navigaatio">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Pizzeria Pizzicato</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="ListaaPizzatServlet">Etusivu</a></li>
			<li><a href="#">:3</a></li>
			<li><a href="#">Yhteystiedot</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					Rekisteröidy</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
					Kirjaudu sisään</a></li>
		</ul>
	</div>
	</nav>

	<div id="loota1">





		<%
			int id = muokattavaTayteId;
			double tayteHinta = 0;
			double tayteKilohinta = 0 ;
			String tayteNimi = "";
			String tayteNimi_eng = "";
			
			ArrayList<Tayte> vanhatTaytteet = new ArrayList<Tayte>();

			for (int i = 0; i < taytteet.size(); i++) {
				if (id == taytteet.get(i).getId()) {
					tayteNimi = taytteet.get(i).getNimi();
					tayteNimi_eng = taytteet.get(i).getNimi_eng();
					tayteHinta = taytteet.get(i).getHinta();
					tayteKilohinta = taytteet.get(i).getKilohinta();
					
				}
			}
		%>
		<form method="post">


			<legend>Syötä täytteen tiedot:</legend>
			<table>
				<tr>
					<td><label>Täytteen nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteNimi" value="<%=tayteNimi%>"
						>
						<%
               if (errors.containsKey("nimi")) {
                  out.println("<span class=\"error\">" + errors.get("nimi") + "</span>");
               }
            %></td>
					<td>Max 20 merkkiä</td>
				</tr>
				
						<tr>
					<td><label>Täytteen englanninkielinen nimi: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteNimi_eng" value="<%=tayteNimi_eng%>"
						>
						<%
               if (errors.containsKey("nimi")) {
                  out.println("<span class=\"error\">" + errors.get("nimi") + "</span>");
               }
            %></td>
					<td>Max 20 merkkiä</td>
				</tr>
				
				<tr>
					<td><label>Hinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteHinta" maxlength="5" step="any" min="0.50" max="10.00"
						value="<%=tayteHinta%>" >
						<%
               if (errors.containsKey("hinta")) {
                  out.println("<span class=\"error\">" + errors.get("hinta") + "</span>");
               }
            %></td>
					<td>Hinta muotoa 0,00 ja hinnan pitää olla 0,50 - 10,00 euroa</td>
				</tr>
				
				<tr>
					<td><label>Kilohinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteHinta" maxlength="7" step="any" min="1.00" max="1000.00"
						value="<%=tayteHinta%>" >
						<%
               if (errors.containsKey("hinta")) {
                  out.println("<span class=\"error\">" + errors.get("hinta") + "</span>");
               }
            %></td>
					<td>Hinta muotoa 0,00 ja hinnan pitää olla 1,00 - 1000,00 euroa</td>
				</tr>
			</table>





			<input type="hidden" name="tayteId" value="<%=id%>"> <input
				type="submit" class="button" value="Täyte täydellinen!"> <a
				href="MuokkaaTaytelistaServlet" class="button">Peruuta</a>

		</form>
	</div>
</body>
</html>