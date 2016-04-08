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
<title>Muokkaa pizzaa</title>
</head>
/** jeejee**/
<body>

<div id="logoloota">
<div id="lootavasen">
<div class="logo">

<a href="OmistajaListaaPizzatServlet"><img alt="Pizzerian logo" src="images/pizzalogofin.png" height="100%" width="100%"></a>

</div>
</div>
<div id="lootakeski">
<p style= "margin-top:100px; font-size:170%; font-family:Kozuka Gothic Pro EL;">
Avoinna: 07-23<br>
Puhelin: +358 0123456<br>
Katuosoite: Katu2<br>
Postitoimipaikka: Helsinki, 010101</p>
</div>
<div id="lootaoikea">


<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
<a href="EngListaaPizzatServlet"><img alt="lib" src="images/uklib3.jpg" width="32" height="32"></a>
</div>
</div>
</div>
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
					<td><input type="text" name="tayteNimi" maxlength="20" title="Täytteen nimi saa olla max 20 merkkiä pitkä" required value="<%=tayteNimi%>">
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
					<td><input type="text" name="tayteNimi_eng" maxlength="20" title="Täytteen nimi saa olla max 20 merkkiä pitkä" required value="<%=tayteNimi_eng%>">
						
						<%
               if (errors.containsKey("nimi")) {
                  out.println("<span class=\"error\">" + errors.get("nimi") + "</span>");
               }
            %></td>
					<td>Max 20 merkkiä</td>
				</tr>
				
				<tr>
					<td><label>Hinta: <span class="pakollinen">*</span></label></td>
					<td><input type="text" name="tayteHinta" maxlength="5" step="any" min="0.50" max="10.00" required
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
					<td><input type="text" name="tayteKilohinta" maxlength="5" step="any" min="0.50" max="99.99" required
						value="<%=tayteKilohinta%>" >
						<%
               if (errors.containsKey("hinta")) {
                  out.println("<span class=\"error\">" + errors.get("hinta") + "</span>");
               }
            %></td>
					<td>Hinta muotoa 0,00 ja hinnan pitää olla 0,50 - 99,99 euroa</td>
				</tr>
			</table>





			<input type="hidden" name="tayteId" value="<%=id%>"> <input
				type="submit" class="button" value="Täyte täydellinen!"> <a
				href="MuokkaaTaytelistaServlet" class="button">Peruuta</a>

		</form>
	</div>
</body>
</html>
