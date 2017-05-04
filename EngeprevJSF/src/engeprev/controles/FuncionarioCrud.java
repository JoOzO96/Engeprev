package engeprev.controles;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;

import br.sistema.uteis.FabricaConexao;
import engeprev.beans.Cidade;
import engeprev.beans.Funcionario;
import engeprev.beans.Usuario;

@ManagedBean
@SessionScoped
public class FuncionarioCrud {

	private List<Funcionario> lista;
	private Funcionario objeto;
	private Usuario usuario;

	public void inicializarLista() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em
				.createQuery(
						"from Funcionario where id_empresa_id_empresa = " + usuario.getId_empresa().getId_empresa())
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

	public List<Cidade> completeFuncao(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		List<Cidade> results = em.createQuery(
				"from Funcao where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
				.getResultList();
		em.close();
		return results;
	}

	public String incluir() {
		objeto = new Funcionario();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		LoginControle loginControle = (LoginControle) session.getAttribute("controleLogin");
		usuario = loginControle.getUsuarioLogado();
		objeto.setCodcidade(usuario.getCodcidade());
		return "FuncionarioForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		objeto.setId_empresa(usuario.getId_empresa());
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "FuncionarioList?faces-redirect=true";
	}

	public void testaCidadeBranco() {

	}

	public String cancelar() {
		return "FuncionarioList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Funcionario.class, id);
		em.close();
		return "FuncionarioForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		try {
			EntityManager em = FabricaConexao.getEntityManager();
			if (id > 0L) {
				objeto = em.find(Funcionario.class, id);
				em.getTransaction().begin();
				em.remove(objeto);
				em.getTransaction().commit();
				em.close();
				return "FuncionarioList?faces-redirect=true";
			} else {
				FacesMessage mensagem = new FacesMessage();
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				mensagem.setSummary("Não é possivel deletar esse Funcionario pois ele e padrao do sistema.");
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
						retorna.setSummary("Nao é possivel excluir o Funcionario, pois ele esta vinculado a um Pedido");
					} else if (erro.contains("Funcionarioendereco")) {
						retorna.setSummary(
								"Nao é possivel excluir o Funcionario, pois ela esta vinculada a um Funcionario");
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

	public FuncionarioCrud() {
		super();
	}

	public List<Funcionario> getLista() {
		return lista;
	}

	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
	}

	public Funcionario getObjeto() {
		return objeto;
	}

	public void setObjeto(Funcionario objeto) {
		this.objeto = objeto;
	}

}
