package connection;

import java.sql.Connection;
import java.sql.DriverManager;

//Aula 2.16
/*
 * Apenas uma conexão por sistema
 * NÃƒO FICAR fechando e abrindo a conexão com o DB. Isso gera muita lentidão
 * o que abre e fecha são as sessões e transações
 */
public class SingleConnectionBanco {

	/*
	 * static porque não vai mudar [?autoReconnect=true] caso caia a conexão se
	 * reconecta automaticamente
	 */
	private static String banco = "jdbc:postgresql://dlei-servlet.postgres.database.azure.com:5432/dlei-servlet?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "Mypstdb@";
	private static Connection connection = null;

	/* retorna a conexão existente */
	public static Connection getConnection() {
		return connection;
	}

	/* permite chamar a classe que faz a conexão em qualquer local */
	static {
		conectar();
	}

	/* Quando houver uma instância irá conectar */
	public SingleConnectionBanco() {
		conectar();
	}

	private static void conectar() {
		/* Se a conexão for igual a nula, ou seja, se não houver conexão */
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");/* Class responsável por carregar o driver de conexão do DB */
				connection = DriverManager.getConnection(banco, user, senha);/* fornece os dados para conexão ao DB */
				connection.setAutoCommit(false); /* Para não efetuar alteração no DB sem nosso comando */
				System.out.println("\n--> Conexão com o DB realizada com sucesso! <--"
						+ "\n==> DB: Azure/dlei-servlet <==\n");

			}
		} catch (Exception e) {
			System.out.println("\n--> Erro na conexão com o DB <--"
					+ "\n==> DB: Azure/dlei-servlet <==\n"
					+ "--> VER Class SingleConnectionBanco <--\n");
			e.printStackTrace(); /* Mostrar qualquer erro no momento que conectar */
		}
	}
}
