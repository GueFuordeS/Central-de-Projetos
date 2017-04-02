package uascTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import projeto.ProjetoController;
import uasc.UASC;

/**
 * Testes de unidade da classe UASC.
 * 
 * @author Gabriel Fernandes
 */
public class UASCTest {
	ProjetoController projController;
	UASC uasc;

	@Before
	public void setUp() throws ValidacaoException, NaoEncontradaException {
		projController = new ProjetoController();
		uasc = new UASC(null, projController, null);
		
		projController.adicionaExtensao("Reflorestamento da ufcg", "trocar as arvores da ufcg por ipês amarelo"
				, 6, "01/06/2017", 12); //duracao so do plantio, porque o florescimento comeca em media depois de 3 anos
		projController.atualizaDespesasProjeto(projController.getCodigoProjeto("Reflorestamento da ufcg")
				, 250000.0, 15000.0, 0);
	}
	
	
	@Test
	public void getReceitaTest() throws ValidacaoException {
		assertEquals(18550.0, uasc.getReceita(), 0.1);
		uasc.diminuiReceita(8550);
		assertEquals(10000.0, uasc.getReceita(), 0.1);
	}
	
	@Test
	public void diminuiReceitaWithFailTest() {
		assertEquals(18550.0, uasc.getReceita(), 0.1);
		
		try {
			uasc.diminuiReceita(-1000);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na atualizacao da receita da unidade: valor negativo", e.getMessage());
		}
		
		try {
			uasc.diminuiReceita(1000);
		}
		catch(ValidacaoException e) {
			fail();
		}
		
		assertEquals(17550.0, uasc.getReceita(), 0.1);
		
		try {
			uasc.diminuiReceita(18000);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na atualizacao da receita da unidade: "
				+ "a unidade nao possui fundos suficientes", e.getMessage());
		}
	}
	
	@Test
	public void calculaColaboracaoTotalUASCTest() throws ValidacaoException, NaoEncontradaException {
		assertEquals(18550, uasc.calculaColaboracaoTotalUASC(), 0.1);
		
		projController.adicionaPED("Ubuntu Phone", "COOP", 
				2, 0, 5,"Parceria para desenvolvimento do ubuntu phone", "01/01/2017", 24);
		
		projController.atualizaDespesasProjeto(projController.getCodigoProjeto("Ubuntu Phone"), 
				150000.0, 100000.0, 50000.0);
		
		assertEquals(40800, projController.calculaColaboracaoUASC(projController.getCodigoProjeto("Ubuntu Phone")), 0.1);
		assertEquals(59350, uasc.calculaColaboracaoTotalUASC(), 0.1);
	}
}