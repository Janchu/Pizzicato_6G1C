package pizzicato.control;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Juoma;
import pizzicato.model.dao.JuomaDAO;

/**
 * Servlet implementation class MuokkaaJuomaServlet
 */
@WebServlet("/MuokkaaJuomaServlet")
public class MuokkaaJuomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan JuomaDAO olio
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();
		
		String muokattavaJuomaId = request.getParameter("JuoId");
		int JId = new Integer(muokattavaJuomaId);
		request.setAttribute("muokattavaJuomaId", JId);
		
		// ArrayList tallennetaan request olioon jsp:lle vietäväksi
		request.setAttribute("juomat", juomat);
		
		// Lähetetään jsp:lle
		String jsp = "/view/muokkaa-juoma.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Luodaan JuomaDAO, jota tarvitaan kun juomien
		// muokkaustoiminto lisätään
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();
		DecimalFormat formatter = new DecimalFormat("#0.00");
		
		// ArrayList tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("juomat", juomat);
		
		String idStr = request.getParameter("juomaId");
		int id = new Integer(idStr);
		
		RequestDispatcher jsp = getServletContext().getRequestDispatcher("/view/muokkaa-juoma.jsp");
		
		HashMap<String, String> errors = validateMuokkaa(request, id);
		if (!errors.isEmpty()) {
			request.setAttribute("muokattavaJuomaId", id);
			jsp.forward(request, response);
		} else {
			
			// Haetaan käyttäjän syöttämät juoman nimi ja hinta. Muutetaan hinta
			// oikeaan muotoon.
			String nimi = request.getParameter("juomaNimi");
			String nimi_eng = request.getParameter("juomaNimi_eng");
			String kokoStr = request.getParameter("juomaKoko");
			String hintaStr = request.getParameter("juomaHinta");
			String uusiHintaStr = hintaStr.replace(",", ".");
			double hinta = new Double(uusiHintaStr);
			formatter.format(hinta);
			
			// Nämä arvot ovat pelkästään futureproofia varten.
			String tyyppi = "juoma";
			int nakyvyys = 1;
				
		}
		try { 
			// Luodaan uusi juoma olio kantaan vietäväksi
			Juoma muokattuJuoma = new Juoma(id, tyyppi, nimi, hinta, koko, nakyvyys, nimi_eng);
			
			if ()
		}
		
	}

}
