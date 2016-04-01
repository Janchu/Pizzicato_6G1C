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

import pizzicato.model.Tayte;
import pizzicato.model.dao.TayteDAO;

/**
 * Servlet implementation class LisaaTayteServlet
 */
@WebServlet("/LisaaTayteServlet")
public class LisaaTayteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private RequestDispatcher jsp;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Luodaan TayteDAO ja ArrayList
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		
		// ArrayList tallennetaan request-olioon jsp:lle viet�v�ksi
		request.setAttribute("taytteet", taytteet);
		
		// L�htetet��n jsp:lle
		String jsp = "/view/lisaa-tayte.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Luodaan TayteDAO (ja decimalformat)
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();
		DecimalFormat formatter = new DecimalFormat("#0.00");
		
		// ArrayList tallennetaan request-olioon jsp:lle viet�v�ksi
		request.setAttribute("taytteet", taytteet);
		
		jsp = getServletContext().getRequestDispatcher("/view/lisaa-tayte.jsp");
		
		// Alustetaan ID nollaksi, koska ID generoituu kannassa
		// automaattisesti 
		int id = 0;
		
		// Haetaan k�ytt�j�n sy�tt�m�t t�ytteen nimi, nimi_eng, hinta ja
		// kilohinta.
		// Muutetaan hinta ja kilohinta oikeaan muotoon.
		String nimi = request.getParameter("tayteNimi");
		String nimi_eng = request.getParameter("tayteNimi_eng");
		String hintaStr = request.getParameter("tayteHinta");
		String uusiHintaStr = hintaStr.replace(",", ".");
		double hinta = new Double(uusiHintaStr);
		formatter.format(hinta);
		String kilohintaStr = request.getParameter("Kilohinta");
		String uusiKilohintaStr = kilohintaStr.replace(",", ".");
		double kilohinta = new Double(uusiKilohintaStr);
		formatter.format(kilohinta);

		try {
			// Luodaan uusi tayte-olio kantaan viet�v�ksi
			Tayte lisattavaTayte = new Tayte(id, nimi, nimi_eng, hinta,
					kilohinta);

			// Kutsutaan updateTayte metodia
			taytedao.addTayte(lisattavaTayte);

			// Palauteen k�ytt�j� taytelistan muokkaustilaan.
			response.sendRedirect("MuokkaaTaytelistaServlet");

		} catch (Exception e) {
			response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp");
	
	}

	}
		
}
