package pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Juoma;
import pizzicato.model.dao.JuomaDAO;

/**
 * Servlet implementation class LisaaJuomaServlet
 */
@WebServlet("/LisaaJuomaServlet")
public class LisaaJuomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Luodaan JuomaDAO ja sille Arraylist
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();
		
		// Arraylist tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("juomat", juomat);
		
		// Lähetetään jsp:lle
		String jsp = "view/lisaa-juoma.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Luodaan JuomaDAO
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();
		
		// Arraylist tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("juomat", juomat);
		
		RequestDispatcher jsp = getServletContext().getRequestDispatcher("/view/lisaa-juoma.jsp");
		
		// Alustetaan ID nollaksi, koska ID generoituu kannassa
		// automaattisesti
		
		int id = 0;
		
		// Nämä arvot ovat pelkästään futureproofia varten
		String tyyppi = "juoma";
		int nakyvyys = 1;
		
		try {
			// Luodaan uusi juoma-olio kantaan vietäväksi
			Juoma leikkiJuoma = (Juoma) request.getAttribute("uusiJuoma");
			String nimi = leikkiJuoma.getNimi();
			double hinta = leikkiJuoma.getHinta();
			double koko = leikkiJuoma.getKoko();
			Juoma uusiJuoma = new Juoma(id, tyyppi, nimi, hinta, koko, nakyvyys);
			juomadao.addJuoma(uusiJuoma);
			response.sendRedirect("MuokkaaJuomalistaServlet");
		}catch(Exception e) {
			response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp");
		}
	}

}
