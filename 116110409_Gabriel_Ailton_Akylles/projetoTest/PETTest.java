package projetoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.ValidacaoException;
import projeto.PET;

public class PETTest {
	PET pet;
	
	@Before
	public void setUp() throws ValidacaoException {
		pet = new PET(0, "PET Computacao", "Objetivo generico", 1, 80, 1, 2, 0, "03/02/2017", 12);
	}
	
	@Test
	public void construtorTest() {
		assertEquals(80, pet.getRendimento());
		assertEquals(12, pet.getDuracao());
		assertEquals("PET Computacao", pet.getNome());
	}
	
	@Test
	public void construtorWithFail() {
		try {
			new PET(0, "    ", "Objetivo generico", 1, 80, 1, 2, 0, "03/02/2017", 12);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro no cadastro de projeto: Nome nulo ou vazio", e.getMessage());
		}
		try {
			new PET(0, "PET Computacao", "Objetivo generico", 1, -15, 1, 2, 0, "03/02/2017", 12);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Rendimento precisa estar entre 0 e 100", e.getMessage());
		}
	}
}