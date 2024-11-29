package model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ModelProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String categoria;
	private String produto;
	private String tipo;
	private BigDecimal vlcompra;
	private BigDecimal icms;
	private BigDecimal vlvenda;

	// Método para verificar "id"
	public boolean isNovo() {
		// Se "id" for null/ não tiver valor
		if (this.id == null) {
			return true;// Inserir novo
			// Se "id" não for nulo e o valor for maior que "0"
		} else if (this.id != null && this.id > 0) {
			return false;// Atualizar
		}
		return id == null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getVlcompra() {
		return vlcompra;
	}

	public void setVlcompra(BigDecimal vlcompra) {
		this.vlcompra = vlcompra;
	}

	public BigDecimal getIcms() {
		return icms;
	}

	public void setIcms(BigDecimal icms) {
		this.icms = icms;
	}

	public BigDecimal getVlvenda() {
		return vlvenda;
	}

	public void setVlvenda(BigDecimal vlvenda) {
		this.vlvenda = vlvenda;
	}

	
}
