package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Movimentacao;

public class TestaBuscaContaDaMovimentacao {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		MovimentacaoDAO dao = new MovimentacaoDAO(em);

		Movimentacao mv = dao.busca(1);
		System.out.println(mv.getConta().getTitular());
		em.close();

	}

}
