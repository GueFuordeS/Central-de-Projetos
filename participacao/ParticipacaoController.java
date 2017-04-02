package participacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;

import static myUtils.Validacao.*;
import pessoa.*;
import projeto.*;

/**
 * Classe de controle geral das Participacoes que
 * sao realizadas em nossa unidade, gerencia criacao, remocao,
 * guarda historico de participacoes e etc...
 * 
 * @author Gabriel Fernandes
 */
public class ParticipacaoController implements Serializable {
	private static final long serialVersionUID = 1L;
	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private List<Participacao> participacoes;

	/**
	 * Como uma associacao eh uma Pessoa ligada a um Projeto,
	 * necessita de um controle de Pessoa e um de Projeto em sua
	 * inicializacao, pois cada associacao depende de uma Pessoa e de
	 * um projeto ja existentes, necessitando ambos estarem previamente
	 * registrados em ambos os seus controles.
	 * 
	 * @param pessoaController		controle de Pessoa
	 * @param projetoController		controle de Projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public ParticipacaoController(PessoaController pessoaController, ProjetoController projetoController)
			throws ValidacaoException {

		validaPessoaController(pessoaController);
		validaProjetoController(projetoController);

		this.pessoaController = pessoaController;
		this.projetoController = projetoController;
		this.participacoes = new ArrayList<>();
	}

	/**
	 * Acessa o controle de Pessoa
	 * na qual este controle de participacoes 
	 * esta usando.
	 * 
	 * @return	o controle de Pessoa
	 */
	public PessoaController getPessoaController() {
		return pessoaController;
	}

	/**
	 * Acessa o controle de Projeto
	 * na qual este controle de participacoes 
	 * esta usando.
	 * 
	 * @return	o controle de Projeto
	 */
	public ProjetoController getProjetoController() {
		return projetoController;
	}

	/**
	 * Acessa a lista contendoo resgitro 
	 * de todas as participacoes ate o dado momento.
	 * 
	 * @return
	 */
	public List<Participacao> getParticipacoes() {
		return participacoes;
	}

