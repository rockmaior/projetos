package br.gov.caixa.vitrine.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.gov.caixa.vitrine.util.JPAUtil;

public class CadastroDAO<T> {
	EntityManager manager = JPAUtil.getEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	private final Class<T> classe;

	public CadastroDAO(Class<T> classe) {
		this.classe = classe;
	}

	public T buscar(Integer id) {
//		return this.manager.getReference(classe, id);
		return this.manager.find(classe, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar() {
		return this.manager.createQuery("FROM " + classe.getName()).getResultList();
	}

	public void adicionar(T t) {
		try {
			manager.getTransaction().begin();
			this.manager.persist(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}

	public void remover(T t) {
		try {
			manager.getTransaction().begin();
			this.manager.remove(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}

	public void atualizar(T t) {
		try {
			manager.getTransaction().begin();
			this.manager.merge(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}
}
