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
import javax.servlet.http.HttpSession;

import pizzicato.model.Pizza;
import pizzicato.model.Tilaus;
import pizzicato.model.Tilausrivi;
import pizzicato.model.dao.PizzaDAO;

/**
 * Servlet implementation class OstoskoriServlet
 */
@WebServlet("/OstoskoriServlet")
public class OstoskoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Tilaus ostoskori = (Tilaus) session.getAttribute("ostoskori");
		
		
		if (ostoskori == null) {
			ostoskori = new Tilaus();
		}
			
		ArrayList<Tilausrivi> tilausrivit = ostoskori.getTilausrivit();
		
			request.setAttribute("ostoskori", tilausrivit);
		
		
		String jsp = "/view/ostoskori.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Tilaus ostoskori;
		
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Pizza tilattuPizza = new Pizza();
		
		HttpSession session = request.getSession();
		ostoskori = (Tilaus) session.getAttribute("ostoskori");
		
		
		if (ostoskori == null) {
			ostoskori = new Tilaus();
			session.setAttribute("ostoskori", ostoskori);
		}
		
		String idStr = request.getParameter("koriin");
		int id = new Integer(idStr);
		
		System.out.println(idStr);
		
		for (int i = 0; i < pizzat.size(); i++) {
			if (pizzat.get(i).getId() == id) {
				tilattuPizza.setId(id);
				tilattuPizza.setHinta(pizzat.get(i).getHinta());
				tilattuPizza.setNimi(pizzat.get(i).getNimi());
				Tilausrivi uusiTilausrivi = new Tilausrivi();
				uusiTilausrivi.setTilattuTuote(tilattuPizza);
				ostoskori.addTilausrivi(uusiTilausrivi);
				
			}
		}
		
		System.out.println(ostoskori);
		
		request.setAttribute("ostoskori", ostoskori);
		
		response.sendRedirect("ListaaPizzatServlet");
		
		
		
		
	}

}
