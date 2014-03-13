package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.modelo.ValorPorMes;

public class TestaBuscaMovimentacaoPorMes {
	
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		ContaDAO contaDao = new ContaDAO(em);
		Conta c = contaDao.busca(9); 
		MovimentacaoDAO movDao = new MovimentacaoDAO(em);
		List<ValorPorMes> mv = movDao.listaMesesComMovimentacoes(c, TipoMovimentacao.ENTRADA);
		
		for (ValorPorMes vpm : mv) {
			System.out.println(vpm.getValor() + " --- " + vpm.getMes());
		}
		
		
		em.close();

	}

}
