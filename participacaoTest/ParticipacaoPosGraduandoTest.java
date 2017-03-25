package participacaoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import participacao.ParticipacaoPosGraduando;
import pessoa.*;
import projeto.*;

public class ParticipacaoPosGraduandoTest {
	PessoaController pessoaCtrl;
	ProjetoController projetoCtrl;
	ParticipacaoPosGraduando partPosGrad;

	@Before
	public void setUp() throws ValidacaoException, NaoEncontradaException {
		pessoaCtrl = new PessoaController();
		projetoCtrl = new ProjetoController();
		
		pessoaCtrl.cadastraPessoa("112.342.456-12", "Natasha", "naty@gmail.com");	
		projetoCtrl.adicionaMonitoria("Monitoria de lp2", "lp2", 5, "tirar as duvidas da galera", "2016.2", 
				"10/08/2016", 6);
		
		partPosGrad = new ParticipacaoPosGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), "doutorado", "10/08/2016", 
				6, 10, 8);
	}

	@Test
	public void construtorTest() { //construtor test pois mostra q a construcao do objeto no setUp() funcionou
		assertEquals("Monitoria de lp2", partPosGrad.getProjeto().getNome());
		assertEquals("Natasha", partPosGrad.getPessoa().getNome());
		assertEquals(8,partPosGrad.getQtdeHorasDedicadas());
		assertEquals("doutorado", partPosGrad.getTitulacao());
	}
	
	@Test
	public void construtorWithFailTest() throws NaoEncontradaException {
		try {
			new ParticipacaoPosGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), "        ", 
					"10/08/2016", 6, 10, 8);
		} catch (ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Titulacao de posgraduando nula ou vazia", 
					e.getMessage());
		}
		try {
			new ParticipacaoPosGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), "tecnico",
					"10/08/2016", 6, 10, 8);
		} catch (ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: titulaco de posgraduando invalida", e.getMessage());
		}
		try {
			new ParticipacaoPosGraduando(null, projetoCtrl.recuperaProjeto("Monitoria de lp2"), "doutorado",
					"10/08/2016", 6, 10, 8);
			fail();
		} catch (ValidacaoException e) {
			assertEquals("Pessoa nao pode ser nula", e.getMessage());
		}
		try {
			new ParticipacaoPosGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), null, "doutorado", 
					"10/08/2016", 6, 10, 8);
			fail();
		} catch (ValidacaoException e) {
			assertEquals("Projeto nao pode ser nulo", e.getMessage());
		}
		try {
			new ParticipacaoPosGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), "doutorado", 
					"10/08/2016", 0, 10, 8);
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Duracao invalida", e.getMessage());
		}
		try {
			new ParticipacaoPosGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
					projetoCtrl.recuperaProjeto("Monitoria de lp2"), "doutorado", 
					"10/08/2016", 6, -5, 8);
		} catch (ValidacaoException e) {
			assertEquals("Erro na associacao de pessoa a projeto: Quantidade de horas invalida",e.getMessage());
		}
	}
	
	@Test
	public void hashEqualsTest() throws ValidacaoException, NaoEncontradaException {
		ParticipacaoPosGraduando partProf2 = new ParticipacaoPosGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), "doutorado",
				"10/08/2016", 
				6, 10, 8);
		assertTrue(partPosGrad.equals(partProf2));

		ParticipacaoPosGraduando partGrad3 = new ParticipacaoPosGraduando(pessoaCtrl.recuperaPessoa("112.342.456-12"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), "mestrado", //titulacao diferem associacoes
				"10/08/2016", 
				6, 10, 8);
		assertFalse(partGrad3.equals(partPosGrad)); //note que aqui a unica diferenca seria a titulacao
		
		pessoaCtrl.cadastraPessoa("456.274.323-02", "Serena", "siri@apple.com");
		ParticipacaoPosGraduando partGrad4 = new ParticipacaoPosGraduando(pessoaCtrl.recuperaPessoa("456.274.323-02"), 
				projetoCtrl.recuperaProjeto("Monitoria de lp2"), "doutorado",
				"10/08/2016", //note aqui o reuso do equals da superclasse
				6, 10, 8); //possibilitando fazer a checagem de igualdade tambem pela pessoa e projeto
		assertFalse(partGrad4.equals(partPosGrad)); //unicamente pessoa de diferente
	}
}