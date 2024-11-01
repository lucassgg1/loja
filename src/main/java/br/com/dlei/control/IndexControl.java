package br.com.dlei.control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dlei.control.util.DataManipulation;
import br.com.dlei.dao.PapelDAO;
import br.com.dlei.dao.UserDAO;
import br.com.dlei.model.Papel;
import br.com.dlei.model.User;
import br.com.dlei.security.Encryption;

@WebServlet("/public")
public class IndexControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDao;
	private PapelDAO papelDao;

	public IndexControl() {
		super();
	}

	// primeiros métodos a serem chamados
	public void init() {
		userDao = new UserDAO();
		papelDao = new PapelDAO();
	}

	// requisição de link
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processarRequisicao(request, response);
	}

	// requisição de form
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processarRequisicao(request, response);
	}

	private void processarRequisicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "new":
				newUser(request, response);
				break;

			case "insert":
				saveUser(request, response);
				break;
				
			case "edit":
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}

	private void newUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// define para qual página que será redirecionada
		RequestDispatcher dispatcher = request.getRequestDispatcher("public/public-new-user.jsp");
		dispatcher.forward(request, response);
	}	
	
	private void saveUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String data = request.getParameter("nascimento");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String telefone = request.getParameter("telefone");
		String endereco = request.getParameter("endereco");
		String numEndereco = request.getParameter("numEndereco");
		String complEndereco = request.getParameter("complEndereco");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String uf = request.getParameter("uf");
		String cep = request.getParameter("cep");

		DataManipulation dateManipul = new DataManipulation();
		// faz a conversão de String para date, muda o formato [dateManipul]
		Date dataNasc = dateManipul.converterStringDate(data);

		//Faz a encriptação da senha - Via classe "Encryption"
		String passwordEncr = Encryption.converterParaMD5(password);
		
		// Objeto User
		//define o ESTADO INICIAL de cadastro do Usuário(true/ false - Ativo/ Inativo)
		User usuario = new User(nome, cpf, dataNasc, email, login, passwordEncr, false, telefone, endereco, numEndereco,
				complEndereco, bairro, cidade, estado, uf, cep);	
		
		
		User recordUser = userDao.insertUser(usuario); //armazena o usuario que foi gravado no DB
		
		//busca o papel padrão para ATRIBUIR ao usuário CADASTRADO [model.Papel]
		Papel papel = papelDao.buscarPapelPorTipo("USER");
		
		papelDao.atribuirPapelUsuario(papel, recordUser); //atribui papel padrão ao usuário recém gravado		
		
		// define para qual página que será redirecionada [public-new-user.jsp]
		RequestDispatcher dispatcher = request.getRequestDispatcher("public/public-new-user.jsp");
		//retorna a mensagem da página de cadastro [public-new-user.jsp]
		request.setAttribute("mensagem", "Usuário cadastrado com sucesso");
		dispatcher.forward(request, response);
	}

}
