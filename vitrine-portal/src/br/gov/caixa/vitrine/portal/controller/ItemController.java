package br.gov.caixa.vitrine.portal.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.gov.caixa.vitrine.portal.util.JSFUtil;
import br.gov.caixa.vitrine.ws.ItemWSStub;
import br.gov.caixa.vitrine.ws.ItemWSStub.Item;
import br.gov.caixa.vitrine.ws.ItemWSStub.ListAll;

@ManagedBean(name = "itemController")
@RequestScoped
public class ItemController {
	private Item item = new Item();
	private Item[] itemJoia;
	private Integer itemCodigo;
	private boolean exibir;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Item[] getItemJoia() {
		return itemJoia;
	}

	public void setItemJoia(Item[] itemJoia) {
		this.itemJoia = itemJoia;
	}

	public void listAll() {
		try {
			ItemWSStub ps = new ItemWSStub();
			this.itemJoia = ps.listAll(new ListAll()).get_return();
		} catch (Exception e) {
			this.item = null;
		}
	}

	public String buscar() {
		if (this.itemJoia == null) {
			listAll();
		}
		Item[] iArray = this.itemJoia;
		String msg = "Nenhum item encontrado";
		for (int i = 0; i < iArray.length; i++) {
			if (iArray[i].getCodigo() == itemCodigo) {
				this.item = iArray[i];
				setExibir(true);
				msg = null;
			}

		}
		if (msg != null) {
			JSFUtil.addInfoMessage(msg);
			setItemCodigo(null);
		}
		return null;
	}

	public Integer getItemCodigo() {
		return itemCodigo;
	}

	public void setItemCodigo(Integer itemCodigo) {
		this.itemCodigo = itemCodigo;
	}

	public boolean isExibir() {
		return exibir;
	}

	public void setExibir(boolean exibir) {
		this.exibir = exibir;
	}

}
