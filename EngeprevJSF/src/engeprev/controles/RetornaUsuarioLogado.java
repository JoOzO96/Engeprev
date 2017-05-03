package engeprev.controles;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import engeprev.beans.Usuario;

public class RetornaUsuarioLogado {

	public Usuario retornaUsuarioLogado(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		Usuario usuarioLogado = loginControle.getUsuarioLogado();
		return usuarioLogado;
	}
}
