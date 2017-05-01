package engeprev.beans;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;


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
	private Funcao funcao;
	@Valid
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "fk_funcionario")
	private List<FuncionarioEPI> funcionarioEPI;
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
   
}
