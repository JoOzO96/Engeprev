package br.upf.projetojsf.converter;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.sistema.uteis.FabricaConexao;
import engeprev.beans.Funcao;


@FacesConverter(value = "FuncaoConverter")
public class FuncaoConverter implements Converter {
	@Override
	public Funcao getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				EntityManager em = FabricaConexao.getEntityManager();
				Funcao ret = em.find(Funcao.class, Long.parseLong(value));
				em.close();
				return ret;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro de Convers�o da Funcao", "Funcao inv�lida."));
			}
		} else
			return null;
	}

	@Override
	 public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		 if(object != null) {
			return String.valueOf(((Funcao) object).getId_funcao());
		 } else
		 	return null;
	}
}