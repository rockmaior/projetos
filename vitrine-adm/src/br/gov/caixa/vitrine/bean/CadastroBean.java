package br.gov.caixa.vitrine.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.gov.caixa.vitrine.dao.CadastroDAO;
import br.gov.caixa.vitrine.entities.Item;
import br.gov.caixa.vitrine.entities.Lote;
import br.gov.caixa.vitrine.entities.TipoObjeto;
import br.gov.caixa.vitrine.util.JSFUtil;

@ManagedBean(name = "cadastroBean")
@SessionScoped
public class CadastroBean {

	private Item item = new Item();

	private List<Lote> listLotes;
	private List<TipoObjeto> listTipoObjetos;
	private List<Item> listItems;
	private boolean flag = false;

	CadastroDAO<Lote> loteDao = new CadastroDAO<Lote>(Lote.class);
	CadastroDAO<Item> itemDao = new CadastroDAO<Item>(Item.class);
	CadastroDAO<TipoObjeto> tipoDao = new CadastroDAO<TipoObjeto>(
			TipoObjeto.class);

	public String salvar() {
		if (item.getCodigo() != null) {
			try {
				itemDao.atualizar(item);
				JSFUtil.addSuccessMessage("Registro atualizado com sucesso");
				this.item = new Item();
			} catch (Exception e) {
				JSFUtil.addErrorMessage("Ocorreu erro ao tentar atualizar " + e.getMessage());
			}
		}else {
			try {
				itemDao.adicionar(item);
				this.item = new Item();
				JSFUtil.addSuccessMessage("Registro inserido com sucesso");
			} catch (Exception e) {
				JSFUtil.addErrorMessage("Ocorreu erro ao tentar salvar " + e.getMessage());
			}
		}
		return null;
	}

	public String consultarItemPorId() {
		try {
			this.item = itemDao.buscar(item.getCodigo());
			if (this.item == null) {
				JSFUtil.addInfoMessage("Nenhum registro encontrado");
			}else{
				this.setFlag(true);
			}
		} catch (Exception e) {
			JSFUtil.addErrorMessage("O seguinte erro ocorreu: " + e.getMessage());
		}
		return "cadastrar";
	}
	
	public String consultarItem() {
		try {
			this.item = itemDao.buscar(item.getCodigo());
			if (this.item == null) {
				JSFUtil.addInfoMessage("Nenhum registro encontrado");
			}else{
				this.setFlag(true);
			}
		} catch (Exception e) {
			JSFUtil.addErrorMessage("O seguinte erro ocorreu: " + e.getMessage());		
		}
		return "exclusao";
	}

	public String excluir() {
		try {
			itemDao.remover(item);
			JSFUtil.addSuccessMessage("Registro excluído com sucesso");
			this.setFlag(false);
			this.item = new Item();
		} catch (Exception e) {
			JSFUtil.addErrorMessage("O seguinte erro ocorreu: "+e.getMessage());
		}
		return "excluir";
	}
	
	

	public List<Item> getListItems() {
		listItems = itemDao.listar();
		return listItems;
	}

	public List<Lote> getListLotes() {
		listLotes = loteDao.listar();
		return listLotes;
	}

	public List<TipoObjeto> getListTipoObjetos() {
		listTipoObjetos = tipoDao.listar();
		return listTipoObjetos;
	}

	public void setListTipoObjetos(List<TipoObjeto> listTipoObjetos) {
		this.listTipoObjetos = listTipoObjetos;
	}

	public void setListLotes(List<Lote> listLotes) {
		this.listLotes = listLotes;
	}

	public void setListItems(List<Item> listItems) {
		this.listItems = listItems;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	

}
