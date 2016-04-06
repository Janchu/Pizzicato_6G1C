package pizzicato.model;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	public void destroy() {
		
	}
	
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpSession session = req.getSession();
		String kayttajatunnus = (String) session.getAttribute("kayttajatunnus");
		
		String servletPath = req.getServletPath();
		
		
		if (servletPath.equals("/LoginServlet")) {
			chain.doFilter(req, resp);
		} else if (servletPath.equals("/ListaaPizzatServlet")) {
			chain.doFilter(req, resp);
		} else if (kayttajatunnus != null) {
			chain.doFilter(req, resp);
		} else {
			resp.sendRedirect("/LoginServlet");
		}
		
		
		
		
	}
}
