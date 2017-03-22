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



	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora, int qntHoras)
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
			ParticipacaoProfessor partProf = new ParticipacaoProfessor(pessoa, projeto, coordenador, 
				projeto.getDataInicio(),projeto.getDuracao() ,
				valorHora, qntHoras);
			participacoes.add(partProf);
			pessoaController.recuperaPessoa(cpfPessoa).adicionaPartcicipacao(partProf);
		}
	}
	
	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
		
		Pessoa pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		Projeto projeto = projetoController.recuperaProjeto(codigoProjeto);

		ParticipacaoGraduando partGrad = new ParticipacaoGraduando(pessoa, projeto, projeto.getDataInicio(), 
				projeto.getDuracao(), valorHora, qntHoras);
		
		participacoes.add(partGrad);
		pessoaController.recuperaPessoa(cpfPessoa).adicionaPartcicipacao(partGrad);
	}
	
	public void associaProfissional(String cpfPessoa, String codigoProjeto,String cargo, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException {
		
		Pessoa pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		Projeto projeto = projetoController.recuperaProjeto(codigoProjeto);
		ParticipacaoProfissional partProf = new ParticipacaoProfissional(pessoa, projeto, cargo, projeto.getDataInicio(), 
		projeto.getDuracao(), valorHora, qntHoras);
		participacoes.add(partProf);
	}
	
	public void associaPosGraduando(String cpfPessoa, String codigoProjeto, String titulacao, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException {
	
		Pessoa pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		Projeto projeto = projetoController.recuperaProjeto(codigoProjeto);
		
		ParticipacaoPosGraduando partPosGrad = new ParticipacaoPosGraduando(pessoa, projeto, titulacao,projeto.getDataInicio(), 
				projeto.getDuracao(), valorHora, qntHoras);
		
		participacoes.add(partPosGrad);
		pessoaController.recuperaPessoa(cpfPessoa).adicionaPartcicipacao(partPosGrad);
	}



	public void removeParticipacao(String cpfPessoa, int codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		Pessoa pes = pessoaController.recuperaPessoa(cpfPessoa);
		Projeto proj = projetoController.recuperaProjeto(codigoProjeto);
		
		for(Participacao p:participacoes) {
			if(p.getPessoa().equals(pes)) {
				participacoes.remove(p);
				pes.removeParticipacao(codigoProjeto);
				proj.removeParticipacao(cpfPessoa);
			}
		}
		
	}
}
