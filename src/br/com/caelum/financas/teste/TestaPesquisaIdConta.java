package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Conta;

public class TestaPesquisaIdConta {

	public static void main(String[] args) {
		long inicio = System.currentTimeMillis();

		EntityManager entityManager = new JPAUtil().getEntityManager();
		ContaDAO dao = new ContaDAO(entityManager);

		try {
			Conta encontrado = dao.busca(10);
			System.out.println(encontrado.getTitular());
		} catch (EntityNotFoundException e) {
			System.out.println("Conta não encontrada");
			//e.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		long fim = System.currentTimeMillis();
		System.out.println("Executado em: " + (fim - inicio) + " ms");
	}
	
}