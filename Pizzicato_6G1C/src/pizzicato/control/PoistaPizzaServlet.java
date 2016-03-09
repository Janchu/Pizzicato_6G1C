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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poistettavapizzaid = request.getParameter("PizId");
		
		int PId = new Integer (poistettavapizzaid);
		
		request.setAttribute("poistettavapizzaid", PId);
		
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		
		request.setAttribute("pizzat", pizzat);
		
		String jsp ="/view/poista-pizza.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PizzaDAO pizzadao = new PizzaDAO();
		
		String pidStr = request.getParameter("pid");
		int pid = new Integer(pidStr);
		String tyyppi = "";
		String nimi = "";
		double hinta = 0;
		int nakyvyys = 0;
		String pohja = "";
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>(); //kukkuu
		
		try {
			Pizza poistettavapizza = new Pizza(pid, tyyppi, nimi, hinta, nakyvyys, pohja,taytelista);
			
			pizzadao.deletePizza(poistettavapizza);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect("MuokkaaPizzalistaServlet");
		
		
	}

}
