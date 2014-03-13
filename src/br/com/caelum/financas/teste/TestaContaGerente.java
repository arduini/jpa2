package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Gerente;

public class TestaContaGerente {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		ContaDAO dao = new ContaDAO(em);
		
		Gerente gerente = new Gerente();
		gerente.setNome("Gerente Teste Dois");
		gerente.setTelefone("3333-9999");
		gerente.getEndereco().setRua("Rua do Ouro");
		gerente.getEndereco().setCidade("Lavras");
		gerente.getEndereco().setEstado("MG");
		gerente.getGerenteId().setCpf("456789");
		gerente.getGerenteId().setRg("123456");
		

		Conta conta = new Conta();
		conta.setBanco("Banco do Brasil");
		conta.setNumero("123456789");
		conta.setGerente(gerente);
		conta.setTitular("Cliente Teste Dois");
		
		em.getTransaction().begin();
		
		try {
			em.persist(gerente);
			dao.adiciona(conta);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println("Não foi possível persistir os dados...");
			//e.printStackTrace();
		} finally {		
			em.close();
		}

	}

}
