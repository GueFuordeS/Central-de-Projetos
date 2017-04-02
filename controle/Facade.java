package controle;

import java.io.IOException;
import easyaccept.EasyAccept;
import excecoes.*;
import participacao.ParticipacaoController;
import pessoa.PessoaController;
import projeto.*;
import uasc.UASC;
import static myUtils.EntradaSaidaDados.*;

/**
 * Uso do padrao de projeto "facade" para convergir todo o sistema para esta classe,
 * a fim de ela ser uma porta de entrada para o controle do sistema como um todo
 * (interface com o usuario).
 * 
 * @author Gabriel Fernandes
 */
public class Facade {
	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private ParticipacaoController participacaoController;
	private UASC uasc;

	/**
	 * Inicializa Facade, assim como
	 * faz a inicializacao de:
	 * um controle de Pessoas 		(@see PessoaController)
	 * um controle de Projetos 		(@see ProjetoController)
	 * um controle de Participacao	(@see ParticipacaoController)
	 * uma uasc						(@see UASC)
	 * 
	 * @throws ValidacaoException	em caso de falha na inicializacao dos controllers
	 */
	public Facade() throws ValidacaoException  {
		pessoaController = new PessoaController();
		projetoController = new ProjetoController();
		participacaoController = new ParticipacaoController(pessoaController, projetoController);
		uasc = new UASC(pessoaController, projetoController, participacaoController);
	}
	
	/**
	 * Responsavel por reinicializar o
	 * sistema de um ponto salvo,
	 * fazendo assim um sistema que persiste dados.
	 * 
	 * @throws ClassNotFoundException	em caso de falha ao ler classe em arquivo
	 * @throws IOException				em caso de erro na conexao com arquivo de persistencia de dados
	 */
	public void iniciaSistema() throws ClassNotFoundException, IOException {	
		try {
			this.uasc = (UASC)leObjeto("arquivos_sistema/cpc_ufcg.dat");
			this.pessoaController = uasc.getPessoaController();
			this.projetoController = uasc.getProjetoController();
			this.participacaoController = uasc.getParticipacaoController();
		}
		catch(NullPointerException e) {
			//deixa o sistema comecar do zero, pois nao ha nenhum ponto no qual o programa ja possa partir
		}
	}
	
// 	######### CONTROLE DE PESSOAS #########
	
	/**
	 * Realiza o cadastro de uma nova Pessoa
	 * na Central de Projetos.
	 * 
	 * @param cpf					cpf da nova Pessoa
	 * @param nome					nome da nova Pessoa
	 * @param email					email da nova Pessoa
	 * @return						cpf do cadastrado
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException {
		return pessoaController.cadastraPessoa(cpf, nome, email);
	}
	
	/**
	 * Edita informacao de uma Pessoa
	 * cadastrada na Central de Projetos.
	 * 
	 * @param cpf						cpf da Pessoa em que se deseja alterar informacao
	 * @param opcao						informacao na qual se deseja alterar(e.g. "email")
	 * @param nova						novo valor para a informacao a se alterar
	 * @throws ValidacaoException		em caso de dados invalidos
	 * @throws NaoEncontradaException	em caso de Pessoa nao estiver previamente cadastrada no controle de Pessoas
	 */
	public void editaPessoa(String cpf, String opcao, String nova) throws ValidacaoException, NaoEncontradaException {
		pessoaController.editaPessoa(cpf, opcao, nova);
	}
	
	/**
	 * Realiza a remocao de uma Pessoa especifica
	 * da Central de Projetos.
	 * 
	 * @param cpf						cpf da Pessoa a qual se deseja remover
	 * @throws ValidacaoException		em caso de dados invalidos
	 * @throws NaoEncontradaException	em caso de Pessoa nao cadastrada
	 */
	public void removePessoa(String cpf) throws ValidacaoException, NaoEncontradaException {
		pessoaController.removePessoa(cpf);
	}
	
