package engeprev.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Empresa
 *
 */
@Entity

public class Empresa implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_empresa")
	@SequenceGenerator(name = "seq_empresa", sequenceName = "seq_empresa", allocationSize = 1, initialValue = 1)
	private Long id_empresa;
	@NotNull(message="O nome fantasia da empresa deve ser informado")
	private String nomefantasia;
	@NotNull(message="A razao social da empresa deve ser informado")
	private String razaosocial;
	@NotNull(message="O CNPJ da empresa deve ser informado")
	private String cnpj;
	private String ie;
	@NotNull(message="A rua da empresa deve ser informada")
	private String rua;
	@NotNull(message="O numero da empresa deve ser informado")
	private String numero;
	@NotNull(message="O bairro da empresa deve ser informado")
	private String bairro;
	@ManyToOne
	@NotNull(message="O codigo da cidade da empresa deve ser informado")
	private Cidade codcidade;
	private static final long serialVersionUID = 1L;

	public Empresa() {
		super();
	}   
	public Long getId_empresa() {
		return this.id_empresa;
	}

	public void setId_empresa(Long id_empresa) {
		this.id_empresa = id_empresa;
	}   
	public String getNomefantasia() {
		return this.nomefantasia;
	}

	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}   
	public String getRazaosocial() {
		return this.razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}   
	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}   
	public String getIe() {
		return this.ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}   
	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}   
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}   
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}   
	public Cidade getCodcidade() {
		return this.codcidade;
	}

	public void setCodcidade(Cidade codcidade) {
		this.codcidade = codcidade;
	}
   
}
