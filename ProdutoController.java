package br.com.projetoMVC.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.projetoMVC.DAO.GenericDAO;
import br.com.projetoMVC.DAO.ProdutoDAOImpl;
import br.com.projetoMVC.model.Produto;

public class ProdutoController {
	
	public List<Produto> listarTodos(){
		
		try {
			GenericDAO dao = new ProdutoDAOImpl();
			List<Produto> lista = new ArrayList<Produto>();
			
			for(Object o : dao.listarTodos()) {
				lista.add((Produto) o);
			}
			return lista;
		} catch (Exception ex) {
			System.out.println("Problemas ao listar produtos");
			ex.printStackTrace();
			return null;
		}
		
			
	}
	
	public Produto listarPorId(int id) {
		
		try {
			GenericDAO dao = new ProdutoDAOImpl();
			return (Produto) dao.listarPorId(id);
		} catch (Exception e) {
			System.out.println("Problemas ao carregar produtos" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}