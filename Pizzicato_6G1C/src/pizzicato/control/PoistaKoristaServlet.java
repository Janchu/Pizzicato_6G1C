package pizzicato.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzicato.model.Tilaus;

/**
 * Servlet implementation class PoistaKoristaServlet
 */
@WebServlet("/PoistaKoristaServlet")
public class PoistaKoristaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Tilaus ostoskori;
		ostoskori = (Tilaus) session.getAttribute("ostoskori");

		String idStr = request.getParameter("id");
		int poistettavaId = new Integer(idStr);

		if (ostoskori.getTilausrivit().get(poistettavaId).getLkm() > 1) {
			int vanhaLkm = ostoskori.getTilausrivit().get(poistettavaId)
					.getLkm();
			int uusiLkm = vanhaLkm - 1;
			ostoskori.getTilausrivit().get(poistettavaId).setLkm(uusiLkm);

		} else {
			ostoskori.getTilausrivit().remove(poistettavaId);
		}

		int yhtlkm = 0;
		double yhthinta = 0;
		
		for (int i = 0; i < ostoskori.getTilausrivit().size(); i++) {
			int rivilkm = ostoskori.getTilausrivit().get(i).getLkm();
			yhtlkm = yhtlkm + rivilkm;
			double rivinhinta = rivilkm * ostoskori.getTilausrivit().get(i).getTilattuTuote().getHinta();
			ostoskori.getTilausrivit().get(i).setRivihinta(rivinhinta);
			yhthinta = yhthinta + rivinhinta;
		}
		ostoskori.setYhthinta(yhthinta);
		ostoskori.setYhtlkm(yhtlkm);
		

		response.sendRedirect("OstoskoriServlet");
	}

}
