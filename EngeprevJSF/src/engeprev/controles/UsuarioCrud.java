package engeprev.controles;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;

import br.sistema.uteis.FabricaConexao;
import engeprev.beans.Cidade;
import engeprev.beans.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioCrud {

	private List<Usuario> lista;
	private Usuario objeto;
	private Usuario usuario;

	public void inicializarLista() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		EntityManager em = FabricaConexao.getEntityManager();
		if (usuario.getGrauAcesso() == 1) {
			lista = em.createQuery("from Usuario").getResultList();
		} else {
			lista = em
					.createQuery(
							"from Usuario where id_empresa_id_empresa = " + usuario.getId_empresa().getId_empresa())
					.getResultList();
		}
		em.close();
	}

	public List<Cidade> completeCidade(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		List<Cidade> results = em.createQuery(
				"from Cidade where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
				.getResultList();
		em.close();
		return results;
	}

	public List<Cidade> completeEmpresa(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		List<Cidade> results = em.createQuery("from Empresa where upper(nomefantasia) like " + "'"
				+ query.trim().toUpperCase() + "%' " + "order by nomefantasia").getResultList();
		em.close();
		return results;
	}

	public String incluir() {
		objeto = new Usuario();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		objeto.setCodcidade(usuario.getCodcidade());
		return "UsuarioForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		if (usuario.getId_empresa() == objeto.getId_empresa()) {
			objeto.setId_empresa(usuario.getId_empresa());
		} else {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
			loginControle.setUsuarioLogado(objeto);
		}
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "UsuarioList?faces-redirect=true";
	}

	public void testaCidadeBranco() {

	}

	public String cancelar() {
		return "UsuarioList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Usuario.class, id);
		em.close();
		return "UsuarioForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		try {
			EntityManager em = FabricaConexao.getEntityManager();
			if (id > 0L) {
				objeto = em.find(Usuario.class, id);
				em.getTransaction().begin();
				em.remove(objeto);
				em.getTransaction().commit();
				em.close();
				return "UsuarioList?faces-redirect=true";
			} else {
				FacesMessage mensagem = new FacesMessage();
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				mensagem.setSummary("Não é possivel deletar esse Usuario pois ele e padrao do sistema.");
				FacesContext.getCurrentInstance().addMessage("", mensagem);
				return "";
			}
		} catch (Exception e) {
			FacesMessage retorna = new FacesMessage();
			retorna.setSeverity(FacesMessage.SEVERITY_ERROR);
			Throwable err = e.getCause();

			while (err != null) {
				if (err instanceof PSQLException) {
					PSQLException pe = (PSQLException) err;
					String erro = pe.toString();
					if (erro.contains("pedido")) {
						retorna.setSummary("Nao é possivel excluir o Usuario, pois ele esta vinculado a um Pedido");
					} else if (erro.contains("Usuarioendereco")) {
						retorna.setSummary("Nao é possivel excluir o Usuario, pois ela esta vinculada a um Usuario");
					} else {
						retorna.setSummary("Nao é possivel excluir a Situacao, pois ela é padrao do sistema");
					}
				}
				err = err.getCause();
			}
			FacesContext.getCurrentInstance().addMessage(null, retorna);
			System.out.println(retorna.toString());
			return "";
		}
	}

	public UsuarioCrud() {
		super();
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public Usuario getObjeto() {
		return objeto;
	}

	public void setObjeto(Usuario objeto) {
		this.objeto = objeto;
	}
}
