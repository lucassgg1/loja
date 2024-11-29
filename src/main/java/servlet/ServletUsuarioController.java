package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;

import model.ModelLogin;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;

@MultipartConfig //Para permitir upload
@WebServlet( urlPatterns = {"/ServletUsuarioController"}) //Array de mapeamento
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuario = new DAOUsuarioRepository();
	private ModelLogin modelLogin = new ModelLogin();

	public ServletUsuarioController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");

			// Apenas JavaScript
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				daoUsuario.deletarUser(idUser);
				
				//Lista  Table do DB após alteração
				List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("msg", "Registro excluído com sucesso!");								
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}
			//gera a table do modal
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				String nomeBusca = request.getParameter("nomeBusca");
				System.out.println(nomeBusca);

				//irá converter em Jason
				List<ModelLogin> dadosJsonUser = daoUsuario.consultaUsuarioList(nomeBusca, super.getUserLogado(request));
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				response.getWriter().write(json);				
			}
			
			// Edita no Form Cadastro de usuário
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				String id = request.getParameter("id"); 
				
				modelLogin = daoUsuario.consultaUsuarioID(id, super.getUserLogado(request));
				
				//Lista  Table do DB após alteração
				List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				
				request.setAttribute("msg", "Editando Usuário");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
			
			//listar todos users cadastrados no DB - Tela Cadastro de usuário
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioList(super.getUserLogado(request));
				
				request.setAttribute("msg", "Usuários Cadastrados");
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}		
								
			
			// Relatório
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")) {
				
				request.setAttribute("listaUser", daoUsuario.consultaUsuarioListReport());				
				
				request.getRequestDispatcher("principal/rel-user.jsp").forward(request, response);
			}			
			
			// Chama Query
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("queryUser")) {
			    request.getRequestDispatcher("principal/query-user.jsp").forward(request, response);
			}
			
			else {
				//Lista  Table do DB após alteração
				List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(" ==> Erro ao tentar Excluir Usuário <==\n--> Ver [Class ServletUsuarioController/doGet]  <--"
							+ "\n--> ou [Class DAOUsuarioRepository/deletarUser] <--\n" + e);
			
			// envia o erro para página de erro
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String msg = "Cadastro realizado com sucesso!";// Mensagem padrão - Aula 2.35

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String dataNascimento = request.getParameter("dataNascimento");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");
			String complemento = request.getParameter("complemento");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");	
			String telefone = request.getParameter("telefone");
			String cpf = request.getParameter("cpf");

			
			// convertendo para Long: Se "id" for diferente de nulo e não estiver em branco,
			// converte-> senão define como null
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			/* faz conversão da String para Date */
			modelLogin.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento))));
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);
			modelLogin.setCep(cep);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setNumero(numero);
			modelLogin.setComplemento(complemento);
			modelLogin.setBairro(bairro);
			modelLogin.setLocalidade(localidade);
			modelLogin.setUf(uf);
			modelLogin.setTelefone(telefone);
			modelLogin.setCpf(cpf);
			
			if(ServletFileUpload.isMultipartContent(request)) {				
				
				Part part = request.getPart("fileFoto"); // Pega foto da tela
				
				if(part.getSize() > 0) {
				byte[] foto = IOUtils.toByteArray(part.getInputStream()); // Convert imagem para byte
				new Base64();
				/* possibilitar o HTML entender a imagem depois*/
				String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," + Base64.toBase64String(foto);
				
				modelLogin.setFotouser(imagemBase64);
				modelLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]);
				}
			}

			// Adicionado o if/ else - Aula 2.35
			// Se já existir um login e quiser fazer novo cadastro
			if (daoUsuario.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe usuário com o mesmo login. Informe outro login!";// Sobrepondo mensagem padrão
			} else {
				if (modelLogin.isNovo()) {
					msg = "Cadastro realizado com sucesso!";
				} else {
					msg = "Cadastro Atualizado com sucesso!";
				}

				// daoUsuario.gravarUser(modelLogin); //Grava no DB
				modelLogin = daoUsuario.gravarUser(modelLogin, super.getUserLogado(request)); // Alterado na aula 2.34
			}
			
			//Lista  Table do DB após alteração
			List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioList(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);

			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin); // Seta os valores do objeto

			// Rediciona
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println(
					" ==> Erro de Cadastro de Usuário <==\n--> Ver [Class ServletUsuarioController/DoPost]  <--"
							+ "\n--> ou [Class DAOUsuarioRepository/gravarUser] <--\n" + e);
			
			// envia o erro para página de erro
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
