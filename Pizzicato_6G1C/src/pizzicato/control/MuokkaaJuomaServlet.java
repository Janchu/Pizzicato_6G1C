package pizzicato.control;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Juoma;
import pizzicato.model.dao.JuomaDAO;

/**
 * Servlet implementation class MuokkaaJuomaServlet
 */
@WebServlet("/MuokkaaJuomaServlet")
public class MuokkaaJuomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan JuomaDAO olio
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();

		String muokattavaJuomaId = request.getParameter("JuoId");
		int JId = new Integer(muokattavaJuomaId);
		request.setAttribute("muokattavaJuomaId", JId);

		// ArrayList tallennetaan request olioon jsp:lle vietäväksi
		request.setAttribute("juomat", juomat);

		// Lähetetään jsp:lle
		String jsp = "/view/muokkaa-juoma.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan JuomaDAO, jota tarvitaan kun juomien
		// muokkaustoiminto lisätään
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();
		DecimalFormat formatter = new DecimalFormat("#0.00");

		// ArrayList tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("juomat", juomat);

		String idStr = request.getParameter("juomaId");
		int id = new Integer(idStr);

		RequestDispatcher jsp = getServletContext().getRequestDispatcher(
				"/view/muokkaa-juoma.jsp");

		HashMap<String, String> errors = validateMuokkaa(request, id);
		if (!errors.isEmpty()) {
			request.setAttribute("muokattavaJuomaId", id);
			jsp.forward(request, response);
		} else {

			// Haetaan käyttäjän syöttämät juoman nimi ja hinta. Muutetaan hinta
			// oikeaan muotoon.
			String nimi = request.getParameter("juomaNimi");
			String nimi_eng = request.getParameter("juomaNimi_eng");
			String kokoStr = request.getParameter("juomaKoko");
			String uusiKokoStr = kokoStr.replace(",", ".");
			double koko = new Double(uusiKokoStr);
			String hintaStr = request.getParameter("juomaHinta");
			String uusiHintaStr = hintaStr.replace(",", ".");
			double hinta = new Double(uusiHintaStr);

			formatter.format(hinta);

			// Nämä arvot ovat pelkästään futureproofia varten.
			String tyyppi = "juoma";
			int nakyvyys = 1;

			try {
				// Luodaan uusi juoma olio kantaan vietäväksi
				Juoma muokattuJuoma = new Juoma(id, tyyppi, nimi, hinta, koko,
						nakyvyys, nimi_eng);

				// Kutsutaan updateJuoma metodia
				juomadao.updateJuoma(muokattuJuoma);

				// Palautetaan käyttäjä juomalistan muokkaustilaan.
				response.sendRedirect("MuokkaaJuomalistaServlet");

			} catch (Exception e) {
				response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp");
			}
		}

	}

	public static HashMap<String, String> validateMuokkaa(
			HttpServletRequest request, int id) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Juoma uusiJuoma = new Juoma();
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();
		HashMap<String, String> errors = new HashMap<String, String>();

		// Haetaan syötetty nimi validointia varten
		String nimi = request.getParameter("juomaNimi");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (nimi == null || nimi.trim().length() == 0) {
			errors.put("nimi", "Nimi vaaditaan.");
		}else {
			// Tarkistetaan, ettei nimi ole jo käytössä
			for (int i = 0; i < juomat.size(); i++) {
				if (nimi.equals(juomat.get(i).getNimi())) {
					errors.put("nimi_eng", "Nimi on jo käytössä");
				}
			}
		}

		int maxLength = (nimi.length() < 20) ? nimi.length() : 20;
		String rajattuNimi = nimi.substring(0, maxLength);
		nimi = rajattuNimi;
		uusiJuoma.setNimi(nimi);

		// Haetaan syötetty nimi_eng validointia varten
		String nimi_eng = request.getParameter("juomaNimi_eng");

		// Tarkistetaan, ettei kenttä ole tyhjä
		if (nimi_eng == null || nimi_eng.trim().length() == 0) {
			errors.put("nimi_eng", "Nimi vaaditaan");
		} else {
			// Tarkistetaan, ettei nimi ole jo käytössä
			for (int i = 0; i < juomat.size(); i++) {
				if (nimi_eng.equals(juomat.get(i).getNimi_eng())) {
					errors.put("nimi_eng", "Nimi on jo käytössä");
				}
			}
		}

		int maxLengthEng = (nimi_eng.length() < 30) ? nimi_eng.length() : 30;
		String rajattuNimi_eng = nimi_eng.substring(0, maxLengthEng);
		nimi_eng = rajattuNimi_eng;
		uusiJuoma.setNimi_eng(nimi_eng);

		// Haetaan syötetty hinta validointia varten
		String hintaStr = request.getParameter("juomaHinta");

		if (hintaStr == null || hintaStr.trim().length() == 0) {
			errors.put("hinta", "Hinta vaaditaan.");
		} else {
			if (hintaStr.matches("[0-9]+([,.][0-9]{1,2})?") == false) {
				errors.put("hinta", "Hinta sisältää kiellettyjä merkkejä.");
			} else {
				String uusiHintaStr = hintaStr.replace(',', '.');

				double hinta = 0;
				hinta = new Double(uusiHintaStr);
				formatter.format(hinta);

				if (hinta < 2.00 || hinta > 20.00) {
					errors.put("hinta",
							"Hinta sallittujen rajojen ulkopuolella.");
				} else {
					uusiJuoma.setHinta(hinta);
					request.setAttribute("uusiJuoma", uusiJuoma);
				}
			}
		}

		// Haetaan syötetty koko validointia varten
		String kokoStr = request.getParameter("juomaKoko");

		if (kokoStr == null || kokoStr.trim().length() == 0) {
			errors.put("koko", "Koko vaaditaan.");
		} else {
			if (kokoStr.matches("[0-9]+([,.][0-9]{1,2})?") == false) {
				errors.put("koko", "Koko sisältää kiellettyjä merkkejä.");
			} else {
				String uusiKokoStr = kokoStr.replace(',', '.');

				double koko = 0;
				koko = new Double(uusiKokoStr);
				formatter.format(koko);

				if (koko < 0.33 || koko > 1.5) {
					errors.put("koko", "Koko sallittujen rajojen ulkopuolella");
				} else {
					uusiJuoma.setKoko(koko);
					request.setAttribute("uusiJuoma", uusiJuoma);
				}
			}
		}

		request.setAttribute("errors", errors);

		return errors;
	}
}
