package br.com.ambientinformatica.fatesg.sgep.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.com.ambientinformatica.util.Entidade;

@Entity
public class SessaoProva extends Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sessaoProva_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sessaoProva_seq", sequenceName = "sessaoProva_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	private Sessao sessao = new Sessao();

	@OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ItemQuestaoProva> itensQuestao = new ArrayList<ItemQuestaoProva>();

	public SessaoProva() {
		sessao = new Sessao();
	}

	public void addQuestao(QuestaoProva questao) {
		try {
			ItemQuestaoProva item = new ItemQuestaoProva(this, questao);
			if (itensQuestao.contains(item)) {
				throw new Exception("Já contem questão");
			} else {
				itensQuestao.add(item);
			}
		} catch (Exception e) {

		}
	}

	public void removeQuestao(ItemQuestaoProva item) {
		itensQuestao.remove(item);
	}

	public Integer getId() {
		return id;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public List<ItemQuestaoProva> getItensQuestao() {
		return itensQuestao;
	}

	public void setItensQuestao(List<ItemQuestaoProva> itensQuestao) {
		this.itensQuestao = itensQuestao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itensQuestao == null) ? 0 : itensQuestao.hashCode());
		result = prime * result + ((sessao == null) ? 0 : sessao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessaoProva other = (SessaoProva) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itensQuestao == null) {
			if (other.itensQuestao != null)
				return false;
		} else if (!itensQuestao.equals(other.itensQuestao))
			return false;
		if (sessao == null) {
			if (other.sessao != null)
				return false;
		} else if (!sessao.equals(other.sessao))
			return false;
		return true;
	}

	

}
