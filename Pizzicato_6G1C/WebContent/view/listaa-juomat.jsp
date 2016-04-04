<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Juoma"%>
<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma>" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div id="juomalistataulukko">
		
	

			<table>
				<caption>Juomalista</caption>

				<tr>
					<th>Numero</th>
					<th>Nimi</th>
					<th>Hinta</th>
				</tr>

				<%
				
					DecimalFormat decimal = new DecimalFormat("0.00");
					int juomanumero = 0;
					for (int i = 0; i < juomat.size(); i++) {

						if (juomat.get(i).getNakyvyys() == 1) {
							juomanumero++;
				%>
				
				<tr>
					<td width = "100px"><%=juomanumero%></td>
					<td><b><%=juomat.get(i).getNimi()%></b>
					<td width="50px"><%=decimal.format(juomat.get(i).getHinta())%></td>
					<td width="50px"><%=juomat.get(i).getKoko()%></td>
				</tr>
				
				<%
						}
					}
				%>

</body>
</html>