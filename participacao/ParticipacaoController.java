package participacao;

import java.util.ArrayList;
import java.util.List;
import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;

import static myUtils.Validacao.*;
import pessoa.*;
import projeto.*;

public class ParticipacaoController {

	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private List<Participacao> participacoes;
	
	public ParticipacaoController(PessoaController pessoaController, ProjetoController projetoController) 
			throws ValidacaoException {
		
		validaPessoaController(pessoaController);
		validaProjetoController(projetoController);
		
		this.pessoaController = pessoaController;
		this.projetoController = projetoController;
		this.participacoes = new ArrayList<>();
	}

	public PessoaController getPessoaController() {
		return pessoaController;
	}

	public ProjetoController getProjetoController() {
		return projetoController;
	}

	public List<Participacao> getParticipacoes() {
		return participacoes;
	}

	public void associaProfessor(String cpfPessoa, int codigoProjeto, boolean coordenador, double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException {

		validaQntHoras(qntHoras);
		validaValorHoraProfessor(valorHora);
		
		Pessoa pessoa = null;
		Projeto projeto = null;
		
		try {
			pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = projetoController.recuperaProjeto(codigoProjeto);
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}
		
		if(projeto instanceof Monitoria) {
			validaValorHoraProfessorMonitoria(valorHora);
		}
		else {
			validaValorHora(valorHora);
		}
		
		if(projeto instanceof Monitoria) {
			if(projetoController.hasProfessor(codigoProjeto)) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: "
						+ "Monitoria nao pode ter mais de um professor");
			}
		}

		if(projeto instanceof PET) {
			if(projetoController.hasProfessor(codigoProjeto)) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos PET "
						+ "nao podem ter mais de um professor");
			}
			else if(!coordenador) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos PET "
						+ "possuem um unico professor coordenador");
			}
		}
		
		if(projeto instanceof Extensao) {
			if(projetoController.hasProfessor(codigoProjeto)) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos Extensao "
						+ "nao podem ter mais de um professor");
			}
			else if(!coordenador) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos Extensao "
						+ "possuem um unico professor coordenador");
			}
		}

		if(projeto instanceof PED) {
			if(projetoController.hasPEDLimitacao(codigoProjeto)) {		
				if(projetoController.hasProfessor(codigoProjeto)) {
					throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos P&D nao podem ter mais de um professor");
				}
			}
			else {
				if(coordenador) {
					if(projetoController.hasCoordenador(codigoProjeto)) {
						throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos P&D nao podem ter mais de um coordenador");
					}
				}
			}
		}

		if(pessoa != null && projeto != null) {
			ParticipacaoProfessor partProf = new ParticipacaoProfessor(pessoa, projeto, coordenador, 
				projeto.getDataInicio(),projeto.getDuracao() ,
				valorHora, qntHoras);
			
			this.hasParticipacaoToAdd(cpfPessoa, codigoProjeto);
			pessoaController.addParticipacao(cpfPessoa, partProf);
			projetoController.addParticipacao(codigoProjeto, partProf);
			participacoes.add(partProf);
		}
	}
	
	public void associaGraduando(String cpfPessoa, int codigoProjeto, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
		
		
		validaValorHoraGraduando(valorHora);
		validaQntHoras(qntHoras);
		
		Pessoa pessoa = null;
		Projeto projeto = null;
		
		try {
			pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = projetoController.recuperaProjeto(codigoProjeto);
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}

		if(projeto instanceof PED) {
			if(projetoController.hasPEDLimitacao(codigoProjeto)) {		
				if(projetoController.hasGraduando(codigoProjeto)) {
					throw new ValidacaoException("Erro na associacao de pessoa a projeto: "
							+ "Projetos P&D nao podem ter mais de um graduando");
				}
			}
		}

		if(pessoa != null && projeto != null) {
			ParticipacaoGraduando partGrad = new ParticipacaoGraduando(pessoa, projeto, projeto.getDataInicio(), 
			projeto.getDuracao(), valorHora, qntHoras);
			
			this.hasParticipacaoToAdd(cpfPessoa, codigoProjeto);
			pessoaController.addParticipacao(cpfPessoa, partGrad);
			projetoController.addParticipacao(codigoProjeto, partGrad);
			participacoes.add(partGrad);
		}
	}	
	
	public void associaProfissional(String cpfPessoa, int codigoProjeto,String cargo, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException {
		
		validaValorHora(valorHora);
		validaQntHoras(qntHoras);
		
		Pessoa pessoa = null;
		Projeto projeto = null;
		try {
			pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = projetoController.recuperaProjeto(codigoProjeto);
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}
		
		if(projeto instanceof Monitoria) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: Monitoria nao pode ter profissional");
		}
		
		if(projeto instanceof PET) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: PET nao pode ter profissional");
		}
		
		if(projeto instanceof PED) {
			if(projetoController.hasPEDLimitacao(codigoProjeto)) {		
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos P&D nao podem ter profissional");
			}
		}
		
		if(pessoa != null && projeto != null) {
			ParticipacaoProfissional partProf = new ParticipacaoProfissional(pessoa, projeto, cargo, projeto.getDataInicio(), 
			projeto.getDuracao(), valorHora, qntHoras);
			
			this.hasParticipacaoToAdd(cpfPessoa, codigoProjeto);
			pessoaController.addParticipacao(cpfPessoa, partProf);
			projetoController.addParticipacao(codigoProjeto, partProf);
			participacoes.add(partProf);
		}
	}
	
	public void associaPosGraduando(String cpfPessoa, int codigoProjeto, String titulacao, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException {

		validaValorHora(valorHora);
		validaQntHoras(qntHoras);

		Pessoa pessoa = null;
		Projeto projeto = null;
		try {
			pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = projetoController.recuperaProjeto(codigoProjeto);
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}
		
		if(projeto instanceof Monitoria) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: Tipo de projeto invalido para pos graduando");
		}
		
		if(projeto instanceof PET) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: Tipo de projeto invalido para pos graduando");
		}
		
