package pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Juoma;
import pizzicato.model.dao.JuomaDAO;

/**
 * Servlet implementation class ListaaJuomatServlet
 */
@WebServlet("/ListaaJuomatServlet")
public class ListaaJuomatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Luodaan JuomaDAO
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomat = juomadao.findAll();
		
		System.out.println(juomat);
		
		// Arraylist tallennetaan request-olioon jsp:lle vietäväksi
		request.setAttribute("juoma", juomat);
		
		// Lähetetään jsp:lle
		String jsp = "/view/listaa-juomat.jsp";
		RequestDispatcher dispatcher = getServletContext().
				getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}