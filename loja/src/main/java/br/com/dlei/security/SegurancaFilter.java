package br.com.dlei.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/auth/*"})
public class SegurancaFilter extends HttpFilter implements Filter{
	private static final long serialVersionUID = 1L;

	public SegurancaFilter() {
		super();
	}
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =  (HttpServletRequest) request;		
		String path = req.getRequestURI();
		
		HttpSession session = req.getSession();
		DetalheUsuario detalheUsuario = (DetalheUsuario) session.getAttribute("usuarioLogado");
		
		Autorizacao autorizacao = new Autorizacao();
		boolean autorizado = autorizacao.temAutorizacao(detalheUsuario, path);
		if (autorizado) {
			chain.doFilter(request, response);
		} else {
			String pth = req.getContextPath() + "/public/publica-acesso-negado.jsp";
			HttpServletResponse resp = (HttpServletResponse) response;
			System.out.println(pth);
			resp.sendRedirect(pth);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
