package engeprev.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: EntradaItem
 *
 */
@Entity

public class EntradaItem implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_entradaitem")
	@SequenceGenerator(name = "seq_entradaitem", sequenceName = "seq_entradaitem", allocationSize = 1, initialValue = 1)
	private Long id_entradaitem;
	@NotNull(message = "A quantidade deve ser informada")
	@Min(message = "A quantidade nao pode ser menor que 0", value = 0)
	private Double quantidade;
	@NotNull(message = "O valor unitario deve ser informado")
	private Double valor_un;
	@NotNull(message = "O CFOP deve ser informado")
	@NotEmpty(message = "O CFOP nao pode ser em branco")
	private String cfop;
	@NotNull(message = "O CST deve ser informado")
	@NotEmpty(message = "O CST nao pode ser em branco")
	private String cst;
	@NotNull(message = "O percentual de IPI nao pode ser nulo")
	private Double pipi;
	@NotNull(message = "O valor do IPI nao pode ser nulo")
	private Double vipi;
	@NotNull(message = "O percentual do ICMS nao pode ser nulo")
	private Double picms;
	@NotNull(message = "O valor do ICMS nao pode ser nulo")
	private Double vicms;
	@NotNull(message = "O percentual do MVA nao pode ser nulo")
	private Double pmva;
	@NotNull(message = "O valor do ST nao pode ser nulo")
	private Double vst;
	@NotNull(message = "O percentual de reducao de ICMS nao pode ser nulo")
	private Double predicms;
	@NotNull(message = "O percentual da reducao da ST nao pode ser nulo")
	private Double predst;
	@NotNull(message = "O percentual do COFINS nao pode ser nulo")
	private Double pconfins;
	@NotNull(message = "O valor do COFINS nao pode ser nulo")
	private Double vcofins;
	@ManyToOne
	@NotNull(message = "O EPI deve ser informado")
	private EPI id_epi;
	@ManyToOne
	@NotNull(message = "A entrada deve ser informada")
	private Entrada id_entrada;
	private static final long serialVersionUID = 1L;

	public EntradaItem() {
		super();
		quantidade = 0D;
		valor_un = 0D;
		cfop = "5405";
		cst = "500";
		pipi = 0D;
		vipi = 0D;
		picms = 0D;
		vicms = 0D;
		pmva = 0D;
		vst = 0D;
		predicms = 0D;
		predst = 0D;
		pconfins = 0D;
		vcofins = 0D;
	}

	public Long getId_entradaitem() {
		return this.id_entradaitem;
	}

	public void setId_entradaitem(Long id_entradaitem) {
		this.id_entradaitem = id_entradaitem;
	}

	public Double getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor_un() {
		return this.valor_un;
	}

	public void setValor_un(Double valor_un) {
		this.valor_un = valor_un;
	}

	public String getCfop() {
		return this.cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getCst() {
		return this.cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public Double getPipi() {
		return this.pipi;
	}

	public void setPipi(Double pipi) {
		this.pipi = pipi;
	}

	public Double getVipi() {
		return this.vipi;
	}

	public void setVipi(Double vipi) {
		this.vipi = vipi;
	}

	public Double getPicms() {
		return this.picms;
	}

	public void setPicms(Double picms) {
		this.picms = picms;
	}

	public Double getVicms() {
		return this.vicms;
	}

	public void setVicms(Double vicms) {
		this.vicms = vicms;
	}

	public Double getPmva() {
		return this.pmva;
	}

	public void setPmva(Double pmva) {
		this.pmva = pmva;
	}

	public Double getVst() {
		return this.vst;
	}

	public void setVst(Double vst) {
		this.vst = vst;
	}

	public Double getPredicms() {
		return this.predicms;
	}

	public void setPredicms(Double predicms) {
		this.predicms = predicms;
	}

	public Double getPredst() {
		return this.predst;
	}

	public void setPredst(Double predst) {
		this.predst = predst;
	}

	public Double getPconfins() {
		return this.pconfins;
	}

	public void setPconfins(Double pconfins) {
		this.pconfins = pconfins;
	}

	public Double getVcofins() {
		return this.vcofins;
	}

	public void setVcofins(Double vcofins) {
		this.vcofins = vcofins;
	}

	public EPI getId_epi() {
		return id_epi;
	}

	public void setId_epi(EPI id_epi) {
		this.id_epi = id_epi;
	}

	public Entrada getId_entrada() {
		return id_entrada;
	}

	public void setId_entrada(Entrada id_entrada) {
		this.id_entrada = id_entrada;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
