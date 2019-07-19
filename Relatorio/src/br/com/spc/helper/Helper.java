package br.com.spc.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.spc.entity.Pessoa;

public class Helper {
	
	private EntityManager em;
	
	public Helper(EntityManager em){
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarPessoas(){
		Query query = em.createQuery("select p from Pessoa p");
		return query.getResultList();
	}

}
