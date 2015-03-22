package br.gov.caixa.vitrine.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.caixa.vitrine.dao.CadastroDAO;
import br.gov.caixa.vitrine.entities.Lote;

@FacesConverter("loteConverter")
public class LoteConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		if (value == null || value.isEmpty())
			return null;
		Integer id = Integer.valueOf(value);
		Lote lote = new CadastroDAO<Lote>(Lote.class).buscar(id);
		return lote;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof Lote) {
			Lote lote = (Lote) value;
			return String.valueOf(lote.getCodigo());
		}
		return "";
	}
}
