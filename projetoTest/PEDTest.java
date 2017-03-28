package projetoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.ValidacaoException;
import projeto.PED;
import projeto.Produtividade;

public class PEDTest {
	private PED ped;
	
	@Before
	public void setUp() throws ValidacaoException {
		ped = new PED(0,"APLICACAO DA MINERACAO DE DADOS EM REPOSITORIOS DINAMICOS", "PIBITI", 
				2, 4, 1,"Desenvolvimento tecnologico e inovacao", "01/01/2017", 24);
	}
	
	@Test
	public void construtorTest() {
		assertEquals("PIBITI", ped.getCategoria());
		assertEquals(24, ped.getDuracao());
		assertEquals("APLICACAO DA MINERACAO DE DADOS EM REPOSITORIOS DINAMICOS", ped.getNome());
	}

	@Test
	public void construtorWithFail() {
		try {
			new PED(0, null, "PIBITI", 
					2, 4, 1,"Desenvolvimento tecnologico e inovacao", "01/01/2017", 24);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro no cadastro de projeto: Nome nulo ou vazio", e.getMessage());
		}
		try {
			new PED(0, "APLICACAO DA MINERACAO DE DADOS EM REPOSITORIOS DINAMICOS", "PIBITI", 
					2, 4, 1,"Desenvolvimento tecnologico e inovacao", "01/01/2017", 0);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro no cadastro de projeto: Duracao invalida", e.getMessage());
		}
	}
	
	@Test
	public void setImpactoTest() throws ValidacaoException {
		ped.setProdutividade(Produtividade.PATENTES, "5");
		assertEquals(5, ped.getValor(Produtividade.PATENTES));
		
		try {
			ped.setProdutividade(Produtividade.PRODACADEMICA, "abacaxi");
		}
		catch (ValidacaoException e) {
			assertEquals("Producao tecnica precisa ter um valor inteiro valido", e.getMessage());
		}
		
		try {
			ped.setProdutividade(Produtividade.PRODACADEMICA, null);
		}
		catch (ValidacaoException e) {
			assertEquals("Producao tecnica precisa ter um valor inteiro valido", e.getMessage());
		}
	}
}