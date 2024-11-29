package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

//aula 2.17
public class DAOLoginRepository {

	//Conexão
	private Connection connection;
	
	//Construtor - pegar a conexão
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();		
	}
	
	//Validar login
	public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception{
		String sql = "select * from model_login where login = ? and senha = ?";
		
		//prepara a conexão
		PreparedStatement statement = connection.prepareStatement(sql);
		
		//Seta os parmetros
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		
		//Resultado
		ResultSet resultSet = statement.executeQuery();
		
		//Se tiver
		if(resultSet.next()) {
			return true; // autenticado
		}
		
		return false; // não autenticado
	}
}
