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
import pizzicato.model.Tilausrivi;
import pizzicato.model.dao.JuomaDAO;
import pizzicato.model.dao.KayttajaDAO;

/**
 * Servlet implementation class AEngListaaJuomatServlet
 */
@WebServlet("/AEngListaaJuomatServlet")
public class AEngListaaJuomatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Luodaan JuomaDAO ja KayttajDAO
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();
		KayttajaDAO kayttajadao = new KayttajaDAO();
		ArrayList<Kayttaja> kayttajat = kayttajadao.findAll();
		
		HttpSession session = request.getSession();
		Tilaus ostoskori = (Tilaus) session.getAttribute("ostoskori");
		
		int kirjautunutKayttajaId = 0;
		
		
		if(ostoskori == null) {
			ostoskori = new Tilaus();
		
		}
		
		ArrayList<Tilausrivi> tilausrivit = ostoskori.getTilausrivit();
		
		//ArrayList tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("juomat", juomat);
		request.setAttribute("kayttajat", kayttajat);
		
		// Lähetetään jsp:lle
		String jsp = "/view/aeng-listaa-juomat.jsp";
		RequestDispatcher dispatcher = getServletContext().
				getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}