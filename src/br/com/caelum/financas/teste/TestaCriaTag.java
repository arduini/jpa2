package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.dao.TagDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.Tag;

public class TestaCriaTag {

	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		TagDAO dao = new TagDAO(em);
		MovimentacaoDAO mvDao = new MovimentacaoDAO(em);

		em.getTransaction().begin();
		Tag tag = dao.adicionaOuBuscaTagComNome("tag_teste3");
		Movimentacao mv = mvDao.busca(5);
		mv.getTags().add(tag);
		em.getTransaction().commit();

		em.close();
	}

}
