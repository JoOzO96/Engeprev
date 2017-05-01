package engeprev.beans;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Entrada
 *
 */
@Entity

public class Entrada implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_entrada")
	@SequenceGenerator(name = "seq_entrada", sequenceName = "seq_entrada", allocationSize = 1, initialValue = 1)
	private Long id_entrada;
	@NotNull(message = "Deve informar o fornecedor!")
	@ManyToOne
	private Fornecedor id_fornecedor;
	@NotNull
	@Max(message="O numero maxima da nota é 9.223.372.036.854.775.807", value= Long.MAX_VALUE)
	private Long numeronota;
	@NotNull(message="A data da nota deve ser informada")
	@Temporal(TemporalType.DATE)
	private Date data;
	@NotNull(message = "As entradas de  produtos devem ser inicializados!")
	@Size(min = 1, message = "A entrada deve conter pelo menos 1 item")
	@Valid
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "id_entrada")
	private List<EntradaItem> itensEntrada;
	private static final long serialVersionUID = 1L;

	public Entrada() {
		super();
		data = new Date();
		itensEntrada = new ArrayList<>();
	}   
	public Long getId_entrada() {
		return this.id_entrada;
	}

	public void setId_entrada(Long id_entrada) {
		this.id_entrada = id_entrada;
	}   
	public Fornecedor getId_fornecedor() {
		return this.id_fornecedor;
	}

	public void setId_fornecedor(Fornecedor id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}   
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}
   
}
