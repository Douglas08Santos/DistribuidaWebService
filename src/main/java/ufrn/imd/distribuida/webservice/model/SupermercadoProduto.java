package ufrn.imd.distribuida.webservice.model;

import java.io.Serializable;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


public class SupermercadoProduto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*Um supermercado tem varios produtos*/
	@OneToMany
	private Long idSupermercado;
	
	/*Varios produtos podem está em varios supermercados*/
	@ManyToMany
	private Long idProduto;
	
	private float preco;
	
	
	public SupermercadoProduto() {}
	
	public SupermercadoProduto(float preco) {
		super();
		this.preco = preco;
		
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
		result = prime * result + ((idSupermercado == null) ? 0 : idSupermercado.hashCode());
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
		SupermercadoProduto other = (SupermercadoProduto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		if (idSupermercado == null) {
			if (other.idSupermercado != null)
				return false;
		} else if (!idSupermercado.equals(other.idSupermercado))
			return false;
		return true;
	}
	
}
