package engeprev.controles;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.sistema.uteis.FabricaConexao;
import engeprev.beans.EPI;
import engeprev.beans.Entrada;
import engeprev.beans.EntradaItem;
import engeprev.beans.Fornecedor;
import engeprev.beans.Usuario;

@ManagedBean
@SessionScoped
public class EntradaCrud {

	private List<Entrada> lista;
	private Entrada objeto;
	private Usuario usuario;

	public void inicializarLista() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		EntityManager em = FabricaConexao.getEntityManager();
		if (usuario.getGrauAcesso() == 1) {
			lista = em.createQuery("from Entrada").getResultList();
		} else {
			lista = em
					.createQuery(
							"from Entrada where id_empresa_id_empresa = " + usuario.getId_empresa().getId_empresa())
					.getResultList();
		}
		em.close();
	}

	public List<EPI> completeEPI(String query) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		EntityManager em = FabricaConexao.getEntityManager();
		List<EPI> results = null;
		if (usuario.getGrauAcesso() == 1) {
			results = em.createQuery("from EPI where upper(nome) like " + "'" + query.trim().toUpperCase() + "%'")
					.getResultList();
		} else {
			results = em.createQuery("from EPI where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' "
					+ "and id_empresa_id_empresa = " + usuario.getId_empresa().getId_empresa() + " order by nome")
					.getResultList();
		}
		em.close();
		return results;
	}

	public List<Fornecedor> completeFornecedor(String query) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		EntityManager em = FabricaConexao.getEntityManager();
		List<Fornecedor> results = null;
		if (usuario.getGrauAcesso() == 1) {
			results = em
					.createQuery("from Fornecedor where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' ")
					.getResultList();
		} else {
			results = em.createQuery("from Fornecedor where upper(nome) like " + "'" + query.trim().toUpperCase()
					+ "%' " + "and id_empresa_id_empresa = " + usuario.getId_empresa().getId_empresa()
					+ " order by nome").getResultList();
		}
		em.close();
		return results;
	}

	public String incluir() {
		objeto = new Entrada();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		return "EntradaForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		objeto.setId_empresa(usuario.getId_empresa());
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "EntradaList?faces-redirect=true";
	}

	public void testaCidadeBranco() {

	}

	public String cancelar() {
		return "EntradaList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Entrada.class, id);
		em.close();
		return "EntradaForm?faces-redirect=true";
	}

	public String excluir(Long id) {

		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Entrada.class, id);
		em.getTransaction().begin();
		em.remove(objeto);
		em.getTransaction().commit();
		em.close();
		return "EntradaList?faces-redirect=true";

	}

	public EntradaCrud() {
		super();
	}

	public List<Entrada> getLista() {
		return lista;
	}

	public void setLista(List<Entrada> lista) {
		this.lista = lista;
	}

	public Entrada getObjeto() {
		return objeto;
	}

	public void setObjeto(Entrada objeto) {
		this.objeto = objeto;
	}

	// --------------------------------------------------------
	// Para os itens
	// --------------------------------------------------------

	private EntradaItem entradaItem;

	private Integer rowIndex = null;

	public void incluirEntradaItem() {
		rowIndex = null;
		entradaItem = new EntradaItem();
	}

	public void alterarEntradaItem(Integer rowIndex) {
		this.rowIndex = rowIndex;
		entradaItem = objeto.getEntradaItem().get(rowIndex);

	}

	public void excluirEntradaItem(Integer rowIndex) {
		objeto.getEntradaItem().remove(rowIndex.intValue());
	}

	public void gravarEntradaItem() {
		if (this.rowIndex == null) {
			entradaItem.setId_entrada(objeto);
			objeto.getEntradaItem().add(entradaItem);
		} else {
			objeto.getEntradaItem().set(rowIndex, entradaItem);
		}
		rowIndex = null;
		entradaItem = null;
	}

	public void cancelarEntradaItem() {
		rowIndex = null;
		entradaItem = null;
	}

	public EntradaItem getEntradaItem() {
		return entradaItem;
	}

	public void setEntradaItem(EntradaItem entradaItem) {
		this.entradaItem = entradaItem;
	}

}
