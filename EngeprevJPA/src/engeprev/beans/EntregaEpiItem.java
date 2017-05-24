package engeprev.beans;

import engeprev.beans.EPI;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: EntregaEpiItem
 *
 */
@Entity

public class EntregaEpiItem implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_entregaitem")
	@SequenceGenerator(name = "seq_entregaitem", sequenceName = "seq_entregaitem", allocationSize = 1, initialValue = 1)
	private Long id_entregaepiitem;
	@ManyToOne
	@NotNull(message = "O EPI deve ser informado")
	private EPI id_epi;
	@NotNull(message = "A data de entrega nao pode ser em nula")
	@Temporal(TemporalType.DATE)
	private Date dataentrega;
	@ManyToOne(optional = false)
	@NotNull(message = "O funcionario deve ser inicializado!")
	private EntregaEpi id_entregaepi;
	@Min(message="A quandidade deve ser maior que {min}", value=0)
	private Double quantidade;
	private static final long serialVersionUID = 1L;

	public EntregaEpiItem() {
		super();
		dataentrega = new Date();
	}

	public Long getId_entregaepiitem() {
		return this.id_entregaepiitem;
	}

	public void setId_entregaepiitem(Long id_entregaepiitem) {
		this.id_entregaepiitem = id_entregaepiitem;
	}

	public EPI getId_epi() {
		return this.id_epi;
	}

	public void setId_epi(EPI id_epi) {
		this.id_epi = id_epi;
	}

	public Date getDataentrega() {
		return this.dataentrega;
	}

	public void setDataentrega(Date dataentrega) {
		this.dataentrega = dataentrega;
	}

	public EntregaEpi getId_entregaepi() {
		return id_entregaepi;
	}

	public void setId_entregaepi(EntregaEpi id_entregaepi) {
		this.id_entregaepi = id_entregaepi;
	}
	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

}
