package engeprev.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: FuncionarioAudiometria
 *
 */
@Entity

public class FuncionarioAudiometria implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_funcionarioaudiometria")
	@SequenceGenerator(name = "seq_funcionarioaudiometria", sequenceName = "seq_funcionarioaudiometria", allocationSize = 1, initialValue = 1)
	private Long id_funcionarioaudiometria;
	@NotNull(message="A data do exame deve ser informada")
	@Temporal(TemporalType.DATE)
	private Date dataexame;
	@NotNull(message="A data de validade do exame deve ser informada")
	@Temporal(TemporalType.DATE)
	private Date datavalidade;
	@NotNull(message="O funcionario deve ser informado")
	private Funcionario id_funcionario;
	@ManyToOne
	private Empresa id_empresa;
	private static final long serialVersionUID = 1L;

	public FuncionarioAudiometria() {
		super();
		dataexame = new Date();
		datavalidade = dataexame;
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(datavalidade);
		calendar.add(Calendar.YEAR, 1);
		datavalidade = calendar.getTime();
	}   
	public Long getId_funcionarioaudiometria() {
		return this.id_funcionarioaudiometria;
	}

	public void setId_funcionarioaudiometria(Long id_funcionarioaudiometria) {
		this.id_funcionarioaudiometria = id_funcionarioaudiometria;
	}   
	public Date getDataexame() {
		return this.dataexame;
	}

	public void setDataexame(Date dataexame) {
		this.dataexame = dataexame;
	}   
	public Date getDatavalidade() {
		return this.datavalidade;
	}

	public void setDatavalidade(Date datavalidade) {
		this.datavalidade = datavalidade;
	}   
	public Funcionario getId_funcionario() {
		return this.id_funcionario;
	}

	public void setId_funcionario(Funcionario id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	public Empresa getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Empresa id_empresa) {
		this.id_empresa = id_empresa;
	}
   
}
