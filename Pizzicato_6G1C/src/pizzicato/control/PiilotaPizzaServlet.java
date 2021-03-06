package pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Pizza;
import pizzicato.model.Tayte;
import pizzicato.model.dao.PizzaDAO;

/**
 * Servlet implementation class PiilotaPizzaServlet
 */
@WebServlet("/PiilotaPizzaServlet")
public class PiilotaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PizzaDAO pizzadao = new PizzaDAO();

		// Pyydetään muokkaa-pizzalista.jsp:ltä pizzan id ja nykyinen näkyvyys
		// ja muutetaan ne oikeaan muotoon.
		String idStr = request.getParameter("Id");
		int id = new Integer(idStr);
		String nakyvyysStr = request.getParameter("Nakyvyys");
		int nakyvyys = new Integer(nakyvyysStr);

		// Alustetaan muut pizza-oliolle vietävät arvot nolliksi ja tyhjiksi,
		// koska vain näkyvyyttä halutaan muuttaa.
		String tyyppi = "";
		String nimi = "";
		double hinta = 0;
		String pohja = "";
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();

		try {
			// Luodaan pizza-olio, jolle annetaan uusi näkyvyys ja tyhjät arvot.
			Pizza pizza = new Pizza(id, tyyppi, nimi, hinta, nakyvyys, pohja,
					taytelista);
			pizzadao.hidePizza(pizza);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect("MuokkaaPizzalistaServlet");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
