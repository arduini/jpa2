package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class TestaCriteriaRelacionamento {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		MovimentacaoDAO dao = new MovimentacaoDAO(em);
		ContaDAO contaDao = new ContaDAO(em);
		
		//BigDecimal soma = dao.somaMovimentacoesDoTitular("%José%");
		//System.out.println(soma);

		Conta c = contaDao.busca(10);
		List<Movimentacao> lista = dao.pesquisa(c, TipoMovimentacao.SAIDA, 3);
		for(Movimentacao mv : lista){
			System.out.println(mv.getDescricao());
		}
	}

}
