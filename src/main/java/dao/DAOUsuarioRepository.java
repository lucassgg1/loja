package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	// Conexão
	private Connection connection;
	String sql;
	PreparedStatement statement;
	ResultSet resultSet;

	// Construtor - pegar a conexão
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	// public void gravarUser(ModelLogin objModellogin) throws SQLException {
	public ModelLogin gravarUser(ModelLogin objeto, Long userLogado) throws SQLException { // Alterado na aula 2.34
		// if/ else - Adicionados na Aula 2.37
		if (objeto.isNovo()) {// Grava um novo
			sql = "INSERT INTO model_login(nome, email, login, senha, dataNascimento, user_id, perfil, sexo, cep, logradouro, "
					+ "numero, complemento, bairro, localidade, uf, telefone, cpf) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

			statement = connection.prepareStatement(sql);

			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getEmail());
			statement.setString(3, objeto.getLogin());
			statement.setString(4, objeto.getSenha());
			statement.setDate(5, objeto.getDataNascimento());
			statement.setLong(6, userLogado);
			statement.setString(7, objeto.getPerfil());
			statement.setString(8, objeto.getSexo());
			statement.setString(9, objeto.getCep());
			statement.setString(10, objeto.getLogradouro());
			statement.setString(11, objeto.getNumero());
			statement.setString(12, objeto.getComplemento());
			statement.setString(13, objeto.getBairro());
			statement.setString(14, objeto.getLocalidade());
			statement.setString(15, objeto.getUf());
			statement.setString(16, objeto.getTelefone());
			statement.setString(17, objeto.getCpf());

			statement.execute();

			connection.commit(); // salva os dados

			if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {// Grava foto
				sql = "UPDATE model_login SET fotouser=?, extensaofotouser=? WHERE login=?";

				statement = connection.prepareStatement(sql);

				statement.setString(1, objeto.getFotouser());
				statement.setString(2, objeto.getExtensaofotouser());
				statement.setString(3, objeto.getLogin());

				statement.execute();

				connection.commit(); // salva os dados
			}

		} else {// Atualiza o cadastro
			sql = "UPDATE model_login SET nome=?, email=?, login=?, senha=?, dataNascimento=?, perfil=?, sexo=?, cep=?, "
					+ "logradouro=?, numero=?, complemento=?, bairro=?, localidade=?, uf=?, telefone=?, cpf=?"
					+ " WHERE id = " + objeto.getId() + ";";

			statement = connection.prepareStatement(sql);

			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getEmail());
			statement.setString(3, objeto.getLogin());
			statement.setString(4, objeto.getSenha());
			statement.setDate(5, objeto.getDataNascimento());
			statement.setString(6, objeto.getPerfil());
			statement.setString(7, objeto.getSexo());
			statement.setString(8, objeto.getCep());
			statement.setString(9, objeto.getLogradouro());
			statement.setString(10, objeto.getNumero());
			statement.setString(11, objeto.getComplemento());
			statement.setString(12, objeto.getBairro());
			statement.setString(13, objeto.getLocalidade());
			statement.setString(14, objeto.getUf());
			statement.setString(15, objeto.getTelefone());
			statement.setString(16, objeto.getCpf());

			statement.executeUpdate();

			connection.commit(); // salva os dados

			if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {// Grava foto
				sql = "UPDATE model_login SET fotouser=?, extensaofotouser=? WHERE id=?";

				statement = connection.prepareStatement(sql);

				statement.setString(1, objeto.getFotouser());
				statement.setString(2, objeto.getExtensaofotouser());
				statement.setLong(3, objeto.getId());

				statement.execute();

				connection.commit(); // salva os dados
			}
		}

		// Após salvar já retorna a consulta
		return this.consultaUsuario(objeto.getLogin(), userLogado);
	}
	
	// Relatorio com data
	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws SQLException {
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		// Query não lista o Admin.
		sql = "SELECT * FROM model_login WHERE useradmin IS false AND user_id = " + userLogado;
		
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();

		while (resultSet.next()) {// Se tem resultado
			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setTelefone(resultSet.getString("telefone"));
			modelLogin.setCpf(resultSet.getString("cpf"));

			retorno.add(modelLogin);
		}

		return retorno;
	}
	
	//Relatório - menu Relatório
	public List<ModelLogin> consultaUsuarioListReport() throws SQLException {
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		// Query não lista o Admin.
		String sql = "SELECT * FROM model_login WHERE useradmin IS false ORDER BY nome;";
		statement = connection.prepareStatement(sql);

		resultSet = statement.executeQuery();

		while (resultSet.next()) {// Se tem resultado
			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setTelefone(resultSet.getString("telefone"));
			modelLogin.setCpf(resultSet.getString("cpf"));
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setComplemento(resultSet.getString("complemento"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));

			retorno.add(modelLogin);
		}

		return retorno;
	}
		
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws SQLException {
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		// Consulta digitando apenas parte dos dados (nome)
		// Query não lista o Admin.
		sql = "SELECT * FROM model_login WHERE TRANSLATE(UPPER(nome), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÇ', 'AEIOUAEIOUAEIOUAOC') "
				+ "LIKE TRANSLATE(UPPER(?), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÇ', 'AEIOUAEIOUAEIOUAOC') AND useradmin IS false AND user_id = ?";

		
		statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);

		resultSet = statement.executeQuery();

		while (resultSet.next()) {// Se tem resultado
			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	public ModelLogin consultaUsuarioLogado(String login) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();// Instancia/ chama [Class ModelLogin]

		sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER('" + login + "')";
		statement = connection.prepareStatement(sql);

		resultSet = statement.executeQuery();

		while (resultSet.next()) {// Se tem resultado
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setDataNascimento(resultSet.getDate("dataNascimento"));
			modelLogin.setUseradmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setComplemento(resultSet.getString("complemento"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setTelefone(resultSet.getString("telefone"));
			modelLogin.setCpf(resultSet.getString("cpf"));
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuario(String login) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();// Instancia/ chama [Class ModelLogin]

		sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER('" + login + "') AND useradmin IS false";
		statement = connection.prepareStatement(sql);

		resultSet = statement.executeQuery();

		while (resultSet.next()) {// Se tem resultado
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setDataNascimento(resultSet.getDate("dataNascimento"));
			modelLogin.setUseradmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setComplemento(resultSet.getString("complemento"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setTelefone(resultSet.getString("telefone"));
			modelLogin.setCpf(resultSet.getString("cpf"));
		}

		return modelLogin;
	}

	// Consulta por login
	// Query não lista o Admin
	public ModelLogin consultaUsuario(String login, Long userLogado) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();// Instancia/ chama [Class ModelLogin]

		sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER('" + login
				+ "') AND useradmin IS false AND user_id = " + userLogado;
		statement = connection.prepareStatement(sql);

		resultSet = statement.executeQuery();

		while (resultSet.next()) {// Se tem resultado
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setDataNascimento(resultSet.getDate("dataNascimento"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setComplemento(resultSet.getString("complemento"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setTelefone(resultSet.getString("telefone"));
			modelLogin.setCpf(resultSet.getString("cpf"));
		}

		return modelLogin;

	}

	// Consulta por ID
	public ModelLogin consultaUsuarioID(String id, Long userLogado) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();// Instancia/ chama [Class ModelLogin]
		// Query não lista o Admin
		sql = "SELECT * FROM model_login WHERE id = ? AND useradmin IS false AND user_id = ?";

		statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, userLogado);

		resultSet = statement.executeQuery();

		while (resultSet.next()) {// Se tem resultado
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setDataNascimento(resultSet.getDate("dataNascimento"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setComplemento(resultSet.getString("complemento"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setTelefone(resultSet.getString("telefone"));
			modelLogin.setCpf(resultSet.getString("cpf"));
		}

		return modelLogin;

	}

	public boolean validarLogin(String login) throws Exception {
		sql = "SELECT COUNT(1) > 0 AS existe FROM model_login WHERE UPPER(login) = UPPER('" + login + "')";
		statement = connection.prepareStatement(sql);

		resultSet = statement.executeQuery();

		resultSet.next();// Para entrar nos resultados do sql
		return resultSet.getBoolean("existe");

	}

	public void deletarUser(String idUser) throws SQLException {
		// Não deixa apagar o Admin
		sql = "DELETE FROM model_login WHERE id = ? AND useradmin IS false";

		statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(idUser)); // Converte de String para Long
		statement.executeUpdate();

		connection.commit();
	}
}
