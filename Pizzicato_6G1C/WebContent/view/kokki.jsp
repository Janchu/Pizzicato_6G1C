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

<table>

<tr>
<th>TilausID</th>
<th>Pizza</th>
<th>Täytteet</th>
<th>Mausteet</th>
<th>Uuniss/Valmis</th>
</tr>

<%
						DecimalFormat decimal = new DecimalFormat("0.00");
						int pizzanumero = 0;
						for (int i = 0; i < pizzat.size(); i++) {
							if (pizzat.get(i).getTyyppi().equalsIgnoreCase("pizza")
									|| pizzat.get(i).getTyyppi()
											.equalsIgnoreCase("fantasia")) {
								if (pizzat.get(i).getNakyvyys() == 1) {
									pizzanumero++;
					%>

<tr>
<td><%=pizzanumero%><td>
<td><%=pizzat.get(i).getNimi()%></td>
<td><%
							for (int j = 0; j < pizzat.get(i).getTaytelista()
												.size(); j++) {
						%> <%=pizzat.get(i).getTaytelista().get(j)
									.getNimi()%><%
 	if (j + 1 < pizzat.get(i).getTaytelista().size()) {
 %>, <%
 	}
 				}
 %>
 </td>
 <td>Mausteet</td>
 <td>nabls</td>
 </tr>
			
	</div>
</body>
</html>