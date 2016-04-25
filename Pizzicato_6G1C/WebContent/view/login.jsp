<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kirjaudu sisään</title>
</head>
<body>
	<div id="loginloota">
		<form method="post">
			<h3>Käyttäjätunnus:</h3>
			<input type="text" name="kayttajatunnus" required>
			<h3>Salasana:</h3>
			<input type="password" name="salasana" required> <br>
			<%
				String message = (String) request.getAttribute("message");
				if (message != null) {
					out.println("<p>" + message + "</p>");
				}
			%>
			<br> <input type="Submit" class="button" value="Kirjaudu sisään"><a
				href="ListaaPizzatServlet" class="button">Peruuta</a>
		</form>
	</div>
</body>
</html>