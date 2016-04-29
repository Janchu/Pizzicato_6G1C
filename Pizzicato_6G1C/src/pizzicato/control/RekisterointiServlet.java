package pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

		// ArrayList tallennetaan request-olioon jsp:lle vietäväksi
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

				// System.out.println(leikkikayttaja);

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

		System.out.println("lomakkeelta" + etunimi);

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (etunimi == null || etunimi.trim().length() == 0) {
			errors.put("etunimi", "Etunimi vaaditaan.");
		} else {

			Pattern p = Pattern.compile("^[a-zA-ZÅÄÖåäö\\- ]+$");
			Matcher m = p.matcher(etunimi);

			if (m.find() == false) {
				errors.put("etunimi", "Etunimi sisältää kiellettyjä merkkejä");
			} else {
				String uusiEtunimi = etunimi.replace('ä', 'a');
				uusiEtunimi = etunimi.replace('å', 'a');
				uusiEtunimi = etunimi.replace('ö', 'o');
				request.setAttribute("etunimi", uusiEtunimi);

			}
		}

		// Haetaan syötetty sukunimi validointia varten
		String sukunimi = request.getParameter("kayttajaSukunimi");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (sukunimi == null || sukunimi.trim().length() == 0) {
			errors.put("sukunimi", "Sukunimi vaaditaan.");
		} else {

			Pattern p = Pattern.compile("^[a-zA-ZÅÄÖåäö\\- ]+$");
			Matcher m = p.matcher(sukunimi);

			if (m.find() == false) {
				errors.put("sukunimi", "Sukunimi sisältää kiellettyjä merkkejä");
			} else {
				String uusiSukunimi = sukunimi.replace('ä', 'a');
				uusiSukunimi = sukunimi.replace('å', 'a');
				uusiSukunimi = sukunimi.replace('ö', 'o');
				request.setAttribute("sukunimi", uusiSukunimi);
			}
		}

			// Haetaan syötetty salasana validointia varten
			String salasana = request.getParameter("kayttajaSalasana");

			// Tarkistetaan, ettei kenttä ole tyhjä
			if (salasana == null || salasana.trim().length() == 0) {
				errors.put("salasana", "Salasana vaaditaan.");
			} else {
				if (salasana
						.matches("\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b") == false) {
					errors.put("salasana", "Salasana on väärän muotoinen");
				} else {
					if (salasana.trim().length() > 30) {
						errors.put("salasana", "Salasana on liian pitkä");
					} else {
						request.setAttribute("salasana", salasana);
					}
				}
			}

			// Haetaan syötetty puh validointia varten
			String puh = request.getParameter("kayttajaPuh");

			// Tarkistetaan, ettei kenttä ole tyhjä
			if (puh == null || puh.trim().length() == 0) {
				errors.put("puh", "Puh vaaditaan.");
			} else {
			Pattern p = Pattern.compile("^[0-9\\+ ]+$");
			Matcher m = p.matcher(puh);

			if (m.find() == false) {
				errors.put("puh", "Puhelinnumero sisältää kiellettyjä merkkejä");
			} else {
				if (puh.length() < 10 || puh.length() > 13) {
					errors.put("puh", "Puhelinnumero 10-13 merkillä");
				} else {
					request.setAttribute("puh", puh);
				}
			}
		}

		// Haetaan syötetty osoite validointia varten
		String osoite = request.getParameter("kayttajaOsoite");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (osoite == null || osoite.trim().length() == 0) {
			errors.put("osoite", "Osoite vaaditaan.");
		}else {
			if (osoite.trim().length() > 30) {
				errors.put("osoite", "Osoite on liian pitkä");
			} else {

				Pattern p = Pattern.compile("^[0-9a-zA-ZÅÄÖåäö ]+$");
				Matcher m = p.matcher(osoite);

				if (m.find() == false) {
					errors.put("osoite", "Osoite sisältää kiellettyjä merkkejä");
				} else {
					String uusiOsoite = osoite.replace('ä', 'a');
					uusiOsoite = osoite.replace('å', 'a');
					uusiOsoite = osoite.replace('ö', 'o');
					request.setAttribute("osoite", uusiOsoite);
				}
			}
		}

		

		// Haetaan syötetty postinro validointia varten
		String postinro = request.getParameter("kayttajaPostinro");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (postinro == null || postinro.trim().length() == 0) {
			errors.put("postinro", "Postinro vaaditaan.");
		} else {
			if (postinro.trim().length() != 5) {
				errors.put("postinro", "Postinumeron pitää sisältää 5 numeroa");
			} else {

				Pattern p = Pattern.compile("^[0-9]+$");
				Matcher m = p.matcher(postinro);

				if (m.find() == false) {
					errors.put("postinro",
							"Postinumero saa sisältää vain numeroita");
				} else {
					request.setAttribute("postinro", postinro);
				}
			}
		}

		// Haetaan syötetty postitmp validointia varten
		String postitmp = request.getParameter("kayttajaPostitmp");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (postitmp == null || postitmp.trim().length() == 0) {
			errors.put("postitmp", "Postitmp vaaditaan.");
		} else {
			if (postitmp.trim().length() > 20) {
				errors.put("postitmp", "Postitoimipaikka max 20 merkkiä");
			} else {

				Pattern p = Pattern.compile("^[0-9a-zA-ZÅÄÖåäö\\- ]+$");
				Matcher m = p.matcher(postitmp);

				if (m.find() == false) {
					errors.put("postitmp",
							"Postitoimipaikka saa sisältää vain kirjaimia");
				} else {
					String uusiPostitmp = postitmp.replace('ä', 'a');
					uusiPostitmp = postitmp.replace('å', 'a');
					uusiPostitmp = postitmp.replace('ö', 'o');
					request.setAttribute("postitmp", uusiPostitmp);
				}
			}
		}


		// Haetaan syötetty email validointia varten
		String email = request.getParameter("kayttajaEmail");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (email == null || email.trim().length() == 0) {
			errors.put("email", "Email vaaditaan.");
		} else {
			if (email
					.matches("\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b") == false) {
				errors.put("email", "Sähköposti on väärän muotoinen");
			} else {
				if (email.trim().length() > 60) {
					errors.put("email", "Sähköposti on liian pitkä");
				} else {
					request.setAttribute("email", email);
				}
			}
		}



		request.setAttribute("errors", errors);
		request.setAttribute("uusiKayttaja", uusiKayttaja);

		System.out.println(uusiKayttaja);

		return errors;
	}

}
