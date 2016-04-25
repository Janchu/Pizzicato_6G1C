package pizzicato.model;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class SecurityFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	public void destroy() {
		
	}
	
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpSession session = req.getSession();

		Long kayttajatunnus = (Long) session.getAttribute("id");

		
		String servletPath = req.getServletPath();
		if (servletPath.endsWith(".css")) {
			chain.doFilter(req, resp);
		} else if(servletPath.endsWith(".png")) {
			chain.doFilter(req, resp);
		} else if(servletPath.endsWith(".jpg")) {
			chain.doFilter(req, resp);
		} else if(servletPath.endsWith(".ico")) {
			chain.doFilter(req, resp);
		} else if (servletPath.equals("/LoginServlet")) {
			chain.doFilter(req, resp);
		} else if (servletPath.equals("/EngListaaPizzatServlet")) {
			chain.doFilter(req, resp);
		} else if (servletPath.equals("/ListaaPizzatServlet")) {
			chain.doFilter(req, resp);
		} else if (kayttajatunnus != null) {
			chain.doFilter(req, resp);
		} else if (servletPath.equals("/OstoskoriServlet")) {
			chain.doFilter(req, resp);
		} else if (servletPath.equals("/TeeTilausServlet")) {
			chain.doFilter(req, resp);
		} else if (servletPath.equals("/PoistaKoristaServlet")) {
			chain.doFilter(req, resp);
		} else if (servletPath.equals("/EngListaaJuomatServlet")) {
			chain.doFilter(req, resp);
		} else if (servletPath.equals("/ListaaJuomatServlet")) {
			chain.doFilter(req, resp);
		} else {
			resp.sendRedirect("/Pizzicato_6G1C/LoginServlet");
		}
		
		
		
		
	}
}
