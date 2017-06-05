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
import engeprev.beans.Empresa;
import engeprev.beans.Usuario;

@ManagedBean
@SessionScoped
public class EmpresaCrud {

	private List<Empresa> lista;
	private Empresa objeto;
	private Usuario usuario;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em
				.createQuery(
						"from Empresa")
				.getResultList();
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

	public String incluir() {
		objeto = new Empresa();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		objeto.setCodcidade(usuario.getCodcidade());
		return "EmpresaForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "EmpresaList?faces-redirect=true";
	}

	public void testaCidadeBranco() {

	}

	public String cancelar() {
		return "EmpresaList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Empresa.class, id);
		em.close();
		return "EmpresaForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		try {
			EntityManager em = FabricaConexao.getEntityManager();
		
				objeto = em.find(Empresa.class, id);
				em.getTransaction().begin();
				em.remove(objeto);
				em.getTransaction().commit();
				em.close();
				return "EmpresaList?faces-redirect=true";
		
		} catch (Exception e) {
			FacesMessage retorna = new FacesMessage();
			retorna.setSeverity(FacesMessage.SEVERITY_ERROR);
			Throwable err = e.getCause();

			while (err != null) {
				if (err instanceof PSQLException) {
					PSQLException pe = (PSQLException) err;
					String erro = pe.toString();
					if (erro.contains("pedido")) {
						retorna.setSummary("Nao é possivel excluir o Empresa, pois ele esta vinculado a um Pedido");
					} else if (erro.contains("Empresaendereco")) {
						retorna.setSummary(
								"Nao é possivel excluir o Empresa, pois ela esta vinculada a um Empresa");
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

	public EmpresaCrud() {
		super();
	}

	public List<Empresa> getLista() {
		return lista;
	}

	public void setLista(List<Empresa> lista) {
		this.lista = lista;
	}

	public Empresa getObjeto() {
		return objeto;
	}

	public void setObjeto(Empresa objeto) {
		this.objeto = objeto;
	}
}
