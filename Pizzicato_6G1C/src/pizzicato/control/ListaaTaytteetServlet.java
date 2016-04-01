package pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Tayte;
import pizzicato.model.dao.TayteDAO;

@WebServlet("/ListaaTaytteetServlet")
public class ListaaTaytteetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Luodaan TayteDAO
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();

		// ArrayList tallennetaan request-olioon jsp:lle viet‰v‰ksi
		request.setAttribute("taytteet", taytteet);

		// L‰hetet‰‰n jsp:lle
		String jsp = "/view/listaa-taytteet.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
