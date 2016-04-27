<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Juoma"%>
<%@ page import="pizzicato.model.Tuote"%>
<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma>"
	scope="request" />
<jsp:useBean id="muokattavaJuomaId" scope="request"
	type="java.lang.Integer" />
<jsp:useBean id="errors" scope="request" type="java.util.HashMap"
	class="java.util.HashMap" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Muokkaa juomaa</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">

				<a href="OmistajaListaaJuomatServlet"><img alt="Pizzerian logo"
					src="images/pizzalogofin.png" height="100%" width="100%"></a>

			</div>
		</div>
		<div id="lootakeski">
			<p3>
				Avoinna: Ma-La 11-21, Su 12-18<br> +358 40 666 666<br>
				Kuusitie 66<br> Meilahti, 00270
			</p3>
		</div>

		<div id="lootaoikea">
			<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
		</div>

	</div>
	</div>

	<div id="otsikkoloota">
		<p2>Muokkaa juomaa</p2>
	</div>

	<div id="loota1">
		<%
			int id = muokattavaJuomaId;
			String juomaNimi = "";
			String juomaNimi_eng = "";
			double juomaHinta = 0;
			double juomaKoko = 0;
			for(int i = 0; i < juomat.size(); i++) {
				if(juomat.get(i).getId() == muokattavaJuomaId) {
					juomaNimi = juomat.get(i).getNimi();
					juomaNimi_eng = juomat.get(i).getNimi_eng();
					juomaHinta = juomat.get(i).getHinta();
					juomaKoko = juomat.get(i).getKoko();
					
				}
			}
		%>

		<form method="post">
			<fieldset>

			<legend>Syötä juoman tiedot:</legend>

			<%
				if (!errors.isEmpty()) {
			%>
			<div class="pakollinenLoota">
				<p class="pakollinen">
					<%
						if (errors.containsKey("nimi")) {
								out.println("<span class=\"error\">" + errors.get("nimi")
										+ "</span><br>");
							}
							if (errors.containsKey("nimi_eng")) {
								out.println("<span class=\"error\">"
										+ errors.get("nimi_eng") + "</span><br>");
							}
							if (errors.containsKey("hinta")) {
								out.println("<span class=\"error\">" + errors.get("hinta")
										+ "</span><br>");
							}
							if (errors.containsKey("koko")) {
								out.println("<span class=\"error\">" + errors.get("koko")
										+ "</span><br>");
							}
					%>
				
			</div>
			<br>
			<%
				}
			%>
			
		<p>
			<label>Juoman nimi: <span class="pakollinen">*</span></label>
			<input type="text" name="juomaNimi" value="<%=juomaNimi%>"
			required maxlength="20">
			<%
 				if (errors.containsKey("nimi")) {
 					out.println("<span class=\"error\">" + errors.get("nimi")
 								+ "</span>");
 					}
 			%>
 				Max 20 merkkiä
 			</p>
 			
 			<p>	
 				<label>Englanninkielinen nimi: <span class="pakollinen">*</span></label>
 				<input type="text" name="juomaNimi_eng"
				value="<%=juomaNimi_eng%>" required maxlength="20"> 
					<%
 						if (errors.containsKey("nimi_eng")) {
 							out.println("<span class=\"error\">" + errors.get("nimi_eng")
 									+ "</span>");
 						}
 					%>
 					Max 20 merkkiä
 			</p>
 			<p>
 					<label>Hinta: <span class="pakollinen">*</span></label>
					<input type="text" name="juomaHinta" maxlength="5"
						step="any" min="2.00" max="20.00" value="<%=juomaHinta%>" required>
						<%
							if (errors.containsKey("hinta")) {
								out.println("<span class=\"error\">" + errors.get("hinta")
										+ "</span>");
							}
						%>
					Hinta muotoa 0,00 ja hinnan pitää olla 2.00 - 20.00 euroa
				</p>
				
				<p>
				<label>Koko: <span class="pakollinen">*</span></label>
					<input type="text" name="juomaKoko" maxlength="4"
					step="any" value="<%=juomaKoko%>" required>
					
					 <%
					if(errors.containsKey("Koko")) {
					out.println("<span class=\"error\">" + errors.get("koko") + "</span>");
					}
					%>
					Max 4 merkkiä
				</p>
			
		

			<%-- seuraavassa koodinpätkässä on nappulat jes --%>
			<input type="hidden" name="juomaId" value="<%=id%>">
			<input type="submit" class="button" value="Juoma valmis!">
			<a href="MuokkaaJuomalistaServlet" class="button">Peruuta</a>
			</fieldset>
		</form>
	</div>

	<div id="footer">
		<div id="footer_sisältö">

			<p>
				Pizzeria Pizzicato<br> Puhelin: +358 40 666 666<br>
				Sähköpostiosoite: pizzeria.pizzicato@gmail.com<br> Katuosoite:
				Kuusitie 66 <br> Postitoimipaikka: Meilahti, 00270 <br>
			</p>
		</div>

	</div>

</body>
</html>