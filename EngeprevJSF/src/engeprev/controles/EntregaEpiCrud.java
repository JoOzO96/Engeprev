package engeprev.controles;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.sistema.uteis.FabricaConexao;
import engeprev.beans.EPI;
import engeprev.beans.EntregaEpi;
import engeprev.beans.EntregaEpiItem;
import engeprev.beans.Funcionario;
import engeprev.beans.Usuario;

@ManagedBean
@SessionScoped
public class EntregaEpiCrud {

	private List<EntregaEpi> lista;
	private EntregaEpi objeto;
	private Usuario usuario;

	public void inicializarLista() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em
				.createQuery(
						"from EntregaEpi where id_empresa_id_empresa = " + usuario.getId_empresa().getId_empresa())
				.getResultList();
		em.close();
	}

	public List<Funcionario> completeFuncionario(String query) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		EntityManager em = FabricaConexao.getEntityManager();
		List<Funcionario> results = em.createQuery(
				"from Funcionario where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + " and id_empresa_id_empresa = "+ usuario.getId_empresa().getId_empresa() +" order by nome")
				.getResultList();
		em.close();
		System.out.println(results);
		return results;
	}

	public List<EPI> completeEPI(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		List<EPI> results = em
				.createQuery(
						"from EPI where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
				.getResultList();
		em.close();
		return results;
	}

	public String incluir() {
		objeto = new EntregaEpi();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		return "EntregaEpiForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		objeto.setId_empresa(usuario.getId_empresa());
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "EntregaEpiList?faces-redirect=true";
	}

	public void testaCidadeBranco() {

	}

	public String cancelar() {
		return "EntregaEpiList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(EntregaEpi.class, id);
		em.close();
		return "EntregaEpiForm?faces-redirect=true";
	}

	public String excluir(Long id) {

		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(EntregaEpi.class, id);
		em.getTransaction().begin();
		em.remove(objeto);
		em.getTransaction().commit();
		em.close();
		return "EntregaEpiList?faces-redirect=true";

	}

	public EntregaEpiCrud() {
		super();
	}

	public List<EntregaEpi> getLista() {
		return lista;
	}

	public void setLista(List<EntregaEpi> lista) {
		this.lista = lista;
	}

	public EntregaEpi getObjeto() {
		return objeto;
	}

	public void setObjeto(EntregaEpi objeto) {
		this.objeto = objeto;
	}

	// --------------------------------------------------------
	// Para os itens
	// --------------------------------------------------------

	private EntregaEpiItem entregaEpiItem;

	private Integer rowIndex = null;

	public void incluirEntregaEpiItem() {
		rowIndex = null;
		entregaEpiItem = new EntregaEpiItem();
	}

	public void alterarEntregaEpiItem(Integer rowIndex) {
		this.rowIndex = rowIndex;
		entregaEpiItem = objeto.getEntregaEpiItem().get(rowIndex);

	}

	public void excluirEntregaEpiItem(Integer rowIndex) {
		objeto.getEntregaEpiItem().remove(rowIndex.intValue());
	}

	public void gravarEntregaEpiItem() {
		if (this.rowIndex == null) {
			entregaEpiItem.setId_entregaepi(objeto);
			objeto.getEntregaEpiItem().add(entregaEpiItem);
		} else {
			objeto.getEntregaEpiItem().set(rowIndex, entregaEpiItem);
		}
		rowIndex = null;
		entregaEpiItem = null;
	}

	public void cancelarEntregaEpiItem() {
		rowIndex = null;
		entregaEpiItem = null;
	}

	public EntregaEpiItem getEntregaEpiItem() {
		return entregaEpiItem;
	}

	public void setEntregaEpiItem(EntregaEpiItem entregaEpiItem) {
		this.entregaEpiItem = entregaEpiItem;
	}

}
