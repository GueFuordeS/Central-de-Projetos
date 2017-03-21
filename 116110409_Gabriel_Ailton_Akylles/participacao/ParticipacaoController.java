package participacao;

import java.util.ArrayList;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import pessoa.*;
import projeto.*;

public class ParticipacaoController {

	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private ArrayList<Participacao> participacoes = new ArrayList<>();
	
	public ParticipacaoController(PessoaController pessoaController, ProjetoController projetoController){
		this.pessoaController = pessoaController;
		this.projetoController = projetoController;
	}
	
	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException{
		
		Pessoa pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		Projeto projeto = projetoController.recuperaProjeto(codigoProjeto);

		ParticipacaoProfessor partProf = new ParticipacaoProfessor(pessoa, projeto, coordenador, projeto.getDataInicio(),projeto.getDuracao() ,
				qntHoras, valorHora);
		
		participacoes.add(partProf);
	}
	
	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras) throws NaoEncontradaException, ValidacaoException{
		
		Pessoa pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		Projeto projeto = projetoController.recuperaProjeto(codigoProjeto);

		ParticipacaoGraduando partGrad = new ParticipacaoGraduando(pessoa, projeto, projeto.getDataInicio(), projeto.getDuracao(), qntHoras, valorHora);
		participacoes.add(partGrad);
	}
	
	public void associaProfissional(String cpfPessoa, String codigoProjeto,String cargo, double valorHora, int qntHoras) throws NaoEncontradaException, ValidacaoException{

		Pessoa pessoa = pessoaController.recuperaPessoa(cpfPessoa);
		Projeto projeto = projetoController.recuperaProjeto(codigoProjeto);
//		ParticipacaoProfissional partProf = 
//				new ParticipacaoProfissional(pessoa, projeto, cargo, dataInicio, duracaoEmMeses, qtdeHorasDedicadas, valorDaHora)
	}
}// fim da classe
