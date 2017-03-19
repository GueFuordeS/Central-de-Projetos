package projeto;

import java.util.HashSet;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import static myUtils.Codigo.*;

public class ProjetoController {
	private HashSet<Projeto> projetos;
	
	public ProjetoController() {
		this.projetos = new HashSet<>();
	}

	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo,
			 String periodo, String dataInicio, int duracao) throws ValidacaoException {
		int codigo = geraCodigo();
		
		projetos.add(new Monitoria(codigo, nome, disciplina, rendimento, objetivo,
		 periodo, dataInicio, duracao));
		
		return codigo;
	}
	
	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, 
			int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) throws ValidacaoException {
		int codigo = geraCodigo();
		
		projetos.add(new PET(codigo, nome, objetivo, impacto, rendimento, 
			 prodTecnica, prodAcademica, patentes, dataInicio, duracao));
		
		return codigo;
	}
	
	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) 
			throws ValidacaoException {
		int codigo = geraCodigo();
		
		projetos.add(new Extensao(codigo, nome, objetivo, impacto, dataInicio, duracao));
		return codigo;
	}
	
	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, 
			String objetivo, String dataInicio, int duracao) throws ValidacaoException {
		int codigo = geraCodigo();
		
		projetos.add(new PED(codigo, nome, categoria, prodTecnica, prodAcademica, patentes, 
				objetivo, dataInicio, duracao));
		return codigo;
	}

	public String getInfoProjeto(int codigo, String atributo) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigo);
		
		if(atributo.toLowerCase().equals("nome")) {
			return p.getNome();
		}
		else if(atributo.toLowerCase().equals("objetivo")) {
			return p.getObjetivo();
		}
		else if(atributo.toLowerCase().equals("dataInicio")) {
			return p.getDataInicio();
		}
		else if(atributo.toLowerCase().equals("duracao")) {
			return Integer.toString(p.getDuracao());
		}
		return null;
	}
	
	public Projeto recuperaProjeto(int codigo) throws NaoEncontradaException, ValidacaoException {
		for(Projeto p:this.projetos) {
			if(p.getCodigo() == codigo) {
				return p;
			}
		}
		throw new NaoEncontradaException();
	}
	
	public Projeto recuperaProjeto(String nome) throws NaoEncontradaException, ValidacaoException {
		for(Projeto p:this.projetos) {
			if(p.getNome() == nome) {
				return p;
			}
		}
		throw new NaoEncontradaException();
	}
	
	public int getCodigoProjeto(String nome) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(nome);
		return p.getCodigo();
	}
}