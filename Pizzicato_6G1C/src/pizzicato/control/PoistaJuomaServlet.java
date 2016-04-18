package pizzicato.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Juoma;
import pizzicato.model.dao.JuomaDAO;

/**
 * Servlet implementation class PoistaJuomaServlet
 */
@WebServlet("/PoistaJuomaServlet")
public class PoistaJuomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Pyydetään poistettavan juoman id muokkaa-juomalista.jsp:ltä ja
		// muutetaan se oikeaan muotoon
		String poistettavaJuomaIdStr = request.getParameter("JuoId");
		int poistettavaJuomaId = new Integer(poistettavaJuomaIdStr);

		// Luodaan juomaDAO johon haetaan juomat kannasta
		JuomaDAO juomadao = new JuomaDAO();

		// Pyydetään jsp:ltä mukana kuljetettu poistettavan juoman id ja
		// alustetaan muut nolliksi
		String tyyppi = "juoma";
		String nimi = "";
		double hinta = 0;
		double koko = 0;
		String nakyvyys = "";
		String nimi_eng = "";

		try {
			// Luodaan Juoma-olio
			Juoma poistettavajuoma = new Juoma(poistettavaJuomaId, tyyppi,
					nimi, hinta, koko, nakyvyys, nimi_eng);

			// Kutsutaan deleteJuoma-metodia
			juomadao.deleteJuoma(poistettavajuoma);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Palautetaan käyttäjä juomalistan muokkaustilaan
		response.sendRedirect("MuokkaaJuomalistaServlet");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
