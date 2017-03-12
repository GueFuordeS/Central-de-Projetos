package projetoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.ValidacaoException;
import projeto.Date;

public class DateTest {
	Date data;
	
	@Before
	public void setUp() throws Exception {
		data = new Date("27/01/2017");
	}

	@Test
	public void construtorTest() {
		assertEquals(27,data.getDia());
		assertEquals(01,data.getMes());
		assertEquals(2017,data.getAno());
	}
	
	@Test
	public void construtorWithFailTest() {
		try {
			new Date("10/08/1993");
		}
		catch(ValidacaoException e) {
			fail();
		}
		
		try {
			new Date("100/080/190");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro: formato invalido de data",e.getMessage());
		}
		
		try {
			new Date("10/08/0");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro: formato invalido de data",e.getMessage());
		}
		
		try {
			new Date("00/08/1993");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Dia, mes ou ano nao podem ser 0",e.getMessage());
		}
		
		try {
			new Date("10/08/0000");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Dia, mes ou ano nao podem ser 0",e.getMessage());
		}
	}
		
	@Test
	public void hashEqualsTest() throws ValidacaoException {
		Date data2 = new Date("10/08/1993");
		assertFalse(data.equals(data2));
		
		Date data3 = new Date("27/01/2017");
		assertTrue(data.equals(data3));
		
		Date data4 = new Date("27/01/1993");
		assertFalse(data.equals(data4));
	}
	
	@Test
	public void toStringTest() {
		final String FIM_DE_LINHA = System.lineSeparator();
		String string = "Dia: 27" + FIM_DE_LINHA +
				        "Mes: 1"  + FIM_DE_LINHA +
				        "Ano: 2017"+FIM_DE_LINHA;
		assertEquals(string, data.toString());
	}
}
