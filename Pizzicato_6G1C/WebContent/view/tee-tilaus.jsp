<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/tyyli.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<title>Ostoskori</title>
</head>
<body>

	<div id="logoloota">
		<div id="lootavasen">
			<div class="logo">
				<a href="OmistajaListaaPizzatServlet"><img alt="Pizzerian logo"
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
		</div>

	</div>

	<div id="otsikkoloota">
		<p style="margin-left: 15%;">Ostoskori</p>

	</div>

	<!-- lootan sisällä on pääsisältö, kuten pizzalista ja nappulat -->

	<div id="loota1">



		<!-- pizzalista on toteutettu taulukkona -->

		<div id="tuotelistataulukko">
			<div id="tablescoller">

				<form method="post">
					<fieldset>
						<p>
							<label>Etunimi: </label><input type="text" name="etunimi">
						</p>
						<p>
							<label>Sukunimi: </label><input type="text" name="sukunimi">
						</p>
						<p>
							<label>Puh: </label><input type="text" name="puh">
						</p>
						<p>
							<label>Email: </label><input type="text" name="email">
						</p>
						<p>
							<label>Osoite: </label><input type="text" name="osoite">
						</p>
						<p>
							<label>Postinumero: </label><input type="text" name="postinro">
						</p>
						<p>
							<label>Postitoimipaikka: </label><input type="text"
								name="postitmp">
						</p>
						<p>
							<label>Lisätiedot: </label><input type="text" name="lisatiedot">
						</p>
						<input type="submit" value="Jatka">
					</fieldset>
				</form>

				<a href="ListaaPizzatServlet" class="button2">Etusivulle</a> <a
					href="ListaaPizzatServlet" class="button2">Jatka tilausta</a>

			</div>
		</div>
	</div>

	<!-- footer_sisältö divillä pystyy liikuttamaan sisällön sijaintia ym. -->

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
