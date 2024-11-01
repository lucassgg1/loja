package br.com.dlei.control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.dlei.dao.UserDAO;
import br.com.dlei.model.User;
import br.com.dlei.security.Autorizacao;
import br.com.dlei.security.DetalheUsuario;
import br.com.dlei.security.Encryption;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDao;

	// primeiro método a ser chamado
	public void init() {
		userDao = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processarRequisicao(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processarRequisicao(request, response);
	}

	private void processarRequisicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "login":
				login(request, response);
				break;
			case "formLogin":
				formLogin(request, response);
				break;
			case "logout":
				logout(request, response);
				break;

			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void formLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
		String username = request.getParameter("login");
		String senha = request.getParameter("senha");

		User usuario = userDao.buscarUsuarioPorLogin(username);
		String path = "";

		// se usuário for diferente de nulo e estiver ativo
		if (usuario != null && usuario.isAtivo()) { // login válido
			// compara a senha
			boolean comparacao = Encryption.compararSenha(senha, usuario.getPassword());

			if (comparacao) { // senha válida

				DetalheUsuario detalheUsuario = new DetalheUsuario(usuario);

				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogado", detalheUsuario);
				// path = "public/publica-logado.jsp"; - página apenas para teste
				// aula 18
				Autorizacao autorizacao = new Autorizacao();
				path = autorizacao.indexPerfil(detalheUsuario);

			} else { // senha inválida ou não ativo
				path = "index.jsp";
				String texto = "Usuário ou senha inválidos";
				request.setAttribute("mensagem", texto);
			}
		} else {
			path = "index.jsp";
			String texto = "Usuário ou senha inválidos";
			request.setAttribute("mensagem", texto);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

}
