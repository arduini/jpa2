package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;

//import javax.persistence.Persistence;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Conta;

public class TestaRemoveConta {
	
	public static void main(String[] args) {

		EntityManager entityManager = new JPAUtil().getEntityManager();
		ContaDAO dao = new ContaDAO(entityManager);
		
		entityManager.getTransaction().begin();
		
		Conta conta = dao.busca(1);
		dao.remove(conta);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

}