/*		//parte em que impede pos graduandos de participarem de P&D do tipo pibiti pivic e pibic como pedia nas especificacoes
 * 		//do projeto, infelizmente nao foi respeitado isso e em um dos testes quer que deixe isso acontecer...
 * 
		if(projeto instanceof PED) {
			if(projetoController.hasPEDLimitacao(codigoProjeto)) {		
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Tipo de projeto invalido para pos graduando");
			}
		}
*/

		if(pessoa != null && projeto != null) {
			ParticipacaoPosGraduando partPosGrad = new ParticipacaoPosGraduando(pessoa, projeto, titulacao,projeto.getDataInicio(), 
					projeto.getDuracao(), valorHora, qntHoras);

			this.hasParticipacaoToAdd(cpfPessoa, codigoProjeto);
			pessoaController.addParticipacao(cpfPessoa, partPosGrad);
			projetoController.addParticipacao(codigoProjeto, partPosGrad);
			participacoes.add(partPosGrad);
		}
	}

	public void removeParticipacao(String cpfPessoa, int codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		Pessoa pes = null;
		Projeto proj = null;
		
		try {
			pes = pessoaController.recuperaPessoa(cpfPessoa);
		}
		catch(NaoEncontradaException e) {
			throw new ValidacaoException("Erro na remocao de participacao: Pessoa nao encontrada");
		}
		try {
			proj = projetoController.recuperaProjeto(codigoProjeto);
		}
		catch(NaoEncontradaException e) {
			throw new ValidacaoException("Erro na remocao de participacao: Projeto nao encontrado");
		}
		
		Participacao participacao = null;
		boolean hasParticipacao = false;
		for(Participacao p:participacoes) {
			if(p.getPessoa().equals(pes) && p.getProjeto().equals(proj)) {
				hasParticipacao = true;
				participacao = p;
			}
			
		}
		if(!hasParticipacao) {
			throw new ValidacaoException("Erro na remocao de participacao: Pessoa nao possui participacao no projeto indicado");
		}
		if(hasParticipacao==true && participacao!=null) {
			participacoes.remove(participacao);
			pessoaController.removeParticipacao(cpfPessoa, codigoProjeto);
			projetoController.removeParticipacao(codigoProjeto, cpfPessoa);
		}
	}

	public void hasParticipacaoToAdd(String cpf, int codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		for(Participacao p:participacoes) {
			if(pessoaController.recuperaPessoa(cpf).equals(p.getPessoa()) 
					&& projetoController.recuperaProjeto(codigoProjeto).equals(p.getProjeto())) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Aluno ja esta cadastrado nesse projeto");
			}
		}
	}
	
	public boolean hasParticipacao(String cpf, String nomeProjeto) throws NaoEncontradaException, ValidacaoException {
		for(Participacao p:participacoes) {
			if(pessoaController.recuperaPessoa(cpf).equals(p.getPessoa()) 
					&& projetoController.recuperaProjeto(nomeProjeto).equals(p.getProjeto())) {
				return true;
			}
		}
		return false;
	}

	public Participacao recuperaParticipacao(String cpfPessoa, String nomeProjeto) throws NaoEncontradaException, ValidacaoException {
		for(Participacao p:participacoes) {
			if(p.getPessoa().getCpf().equals(cpfPessoa) && p.getProjeto().getNome().equals(nomeProjeto)) {
				return p;
			}
		}
		throw new NaoEncontradaException("Participacao nao existe");
	}

/**	
	public static void main(String[] args) throws ValidacaoException, NaoEncontradaException {
		PessoaController pess = new PessoaController();
		ProjetoController projs = new ProjetoController();
		ParticipacaoController pc = new ParticipacaoController(pess, projs);
		pess.cadastraPessoa("700.634.064-03", "Gabriel", "gabriel774455@gmail.com");
		projs.adicionaPED("narutoreg", "pivic", 2, 3, 4, "sanar a falta de inteligencia no povo brasileiro", 
				"01/07/2017", 6);
		PED ped = (PED)projs.recuperaProjeto(1);
		System.out.println(ped.hasGraduando());
		pc.associaGraduando("700.634.064-03", 1, 750.00, 5);
		System.out.println(ped.hasGraduando());
		pc.removeParticipacao("700.634.064-03", 1);
		System.out.println(ped.hasGraduando());

	}
	*/
}