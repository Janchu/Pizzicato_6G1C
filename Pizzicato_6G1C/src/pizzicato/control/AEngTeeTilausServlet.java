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
 * Servlet implementation class AEngTeeTilausServlet
 */
@WebServlet("/AEngTeeTilausServlet")
public class AEngTeeTilausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Lähetetään jsp:lle
		String jsp = "/view/aeng-tee-tilaus.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Tilaus tilaus = new Tilaus();
		Kayttaja tilaaja = new Kayttaja();

		tilaus = (Tilaus) session.getAttribute("ostoskori");

		String etunimi = request.getParameter("etunimi");
		String sukunimi = request.getParameter("sukunimi");
		String puh = request.getParameter("puh");
		String email = request.getParameter("email");
		String osoite = request.getParameter("osoite");
		String postinro = request.getParameter("postinro");
		String postitmp = request.getParameter("postitmp");
		String lisatiedot = request.getParameter("lisatiedot");

		tilaus.setTila("Valmis");
		tilaus.setToimitus("Kotiinkuljetus");
		tilaus.setMaksutapa("Kateinen");

		tilaus.setLisatiedot(lisatiedot);

		System.out.println(tilaus);

		tilaaja.setEtunimi(etunimi);
		tilaaja.setSukunimi(sukunimi);
		tilaaja.setPuh(puh);
		tilaaja.setEmail(email);
		tilaaja.setOsoite(osoite);
		tilaaja.setPostinro(postinro);
		tilaaja.setPostitmp(postitmp);

		System.out.println(tilaaja);

		session.setAttribute("tilaus", tilaus);
		session.setAttribute("tilaaja", tilaaja);

		response.sendRedirect("AEngYhteenvetoServlet");

	}

}
