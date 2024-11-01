package br.com.dlei.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.dlei.dao.util.Conexao;
import br.com.dlei.model.Papel;
import br.com.dlei.model.User;

public class UserDAO {

	private Connection connection;

	private void conectar() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = Conexao.getConexao();
		}
	}

	private void desconectar() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}

	public User insertUser(User usuario) throws SQLException {
		String sql = "INSERT INTO usuario (nome, cpf, dataNascimento, email, login, password, ativo,"
				+ " telefone, endereco, numEndereco, complEndereco, bairro, cidade, estado, uf, cep)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		conectar();

		// [RETURN_GENERATED_KEYS] retorna a sequência do Id do DB
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getCpf());
		Date nascimento = new Date(usuario.getDataNascimento().getTime());
		statement.setDate(3, nascimento);
		statement.setString(4, usuario.getEmail());
		statement.setString(5, usuario.getLogin());
		statement.setString(6, usuario.getPassword());
		statement.setBoolean(7, usuario.isAtivo());
		statement.setString(8, usuario.getTelefone());
		statement.setString(9, usuario.getEndereco());
		statement.setString(10, usuario.getNumEndereco());
		statement.setString(11, usuario.getComplEndereco());
		statement.setString(12, usuario.getBairro());
		statement.setString(13, usuario.getCidade());
		statement.setString(14, usuario.getEstado());
		statement.setString(15, usuario.getUf());
		statement.setString(16, usuario.getCep());

		statement.executeUpdate();

		// Gera a sequência do ID
		ResultSet resultSet = statement.getGeneratedKeys();
		long id = 0;
		if (resultSet.next())
			id = resultSet.getInt("id");
		statement.close();
		
		desconectar();

		usuario.setId(id); // Associa o usuário ao "id"
		return usuario;
	}

	public List<User> listUsersAll() throws SQLException {
		PapelDAO papelDAO = new PapelDAO();
		List<User> usersList = new ArrayList<User>();

		String sql = "SELECT * FROM usuario";

		conectar();
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			long id = resultSet.getLong("id");
			String nome = resultSet.getString("nome");
			String cpf = resultSet.getString("cpf");
			Date dataNasc = new Date(resultSet.getDate("dataNascimento").getTime());
			String email = resultSet.getString("email");
			String login = resultSet.getString("login");
			String password = resultSet.getString("password");			
			boolean ativo = resultSet.getBoolean("ativo");
			String telefone = resultSet.getString("telefone");
			String endereco = resultSet.getString("endereco");
			String numEndereco = resultSet.getString("numEndereco");
			String complEndereco = resultSet.getString("complEndereco");
			String bairro = resultSet.getString("bairro");
			String cidade = resultSet.getString("cidade");
			String estado = resultSet.getString("estado");
			String uf = resultSet.getString("uf");
			String cep = resultSet.getString("cep");

			// Objeto User [classe model/User.java]
			User usuario = new User(nome, cpf, dataNasc, email, login, password, ativo, telefone, endereco, numEndereco,
					complEndereco, bairro, cidade, estado, uf, cep);
			usuario.setId(id); // Associa o usuário ao "id"
			
			// chama lista de papéis [classe model/Papel.java]
			List<Papel> papeis = papelDAO.buscarPapelUsuario(usuario);			
			usuario.setPapeis(papeis); // Associa o usuário "ao papel"
						
			usersList.add(usuario); //adiciona o usuario a lista, já com o papel
		}
		resultSet.close();
		statement.close();
		
		desconectar();

		return usersList; //retorna para o Array
	}

	public boolean userRemove(User usuario) throws SQLException {
		String sql = "DELETE FROM usuario WHERE id = ?";

		conectar();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, usuario.getId());

		boolean linhaApagada = statement.executeUpdate() > 0;
		statement.close();
		
		desconectar();

		return linhaApagada;
	}

	// aula 14
	public User buscarUsuarioPorId(long id) throws SQLException {
		PapelDAO papelDao = new PapelDAO();
		User usuario = null;
		String sql = "SELECT * FROM usuario WHERE id = ?";

		conectar();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String nome = resultSet.getString("nome");
			String cpf = resultSet.getString("cpf");
			Date nascimento = new Date(resultSet.getDate("dataNascimento").getTime());
			String email = resultSet.getString("email");
			String login = resultSet.getString("login");
			String password = resultSet.getString("password");			
			boolean ativo = resultSet.getBoolean("ativo");
			String telefone = resultSet.getString("telefone");
			String endereco = resultSet.getString("endereco");
			String numEndereco = resultSet.getString("numEndereco");
			String complEndereco = resultSet.getString("complEndereco");
			String bairro = resultSet.getString("bairro");
			String cidade = resultSet.getString("cidade");
			String estado = resultSet.getString("estado");
			String uf = resultSet.getString("uf");
			String cep = resultSet.getString("cep");

			// Objeto User [classe model/User.java]
			usuario = new User(nome, cpf, nascimento, email, login, password, ativo, telefone, endereco, numEndereco,
					complEndereco, bairro, cidade, estado, uf, cep);
			usuario.setId(id); // Associa o usuário ao "id"
			// chama lista de papéis [classe model/Papel.java]
			List<Papel> papeisUsuario = papelDao.buscarPapelUsuario(usuario);			
			usuario.setPapeis(papeisUsuario); // Associa o usuário ao "papel"
		}
		
		resultSet.close();
		statement.close();
		
		desconectar();
		
		return usuario;
	}

	public boolean editarUsuario(User usuario) throws SQLException {
		String sql = "UPDATE usuario SET nome = ?, cpf = ?, dataNascimento = ?, email = ?, login = ?, password = ?, ativo = ?,"
				+ " telefone = ?, endereco = ?, numEndereco = ?, complEndereco = ?, bairro = ?, cidade = ?, estado = ?,"
				+ "uf = ?, cep = ? WHERE id = ?";

		conectar();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getCpf());
		statement.setDate(3, new java.sql.Date(usuario.getDataNascimento().getTime()));
		statement.setString(4, usuario.getEmail());		
		statement.setString(5, usuario.getLogin());
		statement.setString(6, usuario.getPassword());
		statement.setBoolean(7, usuario.isAtivo());
		statement.setString(8, usuario.getTelefone());
		statement.setString(9, usuario.getEndereco());
		statement.setString(10, usuario.getNumEndereco());
		statement.setString(11, usuario.getComplEndereco());
		statement.setString(12, usuario.getBairro());
		statement.setString(13, usuario.getCidade());
		statement.setString(14, usuario.getEstado());
		statement.setString(15, usuario.getUf());
		statement.setString(16, usuario.getCep());
		statement.setLong(17, usuario.getId());

		boolean linhaAlterada = statement.executeUpdate() > 0;
		statement.close();

		desconectar();
		
		return linhaAlterada;
	}
	
		public User buscarUsuarioPorLogin(String login) throws SQLException {
			PapelDAO papelDao = new PapelDAO();
	        User usuario = null;
	        String sql = "SELECT * FROM usuario WHERE login = ?";
	         
	        conectar();	   
	        
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, login);
	         
	        ResultSet resultSet = statement.executeQuery();
	         
	        if (resultSet.next()) {
	        	long id = resultSet.getLong("id");
	        	String nome = resultSet.getString("nome");
				String cpf = resultSet.getString("cpf");
				Date nascimento = new Date(resultSet.getDate("dataNascimento").getTime());
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				boolean ativo = resultSet.getBoolean("ativo");
				String telefone = resultSet.getString("telefone");
				String endereco = resultSet.getString("endereco");
				String numEndereco = resultSet.getString("numEndereco");
				String complEndereco = resultSet.getString("complEndereco");
				String bairro = resultSet.getString("bairro");
				String cidade = resultSet.getString("cidade");
				String estado = resultSet.getString("estado");
				String uf = resultSet.getString("uf");
				String cep = resultSet.getString("cep");

				// Objeto User [classe model/User.java]
				usuario = new User(nome, cpf, nascimento, email, login, password, ativo, telefone, endereco, numEndereco,
						complEndereco, bairro, cidade, estado, uf, cep);
				usuario.setId(id); // Associa o usuário ao "id"
				// chama lista de papéis [classe model/Papel.java]
				List<Papel> papeisUsuario = papelDao.buscarPapelUsuario(usuario);				
				usuario.setPapeis(papeisUsuario);// Associa o usuário "ao papel"
			}
	         
	        resultSet.close();
	        statement.close();
	        
	        desconectar();
	        
	        return usuario; 
	    }
}