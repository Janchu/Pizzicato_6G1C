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
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Tilaus ostoskori;
		ostoskori = (Tilaus) session.getAttribute("ostoskori");
		
		String idStr = request.getParameter("id");
		int id = new Integer(idStr);
		
		System.out.println(id);
		System.out.println(ostoskori.getTilausrivit().get(id));
		ostoskori.getTilausrivit().remove(id);
		
		response.sendRedirect("OstoskoriServlet");
	}

}
