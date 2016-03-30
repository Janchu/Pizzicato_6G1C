package pizzicato.control;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.logging.Logger;
import com.sun.javafx.collections.MappingChange.Map;

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

	private RequestDispatcher jsp;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Luodaan PizzaDAO ja TayteDAO, ja molemmille ArrayListit
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();

		// ArrayList tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("pizzat", pizzat);
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
		request.setAttribute("pizzat", pizzat);
		request.setAttribute("taytteet", taytteet);
		
		jsp = getServletContext().getRequestDispatcher("/view/lisaa-pizza.jsp");

		HashMap<String, String> errors = validate(request);
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
				System.out.println(tayteNimi);
				Double tayteHinta = 0.00;

				// Luodaan uusi Tayte-olio taytelistaan lisättäväksi
				Tayte uusiTayte = new Tayte(tayteId, tayteNimi, tayteHinta);
				taytelista.add(uusiTayte);
			}
			
			

			try {
				// Luodaan uusi pizza olio kantaan vietäväksi
				Pizza leikkiPizza = (Pizza) request.getAttribute("uusiPizza");
				String nimi = leikkiPizza.getNimi();
				double hinta = leikkiPizza.getHinta();
				Pizza uusiPizza = new Pizza(id, tyyppi, nimi, hinta, nakyvyys, pohja, taytelista);
				pizzadao.addPizza(uusiPizza);
				response.sendRedirect("MuokkaaPizzalistaServlet");

			} catch (Exception e) {
				response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp");
			}
		}
	}

	public static HashMap<String, String> validate(HttpServletRequest request) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Pizza uusiPizza = new Pizza();
		HashMap<String, String> errors = new HashMap<String, String>();

		// Haetaan syötetty nimi validointia varten
		String nimi = request.getParameter("pizzaNimi");
		if (nimi == null || nimi.trim().length() == 0) {
			errors.put("nimi", "Nimi vaaditaan.");
		}
		int maxLength = (nimi.length() < 20)?nimi.length():20;
		String rajattuNimi = nimi.substring(0, maxLength);
		nimi = rajattuNimi;
		uusiPizza.setNimi(nimi);

		// Haetaan syötetty hinta validointia varten
		String hintaStr = request.getParameter("pizzaHinta");
		if (hintaStr == null) {
			errors.put("hintaStr", "Hinta vaaditaan.");
		}
		String uusiHintaStr = hintaStr.replace(',', '.');
		double hinta = 0;
		hinta = new Double(uusiHintaStr);
		formatter.format(hinta);

		if (hinta < 6 || hinta > 99.99) {
			errors.put("hinta", "Hinta sallittujen rajojen ulkopuolella.");
		}
		uusiPizza.setHinta(hinta);
		
		request.setAttribute("errors", errors);
		request.setAttribute("uusiPizza", uusiPizza);

		return errors;
	}
}
