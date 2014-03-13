package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Movimentacao;

public class TestaCriteriaSimples {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		MovimentacaoDAO dao = new MovimentacaoDAO(em);
		
		List<Movimentacao> lista = dao.todasComCriteria();
		
		for(Movimentacao mv : lista) {
			System.out.println(mv.getDescricao());
		}
	}

}