	/**
	 * Acessa informacao sobre uma Pessoa que
	 * esteja cadastrada na Central de Projetos.
	 * 
	 * @param cpf						cpf da Pessoa a qual se deseja buscar a informacao
	 * @param atributo					informacao desejada
	 * @return							String contendo a informacao
	 * @throws NaoEncontradaException	em caso de Pessoa nao cadastrada
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public String getInfoPessoa(String cpf, String atributo) throws NaoEncontradaException, ValidacaoException {
		return pessoaController.getInfoPessoa(cpf, atributo);
	}
	
	/**
	 * Realiza o calculo de pontos de uma
	 * Pessoa baseada nos projetos em que participa.
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @return							pontuacao total da Pessoa
	 * @throws NaoEncontradaException	em caso de Pessoa nao cadastrada
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public double calculaPontuacaoPorParticipacao(String cpfPessoa) throws NaoEncontradaException, ValidacaoException {
		return pessoaController.calculaPontuacaoPorParticipacao(cpfPessoa);
	}
	
	/**
	 * Realiza calculo de valor em que Pessoa
	 * recebe de bolsas, por meio de suas participacoes
	 * em projetos, levando em consideracao seu valor por hora
	 * e tempo dedicado ao Projeto.
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @return							valor total de Bolsas recebidos por Pessoa
	 * @throws NaoEncontradaException	em caso de Pessoa nao cadastrada
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public double getValorBolsa(String cpfPessoa) throws NaoEncontradaException, ValidacaoException {
		return pessoaController.getValorBolsa(cpfPessoa);
	}

// 	######### CONTROLE DE PROJETOS #########
	
	/**
	 * Realiza o cadastro de um Projeto do
	 * tipo Monitoria na Central de Projetos.
	 * 
	 * @param nome					nome do Projeto
	 * @param disciplina			nome da disciplina associada a Monitoria
	 * @param rendimento			rendimento esperado deste Projeto
	 * @param objetivo				objetivo deste Projeto
	 * @param periodo				periodo letivo em que ocorrera esta Monitoria
	 * @param dataInicio			data do inico deste Projeto
	 * @param duracao				duracao prevista para este Projeto
	 * @return						codigo unico gerado para este Projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo,
			 String periodo, String dataInicio, int duracao) throws ValidacaoException {
		
		return projetoController.adicionaMonitoria(nome, disciplina, rendimento, objetivo,
				 periodo, dataInicio, duracao);
	}
	
	/**
	 * Realiza o cadastro de um Projeto do
	 * tipo PET na Central de Projetos.
	 * 
	 * @param nome					nome do Projeto
	 * @param objetivo				objetivo deste Projeto
	 * @param impacto				Impacto planejado para este Projeto ter na comunidade
	 * @param rendimento			endimento esperado deste Projeto
	 * @param prodTecnica			quantidade de producoes tecnicas planejadas para este Projeto
	 * @param prodAcademica			quantidade de producoes academicas planejadas para este Projeto
	 * @param patentes				quantidade de patentes planejadas para este Projeto
	 * @param dataInicio			data do inico deste Projeto
	 * @param duracao				duracao prevista para este Projeto
	 * @return						codigo unico gerado para este Projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, 
			int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) 
					throws ValidacaoException {
		
		return projetoController.adicionaPET(nome, objetivo, impacto, rendimento, 
				 prodTecnica, prodAcademica, patentes, dataInicio, duracao);
	}
	
	/**
	 * Realiza o cadastro de um Projeto do
	 * tipo Extensao na Central de Projetos.
	 * 
	 * @param nome					nome do Projeto
	 * @param objetivo				objetivo deste Projeto
	 * @param impacto				Impacto planejado para este Projeto ter na comunidade
	 * @param dataInicio			data do inico deste Projeto
	 * @param duracao				duracao prevista para este Projeto
	 * @return						codigo unico gerado para este Projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) 
			throws ValidacaoException {
		
		return projetoController.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}
	
	/**
	 * Realiza o cadastro de um Projeto do
	 * tipo PED na Central de Projetos.
	 * 
	 * @param nome					nome do Projeto
	 * @param categoria				categoria a qual este PED vai pertencer(PIBIC, PIBITI, PIVIC ou COOP)	
	 * @param prodTecnica			quantidade de producoes tecnicas planejadas para este Projeto
	 * @param prodAcademica			quantidade de producoes academicas planejadas para este Projeto
	 * @param patentes				quantidade de patentes planejadas para este Projeto
	 * @param objetivo				objetivo deste Projeto
	 * @param dataInicio			data do inico deste Projeto
	 * @param duracao				duracao prevista para este Projeto
	 * @return						codigo unico gerado para este Projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, 
			String objetivo, String dataInicio, int duracao) throws ValidacaoException {
		
		return projetoController.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, 
				objetivo, dataInicio, duracao);
	}
	
	/**
	 * Acessa uma informacao desejada
	 * sobre um Projeto especifico. 
	 * 
	 * @param codigo					identificador unico de cada Projeto
	 * @param atributo					informacao a qual se deseja obter do Projeto(e.g. atributo "nome")
	 * @return							String contendo a informacao
	 * @throws NaoEncontradaException	em caso de Projeto nao encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public String getInfoProjeto(int codigo, String atributo) throws NaoEncontradaException, ValidacaoException {
		return projetoController.getInfoProjeto(codigo, atributo);
	}
	
	/**
	 * Acessa o identificador unico de um Projeto
	 * a partir do seu nome.
	 * 
	 * @param nome						nome do Projeto
	 * @return							codigo do Projeto
	 * @throws NaoEncontradaException	em caso de Projeto nao cadastrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public int getCodigoProjeto(String nome) throws NaoEncontradaException, ValidacaoException {
		return projetoController.getCodigoProjeto(nome);
	}
	
	/**
	 * Realiza a remocao de um Projeto especifico
	 * da Central de Projetos.
	 * 
	 * @param codigo					identificador unico do Projeto
	 * @throws NaoEncontradaException	em caso de Projeto nao cadastrado
	 */
	public void removeProjeto(int codigo) throws NaoEncontradaException {
		projetoController.removeProjeto(codigo);
	}
	
