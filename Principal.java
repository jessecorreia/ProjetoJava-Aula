package br.com.projetoMVC;

import java.sql.Connection;
import java.util.List;

import br.com.projetoMVC.controller.ProdutoController;
import br.com.projetoMVC.model.Produto;
import br.com.projetoMVC.util.ConnectionFactory;

public class Principal {

	public static void main(String[] args) throws Exception {
	
		
		
/*		
 * //testando conexao
 * Connection connection = ConnectionFactory.getConnection();
		
			
			if(connection != null) {
				System.out.println("CONEXÃO OK");
			}else {
				System.out.println("PROBLEMA NA CONEXÃO");
			}
		
		connection.close();
*/

	ProdutoController controller = new ProdutoController();
	
	List<Produto> lista = controller.listarTodos();
	
	for(Produto produto : lista ) {
		System.out.println("ID " + produto.getId());
		System.out.println("Descrição " + produto.getDescricao());
	}

	}

}
