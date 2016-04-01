<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tayte"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


		<table>
			<caption>Taytelista</caption>
			<tr>
			
				<th>Numero</th>
				<th>Nimi</th>
				<th>Englanninkielinen nimi</th>
				<th>Hinta</th>
				<th>Kilohinta</th>
				<th>Muokkaa</th>
				<th>Poista</th>
			</tr>
			
			<%
	
				DecimalFormat decimal = new DecimalFormat("0.00");
				int taytenumero = 0;
				for (int i = 0; i <taytteet.size(); i++) {
					taytenumero++;
	

			%>

		<tr>
		
			<td width="50px"><%=taytenumero%></td>
			<td><%=taytteet.get(i).getNimi()%></td>
			<td><%=taytteet.get(i).getNimi_eng()%></td>
			<td width="100px"><%=decimal.format(taytteet.get(i).getHinta())%></td>
			<td width="100px"><%=decimal.format(taytteet.get(i).getKilohinta())%></td>
			
		</tr>
		
		<%
			}
		%>

</table>
</body>
</html>