package myUtilsTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import excecoes.ValidacaoException;
import myUtils.Date;

/**
 * Testes de unidade que asseguram a funcionalidade esperada
 * da classe Date
 * 
 * @author Gabriel Fernandes
 */
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
			assertEquals("Formato de data invalido",e.getMessage());
		}
		
		try {
			new Date("10/08/0");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Formato de data invalido",e.getMessage());
		}
		
		try {
			new Date("00/08/1993");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Formato de data invalido",e.getMessage());
		}
		
		try {
			new Date("10/08/0000");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Formato de data invalido",e.getMessage());
		}
	}
	
	@Test
	public void comparableTest() throws ValidacaoException {
		Date data2 = new Date("10/08/1993");
		Date data3 = new Date("10/08/1992");
		assertEquals(1,data2.compareTo(data3));
		Date data4 = new Date("27/01/2017");
		assertEquals(0,data4.compareTo(data));
		
		final String FIM_DE_LINHA = System.lineSeparator();
		
		ArrayList<Date> lista = new ArrayList<>();
		lista.add(data);
		lista.add(data2);
		lista.add(data3);
		lista.add(data4);
		assertEquals("[Dia: 27"     + FIM_DE_LINHA + // poxa, esse deu trabalho
						"Mes: 1"    + FIM_DE_LINHA +
						"Ano: 2017" + FIM_DE_LINHA +
						", Dia: 10" + FIM_DE_LINHA +
						"Mes: 8"	+ FIM_DE_LINHA +
						"Ano: 1993" + FIM_DE_LINHA +
						", Dia: 10" + FIM_DE_LINHA +
						"Mes: 8"	+ FIM_DE_LINHA +
						"Ano: 1992" + FIM_DE_LINHA +
						", Dia: 27" + FIM_DE_LINHA +
						"Mes: 1"    + FIM_DE_LINHA +
						"Ano: 2017" + FIM_DE_LINHA +
						"]", lista.toString());
		Collections.sort(lista); // compareTo em funcionamento
		assertEquals("[Dia: 10"     + FIM_DE_LINHA + // ja esse nem tanto hehe
						"Mes: 8"    + FIM_DE_LINHA +
						"Ano: 1992" + FIM_DE_LINHA +
						", Dia: 10" + FIM_DE_LINHA +
						"Mes: 8"	+ FIM_DE_LINHA +
						"Ano: 1993" + FIM_DE_LINHA +
						", Dia: 27" + FIM_DE_LINHA +
						"Mes: 1"	+ FIM_DE_LINHA +
						"Ano: 2017" + FIM_DE_LINHA +
						", Dia: 27" + FIM_DE_LINHA +
						"Mes: 1"    + FIM_DE_LINHA +
						"Ano: 2017" + FIM_DE_LINHA +
						"]", lista.toString());
	}
	
	
	@Test
	public void contructorFailTest(){
		try {
			new Date("-12/11/1991");
			fail();
		} catch (Exception e) {
			assertEquals("Formato de data invalido", e.getMessage());
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
