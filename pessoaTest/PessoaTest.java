package pessoaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.ValidacaoException;
import pessoa.Pessoa;

public class PessoaTest {
	Pessoa pessoa;
	
	@Before
	public void setUp() throws Exception {
		pessoa = new Pessoa("222.333.444-55","Natasha","natasha@ccc.ufcg.edu.br");
	}

	@Test
	public void contructorTest() {
		assertEquals("222.333.444-55",pessoa.getCpf());
		assertEquals("Natasha",pessoa.getNome());
		assertEquals("natasha@ccc.ufcg.edu.br",pessoa.getEmail());
	}
	
	@Test
	public void contructorFailTest() {
		try {
			new Pessoa("222.333.444.555","Natasha","natasha@ccc.ufcg.edu.br");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro no cadastro de pessoa: CPF invalido",e.getMessage());
		}
		
		try {
			new Pessoa("222.333.444-55","     ","natasha@ccc.ufcg.edu.br");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro no cadastro de pessoa.",e.getMessage());
		}
		
		try {
			new Pessoa("222.333.444-55","natasha",null);
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro no cadastro de pessoa: Email nulo ou vazio",e.getMessage());
		}
	}	
	
	@Test
	public void settersTest() throws ValidacaoException {
		assertEquals("Natasha",pessoa.getNome());
		pessoa.setNome("Naty");
		assertEquals("Naty",pessoa.getNome());
		
		assertEquals("natasha@ccc.ufcg.edu.br",pessoa.getEmail());
		pessoa.setEmail("naty@ccc.ufcg.edu.br");
		assertEquals("naty@ccc.ufcg.edu.br",pessoa.getEmail());
	}
	
	@Test
	public void hashEqualsTest() throws ValidacaoException {
		Pessoa p2 = new Pessoa("222.333.444-55","Naty","naty@ccc.ufcg.edu.br");
		assertTrue(pessoa.equals(p2)); //note que apenas o cpf diferenciam duas pessoas
		
		Pessoa p3 = new Pessoa("222.333.444-56","Natasha","natasha@ccc.ufcg.edu.br");
		assertFalse(pessoa.equals(p3));
	}
	
	@Test
	public void toStringTest() {
		final String FIM_DE_LINHA = System.lineSeparator();
		String string = "Cpf: 222.333.444-55"   + FIM_DE_LINHA +
		        "Nome: Natasha"                 + FIM_DE_LINHA +
		        "Email: natasha@ccc.ufcg.edu.br"+ FIM_DE_LINHA;
		assertEquals(string, pessoa.toString());
	}
	
	
	@Test
	public void getInfoBolsaTest() {
		assertEquals(0,pessoa.getInfoBolsa(),0.1); //pessoa nao tem a expertise para criar controllers e se adcionar a projetos
	}													//para mudar este valor, mas com o easyaccept mostra-se que funcionam
															//como o esperado
	@Test
	public void calculaPontuacaoPorParticipacaoTest() {
		assertEquals(0,pessoa.calculaPontuacaoPorParticipacao(),0.1); //mesmo caso anterior
	}
}