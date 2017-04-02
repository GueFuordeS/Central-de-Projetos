package participacaoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import participacao.ParticipacaoGraduando;
import pessoa.*;
import projeto.*;

/**
 * Testes de unidade da classe ParticipacaoGraduando.
 * 
 * @author Gabriel Fernandes
 */
public class ParticipacaoGraduandoTest {
	PessoaController pessoaCtrl;
	ProjetoController projetoCtrl;
	ParticipacaoGraduando partGrad;

	@Before
	public void setUp() throws ValidacaoException, NaoEncontradaException {
		pessoaCtrl = new PessoaController();
		projetoCtrl = new ProjetoController();
		
		pessoaCtrl.cadastraPessoa("112.342.456-12", "Natasha", "naty@gmail.com");	
		projetoCtrl.adicionaMonitoria("Monitoria de lp2", "lp2", 5, "tirar as duvidas da galera", "2016.2", 
				"10/08/2016", 6);
		
		partGrad = new ParticipacaoGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2")
				, "10/08/2016", 
				6, 10, 8);
	}
	
	@Test
	public void construtorTest() { //construtor test pois mostra q a construcao do objeto no setUp() funcionou
		assertEquals("Monitoria de lp2", partGrad.getProjeto().getNome());
		assertEquals("Natasha", partGrad.getPessoa().getNome());
		assertEquals(8,partGrad.getQtdeHorasDedicadas());
	}
	
	@Test
	public void construtorWithFailTest() throws NaoEncontradaException {
		try {
			new ParticipacaoGraduando(null, projetoCtrl.recuperaProjeto("Monitoria de lp2"), 
					"10/08/2016", 6, 10, 8);
			fail();
		} catch (ValidacaoException e) {
			assertEquals("Pessoa nao pode ser nula", e.getMessage());
		}
		try {
			new ParticipacaoGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), null, 
					"10/08/2016", 6, 10, 8);
			fail();
		} catch (ValidacaoException e) {
			assertEquals("Projeto nao pode ser nulo", e.getMessage());
		}
		try {
			new ParticipacaoGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), 
					"10/08/2016", 0, 10, 8);
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Duracao invalida", e.getMessage());
		}
		try {
			new ParticipacaoGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), 
					"10/08/2016", 6, -5, 8);
		} catch (ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Quantidade de horas invalida",e.getMessage());
		}
	}
	
	@Test
	public void hashEqualsTest() throws ValidacaoException, NaoEncontradaException {
		ParticipacaoGraduando partGrad2 = new ParticipacaoGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2")
				, "10/08/2016", 
				6, 10, 8);
		assertTrue(partGrad.equals(partGrad2));
		
		pessoaCtrl.cadastraPessoa("429.502.123-02", "Serena", "serena@yahoo.com");
		ParticipacaoGraduando partGrad3 = new ParticipacaoGraduando(pessoaCtrl.recuperaPessoa("429.502.123-02"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2")
				, "10/08/2016", 
				6, 10, 8);
		assertFalse(partGrad3.equals(partGrad));
	}
}