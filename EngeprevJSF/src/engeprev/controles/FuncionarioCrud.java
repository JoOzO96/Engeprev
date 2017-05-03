package engeprev.controles;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

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
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("controleLogin");
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Funcionario where id_empresa_id_empresa = " + usuario.getId_empresa()).getResultList();
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
		objeto = new Funcionario();
		return "FuncionarioForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		if (objeto.getId_funcionario() == null){		
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "FuncionarioList?faces-redirect=true";
		}else if (objeto.getId_funcionario() > 0){
			objeto.setId_empresa(usuario.getId_empresa());
			em.merge(objeto);
			em.getTransaction().commit();
			em.close();
			return "FuncionarioList?faces-redirect=true";
		}else{
			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensagem.setSummary("Não é possivel editar esse Funcionario pois ela e padrao do sistema.");
			FacesContext.getCurrentInstance().addMessage("", mensagem);
			return "";
		}
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
			}else {
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
						retorna.setSummary("Nao é possivel excluir o Funcionario, pois ela esta vinculada a um Funcionario");
					}else{
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
