package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOOrcamento;
import model.ModelOrcamento;

@WebServlet(urlPatterns = { "/ServletOrcamentoController" }) // Array de mapeamento
public class ServletOrcamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOOrcamento daoOrcamento = new DAOOrcamento();

	public ServletOrcamentoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");

			// listar todos orcamentos cadastrados no DB - Tela Cadastro de Produtos -
			// "LIST<>"
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarOrc")) {

				List<ModelOrcamento> modelOrcamentos = daoOrcamento.consultaOrcListReport();

				request.setAttribute("msg", "Produtos Cadastrados");
				request.setAttribute("modelOrcamentos", modelOrcamentos);
				request.getRequestDispatcher("orcamento.jsp").forward(request, response);
			}

			// Relatório completo
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioProd")) {

				// Use diretamente a consulta para obter a lista dos orcamentos - "LIST<>"
				request.setAttribute("listaOrc", daoOrcamento.consultaOrcListReport());

				// Ajustar redirecionamento para a página JSP
				request.getRequestDispatcher("principal/rel-orc.jsp").forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(
					" ==> Erro ao tentar Listar Produto <==\n--> Ver [Class ServletOrcamentoController/doGet]  <--"
							+ "\n--> ou [Class DAOOrcamento/querys] <--\n" + e);

			// envia o erro para página de erro
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Vindo do form [orcamento.jsp]
		try {
			String msg = "Cadastro realizado com sucesso!";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String telefone = request.getParameter("telefone");
			String orcamento = request.getParameter("orcamento");

			// convertendo para Long: Se "id" for diferente de nulo e não estiver em branco,
			// converte-> senão define como null
			ModelOrcamento modelOrcamnto = new ModelOrcamento();
			modelOrcamnto.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelOrcamnto.setNome(nome);
			modelOrcamnto.setEmail(email);
			modelOrcamnto.setTelefone(telefone);
			modelOrcamnto.setOrcamento(orcamento);

			msg = "Orçamento Realizado com sucesso! Em Breve entraremos em contato!"; //ver procesar por meio do JS

			// daoUsuario.gravarProd(modelProduto); //Grava no DB
			modelOrcamnto = daoOrcamento.gravarOrc(modelOrcamnto);

			request.setAttribute("msg", msg);

			// Rediciona
			request.getRequestDispatcher("orcamento.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println(
					" ==> Erro ao Salvar Orçamento <==\n--> Ver [Class ServletOrcamentoController/DoPost]  <--"
							+ "\n--> ou [Class DAOOrcamento/gravarOrc] <--\n" + e);

			// envia o erro para página de erro
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
