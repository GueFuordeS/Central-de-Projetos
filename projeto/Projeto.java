package projeto;

import static myUtils.Validacao.*;

import java.util.ArrayList;
import java.util.Collections;

import excecoes.ValidacaoException;
import myUtils.Date;
import participacao.Participacao;
import participacao.ParticipacaoGraduando;
import participacao.ParticipacaoPosGraduando;
import participacao.ParticipacaoProfessor;
import participacao.ParticipacaoProfissional;

/**
 * 
 * Classe responsavel por criar uma forma geral para projetos, uma abstracao para suas subclasses.
 * 
 * @author Gabriel Fernandes
 *
 */

public abstract class Projeto {
	private int codigo;
	private String nome;
	private String objetivo;
	private Date dataInicio;
	private int duracao;
	private Despesa despesas;
	private boolean checkingUASC;
	private Date dataTermino;
	private ArrayList<Participacao> participacoes;
	
	/** 
	 * Construtor de classe
	 * 
	 * @param nome - String nome do projeto.
	 * @param objetivo - String objetivo do projeto.
	 * @param dataInicio - String data de inicio do projeto.
	 * @param duracao - int duracao do projeto.
	 * @throws ValidacaoException - Ira validar os "param" nome, objetivo, dataInicio e duracao.
	 */
	public Projeto(int codigo, String nome, String objetivo, String dataInicio, int duracao) throws ValidacaoException {
		
		validaNomeProjeto(nome);
		validaObjetivo(objetivo);
		validaDuracao(duracao);
		
		this.codigo = codigo;
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = new Date(dataInicio);
		this.duracao = duracao;
		this.despesas = new Despesa();
		this.checkingUASC = false;
		this.dataTermino = this.dataInicio.geraDataTermino(duracao);
		participacoes = new ArrayList<>();
	}
	
	/**
	 * Acessador de codigo
	 * 
	 * @return inteiro com o codigo do projeto
	 */
	public int getCodigo() {
		return this.codigo;
	}
	
	/** Metodo que ira retornar o nome do projeto.
	 * 
	 * @return String - nome do projeto.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/** Metodo que ira retornar o objetivo do projeto.
	 * 
	 * @return String - objetivo do projeto.
	 */
	public String getObjetivo() {
		return this.objetivo;
	}
	
	/** Metodo que ira retornar a data de inicio do projeto.
	 * 
	 * @return String - data de inicio do projeto.
	 */
	public String getDataInicio() {
		return this.dataInicio.toString();
	}
	
	public String getDataInicioAMD() {
		return this.dataInicio.toStringAMD();
	}
	
	public String getDataTermino() {
		return this.dataTermino.toString();
	}
	
	public String getDataTerminoAMD() {
		return this.dataTermino.toStringAMD();
	}
	
	/** Metodo que ira retornar a duracao do projeto.
	 * 
	 * @return int - duracao do projeto.
	 */
	public int getDuracao() {
		return this.duracao;
	}
	
	public double getDespesasTotais() {
		return this.despesas.getMontanteBolsas() + this.despesas.getMontanteCusteio() + this.despesas.getMontanteCapital();
	}
	
	public double getMontanteBolsas() {
		return this.despesas.getMontanteBolsas();
	}


	public double getMontanteCusteio() {
		return this.despesas.getMontanteCusteio();
	}


	public double getMontanteCapital() {
		return this.despesas.getMontanteCapital();
	}
	
	public boolean getCheckingUASC() {
		return checkingUASC;
	}
	
	public Despesa getDespesas() {
		return this.despesas;
	}
	
	public String getCoordenadorNome() {
		String coordenador = "";
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoProfessor) {
				if(((ParticipacaoProfessor)p).isCoordenador()) {
					coordenador += p.getPessoa().getNome();
				}
			}
		}
		
		if(coordenador.trim().isEmpty()) coordenador += "sem coordenador ate o dado momento";
		return coordenador;
	}

	public String getSituacaoProjeto() throws ValidacaoException {
		if(this.dataTermino.compareTo(new Date("30/03/2017")) > 0) {
			return "em andamento";
		}
		return "finalizado";
	}

	public int getNumParticipantes() {
		return this.participacoes.size();
	}
	
	public int getNumGraduandos() {
		int numGraduandos = 0;
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoGraduando || p instanceof ParticipacaoPosGraduando) {
				numGraduandos++;
			}
		}
		return numGraduandos;
	}
	
	public int getNumParitipacaoGradPosProfi() {
		int numParticipacao = 0;
		for(Participacao p:this.participacoes) {
			if(!(p instanceof ParticipacaoProfessor)) {
				numParticipacao++;
			}
		}
		return numParticipacao;
	}
	
	public int getNumParticipacaoGraduandos() {
		int numGraduandos = 0;
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoGraduando) {
				numGraduandos++;
			}
		}
		return numGraduandos;
	}
	
	public int getNumParticipacaoPosGraduandos() {
		int numPosGraduandos = 0;
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoPosGraduando) {
				numPosGraduandos++;
			}
		}
		return numPosGraduandos;
	}
	
	public int getNumParticipacaoProfissionais() {
		int numProfissionais = 0;
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoProfissional) {
				numProfissionais++;
			}
		}
		return numProfissionais;
	}

	public void setTrueUASC() {
		this.checkingUASC = true;
	}
	
	public void setFalseUASC() {
		this.checkingUASC = false;
	}
	
	public void setNome(String nome) throws ValidacaoException {
		validaNomeProjeto(nome);
		this.nome = nome;
	}
	
	public void setObjetivo(String objetivo) throws ValidacaoException {
		validaObjetivoUpdate(objetivo);
		this.objetivo = objetivo;
	}
	
	public void setDataInicio(String data) throws ValidacaoException {
		try {
			this.dataInicio = new Date(data);
		}
		catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na atualizacao de projeto: Formato de data invalido");
		}
		
	}

	public void setDuracao(int duracao) throws ValidacaoException {
		validaInt(duracao);
		this.duracao = duracao;
	}
	
	public void removeParticipacao(String cpf) {
		boolean hasParticipacao = false;
		Participacao participacao = null;
		for(Participacao p:this.participacoes) {
			if(p.getPessoa().getCpf().equals(cpf)) {
				hasParticipacao = true;
				participacao = p;
			}
		}
		if(hasParticipacao==true && participacao!=null) {
			participacoes.remove(participacao);
		}
	}
	
	public ArrayList<Participacao> getParticipacoes() {
		return this.participacoes;
	}

	public void addicionaParticipacao(Participacao participacao) throws ValidacaoException {
		for(Participacao p:this.participacoes) {
			if(p.getPessoa().equals(participacao.getPessoa())) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Aluno ja esta cadastrado nesse projeto");
			}
		}
		this.participacoes.add(participacao);
		Collections.sort(participacoes);
	}
	
	public boolean hasProfessor() {
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoProfessor) {
				return true;
			}
		}
		return false;
	}

	public boolean hasGraduando() {
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoGraduando) {
				return true;
			}
		}
		return false;
	}

	public boolean hasCoordenador() {
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoProfessor) {
				if(((ParticipacaoProfessor) p).isCoordenador()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public abstract double calculaColaboracao();

	public abstract void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital) throws ValidacaoException;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (codigo != other.codigo)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public boolean hasParticipacao(String cpfPessoa) {
		for(Participacao p:participacoes) {
			if(p.getPessoa().getCpf().equals(cpfPessoa)) {
				return true;
			}
		}
		return false;
	}
}