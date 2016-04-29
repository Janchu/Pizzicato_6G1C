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
import pizzicato.model.Tilaus;
import pizzicato.model.Tilausrivi;
import pizzicato.model.dao.TilausDAO;

/**
 * Servlet implementation class YhteenvetoServlet
 */
@WebServlet("/YhteenvetoServlet")
public class YhteenvetoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");
		Kayttaja tilaaja = (Kayttaja) session.getAttribute("tilaaja");

		request.setAttribute("tilaus", tilaus);
		request.setAttribute("tilaaja", tilaaja);

		// Lähetetään jsp:lle
		String jsp = "/view/yhteenveto.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");
		//Kayttaja tilaaja = (Kayttaja) session.getAttribute("tilaaja");
		
		//TilausDAO tilausdao = new TilausDAO();
		
		//try {
		//tilausdao.addTilaus(tilaus, tilaaja);
		//} catch (Exception e) {
		//	response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp");
		//}
		
		ArrayList<Tilausrivi> uusiTilausrivit = new ArrayList<Tilausrivi>();
		
		tilaus.setTilausrivit(uusiTilausrivit);
				
		response.sendRedirect("ListaaPizzatServlet");
		
	}

}
