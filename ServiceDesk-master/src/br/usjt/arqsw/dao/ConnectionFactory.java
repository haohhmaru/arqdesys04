package br.usjt.arqsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por fazer a conexão SGBD
 * 
 * @author Alessandro Lima Da Silva R.A 201522705
 *
 */
public class ConnectionFactory {

	// Singleton Connection
	private static final ThreadLocal<Connection> conn = new ThreadLocal<>();

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Obtém a conexão com o Banco de Dados
	 * 
	 * @return conexão
	 * @throws SQLException
	 */
	public static Connection obtemConexao() throws SQLException {
		if (conn.get() == null) {
			conn.set(DriverManager.getConnection(
					"jdbc:mysql://localhost/servicedesk?user=yabaconsultoria&password=yaba2389&useSSL=false"));
		}
		return conn.get();
	}

	/**
	 * Fecha conexão com Bando de Dados
	 * 
	 * @throws SQLException
	 */
	public static void fechaConexao() throws SQLException {
		if (conn.get() != null) {
			conn.get().close();
			conn.set(null);
		}
	}
}
