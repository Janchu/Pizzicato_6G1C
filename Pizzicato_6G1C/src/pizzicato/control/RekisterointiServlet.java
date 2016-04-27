package pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Kayttaja;
import pizzicato.model.dao.KayttajaDAO;

/**
 * Servlet implementation class RekisterointiServlet
 */
@WebServlet("/RekisterointiServlet")
public class RekisterointiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan KayttajaDAO, ja molemmille ArrayListit
		KayttajaDAO kayttajadao = new KayttajaDAO();
		ArrayList<Kayttaja> kayttajat = kayttajadao.findAll();

		//ArrayList tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("kayttajat", kayttajat);

		// Lähetetään jsp:lle
		String jsp = "/view/rekisterointi.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Luodaan KayttajaDAO
		KayttajaDAO kayttajadao = new KayttajaDAO();
		ArrayList<Kayttaja> kayttajat = kayttajadao.findAll();

		// ArrayList tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("kayttajat", kayttajat);
		
		RequestDispatcher jsp = getServletContext().getRequestDispatcher(
				"/view/rekisterointi.jsp");
		
		
		HashMap<String, String> errors = validateRekisteroi(request);
		if (!errors.isEmpty()) {
			jsp.forward(request, response);
		} else {
			
			System.out.println("Ei virheitä");
			
			int id = 0;

			// Futureproof
			String tyyppi = "asiakas";

			try {
				// Luodaan uusi kayttaja-olio kantaan
				Kayttaja leikkikayttaja = (Kayttaja) request
						.getAttribute("uusiKayttaja");
				String etunimi = leikkikayttaja.getEtunimi();
				String sukunimi = leikkikayttaja.getSukunimi();
				String salasana = leikkikayttaja.getSalasana();
				String puh = leikkikayttaja.getPuh();
				String osoite = leikkikayttaja.getOsoite();
				String postinro = leikkikayttaja.getPostinro();
				String postitmp = leikkikayttaja.getPostitmp();
				String email = leikkikayttaja.getEmail();
				
				System.out.println("tyyppi" + tyyppi);

				Kayttaja uusiKayttaja = new Kayttaja(id, etunimi, sukunimi,
						salasana, tyyppi, puh, osoite, postinro, postitmp,
						email);
				
				//System.out.println(leikkikayttaja);

				// Kutsutaan create-kayttaja-metodia
				kayttajadao.create(uusiKayttaja);

				// Uudelleenohjataan Etusivulle
				response.sendRedirect("ListaaPizzatServlet");

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static HashMap<String, String> validateRekisteroi(
			HttpServletRequest request) {
		Kayttaja uusiKayttaja = new Kayttaja();
		KayttajaDAO kayttajadao = new KayttajaDAO();
		ArrayList<Kayttaja> kayttajat = kayttajadao.findAll();
		HashMap<String, String> errors = new HashMap<String, String>();

		// Haetaan syötetty etunimi validointia varten
		String etunimi = request.getParameter("kayttajaEtunimi");
		
		System.out.println("lomakkeelta" +etunimi);
		
		// Tarkistetaan, ettei kenttä ole tyhjä
		if (etunimi == null || etunimi.trim().length() == 0) {
			errors.put("etunimi", "Etunimi vaaditaan.");
		}

		int maxLengthEtu = (etunimi.length() < 30) ? etunimi.length() : 30;
		String rajattuEtunimi = etunimi.substring(0, maxLengthEtu);
		etunimi = rajattuEtunimi;
		uusiKayttaja.setEtunimi(etunimi);

		// Haetaan syötetty sukunimi validointia varten
		String sukunimi = request.getParameter("kayttajaSukunimi");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (sukunimi == null || sukunimi.trim().length() == 0) {
			errors.put("sukunimi", "Sukunimi vaaditaan.");
		}

		int maxLengthSuku = (sukunimi.length() < 30) ? sukunimi.length() : 30;
		String rajattuSukunimi = sukunimi.substring(0, maxLengthSuku);
		sukunimi = rajattuSukunimi;
		uusiKayttaja.setSukunimi(sukunimi);

		// Haetaan syötetty salasana validointia varten
		String salasana = request.getParameter("kayttajaSalasana");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (salasana == null || salasana.trim().length() == 0) {
			errors.put("salasana", "Salasana vaaditaan.");
		}

		int maxLengthSala = (salasana.length() < 20) ? salasana.length() : 20;
		String rajattuSalasana = salasana.substring(0, maxLengthSala);
		salasana = rajattuSalasana;
		uusiKayttaja.setSalasana(salasana);

		// Haetaan syötetty puh validointia varten
		String puh = request.getParameter("kayttajaPuh");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (puh == null || puh.trim().length() == 0) {
			errors.put("puh", "Puh vaaditaan.");
		}

		int maxLengthPuh = (puh.length() < 10) ? puh.length() : 10;
		String rajattuPuh = puh.substring(0, maxLengthPuh);
		puh = rajattuPuh;
		uusiKayttaja.setPuh(puh);

		// Haetaan syötetty osoite validointia varten
		String osoite = request.getParameter("kayttajaOsoite");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (osoite == null || osoite.trim().length() == 0) {
			errors.put("osoite", "Osoite vaaditaan.");
		}

		int maxLengthOsoite = (osoite.length() < 30) ? osoite.length() : 30;
		String rajattuOsoite = osoite.substring(0, maxLengthOsoite);
		osoite = rajattuOsoite;
		uusiKayttaja.setOsoite(osoite);

		// Haetaan syötetty postinro validointia varten
		String postinro = request.getParameter("kayttajaPostinro");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (postinro == null || postinro.trim().length() == 0) {
			errors.put("postinro", "Postinro vaaditaan.");
		}

		int maxLengthPostinro = (postinro.length() < 5) ? postinro.length() : 5;
		String rajattuPostinro = postinro.substring(0, maxLengthPostinro);
		postinro = rajattuPostinro;
		uusiKayttaja.setPostinro(postinro);

		// Haetaan syötetty postitmp validointia varten
		String postitmp = request.getParameter("kayttajaPostitmp");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (postitmp == null || postitmp.trim().length() == 0) {
			errors.put("postitmp", "Postitmp vaaditaan.");
		}

		int maxLengthPostitmp = (postitmp.length() < 30) ? postitmp.length()
				: 30;
		String rajattuPostitmp = postitmp.substring(0, maxLengthPostitmp);
		postitmp = rajattuPostitmp;
		uusiKayttaja.setPostitmp(postitmp);

		// Haetaan syötetty email validointia varten
		String email = request.getParameter("kayttajaEmail");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (email == null || email.trim().length() == 0) {
			errors.put("email", "Email vaaditaan.");
		}

		int maxLengthEmail = (email.length() < 60) ? email.length() : 60;
		String rajattuEmail = email.substring(0, maxLengthEmail);
		email = rajattuEmail;
		uusiKayttaja.setEmail(email);

		request.setAttribute("errors", errors);
		request.setAttribute("uusiKayttaja", uusiKayttaja);
		
		System.out.println(uusiKayttaja);
		
		return errors;
	}

}
