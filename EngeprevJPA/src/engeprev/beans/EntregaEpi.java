package engeprev.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: EntregaEpi
 *
 */
@Entity

public class EntregaEpi implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_entregaepi")
	@SequenceGenerator(name = "seq_entregaepi", sequenceName = "seq_entregaepi", allocationSize = 1, initialValue = 1)
	private Long id_entregaepi;
	@NotNull(message="O funcionario deve ser informado")
	private Funcionario id_funcionario;
	@NotNull(message="A data de entrega deve ser informada")
	@Temporal(TemporalType.DATE)
	private Date dataentrega;
	private static final long serialVersionUID = 1L;

	public EntregaEpi() {
		super();
		dataentrega = new Date();
	}   
	public Long getId_entregaepi() {
		return this.id_entregaepi;
	}

	public void setId_entregaepi(Long id_entregaepi) {
		this.id_entregaepi = id_entregaepi;
	}   
	public Funcionario getId_funcionario() {
		return this.id_funcionario;
	}

	public void setId_funcionario(Funcionario id_funcionario) {
		this.id_funcionario = id_funcionario;
	}   
	public Date getDataentrega() {
		return this.dataentrega;
	}

	public void setDataentrega(Date dataentrega) {
		this.dataentrega = dataentrega;
	}
   
}
