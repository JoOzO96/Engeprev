package engeprev.beans;

import java.io.Serializable;
import java.lang.Long;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * Entity implementation class for Entity: FuncionarioExame
 *
 */
@Entity

public class FuncionarioExame implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_funcionarioexame")
	@SequenceGenerator(name = "seq_funcionarioexame", sequenceName = "seq_funcionarioexame", allocationSize = 1, initialValue = 1)
	private Long id_funcionarioexame;
	@NotNull(message="A data do exame deve ser informada")
	@Past(message="A data do exame nao pode ser no passado")
	@Temporal(TemporalType.DATE)
	private Date dataexame;
	@Future(message="A data de validade deve ser no futuro")
	@Temporal(TemporalType.DATE)
	private Date datavalidade;
	@NotNull(message="O funcionario deve ser informado")
	private Funcionario id_funcionario;
	private static final long serialVersionUID = 1L;

	public FuncionarioExame() {
		super();
		dataexame = new Date();
		datavalidade = dataexame;
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(datavalidade);
		calendar.add(Calendar.YEAR, 1);
		datavalidade = calendar.getTime();
	}   
	public Long getId_funcionarioexame() {
		return this.id_funcionarioexame;
	}

	public void setId_funcionarioexame(Long id_funcionarioexame) {
		this.id_funcionarioexame = id_funcionarioexame;
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
   
}
