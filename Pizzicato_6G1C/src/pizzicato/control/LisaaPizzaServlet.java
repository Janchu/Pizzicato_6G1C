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

/**
 * Servlet implementation class LisaaPizzaServlet
 */
@WebServlet("/LisaaPizzaServlet")
public class LisaaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Luodaan PizzaDAO
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();

		// ArrayList tallennetaan request-olioon jsp:lle viet‰v‰ksi
		request.setAttribute("pizzat", pizzat);

		// L‰hetet‰‰n jsp:lle
		String jsp = "/view/lisaa-pizza.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
