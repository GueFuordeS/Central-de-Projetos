package projeto;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import myUtils.GeradorCodigo;
import participacao.*;

/**
 * Classe responsavel por fazer o controle de projetos em nosssa
 * unidade academica.
 * 
 * @author Gabriel Fernandes
 */
public class ProjetoController implements Serializable {
	private static final long serialVersionUID = 1L;
	private GeradorCodigo geradorCodigo;
	private List<Projeto> projetos;
	
	/**
	 * Quando instanciada, inicializa o array que guarda os projetos
	 * e a classe de utilidade GeradorCodigo, que eh responsavel por gerir os codigos
	 * unicos para nossos projetos.
	 */
	public ProjetoController() {
		this.geradorCodigo = new GeradorCodigo();
		this.projetos = new ArrayList<>();
	}
	
	/**
	 * Para exportar a mesma instancia usada na ProjetoController,
	 * caso aja necessidade de gerar a partir dos codigos ja gerados aqui.
	 * 
	 * @return 		instancia de GeradorCodigo
	 */
	public GeradorCodigo getCodigoGerador() {
		return geradorCodigo;
	}

	/**
	 * Realiza a criacao de um projeto do tipo
	 * Monitoria.
	 * 
	 * @param nome					nome da monitoria
	 * @param disciplina			disciplina associada
	 * @param rendimento			rendimento esperado
	 * @param objetivo				objetivo para com esta monitoria
	 * @param periodo				periodo da monitoria
	 * @param dataInicio			data de inicio da monitoria
	 * @param duracao				duracao desta monitoria
	 * @return						codigo gerado unicamente para esta monitoria
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo,
			 String periodo, String dataInicio, int duracao) throws ValidacaoException {
		int codigo = this.geradorCodigo.geraCodigo();
		
		Monitoria mon = new Monitoria(codigo, nome, disciplina, rendimento, objetivo, 
				periodo, dataInicio, duracao);
		this.hasProjetoToAdd(nome);
		projetos.add(mon);

		return codigo;
	}
	
	/**
	 * Realiza a criacao de um projeto do tipo
	 * PET.
	 * 
	 * @param nome 					nome do pet
	 * @param objetivo				objetivo para este pet
	 * @param impacto				impacto desejado
	 * @param rendimento			rendimento esperado
	 * @param prodTecnica			producoes tecnicas previstas
	 * @param prodAcademica			producoes academicas previstas
	 * @param patentes				patentes previstas
	 * @param dataInicio			data de inicio das operacoes deste pet
	 * @param duracao				duracao prevista
	 * @return						codigo unico gerado para este projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, 
			int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) throws ValidacaoException {
		int codigo = this.geradorCodigo.geraCodigo();

		PET pet = new PET(codigo, nome, objetivo, impacto, rendimento, 
				prodTecnica, prodAcademica, patentes, dataInicio, duracao);
		this.hasProjetoToAdd(nome);
		projetos.add(pet);

		return codigo;
	}
	
	/**
	 * Realiza a criacao de um projeto do tipo
	 * Extensao.
	 * 
	 * @param nome					nome do projeto de extensao
	 * @param objetivo				objetivo para esta extensao
	 * @param impacto				impacto esperado
	 * @param dataInicio			data de inicio desta extensao
	 * @param duracao				duracao prevista
	 * @return						codigo unico gerado para esta extensao
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) 
			throws ValidacaoException {
		int codigo = geradorCodigo.geraCodigo();

		Extensao ext = new Extensao(codigo, nome, objetivo, impacto, dataInicio, duracao);
		this.hasProjetoToAdd(nome);
		projetos.add(ext);
			
		return codigo;
	}
	
	/**
	 * Realiza a criacao de um projeto do tipo
	 * PED.
	 * 
	 * @param nome					nome desta pesquisa e desenvolvimento
	 * @param categoria				categoria a que pertence(entre as 4 possiveis para ped)
	 * @param prodTecnica			producoes tecnicas previstas
	 * @param prodAcademica			producoes academicas previstas
	 * @param patentes				patentes previstas
	 * @param objetivo				objetivo deste ped
	 * @param dataInicio			data do inicio deste ped
	 * @param duracao				duracao prevista
	 * @return						codigo unico para este novo projeto criado
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, 
			String objetivo, String dataInicio, int duracao) throws ValidacaoException {
		int codigo = geradorCodigo.geraCodigo();
		
		PED ped = new PED(codigo, nome, categoria, prodTecnica, prodAcademica, patentes, 
				objetivo, dataInicio, duracao);
		this.hasProjetoToAdd(nome);
		projetos.add(ped);
		
		return codigo;
	}
	
	/**
	 * Metodo verificador se projeto ja esta existe
	 * gerando erro se ja existir.
	 * 
	 * @param nome					nome do projeto a verificar
	 * @throws ValidacaoException	em caso de projeto ja existir
	 */
	private void hasProjetoToAdd(String nome) throws ValidacaoException {
		for(Projeto p:projetos) {
			if(p.getNome().equals(nome)) {
				throw new ValidacaoException("Projeto ja existe");
			}
		}
	}
	
