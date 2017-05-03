package engeprev.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: Funcao
 *
 */
@Entity

public class Funcao implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_funcao")
	@SequenceGenerator(name = "seq_funcao", sequenceName = "seq_funcao", allocationSize = 1, initialValue = 1)
	private Long id_funcao;
	@NotNull(message="O nome da funcao nao pode ser nulo")
	@NotEmpty(message="O nome da funcao nao pode ser em branco")
	private String nome;
	@ManyToOne
	private Empresa id_empresa;
	private static final long serialVersionUID = 1L;

	public Funcao() {
		super();
	}   
	
	public Funcao(Long id_funcao, String nome, Empresa id_empresa) {
		super();
		this.id_funcao = id_funcao;
		this.nome = nome;
		this.id_empresa = id_empresa;
	}

	public Long getId_funcao() {
		return this.id_funcao;
	}

	public void setId_funcao(Long id_funcao) {
		this.id_funcao = id_funcao;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public Empresa getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Empresa id_empresa) {
		this.id_empresa = id_empresa;
	}
   
}
