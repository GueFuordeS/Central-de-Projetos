package projetoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.*;
import myUtils.Codigo;
import projeto.*;

/**
 * Testes de unidades da classe PessoaController, 
 * 
 * @author Gabriel Fernandes
 */
public class ProjetoControllerTest {
	ProjetoController controller;

	/*
	 * neste caso testes bem gerais,
	 * visto que testes de excecoes ja foram testados em cada subclasse de projeto e 		
	 * com o easyaccept passsamos em todos os testes do script fornecido
	 * (fica meio redundante utilizar-se do junit e do easyaccept, desnecessario inclusive)
	 */
	@Before
	public void setUp() throws ValidacaoException {
		controller = new ProjetoController();
	}
	
	/**
	 * Uso de uma variavel de nome codigo em todos os casos de teste, para garantir 
	 * o sucesso nao importa
	 * quantas vezes o teste seja rodado
	 * (lembre-se, o junit roda os metodos de test aleatoriamente)
	 * 
	 * @throws ValidacaoException Em caso de um erro de validacao
	 */
	@Test
	public void adicionaProjetotest() throws ValidacaoException {
		int codigo = Codigo.getCodigo();
		assertEquals(codigo+1, controller.adicionaExtensao("Projeto olimpico","Ganhar medalhas de ouro",6,"01/01/2017",6));
		assertEquals(codigo+2, controller.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12));
		assertEquals(codigo+3 ,controller.adicionaPED("APLICACAO DA MINERACAO DE DADOS EM REPOSITORIOS DINAMICOS", "PIBITI", 
				2, 4, 1,"Desenvolvimento tecnologico e inovacao", "01/01/2017", 24));
		assertEquals(codigo+4, controller.adicionaPET("PET Computacao", "Objetivo generico", 1, 80, 1, 2, 0, "03/02/2017", 12));
	}
	
	@Test
	public void removeProjetoTest() throws ValidacaoException {
		controller.adicionaExtensao("Projeto olimpico","Ganhar medalhas de ouro",6,"01/01/2017",6);
		controller.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);

		int codigo = Codigo.getCodigo();
		try {
			controller.removeProjeto(codigo-1);
		}
		catch(ValidacaoException e) {
			fail();
		}
		catch(NaoEncontradaException e) {
			fail();
		}
		
		try {
			controller.removeProjeto(codigo);
		}
		catch(ValidacaoException e) {
			fail();
		}
		catch(NaoEncontradaException e) {
			fail();
		}
	}
	
	@Test
	public void getInfoProjetoTest() throws ValidacaoException, NaoEncontradaException {
		controller.adicionaExtensao("Projeto olimpico","Ganhar medalhas de ouro",6,"01/01/2017",6);
		controller.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);

		int codigo = Codigo.getCodigo();
		assertEquals("01/01/2017",controller.getInfoProjeto(codigo-1, "data de inicio"));
		assertEquals("95",controller.getInfoProjeto(codigo, "rendimento"));
	}
	
	@Test
	public void editaProjetoTest() throws ValidacaoException, NaoEncontradaException {
		controller.adicionaExtensao("Projeto olimpico","Ganhar medalhas de ouro",6,"01/01/2017",6);
		controller.adicionaMonitoria("Monitoria de lp2", "lp2", 95, "auxiliar","2016.2", "01/01/2017", 12);
		
		int codigo = Codigo.getCodigo();
		controller.editaProjeto(codigo-1, "impacto", "4");
		controller.editaProjeto(codigo, "rendimento", "80");
		
		assertEquals("4", controller.getInfoProjeto(codigo-1, "impacto"));
		assertEquals("80", controller.getInfoProjeto(codigo, "rendimento"));
	}
}