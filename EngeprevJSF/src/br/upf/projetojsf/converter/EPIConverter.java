package br.upf.projetojsf.converter;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.sistema.uteis.FabricaConexao;
import engeprev.beans.EPI;
import engeprev.beans.Funcao;


@FacesConverter(value = "epiConverter")
public class EPIConverter implements Converter {
	@Override
	public EPI getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				EntityManager em = FabricaConexao.getEntityManager();
				EPI ret = em.find(EPI.class, Long.parseLong(value));
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
			return String.valueOf(((EPI) object).getId_epi());
		 } else
		 	return null;
	}
}