package engeprev.beans;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Entity implementation class for Entity: Funcionario
 *
 */
@Entity

public class Funcionario implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "seq_funcionario")
	@SequenceGenerator(name = "seq_funcionario", allocationSize = 1, sequenceName = "seq_funcionario")
	private Long id_funcionario;
	@Column(unique = true, nullable = false)
	private String nome;
	@ManyToOne(optional=false)
	@NotNull(message="A função nao pode ser nula.")
	private Funcao funcao;
	@Valid
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "fk_funcionario", fetch=FetchType.EAGER)
	private List<FuncionarioEPI> funcionarioEPI;
	@ManyToOne
	private Cidade codcidade;
	@ManyToOne
	private Empresa id_empresa;
	private static final long serialVersionUID = 1L;

	public Funcionario() {
		super();
		funcionarioEPI = new ArrayList<>();
	}   
	public Long getId_funcionario() {
		return this.id_funcionario;
	}

	public void setId_funcionario(Long id_funcionario) {
		this.id_funcionario = id_funcionario;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public List<FuncionarioEPI> getFuncionarioEPI() {
		return funcionarioEPI;
	}
	public void setFuncionarioEPI(List<FuncionarioEPI> funcionarioEPI) {
		this.funcionarioEPI = funcionarioEPI;
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
