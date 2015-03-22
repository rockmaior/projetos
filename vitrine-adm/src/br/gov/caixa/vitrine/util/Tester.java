package br.gov.caixa.vitrine.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.gov.caixa.vitrine.dao.CadastroDAO;
import br.gov.caixa.vitrine.entities.Item;
import br.gov.caixa.vitrine.entities.Lote;
import br.gov.caixa.vitrine.entities.TipoObjeto;

public class Tester {

	public static void main(String[] args) {
		EntityManager manager = JPAUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
//		listarItems(manager);
		cadastrarItem(manager, transaction);
//		recuperarLoteById();
//		cadastrarLote(manager, transaction);
//		cadastrarTipoObjeto(manager, transaction);
	}

	@SuppressWarnings("unchecked")
	public static void listarItems(EntityManager manager) {
		List<Item> list = new ArrayList<Item>();
		list = manager.createQuery("FROM Item").getResultList();
		
		for (Item item : list) {
			System.out.println("Item: " + item.getDescricao());
		}
		manager.close();
	}

	public static void cadastrarItem(EntityManager manager,
			EntityTransaction transaction) {
		TipoObjeto objeto = new TipoObjeto();
		Lote lote = new Lote();
		Item item = new Item();
		item.setDescricao("Um relogio");
		item.setObservacoes("Ouro e Prata, Rolex");
		item.setPeso(100.0);
		item.setValor(1500.00);
		try {
			transaction.begin();
			lote = manager.find(Lote.class, 2);
			objeto = manager.find(TipoObjeto.class, 4);
			item.setLote(lote);
			item.setTipoObjeto(objeto);
			manager.persist(item);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally{
			manager.close();
		}
	}
	
	public static void cadastrarLote(EntityManager manager,
			EntityTransaction transaction) {
		Lote lote = new Lote();
		try {
			transaction.begin();
			manager.persist(lote);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally{
			manager.close();
		}
	}
	
	public static void cadastrarTipoObjeto(EntityManager manager,
			EntityTransaction transaction) {
		TipoObjeto tipo = new TipoObjeto();
		TipoObjeto tipo2 = new TipoObjeto();
		TipoObjeto tipo3 = new TipoObjeto();
		tipo.setDescricao("ANEL");
		tipo2.setDescricao("RELÓGIO");
		tipo3.setDescricao("COLAR");
		try {
			transaction.begin();
			manager.persist(tipo);
			manager.persist(tipo2);
			manager.persist(tipo3);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally{
			manager.close();
		}
	}
	
	
	public static void recuperarLoteById(){
		CadastroDAO<Lote> l = new CadastroDAO<Lote>(Lote.class);
		Lote lote = l.buscar(1);
		System.out.println(lote.getCodigo());
	}
}
