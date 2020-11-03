package ufrn.imd.distribuida.webservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Supermercados")
public class Supermercado  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idSupermercado;	
	private String nome;
	
	@OneToMany(mappedBy = "supermercado")
	private List<Produto> produto = new ArrayList<Produto>();
		
	
	public Long getIdSupermercado() {
		return idSupermercado;
	}

	public void setIdSupermercado(Long idSupermercado) {
		this.idSupermercado = idSupermercado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Supermercado other = (Supermercado) obj;
		if (idSupermercado == null) {
			if (other.idSupermercado != null)
				return false;
		} else if (!idSupermercado.equals(other.idSupermercado))
			return false;
		return true;
	}

	
	
}
