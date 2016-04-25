<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato.model.Tuote"%>
<%@ page import="pizzicato.model.Juoma"%>
<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Listaa juomat</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">
				<a href="ListaaPizzatServlet"><img alt="Pizzerian logo"
					src="images/pizzalogofin.png" height="100%" width="100%"></a>
			</div>
		</div>
		<div id="lootakeski">
			<p
				style="margin-top: 100px; font-size: 170%; font-family: Kozuka Gothic Pro EL;">
				Avoinna: Ma-La 11-21, Su 12-18<br> +358 40 666 666<br>
				Kuusitie 66<br> Meilahti, 00270
			</p>
		</div>

		<div id="lootaoikea">
			<a href="LogoutServlet" class="button2">Kirjaudu Ulos</a>
			<a href="EngListaaJuomattServlet"><img alt="lib" src="images/uklib4.png" width="32" height="32"></a>
			<a href="ListaaJuomatServlet"><img alt="lib" src="images/finlib.png" width="32" height="32"></a><br>
			<div id="ostoskoributton2">
<img src="images/ostoskoriicon.png" width="40" height="40">1 kpl, yht. 7,00€
<div id="ostoskoributton1">
<a href="ostoskori.html">Ostoskoriin</a>
</div>
</div>
		</div>
	</div>
	</div>

	<div id="otsikkoloota">
<a href="ListaaPizzatServlet" class="pizzalistabutton">Pizzalista</a>
<a href="ListaaJuomatServlet" class="juomabutton"><u>Juomat</u></a>
</div>
<div id="loota1">
<div id="tuotelistataulukko">
<div id="tablescoller">

		<table>
		
			<tr>
				<th>Numero</th>
				<th>Nimi</th>
				<th>Hinta</th>
				<th></th>
				<th></th>
			</tr>

			<%
				DecimalFormat decimal = new DecimalFormat("0.00");
				int juomanumero = 0;
				for (int i = 0; i < juomat.size(); i++) {

					if (juomat.get(i).getTyyppi().equalsIgnoreCase("juoma")) {

						if (juomat.get(i).getNakyvyys() == 1) {
							juomanumero++;
			%>

			<tr>
				<td width="100px"><%=juomanumero%></td>
				<td><b><%=juomat.get(i).getNimi()%></b>
				<td width="50px"><%=decimal.format(juomat.get(i).getHinta())%></td>
				<td width="100px"><input type="text" size=2 maxlength="2" name="maara" onChange='this.value=CKquantity(this.value)' value="1">  Määrä<input type=button value='  Lisää ostoskoriin  ' onClick='AddToCart(this.form)'></td>
			</tr>

			<%
				}
					}
				}
			%>
			</table>
			</div>
			</div>
			</div>
			
			<div id="footer">
				<div id="footer_sisältö">
					<p>
						Pizzeria Pizzicato<br> Puhelin: +358 40 666 666<br>
						Sähköpostiosoite: pizzeria.pizzicato@gmail.com<br>
						Katuosoite: Kuusitie 66 <br> Postitoimipaikka: Meilahti,
						00270 <br>
					</p>
				</div>
			</div>
</body>
</html>