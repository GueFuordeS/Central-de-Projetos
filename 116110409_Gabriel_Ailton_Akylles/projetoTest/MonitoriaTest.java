package projetoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.ValidacaoException;
import projeto.Monitoria;

public class MonitoriaTest {
	private Monitoria ext;
	
	@Before
	public void setUp() throws Exception {
		ext = new Monitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);
	}

	@Test
	public void construtorTest() throws ValidacaoException {
		assertEquals("Monitoria de lp2", ext.getNome());
		assertEquals("auxiliar", ext.getObjetivo());
		assertEquals("01/01/2017", ext.getDataInicio());
		assertEquals(12, ext.getDuracao());
		assertEquals(95, ext.getRendimento());
	}
	
	@Test
	public void construtorWithFailTest() {
		try {
			new Monitoria("     ", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Nome de projeto nao pode ser nulo ou vazio",e.getMessage());
		}
		
		try {
			new Monitoria("Monitoria de lp2", "lp2", 105, "auxiliar","2016.2", "01/01/2017", 12);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Rendimento precisa estar entre 0 e 100",e.getMessage());
		}
	}
}