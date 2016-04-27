package pizzicato.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzicato.model.Kayttaja;
import pizzicato.model.Tilaus;

/**
 * Servlet implementation class AEngYhteenvetoServlet
 */
@WebServlet("/AEngYhteenvetoServlet")
public class AEngYhteenvetoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");
		Kayttaja tilaaja = (Kayttaja) session.getAttribute("tilaaja");

		request.setAttribute("tilaus", tilaus);
		request.setAttribute("tilaaja", tilaaja);

		// Lähetetään jsp:lle
		String jsp = "/view/aeng-yhteenveto.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
