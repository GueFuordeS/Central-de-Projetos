package participacao;

import java.util.HashSet;
import java.util.Set;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import static myUtils.Validacao.*;
import pessoa.*;
import projeto.*;

public class ParticipacaoController {

	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private Set<Participacao> participacoes;
	
	public ParticipacaoController(PessoaController pessoaController, ProjetoController projetoController){
		this.pessoaController = pessoaController;
		this.projetoController = projetoController;
		this.participacoes = new HashSet<>();
	}
	
	public PessoaController getPessoaController() {
		return pessoaController;
	}

	public ProjetoController getProjetoController() {
		return projetoController;
	}

	public Set<Participacao> getParticipacoes() {
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
		
		if(coordenador) {
			if(projeto instanceof PED) {
				if(projeto.hasCoordenador()) {
						throw new ValidacaoException("Erro na associacao de pessoa a projeto: "
								+ "Projetos P&D nao podem ter mais de um coordenador");
				}
			}
		}
		
		if(projeto instanceof Monitoria) {
			if(projeto.hasProfessor()) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: "
						+ "Monitoria nao pode ter mais de um professor");
			}
		}
		
		if(projeto instanceof PED) {
			if(((PED) projeto).hasProfLimitacao()) {
				if(projeto.hasProfessor()) {
					throw new ValidacaoException("Erro na associacao de pessoa a projeto: Projetos P&D "
							+ "nao podem ter mais de um professor");
				}
			}
		}
		if(pessoa != null && projeto != null) {
			ParticipacaoProfessor partProf = new ParticipacaoProfessor(pessoa, projeto, coordenador, 
				projeto.getDataInicio(),projeto.getDuracao() ,
				valorHora, qntHoras);
			participacoes.add(partProf);
			pessoaController.addParticipacao(cpfPessoa, partProf);
			projetoController.addParticipacao(codigoProjeto, partProf);
		}
	}
	
	public void associaGraduando(String cpfPessoa, int codigoProjeto, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
		
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
		if(projeto instanceof PED && !((PED) projeto).getCategoria().toLowerCase().equals("coop")) {
			if(projeto.hasGraduando()) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: "
						+ "Projetos P&D nao podem ter mais de um graduando");
			}
		}

		if(pessoa != null && projeto != null) {
			ParticipacaoGraduando partGrad = new ParticipacaoGraduando(pessoa, projeto, projeto.getDataInicio(), 
			projeto.getDuracao(), valorHora, qntHoras);
			
			pessoaController.addParticipacao(cpfPessoa, partGrad);
			try {
				projetoController.addParticipacao(codigoProjeto, partGrad);
				
			}
			catch(ValidacaoException e) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Aluno ja esta cadastrado nesse projeto");
			}	
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
		
		if(pessoa != null && projeto != null) {
			ParticipacaoProfissional partProf = new ParticipacaoProfissional(pessoa, projeto, cargo, projeto.getDataInicio(), 
			projeto.getDuracao(), valorHora, qntHoras);
			participacoes.add(partProf);
			pessoaController.addParticipacao(cpfPessoa, partProf);
			projetoController.addParticipacao(codigoProjeto, partProf);
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

		if(pessoa != null && projeto != null) {
			ParticipacaoPosGraduando partPosGrad = new ParticipacaoPosGraduando(pessoa, projeto, titulacao,projeto.getDataInicio(), 
					projeto.getDuracao(), valorHora, qntHoras);
			
			participacoes.add(partPosGrad);
			pessoaController.addParticipacao(cpfPessoa, partPosGrad);
			projetoController.addParticipacao(codigoProjeto, partPosGrad);
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
			if(p.getPessoa().equals(pes) && p.getProjeto().getCodigo()==codigoProjeto) {
				hasParticipacao = true;
				participacao = p;
			}
			
		}
		if(hasParticipacao==false) {
			throw new ValidacaoException("Erro na remocao de participacao: Pessoa nao possui participacao no projeto indicado");
		}
		if(hasParticipacao==true && participacao!=null) {
			participacoes.remove(participacao);
			pes.removeParticipacao(codigoProjeto);
			proj.removeParticipacao(cpfPessoa);
		}
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