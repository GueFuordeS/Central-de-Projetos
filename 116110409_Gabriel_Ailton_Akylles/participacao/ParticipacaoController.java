package participacao;

import java.util.ArrayList;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import pessoa.*;
import projeto.*;

public class ParticipacaoController {
	
	private ArrayList<Participacao> participacoes;;
	
	public ParticipacaoController() {
		participacoes = new ArrayList<>();
	}

	public ArrayList<Participacao> getParticipacoes() {
		return participacoes;
	}



	public void associaProfessor(Pessoa pessoa, Projeto projeto, boolean coordenador, double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException{
		
		ParticipacaoProfessor partProf = new ParticipacaoProfessor(pessoa, projeto, coordenador, projeto.getDataInicio(),projeto.getDuracao() ,
				valorHora, qntHoras);
		participacoes.add(partProf);
		
	}
	
	public void associaGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
		
		ParticipacaoGraduando partGrad = new ParticipacaoGraduando(pessoa, projeto, projeto.getDataInicio(), 
				projeto.getDuracao(), valorHora, qntHoras);
		
		participacoes.add(partGrad);
	}
	
	public void associaProfissional(Pessoa pessoa, Projeto projeto, String cargo, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{

		ParticipacaoProfissional partProf = new 
				ParticipacaoProfissional(pessoa, projeto, cargo, projeto.getDataInicio(), 
						projeto.getDuracao(), valorHora, qntHoras);
		
		participacoes.add(partProf);
	}
	
	public void associaPosGraduando(Pessoa pessoa, Projeto projeto, String titulacao, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
	
		ParticipacaoPosGraduando partPosGrad = new ParticipacaoPosGraduando(pessoa, projeto, titulacao,projeto.getDataInicio(), 
				projeto.getDuracao(), valorHora, qntHoras);
		
		participacoes.add(partPosGrad);
	}
	
	public Participacao recuperaParticipacao(String cpf){
		Participacao part = null;
		
		for (Participacao participacao : participacoes) {
			participacao.getPessoa().getCpf().equals(cpf);
			part = participacao;
		}
		return part;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// fim da classe
