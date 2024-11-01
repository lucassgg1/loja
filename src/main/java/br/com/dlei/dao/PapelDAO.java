package br.com.dlei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dlei.dao.util.Conexao;
import br.com.dlei.model.Papel;
import br.com.dlei.model.User;

public class PapelDAO {

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

	// define o relacionamento
	public void atribuirPapelUsuario(Papel papel, User usuario) throws SQLException {
		String sql = "INSERT INTO usuario_papel (usuario_id, papel_id)" + " VALUES (?, ?)";

		conectar();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, usuario.getId());
		statement.setLong(2, papel.getId());

		statement.executeUpdate();
		statement.close();
		
		desconectar();
	}

	public Papel buscarPapelPorTipo(String tipo) throws SQLException {
		Papel papel = null;
		String sql = "SELECT * FROM papel WHERE tipo_papel LIKE ?";

		conectar();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, tipo);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			long id = resultSet.getLong("id");
			String tipoPapel = resultSet.getString("tipo_papel");

			papel = new Papel(id, tipoPapel);
		}

		resultSet.close();
		statement.close();
		
		desconectar();

		return papel;
	}

	// consulta com mais de uma table
	public List<Papel> buscarPapelUsuario(User usuario) throws SQLException {
		List<Papel> listaPapeis = new ArrayList<Papel>();
		String sql = "SELECT papel.id, papel.tipo_papel FROM papel "
				+ " JOIN usuario_papel ON usuario_papel.papel_id = papel.id "
				+ " JOIN usuario ON usuario.id = usuario_papel.usuario_id "
				+ " WHERE usuario.id = ?";

		conectar();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, usuario.getId());
		
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			long id = resultSet.getLong("id");
			String tipoPapel = resultSet.getString("tipo_papel");

			Papel papel = new Papel(id, tipoPapel);
			listaPapeis.add(papel);
		}

		resultSet.close();
		statement.close();
		
		desconectar();

		return listaPapeis;
	}

	public List<Papel> buscarTodosPapeis() throws SQLException {
		List<Papel> listaPapeis = new ArrayList<Papel>();
		String sql = "SELECT * FROM papel";

		conectar();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			long id = resultSet.getLong("id");
			String tipoPapel = resultSet.getString("tipo_papel");

			Papel papel = new Papel(id, tipoPapel);
			listaPapeis.add(papel);
		}

		resultSet.close();
		statement.close();
		
		desconectar();

		return listaPapeis;
	}

	public void apagarPapeisUsuario(User usuario) throws SQLException {
		String sql = "DELETE FROM usuario_papel WHERE usuario_id=?";

		conectar();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, usuario.getId());

		statement.executeUpdate();
		statement.close();
		
		desconectar();
	}

}
