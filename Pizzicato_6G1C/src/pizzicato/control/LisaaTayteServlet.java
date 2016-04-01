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
		
		// ArrayList tallennetaan request-olioon jsp:lle viet‰v‰ksi
		request.setAttribute("taytteet", taytteet);
		
		// L‰htetet‰‰n jsp:lle
		String jsp = "/view/lisaa-tayte.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Luodaan TayteDAO (ja decimalformat)
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> taytteet = taytedao.findAll();
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();
		
		// ArrayList tallennetaan request-olioon jsp:lle viet‰v‰ksi
		request.setAttribute("taytteet", taytteet);
		
		jsp = getServletContext().getRequestDispatcher("/view/lisaa-tayte.jsp");
		
		// Alustetaan ID nollaksi, koska ID generoituu kannassa
		// automaattisesti 
		int id = 0;
	
	}

}
