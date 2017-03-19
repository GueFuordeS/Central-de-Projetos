package projetoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.ValidacaoException;
import projeto.Extensao;

public class ExtensaoTest {
	private Extensao ext;
	
	@Before
	public void setUp() throws Exception {
		ext = new Extensao("Projeto olimpico","Ganhar medalhas de ouro",12,"01/01/2017",6);
	}

	@Test
	public void construtorTest() throws ValidacaoException {
		assertEquals("Projeto olimpico", ext.getNome());
		assertEquals("Ganhar medalhas de ouro", ext.getObjetivo());
		assertEquals("01/01/2017", ext.getDataInicio());
		assertEquals(6, ext.getDuracao());
		assertEquals(12, ext.getImpacto());
	}
	
	@Test
	public void construtorWithFailTest() {
		try {
			new Extensao(null,"Ganhar medalhas de ouro",12,"01/01/2017",6);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Nome de projeto nao pode ser nulo ou vazio",e.getMessage());
		}
		
		try {
			new Extensao("Projeto olimpico","Ganhar medalhas de ouro",12,"01/13/2017",6);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Formato de data invalido",e.getMessage());
		}
	}
}