package pizzicato.control;

import java.io.IOException;
import java.text.DecimalFormat;
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

		RequestDispatcher jsp = getServletContext().getRequestDispatcher(
				"/view/muokkaa-tayte.jsp");
		// Validointia
		HashMap<String, String> errors = validateMuokkaa(request);
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
			HttpServletRequest request) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Tayte muokattavaTayte = new Tayte();
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		HashMap<String, String> errors = new HashMap<String, String>();
		
		String muokattavanIdStr = request.getParameter("tayteId");
		int muokattavanId = new Integer(muokattavanIdStr);
		muokattavaTayte.setId(muokattavanId);

		// Haetaan syötetty nimi validointia varten
		String nimi = request.getParameter("tayteNimi");

		if (nimi == null || nimi.trim().length() == 0) {
			errors.put("nimi", "Nimi vaaditaan");
		} else {
			for (int i = 0; i < taytteet.size(); i++) {
				if (nimi.equalsIgnoreCase(taytteet.get(i).getNimi()) && taytteet.get(i).getId() != muokattavanId) {
					errors.put("nimi", "Nimi on jo käytössä");
				} else {
					if (nimi.trim().length() > 20) {
						errors.put("nimi", "Max 20 merkkiä");
					} else {
						Pattern p = Pattern.compile("^[a-zA-ZÅÄÖåäö\\- ]+$");
						Matcher m = p.matcher(nimi);
						if (m.find() == false) {
							errors.put("nimi",
									"Nimi sisältää kiellettyjä merkkejä");
						} else {
							String uusiNimi = nimi.replace('ä', 'a');
							uusiNimi = nimi.replace('å', 'a');
							uusiNimi = nimi.replace('ö', 'o');
							muokattavaTayte.setNimi(uusiNimi.trim());
						}
					}
				}
			}
		}

		// Haetaan syötetty englanninkielinen nimi validointia varten
		String nimiEng = request.getParameter("tayteNimi_eng");

		if (nimiEng == null || nimiEng.trim().length() == 0) {
			errors.put("nimi_eng", "Englanninkielinen nimi vaaditaan");
		} else {
			for (int i = 0; i < taytteet.size(); i++) {
				if (nimi.equalsIgnoreCase(taytteet.get(i).getNimi()) && taytteet.get(i).getId() != muokattavanId) {
					errors.put("nimi_eng",
							"Englanninkielinen nimi on jo käytössä");
				} else {
					if (nimi.trim().length() > 20) {
						errors.put("nimi_eng", "Max 20 merkkiä");
					} else {
						Pattern p = Pattern.compile("^[a-zA-ZÅÄÖåäö\\- ]+$");
						Matcher m = p.matcher(nimi);
						if (m.find() == false) {
							errors.put("nimi_eng",
									"Englanninkielinen nimi sisältää kiellettyjä merkkejä");
						} else {
							String uusiNimi = nimiEng.replace('ä', 'a');
							uusiNimi = nimi.replace('å', 'a');
							uusiNimi = nimi.replace('ö', 'o');
							muokattavaTayte.setNimi(uusiNimi.trim());
						}
					}
				}
			}
		}

		// Haetaan syötetty hinta validointia varten
		String hintaStr = request.getParameter("tayteHinta");

		if (hintaStr == null || hintaStr.trim().length() == 0) {
			errors.put("hinta", "Hinta vaaditaan.");
		} else {
			Pattern p = Pattern.compile("[0-9]+([,.][0-9]{1,2})?");
			Matcher m = p.matcher(hintaStr);
			if (m.find() == false) {
				errors.put("hinta", "Hinta on väärää muotoa");
			} else {
				String uusiHintaStr = hintaStr.replace(',', '.');

				double hinta = new Double(uusiHintaStr);
				formatter.format(hinta);

				if (hinta < 0.5 || hinta > 10.00) {
					errors.put("hinta",
							"Hinta sallittujen rajojen ulkopuolella");
				} else {
					muokattavaTayte.setHinta(hinta);
				}
			}
		}

		// Haetaan syötetty hinta validointia varten
		String kilohintaStr = request.getParameter("tayteKilohinta");

		if (kilohintaStr == null || kilohintaStr.trim().length() == 0) {
			errors.put("kilohinta", "Kilointa vaaditaan.");
		} else {
			Pattern p = Pattern.compile("[0-9]+([,.][0-9]{1,2})?");
			Matcher m = p.matcher(kilohintaStr);
			if (m.find() == false) {
				errors.put("kilohinta", "Kilohinta on väärää muotoa");
			} else {
				String uusiHintaStr = kilohintaStr.replace(',', '.');

				double kilohinta = new Double(uusiHintaStr);
				formatter.format(kilohinta);

				if (kilohinta < 0.5 || kilohinta > 99.99) {
					errors.put("kilohinta",
							"Kilohinta sallittujen rajojen ulkopuolella");
				} else {
					muokattavaTayte.setHinta(kilohinta);
				}
			}
		}
		request.setAttribute("errors", errors);
		request.setAttribute("muokattavaTayte", muokattavaTayte);

		return errors;
	}

}