package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;

public class TestaRelacionamentoBidirecional {

	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		ContaDAO dao = new ContaDAO(em);

		Conta c = dao.busca(10);
		
		for(Movimentacao m : c.getMovimentacoes()) {
			System.out.println(m.getDescricao());
		}
		
		em.close();

	}

}
