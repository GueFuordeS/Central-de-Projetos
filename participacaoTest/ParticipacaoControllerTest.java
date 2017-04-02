package participacaoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.*;
import participacao.*;
import pessoa.PessoaController;
import projeto.ProjetoController;
import uasc.UASC;

/**
 * Testes de unidade da classe ParticipacaoController.
 * 
 * @author Gabriel Fernandes
 */
public class ParticipacaoControllerTest {
	PessoaController pesController;
	ProjetoController projController;
	ParticipacaoController partController;
	UASC uasc;
	
	@Before
	public void setUp() throws ValidacaoException, NaoEncontradaException {
		pesController = new PessoaController();
		projController = new ProjetoController();
		
		partController = new ParticipacaoController(pesController, projController);
		
		uasc = new UASC(pesController, projController, partController);
	}

	@Test
	public void construtorTest() {
		try {
			new ParticipacaoController(pesController, projController);
		}
		catch (ValidacaoException e) {
			fail();
		}
	}
	
	@Test
	public void construtorWithFailTest() {
		try {
			new ParticipacaoController(null, projController);
			fail();
		} catch (ValidacaoException e) {
			assertEquals("PessoaController nulo", e.getMessage());
		}
		
		try {
			new ParticipacaoController(pesController, null);
			fail();
		} catch (ValidacaoException e) {
			assertEquals("ProjetoController nulo", e.getMessage());
		}
	}
	
