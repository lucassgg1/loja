package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelOrcamento;

public class DAOOrcamento {
	private Connection connection;
	String sql;
	PreparedStatement statement;
	ResultSet resultSet;

	// Construtor - pegar a conexão
	public DAOOrcamento() {
		connection = SingleConnectionBanco.getConnection();
	}

	// Adiciona o produto
	public ModelOrcamento gravarOrc(ModelOrcamento objeto) throws SQLException {
		sql = "INSERT INTO orcamento(nome, email, telefone, orcamento) VALUES(?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getEmail());
			statement.setString(3, objeto.getTelefone());
			statement.setString(4, objeto.getOrcamento());

			statement.execute();

			connection.commit();

		} catch (SQLException e) {
			System.out.println("Erro no método Insert - DAOOrcamento\n" + e);
		}

		// Chama consultaProduto para retornar o orçamento salvo
		return this.consultaOrcamento(objeto.getNome());
	}

	// Retorna query baseada nome do solitante do orcamento
	public ModelOrcamento consultaOrcamento(String nome) throws SQLException {
		sql = "SELECT * FROM orcamento WHERE UPPER(nome) = UPPER(?)";
		statement = connection.prepareStatement(sql);
		statement.setString(1, nome);

		resultSet = statement.executeQuery();

		ModelOrcamento modelOrcamento = new ModelOrcamento();
		while (resultSet.next()) {
			modelOrcamento.setId(resultSet.getLong("id"));
			modelOrcamento.setNome(resultSet.getString("nome"));
			modelOrcamento.setEmail(resultSet.getString("email"));
			modelOrcamento.setTelefone(resultSet.getString("telefone"));
			modelOrcamento.setOrcamento(resultSet.getString("orcamento"));
		}
		return modelOrcamento;
	}
	
	// Relatorio - lista table atualizada - No Menu Relatório do ADMIN
		public List<ModelOrcamento> consultaOrcListReport() throws SQLException {
		    List<ModelOrcamento> returnReport = new ArrayList<>();
		    String sql = "SELECT * FROM orcamento ORDER BY nome";

		    try (PreparedStatement statement = connection.prepareStatement(sql);
		         ResultSet resultSet = statement.executeQuery()) {
		        

		        while (resultSet.next()) {
		            ModelOrcamento modelOrcamento = new ModelOrcamento(); // Instanciar um novo objeto dentro do loop
		            modelOrcamento.setId(resultSet.getLong("id"));
					modelOrcamento.setNome(resultSet.getString("nome"));
					modelOrcamento.setEmail(resultSet.getString("email"));
					modelOrcamento.setTelefone(resultSet.getString("telefone"));
					modelOrcamento.setOrcamento(resultSet.getString("orcamento"));

		            returnReport.add(modelOrcamento);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new SQLException("-> Erro no Método 'consultaOrcListReport/DAOOrcamento' ao executar consulta: " + e.getMessage() + " <--\n");
		    }
		    
		    return returnReport;
		}

}
