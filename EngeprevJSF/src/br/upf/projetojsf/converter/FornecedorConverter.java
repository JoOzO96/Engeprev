package br.upf.projetojsf.converter;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.sistema.uteis.FabricaConexao;
import engeprev.beans.Fornecedor;
import engeprev.beans.Funcionario;


@FacesConverter(value = "fornecedorConverter")
public class FornecedorConverter implements Converter {
	@Override
	public Fornecedor getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				EntityManager em = FabricaConexao.getEntityManager();
				Fornecedor ret = em.find(Fornecedor.class, Long.parseLong(value));
				em.close();
				return ret;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro de Conversão do Fornecedor", "Fornecedor inválido."));
			}
		} else
			return null;
	}

	@Override
	 public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		 if(object != null) {
			return String.valueOf(((Fornecedor) object).getId_fornecedor());
		 } else
		 	return null;
	}
}