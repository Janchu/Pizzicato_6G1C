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

import pizzicato.model.Tayte;
import pizzicato.model.dao.TayteDAO;

/**
 * Servlet implementation class MuokkaaTayteServlet
 */
@WebServlet("/MuokkaaTayteServlet")
public class MuokkaaTayteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan TayteDAO-oliot
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();

		String muokattavaTayteId = request.getParameter("TId");
		int TId = new Integer(muokattavaTayteId);
		request.setAttribute("muokattavaTayteId", TId);

		// ArrayList tallennetaan request-olioon jsp:lle viet�v�ksi
		request.setAttribute("taytteet", taytteet);

		// Lähetetään jsp:lle
		String jsp = "/view/muokkaa-tayte.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan TayteDAO, jota tarvitaan kun t�ytteiden
		// muokkaustoiminto lis�t��n.
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		DecimalFormat formatter = new DecimalFormat("#0.00");
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();
		
		request.setAttribute("taytteet", taytteet);
		String idStr = request.getParameter("tayteId");
		int id = new Integer(idStr);
		
		RequestDispatcher jsp = getServletContext().getRequestDispatcher("/view/muokkaa-tayte.jsp");
		// Validointia
		HashMap<String, String> errors = validateMuokkaa(request, id);
		if (!errors.isEmpty()) {
			request.setAttribute("muokattavaTayteId", id);
			jsp.forward(request, response);
		} else {

			// Haetaan käyttäjän syöttämät täytteen nimi, nimi_eng, hinta ja
			// kilohinta.
			// Muutetaan hinta ja kilohinta oikeaan muotoon.
			String nimi = request.getParameter("tayteNimi");
			String nimi_eng = request.getParameter("tayteNimi_eng");
			String hintaStr = request.getParameter("tayteHinta");
			String uusiHintaStr = hintaStr.replace(",", ".");
			double hinta = new Double(uusiHintaStr);
			formatter.format(hinta);
			String kilohintaStr = request.getParameter("tayteKilohinta");
			String uusiKilohintaStr = kilohintaStr.replace(",", ".");
			double kilohinta = new Double(uusiKilohintaStr);
			formatter.format(kilohinta);

			// Luodaan Tayte-olio.
			Tayte muokattavaTayte = new Tayte(id, nimi, nimi_eng, hinta,
					kilohinta);
			taytelista.add(muokattavaTayte);

			try {
				// Luodaan uusi tayte-olio kantaan vietäväksi
				Tayte muokattuTayte = new Tayte(id, nimi, nimi_eng, hinta,
						kilohinta);

				// Kutsutaan updateTayte metodia
				taytedao.updateTayte(muokattuTayte);

				// Palauteen käyttäjä taytelistan muokkaustilaan.
				response.sendRedirect("MuokkaaTaytelistaServlet");

			} catch (Exception e) {
				response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp");

			}
		}

	}

	public static HashMap<String, String> validateMuokkaa(
			HttpServletRequest request, int id) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Tayte uusiTayte = new Tayte();
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		HashMap<String, String> errors = new HashMap<String, String>();

		// Haetaan syötetty nimi validointia varten
		String nimi = request.getParameter("tayteNimi");
		if (nimi == null || nimi.trim().length() == 0) {
			errors.put("nimi", "Nimi vaaditaan.");
		} else {
			for (int i = 0; i < taytteet.size(); i++) {
				if (nimi.equalsIgnoreCase(taytteet.get(i).getNimi())) {
					if (id != taytteet.get(i).getId()) {
						errors.put("nimi", "Nimi on jo käytössä");
					}
				}

			}
		}
		int maxLength = (nimi.length() < 20) ? nimi.length() : 20;
		String rajattuNimi = nimi.substring(0, maxLength);
		nimi = rajattuNimi;
		uusiTayte.setNimi(nimi);

		// Haetaan syötetty nimi_eng validointia varten
		String nimi_eng = request.getParameter("tayteNimi_eng");
		if (nimi_eng == null || nimi.trim().length() == 0) {
			errors.put("nimi_eng", "Nimi vaaditaan.");
		} else {
			for (int i = 0; i < taytteet.size(); i++) {
				if (nimi_eng.equalsIgnoreCase(taytteet.get(i).getNimi_eng())) {
					if (id != taytteet.get(i).getId()) {
						errors.put("nimi_eng", "Nimi on jo käytössä");
					}
				}

			}
		}
		int maxLengthEng = (nimi_eng.length() < 20) ? nimi_eng.length() : 20;
		String rajattuNimi_eng = nimi_eng.substring(0, maxLengthEng);
		nimi_eng = rajattuNimi_eng;
		uusiTayte.setNimi_eng(nimi_eng);

		// Haetaan syötetty hinta validointia varten
		String hintaStr = request.getParameter("tayteHinta");
		if (hintaStr == null || hintaStr.trim().length() == 0) {
			errors.put("hinta", "Hinta vaaditaan.");
		} else {
			String uusiHintaStr = hintaStr.replace(',', '.');
			double hinta = 0;
			hinta = new Double(uusiHintaStr);
			formatter.format(hinta);

			if (hinta < 0.50 || hinta > 10.00) {
				errors.put("hinta", "Hinta sallittujen rajojen ulkopuolella.");
			} else {
				uusiTayte.setHinta(hinta);
				request.setAttribute("uusiTayte", uusiTayte);
			}
		}
		// Haetaan syötetty kilohinta validointia varten
		String kilohintaStr = request.getParameter("tayteKilohinta");
		if (kilohintaStr == null || kilohintaStr.trim().length() == 0) {
			errors.put("kilohinta", "Kilohinta vaaditaan.");
		} else {
			String uusiKiloHintaStr = kilohintaStr.replace(',', '.');
			double kilohinta = 0;
			kilohinta = new Double(uusiKiloHintaStr);
			formatter.format(kilohinta);

			if (kilohinta < 0.50 || kilohinta > 99.99) {
				errors.put("kilohinta",
						"Kilohinta sallittujen rajojen ulkopuolella.");
			} else {
				uusiTayte.setKilohinta(kilohinta);
				request.setAttribute("uusiTayte", uusiTayte);
			}

		}
		request.setAttribute("errors", errors);

		return errors;
	}

}
