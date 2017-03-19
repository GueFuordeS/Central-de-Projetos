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
	
	public void removeProjeto(int codigo) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigo);
		projetos.remove(p);
	}

	public String getInfoProjeto(int codigo, String atributo) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigo);
		
		if(atributo.toLowerCase().equals("nome")) {
			return p.getNome();
		}
		else if(atributo.toLowerCase().equals("objetivo")) {
			return p.getObjetivo();
		}
		else if(atributo.toLowerCase().equals("data de inicio")) {
			return p.getDataInicio();
		}
		else if(atributo.toLowerCase().equals("duracao")) {
			return Integer.toString(p.getDuracao());
		}
		else if(atributo.toLowerCase().equals("impacto")) {
			return this.getImpacto(p);
		}
		else if(atributo.toLowerCase().equals("disciplina")) {
			return this.getDisciplina(p);
		}
		else if(atributo.toLowerCase().equals("periodo")) {
			return this.getPeriodo(p);
		}
		else if(atributo.toLowerCase().equals("rendimento")) {
			return this.getRendimento(p);
		}
		else if(atributo.toLowerCase().equals("categoria")) {
			return this.getCategoria(p);
		}
		return null;
	}
	
	private String getCategoria(Projeto p) {
		if(p instanceof PED) {
			PED ext = (PED) p;
			return ext.getCategoria();
		}
		return null;
	}

	private String getRendimento(Projeto p) {
		if(p instanceof Monitoria) {
			Monitoria ext = (Monitoria) p;
			return Integer.toString(ext.getRendimento());
		}
		if(p instanceof PET) {
			PET ext = (PET) p;
			return Integer.toString(ext.getRendimento());
		}
		return null;
	}

	private String getPeriodo(Projeto p) {
		if(p instanceof Monitoria) {
			Monitoria ext = (Monitoria) p;
			return ext.getPeriodo();
		}
		return null;
	}

	private String getDisciplina(Projeto p) {
		if(p instanceof Monitoria) {
			Monitoria ext = (Monitoria) p;
			return ext.getDisciplina();
		}
		return null;
	}

	private String getImpacto(Projeto p) {
		if(p instanceof Extensao) {
			Extensao ext = (Extensao) p;
			return Integer.toString(ext.getImpacto());
		}
		if(p instanceof PET) {
			PET ext = (PET) p;
			return Integer.toString(ext.getImpacto());
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