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
import pizzicato.model.Mauste;
import pizzicato.model.Pizza;
import pizzicato.model.Tayte;
import pizzicato.model.Tilaus;
import pizzicato.model.Tilausrivi;
import pizzicato.model.dao.JuomaDAO;
import pizzicato.model.dao.MausteDAO;
import pizzicato.model.dao.PizzaDAO;

/**
 * Servlet implementation class OstoskoriServlet
 */
@WebServlet("/EngOstoskoriServlet")
public class EngOstoskoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();

		HttpSession session = request.getSession();
		Tilaus ostoskori = (Tilaus) session.getAttribute("ostoskori");

		if (ostoskori == null) {
			ostoskori = new Tilaus();
		}

		// Käyttäjän checkaus
		Kayttaja kayttaja = (Kayttaja) session.getAttribute("kayttaja");

		if (kayttaja == null) {
			kayttaja = new Kayttaja();
		}
		request.setAttribute("kayttaja", kayttaja);

		request.setAttribute("ostoskori", ostoskori);
		request.setAttribute("pizzat", pizzat);

		String jsp = "/view/eng-ostoskori.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Tilaus ostoskori;

		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = pizzadao.findAll();
		MausteDAO maustedao = new MausteDAO();
		ArrayList<Mauste> maustelista = maustedao.findAll();
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();

		HttpSession session = request.getSession();
		ostoskori = (Tilaus) session.getAttribute("ostoskori");

		if (ostoskori == null) {
			ostoskori = new Tilaus();
			session.setAttribute("ostoskori", ostoskori);
		}

		int yhtlkm = ostoskori.getYhtlkm();
		double yhthinta = ostoskori.getYhthinta();

		String idStr = request.getParameter("koriin");
		int id = new Integer(idStr);
		String tyyppi = request.getParameter("tyyppi");

		Tilausrivi uusiTilausrivi = new Tilausrivi();

		ArrayList<Tilausrivi> uudetTilausrivit = new ArrayList<Tilausrivi>();
		if (ostoskori.getTilausrivit() != null) {
			uudetTilausrivit = ostoskori.getTilausrivit();
		}
		int uusiRivi = 0;

		if (tyyppi.equalsIgnoreCase("pizza")) {

			Pizza tilattuPizza = new Pizza();
			String[] mausteet = request.getParameterValues("mauste");
			ArrayList<Mauste> uudetMausteet = new ArrayList<Mauste>();

			ArrayList<Tayte> taytelista = new ArrayList<Tayte>();

			for (int i = 0; i < pizzat.size(); i++) {
				if (pizzat.get(i).getId() == id) {
					tilattuPizza.setId(id);
					tilattuPizza.setHinta(pizzat.get(i).getHinta());
					tilattuPizza.setNimi(pizzat.get(i).getNimi());
					tilattuPizza.setTyyppi(pizzat.get(i).getTyyppi());

					yhthinta = yhthinta + tilattuPizza.getHinta();

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
							for (int k = 0; k < maustelista.size(); k++) {
								if (mausteId == maustelista.get(k).getId()) {
									String mausteNimi = maustelista.get(k)
											.getNimi();
									Mauste mauste = new Mauste();
									mauste.setNimi(mausteNimi);
									uudetMausteet.add(mauste);
								}
							}

						}
					}

					String lkmStr = request.getParameter("maara");
					int lkm = new Integer(lkmStr);

					yhtlkm = yhtlkm + lkm;

					ostoskori.setYhtlkm(yhtlkm);
					
					int korinKoko;
					
					if (ostoskori.getTilausrivit() == null) {
						korinKoko = 0;
					} else {
					korinKoko = ostoskori.getTilausrivit().size();
					}

					uusiRivi = korinKoko + 1;

					uusiTilausrivi.setRivinumero(uusiRivi);
					uusiTilausrivi.setLkm(lkm);
					uusiTilausrivi.setTilattuTuote(tilattuPizza);
					uusiTilausrivi.setMaustelista(uudetMausteet);

				}
			}
		} else if (tyyppi.equalsIgnoreCase("juoma")) {

			Juoma tilattuJuoma = new Juoma();

			for (int i = 0; i < juomat.size(); i++) {
				if (juomat.get(i).getId() == id) {
					tilattuJuoma.setId(id);
					tilattuJuoma.setNimi(juomat.get(i).getNimi());
					tilattuJuoma.setHinta(juomat.get(i).getHinta());
					tilattuJuoma.setTyyppi(juomat.get(i).getTyyppi());

				}
			}

			String lkmStr = request.getParameter("maara");
			int lkm = new Integer(lkmStr);

			yhtlkm = yhtlkm + lkm;

			ostoskori.setYhtlkm(yhtlkm);

			int korinKoko;
			
			if (ostoskori.getTilausrivit() == null) {
				korinKoko = 0;
			} else {
			korinKoko = ostoskori.getTilausrivit().size();
			}

			uusiRivi = korinKoko + 1;
			uusiTilausrivi.setRivinumero(uusiRivi);
			uusiTilausrivi.setLkm(lkm);
			uusiTilausrivi.setTilattuTuote(tilattuJuoma);

		}

		uudetTilausrivit.add(uusiTilausrivi);

		ostoskori.setTilausrivit(uudetTilausrivit);

		// Lasketaan kokonaishinta ja kokonaislkm
		yhtlkm = 0;
		yhthinta = 0;
		for (int i = 0; i < ostoskori.getTilausrivit().size(); i++) {
			int rivilkm = ostoskori.getTilausrivit().get(i).getLkm();
			yhtlkm = yhtlkm + rivilkm;
			double rivinhinta = rivilkm
					* ostoskori.getTilausrivit().get(i).getTilattuTuote()
							.getHinta();
			ostoskori.getTilausrivit().get(i).setRivihinta(rivinhinta);
			yhthinta = yhthinta + rivinhinta;
		}
		ostoskori.setYhthinta(yhthinta);
		ostoskori.setYhtlkm(yhtlkm);

		session.setAttribute("ostoskori", ostoskori);

		request.setAttribute("ostoskori", ostoskori);
		request.setAttribute("pizzat", pizzat);
		
		if (tyyppi.equalsIgnoreCase("juoma")) {
		response.sendRedirect("EngListaaJuomatServlet");
		} else {
			response.sendRedirect("EngListaaPizzatServlet");
		}
		
	}
}
