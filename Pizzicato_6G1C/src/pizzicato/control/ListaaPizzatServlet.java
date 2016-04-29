package pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzicato.model.Kayttaja;
import pizzicato.model.Mauste;
import pizzicato.model.Pizza;
import pizzicato.model.Tilaus;
import pizzicato.model.Tilausrivi;
import pizzicato.model.dao.MausteDAO;
import pizzicato.model.dao.PizzaDAO;

@WebServlet("/ListaaPizzatServlet")
public class ListaaPizzatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan PizzaDAO
		PizzaDAO pizzadao = new PizzaDAO();
		MausteDAO maustedao = new MausteDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		ArrayList<Mauste> mausteet = maustedao.findAll();

		HttpSession session = request.getSession();
		Tilaus ostoskori = (Tilaus) session.getAttribute("ostoskori");

		if (ostoskori == null) {
			ostoskori = new Tilaus();
		}

		ArrayList<Tilausrivi> tilausrivit = ostoskori.getTilausrivit();

		
		Kayttaja kayttaja = (Kayttaja) session.getAttribute("kayttaja");

	
		request.setAttribute("kayttaja", kayttaja);
		

		// ArrayList tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("pizzat", pizzat);
		request.setAttribute("mausteet", mausteet);
		request.setAttribute("ostoskori", tilausrivit);

		// Lähetetään jsp:lle
		String jsp = "/view/listaa-pizzat.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
