package br.gov.caixa.vitrine.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.caixa.vitrine.dao.CadastroDAO;
import br.gov.caixa.vitrine.entities.TipoObjeto;

@FacesConverter("tipoObjetoConverter")
public class TipoObjetoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Integer id = Integer.valueOf(string);
		TipoObjeto tipoObjeto = new CadastroDAO<TipoObjeto>(TipoObjeto.class).buscar(id);
		return tipoObjeto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		if (object != null || object instanceof TipoObjeto) {
			TipoObjeto tipoObjeto = (TipoObjeto) object;
			return String.valueOf(tipoObjeto.getCodigo());
		}
		return "";
	}
}
