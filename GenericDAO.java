package br.com.projetoMVC.DAO;

import java.util.List;

public interface GenericDAO {

	/* Criando as assinaturas do metodos que serao implementador por todas as classes DAO
	 * 
	 */
	
	public List<Object> listarTodos();
	
	public Object listarPorId(int id);
	
	public Boolean cadastrar (Object object);
	
	public Boolean alterar (Object object);
	
	public void excluir (int id);
	
}