	/**
	 * Metodo verificador se nome de projeto ja esta em uso
	 * (ja que nome eh um atributo unico para projetos).
	 * 
	 * @param nome					nome a verificar
	 * @throws ValidacaoException	em caso de nome ja esta em uso
	 */
	public boolean hasProjeto(String nome) throws ValidacaoException {
		for(Projeto p:projetos) {
			if(p.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo verificador se projeto ja existe
	 * utilizando-se do codigo.
	 * 
	 * @param nome					nome a verificar
	 * @throws ValidacaoException	em caso de nome ja esta em uso
	 */
	public boolean hasProjeto(int codigo) throws ValidacaoException {
		for(Projeto p:projetos) {
			if(p.getCodigo() == codigo) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remove projeto dentre os registros.
	 * 
	 * @param codigo					codigo do projeto a ser removido
	 * @throws NaoEncontradaException	em caso de projeto nao existir previamente no controle
	 */
	public void removeProjeto(int codigo) throws NaoEncontradaException {
		Projeto p = this.recuperaProjeto(codigo);
		projetos.remove(p);
	}

	/**
	 * Busca informacao especifica sobre um projeto e retorna
	 * esta.
	 * 
	 * @param codigo					codigo referente ao projeto
	 * @param atributo					atributo referente a informacao a qual se quer acessar
	 * @return							string contendo o valor do atributo desejado
	 * @throws NaoEncontradaException	em caso de projeto nao encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public String getInfoProjeto(int codigo, String atributo) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigo);
		
		if(atributo.toLowerCase().equals("nome")) {
			return p.getNome();
		}
		else if(atributo.toLowerCase().equals("objetivo")) {
			return p.getObjetivo();
		}
		else if(atributo.toLowerCase().equals("data de inicio")) {
			return p.getDataInicio();
		}
		else if(atributo.toLowerCase().equals("duracao")) {
			return Integer.toString(p.getDuracao());
		}
		else if(atributo.toLowerCase().equals("impacto")) {
			return this.getImpacto(p);
		}
		else if(atributo.toLowerCase().equals("disciplina")) {
			return this.getDisciplina(p);
		} 
		else if(atributo.toLowerCase().equals("periodo")) {
			return this.getPeriodo(p);
		}
		else if(atributo.toLowerCase().equals("rendimento")) {
			return this.getRendimento(p);
		}
		else if(atributo.toLowerCase().equals("categoria")) {
			return this.getCategoria(p);
		}
		else if(atributo.toLowerCase().equals("producao academica")) {
			return this.getProdAcademica(p);
		}
		else if(atributo.toLowerCase().equals("producao tecnica")) {
			return this.getProdTecnica(p);
		}
		else if(atributo.toLowerCase().equals("patentes")) {
			return this.getPatentes(p);
		}
		else if(atributo.toLowerCase().equals("participacoes")){
			ArrayList<Participacao> participacoes = p.getParticipacoes();
			
			String participacoesRetorno = "";
			
			for (int i = 0; i < participacoes.size(); i++) {
				if(i<participacoes.size()-1)
					participacoesRetorno += participacoes.get(i).getPessoa().getNome() + ", ";
				else { 
					participacoesRetorno += participacoes.get(i).getPessoa().getNome();
				}
			}
			return participacoesRetorno;
		}
		throw new ValidacaoException("Erro na consulta de projeto: Atributo nulo ou invalido");
	}
	
	/**
	 * Acessa o valor de produtividade desejado caso projeto em questao
	 * possua produtividade.
	 * 
	 * @param p						projeto em questao
	 * @return						valor da produtividade
	 * @throws ValidacaoException	em caso de erro na consulta de produtividade de projeto
	 */
	private String getPatentes(Projeto p) throws ValidacaoException {
		if(p instanceof PED) {
			PED ext = (PED) p;
			return Integer.toString(ext.getValor(Produtividade.PATENTES));
		}
		if(p instanceof PET) {
			PET ext = (PET) p;
			return Integer.toString(ext.getValor(Produtividade.PATENTES));
		}
		if(p instanceof Monitoria) {
			throw new ValidacaoException("Erro na consulta de projeto: Monitoria nao possui Patentes");
		}
		if(p instanceof Extensao) {
			throw new ValidacaoException("Erro na consulta de projeto: Extensao nao possui Patentes");
		}
		return null;
	}

	/**
	 * Acessa o valor de produtividade desejado caso projeto em questao
	 * possua produtividade.
	 * 
	 * @param p						projeto em questao
	 * @return						valor da produtividade
	 * @throws ValidacaoException	em caso de erro na consulta de produtividade de projeto
	 */
	private String getProdTecnica(Projeto p) throws ValidacaoException {
		if(p instanceof PED) {
			PED ext = (PED) p;
			return Integer.toString(ext.getValor(Produtividade.PRODTECNICA));
		}
		if(p instanceof PET) {
			PET ext = (PET) p;
			return Integer.toString(ext.getValor(Produtividade.PRODTECNICA));
		}
		if(p instanceof Monitoria) {
			throw new ValidacaoException("Erro na consulta de projeto: Monitoria nao possui Producao tecnica");
		}
		if(p instanceof Extensao) {
			throw new ValidacaoException("Erro na consulta de projeto: Extensao nao possui Producao tecnica");
		}
		return null;
	}

	/**
	 * Acessa o valor de produtividade desejado caso projeto em questao
	 * possua produtividade.
	 * 
	 * @param p						projeto em questao
	 * @return						valor da produtividade
	 * @throws ValidacaoException	em caso de erro na consulta de produtividade de projeto
	 */
	private String getProdAcademica(Projeto p) throws ValidacaoException {
		if(p instanceof PED) {
			PED ext = (PED) p;
			return Integer.toString(ext.getValor(Produtividade.PRODACADEMICA));
		}
		if(p instanceof PET) {
			PET ext = (PET) p;
			return Integer.toString(ext.getValor(Produtividade.PRODACADEMICA));
		}
		if(p instanceof Monitoria) {
			throw new ValidacaoException("Erro na consulta de projeto: Monitoria nao possui Producao academica");
		}
		if(p instanceof Extensao) {
			throw new ValidacaoException("Erro na consulta de projeto: Extensao nao possui Producao academica");
		}
		return null;
	}

	/**
	 * Acessa a categoria a qual o PED
	 * esta associado.
	 * 
	 * @param p		projeto em questao
	 * @return		string contendo a categoria associada
	 */
	private String getCategoria(Projeto p) {
		if(p instanceof PED) {
			PED ext = (PED) p;
			return ext.getCategoria();
		}
		return null;
	}

	/**
	 * Acessa rendimento de projeto, caso este
	 * possua esse atributo.
	 * 
	 * @param p		projeto em questao
	 * @return		string contendo o rendimento(embora um int, uso de string para padronizacao na saida destes acessadores)
	 */
	private String getRendimento(Projeto p) {
		if(p instanceof Rendimento) {
			return Integer.toString(((Rendimento) p).getRendimento());
		}
		return null;
	}

	/**
	 * Acessa periodo na qual Monitoria
	 * esta associada.
	 * 
	 * @param p		Monitoria em questao(espera-se que neste ponto seja uma Monitoria)
	 * @return		toString() do periodo
	 */
	private String getPeriodo(Projeto p) {
		if(p instanceof Monitoria) {
			Monitoria ext = (Monitoria) p;
			return ext.getPeriodo();
		}
		return null;
	}

	/**
	 * Acessa disciplina na qual Monitoria
	 * esta associada.
	 * 
	 * @param p		Monitoria em questao
	 * @return		nome da disciplina
	 */
	private String getDisciplina(Projeto p) {
		if(p instanceof Monitoria) {
			Monitoria ext = (Monitoria) p;
			return ext.getDisciplina();
		}
		return null;
	}

	/**
	 * Acessa impacto que projeto em questao esta
	 * planejado ter.
	 * 
	 * @param p		projeto em questao
	 * @return		valor transformado para string do impacto
	 */
	private String getImpacto(Projeto p) {
		if(p instanceof Impacto) {
			return Integer.toString(((Impacto) p).getImpacto());
		}
		return null;
	}
	
	/**
	 * Projetos cadastrados ate o
	 * dado momento.
	 * 
	 * @return		valor de projetos cadastrados
	 */
	public int getTotalProjetosCadastrados() {
		return projetos.size();
	}

	/**
	 * Busca projeto em especifico e retorna ele.
	 * Utiliza-se codigo do projeto como fator busca.
	 * 
	 * @param codigo					codigo do projeto em especifico
	 * @return							projeto desejado
	 * @throws NaoEncontradaException	em caso de projeto nao ser encontrado
	 */
	public Projeto recuperaProjeto(int codigo) throws NaoEncontradaException {
		for(Projeto p:this.projetos) {
			if(p.getCodigo() == codigo) {
				return p;
			}
		}
		throw new NaoEncontradaException("Erro na consulta de projeto: Projeto nao encontrado");
	}

	/**
	 * Busca projeto em especifico e retorna ele.
	 * Utiliza-se nome do projeto como fator busca.
	 * 
	 * @param nome						nome do projeto em especifico
	 * @return							projeto desejado
	 * @throws NaoEncontradaException	em caso de projeto nao ser encontrado
	 */
	public Projeto recuperaProjeto(String nome) throws NaoEncontradaException, ValidacaoException {
		for(Projeto p:this.projetos) {
			if(p.getNome().equals(nome)) {
				return p;
			}
		}
		throw new NaoEncontradaException("Erro na consulta de projeto: Projeto nao encontrado");
	}
	
	/**
	 * Realiza alteracoes em um projeto especifico com base nos
	 * parametros passados.
	 * 
	 * @param codigo					codigo do projeto em questao
	 * @param atributo					atributo desejado para alteracao
	 * @param valor						novo valor para este campo
	 * @throws NaoEncontradaException	em caso de projeto nao encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void editaProjeto(int codigo, String atributo, String valor) 
			throws NaoEncontradaException, ValidacaoException {
		
		Projeto p = recuperaProjeto(codigo);
		
		if(atributo.toLowerCase().equals("nome")) {
			if(!this.hasProjeto(valor)) p.setNome(valor);
		}
		else if(atributo.toLowerCase().equals("objetivo")) {
			p.setObjetivo(valor);
		}
		else if(atributo.toLowerCase().equals("data de inicio")) {
			p.setDataInicio(valor);
		}
		else if(atributo.toLowerCase().equals("duracao")) {
			p.setDuracao(Integer.parseInt(valor));
		}
		else if(atributo.toLowerCase().equals("impacto")) {
			this.setImpacto(p, valor);
		}
		else if(atributo.toLowerCase().equals("disciplina")) {
			this.setDisciplina(p, valor);
		}
		else if(atributo.toLowerCase().equals("periodo")) {
			this.setPeriodo(p, valor);
		}
		else if(atributo.toLowerCase().equals("rendimento")) {
			this.setRendimento(p, valor);
		}
		else if(atributo.toLowerCase().equals("categoria")) {
			this.setCategoria(p, valor);
		}
		else if(atributo.toLowerCase().equals("producao academica")) {
			this.setProdAcademica(p, valor);
		}
		else if(atributo.toLowerCase().equals("producao tecnica")) {
			this.setProdTecnica(p, valor);
		}
		else if(atributo.toLowerCase().equals("patentes")) {
			this.setPatentes(p, valor);
		}
	}
	
	/**
	 * Atribui novo valor a produtividade desejada
	 * em projeto especifico.
	 * 
	 * @param p							projeto em questao
	 * @param valor						novo valor para produtividade
	 * @throws NumberFormatException	em caso de valor nao ser um int valido
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	private void setPatentes(Projeto p, String valor) throws NumberFormatException, ValidacaoException {
		if(p instanceof PED) {
			((PED) p).setProdutividade(Produtividade.PATENTES, valor);
		}
		if(p instanceof PET) {
			((PET) p).setProdutividade(Produtividade.PATENTES, valor);
		}
		
	}

	/**
	 * Atribui novo valor a produtividade desejada
	 * em projeto especifico.
	 * 
	 * @param p							projeto em questao
	 * @param valor						novo valor para produtividade
	 * @throws NumberFormatException	em caso de valor nao ser um int valido
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	private void setProdTecnica(Projeto p, String valor) throws NumberFormatException, ValidacaoException {
		if(p instanceof PED) {
			((PED) p).setProdutividade(Produtividade.PRODTECNICA, valor);
		}
		if(p instanceof PET) {
			((PET) p).setProdutividade(Produtividade.PRODTECNICA, valor);
		}
	}

	/**
	 * Atribui novo valor a produtividade desejada
	 * em projeto especifico.
	 * 
	 * @param p							projeto em questao
	 * @param valor						novo valor para produtividade
	 * @throws NumberFormatException	em caso de valor nao ser um int valido
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	private void setProdAcademica(Projeto p, String valor) throws NumberFormatException, ValidacaoException {
		if(p instanceof PED) {
			((PED) p).setProdutividade(Produtividade.PRODACADEMICA, valor);
		}
		if(p instanceof PET) {
			((PET) p).setProdutividade(Produtividade.PRODACADEMICA, valor);
		}
	}

	/**
	 * Altera categoria de PED.
	 * 
	 * @param p						PED em questao
	 * @param valor					nova categoria
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	private void setCategoria(Projeto p, String valor) throws ValidacaoException {
		if(p instanceof PED) {
			((PED) p).setCategoria(valor);
		}
	}

	/**
	 * Altera rendimento se este projeto pertencer a
	 * interface das classes que possuem o atributo rendimento.
	 * 
	 * @param p							projeto em questao
	 * @param valor						novo valor para rendimento
	 * @throws NumberFormatException	em caso de valor nao ser um inteiro valido
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	private void setRendimento(Projeto p, String valor) throws NumberFormatException, ValidacaoException {
		if(p instanceof Rendimento) {
			((Rendimento) p).setRendimento(Integer.parseInt(valor));
		}
	}

	/**
	 * Realiza alteracao no periodo de monitoria.
	 * 
	 * @param p						Monitoria em questao
	 * @param valor					novo periodo, formato de periodo valido
	 * @throws ValidacaoException	em caso de dados invalidos(como o formato de periodo ser invalido)
	 */
	private void setPeriodo(Projeto p, String valor) throws ValidacaoException {
		if(p instanceof Monitoria) {
			((Monitoria) p).setPeriodo(valor);
		}
	}

	/**
	 * Realiza alteracao no disciplina de monitoria.
	 * 
	 * @param p						Monitoria em questao
	 * @param valor					nova disciplina
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	private void setDisciplina(Projeto p, String valor) throws ValidacaoException {
		if(p instanceof Monitoria) {
			((Monitoria) p).setDisciplina(valor);
		}
	}

	/**
	 * Realiza alteracao no impacto social do projeto.
	 * Utiliza interface Impacto para eleger os projetos possiveis
	 * dessa mudanca.
	 * 
	 * @param p							projeto em questao
	 * @param valor						novo impacto social
	 * @throws NumberFormatException	em caso de inteiro invalido passado como valor
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	private void setImpacto(Projeto p, String valor) throws NumberFormatException, ValidacaoException {
		if(p instanceof Impacto) {
			((Impacto) p).setImpacto(Integer.parseInt(valor));
		}
	}

	/**
	 * A partir de nome de projeto, retorna codigo associado
	 * a aquele projeto.
	 * 
	 * @param nome						nome de projeto
	 * @return							codigo de projeto
	 * @throws NaoEncontradaException	em caso de projeto nao ser encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public int getCodigoProjeto(String nome) throws NaoEncontradaException, ValidacaoException {
		try {
			Projeto p = this.recuperaProjeto(nome);
			return p.getCodigo();
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na obtencao de codigo de projeto: Projeto nao encontrado");
		}
	}

	/**
	 * Metodo delegador, passa para projeto funcao de adicionar
	 * e guardar participacoes de pessoas nele. 
	 * 
	 * @param codigoProjeto				codigo do projeto em questao
	 * @param participacao				participacao, associacao entre pessoa e projeto
	 * @throws NaoEncontradaException	em caso de projeto nao ser encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void addParticipacao(int codigoProjeto, Participacao participacao) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigoProjeto);
		p.addicionaParticipacao(participacao);
	}

	/**
	 * Certifica de se uma dada pessoa e um projeto possuem
	 * associacao.
	 * 
	 * @param cpfPessoa		cpf da pessoa
	 * @param nomeProjeto	nome do projeto
	 * @return				true para se associacao existir, false caso nao exista
	 */
	public boolean hasParticipacao(String cpfPessoa, String nomeProjeto) {
		for(Projeto p:projetos) {
			if(p.getNome().equals(nomeProjeto)) {
				return p.hasParticipacao(cpfPessoa);
			}
		}
		return false;
	}
	
	/**
	 * Verifica se um projeto em especifico
	 * ja possui professor associado a ele.
	 * 
	 * @param codigo					codigo do projeto
	 * @return							true para ja possuir, false para nao possuir
	 * @throws NaoEncontradaException	em caso de projeto nao existir
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public boolean hasProfessor(int codigo) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigo);
		return p.hasProfessor();
	}

	/**
	 * Verifica se um projeto em especifico
	 * ja possui graduando associado a ele.
	 * 
	 * @param codigo					codigo do projeto
	 * @return							true para ja possuir, false para nao possuir
	 * @throws NaoEncontradaException	em caso de projeto nao existir
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public boolean hasGraduando(int codigo) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigo);
		return p.hasGraduando();
	}
	
	/**
	 * Verifica se um projeto em especifico
	 * ja possui coordenador associado a ele.
	 * 
	 * @param codigo					codigo do projeto
	 * @return							true para ja possuir, false para nao possuir
	 * @throws NaoEncontradaException	em caso de projeto nao existir
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public boolean hasCoordenador(int codigo) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigo);
		return p.hasCoordenador();
	}
	
	/**
	 * Varifica se PED em questao possui limitacoes
	 * especifica.
	 * (limitacoes no sentido de que pivic so pode possuir um graduando e um professor por exemplo).
	 * 
	 * @param codigo					codigo do projeto
	 * @return							true para possuir limitacoes, false caso nao possuir
	 * @throws NaoEncontradaException	em caso de projeto nao ser encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public boolean hasPEDLimitacao(int codigo) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigo);
		if(p instanceof PED) {
			PED ped = (PED) p;
			return ped.hasPEDLimitacao();
		}
		return false;
	}

	/**
	 * Remove uma participacao de um projeto
	 * a partir de codigo do projeto, e
	 * cpf da pessoa envolvida na participacao.
	 * 
	 * @param codigo					codigo do projeto
	 * @param cpf						cpf da pessoa
	 * @throws NaoEncontradaException	em caso de projeto nao ser encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void removeParticipacao(int codigo, String cpf) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigo);
		p.removeParticipacao(cpf);
	}
	
	/**
	 * Realiza a atualizacao das despesas associadas
	 * a cada projeto. Delega a cada projeto esta tarefa.
	 * 
	 * @param codigo					codigo do projeto
	 * @param montanteBolsas			despesas com bolsas
	 * @param montanteCusteio			despesas com custeio
	 * @param montanteCapital			despesas com capital
	 * @throws NaoEncontradaException	em caso de projeto nao ser encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void atualizaDespesasProjeto(int codigo, double montanteBolsas, double montanteCusteio, double montanteCapital) 
			throws NaoEncontradaException, ValidacaoException {
		this.recuperaProjeto(codigo).atualizaDespesas(montanteBolsas, montanteCusteio, montanteCapital);
	}
	
	/**
	 * Delega calculo das colaboracoes para com a uasc.
	 * 
	 * @param codigoProjeto				codigo do projeto
	 * @return							valor da colaboracao
	 * @throws NaoEncontradaException	em caso de projeto nao ser encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public double calculaColaboracaoUASC(int codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		
		return this.recuperaProjeto(codigoProjeto).calculaColaboracao();
	}
	/**
	 * Calcula colaboracoes de novos projetos,
	 * ja que os que ja previamente doaram mudam de estado
	 * quanto ao atributo checkingUASC(responsavel por determinar se um projeto ja colaborou ou nao).
	 * 
	 * @return			valor das novas colaboracoes
	 */
	public double calculaColaboracoesUASC() {
		double total = 0;
		for(Projeto p:this.projetos) {
			if(!p.getCheckingUASC()) {
				total += p.calculaColaboracao();
				p.setTrueUASC();
			}
		} 
		return total;
	}

	/**
	 * Calcula o total ja colaborado para com a UASC.
	 * (nao leva em consideracao o estado colaborado, da flag checkingUASC),
	 * ja que a ideia aqui eh saber quanto ja foi colaborado ate o dado momento.
	 * 
	 * @return			valor total de colaboracoes
	 */
	public double calculaColaboracaoTotalUASC() {
		double total = 0;
		for(Projeto p:this.projetos) {
				total += p.calculaColaboracao();

		} 
		return total;
	}

	/**
	 * Soma a partir de projetos em estado "finalizado" quantos projetos
	 * ja foram concluidos.
	 * 
	 * @return						somatorio de projetos ja concluidos
	 * @throws ValidacaoException	em caso de mecanismo interno que gera a data e verifica os ja terminados ter algum erro
	 */
	public int getTotalProjetosConcluidos() throws ValidacaoException {
		int total = 0;
		for(Projeto p:this.projetos) {
			if(p.getSituacaoProjeto().equals("finalizado")) {
				total++;
			}
		}
		return total;
	}
	
	/**
	 * Retorna o total de graduandos, pos-graduandos e profissionais participantes
	 * de um dado projeto.
	 * 
	 * @return				soma de graduandos pos-graduandos e profissionais participantes de projetos na unidade
	 */
	public int getNumParitipacaoGradPosProfi() {
		int total = 0;
		
		for(Projeto p:this.projetos) {
			total += p.getNumParitipacaoGradPosProfi();
		}
		return total;
	}

	/**
	 * Quantidade total de graduandos participantes de
	 * projetos na unidade.
	 * 
	 * @return				quantidade total
	 */
	public int getNumParticipacaoGraduandos() {
		int total = 0;
		for(Projeto p:projetos) {
			total += p.getNumParticipacaoGraduandos();
		}
		return total;
	}

	/**
	 * Quantidade total de pos-graduandos participantes de
	 * projetos na unidade.
	 * 
	 * @return				quantidade total
	 */
	public int getNumParticipacaoPosGraduandos() {
		int total = 0;
		for(Projeto p:projetos) {
			total += p.getNumParticipacaoPosGraduandos();
		}
		return total;
	}
	
	/**
	 * Quantidade total de profissionais participantes de
	 * projetos na unidade.
	 * 
	 * @return				quantidade total
	 */
	public int getNumParticipacaoProfissionais() {
		int total = 0;
		for(Projeto p:projetos) {
			total += p.getNumParticipacaoProfissionais();
		}
		return total;
	}
	
	/**
	 * Realiza calculo de porcentagem de participacao de graduandos
	 * em projetos em relacao a quantidade total de graduandos, pos-graduandos e profissionais
	 * participantes de projetos.
	 * 
	 * @return				porcentagem de participacao
	 */
	public String getPorcentagemParticipacaoGraduacao() {
		double total = 0;
		DecimalFormat df = new DecimalFormat("#.0");
		
		if(this.getNumParitipacaoGradPosProfi() != 0) {
			total += (this.getNumParticipacaoGraduandos()/(this.getNumParitipacaoGradPosProfi()*1.0))*100;
		}
		return df.format(total);
	}
	
	/**
	 * Realiza calculo de porcentagem de participacao de pos-graduandos
	 * em projetos em relacao a quantidade total de graduandos, pos-graduandos e profissionais
	 * participantes de projetos.
	 * 
	 * @return				porcentagem de participacao
	 */
	public String getPorcentagemParticipacaoPosGraduacao() {
		double total = 0;
		DecimalFormat df = new DecimalFormat("#.0");
		
		if(this.getNumParitipacaoGradPosProfi() != 0) {
			total += (this.getNumParticipacaoPosGraduandos()/(this.getNumParitipacaoGradPosProfi()*1.0))*100;
		}

		return df.format(total);
	}
	
	/**
	 * Realiza calculo de porcentagem de participacao de profissionais
	 * em projetos em relacao a quantidade total de graduandos, pos-graduandos e profissionais
	 * participantes de projetos.
	 * 
	 * @return				porcentagem de participacao
	 */
	public String getPorcentagemParticipacaoProfissional() {
		double total = 0;
		DecimalFormat df = new DecimalFormat("#.0");

		if(this.getNumParitipacaoGradPosProfi() != 0) {
			total += (this.getNumParticipacaoProfissionais()/(this.getNumParitipacaoGradPosProfi()*1.0))*100;
		}

		return df.format(total);
	}
	
	/**
	 * Levanta dados sobre todos os projetos
	 * cadastrados na unidade, cria entao uma string que eh o retorno deste metodo,
	 * usado para fins de exportacao de dados para arquivos.
	 * 
	 * @return						dados gerais sobre os projetos
	 * @throws ValidacaoException	em caso de algum erro na verificacao da situacao do projeto(por causa da data)
	 */
	public String listaProjetos() throws ValidacaoException {
		final String FIM_DE_LINHA = System.lineSeparator();
		String dados = "";
		int contador = 0;
		
		for(Projeto p:this.projetos) {
			contador++;
			dados += "==> Projeto " + contador + ":" + FIM_DE_LINHA
			      + "Nome: " + p.getNome() + FIM_DE_LINHA
			      + "Data de inicio: " + p.getDataInicioAMD() + FIM_DE_LINHA
			      + "Coordenador: " + p.getCoordenadorNome() + FIM_DE_LINHA
				  + "Situacao: " + p.getSituacaoProjeto() + FIM_DE_LINHA
				  + FIM_DE_LINHA;
		}
		return dados;
	}

	/**
	 * Levanta dados sobre todas as colaboracoes feitas a partir
	 * dos projetos a uasc.
	 * Usado para fins de exportacao de informacoes para arquivos externos.
	 * 
	 * @return		dados gerais sobre cada colaboracao
	 */
	public String listaColaboracoes() {
		final String FIM_DE_LINHA = System.lineSeparator();
		String dados = "";
		
		for(Projeto p:this.projetos) {
			dados += "==> Nome: " + p.getNome() + " Data de inicio: " + p.getDataInicioAMD() 
					 + " Valor colaborado: " + p.calculaColaboracao() + FIM_DE_LINHA;
		}
		return dados;
	}
}