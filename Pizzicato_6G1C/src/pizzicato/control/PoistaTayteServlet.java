package pizzicato.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Tayte;
import pizzicato.model.dao.TayteDAO;

/**
 * Servlet implementation class PoistaTayteServlet
 */
@WebServlet("/PoistaTayteServlet")
public class PoistaTayteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Pyydetään poistettavan täytteen id muokkaa-taytelista.jsp:ltä ja
		// muutetaan se oikeaan muotoon.
		String poistettavaTayteIdStr = request.getParameter("TayId");
		int poistettavaTayteId = new Integer(poistettavaTayteIdStr);

		// Luodaan tayteDAO johon haetaan täytteet kannasta.
		TayteDAO taytedao = new TayteDAO();

		// Pyydetään jsp:ltä mukana kuljetettu poistettavan täytteen id ja
		// alustetaan muut nolliksi
		String nimi = "";
		String nimi_eng = "";
		double hinta = 0;
		double kilohinta = 0;

		try {
			// Luodaan Täyte-olio
			Tayte poistettavatayte = new Tayte(poistettavaTayteId, nimi,
					nimi_eng, hinta, kilohinta);

			// Kutsutaan deleteTayte-metodia
			taytedao.deleteTayte(poistettavatayte);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Palautetaan käyttäjä täytelistan muokkaustilaan.
		response.sendRedirect("MuokkaaTaytelistaServlet");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
