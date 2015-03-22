package br.gov.caixa.vitrine.ws;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.gov.caixa.vitrine.entities.Item;
import br.gov.caixa.vitrine.util.JPAUtil;

public class ItemWS {
	EntityManager manager = JPAUtil.getEntityManager();

	@SuppressWarnings("unchecked")
	public List<Item> listAll() {
		List<Item> listItems = new ArrayList<Item>();
		listItems = manager.createQuery("FROM Item").getResultList();
		manager.close();
		return listItems;
	}
}
