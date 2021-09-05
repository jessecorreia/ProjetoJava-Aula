package br.com.projetoMVC.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {

	//CRIANDO AS CONSTANTES QUE GUARDARAO OS PARAMENTROS PARA ESTABELECER A CONEXAO
	private static final String URL = "jdbc:postgresql://localhost:5432/turmajava";
	private static final String USER = "postgres";
	private static final String PASSWORD = "admin";
		
	// MÉTODO PARA ESTABELECER A CONEXAO COM O BANCO
	
	public static Connection getConnection() throws Exception {
	try {
		//CARREGA O DRIVER DO POSTGRESQL
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	} catch (Exception e) {
		//RETORNA UMA MENSAGEM DE ERRO CASO A CONEXÃO NAO SEJA EFETIVADA
		throw new Exception(e.getMessage());
	}
	
	}
	//cRIANDO OS MÉTODOS PARA FECHAR A CONEXAO
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		close(conn, stmt, rs);
	}

	public static void closeConnection(Connection conn, Statement stmt) throws Exception {
		close(conn, stmt, null);
	}
	
	public static void closeConnection(Connection conn) throws Exception {
		close(conn, null, null);
	}
	
	//MÉTODO PARA GERENCIAR O FECHAMENTO DAS CONEXOES POR TIPO
	private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		try {
			if(rs != null) {
				rs.close();
			}
			 
			if(stmt != null) {
				stmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
