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
import engeprev.beans.Fornecedor;
import engeprev.beans.Usuario;

@ManagedBean
@SessionScoped
public class FornecedorCrud {

	private List<Fornecedor> lista;
	private Fornecedor objeto;
	private Usuario usuario;

	public void inicializarLista() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		RetornaUsuarioLogado logado = new RetornaUsuarioLogado();
		usuario = logado.retornaUsuarioLogado();
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Fornecedor where id_empresa_id_empresa = " + usuario.getId_empresa().getId_empresa())
				.getResultList();
		em.close();
	}

	public String incluir() {
		objeto = new Fornecedor();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		RetornaUsuarioLogado logado = new RetornaUsuarioLogado();
		usuario = logado.retornaUsuarioLogado();
		objeto.setCodcidade(usuario.getCodcidade());
		return "FornecedorForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		RetornaUsuarioLogado logado = new RetornaUsuarioLogado();
		usuario = logado.retornaUsuarioLogado();
		objeto.setId_empresa(usuario.getId_empresa());
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "FornecedorList?faces-redirect=true";

	}

	public String cancelar() {
		return "FornecedorList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Fornecedor.class, id);
		em.close();
		return "FornecedorForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		try {
			EntityManager em = FabricaConexao.getEntityManager();

			objeto = em.find(Fornecedor.class, id);
			em.getTransaction().begin();
			em.remove(objeto);
			em.getTransaction().commit();
			em.close();
			return "FornecedorList?faces-redirect=true";

		} catch (Exception e) {
			FacesMessage retorna = new FacesMessage();
			retorna.setSeverity(FacesMessage.SEVERITY_ERROR);
			Throwable err = e.getCause();

			while (err != null) {
				if (err instanceof PSQLException) {
					PSQLException pe = (PSQLException) err;
					String erro = pe.toString();
					if (erro.contains("funcionario")) {
						retorna.setSummary("Nao é possivel excluir a Fornecedor, pois ele esta vinculado a um Funcionario");
					} else {
						retorna.setSummary("Nao é possivel excluir a Fornecedor, erro nao conhecido, contate o suporte!!");
					} 
				}
				err = err.getCause();
			}
			FacesContext.getCurrentInstance().addMessage(null, retorna);
			return "";
		}
	}

	public FornecedorCrud() {
		super();
	}

	public List<Fornecedor> getLista() {
		return lista;
	}

	public void setLista(List<Fornecedor> lista) {
		this.lista = lista;
	}

	public Fornecedor getObjeto() {
		return objeto;
	}

	public void setObjeto(Fornecedor objeto) {
		this.objeto = objeto;
	}

}
