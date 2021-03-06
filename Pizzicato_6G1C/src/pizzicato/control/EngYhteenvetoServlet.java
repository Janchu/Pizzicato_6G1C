package pizzicato.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
 * Servlet implementation class EngYhteenvetoServlet
 */
@WebServlet("/EngYhteenvetoServlet")
public class EngYhteenvetoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");
		Kayttaja tilaaja = (Kayttaja) session.getAttribute("tilaaja");

		request.setAttribute("tilaus", tilaus);
		request.setAttribute("tilaaja", tilaaja);

		// Lähetetään jsp:lle
		String jsp = "/view/eng-yhteenveto.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

HttpSession session = request.getSession();
		
		Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");
		Kayttaja tilaaja = (Kayttaja) session.getAttribute("tilaaja");
		
		Date pvmaika = new Date();
		SimpleDateFormat asettelu = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tilausaika = asettelu.format(pvmaika);
		tilaus.setTilausaika(tilausaika);
		
		TilausDAO tilausdao = new TilausDAO();
		
		try {
		tilausdao.addTilaus(tilaus, tilaaja);
		ArrayList<Tilausrivi> uusiTilausrivit = new ArrayList<Tilausrivi>();
		tilaus.setYhthinta(0);
		tilaus.setYhtlkm(0);
		tilaus.setTilausrivit(uusiTilausrivit);		
		session.setAttribute("ostoskori", tilaus);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
				
		response.sendRedirect("EngListaaPizzatServlet");
	}

}
