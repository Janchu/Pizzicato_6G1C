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

import pizzicato.model.Mauste;
import pizzicato.model.Pizza;
import pizzicato.model.Tayte;
import pizzicato.model.Tilaus;
import pizzicato.model.Tilausrivi;
import pizzicato.model.Tuote;
import pizzicato.model.dao.MausteDAO;
import pizzicato.model.dao.PizzaDAO;

/**
 * Servlet implementation class OstoskoriServlet
 */
@WebServlet("/OstoskoriServlet")
public class OstoskoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Tilaus ostoskori = (Tilaus) session.getAttribute("ostoskori");

		if (ostoskori == null) {
			ostoskori = new Tilaus();
		}

		ArrayList<Tilausrivi> tilausrivit = ostoskori.getTilausrivit();

		request.setAttribute("ostoskori", tilausrivit);

		String jsp = "/view/ostoskori.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Tilaus ostoskori;

		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();

		Pizza tilattuPizza = new Pizza();

		MausteDAO maustedao = new MausteDAO();
		ArrayList<Mauste> maustelista = maustedao.findAll();

		HttpSession session = request.getSession();
		ostoskori = (Tilaus) session.getAttribute("ostoskori");

		if (ostoskori == null) {
			ostoskori = new Tilaus();
			session.setAttribute("ostoskori", ostoskori);
		}

		String idStr = request.getParameter("koriin");
		int id = new Integer(idStr);

		System.out.println(idStr);

		Tilausrivi uusiTilausrivi = new Tilausrivi();

		String[] mausteet = request.getParameterValues("mauste");
		ArrayList<Mauste> uudetMausteet = new ArrayList<Mauste>();

		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();

		for (int i = 0; i < pizzat.size(); i++) {
			if (pizzat.get(i).getId() == id) {
				tilattuPizza.setId(id);
				tilattuPizza.setHinta(pizzat.get(i).getHinta());
				tilattuPizza.setNimi(pizzat.get(i).getNimi());
				tilattuPizza.setTyyppi(pizzat.get(i).getTyyppi());

				if (tilattuPizza.getTyyppi().equalsIgnoreCase("pizza")) {
					for (int j = 0; j < pizzat.size(); j++) {
						if (tilattuPizza.getId() == pizzat.get(i).getId()) {
							taytelista = pizzat.get(i).getTaytelista();
							tilattuPizza.setTaytelista(taytelista);
						}
					}
				}

				if (mausteet != null) {
					for (int j = 0; j < mausteet.length; j++) {

						String mausteIdStr = mausteet[j];
						int mausteId = new Integer(mausteIdStr);
						System.out.println(mausteId);
						for (int k = 0; k < maustelista.size(); k++) {
							if (mausteId == maustelista.get(k).getId()) {
								String mausteNimi = maustelista.get(k)
										.getNimi();
								Mauste mauste = new Mauste();
								mauste.setNimi(mausteNimi);
								System.out.println(mauste);
								uudetMausteet.add(mauste);
								System.out.println(uudetMausteet);
							}
						}

					}
				}

				String lkmStr = request.getParameter("maara");
				int lkm = new Integer(lkmStr);

				uusiTilausrivi.setLkm(lkm);
				uusiTilausrivi.setTilattuTuote(tilattuPizza);
				uusiTilausrivi.setMaustelista(uudetMausteet);
				ostoskori.addTilausrivi(uusiTilausrivi);

			}
		}

		System.out.println(ostoskori);

		System.out.println(ostoskori.getTilausrivit().get(0).getMaustelista());

		session.setAttribute("ostoskori", ostoskori);

		request.setAttribute("ostoskori", ostoskori);

		request.setAttribute("pizzat", pizzat);

		response.sendRedirect("ListaaPizzatServlet");
	}
}
