package myUtilsTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import excecoes.ValidacaoException;
import myUtils.Periodo;

/**
 * Testes de unidade que asseguram a funcionalidade esperada
 * da classe Periodo
 * 
 * @author Gabriel Fernandes
 */
public class PeriodoTest {
	private Periodo periodo;
	
	@Before
	public void setUp() throws ValidacaoException {
		periodo = new Periodo("2016.1");
	}
	
	@Test
	public void construtorTest() {
		assertEquals(2016,periodo.getAno());
		assertEquals(1,periodo.getSemestre());
	}
	
	@Test
	public void construtorWithFailTest() {
		try {
			new Periodo("1099.1"); //se bem que ja haviam universidades nesse tempo ne hehe
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro: Formato de data invalido", e.getMessage());
		}
		
		try {
			new Periodo("3229.1"); //in the future
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro: Formato de data invalido", e.getMessage());
		}
		
		try {
			new Periodo("    "); //so tem vento aqui...
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Formato de periodo invalido", e.getMessage());
		}
		
		try {
			new Periodo("2009-2"); //Ambos funfam
			new Periodo("2009.2");
			
		}
		catch(ValidacaoException e) {
			fail();
		}
		
		try {
			new Periodo("2009.b"); //semestre b? ate q poderia ser valido ne
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Formato de periodo invalido", e.getMessage());
		}
		
		try {
			new Periodo("20099.1"); //se bem que ja haviam universidades nesse tempo ne hehe
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Formato de periodo invalido", e.getMessage());
		}
	}
	
	@Test
	public void comparableTest() throws ValidacaoException {
		Periodo p1 = new Periodo("2012.1");
		Periodo p2 = new Periodo("2023.1");
		Periodo p3 = new Periodo("2016.1");
		
		assertEquals(1,periodo.compareTo(p1));
		assertEquals(-1,periodo.compareTo(p2));
		assertEquals(0,periodo.compareTo(p3));
		
		ArrayList<Periodo> lista = new ArrayList<>();
		lista.add(periodo);
		lista.add(p1);
		lista.add(p2);
		lista.add(p3);
		assertEquals("[2016.1, 2012.1, 2023.1, 2016.1]", lista.toString());
		Collections.sort(lista); //aqui onde acontece a magica
		assertEquals("[2012.1, 2016.1, 2016.1, 2023.1]", lista.toString());
	}

	@Test
	public void hashEqualsTest() throws ValidacaoException {
		Periodo periodo2 = new Periodo("2016.1");
		assertTrue(periodo.equals(periodo2));
		Periodo periodo3 = new Periodo("2017.1");
		assertFalse(periodo3.equals(periodo));
		assertEquals(new Periodo("2019.1"), new Periodo("2019.1"));
		assertNotEquals(new Periodo("2012.2"), new Periodo("2020.2"));
	}

	@Test
	public void toStringTest() {
		assertEquals("2016.1",periodo.toString());
	}
}