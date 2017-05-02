package engeprev.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity

public class Usuario implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_usuario")
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
	private Long id_usuario;
	@NotNull(message="O nome do usuario nao pode ser em branco")
	private String nome;
	private String cpf;
	private String senha;
	private String usuario;
	@ManyToOne
	private Empresa codempresa;
	@ManyToOne
	private Cidade codcidade;
	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}   
	public Long getId_usuario() {
		return this.id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}   
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}   
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Empresa getCodempresa() {
		return codempresa;
	}
	public void setCodempresa(Empresa codempresa) {
		this.codempresa = codempresa;
	}
	public Cidade getCodcidade() {
		return codcidade;
	}
	public void setCodcidade(Cidade codcidade) {
		this.codcidade = codcidade;
	}
   
}
