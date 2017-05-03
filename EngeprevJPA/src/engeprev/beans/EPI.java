package engeprev.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: EPI
 *
 */
@Entity

public class EPI implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_epi")
	@SequenceGenerator(name = "seq_epi", sequenceName = "seq_epi", allocationSize = 1, initialValue = 1)
	private Long id_epi;
	@NotNull(message="O nome do EPI deve ser informado")
	@NotEmpty(message="O nome do EPI deve ser informado")
	private String nome;
	@NotNull(message="O CA do EPI deve ser informado")
	@NotEmpty(message="O CA do EPI deve ser informado")
	private String ca;
	@NotNull(message="Os dias de validade do EPI deve ser informado")
	@NotEmpty(message="Os dias de validade do EPI deve ser informado")
	private String dias;
	@NotNull(message="Os dias de validade do EPI deve ser informado")
	private Double quantidade;
	@NotNull(message="O valor unitario deve ser informado")
	private Double valor_un;
	@ManyToOne
	private Empresa id_empresa;
	private static final long serialVersionUID = 1L;

	public EPI() {
		super();
		quantidade = 0D;
		valor_un = 0D;
	}   
	public Long getId_epi() {
		return this.id_epi;
	}

	public void setId_epi(Long id_epi) {
		this.id_epi = id_epi;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getCa() {
		return this.ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}   
	public String getDias() {
		return this.dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor_un() {
		return valor_un;
	}
	public void setValor_un(Double valor_un) {
		this.valor_un = valor_un;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Empresa getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Empresa id_empresa) {
		this.id_empresa = id_empresa;
	}
   
}
