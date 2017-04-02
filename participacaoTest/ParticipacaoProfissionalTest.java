package participacaoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import participacao.ParticipacaoProfissional;
import pessoa.*;
import projeto.*;

/**
 * Testes de unidade da classe ParticipacaoProfissional.
 * 
 * @author Gabriel Fernandes
 */
public class ParticipacaoProfissionalTest {
	PessoaController pessoaCtrl;
	ProjetoController projetoCtrl;
	ParticipacaoProfissional partProf;

	@Before
	public void setUp() throws ValidacaoException, NaoEncontradaException {
		pessoaCtrl = new PessoaController();
		projetoCtrl = new ProjetoController();
		
		pessoaCtrl.cadastraPessoa("112.342.456-12", "Natasha", "naty@gmail.com");	
		projetoCtrl.adicionaMonitoria("Monitoria de lp2", "lp2", 5, "tirar as duvidas da galera", "2016.2", 
				"10/08/2016", 6);
		
		partProf = new ParticipacaoProfissional(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), "desenvolvedor", "10/08/2016", 
				6, 10, 8);
	}

	@Test
	public void construtorTest() { //construtor test pois mostra q a construcao do objeto no setUp() funcionou
		assertEquals("Monitoria de lp2", partProf.getProjeto().getNome());
		assertEquals("Natasha", partProf.getPessoa().getNome());
		assertEquals(8,partProf.getQtdeHorasDedicadas());
		assertEquals("desenvolvedor", partProf.getCargo());
	}
	
	@Test
	public void construtorWithFailTest() throws NaoEncontradaException {
		try {
			new ParticipacaoProfissional(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), "        ", 
					"10/08/2016", 6, 10, 8);
		} catch (ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Cargo de profissional nulo"
					+ " ou vazio", e.getMessage());
		}
		try {
			new ParticipacaoProfissional(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), "freelancer", //nao que freelancers nao sejam 
					"10/08/2016", 6, 10, 8);										//profissional ne, pelo contrario
		} catch (ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Cargo de profissional invalido", e.getMessage());
		}
		try {
			new ParticipacaoProfissional(null, projetoCtrl.recuperaProjeto("Monitoria de lp2"), "desenvolvedor",
					"10/08/2016", 6, 10, 8);
			fail();
		} catch (ValidacaoException e) {
			assertEquals("Pessoa nao pode ser nula", e.getMessage());
		}
		try {
			new ParticipacaoProfissional(pessoaCtrl.recuperaPessoa("112.342.456-12"), null, "desenvolvedor", 
					"10/08/2016", 6, 10, 8);
			fail();
		} catch (ValidacaoException e) {
			assertEquals("Projeto nao pode ser nulo", e.getMessage());
		}
		try {
			new ParticipacaoProfissional(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), "pesquisador", 
					"10/08/2016", 0, 10, 8);
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Duracao invalida", e.getMessage());
		}
		try {
			new ParticipacaoProfissional(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), "pesquisador", 
					"10/08/2016", 6, -5, 8);
		} catch (ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Quantidade de horas invalida",e.getMessage());
		}
	}
	
	@Test
	public void hashEqualsTest() throws ValidacaoException, NaoEncontradaException {
		ParticipacaoProfissional partProf2 = new ParticipacaoProfissional(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), "desenvolvedor",
				"10/08/2016", 
				6, 10, 8);
		assertTrue(partProf.equals(partProf2));

		ParticipacaoProfissional partGrad3 = new ParticipacaoProfissional(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), "gerente", //cargos diferentes diferem associacoes
				"10/08/2016", 
				6, 10, 8);
		assertFalse(partGrad3.equals(partProf)); //note que aqui a unica diferenca seria o cargo
		
		pessoaCtrl.cadastraPessoa("456.274.323-02", "Serena", "siri@apple.com"); //pessoa e projeto continuam diferenciando
		ParticipacaoProfissional partGrad4 = new ParticipacaoProfissional(pessoaCtrl.recuperaPessoa("456.274.323-02"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), "desenvolvedor",
				"10/08/2016", //embora equals tenha sido implementado na classe ParitipacaoProfissional apenas com cargo para
				6, 10, 8); //diferir, ele tambem faz uso do equals da superclasse, tendo aqui um reaproveitamento
		assertFalse(partGrad4.equals(partProf)); //unicamente pessoa de diferente
	}
}