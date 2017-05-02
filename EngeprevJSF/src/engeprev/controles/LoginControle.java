package engeprev.controles;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.sistema.uteis.FabricaConexao;
import engeprev.beans.Empresa;
import engeprev.beans.Usuario;

@ManagedBean
@SessionScoped
public class LoginControle {
	private String usuario;
	private String senha;
	/**
	 * Atributo para controle do usu�rio logado. � inicializado quando
	 * informados login e senha v�lidos. Setado para null quando o usu�rio sair
	 * do sistema.
	 */
	private Usuario usuarioLogado = null;
	public Empresa empresa = null;

	public LoginControle() {
	}

	/**
	 * M�todo respons�vel por valodar o login e senha do usu�rio. Se for v�lido
	 * inicializa o usu�rio logado com a instancia do usu�rio respectivo ao
	 * login e senha informados e redireciona para a tela principal da
	 * aplica��o.
	 * 
	 * @throws Exception
	 */
	public String entrar() {
		EntityManager em = FabricaConexao.getEntityManager();
		Query qry = em.createQuery("from Usuario where usuario = :usuario and senha = :senha");
		qry.setParameter("usuario", usuario);
		qry.setParameter("senha", senha);
		List<Usuario> list = qry.getResultList();
		if (list.size() <= 0) {
			usuarioLogado = null;
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inv�lida!", "");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			em.close();
			return "";
			
		} else {
			usuarioLogado = list.get(0);
			empresa = (Empresa) em.createQuery("from Emitente where codemitente = " + usuarioLogado.getCodempresa()).getResultList().get(0);
			em.close();
			return "/Sistema/Home/Home.xhtml";
		}
	}

	/**
	 * M�todo respons�vel por desconectar o usu�rio e abrir a p�gina de login
	 * 
	 * @throws Exception
	 */
	public String sair() {
		usuarioLogado = null;
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usu�rio Desconectado!", "");
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		return "/Login/LoginForm.xhtml?faces-redirect=true";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
