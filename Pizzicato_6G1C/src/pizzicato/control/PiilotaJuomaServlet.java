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
 * Servlet implementation class PiilotaJuomaServlet
 */
@WebServlet("/PiilotaJuomaServlet")
public class PiilotaJuomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		JuomaDAO juomadao = new JuomaDAO();

		// Pyydetään muokkaa-juomalista.jsp:ltä juoman id ja nykyinen näkyvyys
		// ja muutetaan ne oikeaan muotoon
		String idStr = request.getParameter("Id");
		int id = new Integer(idStr);
		String nakyvyysStr = request.getParameter("Nakyvyys");
		int nakyvyys = new Integer(nakyvyysStr);

		// Alustetaan muut juoma-oliolle vietävät arvot nolliksi ja tyhjiksi,
		// koska vain näkyvyyttä halutaan muuttaa.
		String tyyppi = "";
		String nimi = "";
		String nimi_eng = "";
		double koko = 0;
		double hinta = 0;

		try {
			// Luodaan juoma-olio, jolle annetaan uusi näkyvyys ja tyhjät arvot.
			Juoma juoma = new Juoma(id, tyyppi, nimi, hinta, koko, nakyvyys,
					nimi_eng);
			juomadao.hideJuoma(juoma);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect("MuokkaaPizzalistaServlet");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