	/**
	 * Altera informacao especifica referente
	 * a um Projeto.
	 * 
	 * @param codigo					identificador unico do Projeto
	 * @param atributo					atributo em que se deseja alterar
	 * @param valor						valor a substituir atributo
	 * @throws NaoEncontradaException	em caso de projeto nao encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void editaProjeto(int codigo, String atributo, String valor) throws NaoEncontradaException, 
	ValidacaoException {
		projetoController.editaProjeto(codigo, atributo, valor);
	}

	/**
	 * Realiza a atualizacao de despesas planejadas
	 * para um Projeto.
	 * 
	 * @param codigoProjeto				codigo do Projeto a ser atualizado
	 * @param montanteBolsas			valor das despesas com bolsas
	 * @param montanteCusteio			valor das despesas com custeio
	 * @param montanteCapital			valor das despesas com capital
	 * @throws NaoEncontradaException	em caso de Projeto nao cadastrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void atualizaDespesasProjeto(String codigoProjeto, double montanteBolsas, double montanteCusteio, double montanteCapital) 
			throws NaoEncontradaException, ValidacaoException {
		if(codigoProjeto == null || codigoProjeto.trim().isEmpty()) throw new ValidacaoException("Erro na "
				+ "atualizacao de projeto: codigo nulo ou vazio");
		projetoController.atualizaDespesasProjeto(Integer.parseInt(codigoProjeto), montanteBolsas, montanteCusteio, montanteCapital);
	}

	/**
	 * Realiza o calculo do valor a ser colaborado
	 * de um Projeto em especifico para com a uasc.
	 * 
	 * @param codigoProjeto				codigo do Projeto a ser calculado
	 * @return							valor total a ser contribuido por este Projeto
	 * @throws NaoEncontradaException	em caso de Projeto nao cadastrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public double calculaColaboracaoUASC(String codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		if(codigoProjeto == null || codigoProjeto.trim().isEmpty()) throw new ValidacaoException("Erro na "
				+ "consulta de projeto: codigo nulo ou vazio");
		return projetoController.calculaColaboracaoUASC(Integer.parseInt(codigoProjeto));
	}

// 	######### CONTROLE DE PARTICIPACOES #########
	
	/**
	 * Realiza associacao de uma Pessoa
	 * como Professor a um Projeto.
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @param codigoProjeto				identificador unico do Projeto
	 * @param coordenador				se associacao de Professor a Projeto eh como coordenador
	 * @param valorHora					valor por hora contribuida com Projeto
	 * @param qntHoras					quantidade de horas semanais contribuidas com Projeto
	 * @throws NaoEncontradaException	em caso de Pessoa ou Projeto nao estarem cadastrados
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void associaProfessor(String cpfPessoa, int codigoProjeto, boolean coordenador, 
			double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaProfessor(cpfPessoa, codigoProjeto, coordenador, valorHora, qntHoras);
	}
	
	/**
	 * Realiza associacao de uma Pessoa
	 * como Graduando a um Projeto.
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @param codigoProjeto				identificador unico do Projeto
	 * @param valorHora					valor por hora contribuida com Projeto
	 * @param qntHoras					quantidade de horas semanais contribuidas com Projeto
	 * @throws NaoEncontradaException	em caso de Pessoa ou Projeto nao estarem cadastrados
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void associaGraduando(String cpfPessoa, int codigoProjeto, double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaGraduando(cpfPessoa, codigoProjeto, valorHora, qntHoras);
	}
	
	/**
	 * Realiza associacao de uma Pessoa
	 * como Profissional a um Projeto.
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @param codigoProjeto				identificador unico do Projeto
	 * @param cargo						cargo em que Profissional desempenhara
	 * @param valorHora					valor por hora contribuida com Projeto
	 * @param qntHoras					quantidade de horas semanais contribuidas com Projeto
	 * @throws NaoEncontradaException	em caso de Pessoa ou Projeto nao estarem cadastrados
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void associaProfissional(String cpfPessoa, int codigoProjeto,String cargo, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaProfissional(cpfPessoa, codigoProjeto, cargo, valorHora, qntHoras);
	}
	
	/**
	 * Realiza associacao de uma Pessoa
	 * como Pos-Graduando a um Projeto.
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @param codigoProjeto				identificador unico do Projeto
	 * @param titulacao					titulacao em que Pos-Graduando possui
	 * @param valorHora					valor por hora contribuida com Projeto
	 * @param qntHoras					quantidade de horas semanais contribuidas com Projeto
	 * @throws NaoEncontradaException	em caso de Pessoa ou Projeto nao estarem cadastrados
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void associaPosGraduando(String cpfPessoa, int codigoProjeto, String titulacao, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaPosGraduando(cpfPessoa, codigoProjeto, titulacao, valorHora, qntHoras);
	}

	/**
	 * Remove uma associacao de Pessoa a Projeto
	 * do controle de Participacao, alem de remover esse registo
	 * de Participacao da Pessoa e do Projeto
	 * (Ja que alem do controle de Participacao, ambas os outros
	 * envolvidos tambem mantem registro dessa Participacao).
	 * 
	 * @param cpfPessoa					cpf da Pessoa
	 * @param codigoProjeto				codigo do Projeto
	 * @throws NaoEncontradaException	em caso de Participacao, Pessoa ou Projeto nao encontrado
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void removeParticipacao(String cpfPessoa, int codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		participacaoController.removeParticipacao(cpfPessoa, codigoProjeto);
	}
	
// 	######### UASC #########
	
	/**
	 * Atualiza gasto que a uasc teve,
	 * reduzindo assim a receita que ela possui no momento.
	 * 
	 * @param valor						valor gasto
	 * @throws ValidacaoException		em caso de valor negativo ou uasc nao possuir recursos suficientes
	 */
	public void diminuiReceita(double valor) throws ValidacaoException {
		uasc.diminuiReceita(valor);
	}
	
