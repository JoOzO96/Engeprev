package engeprev.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

/**
 * Entity implementation class for Entity: FuncionarioEPI
 *
 */
@Entity

public class FuncionarioEPI implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_funcionarioepi")
	@SequenceGenerator(name = "seq_funcionarioepi", sequenceName = "seq_funcionarioepi", allocationSize = 1, initialValue = 1)
	private Long id_funcionarioepi;
	@ManyToOne(optional = false)
	private EPI fk_epi;
	@ManyToOne(optional = false)
	@NotNull(message = "O funcionario deve ser inicializado!")
	private Funcionario fk_funcionario;
	private Double quantidadeIdealAnual;
	private static final long serialVersionUID = 1L;

	public FuncionarioEPI() {
		super();
		quantidadeIdealAnual = 0D;
	}

	public Long getId_funcionarioepi() {
		return id_funcionarioepi;
	}

	public void setId_funcionarioepi(Long id_funcionarioepi) {
		this.id_funcionarioepi = id_funcionarioepi;
	}

	public EPI getFk_epi() {
		return fk_epi;
	}

	public void setFk_epi(EPI fk_epi) {
		this.fk_epi = fk_epi;
	}

	public Funcionario getFk_funcionario() {
		return fk_funcionario;
	}

	public void setFk_funcionario(Funcionario fk_funcionario) {
		this.fk_funcionario = fk_funcionario;
	}
	public Double getQuantidadeIdealAnual() {
		return quantidadeIdealAnual;
	}

	public void setQuantidadeIdealAnual(Double quantidadeIdealAnual) {
		this.quantidadeIdealAnual = quantidadeIdealAnual;
	}
}
