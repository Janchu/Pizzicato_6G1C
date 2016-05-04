package pizzicato.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzicato.model.Kayttaja;
import pizzicato.model.Tilaus;

/**
 * Servlet implementation class TeeTilausServlet
 */
@WebServlet("/TeeTilausServlet")
public class TeeTilausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		Tilaus ostoskori = (Tilaus) session.getAttribute("ostoskori");

		if (ostoskori == null) {
			ostoskori = new Tilaus();
		}

		
		
		HashMap<String, String> errors = new HashMap<String, String>();

		request.setAttribute("errors", errors);
		request.setAttribute("toimitus", "");
		request.setAttribute("maksutapa", "");
		request.setAttribute("etunimi", "");
		request.setAttribute("sukunimi", "");
		request.setAttribute("puh", "");
		request.setAttribute("email", "");
		request.setAttribute("osoite", "");
		request.setAttribute("postinro", "");
		request.setAttribute("postitmp", "");
		request.setAttribute("lisatiedot", "");

		// Käyttäjän checkaus
		Kayttaja kayttaja = (Kayttaja) session.getAttribute("kayttaja");
		
		if (kayttaja == null) {
			kayttaja = new Kayttaja();
			
		}
		
		request.setAttribute("tilaus", ostoskori);
		request.setAttribute("tilaaja", kayttaja);
		

		// Lähetetään jsp:lle
		String jsp = "/view/tee-tilaus.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		RequestDispatcher jsp = getServletContext().getRequestDispatcher(
				"/view/tee-tilaus.jsp");

		HashMap<String, String> errors = validateTilaus(request);

		if (!errors.isEmpty()) {
			jsp.forward(request, response);
		}

		Tilaus tilaus = new Tilaus();
		Kayttaja tilaaja = new Kayttaja();

		tilaus = (Tilaus) session.getAttribute("ostoskori");

		String etunimi = request.getParameter("etunimi");
		String sukunimi = request.getParameter("sukunimi");
		String puh = request.getParameter("puh");
		String email = request.getParameter("email");
		String osoite = request.getParameter("osoite");
		String postinro = request.getParameter("postinro");
		String postitmp = request.getParameter("postitmp");
		String lisatiedot = request.getParameter("lisatiedot");

		tilaus.setTila("Valmis");
		tilaus.setToimitus(request.getParameter("toimitus"));
		tilaus.setMaksutapa(request.getParameter("maksutapa"));
		request.setAttribute("toimitus", tilaus.getToimitus());
		request.setAttribute("maksutapa", tilaus.getMaksutapa());

		tilaus.setLisatiedot(lisatiedot);


		tilaaja.setEtunimi(etunimi);
		tilaaja.setSukunimi(sukunimi);
		tilaaja.setTyyppi("vieras");
		tilaaja.setPuh(puh);
		tilaaja.setEmail(email);
		tilaaja.setOsoite(osoite);
		tilaaja.setPostinro(postinro);
		tilaaja.setPostitmp(postitmp);

		session.setAttribute("tilaus", tilaus);
		session.setAttribute("tilaaja", tilaaja);

		response.sendRedirect("YhteenvetoServlet");

	}

	public static HashMap<String, String> validateTilaus(
			HttpServletRequest request) {
		
		Tilaus tilaus = new Tilaus();
		Kayttaja tilaaja = new Kayttaja();
		
		// Radiobuttoneilla valitut tiedot
		tilaus.setToimitus(request.getParameter("toimitus"));
		tilaus.setMaksutapa(request.getParameter("maksutapa"));

		HashMap<String, String> errors = new HashMap<String, String>();

		// Validoidaan etunimi
		String etunimi = request.getParameter("etunimi");

		if (etunimi == null || etunimi.trim().length() == 0) {
			errors.put("etunimi", "Etunimi vaaditaan");
		} else {
			if (etunimi.trim().length() > 30) {
				errors.put("etunimi", "Max 30 merkkiä");
			} else {

				Pattern p = Pattern.compile("^[a-zA-ZÅÄÖåäö\\- ]+$");
				Matcher m = p.matcher(etunimi);

				if (m.find() == false) {
					errors.put("etunimi",
							"Etunimi sisältää kiellettyjä merkkejä");
				} else {
					String uusiEtunimi = etunimi.replace('ä', 'a');
					uusiEtunimi = etunimi.replace('å', 'a');
					uusiEtunimi = etunimi.replace('ö', 'o');
					tilaaja.setEtunimi(uusiEtunimi);
				}
			}
		}

		// Validoidaan sukunimi
		String sukunimi = request.getParameter("sukunimi");

		if (sukunimi == null || sukunimi.trim().length() == 0) {
			errors.put("sukunimi", "Sukunimi vaaditaan");
		} else {
			if (sukunimi.trim().length() > 30) {
				errors.put("sukunimi", "Max 30 merkkiä");
			} else {

				Pattern p = Pattern.compile("^[a-zA-ZÅÄÖåäö\\- ]+$");
				Matcher m = p.matcher(sukunimi);

				if (m.find() == false) {
					errors.put("sukunimi",
							"Sukunimi sisältää kiellettyjä merkkejä");
				} else {
					String uusiSukunimi = sukunimi.replace('ä', 'a');
					uusiSukunimi = sukunimi.replace('å', 'a');
					uusiSukunimi = sukunimi.replace('ö', 'o');
					tilaaja.setSukunimi(uusiSukunimi);
				}
			}
		}

		// Validoidaan puhelinnumero
		String puh = request.getParameter("puh");

		if (puh == null || puh.trim().length() == 0) {
			errors.put("puh", "Puhelinnumero vaaditaan");
		} else {

			Pattern p = Pattern.compile("^[0-9\\+ ]+$");
			Matcher m = p.matcher(puh);

			if (m.find() == false) {
				errors.put("puh", "Puhelinnumero sisältää kiellettyjä merkkejä");
			} else {
				if (puh.length() < 10 || puh.length() > 13) {
					errors.put("puh", "Puhelinnumero 10-13 merkillä");
				} else {
					tilaaja.setPuh(puh);
				}
			}
		}

		// Validoidaan email
		String email = request.getParameter("email");

		if (email == null || email.trim().length() == 0) {
			errors.put("email", "Sähköposti vaaditaan");
		} else {
			if (email
					.matches("\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b") == false) {
				errors.put("email", "Sähköposti on väärän muotoinen");
			} else {
				if (email.trim().length() > 60) {
					errors.put("email", "Sähköposti on liian pitkä");
				} else {
					tilaaja.setEmail(email);
				}
			}
		}

		// Validoidaan osoite
		String osoite = request.getParameter("osoite");

		if (osoite == null || osoite.trim().length() == 0) {
			errors.put("osoite", "Osoite vaaditaan");
		} else {
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
					tilaaja.setOsoite(uusiOsoite);
				}
			}
		}

		// Validoidaan postinumero
		String postinro = request.getParameter("postinro");

		if (postinro == null || postinro.trim().length() == 0) {
			errors.put("postinro", "Postinumero vaaditaan");
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
					tilaaja.setPostinro(postinro);
				}
			}
		}

		// Validoidaan postitoimipaikka
		String postitmp = request.getParameter("postitmp");

		if (postitmp == null || postitmp.trim().length() == 0) {
			errors.put("postitmp", "Postitoimipaikka vaaditaan");
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
					tilaaja.setPostitmp(uusiPostitmp);
				}
			}
		}

		// Validoidaan lisätiedot
		String lisatiedot = request.getParameter("lisatiedot");

		if (lisatiedot.trim().length() > 0) {
			if (lisatiedot.trim().length() > 300) {
				errors.put("lisatiedot", "Max 300 merkkiä");
			} else {

				Pattern p = Pattern.compile("^[0-9a-zA-ZÅÄÖåäö\\-\\.\\, ]+$");
				Matcher m = p.matcher(osoite);

				if (m.find() == false) {
					errors.put("lisatiedot",
							"Lisätiedoissa kiellettyjä merkkejä");
				} else {
					String uusiLisatiedot = lisatiedot.replace('ä', 'a');
					uusiLisatiedot = lisatiedot.replace('å', 'a');
					uusiLisatiedot = lisatiedot.replace('ö', 'o');
					uusiLisatiedot.trim();
					tilaus.setLisatiedot(uusiLisatiedot);
				}
			}
		}

		request.setAttribute("tilaus", tilaus);
		request.setAttribute("tilaaja", tilaaja);
		request.setAttribute("errors", errors);

		return errors;
	}
}
