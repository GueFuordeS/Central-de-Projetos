package participacaoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.*;
import participacao.*;
import pessoa.PessoaController;
import projeto.ProjetoController;

public class ParticipacaoControllerTest {
	PessoaController pesController;
	ProjetoController projController;
	ParticipacaoController partController;
	
	@Before
	public void setUp() throws ValidacaoException, NaoEncontradaException {
		pesController = new PessoaController();
		projController = new ProjetoController();
		
		partController = new ParticipacaoController(pesController, projController);
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
		projController.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);
		int codigoProjeto = projController.getCodigoProjeto("Monitoria de lp2");
		partController.associaProfissional("810.455.237-02", codigoProjeto, "desenvolvedor", 25, 8);
		
		assertEquals("desenvolvedor", ((ParticipacaoProfissional)partController.recuperaParticipacao
				("810.455.237-02", "Monitoria de lp2")).getCargo());
		assertEquals("Monitoria de lp2", pesController.getInfoPessoa("810.455.237-02", "Participacoes"));
		assertEquals("Jeonghwa", projController.getInfoProjeto(codigoProjeto, "participacoes"));
		
		assertTrue(partController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(pesController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(projController.hasParticipacao("810.455.237-02","Monitoria de lp2"));
	}

	@Test
	public void associaPosGraduandoTest() throws NaoEncontradaException, ValidacaoException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		projController.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);
		int codigoProjeto = projController.getCodigoProjeto("Monitoria de lp2");
		partController.associaPosGraduando("810.455.237-02", codigoProjeto, "Doutorado", 25, 8);
		
		assertEquals("Doutorado",((ParticipacaoPosGraduando)partController.recuperaParticipacao
				("810.455.237-02", "Monitoria de lp2")).getTitulacao());
		assertEquals("Monitoria de lp2", pesController.getInfoPessoa("810.455.237-02", "Participacoes"));
		assertEquals("Jeonghwa", projController.getInfoProjeto(codigoProjeto, "participacoes"));
		
		assertTrue(partController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(pesController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(projController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
	}
	
	@Test
	public void associaProfessorWithFailTest() throws ValidacaoException, NaoEncontradaException {
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		projController.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);
		int codigoProjeto = projController.getCodigoProjeto("Monitoria de lp2");
		partController.associaProfessor("810.455.237-02", codigoProjeto, false, 0, 8);
		
		
	}
}