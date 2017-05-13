package engeprev.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

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
	@NotEmpty(message="O nome do fornecedor deve ser informado")
	private String nome;
	@NotNull(message="O CNPJ do fornecedor deve ser informado")
	@NotEmpty(message="O CNPJ do fornecedor deve ser informado")
	private String cnpj;
	private String razaosocial;
	@Email(message="O email deve ser valido.")
	private String email;
	@ManyToOne
	private Cidade codcidade;
	@ManyToOne
	private Empresa id_empresa;
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
	public Cidade getCodcidade() {
		return codcidade;
	}
	public void setCodcidade(Cidade codcidade) {
		this.codcidade = codcidade;
	}
	public Empresa getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Empresa id_empresa) {
		this.id_empresa = id_empresa;
	}
   
}
