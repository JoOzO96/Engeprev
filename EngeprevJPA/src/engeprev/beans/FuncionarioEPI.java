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
	@NotNull(message="A data de entrega deve ser informada")
	private Date dataEntrega;
	@NotNull(message="A data de vencimento deve ser informada")
	private Date dataVencimento;
	@NotNull(message="A quantidade deve ser informada")
	@Min(message="A quantidade deve ser maio que 0", value=0)
	private Float quantidade;
	private Float quantidadeIdealAnual;
	@ManyToOne
	private Empresa id_empresa;
	private static final long serialVersionUID = 1L;

	public FuncionarioEPI() {
		super();
		dataEntrega = new Date();
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

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Float getQuantidadeIdealAnual() {
		return quantidadeIdealAnual;
	}

	public void setQuantidadeIdealAnual(Float quantidadeIdealAnual) {
		this.quantidadeIdealAnual = quantidadeIdealAnual;
	}

	public Empresa getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Empresa id_empresa) {
		this.id_empresa = id_empresa;
	}
}
