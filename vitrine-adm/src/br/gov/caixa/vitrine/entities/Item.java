package br.gov.caixa.vitrine.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@SuppressWarnings("deprecation")
@Entity
@Table(name="EVJTB001_ITEM")
@SequenceGenerator(name = "itemSeqGenerator", sequenceName = "ITEM_SEQ", allocationSize = 1, initialValue = 0)
public class Item implements Serializable{
	private static final long serialVersionUID = -5719773512025073027L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSeqGenerator")
	private Integer codigo;
	private String descricao;
	private Double peso;
	private Double valor;
	private String observacoes;
	@OneToOne
	@ForeignKey(name = "FK_ITEM_LOTE")
	private Lote lote;
	@OneToOne
	@ForeignKey(name="FK_ITEM_OBJETO")
	private TipoObjeto tipoObjeto;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public TipoObjeto getTipoObjeto() {
		return tipoObjeto;
	}

	public void setTipoObjeto(TipoObjeto tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Item(Integer codigo, String descricao, Double peso, Double valor,
			String observacoes, Lote lote, TipoObjeto tipoObjeto) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.peso = peso;
		this.valor = valor;
		this.observacoes = observacoes;
		this.lote = lote;
		this.tipoObjeto = tipoObjeto;
	}

	public Item() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	

}
