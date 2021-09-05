package br.com.projetoMVC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projetoMVC.model.Produto;
import br.com.projetoMVC.util.ConnectionFactory;

public class ProdutoDAOImpl implements GenericDAO {

	private Connection conn;

	public ProdutoDAOImpl() throws Exception {
		try {
			this.conn = ConnectionFactory.getConnection();
			System.out.println("Conectado com Sucesso!");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Object> listarTodos() {

		// CRIANDO A LISTA DE OBJETO Q SERA RETORNADO
		List<Object> lista = new ArrayList<Object>();

		// CRIANDO UM OBJETO DA PREPAREDSTATEMENT
		// ENVIO PARA O BANCO
		PreparedStatement stmt = null;
		// CRIANDO UM OBJETO DA RESULTSET PARA OBTER O
		// RETORNO DA QUERY EXECUTADA
		ResultSet rs = null;
		// CRIAR A SQL Q SERA EXECUTADA NO BANCO
		String sql = "SELECT * FROM produto ORDER BY descricao";
		// TENTAR LISTAR TODOS OS REGISTROS
		try {
			// PREPARAR A SQL PARA SER EXECUTADA
			stmt = conn.prepareStatement(sql);
			// EXECUTAR A QUERY
			rs = stmt.executeQuery();
			// PERCORRER CADA LINHA DO RESULTADO DA QUERY
			while (rs.next()) {
				// INSTANCIANDO A CLASSE PRODUTO
				Produto produto = new Produto();
				// INSERIR EM CADA ATRIBUTO DO OBJETO A COLUNA CORRESPONDENTE
				// DO BANCO DE DADOS
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				// ADCIONAR NA LISTA ESSE OBJETO COM AS INFORMAÇÕES DA LINHA
				lista.add(produto);
			}

		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao listar o Produto" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			// FECHAR A CONEXAO COM O BANCO DE DADOS INDEPENDENTE SE OBTEVE
			// RETORNO POSITIVO OU NAO'
			try {
				ConnectionFactory.closeConnection(conn, stmt, rs);
			} catch (Exception e) {
				System.out.println("Problemas ao fechar a conexão!" + e.getMessage());
			}
		}

		return lista;
	}

	@Override
	public Object listarPorId(int id) {
	
		Produto produto = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM produto Where id = ?";
	
	try {
		stmt=conn.prepareStatement(sql);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			produto = new Produto();
			produto.setId(rs.getInt("id"));
			produto.setDescricao(rs.getString("descricao"));
			
		}
		

	} catch(SQLException ex) {
		System.out.println("Problemas na DAO ao carregar o produto" + ex.getMessage());
		ex.printStackTrace();
		
	}finally {
		try {
		ConnectionFactory.closeConnection(conn, stmt , rs);
		} catch (Exception e) {
			System.out.println("Problemas ao fechar a conexão! ERRO" + e.getMessage());
			System.out.println();
		
		
	}
	}
	return produto;
	}
	@Override
	public Boolean cadastrar(Object object) {
		// CONVERTENDO UM OBJETO DO TIPO GENERICO PARA UM
		// OBJETO DE CLASSE PRODUTO
		// CONVERSAO TIPO CAST
		Produto produto = (Produto) object;

		PreparedStatement stmt = null;
		// PASSANDO OS VALUES DE FORMA DINAMICA PARA A QUERY
		String sql = "INSERT INTO produto (descricao)" + "VALUES (?)";

		try {
			stmt = conn.prepareStatement(sql);
			// VINCULANDO O PRIMEIRO PONTO DE INTERROGAÇÃO COM O PRIMEIRO
			// PARAMETRO DO STMT NA SEQUENCIA
			stmt.setString(1, produto.getDescricao());
			stmt.execute();

			return true;

		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao cadastrar Produto!" + ex.getMessage());
			ex.printStackTrace();
			return false;
		} finally {
			// FECHAR A CONEXAO COM O BANCO DE DADOS INDEPENDENTE SE OBTEVE
			// RETORNO POSITIVO OU NAO'
			try {
				ConnectionFactory.closeConnection(conn, stmt, null);
			} catch (Exception e) {
				System.out.println("Problemas ao fechar a conexão!" + e.getMessage());
			}
		}

	}

	@Override
	public Boolean alterar(Object object) {
	
		//CONVERTENDO O OBJETO GENERICO PARA UM OBJETO DO TIPO PRODUTO
		Produto produto = (Produto) object;
		PreparedStatement stmt = null;
		String sql = "UPDATE produto SET descricao = ?" + 
		"WHERE id = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, produto.getDescricao());
			stmt.setInt(2, produto.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			System.out.println("Probelmas na DAO de alteração de Produto" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, null);
			} catch (Exception e) {
				System.out.println("Erro ao fechar conexão com banco" + e.getMessage());
			}
		}
	}
	@Override
	public void excluir(int id) {
	
		PreparedStatement stmt = null;
		String sql = "delete from PRODUTO where ID = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Probelmas na DAO ao excluir Produto" + ex.getMessage());
			ex.printStackTrace();
		}finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, null);
			} catch (Exception e) {
				System.out.println("Problemas ao fechar conexão" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	}

