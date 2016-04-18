<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

	<div id = "loota1">
		<div id="juomalistataulukko">
		<div id="tablescoller">
			<table>
			
				<tr>	
				
					<th>Numero</th>
					<th>Nimi</th>
					<th>Hinta</th>
					<th>Näkyvillä</th>
					<th>Muokkaa</th>
					<th>Piilota</th>
					<th>Poista</th>
				</tr>
				
				<%
					DecimalFormat decimal = new DecimalFormat("0.00");
					int juomanumero = 0;
					for(int i = 0; i < juomat.size(); i++) {
					juomanumero++;
					int nakyvyysInt = juomat.get(i).getNakyvyys();
					String nakyvyys = "";
					if(nakyvyysInt == 1) {
					nakyvyys = "Näkyvillä";
					}else {
					nakyvyys = "Piilossa";
					}
				%>
				
				<tr>
				
					<td width="50px"><%=juomanumero%></td>
					<td><b><%=juomat.get(i).getNimi()%></b></td>
					<td width="100px"><%=decimal.format(juomat.get(i).getHinta())%></td>
					<td width="50px"><%=nakyvyys%></td>
					<td width="50px"><a href="MuokkaaPizzaServlet?PizId=<%=juomat.get(i).getId()%>"><img alt="lib" src="images/muokkaaicon.png" width="32" height="32"></a></td>
					<td width="50px"><a href="PiilotaPizzaServlet?Nakyvyys=<%=juomat.get(i).getNakyvyys()%>&Id=<%=pizzat.get(i).getId()%>"><img alt="lib" src="images/showicon.png" width="32" height="32"></a></td>
					<td width="50px"><a href="PoistaPizzaServlet?PizId=<%=juomat.get(i).getId()%>"
						onclick="return confirm('Haluatko varmasti poistaa Juoman?')"><img alt="lib" src="images/remove.png" width="32" height="32"></a></td></table></div></div></div>

</body>
</html>