package br.upf.projetojsf.converter;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.sistema.uteis.FabricaConexao;
import engeprev.beans.Funcionario;


@FacesConverter(value = "funcionarioConverter")
public class FuncionarioConverter implements Converter {
	@Override
	public Funcionario getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				EntityManager em = FabricaConexao.getEntityManager();
				Funcionario ret = em.find(Funcionario.class, Long.parseLong(value));
				em.close();
				return ret;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro de Conversão da Funcao", "Funcao inválida."));
			}
		} else
			return null;
	}

	@Override
	 public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		 if(object != null) {
			return String.valueOf(((Funcionario) object).getId_funcionario());
		 } else
		 	return null;
	}
}