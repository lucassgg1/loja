package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelProduto;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOProduto;

@WebServlet( urlPatterns = {"/ServletProdutoController"}) //Array de mapeamento
public class ServletProdutoController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	

	private DAOProduto daoProduto = new DAOProduto();

	public ServletProdutoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");

			// DELETAR - Com JavaScript
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			    String idProd = request.getParameter("id");
			    daoProduto.deletarProd(idProd);
			    
			    // Lista a tabela do DB após alteração
			    List<ModelProduto> modelProdutos = daoProduto.consultaProdListReport();
			    
			    request.setAttribute("modelProdutos", modelProdutos);
			    request.setAttribute("msg", "Registro excluído com sucesso!");                              
			    request.getRequestDispatcher("principal/produto.jsp").forward(request, response);
			}

			
			// Início Modal
			// BOTÃO PESQUISAR - gera a table do modal
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarProdAjax")) {
			    String prodBusca = request.getParameter("prodBusca");
			
			        List<ModelProduto> dadosJsonProd = daoProduto.consultaProdutoList(prodBusca);

			        ObjectMapper mapper = new ObjectMapper();
			        String json = mapper.writeValueAsString(dadosJsonProd);
			        
			        response.getWriter().write(json);			   
			}


			// Edita no Form Cadastro de produto
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
			    String id = request.getParameter("id");

			    ModelProduto modelProduto = daoProduto.consultaProdutoID(id);

			    List<ModelProduto> modelProdutos = daoProduto.consultaProdListReport();
			    
			    request.setAttribute("modelProdutos", modelProdutos);
			    request.setAttribute("msg", "Editando Produto");
			    request.setAttribute("modelProduto", modelProduto);
			    request.getRequestDispatcher("principal/produto.jsp").forward(request, response);
			}
			// Fim Modal

			
			//listar todos produtos cadastrados no DB - Tela Cadastro de Produtos - "LIST<>"
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarProd")) {
				
				 List<ModelProduto> modelProdutos = daoProduto.consultaProdListReport();
				
				request.setAttribute("msg", "Produtos Cadastrados");
				request.setAttribute("modelProdutos", modelProdutos);
				request.getRequestDispatcher("principal/produto.jsp").forward(request, response);
			}	
			
			// Relatório completo
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioProd")) {
			    
			    // Use diretamente a consulta para obter a lista de produtos - "LIST<>"
			    request.setAttribute("listaProd", daoProduto.consultaProdListReport());
			    
			    // Ajustar redirecionamento para a página JSP
			    request.getRequestDispatcher("principal/rel-prod.jsp").forward(request, response);
			}
			
			// Chama Query
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("queryProd")) {
			    // Ajustar redirecionamento para a página JSP
			    request.getRequestDispatcher("principal/query-prod.jsp").forward(request, response);
			}
			
			
			else {
				//Lista Table do DB após alteração - "LIST<> ORDER BY produto"
				List<ModelProduto> modelProdutos = daoProduto.consultaProdListReport();
				
				request.setAttribute("modelProdutos", modelProdutos);
				request.getRequestDispatcher("principal/produto.jsp").forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(" ==> Erro ao tentar Excluir Produto <==\n--> Ver [Class ServletProdutoController/doGet]  <--"
							+ "\n--> ou [Class DAOProduto/deletarProd] <--\n" + e);
			
			// envia o erro para página de erro
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Vindo do form [produto.jsp]
		try {
			String msg = "Cadastro realizado com sucesso!";

			String id = request.getParameter("id");
			String categoria = request.getParameter("categoria");
			String produto = request.getParameter("produto");
			String tipo = request.getParameter("tipo");
			String vlcompra = request.getParameter("vlcompra");
			String icms = request.getParameter("icms");
			String vlvenda = request.getParameter("vlvenda");
			
			//Conversões
			BigDecimal vlCompr = new BigDecimal(vlcompra);
			BigDecimal icmsStr = new BigDecimal(icms);
			BigDecimal vlVend = new BigDecimal(vlvenda);
			
			// convertendo para Long: Se "id" for diferente de nulo e não estiver em branco,
			// converte-> senão define como null
			ModelProduto modelProduto = new ModelProduto();
			modelProduto.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelProduto.setCategoria(categoria);
			modelProduto.setProduto(produto);
			modelProduto.setTipo(tipo);			
			modelProduto.setVlcompra(vlCompr);			
			modelProduto.setIcms(icmsStr);			
			modelProduto.setVlvenda(vlVend);			
						
			
			// Não permite produto duplicado
			boolean nomeProdutoExistente = daoProduto.validarNomeProduto(modelProduto.getProduto());
			// Mostra no console se o produto já existe
			System.out.println("Produto já existe: " + nomeProdutoExistente);
			System.out.println("ID do Produto: " + modelProduto.getId());

			// Existir envia a mensagem
			if (nomeProdutoExistente && modelProduto.getId() == null) {
			    msg = "Já existe um produto com o mesmo nome. Informe outro nome!";// Sobrepondo mensagem padrão
			}
			// Se não existir prossegue o cadastro
			else {
			    if (modelProduto.isNovo()) {
			        msg = "Produto cadastrado com sucesso!";
			    } else {
			        msg = "Produto Atualizado com sucesso!";
			    }

			    // daoUsuario.gravarProd(modelProduto); //Grava no DB
			    modelProduto = daoProduto.gravarProd(modelProduto);
			}		
			
			
			//Lista Table do DB após alteração - "LIST<> com LIKE"
			//List<ModelProduto> modelProdutos = daoProduto.consultaProdutoList(produto);
			
			//Lista Table do DB após alteração - "LIST<> ORDER BY produto"
			List<ModelProduto> modelProdutos = daoProduto.consultaProdListReport();
			request.setAttribute("modelProdutos", modelProdutos);

			request.setAttribute("msg", msg);
			request.setAttribute("modelProduto", modelProduto); // Seta os valores do objeto

			// Rediciona
			request.getRequestDispatcher("principal/produto.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println(
					" ==> Erro de Cadastro de Produto <==\n--> Ver [Class ServletProdutoController/DoPost]  <--"
							+ "\n--> ou [Class DAOProduto/gravarProd] <--\n" + e);
			
			// envia o erro para página de erro
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
