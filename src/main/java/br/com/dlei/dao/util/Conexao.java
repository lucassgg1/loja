package br.com.dlei.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexao {	
	
	private static final String RESOURCE = "java:/comp/env/jdbc/postgresqlazure";
	
	public static Connection getConexao() {
		try {
			//objeto Context
			Context ctx = new InitialContext();
			//remete ao arquivo "context.xml" [type="javax.sql.DataSource"]
			DataSource ds = (DataSource) ctx.lookup(RESOURCE);	
			//a variável [ds] pegará uma das conexões disponíveis no meu pool
			return ds.getConnection();
		} catch (SQLException | NamingException e) {
			throw new RuntimeException("Erro na conexão com o DB Postgre Azure -> \n", e);
		}
	}
}

