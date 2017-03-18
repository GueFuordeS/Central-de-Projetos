package pessoaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import pessoa.*;

public class PessoaControllerTest {
	PessoaController pessoas;
	
	@Before
	public void setUp() throws Exception {
		pessoas = new PessoaController();
		pessoas.cadastraPessoa("222.333.444-55","Natasha","natasha@ccc.ufcg.edu.br");
	}
	
	@Test
	public void cadastraPessoaTest() throws NaoEncontradaException, ValidacaoException  {
		try {
			assertEquals("111.111.111-11",pessoas.cadastraPessoa("111.111.111-11", "manaus", "manaus@ccc.ufcg.edu.br"));
		}
		catch(ValidacaoException e) {
			fail();
		}
		
		try {
			pessoas.cadastraPessoa("111.111.111.11", "manaus", "manaus@ccc.ufcg.edu.br");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro no cadastro de pessoa: CPF invalido",e.getMessage());
		}
		
		assertEquals("manaus",pessoas.recuperaPessoa("111.111.111-11").getNome());
	}
	
	@Test
	public void removePessoaTest() throws ValidacaoException {
		try {
			pessoas.removePessoa("222.333.444-50");
			fail();
		}
		catch(Exception e) {
			assertEquals("Erro na consulta de pessoa: Pessoa nao encontrada",e.getMessage());
		}
		try {
			pessoas.removePessoa("222.333.444-55");
		}
		catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void recuperaPessoaTest() throws NaoEncontradaException, ValidacaoException {
		Pessoa pessoinha = pessoas.recuperaPessoa("222.333.444-55");
		assertEquals("Natasha",pessoinha.getNome());
		
		pessoas.cadastraPessoa("134.134.134-84", "Roberta", "roberta@yahoo.com");
		assertEquals(new Pessoa("134.134.134-84", "Roberta", "roberta@yahoo.com"),pessoas.recuperaPessoa("134.134.134-84"));
		
		try {
			pessoas.recuperaPessoa("134.134.134-84");
		}
		catch(NaoEncontradaException e) {
			fail();
		}
		
		try {
			pessoas.recuperaPessoa("700.600.500-40");
			fail();
		}
		catch(NaoEncontradaException e) {
			assertEquals("Erro na consulta de pessoa: Pessoa nao encontrada",e.getMessage());
		}
	}
	
	@Test
	public void editaPessoaTest() throws NaoEncontradaException, ValidacaoException {
		assertEquals("Natasha",pessoas.recuperaPessoa("222.333.444-55").getNome());
		pessoas.editaPessoa("222.333.444-55", "nome", "Naty");
		assertEquals("Naty",pessoas.recuperaPessoa("222.333.444-55").getNome());
		
		assertEquals("natasha@ccc.ufcg.edu.br",pessoas.recuperaPessoa("222.333.444-55").getEmail());
		pessoas.editaPessoa("222.333.444-55", "eMail", "naty@ccc.ufcg.edu.br");
		assertEquals("naty@ccc.ufcg.edu.br",pessoas.recuperaPessoa("222.333.444-55").getEmail());
		
		try {
			pessoas.editaPessoa("222.333.444-55", "CPF", "222.333.444-50");
			fail();
		}
		catch(ValidacaoException e) {
			assertEquals("Erro na atualizacao de pessoa: CPF nao pode ser alterado",e.getMessage());
		}
	}

	@Test
	public void toStringTest() throws ValidacaoException {
		final String FIM_DE_LINHA = System.lineSeparator();
		assertEquals("Cpf: 222.333.444-55"
				   + FIM_DE_LINHA
				   + "Nome: Natasha"
				   + FIM_DE_LINHA
				   + "Email: natasha@ccc.ufcg.edu.br"
				   + FIM_DE_LINHA
				   , pessoas.toString());
	}
}
