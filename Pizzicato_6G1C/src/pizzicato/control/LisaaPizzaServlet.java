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

import pizzicato.model.Pizza;
import pizzicato.model.Tayte;
import pizzicato.model.dao.PizzaDAO;
import pizzicato.model.dao.TayteDAO;

/**
 * Servlet implementation class LisaaPizzaServlet
 */
@WebServlet("/LisaaPizzaServlet")
public class LisaaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Luodaan täytedao ja kaivetaan kannasta kaikki täytteet, joista
		// käyttäjä voi valita
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();

		// Luodaan tyhjä pizza-olio ettei request suutu
		Pizza uusiPizza = new Pizza();

		// Pizza-olio ja täytteet tallennetaan requestiin jsp:lle vietäväksi
		request.setAttribute("uusiPizza", uusiPizza);
		request.setAttribute("taytteet", taytteet);

		// Lähetetään jsp:lle
		String jsp = "/view/lisaa-pizza.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan PizzaDAO ja TayteDAO (ja decimalformat)
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();

		// ArrayList tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("taytteet", taytteet);

		RequestDispatcher jsp = getServletContext().getRequestDispatcher(
				"/view/lisaa-pizza.jsp");

		HashMap<String, String> errors = validateLisaa(request);
		if (!errors.isEmpty()) {
			jsp.forward(request, response);
		} else {

			// Alustetaan ID nollaksi, koska ID generoituu kannassa
			// automaattisesti
			int id = 0;

			// Nämä arvot ovat pelkästään futureproofia varten.
			String tyyppi = "pizza";
			int nakyvyys = 1;
			String pohja = "normaali";

			// Laitetaan käyttäjän valitsemat täytteet listaan
			String[] tayte = request.getParameterValues("tayte");
			for (int i = 0; i < tayte.length; i++) {

				int tayteId = new Integer(tayte[i]);
				String tayteNimi = taytteet.get(i).getNimi();
				String tayteNimi_eng = taytteet.get(i).getNimi_eng();
				Double tayteHinta = taytteet.get(i).getHinta();
				Double tayteKilohinta = taytteet.get(i).getKilohinta();

				// Luodaan uusi Tayte-olio taytelistaan lisättäväksi
				Tayte uusiTayte = new Tayte(tayteId, tayteNimi, tayteNimi_eng,
						tayteHinta, tayteKilohinta);
				taytelista.add(uusiTayte);
			}

			try {
				// Luodaan uusi pizza olio kantaan vietäväksi
				Pizza leikkiPizza = (Pizza) request.getAttribute("uusiPizza");
				String nimi = leikkiPizza.getNimi();
				double hinta = leikkiPizza.getHinta();
				Pizza uusiPizza = new Pizza(id, tyyppi, nimi, hinta, nakyvyys,
						pohja, taytelista);

				// Kutsutaan addPizza-metodia
				pizzadao.addPizza(uusiPizza);

				// Uudelleenohjataan MuokkaaPizzalistaServletille
				response.sendRedirect("MuokkaaPizzalistaServlet");

			} catch (Exception e) {
				response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp");
			}
		}
	}

	public static HashMap<String, String> validateLisaa(
			HttpServletRequest request) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Pizza uusiPizza = new Pizza();
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		HashMap<String, String> errors = new HashMap<String, String>();
		
		

		// Haetaan syötetty nimi validointia varten
		String nimi = request.getParameter("pizzaNimi");
		
		if (nimi == null || nimi.trim().length() == 0) {
			errors.put("nimi", "Nimi vaaditaan");
		} else {
			for (int i = 0; i < pizzat.size(); i++) {
				if (nimi.equalsIgnoreCase(pizzat.get(i).getNimi())) {
					errors.put("nimi", "Nimi on jo käytössä");
				} else {
					if (nimi.trim().length() > 20) {
						errors.put("nimi", "Max 20 merkkiä");
					} else {
						Pattern p = Pattern.compile("^[a-zA-ZÅÄÖåäö\\- ]+$");
						Matcher m = p.matcher(nimi);						
						if (m.find() == false) {
							errors.put("nimi", "Nimi sisältää kiellettyjä merkkejä");
						} else {
							String uusiNimi = nimi.replace('ä', 'a');
							uusiNimi = nimi.replace('å', 'a');
							uusiNimi = nimi.replace('ö', 'o');
							uusiPizza.setNimi(uusiNimi);
						}
					}
				}
			}
		}
		

		// Haetaan syötetty hinta validointia varten
		String hintaStr = request.getParameter("pizzaHinta");
				
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

				if (hinta < 6 || hinta > 99.99) {
					errors.put("hinta",
							"Hinta sallittujen rajojen ulkopuolella");
				} else {
					uusiPizza.setHinta(hinta);
				}
			}
		}
		
		// Haetaan täytteet validointia varten
		String[] tayte = request.getParameterValues("tayte");
		
		if (tayte == null) {
			errors.put("taytteet", "Valitse vähintään yksi täyte");
		} else {
			for (int i = 0; i < tayte.length; i++) {
				int tayteId = new Integer(tayte[i]);
				Tayte uusiTayte = new Tayte();
				uusiTayte.setId(tayteId);
				uusiPizza.getTaytelista().add(uusiTayte);
			}
		}

		request.setAttribute("errors", errors);
		request.setAttribute("uusiPizza", uusiPizza);

		return errors;
	}
}
