package projetoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.ValidacaoException;
import projeto.Extensao;

/**
 * Testes de unidade da classe Extensao.
 * 
 * @author Gabriel Fernandes
 */
public class ExtensaoTest {
	private Extensao ext;
	
	@Before
	public void setUp() throws Exception {
		ext = new Extensao(0, "Projeto olimpico","Ganhar medalhas de ouro",6,"01/01/2017",6);
	}

	@Test
	public void construtorTest() throws ValidacaoException {
		assertEquals("Projeto olimpico", ext.getNome());
		assertEquals("Ganhar medalhas de ouro", ext.getObjetivo());
		assertEquals("01/01/2017", ext.getDataInicio());
		assertEquals(6, ext.getDuracao());
		assertEquals(6, ext.getImpacto());
	}
	
	@Test
	public void construtorWithFailTest() {
		try {
			new Extensao(0, null,"Ganhar medalhas de ouro",6,"01/01/2017",6);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro no cadastro de projeto: Nome nulo ou vazio",e.getMessage());
		}
		
		try {
			new Extensao(0, "Projeto olimpico","Ganhar medalhas de ouro",6,"01/13/2017",6);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro: Formato de data invalido",e.getMessage());
		}
		try {
			new Extensao(0, "Projeto olimpico","Ganhar medalhas de ouro",7,"01/01/2017",6);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Impacto social precisa ser entre 1 e 6",e.getMessage());
		}
	}
	
	@Test
	public void setImpactoTest() throws ValidacaoException {
		ext.setImpacto(5);
		assertEquals(5,ext.getImpacto());
		
		try {
			ext.setImpacto(7);
			fail();
		}
		catch (ValidacaoException e) {
			assertEquals("Impacto social precisa ser entre 1 e 6", e.getMessage());
		}
	}
}