	/**
	 * Calcula toda a contribuicao ate o dado momento
	 * feita pelos Projetos a uasc.
	 * 
	 * @return		valor total contribuido
	 */
	public double calculaColaboracaoTotalUASC() {
		return uasc.calculaColaboracaoTotalUASC();
	}

	/**
	 * Calcula o valor em que a uasc tem
	 * disponivel para usar.
	 * 
	 * @return		valor disponivel
	 */
	public double calculaTotalEmCaixaUASC() {
		return uasc.getReceita();
	}
	
	/**
	 * Realiza a criacao de um relatorio com
	 * informacoes gerais sobre todos os projetos
	 * cadastrados na Central de Projetos.
	 * Arquivo gerado para este relatorio se encontra em:
	 * arquivos_sistema.relatorios.cad_projetos.txt
	 * 
	 * @throws ESException				em caso de problemas com conexao a arquivo
	 * @throws ValidacaoException		em caso de problemas ao reunir informacoes dos projetos
	 */
	public void exportaDadosProjetos() throws ESException, ValidacaoException {
		uasc.exportaDadosProjetos();
	}
	
	/**
	 * Realiza a criacao de um relatorio com
	 * informacoes gerais sobre todos as contribuicoes
	 * realizados dos projetos cadastrados na Central de Projetos
	 * a uasc.
	 * Arquivo gerado para este relatorio se encontra em:
	 * arquivos_sistema.relatorios.cad_colaboracoes.txt
	 * 
	 * @throws ESException				em caso de problemas com conexao a arquivo
	 */
	public void exportaDadosColaboracoes() throws ESException {
		uasc.exportaDadosColaboracoes();
	}
	
	/**
	 * Realiza a persistencia de dados da
	 * Central de Projetos.
	 * Uso de classe de utilidade para auxiliar
	 * a escrita no arquivo.
	 * @see myUtils.EntradaSaidaDados
	 * 
	 * @throws ValidacaoException		em caso de problemas ao reunir informacoes dos projetos		
	 * @throws IOException				em caso de problemas com conexao a arquivo
	 */
	public void fechaSistema() throws ValidacaoException, IOException  {
		escreveObjeto("arquivos_sistema/cpc_ufcg.dat", uasc);
	}

	/**
	 * Metodo main para rodar os testes
	 * dos scripts utilizando o easy accept
	 * como testes de aceitacao.
	 * 
	 * @param args		argumentos que o sistema pode passar
	 */
	public static void main(String[] args) {
	    args = new String[] {"controle.Facade", 
	    		"acceptance_test/us1_test.txt", 
	    		"acceptance_test/us1_test_exception.txt",
	    		"acceptance_test/us2_test.txt",
	    		"acceptance_test/us2_test_exception.txt", 
	    		"acceptance_test/us3_test.txt",
	    		"acceptance_test/us3_test_exception.txt",
	    		"acceptance_test/us4_test.txt",
	    		"acceptance_test/us5_test.txt",
	    		"acceptance_test/us6_test.txt",
	    		"acceptance_test/us6_test_exception.txt"};
	    EasyAccept.main(args);
	}
}