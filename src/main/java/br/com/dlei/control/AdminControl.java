package br.com.dlei.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dlei.dao.PapelDAO;
import br.com.dlei.dao.UserDAO;
import br.com.dlei.model.Papel;
import br.com.dlei.model.User;

@WebServlet("/auth/admin")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDao;
	private PapelDAO papelDao;

	public AdminControl() {
		super();
	}

	// primeiros métodos a serem chamados
	public void init() {
		userDao = new UserDAO();
		papelDao = new PapelDAO();
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
			case "homeAdm":
				adminHome(request, response);
				break;
			case "list":
				userList(request, response);
				break;
			case "remove":
				userRemove(request, response);
				break;
			case "edit":
				userEdit(request, response);
				break;
			case "iniciarEditarPapel":
				iniciarEdicaoPapel(request, response);
				break;
			case "editarPapel":
				edicaoPapel(request, response);// aula 15
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void adminHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// define para qual página que será redirecionada
		String path = request.getServletPath() + "/admin-index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
		dispatcher.forward(request, response);
	}
	
	private void userList(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		// gera a lista vindo do DB
		List<User> users = userDao.listUsersAll();

		request.setAttribute("usersList", users);

		// chama a página [admin-user-list.jsp]
		String path = request.getServletPath() + "/admin-user-list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);

		dispatcher.forward(request, response);
	}

	private void userRemove(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		// converte String para Long
		long id = Long.parseLong(request.getParameter("id"));

		User usuario = new User();
		usuario.setId(id);
		userDao.userRemove(usuario);

		// após exclusão, atualiza a página
		String path = request.getContextPath() + request.getServletPath() + "?action=list";
		response.sendRedirect(path);
	}

	private void userEdit(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		// converte String para Long
		long id = Long.parseLong(request.getParameter("id"));

		User usuario = new User();
		usuario.setId(id);
		
		userDao.editarUsuario(usuario);

		// após edição, atualiza a página
		String path = request.getContextPath() + request.getServletPath() + "?action=list";
		response.sendRedirect(path);
	}
	
	// aula 14 - atualizado na aula 19
	private void iniciarEdicaoPapel(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// converte String
		int id = Integer.parseInt(request.getParameter("id"));
		User usuario = userDao.buscarUsuarioPorId(id);

		// gera a lista vindo do DB
		List<Papel> papeisUuario = papelDao.buscarPapelUsuario(usuario); //aula 19
		usuario.setPapeis(papeisUuario); //aula 19
		request.setAttribute("usuario", usuario);
		
		List<Papel> papeis = papelDao.buscarTodosPapeis();
		request.setAttribute("listaPapeis", papeis);

		// direciona para página abaixo
		String path = request.getServletPath() + "/admin-papel-usuario.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);

		dispatcher.forward(request, response);
	}

	private void edicaoPapel(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User usuario = userDao.buscarUsuarioPorId(id);

		boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
		usuario.setAtivo(ativo);
		userDao.editarUsuario(usuario);

		String[] idsPapeis = request.getParameterValues("pps");
		papelDao.apagarPapeisUsuario(usuario);

		for (int i = 0; i < idsPapeis.length; i++) { // idsPapeis = [1,2]
			Papel papel = new Papel();
			long idPapel = Long.valueOf(idsPapeis[i]); // idPapel = 2
			papel.setId(idPapel);
			papelDao.atribuirPapelUsuario(papel, usuario);
		}

		// http://localhost:8082/dlei-4cats-dev/auth/admin?action=list
		String path = request.getContextPath() + request.getServletPath() + "?action=list";
		response.sendRedirect(path);
	}

}
