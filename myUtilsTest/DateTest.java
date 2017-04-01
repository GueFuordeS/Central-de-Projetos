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
		assertEquals(27, data.getDia());
		assertEquals(01, data.getMes());
		assertEquals(2017, data.getAno());
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
			assertEquals("Erro: Formato de data invalido",e.getMessage());
		}
		
		try {
			new Date("10/08/0");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro: Formato de data invalido",e.getMessage());
		}
		
		try {
			new Date("00/08/1993");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro: Formato de data invalido",e.getMessage());
		}
		
		try {
			new Date("10/08/0000");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro: Formato de data invalido",e.getMessage());
		}
	}
	
	@Test
	public void comparableTest() throws ValidacaoException {
		Date data2 = new Date("10/08/1993");
		Date data3 = new Date("10/08/1992");
		assertEquals(1,data2.compareTo(data3));
		Date data4 = new Date("27/01/2017");
		assertEquals(0,data4.compareTo(data));

		ArrayList<Date> lista = new ArrayList<>();
		lista.add(data);
		lista.add(data2);
		lista.add(data3);
		lista.add(data4);
		assertEquals("[27/01/2017, 10/08/1993, 10/08/1992, 27/01/2017]", lista.toString());
		Collections.sort(lista); // compareTo em funcionamento
		assertEquals("[10/08/1992, 10/08/1993, 27/01/2017, 27/01/2017]", lista.toString());
	}
	
	
	@Test
	public void contructorFailTest(){
		try {
			new Date("-12/11/1991");
			fail();
		} catch (Exception e) {
			assertEquals("Erro: Formato de data invalido", e.getMessage());
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
		assertEquals("27/01/2017", data.toString());
	}
	
	@Test
	public void toStringAMDTest() {
		assertEquals("2017-01-27", data.toStringAMD());
	}
	
	@Test
	public void addMesesTest() throws ValidacaoException {
		data.addMeses(28); //adcionando 28 meses à data
		assertEquals("2019-05-27", data.toStringAMD()); //usando o formato aaaa-mm-dd
	}
	
	@Test
	public void addMesesWithFail() {
		try {
			data.addMeses(0); //adicionar 0 meses
			fail();
		} catch (Exception e) {
			assertEquals("Quantidade de meses nao pode ser menor ou igual a zero", e.getMessage());
		}
		
		try {
			data.addMeses(-13); //adicionando meses, só que negativo
			fail();
		} catch (Exception e) {
			assertEquals("Quantidade de meses nao pode ser menor ou igual a zero", e.getMessage());
		}
	}
	
	@Test
	public void geraDataTerminoTest() throws ValidacaoException {
		Date data2 = data.geraDataTermino(14); //adicionando 14 meses à data
		assertEquals("2017-01-27", data.toStringAMD()); //data anterior
		assertEquals("2018-03-27", data2.toStringAMD()); //nova data, usada pra simbolizar a data decorrido o tempo
		
		assertEquals(1, data2.compareTo(data)); //nova data sempre sera maior que a anterior
	}
}