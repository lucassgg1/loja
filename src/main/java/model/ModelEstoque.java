package model;

import java.io.Serializable;
import java.sql.Date;

public class ModelEstoque implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Long idEstoque;
    private ModelProduto produto; // Refere-se diretamente ao objeto ModelProduto
    private int quantidade;
    private Date dataMovimentacao;
    private String tipoMovimentacao;
    
 // Método para verificar "id"
 	public boolean isNovo() {
 		// Se "id" for null/ não tiver valor
 		if(this.idEstoque == null) {
 			return true;// Inserir novo
 			// Se "id" não for nulo e o valor for maior que "0" 
 		}else if(this.idEstoque != null  && this.idEstoque > 0) {
 			return false;// Atualizar
 		}
 		return idEstoque == null;
 	}
    
    public Long getIdEstoque() {
        return idEstoque;
    }
    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }
    public ModelProduto getProduto() {
        return produto;
    }
    public void setProduto(ModelProduto produto) {
        this.produto = produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }
    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }
    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }
}
