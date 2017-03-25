package participacaoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import participacao.ParticipacaoController;
import pessoa.PessoaController;
import projeto.ProjetoController;

public class ParticipacaoControllerTest {
	ParticipacaoController partController;
	PessoaController pesController;
	ProjetoController projController;
	
	@Before
	public void setUp() throws ValidacaoException, NaoEncontradaException {
		pesController = new PessoaController();
		pesController.cadastraPessoa("810.455.237-02", "Jeonghwa", "jeonghwa@hanmail.net");
		
		projController = new ProjetoController();
		projController.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);
		int codigoProjeto = projController.getCodigoProjeto("Monitoria de lp2");
		
		partController = new ParticipacaoController(pesController, projController);
		partController.associaGraduando("810.455.237-02", codigoProjeto, 25, 8);
	}
	
	@Test
	public void construtorTest() throws NaoEncontradaException, ValidacaoException {
		assertTrue(partController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(pesController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(projController.hasParticipacao("810.455.237-02","Monitoria de lp2"));
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
	public void associaProfessorTest() {
		//partController.associaProfessor(cpfPessoa, codigoProjeto, coordenador, valorHora, qntHoras);
	}
	
	@Test
	public void Test() throws NaoEncontradaException, ValidacaoException {
		assertTrue(partController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(pesController.hasParticipacao("810.455.237-02", "Monitoria de lp2"));
		assertTrue(projController.hasParticipacao("810.455.237-02","Monitoria de lp2"));
	}
	
	@Test
	public void associaGraduandoTest() {
		//partController.associaGraduando(cpfPessoa, codigoProjeto, valorHora, qntHoras);
	}
}