package br.gov.caixa.vitrine.portal.controller;

import java.util.ArrayList;

import br.gov.caixa.vitrine.ws.ItemWSStub.Item;

public class Tester {

	public static void main(String[] args) {
		ItemController ic = new ItemController();
		ic.listAll();
		
		Item[] item = ic.getItemJoia();
		
		for (int i = 0; i < item.length; i++) {
			if (item[i].getCodigo() == 2) {
				System.out.println(item[i].getDescricao());
			}
		}
		
//		
//		for (Item i : item) {
//			System.out.println(i.getDescricao());
//		}

	}

}
