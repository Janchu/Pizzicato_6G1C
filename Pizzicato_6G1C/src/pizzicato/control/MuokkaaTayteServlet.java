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
 * Servlet implementation class MuokkaaTayteServlet
 */
@WebServlet("/MuokkaaTayteServlet")
public class MuokkaaTayteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan TayteDAO-oliot
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();

		String muokattavaTayteId = request.getParameter("TId");
		int TId = new Integer(muokattavaTayteId);
		request.setAttribute("muokattavaTayteId", TId);

		// ArrayList tallennetaan request-olioon jsp:lle viet�v�ksi
		request.setAttribute("taytteet", taytteet);

		// L�hetet��n jsp:lle
		String jsp = "/view/muokkaa-tayte.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan TayteDAO, jota tarvitaan kun t�ytteiden
		// muokkaustoiminto lis�t��n.
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		DecimalFormat formatter = new DecimalFormat("#0.00");
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();

		// Haetaan k�ytt�j�n sy�tt�m�t t�ytteen nimi, nimi_eng, hinta ja
		// kilohinta.
		// Muutetaan hinta ja kilohinta oikeaan muotoon.
		String nimi = request.getParameter("tayteNimi");
		String nimi_eng = request.getParameter("tayteNimi_eng");
		String hintaStr = request.getParameter("tayteHinta");
		String uusiHintaStr = hintaStr.replace(",", ".");
		double hinta = new Double(uusiHintaStr);
		formatter.format(hinta);
		String kilohintaStr = request.getParameter("tayteKilohinta");
		String uusiKilohintaStr = kilohintaStr.replace(",", ".");
		double kilohinta = new Double(uusiKilohintaStr);
		formatter.format(kilohinta);
		String idStr = request.getParameter("tayteId");
		int id = new Integer(idStr);

		// Luodaan Tayte-olio.
		Tayte muokattavaTayte = new Tayte(id, nimi, nimi_eng, hinta, kilohinta);
		taytelista.add(muokattavaTayte);

		try {
			// Luodaan uusi tayte-olio kantaan viet�v�ksi
			Tayte muokattuTayte = new Tayte(id, nimi, nimi_eng, hinta,
					kilohinta);

			// Kutsutaan updateTayte metodia
			taytedao.updateTayte(muokattuTayte);

			// Palauteen k�ytt�j� taytelistan muokkaustilaan.
			response.sendRedirect("MuokkaaTaytelistaServlet");

		} catch (Exception e) {
			response.sendRedirect("/Pizzicato_6G1C/view/virheilmoitus.jsp");

		}

	}

}