	/**
	 * Faz uma vinculacao de uma Pessoa do controle
	 * de Pessoas a um Projeto do controle de Projetos
	 * como Professor, podendo ser ou nao um coordenador.
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @param codigoProjeto				identificador unico do Projeto
	 * @param coordenador				indicador se participacao de Professor eh como coordenador ou nao
	 * @param valorHora					valor por hora gerado a partir da quantidade de horas dedicadas a essa associacao
	 * @param qntHoras					quantidade de horas previstas para esta Pessoa se dedicar a esta associacao
	 * @throws NaoEncontradaException	em caso de ambos, Pessoa ou Projeto, nao estiverem em seus respectivos controles
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void associaProfessor(String cpfPessoa, int codigoProjeto, boolean coordenador, double valorHora,
			int qntHoras) throws NaoEncontradaException, ValidacaoException {

		validaQntHoras(qntHoras);
		validaValorHoraProfessor(valorHora);

		Pessoa pessoa = null;
		Projeto projeto = null;

		try {
			pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		} catch (NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = projetoController.recuperaProjeto(codigoProjeto);
		} catch (NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}

		if (projeto instanceof Monitoria) {
			validaValorHoraProfessorMonitoria(valorHora);
		} else {
			validaValorHora(valorHora);
		}

		if (projeto instanceof Monitoria) {
			if (projetoController.hasProfessor(codigoProjeto)) {
				throw new ValidacaoException(
						"Erro na associacao de pessoa a projeto: " + "Monitoria nao pode ter mais de um professor");
			}
		}

		if (projeto instanceof PET) {
			if (projetoController.hasProfessor(codigoProjeto)) {
				throw new ValidacaoException(
						"Erro na associacao de pessoa a projeto: Projetos PET " + "nao podem ter mais de um professor");
			} else if (!coordenador) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos PET "
						+ "possuem um unico professor coordenador");
			}
		}

		if (projeto instanceof Extensao) {
			if (projetoController.hasProfessor(codigoProjeto)) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos Extensao "
						+ "nao podem ter mais de um professor");
			} else if (!coordenador) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos Extensao "
						+ "possuem um unico professor coordenador");
			}
		}

		if (projeto instanceof PED) {
			if (projetoController.hasPEDLimitacao(codigoProjeto)) {
				if (projetoController.hasProfessor(codigoProjeto)) {
					throw new ValidacaoException(
							"Erro na associacao de pessoa a projeto: Projetos P&D nao podem ter mais de um professor");
				}
			} else {
				if (coordenador) {
					if (projetoController.hasCoordenador(codigoProjeto)) {
						throw new ValidacaoException(
								"Erro na associacao de pessoa a projeto: Projetos P&D nao podem ter mais de um coordenador");
					}
				}
			}
		}

		if (pessoa != null && projeto != null) {
			ParticipacaoProfessor partProf = new ParticipacaoProfessor(pessoa, projeto, coordenador,
					projeto.getDataInicio(), projeto.getDuracao(), valorHora, qntHoras);

			this.hasParticipacaoToAdd(cpfPessoa, codigoProjeto);
			pessoaController.addParticipacao(cpfPessoa, partProf);
			projetoController.addParticipacao(codigoProjeto, partProf);
			participacoes.add(partProf);
		}
	}

	/**
	 * Faz uma vinculacao de uma Pessoa do controle
	 * de Pessoas a um Projeto do controle de Projetos
	 * como Graduando.
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @param codigoProjeto				identificador unico do Projeto
	 * @param valorHora					valor por hora gerado a partir da quantidade de horas dedicadas a essa associacao
	 * @param qntHoras					quantidade de horas previstas para esta Pessoa se dedicar a esta associacao
	 * @throws NaoEncontradaException	em caso de ambos, Pessoa ou Projeto, nao estiverem em seus respectivos controles
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void associaGraduando(String cpfPessoa, int codigoProjeto, double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException {

		validaValorHoraGraduando(valorHora);
		validaQntHoras(qntHoras);

		Pessoa pessoa = null;
		Projeto projeto = null;

		try {
			pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		} catch (NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = projetoController.recuperaProjeto(codigoProjeto);
		} catch (NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}

		if (projeto instanceof PED) {
			if (projetoController.hasPEDLimitacao(codigoProjeto)) {
				if (projetoController.hasGraduando(codigoProjeto)) {
					throw new ValidacaoException("Erro na associacao de pessoa a projeto: "
							+ "Projetos P&D nao podem ter mais de um graduando");
				}
			}
		}

		if (pessoa != null && projeto != null) {
			ParticipacaoGraduando partGrad = new ParticipacaoGraduando(pessoa, projeto, projeto.getDataInicio(),
					projeto.getDuracao(), valorHora, qntHoras);

			this.hasParticipacaoToAdd(cpfPessoa, codigoProjeto);
			pessoaController.addParticipacao(cpfPessoa, partGrad);
			projetoController.addParticipacao(codigoProjeto, partGrad);
			participacoes.add(partGrad);
		}
	}

	/**
	 * Faz uma vinculacao de uma Pessoa do controle
	 * de Pessoas a um Projeto do controle de Projetos
	 * como Profissional, desempenhando uma funcao especifica.
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @param codigoProjeto				identificador unico do Projeto
	 * @param cargo						cargo em que profissional vai desempenhar no Projeto
	 * @param valorHora					valor por hora gerado a partir da quantidade de horas dedicadas a essa associacao
	 * @param qntHoras					quantidade de horas previstas para esta Pessoa se dedicar a esta associacao
	 * @throws NaoEncontradaException	em caso de ambos, Pessoa ou Projeto, nao estiverem em seus respectivos controles
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void associaProfissional(String cpfPessoa, int codigoProjeto, String cargo, double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException {

		validaValorHora(valorHora);
		validaQntHoras(qntHoras);

		Pessoa pessoa = null;
		Projeto projeto = null;
		try {
			pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		} catch (NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = projetoController.recuperaProjeto(codigoProjeto);
		} catch (NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}

		if (projeto instanceof Monitoria) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: Monitoria nao pode ter profissional");
		}

		if (projeto instanceof PET) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: PET nao pode ter profissional");
		}

		if (projeto instanceof PED) {
			if (projetoController.hasPEDLimitacao(codigoProjeto)) {
				throw new ValidacaoException(
						"Erro na associacao de pessoa a projeto: Projetos P&D nao podem ter profissional");
			}
		}

		if (pessoa != null && projeto != null) {
			ParticipacaoProfissional partProf = new ParticipacaoProfissional(pessoa, projeto, cargo,
					projeto.getDataInicio(), projeto.getDuracao(), valorHora, qntHoras);

			this.hasParticipacaoToAdd(cpfPessoa, codigoProjeto);
			pessoaController.addParticipacao(cpfPessoa, partProf);
			projetoController.addParticipacao(codigoProjeto, partProf);
			participacoes.add(partProf);
		}
	}

	/**
	 * Faz uma vinculacao de uma Pessoa do controle
	 * de Pessoas a um Projeto do controle de Projetos
	 * como pos-graduando, possuindo uma titulacao.
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @param codigoProjeto				identificador unico do Projeto
	 * @param titulacao					titulo de pos-graduacao na qual Pessoa esta cursando
	 * @param valorHora					valor por hora gerado a partir da quantidade de horas dedicadas a essa associacao
	 * @param qntHoras					quantidade de horas previstas para esta Pessoa se dedicar a esta associacao
	 * @throws NaoEncontradaException	em caso de ambos, Pessoa ou Projeto, nao estiverem em seus respectivos controles
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void associaPosGraduando(String cpfPessoa, int codigoProjeto, String titulacao, double valorHora,
			int qntHoras) throws NaoEncontradaException, ValidacaoException {

		validaValorHora(valorHora);
		validaQntHoras(qntHoras);

		Pessoa pessoa = null;
		Projeto projeto = null;
		try {
			pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		} catch (NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = projetoController.recuperaProjeto(codigoProjeto);
		} catch (NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}

		if (projeto instanceof Monitoria) {
			throw new ValidacaoException(
					"Erro na associacao de pessoa a projeto: Tipo de projeto invalido para pos graduando");
		}

		if (projeto instanceof PET) {
			throw new ValidacaoException(
					"Erro na associacao de pessoa a projeto: Tipo de projeto invalido para pos graduando");
		}

		if (projeto instanceof PED) {
			if (projetoController.hasPEDLimitacao(codigoProjeto)) {
				throw new ValidacaoException(
						"Erro na associacao de pessoa a projeto: Tipo de projeto invalido para pos graduando");
			}
		}

		if (pessoa != null && projeto != null) {
			ParticipacaoPosGraduando partPosGrad = new ParticipacaoPosGraduando(pessoa, projeto, titulacao,
					projeto.getDataInicio(), projeto.getDuracao(), valorHora, qntHoras);

			this.hasParticipacaoToAdd(cpfPessoa, codigoProjeto);
			pessoaController.addParticipacao(cpfPessoa, partPosGrad);
			projetoController.addParticipacao(codigoProjeto, partPosGrad);
			participacoes.add(partPosGrad);
		}
	}

	/**
	 * Realiza a remocao de Participacao
	 * dos registros, retirando tambem de ambos
	 * Pessoa e Projeto, o registro desta Participacao.
	 * 
	 * @param cpfPessoa					cpf da Pessoa a ser removida
	 * @param codigoProjeto				identificador unico do Projeto
	 * @throws NaoEncontradaException	em caso de Participacao, Pessoa ou Projeto nao existirem	
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void removeParticipacao(String cpfPessoa, int codigoProjeto)
			throws NaoEncontradaException, ValidacaoException {
		Pessoa pes = null;
		Projeto proj = null;

		try {
			pes = pessoaController.recuperaPessoa(cpfPessoa);
		} catch (NaoEncontradaException e) {
			throw new ValidacaoException("Erro na remocao de participacao: Pessoa nao encontrada");
		}
		try {
			proj = projetoController.recuperaProjeto(codigoProjeto);
		} catch (NaoEncontradaException e) {
			throw new ValidacaoException("Erro na remocao de participacao: Projeto nao encontrado");
		}

		Participacao participacao = null;
		boolean hasParticipacao = false;
		for (Participacao p : participacoes) {
			if (p.getPessoa().equals(pes) && p.getProjeto().equals(proj)) {
				hasParticipacao = true;
				participacao = p;
			}

		}
		if (!hasParticipacao) {
			throw new ValidacaoException(
					"Erro na remocao de participacao: Pessoa nao possui participacao no projeto indicado");
		}
		if (hasParticipacao == true && participacao != null) {
			participacoes.remove(participacao);
			pessoaController.removeParticipacao(cpfPessoa, codigoProjeto);
			projetoController.removeParticipacao(codigoProjeto, cpfPessoa);
		}
	}

	/**
	 * Usado para lancar excecao caso associacao entre
	 * Pessoa e Projeto a qual se esta tentando criar 
	 * uma nova associacao ja exista.
	 * 
	 * @param cpf						cpf da Pessoa
	 * @param codigoProjeto				codigo do Projeto
	 * @throws NaoEncontradaException	em caso de Pessoa/Projeto nao sendo encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void hasParticipacaoToAdd(String cpf, int codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		for (Participacao p : participacoes) {
			if (pessoaController.recuperaPessoa(cpf).equals(p.getPessoa())
					&& projetoController.recuperaProjeto(codigoProjeto).equals(p.getProjeto())) {
				throw new ValidacaoException(
						"Erro na associacao de pessoa a projeto: Aluno ja esta cadastrado nesse projeto");
			}
		}
	}

	/**
	 * Verifica se uma associacao entre Pessoa e Projeto
	 * ja esta nos registros, utilizando-se de um boolean para o retorno,
	 * usado principalmente em if's.
	 * Difere de {@link participacao.ParticipacaoController#hasParticipacaoToAdd}
	 * pois esta gera uma excecao ao confirmar Participacao, sendo necessario em casos
	 * especificos de uso.
	 * 
	 * @param cpf						cpf da Pessoa
	 * @param nomeProjeto				codigo do Projeto
	 * @return							true para Participacao ja existe, false para ainda nao existe essa associacao
	 * @throws NaoEncontradaException	em caso de Pessoa/Projeto nao sendo encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public boolean hasParticipacao(String cpf, String nomeProjeto) throws NaoEncontradaException, ValidacaoException {
		for (Participacao p : participacoes) {
			if (pessoaController.recuperaPessoa(cpf).equals(p.getPessoa())
					&& projetoController.recuperaProjeto(nomeProjeto).equals(p.getProjeto())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Recupera uma Participacao especifica
	 * dentre os registros do controle de participacoes.
	 * 
	 * @param cpfPessoa					cpf da Pessoa presente na associacao
	 * @param nomeProjeto				nome do Projeto da associacao
	 * @return							objeto do tipo Participacao
	 * @throws NaoEncontradaException	em caso de Participacao nao ser encontrada
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public Participacao recuperaParticipacao(String cpfPessoa, String nomeProjeto)
			throws NaoEncontradaException, ValidacaoException {
		for (Participacao p : participacoes) {
			if (p.getPessoa().getCpf().equals(cpfPessoa) && p.getProjeto().getNome().equals(nomeProjeto)) {
				return p;
			}
		}
		throw new NaoEncontradaException("Participacao nao existe");
	}
}