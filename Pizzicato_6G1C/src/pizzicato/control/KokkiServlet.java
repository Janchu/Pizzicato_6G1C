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
import pizzicato.model.Tilaus;
import pizzicato.model.dao.PizzaDAO;
import pizzicato.model.dao.TilausDAO;

/**
 * Servlet implementation class KokkiServlet
 */
@WebServlet("/KokkiServlet")
public class KokkiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// PizzaDAO ja TilausDAO
		PizzaDAO pizzadao = new PizzaDAO();
		TilausDAO tilausdao = new TilausDAO();
		
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		ArrayList<Tilaus> tilaukset = tilausdao.findAll2();
		request.setAttribute("pizzat", pizzat);
		request.setAttribute("tilaukset", tilaukset);
		
		// Lähetetään jsp:lle
				String jsp = "/view/kokki.jsp";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(jsp);
				dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
