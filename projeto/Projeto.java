package projeto;

import static myUtils.Validacao.*;

import java.io.Serializable;
import java.time.LocalDateTime;
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
 * Classe responsavel por criar uma forma geral para projetos, uma abstracao para suas subclasses.
 * 
 * @author Gabriel Fernandes
 */
public abstract class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;
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
	 * Construtor de classe.
	 * 
	 * @param codigo				identificador unico de cada projeto
	 * @param nome  				string nome do projeto
	 * @param objetivo  			string objetivo do projeto
	 * @param dataInicio  			string data de inicio do projeto
	 * @param duracao  				int duracao do projeto
	 * @throws ValidacaoException  	ira validar os "param" nome, objetivo, dataInicio e duracao
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
	 * Acessador de codigo(identificador unico de um projeto).
	 * 
	 * @return 		inteiro com o codigo do projeto
	 */
	public int getCodigo() {
		return this.codigo;
	}
	
	/** 
	 * Metodo que ira retornar o nome do projeto.
	 * 
	 * @return String  	nome do projeto
	 */
	public String getNome() {
		return this.nome;
	}
	
	/** 
	 * Metodo que ira retornar o objetivo do projeto.
	 * 
	 * @return 		string objetivo do projeto
	 */
	public String getObjetivo() {
		return this.objetivo;
	}
	
	/** 
	 * Metodo que ira retornar a data de inicio do projeto.
	 * 
	 * @return 		string data de inicio do projeto
	 */
	public String getDataInicio() {
		return this.dataInicio.toString();
	}
	
	/**
	 * Acessador de data de inicio do projeto
	 * com formato do retorno em aaaa-mm-dd em vez de dd/mm/aaaa.
	 * 
	 * @return		string contendo a data
	 */
	public String getDataInicioAMD() {
		return this.dataInicio.toStringAMD();
	}
	
	/**
	 * A partir da data de inicio e duracao planejada, eh possivel
	 * ter uma previsao da data em que o projeto termina, retorna essa data.
	 * 
	 * @return 		string contendo a data de termino
	 */
	public String getDataTermino() {
		return this.dataTermino.toString();
	}
	
	/**
	 * Versao aaaa-mm-dd da data de termino.
	 * 
	 * @return 		string contendo a data de termino
	 */
	public String getDataTerminoAMD() {
		return this.dataTermino.toStringAMD();
	}
	
	/** 
	 * Metodo que ira retornar a duracao planejada do projeto(em meses).
	 * 
	 * @return  	duracao do projeto
	 */
	public int getDuracao() {
		return this.duracao;
	}
	
	/**
	 * Acessa as despesas planejadas para o projetos, armazenadas na classse Despesa
	 * fazendo assim somatorio entre as 3, retornando a despesas total.
	 * 
	 * @return 		despesas totais para o projeto
	 */
	public double getDespesasTotais() {
		return this.despesas.getMontanteBolsas() + this.despesas.getMontanteCusteio() + this.despesas.getMontanteCapital();
	}
	
	/**
	 * Get com acesso direto a despesas para bolsas do projeto.
	 * 
	 * @return 		despesas com bolsas
	 */
	public double getMontanteBolsas() {
		return this.despesas.getMontanteBolsas();
	}

	/**
	 * Get com acesso direto a despesas para custeio do projeto.
	 * 
	 * @return 		despesas com custeio
	 */
	public double getMontanteCusteio() {
		return this.despesas.getMontanteCusteio();
	}

	/**
	 * Get com acesso direto a despesas para capital do projeto.
	 * 
	 * @return 		despesas com capital
	 */
	public double getMontanteCapital() {
		return this.despesas.getMontanteCapital();
	}
	
	/**
	 * Acessa a condicao atual do projeto quanto a contribuicoes com a uasc
	 * se ja foi contribuido eh setado em true, caso contrario fica como false.
	 * 
	 * @return		boolean, true para ja contribuido, false para nao contribuido
	 */
	public boolean getCheckingUASC() {
		return checkingUASC; //note, ja ter contribuido nao significa necessariamente com um valor em dinheiro,
	}							//mas que passou pela checagem, e eh setado em true mesmo que tenha contribuido com 0
	
	/**
	 * Acessa diretamente o objeto Despesa associado
	 * ao projeto.
	 * 
	 * @return 		uma Despesa
	 */
	public Despesa getDespesas() {
		return this.despesas;
	}
	
	/**
	 * Varre a lista de participacoes atreladas ao projeto
	 * buscando o coordenador responsavel por ele
	 * (retorna apenas o nome dele, a fim de impressao em arquivo somete).
	 * 
	 * @return 		nome do coordenador
	 */
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

	/**
	 * Verifica se o projeto ja terminou ate o dado momento
	 * classificando ele entre em andamento ou ja finalizado.
	 * 
	 * @return						string contendo o estado do projeto
	 * @throws ValidacaoException 	necessaria caso a criacao do tipo Date gere algum erro
	 */
	public String getSituacaoProjeto() throws ValidacaoException {
		String dataAgora = LocalDateTime.now().toString();
		String dataFormatada = 	dataAgora.charAt(8) + dataAgora.charAt(9) + "/"
							  + dataAgora.charAt(5) + dataAgora.charAt(6) + "/"
							  + dataAgora.charAt(0) + dataAgora.charAt(1) + dataAgora.charAt(2) + dataAgora.charAt(3);
		if(this.dataTermino.compareTo(new Date(dataFormatada)) > 0) {
			return "em andamento";
		}
		return "finalizado";
	}

	/**
	 * Acessa a quantidade de participacoes associadas
	 * com este projeto.
	 * 
	 * @return 	numero de associacoes a este projeto
	 */
	public int getNumParticipantes() {
		return this.participacoes.size();
	}
	
	/**
	 * Acessa a quantidade de graduandos associados a este projetos
	 * seja ele graduandos ou pos-graduando
	 * 
	 * @return 		numero de alunos associados a este projeto
	 */
	public int getNumGraduandos() {
		int numGraduandos = 0;
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoGraduando || p instanceof ParticipacaoPosGraduando) {
				numGraduandos++;
			}
		}
		return numGraduandos;
	}
	
	/**
	 * Acessa quantidade total de graduandos, pos-graduandos
	 * e profissionais presentes neste projeto.
	 * 
	 * @return		numero representando o total de graduandos, pos-graduandos e profissionais
	 */
	public int getNumParitipacaoGradPosProfi() {
		int numParticipacao = 0;
		for(Participacao p:this.participacoes) {
			if(!(p instanceof ParticipacaoProfessor)) {
				numParticipacao++;
			}
		}
		return numParticipacao;
	}
	
	/**
	 * Acessa quantidade de graduandos neste projeto
	 * (sem pos-graduandos).
	 * 
	 * @return		quantidade total de graduandos neste projeto
	 */
	public int getNumParticipacaoGraduandos() {
		int numGraduandos = 0;
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoGraduando) {
				numGraduandos++;
			}
		}
		return numGraduandos;
	}
	
	/**
	 * Acessa quantidade de pos-graduandos neste projeto.
	 * 
	 * @return 		quantidade total de pos-graduandos
	 */
	public int getNumParticipacaoPosGraduandos() {
		int numPosGraduandos = 0;
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoPosGraduando) {
				numPosGraduandos++;
			}
		}
		return numPosGraduandos;
	}
	
	/**
	 * Acessa a quantidade de profissionais neste projeto.
	 * 
	 * @return		quantidade total de profissionais
	 */
	public int getNumParticipacaoProfissionais() {
		int numProfissionais = 0;
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoProfissional) {
				numProfissionais++;
			}
		}
		return numProfissionais;
	}

	/**
	 * Altera a condicao de checado do projeto(quanto ao estado de contribuicao)
	 * para true, ou contribuido.
	 * 
	 */
	public void setTrueUASC() {
		this.checkingUASC = true;
	}
	
	/**
	 * Altera a condicao de checado do projeto(quanto ao estado de contribuicao)
	 * para false, ou nao contribuido.
	 * 
	 */
	public void setFalseUASC() {
		this.checkingUASC = false;
	}
	
	/**
	 * Faz a alteracao do nome.
	 * 
	 * @param nome 					novo nome para o projeto
	 * @throws ValidacaoException	em caso de nome ser invalido
	 */
	public void setNome(String nome) throws ValidacaoException {
		validaNomeProjeto(nome);
		this.nome = nome;
	}
	
	/**
	 * Faz alteracao do objetivo do projeto.
	 * 
	 * @param objetivo				novo objetivo
	 * @throws ValidacaoException	em caso de objetivo ser invalido
	 */
	public void setObjetivo(String objetivo) throws ValidacaoException {
		validaObjetivoUpdate(objetivo);
		this.objetivo = objetivo;
	}
	
	/**
	 * Faz a alteracao da data de inicio do projeto.
	 * 
	 * @param data					nova data
	 * @throws ValidacaoException	em caso de data ser invalida
	 */
	public void setDataInicio(String data) throws ValidacaoException {
		try {
			this.dataInicio = new Date(data);
		}
		catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na atualizacao de projeto: Formato de data invalido");
		}
		
	}

	/**
	 * Faz a alteracao da duracao do projeto,
	 * gera uma alteracao tambem na data de termino.
	 * 
	 * @param duracao				nova duracao
	 * @throws ValidacaoException	em caso de duracao ser invalida
	 */
	public void setDuracao(int duracao) throws ValidacaoException {
		validaInt(duracao);
		this.duracao = duracao;  //visto que a duracao foi mudada, a data de termino tambem muda
		this.dataTermino = dataInicio.geraDataTermino(duracao);
	}
	
	/**
	 * Remove uma participacao especifica do projeto.
	 * 
	 * @param cpf	cpf da pessoa a ser removida
	 */
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
	
	/**
	 * Get do conjunto de participacoes do projeto.
	 * 
	 * @return		um arrayList contendo as participacoes associadas a esse projeto
	 */
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
	
	/**
	 * Metodo certificador de se o projeto ja possui
	 * um professor.
	 * 
	 * @return		boolean com a confirmacao, true para ja possui professor, false para negativo
	 */
	public boolean hasProfessor() {
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoProfessor) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo certificador de se o projeto ja possui
	 * um graduando.
	 * 
	 * @return		true para ja possuir, false para ainda nao possui
	 */
	public boolean hasGraduando() {
		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoGraduando) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo certificador de se o projeto ja possui
	 * um coordenador.
	 * 
	 * @return		true para ja possuir, false para ainda nao possui
	 */
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
	
	/**
	 * Abstracao da logica de calculaColaboracao,
	 * na qual as subclasses de Projeto a implementam.
	 * 
	 * @return		double, com o valor da colaboracao para a uasc
	 */
	public abstract double calculaColaboracao();

	/**
	 * Abstracao da logica de atualizaDespesas,
	 * na qual as subclasses de Projeto a implementam.
	 * 
	 * @param montanteBolsas		valor referente aos custos com bolsa
	 * @param montanteCusteio		valor referente aos custos de custeio do projeto
	 * @param montanteCapital		valor referente aos custos de capital
	 * @throws ValidacaoException	em caso de alguma operacao invalida
	 */
	public abstract void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital) 
			throws ValidacaoException;

	/**
	 * Sobrescrita do metodo hashCode da classe Object.
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Sobrescrita do metodo equals da classe Object.
	 * 
	 */
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

	/**
	 * Metodo verificador de se o projeto ja possui
	 * a participacao com a dada pessoa.
	 * 
	 * @param cpfPessoa		cpf da pessoa
	 * @return				true para ja possui ela, false para negativo
	 */
	public boolean hasParticipacao(String cpfPessoa) {
		for(Participacao p:participacoes) {
			if(p.getPessoa().getCpf().equals(cpfPessoa)) {
				return true;
			}
		}
		return false;
	}
}