package br.com.alura.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.entity.AgendamentoEmail;

public class QueryBuilder<T> {

	private final EntityManager entityManager;
	private final Class<T> clazz;

	private final CriteriaBuilder criteriaBuilder;
	private final CriteriaQuery<T> criteriaQuery;
	private final Root<T> root;

	private List<Predicate> predicates = new ArrayList<Predicate>();

	public QueryBuilder(EntityManager entityManager, Class<T> clazz) {
		this.entityManager = entityManager;
		this.clazz = clazz;

		this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
		this.criteriaQuery = this.criteriaBuilder.createQuery(clazz);
		this.root = this.criteriaQuery.from(clazz);
	}

	public <E> QueryBuilder<T> where(String fieldName, Object fieldValue) {
		try {
			this.predicates.add(criteriaBuilder.equal(this.root.<E>get(fieldName), fieldValue));
		} catch (SecurityException e) {

		}
		return this;
	}

	public List<T> getResult() {
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
