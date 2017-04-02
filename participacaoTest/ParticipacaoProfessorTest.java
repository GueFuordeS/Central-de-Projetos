package participacaoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import participacao.ParticipacaoProfessor;
import pessoa.*;
import projeto.*;

/**
 * Testes de unidade da classe ParticipacaoProfessor.
 * 
 * @author Gabriel Fernandes
 */
public class ParticipacaoProfessorTest {
	PessoaController pessoaCtrl;
	ProjetoController projetoCtrl;
	ParticipacaoProfessor partProf;

	@Before
	public void setUp() throws ValidacaoException, NaoEncontradaException {
		pessoaCtrl = new PessoaController();
		projetoCtrl = new ProjetoController();
		
		pessoaCtrl.cadastraPessoa("112.342.456-12", "Natasha", "naty@gmail.com");	
		projetoCtrl.adicionaMonitoria("Monitoria de lp2", "lp2", 5, "tirar as duvidas da galera", "2016.2", 
				"10/08/2016", 6);
		
		partProf = new ParticipacaoProfessor(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), true, "10/08/2016", 
				6, 10, 8);
	}
	
	@Test
	public void construtorTest() { //construtor test pois mostra q a construcao do objeto no setUp() funcionou
		assertEquals("Monitoria de lp2", partProf.getProjeto().getNome());
		assertEquals("Natasha", partProf.getPessoa().getNome());
		assertEquals(8,partProf.getQtdeHorasDedicadas());
		assertEquals(true, partProf.isCoordenador());
	}
	
	@Test
	public void construtorWithFailTest() throws NaoEncontradaException {
		try {
			new ParticipacaoProfessor(null, projetoCtrl.recuperaProjeto("Monitoria de lp2"), true,
					"10/08/2016", 6, 10, 8);
			fail();
		} catch (ValidacaoException e) {
			assertEquals("Pessoa nao pode ser nula", e.getMessage());
		}
		try {
			new ParticipacaoProfessor(pessoaCtrl.recuperaPessoa("112.342.456-12"), null, true, 
					"10/08/2016", 6, 10, 8);
			fail();
		} catch (ValidacaoException e) {
			assertEquals("Projeto nao pode ser nulo", e.getMessage());
		}
		try {
			new ParticipacaoProfessor(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), true, 
					"10/08/2016", 0, 10, 8);
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Duracao invalida", e.getMessage());
		}
		try {
			new ParticipacaoProfessor(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), true, 
					"10/08/2016", 6, -5, 8);
		} catch (ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Quantidade de horas invalida",e.getMessage());
		}
	}
	
	@Test
	public void hashEqualsTest() throws ValidacaoException, NaoEncontradaException {
		ParticipacaoProfessor partProf2 = new ParticipacaoProfessor(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), true,
				"10/08/2016", 
				6, 10, 8);
		assertTrue(partProf.equals(partProf2));

		ParticipacaoProfessor partGrad3 = new ParticipacaoProfessor(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), false, //note que o ser coordenador difere os objetos
				"10/08/2016", 
				6, 10, 8);
		assertFalse(partGrad3.equals(partProf)); //unica diferenca sendo o true/false para ser coordenador
	}
}