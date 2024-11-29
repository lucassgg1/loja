package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;



//aula 2.15
// Tudo que vier da pasta "principal" será interceptado
@WebFilter(urlPatterns = { "/principal/*" }) /* Intercepta todas as requisições que vierem do projeto ou mapeamento */
public class FilterAutenticacao extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	private static Connection connection; /* chama a conexão [Class SingleConnectionBanco] */

	public FilterAutenticacao() {

	}

	// Encerra os processos quando o servidor é parado
	// finalizaria os processos de conexão com o DB
	public void destroy() {
		try {
			connection.close(); // **Fecha a conexão do DB/
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Intercepta as requisições e dá as respostas no sistema
	// Tudo que fizer no sistema passará por aqui
	/*
	 * Validação de autenticação; Dar commit e rolback de transações no DB Validar e
	 * fazer redirecionamento de páginas
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlParaAutenticar = req.getServletPath();/* URL que está sendo acessada */

			// Validar se está logado
			/*
			 * Se não estiver logado, e a URL que está acessando for diferente da URL de
			 * login, redireciona para tela de login
			 */
			if (usuarioLogado == null || (usuarioLogado != null && usuarioLogado.isEmpty()) /* aula 2.15 */
					&& !urlParaAutenticar.contains("/principal/ServeletLogin")) { /* Não está logado */

				/*
				 * mando para url de login, e depois de logado redireciono para a url que ele
				 * estava tentando acessar
				 */
				RequestDispatcher redireciona = request.getRequestDispatcher("/login.jsp?url=" + urlParaAutenticar);
				// mando a mensagem
				request.setAttribute("msg", "Por favor realize o login!");
				// executo o redirecionamento
				redireciona.forward(request, response);
				return; // Para a execução e redireciona
			} else {
				chain.doFilter(request, response);
			}

			connection.commit(); /* se deu certo, executa/ salva as ações solicitadas no DB */

		} catch (Exception e) {
			System.out.println("Erro na Class FilterAutenticacao/ doFilter");			
			
			//envia o erro para página de erro
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

			try {
				connection.rollback(); /* desfaz as alteraÃ§Ãµes feitas no DB - PESQUISAR SOBRE */
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	// Inicia os processos ou recursos quando o servidor sobe o projeto
	// iniciar a conexão com o DB
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection(); /* Aqui efetivamente é iniciada a conexão ao DB */
	}

}
