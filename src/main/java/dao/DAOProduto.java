package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelProduto;

public class DAOProduto {

	// Conexão
	private Connection connection;
	String sql;
	PreparedStatement statement;
	ResultSet resultSet;

	// Construtor - pegar a conexão
	public DAOProduto() {
		connection = SingleConnectionBanco.getConnection();
	}

	// OK
	// Adiciona o produto
	public ModelProduto gravarProd(ModelProduto objeto) throws SQLException {
		String sql;
		if (objeto.isNovo()) { // Grava um novo
			sql = "INSERT INTO produto(categoria, produto, tipo, vlcompra, icms, vlvenda) VALUES(?, ?, ?, ?, ?, ?)";
		} else { // Atualiza o produto
			sql = "UPDATE produto SET categoria=?, produto=?, tipo=?, vlcompra=?, icms=?, vlvenda=? WHERE id=?";
		}

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, objeto.getCategoria());
			statement.setString(2, objeto.getProduto());
			statement.setString(3, objeto.getTipo());
			statement.setBigDecimal(4, objeto.getVlcompra());
			statement.setBigDecimal(5, objeto.getIcms());
			statement.setBigDecimal(6, objeto.getVlvenda());

			if (!objeto.isNovo()) {
				statement.setLong(7, objeto.getId());
			}

			if (objeto.isNovo()) {
				statement.execute();
			} else {
				statement.executeUpdate();
			}

