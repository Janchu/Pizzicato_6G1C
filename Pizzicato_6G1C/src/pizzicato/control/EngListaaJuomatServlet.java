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

import pizzicato.model.Juoma;
import pizzicato.model.Kayttaja;
import pizzicato.model.Tilaus;
import pizzicato.model.dao.JuomaDAO;


@WebServlet("/EngListaaJuomatServlet")
public class EngListaaJuomatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan JuomaDAO
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();

		HttpSession session = request.getSession();
		Tilaus ostoskori = (Tilaus) session.getAttribute("ostoskori");

		if (ostoskori == null) {
			ostoskori = new Tilaus();
		}


		// Käyttäjän checkaus
		Kayttaja kayttaja = (Kayttaja) session.getAttribute("kayttaja");
		if (kayttaja == null) {
			kayttaja = new Kayttaja();

		}
		request.setAttribute("kayttaja", kayttaja);

		// Tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("juomat", juomat);
		request.setAttribute("ostoskori", ostoskori);

		// Lähetetään jsp:lle
		String jsp = "/view/eng-listaa-juomat.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
