<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Tayte"%>
<%@ page import="pizzicato.model.Mauste"%>
<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.Kayttaja"%>
<%@ page import="pizzicato.model.Tilausrivi"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />
<jsp:useBean id="tilaukset" type="java.util.ArrayList<Tilaus>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Hallinnointinäkymä</title>
</head>
<body>
	<div id="otsikkoloota">
		<p2 style="margin-left:5%;"> <u>Hallinnointinäkymä</u></p2>
		<a href="OmistajaListaaPizzatServlet" class="juomabutton"><p2>Edelliseen</p2></a>
	</div>
	<div id="hal_loota">

		<table>

			<tr>
				<th>TilausID</th>
				<th>Tilausaika</th>
				<th>Pizza</th>
				<th>Täytteet</th>
				<th>Mausteet</th>
				<th>Uunissa/Valmis</th>
			</tr>

			<%
				for (int i = 0; i < tilaukset.size(); i++) {
			%>
			<tr>
				<td><%=tilaukset.get(i).getId()%></td>
				<td><%=tilaukset.get(i).getTilausaika()%></td>
				<td>
					<%
						for (int j = 0; j < tilaukset.get(i).getTilausrivit().size(); j++) {
								for (int k = 0; k < pizzat.size(); k++) {
									if (tilaukset.get(i).getTilausrivit().get(j)
											.getTilattuTuote().getId() == pizzat.get(k)
											.getId()) {
					%><%=pizzat.get(k).getNimi()%>
					<%
						}
								}
							}
					%>
				</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>