	@Test
	public void associaProfessorTest() throws ValidacaoException, NaoEncontradaException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		projController.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);
		int codigoProjeto = projController.getCodigoProjeto("Monitoria de lp2");
		partController.associaProfessor("810.455.237-02", codigoProjeto, false, 0, 8);
		
		assertEquals("Jeonghwa",partController.recuperaParticipacao("810.455.237-02", "Monitoria de lp2").getPessoa().getNome());
		assertEquals("Monitoria de lp2", pesController.getInfoPessoa("810.455.237-02", "Participacoes"));
		assertEquals("Jeonghwa", projController.getInfoProjeto(codigoProjeto, "participacoes"));
		
		assertTrue(partController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(pesController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(projController.hasParticipacao("810.455.237-02","Monitoria de lp2"));
	}

	@Test
	public void associaGraduandoTest() throws NaoEncontradaException, ValidacaoException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		projController.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);
		int codigoProjeto = projController.getCodigoProjeto("Monitoria de lp2");
		partController.associaGraduando("810.455.237-02", codigoProjeto, 25, 8);
		
		assertEquals("Jeonghwa",partController.recuperaParticipacao("810.455.237-02", "Monitoria de lp2").getPessoa().getNome());
		assertEquals("Monitoria de lp2", pesController.getInfoPessoa("810.455.237-02", "Participacoes"));
		assertEquals("Jeonghwa", projController.getInfoProjeto(codigoProjeto, "participacoes"));
		
		assertTrue(partController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(pesController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(projController.hasParticipacao("810.455.237-02","Monitoria de lp2"));
	}
	
	@Test
	public void associaProfissionalTest() throws NaoEncontradaException, ValidacaoException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		projController.adicionaExtensao("Projeto olimpico","Ganhar medalhas de ouro",6,"01/01/2017",6);
		int codigoProjeto = projController.getCodigoProjeto("Projeto olimpico");
		partController.associaProfissional("810.455.237-02", codigoProjeto, "desenvolvedor", 25, 8);
		
		assertEquals("desenvolvedor", ((ParticipacaoProfissional)partController.recuperaParticipacao
				("810.455.237-02", "Projeto olimpico")).getCargo());
		assertEquals("Projeto olimpico", pesController.getInfoPessoa("810.455.237-02", "Participacoes"));
		assertEquals("Jeonghwa", projController.getInfoProjeto(codigoProjeto, "participacoes"));
		
		assertTrue(partController.hasParticipacao("810.455.237-02", "Projeto olimpico"));
		assertTrue(pesController.hasParticipacao("810.455.237-02", "Projeto olimpico"));
		assertTrue(projController.hasParticipacao("810.455.237-02","Projeto olimpico"));
	}

	@Test
	public void associaPosGraduandoTest() throws NaoEncontradaException, ValidacaoException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		projController.adicionaExtensao("Projeto olimpico","Ganhar medalhas de ouro",6,"01/01/2017",6);
		int codigoProjeto = projController.getCodigoProjeto("Projeto olimpico");
		partController.associaPosGraduando("810.455.237-02", codigoProjeto, "Doutorado", 25, 8);
		
		assertEquals("Doutorado",((ParticipacaoPosGraduando)partController.recuperaParticipacao
				("810.455.237-02", "Projeto olimpico")).getTitulacao());
		assertEquals("Projeto olimpico", pesController.getInfoPessoa("810.455.237-02", "Participacoes"));
		assertEquals("Jeonghwa", projController.getInfoProjeto(codigoProjeto, "participacoes"));
		
		assertTrue(partController.hasParticipacao("810.455.237-02", "Projeto olimpico"));
		assertTrue(pesController.hasParticipacao("810.455.237-02", "Projeto olimpico"));
		assertTrue(projController.hasParticipacao("810.455.237-02", "Projeto olimpico"));
	}
	
	@Test
	public void associaProfessorWithFailTest() throws ValidacaoException, NaoEncontradaException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		pesController.cadastraPessoa("411.425.581-23", "Hyelin", "hyelin@hanmail.net");
		
		projController.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);
		int codigoProjeto = projController.getCodigoProjeto("Monitoria de lp2");
		
		partController.associaProfessor("810.455.237-02", codigoProjeto, false, 0, 8);
		try {
			partController.associaProfessor("810.455.237-02", codigoProjeto, false, 0, 16);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: "
					+ "Monitoria nao pode ter mais de um professor", e.getMessage());
		}
		
		partController.associaGraduando("411.425.581-23", codigoProjeto, 10, 8);
		try {
			partController.associaGraduando("411.425.581-23", codigoProjeto, 15, 16);
			fail();
		} //repare q mesmo mudando o valor da hora nao deixa de ser a mesma pessoa tentando o mesmo projeto q ja faz parte
		catch(ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: "
					+ "Aluno ja esta cadastrado nesse projeto", e.getMessage());
		}
	}
	
	@Test
	public void associaGraduandoWithFailTest() throws ValidacaoException, NaoEncontradaException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		pesController.cadastraPessoa("411.425.581-23", "Hyelin", "hyelin@hanmail.net");
		
		projController.adicionaPED("APLICACAO DA MINERACAO DE DADOS EM REPOSITORIOS DINAMICOS", "PIBITI", 
				2, 4, 1,"Desenvolvimento tecnologico e inovacao", "01/01/2017", 24);
		int codigoProjeto = projController.getCodigoProjeto("APLICACAO DA MINERACAO DE DADOS EM REPOSITORIOS DINAMICOS");
		
		partController.associaGraduando("810.455.237-02", codigoProjeto, 25, 8);
		try { //note que ao tentar adcionar outro graduando a um P&D(PIBITI) deve gerar excecao
			partController.associaGraduando("411.425.581-23", codigoProjeto, 20, 16);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: "
					+ "Projetos P&D nao podem ter mais de um graduando", e.getMessage());
		}
	}
	
	@Test
	public void associaPosGraduandoWithFailTest() throws ValidacaoException, NaoEncontradaException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		pesController.cadastraPessoa("411.425.581-23", "Hyelin", "hyelin@hanmail.net");
		
		projController.adicionaPED("APLICACAO DA MINERACAO DE DADOS EM REPOSITORIOS DINAMICOS", "COOP", 
				2, 4, 1,"Desenvolvimento tecnologico e inovacao", "01/01/2017", 24);
		int codigoProjeto = projController.getCodigoProjeto("APLICACAO DA MINERACAO DE DADOS EM REPOSITORIOS DINAMICOS");
		
		partController.associaPosGraduando("810.455.237-02", codigoProjeto,"Doutorado", 25, 8);
		partController.associaPosGraduando("411.425.581-23", codigoProjeto, "Mestrado", 25, 8);
		try { //note que ao tentar adcionar outro graduando a um P&D(PIBITI) deve gerar excecao
			partController.associaPosGraduando("411.425.581-23", codigoProjeto, "Mestrado", 20, 16);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: "
					+ "Aluno ja esta cadastrado nesse projeto", e.getMessage());
		}
	}
	
	@Test
	public void associaProfissionalWithFailTest() throws ValidacaoException, NaoEncontradaException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		pesController.cadastraPessoa("411.425.581-23", "Hyelin", "hyelin@hanmail.net");
		
		projController.adicionaPED("APLICACAO DA MINERACAO DE DADOS EM REPOSITORIOS DINAMICOS", "COOP", 
				2, 4, 1,"Desenvolvimento tecnologico e inovacao", "01/01/2017", 24);
		int codigoProjeto = projController.getCodigoProjeto("APLICACAO DA MINERACAO DE DADOS EM REPOSITORIOS DINAMICOS");
		
		partController.associaProfissional("810.455.237-02", codigoProjeto,"desenvolvedor", 25, 8);
		partController.associaProfissional("411.425.581-23", codigoProjeto, "gerente", 25, 8);
		try { //note que ao tentar a mesma pessoa no mesmo projeto obtemos falha
			partController.associaProfissional("411.425.581-23", codigoProjeto, "gerente", 20, 16);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: "
					+ "Aluno ja esta cadastrado nesse projeto", e.getMessage());
		}
	}
	
	@Test
	public void removeParticipacaoTest() throws ValidacaoException, NaoEncontradaException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		
		projController.adicionaMonitoria("Monitoria de lp2", "lp2", 100, "auxiliar","2016.2", "01/01/2017", 12);
		int codigoProjeto = projController.getCodigoProjeto("Monitoria de lp2");
		
		partController.associaGraduando("810.455.237-02", codigoProjeto, 25, 8);
		
		assertTrue(partController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(pesController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(projController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));

		partController.removeParticipacao("810.455.237-02", codigoProjeto);
		
		assertFalse(partController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertFalse(pesController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertFalse(projController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
	}
	
	@Test
	public void removeParticipacaoWithFailTest() throws ValidacaoException, NaoEncontradaException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		
		projController.adicionaMonitoria("Monitoria de lp2", "lp2", 100, "auxiliar","2016.2", "01/01/2017", 12);
		int codigoProjeto = projController.getCodigoProjeto("Monitoria de lp2");
		
		partController.associaGraduando("810.455.237-02", codigoProjeto, 25, 8);
		
		partController.removeParticipacao("810.455.237-02", codigoProjeto);
		
		try {
			partController.removeParticipacao("810.455.237-02", codigoProjeto);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na remocao de participacao: Pessoa nao possui participacao no projeto indicado", 
					e.getMessage());
		}
		
		try {
			partController.removeParticipacao("411.425.581-23", codigoProjeto);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na remocao de participacao: Pessoa nao encontrada", e.getMessage());
		}
		
		try {
			partController.removeParticipacao("810.455.237-02", Integer.MAX_VALUE);
			fail(); //ate que levaria um tempo ne para o codigo chegar na casa dos 2,147,483,647
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na remocao de participacao: Projeto nao encontrado", e.getMessage());
		}
	}
}