			connection.commit();
		} catch (SQLException e) {
			System.out.println("Erro no método Insert/ Update - DAOProduto\n" + e);
		}

		// Chama consultaProduto para retornar o produto salvo ou atualizado
		return this.consultaProduto(objeto.getProduto());
	}

	// Relatorio - lista table atualizada - no form Produto
	public List<ModelProduto> consultaProdListReport() throws SQLException {
		List<ModelProduto> returnReport = new ArrayList<ModelProduto>();
		sql = "SELECT * FROM produto ORDER BY produto";

		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();		

		while (resultSet.next()) {
			ModelProduto modelProduto = new ModelProduto(); // Instanciar um novo objeto dentro do loop
			modelProduto.setId(resultSet.getLong("id"));
			modelProduto.setCategoria(resultSet.getString("categoria"));
			modelProduto.setProduto(resultSet.getString("produto"));
			modelProduto.setTipo(resultSet.getString("tipo"));
			modelProduto.setVlcompra(resultSet.getBigDecimal("vlcompra"));
			modelProduto.setIcms(resultSet.getBigDecimal("icms"));
			modelProduto.setVlvenda(resultSet.getBigDecimal("vlvenda"));

			returnReport.add(modelProduto);
		}
		return returnReport;
	}

	// Query mostrada na atualização dos campos no Form Cadastro de produto - TESTAR
	public ModelProduto consultaProdFiltradoPorNome(String produto) throws SQLException {
		ModelProduto modelProduto = null;
		String sql = "SELECT * FROM produto"; // LIMIT 1 para garantir um único resultado
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			modelProduto = new ModelProduto();
			modelProduto.setId(resultSet.getLong("id"));
			modelProduto.setCategoria(resultSet.getString("categoria"));
			modelProduto.setProduto(resultSet.getString("produto"));
			modelProduto.setTipo(resultSet.getString("tipo"));
			modelProduto.setVlcompra(resultSet.getBigDecimal("vlcompra"));
			modelProduto.setIcms(resultSet.getBigDecimal("icms"));
			modelProduto.setVlvenda(resultSet.getBigDecimal("vlvenda"));
		}

		return modelProduto;
	}

	// ok
	// Consulta digitando apenas parte dos dados (produto) - Botão pesquisar/ Modal
	public List<ModelProduto> consultaProdutoList(String produto) throws SQLException {
		List<ModelProduto> queryProdList = new ArrayList<ModelProduto>();

		sql = "SELECT * FROM produto WHERE TRANSLATE(UPPER(produto), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÇ', 'AEIOUAEIOUAEIOUAOC')"
				+ " LIKE TRANSLATE(UPPER(?), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÇ', 'AEIOUAEIOUAEIOUAOC')";


		statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + produto + "%");

		resultSet = statement.executeQuery();

		while (resultSet.next()) {
			ModelProduto modelProduto = new ModelProduto(); // Instanciar um novo objeto dentro do loop
			modelProduto.setId(resultSet.getLong("id"));
			modelProduto.setCategoria(resultSet.getString("categoria"));
			modelProduto.setProduto(resultSet.getString("produto"));
			modelProduto.setTipo(resultSet.getString("tipo"));
			modelProduto.setVlcompra(resultSet.getBigDecimal("vlcompra"));
			modelProduto.setIcms(resultSet.getBigDecimal("icms"));
			modelProduto.setVlvenda(resultSet.getBigDecimal("vlvenda"));

			queryProdList.add(modelProduto);
		}

		return queryProdList;
	}

	// ok
	// Retorna query baseada nome do produto
	public ModelProduto consultaProduto(String produto) throws SQLException {
		String sql = "SELECT * FROM produto WHERE UPPER(produto) = UPPER(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, produto);

		ResultSet resultSet = statement.executeQuery();

		ModelProduto modelProduto = new ModelProduto();
		while (resultSet.next()) {
			modelProduto.setId(resultSet.getLong("id"));
			modelProduto.setCategoria(resultSet.getString("categoria"));
			modelProduto.setProduto(resultSet.getString("produto"));
			modelProduto.setTipo(resultSet.getString("tipo"));
			modelProduto.setVlcompra(resultSet.getBigDecimal("vlcompra"));
			modelProduto.setIcms(resultSet.getBigDecimal("icms"));
			modelProduto.setVlvenda(resultSet.getBigDecimal("vlvenda"));
		}
		return modelProduto;
	}

	// ok
	// Consulta por ID
	public ModelProduto consultaProdutoID(String id) throws SQLException {
		ModelProduto modelProduto = new ModelProduto();
		sql = "SELECT * FROM produto WHERE id = ? ORDER BY id";

		statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));

		resultSet = statement.executeQuery();

		if (resultSet.next()) {
			modelProduto.setId(resultSet.getLong("id"));
			modelProduto.setCategoria(resultSet.getString("categoria"));
			modelProduto.setProduto(resultSet.getString("produto"));
			modelProduto.setTipo(resultSet.getString("tipo"));
			modelProduto.setVlcompra(resultSet.getBigDecimal("vlcompra"));
			modelProduto.setIcms(resultSet.getBigDecimal("icms"));
			modelProduto.setVlvenda(resultSet.getBigDecimal("vlvenda"));
		}
		return modelProduto;
	}

	// Verificação duplicação no nome do produto
	public boolean validarNomeProduto(String produto) throws Exception {
		String sql = "SELECT COUNT(1) > 0 AS existe FROM produto WHERE TRIM(UPPER(produto)) = TRIM(UPPER(?))";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, produto);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					boolean existe = resultSet.getBoolean("existe");
					System.out.println("Validação de produto: " + produto + " - Existe: " + existe);
					return existe;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao validar nome do produto: " + e.getMessage());
		}
		return false;
	}

	/*
	 * public boolean validarNomeProduto(String produto) throws Exception { sql =
	 * "SELECT COUNT(1) > 0 AS existe FROM produto WHERE produto = UPPER('" +
	 * produto + "')"; statement = connection.prepareStatement(sql);
	 * 
	 * resultSet = statement.executeQuery();
	 * 
	 * resultSet.next();// Para entrar nos resultados do sql return
	 * resultSet.getBoolean("existe"); }
	 */

	// Deleta produto
	public void deletarProd(String id) throws SQLException {
		try {
			sql = "DELETE FROM produto WHERE id = ?";

			statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id)); // Converte de String para Long
			statement.executeUpdate();

			connection.commit();
		} catch (Exception e) {
			System.out.println("erro no método deletarProd - Class DAOProduto\n" + e);
		}
	}
}
