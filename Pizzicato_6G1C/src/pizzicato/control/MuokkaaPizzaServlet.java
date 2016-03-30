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
 * Servlet implementation class MuokkaaPizzaServlet
 */
@WebServlet("/MuokkaaPizzaServlet")
public class MuokkaaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan PizzaDAO- ja TayteDAO-oliot
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();

		String muokattavaPizzaId = request.getParameter("PizId");
		int PId = new Integer(muokattavaPizzaId);
		request.setAttribute("muokattavaPizzaId", PId);

		// ArrayList tallennetaan request-olioon jsp:lle viet‰v‰ksi
		request.setAttribute("pizzat", pizzat);
		request.setAttribute("taytteet", taytteet);

		// L‰hetet‰‰n jsp:lle
		String jsp = "/view/muokkaa-pizza.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan PizzaDAO ja TayteDAO, joita tarvitaan kun t‰ytteiden
		// muokkaustoiminto lis‰t‰‰n.
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		DecimalFormat formatter = new DecimalFormat("#0.00");
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();

		// Haetaan k‰ytt‰j‰n syˆtt‰m‰t pizzan nimi ja hinta. Muutetaan hinta
		// oikeaan muotoon.
		String nimi = request.getParameter("pizzaNimi");
		String hintaStr = request.getParameter("pizzaHinta");
		String uusiHintaStr = hintaStr.replace(',', '.');
		double hinta = new Double(uusiHintaStr);
		formatter.format(hinta);
		String idStr = request.getParameter("pizzaId");
		int id = new Integer(idStr);

		// N‰m‰ arvot ovat pelk‰st‰‰n futureproofia varten.
		String tyyppi = "pizza";
		int nakyvyys = 1;
		String pohja = "normaali";

		// T‰ytteiden tiedot alustetaan nolliksi kunnes t‰ytteiden
		// muokkaustoiminto toimii.
		int tayteId = 0;
		String tayteNimi = "";
		String tayteNimi_eng = "";
		Double tayteHinta = 0.00;
		Double tayteKilohinta = 0.00;

		// Luodaan Tayte-olio, muokattavan pizzan mukana viet‰v‰ksi.
		Tayte uusiTayte = new Tayte(tayteId, tayteNimi, tayteNimi_eng, tayteHinta, tayteKilohinta);
		taytelista.add(uusiTayte);

		try {
			// Luodaan uusi pizza olio kantaan viet‰v‰ksi
			Pizza muokattuPizza = new Pizza(id, tyyppi, nimi, hinta, nakyvyys,
					pohja, taytelista);

			// Kutsutaan updatePizza metodia
			pizzadao.updatePizza(muokattuPizza);

			// Palautetaan k‰ytt‰j‰ pizzalistan muokkaustilaan.
			response.sendRedirect("MuokkaaPizzalistaServlet");

		} catch (Exception e) {
			response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp"); //
		}

	}

}
