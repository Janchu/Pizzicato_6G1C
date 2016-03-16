package pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Pizza;
import pizzicato.model.dao.PizzaDAO;
import pizzicato.model.Tayte;

/**
 * Servlet implementation class PoistaPizzaServlet
 */
@WebServlet("/PoistaPizzaServlet")
public class PoistaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Pyydet‰‰n poistettavan pizzan id muokkaa-pizzalista.jsp:lt‰ ja
		// muutetaan se oikeaan muotoon.
		String poistettavaPizzaIdStr = request.getParameter("PizId");
		int poistettavaPizzaId = new Integer(poistettavaPizzaIdStr);

		// Luodaan pizzaDAO johon haetaan pizzat kannasta.
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();

		// Laitetaan id ja pizzalista menem‰‰n jsp:lle
		request.setAttribute("poistettavaPizzaId", poistettavaPizzaId);
		request.setAttribute("pizzat", pizzat);

		// L‰hetet‰‰n jsp:lle
		String jsp = "/view/poista-pizza.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan pizzadao
		PizzaDAO pizzadao = new PizzaDAO();

		// Pyydet‰‰n jsp:lt‰ mukana kuljetettu poistettavan pizzan id ja
		// alustetaan muut nolliksi.
		String pidStr = request.getParameter("pid");
		int pid = new Integer(pidStr);
		String tyyppi = "";
		String nimi = "";
		double hinta = 0;
		int nakyvyys = 0;
		String pohja = "";
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>(); // kukkuu

		try {
			// Luodaan Pizza-olio
			Pizza poistettavapizza = new Pizza(pid, tyyppi, nimi, hinta,
					nakyvyys, pohja, taytelista);

			// Kutsutaan deletePizza-metodia
			pizzadao.deletePizza(poistettavapizza);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Palautetaan k‰ytt‰j‰ pizzalistan muokkaustilaan.
		response.sendRedirect("MuokkaaPizzalistaServlet");

	}

}
