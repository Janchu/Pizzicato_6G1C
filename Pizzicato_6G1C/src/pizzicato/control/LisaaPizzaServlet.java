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
		// Luodaan PizzaDAO ja TayteDAO
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
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		DecimalFormat formatter = new DecimalFormat("#0.00");
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();
		
		// Haetaan k�ytt�j�n sy�tt�m�t pizzan nimi ja hinta. Muutetaan hinta oikeaan muotoon.
		String nimi = request.getParameter("pizzaNimi");
		String hintaStr = request.getParameter("pizzaHinta");		
		double hinta = new Double(hintaStr);
		formatter.format(hinta);
		
		// Selvitet��n seuraava vapaa id pizzalle
		pizzat = pizzadao.findAll();		
		int nykyinenSuurinId = 0;		
		for (int i = 0; i < pizzat.size(); i++) {
			System.out.println(pizzat.get(i).getId());
			nykyinenSuurinId = pizzat.get(i).getId();
		}		
		int id = nykyinenSuurinId + 1;
		System.out.println("Uusi id: " + id);
		
		
		// N�m� arvot ovat pelk�st��n futureproofia varten.
		String tyyppi = "pizza";
		int nakyvyys = 1;
		String pohja = "normaali";
		
		for (int i = 1; i < taytteet.size()+1; i++) {
			String tayte = request.getParameter("tayte"+i);
				if (tayte != null) {
					int tayteId = new Integer(tayte);
					tayteId = tayteId - 1;
					String tayteNimi = taytteet.get(tayteId).getNimi();
					System.out.println(tayteNimi);
					Double tayteHinta = 0.00;
					
					Tayte uusiTayte = new Tayte(tayteId, tayteNimi, tayteHinta);
					taytelista.add(uusiTayte);
				}
			System.out.println(tayte);

		}
		
		try {
			// Luodaan uusi pizza olio kantaan viet�v�ksi
		Pizza uusiPizza = new Pizza(id, tyyppi, nimi, hinta, nakyvyys, pohja, taytelista);
		
		pizzadao.addPizza(uusiPizza);

		} catch (Exception e) {
			response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp"); // 
		}
	}

}
