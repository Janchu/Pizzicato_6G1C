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

		// Pyydetään poistettavan pizzan id muokkaa-pizzalista.jsp:ltä ja
		// muutetaan se oikeaan muotoon.
		String poistettavaPizzaIdStr = request.getParameter("PizId");
		int poistettavaPizzaId = new Integer(poistettavaPizzaIdStr);

		// Luodaan pizzaDAO johon haetaan pizzat kannasta.
		PizzaDAO pizzadao = new PizzaDAO();

		// Pyydetään jsp:ltä mukana kuljetettu poistettavan pizzan id ja
		// alustetaan muut nolliksi.
		String tyyppi = "";
		String nimi = "";
		double hinta = 0;
		int nakyvyys = 0;
		String pohja = "";
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();

		try {
			// Luodaan Pizza-olio
			Pizza poistettavapizza = new Pizza(poistettavaPizzaId, tyyppi,
					nimi, hinta, nakyvyys, pohja, taytelista);

			// Kutsutaan deletePizza-metodia
			pizzadao.deletePizza(poistettavapizza);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Palautetaan käyttäjä pizzalistan muokkaustilaan.
		response.sendRedirect("MuokkaaPizzalistaServlet");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
