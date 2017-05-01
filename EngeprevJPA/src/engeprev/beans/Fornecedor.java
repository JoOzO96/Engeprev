package engeprev.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Fornecedor
 *
 */
@Entity

public class Fornecedor implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_fornecedor")
	@SequenceGenerator(name = "seq_fornecedor", sequenceName = "seq_fornecedor", allocationSize = 1, initialValue = 1)
	private Long id_fornecedor;
	@NotNull(message="O nome do fornecedor deve ser informado")
	private String nome;
	@NotNull(message="O CNPJ do fornecedor deve ser informado")
	private String cnpj;
	private String razaosocial;
	private String email;
	private static final long serialVersionUID = 1L;

	public Fornecedor() {
		super();
	}   
	public Long getId_fornecedor() {
		return this.id_fornecedor;
	}

	public void setId_fornecedor(Long id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}    
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getRazaosocial() {
		return razaosocial;
	}
	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
   
}
