package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.Tag;

public class TestaRecuperaTagsAssociadasAUmaMovimentacao {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		Movimentacao mv = em.find(Movimentacao.class, 5);
		
		for(Tag tag : mv.getTags()) {
			System.out.println(tag.getNome());
		}
		
		em.close();


	}

}
