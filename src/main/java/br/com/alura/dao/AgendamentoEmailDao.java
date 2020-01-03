package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.entity.AgendamentoEmail;
import br.com.alura.util.QueryBuilder;

@Stateless
public class AgendamentoEmailDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}

	public void atualizarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		entityManager.merge(agendamentoEmail);
	}

	public List<AgendamentoEmail> listarAgendamentoEmail() {
		return new QueryBuilder<AgendamentoEmail>(entityManager, AgendamentoEmail.class).getResult();
	}

	public List<AgendamentoEmail> listarAgendamentoEmailNaoEnviado() {
		return new QueryBuilder<AgendamentoEmail>(entityManager, AgendamentoEmail.class).where("enviado", false)
				.getResult();
	}

	public List<AgendamentoEmail> listarAgendamentoEmailNaoEnviadoPorEmail(String email) {
		return new QueryBuilder<AgendamentoEmail>(entityManager, AgendamentoEmail.class).where("email", email)
				.where("enviado", false).getResult();
	}
}