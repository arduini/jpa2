package br.com.caelum.financas.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.modelo.ValorPorMes;

public class MovimentacaoDAO {
	
	private final DAO<Movimentacao> dao;
	private final EntityManager em;
	
	public MovimentacaoDAO(EntityManager em) {
		this.em = em;
		dao = new DAO<Movimentacao>(em, Movimentacao.class);
	}
	
	public void adiciona(Movimentacao t) {
		dao.adiciona(t);
	}
	
	public Movimentacao busca(Integer id) {
		return dao.busca(id);
	}
	
	public List<Movimentacao> lista() {
		return dao.lista();
	}
	
	public void remove(Movimentacao mv) {
		dao.remove(mv);
	}
	
	public List<Movimentacao> listaTodasMovimentacoes(Conta conta) {
		Query query = this.em.createQuery("select m from Movimentacao m where m.conta = :conta "
			+ " order by m.data asc");
		query.setParameter("conta", conta);
		return query.getResultList();
	}
	
	public List<Movimentacao> listaPorValorETipo(BigDecimal valor, TipoMovimentacao tipo, Conta conta) {
		String jpql = "select m from Movimentacao m where m.valor <= :pValor "
				+ " and m.tipoMovimentacao = :pTipoMovimentacao";
		Query query = this.em.createQuery(jpql);
		query.setParameter("pValor", valor);
		query.setParameter("pTipoMovimentacao", tipo);
		return query.getResultList();
	}
	
	public BigDecimal calculaTotalMovimentado(Conta conta, TipoMovimentacao tipo) {
		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta "
				+ "and m.tipoMovimentacao = :pTipoMovimentacao";
		TypedQuery<BigDecimal> query = this.em.createQuery(jpql, BigDecimal.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipoMovimentacao", tipo);
		return query.getSingleResult();
	}
	
	public List<Movimentacao> buscaTodasMovimentacoesDaConta(String titular) {
		Query query = this.em.createNamedQuery("Movimentacao.buscaTodasMovimentacoesDaConta");
		query.setParameter("pTitular", titular);
		return query.getResultList();
	}
	
	public List<ValorPorMes> listaMesesComMovimentacoes(Conta c, TipoMovimentacao tipo) {
		
		String jpql = "select new br.com.caelum.financas.modelo.ValorPorMes(month(m.data), sum(m.valor)) "
				+ " from Movimentacao m group by month(m.data), m.conta, m.tipoMovimentacao "
				+ " having m.conta = :pConta and m.tipoMovimentacao = :pTipoMovimentacao "
				+ " order by sum(m.valor) desc";
		
		Query query = this.em.createQuery(jpql);
		query.setParameter("pConta", c);
		query.setParameter("pTipoMovimentacao", tipo);
		 
		return query.getResultList();
	}
	
	public List<Movimentacao> todasComCriteria() {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Movimentacao> criteria = builder.createQuery(Movimentacao.class);
		criteria.from(Movimentacao.class);
		
		return em.createQuery(criteria).getResultList();
	}
	
	public BigDecimal somaMovimentacoesDoTitular(String titular) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		Root<Movimentacao> root = criteria.from(Movimentacao.class);
		
		criteria.select(builder.sum(root.<BigDecimal>get("valor")));
		criteria.where(builder.like(root.<Conta>get("conta").<String>get("titular"), titular));
		
		return this.em.createQuery(criteria).getSingleResult();
	}
	
	public List<Movimentacao> pesquisa(Conta conta, TipoMovimentacao tipo, Integer mes) {
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> criteria = builder.createQuery(Movimentacao.class);
			Root<Movimentacao> root = criteria.from(Movimentacao.class);
		Predicate conjunction = builder.conjunction();
		
		if(conta.getId() != null) {
			conjunction = builder.and(conjunction, builder.equal(root.<Conta>get("conta"),conta));
		}
		
		if(mes != null && mes != 0) {
			Expression<Integer> expression = builder.function("month", Integer.class, root.<Calendar> get("data"));
			conjunction = builder.and(conjunction, builder.equal(expression, mes));
		}
		
		if(tipo != null) {
			conjunction = builder.and(conjunction, builder.equal(root.<TipoMovimentacao>get("tipoMovimentacao"), tipo));
		}
		
		criteria.where(conjunction);
		
		return this.em.createQuery(criteria).getResultList();
	}
}
