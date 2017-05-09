package engeprev.beans;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
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
	@ManyToOne
	private Empresa id_empresa;
	@Valid
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "fk_funcionario", fetch=FetchType.EAGER)
	private List<EntregaEpiItem> entregaEpiItem;
	private static final long serialVersionUID = 1L;

	public EntregaEpi() {
		super();
		dataentrega = new Date();
		entregaEpiItem = new ArrayList<>();
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
	public Empresa getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Empresa id_empresa) {
		this.id_empresa = id_empresa;
	}
	public List<EntregaEpiItem> getEntregaEpiItem() {
		return entregaEpiItem;
	}
	public void setEntregaEpiItem(List<EntregaEpiItem> entregaEpiItem) {
		this.entregaEpiItem = entregaEpiItem;
	}
   
}
