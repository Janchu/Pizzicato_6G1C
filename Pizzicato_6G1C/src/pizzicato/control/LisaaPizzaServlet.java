package pizzicato.control;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
		// Luodaan PizzaDAO ja TayteDAO, ja molemmille ArrayListit
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();

		// ArrayList tallennetaan request-olioon jsp:lle viet�v�ksi
		request.setAttribute("pizzat", pizzat);
		request.setAttribute("taytteet", taytteet);

		// L�hetet��n jsp:lle
		String jsp = "/view/lisaa-pizza.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan PizzaDAO ja TayteDAO (ja decimalformat)
		PizzaDAO pizzadao = new PizzaDAO();
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		DecimalFormat formatter = new DecimalFormat("#0.00");
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();

		// Haetaan k�ytt�j�n sy�tt�m�t pizzan nimi ja hinta. Muutetaan hinta
		// oikeaan muotoon.
		String nimi = request.getParameter("pizzaNimi");
		String hintaStr = request.getParameter("pizzaHinta");
		double hinta = new Double(hintaStr);
		formatter.format(hinta);

		// Alustetaan ID nollaksi, koska ID generoituu kannassa automaattisesti
		int id = 0;

		// N�m� arvot ovat pelk�st��n futureproofia varten.
		String tyyppi = "pizza";
		int nakyvyys = 1;
		String pohja = "normaali";

		// Laitetaan k�ytt�j�n valitsemat t�ytteet listaan
		String[] tayte = request.getParameterValues("tayte");
		for (int i = 0; i < tayte.length; i++) {

			int tayteId = new Integer(tayte[i]);
			String tayteNimi = taytteet.get(i).getNimi();
			System.out.println(tayteNimi);
			Double tayteHinta = 0.00;

			// Luodaan uusi Tayte-olio taytelistaan lis�tt�v�ksi
			Tayte uusiTayte = new Tayte(tayteId, tayteNimi, tayteHinta);
			taytelista.add(uusiTayte);
		}

		try {
			// Luodaan uusi pizza olio kantaan viet�v�ksi
			Pizza uusiPizza = new Pizza(id, tyyppi, nimi, hinta, nakyvyys,
					pohja, taytelista);

			pizzadao.addPizza(uusiPizza);
			response.sendRedirect("MuokkaaPizzalistaServlet");

		} catch (Exception e) {
			response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp"); //
		}
	}

}
