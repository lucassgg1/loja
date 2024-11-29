package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import model.ModelLogin;



/*O chamando Controller são as servlets ou ServletLoginController*/
@WebServlet(urlPatterns = { "/principal/ServletLogin", "/ServletLogin" }) /* Mapeamento de URL que vem da tela */
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Aula 2.17
	private DAOLoginRepository daoLogin = new DAOLoginRepository();
	private DAOUsuarioRepository daousuario = new  DAOUsuarioRepository();
	private ModelLogin modelLogin = new ModelLogin();
	//private RequestDispatcher redirecionar;

	public ServletLogin() {

	}

	/* Recebe os dados pela URL em parâmetros */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Logout
		String acao = request.getParameter("acao");
		if (acao != null && !acao.isEmpty() & acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();// invalida/ derruba a sessão
			//Redireciona para página de login
			RequestDispatcher redirecionar = request.getRequestDispatcher("/login.jsp");
			redirecionar.forward(request, response);
		} else {
			doPost(request, response); // Absorve a requisição doGet e não deixa a página em branco
		}

	}

	/* Recebe os dados envados por um formulário */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* "request.getParameter" "pega" da tela do usuário */
		String login = (request.getParameter("login"));
		String senha = (request.getParameter("senha"));
		String url = request.getParameter("url"); // pegando a URL que vem da página de login "index.jsp"

		try {
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				/* aqui eu passo/ "envio" os dados para o objeto */				
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);

				if (daoLogin.validarAutenticacao(modelLogin)) {/* Deu certo o login */
					
					modelLogin = daousuario.consultaUsuarioLogado(login);
					/* Coloca o login do user na sessao */
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					/* Indentifica se é administrador */
					request.getSession().setAttribute("perfil", modelLogin.getPerfil());
					/* Pega foto do user logado*/
					request.getSession().setAttribute("imagemUser", modelLogin.getFotouser());

					if (url == null || url.equals("null")) { // se a URL for nula
						url = "principal/principal.jsp"; // adiciono o valor default do sistema
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);

				} else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("/login.jsp");
					request.setAttribute("msg", "Informe o login e senha corretamente!");
					redirecionar.forward(request, response);
				}

			} else {
				/* Define o redirecionamento */
				RequestDispatcher redirecionar = request.getRequestDispatcher("login.jsp");
				/* Disponibiliza a mensagem usando "setAttribute" */
				request.setAttribute("msg", "Informe o login e senha corretamente!");
				/* Executa o redirecionamento */
				redirecionar.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(" --> Erro de Login - [Class ServletLogin/DoPost] <-- \n" + e);
			//e.printStackTrace();
			// envia o erro para página de erro
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}