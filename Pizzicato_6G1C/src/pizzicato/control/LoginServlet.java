package pizzicato.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzicato.model.Kayttaja;
import pizzicato.model.dao.KayttajaDAO;

import com.sun.istack.internal.logging.Logger;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher jsp;

	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		jsp = context.getRequestDispatcher("/view/login.jsp");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		jsp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String kayttajatunnus = request.getParameter("kayttajatunnus");
		String salasana = request.getParameter("salasana");
		
		KayttajaDAO kayttajadao = new KayttajaDAO();
		Kayttaja kayttaja = kayttajadao.etsiTunnuksella(kayttajatunnus);
		
		if (kayttaja == null) {
			request.setAttribute("message", "Kirjautuminen epäonnistui.");
			jsp.forward(request, response);
		} else if (salasana == null || !kayttaja.getSalasana().equals(salasana)) {
			request.setAttribute("message", "Kirjautuminen epäonnistui.");
			jsp.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			int idInt = kayttaja.getId();
			Long id = new Long(idInt);
			session.setAttribute("id", id);
			System.out.println("Sessiossa id:n arvo on: " + session.getAttribute("id"));
			response.sendRedirect("OmistajaListaaPizzatServlet");
		}
		
	}

}
