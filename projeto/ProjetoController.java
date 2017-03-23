package projeto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import participacao.Participacao;
import static myUtils.Codigo.*;

public class ProjetoController {
	private Set<Projeto> projetos;
	
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
		else if(atributo.toLowerCase().equals("producao academica")) {
			return this.getProdAcademica(p);
		}
		else if(atributo.toLowerCase().equals("producao tecnica")) {
			return this.getProdTecnica(p);
		}
		else if(atributo.toLowerCase().equals("patentes")) {
			return this.getPatentes(p);
		}
		else if(atributo.toLowerCase().equals("participacoes")){
			ArrayList<Participacao> participacoes = p.getParticipacoes();
			
			String participacoesRetorno = "";
			
			for (int i = 0; i < participacoes.size(); i++) {
				System.out.println(participacoes.get(i).getPessoa().getNome());
				if(i<participacoes.size()-1)
					participacoesRetorno += participacoes.get(i).getPessoa().getNome() + ", ";
				else { 
					participacoesRetorno += participacoes.get(i).getPessoa().getNome();
				}
			}
			return participacoesRetorno;
		}
		throw new ValidacaoException("Erro na consulta de projeto: Atributo nulo ou invalido");
	}
	
	private String getPatentes(Projeto p) throws ValidacaoException {
		if(p instanceof PED) {
			PED ext = (PED) p;
			return Integer.toString(ext.getValor(Produtividade.PATENTES));
		}
		if(p instanceof PET) {
			PET ext = (PET) p;
			return Integer.toString(ext.getValor(Produtividade.PATENTES));
		}
		if(p instanceof Monitoria) {
			throw new ValidacaoException("Erro na consulta de projeto: Monitoria nao possui Patentes");
		}
		if(p instanceof Extensao) {
			throw new ValidacaoException("Erro na consulta de projeto: Extensao nao possui Patentes");
		}
		return null;
	}

	private String getProdTecnica(Projeto p) throws ValidacaoException {
		if(p instanceof PED) {
			PED ext = (PED) p;
			return Integer.toString(ext.getValor(Produtividade.PRODTECNICA));
		}
		if(p instanceof PET) {
			PET ext = (PET) p;
			return Integer.toString(ext.getValor(Produtividade.PRODTECNICA));
		}
		if(p instanceof Monitoria) {
			throw new ValidacaoException("Erro na consulta de projeto: Monitoria nao possui Producao tecnica");
		}
		if(p instanceof Extensao) {
			throw new ValidacaoException("Erro na consulta de projeto: Extensao nao possui Producao tecnica");
		}
		return null;
	}

	private String getProdAcademica(Projeto p) throws ValidacaoException {
		if(p instanceof PED) {
			PED ext = (PED) p;
			return Integer.toString(ext.getValor(Produtividade.PRODACADEMICA));
		}
		if(p instanceof PET) {
			PET ext = (PET) p;
			return Integer.toString(ext.getValor(Produtividade.PRODACADEMICA));
		}
		if(p instanceof Monitoria) {
			throw new ValidacaoException("Erro na consulta de projeto: Monitoria nao possui Producao academica");
		}
		if(p instanceof Extensao) {
			throw new ValidacaoException("Erro na consulta de projeto: Extensao nao possui Producao academica");
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
		throw new NaoEncontradaException("Erro na consulta de projeto: Projeto nao encontrado");
	}
	
	public Projeto recuperaProjeto(String nome) throws NaoEncontradaException, ValidacaoException {
		for(Projeto p:this.projetos) {
			if(p.getNome().equals(nome)) {
				return p;
			}
		}
		throw new NaoEncontradaException("Erro na consulta de projeto: Projeto nao encontrado");
	}
	
	public void editaProjeto(int codigo, String atributo, String valor) 
			throws NaoEncontradaException, ValidacaoException {
		
		Projeto p = recuperaProjeto(codigo);
		
		if(atributo.toLowerCase().equals("nome")) {
			p.setNome(valor);
		}
		else if(atributo.toLowerCase().equals("objetivo")) {
			p.setObjetivo(valor);
		}
		else if(atributo.toLowerCase().equals("data de inicio")) {
			p.setDataInicio(valor);
		}
		else if(atributo.toLowerCase().equals("duracao")) {
			p.setDuracao(Integer.parseInt(valor));
		}
		else if(atributo.toLowerCase().equals("impacto")) {
			this.setImpacto(p, valor);
		}
		else if(atributo.toLowerCase().equals("disciplina")) {
			this.setDisciplina(p, valor);
		}
		else if(atributo.toLowerCase().equals("periodo")) {
			this.setPeriodo(p, valor);
		}
		else if(atributo.toLowerCase().equals("rendimento")) {
			this.setRendimento(p, valor);
		}
		else if(atributo.toLowerCase().equals("categoria")) {
			this.setCategoria(p, valor);
		}
		else if(atributo.toLowerCase().equals("producao academica")) {
			this.setProdAcademica(p, valor);
		}
		else if(atributo.toLowerCase().equals("producao tecnica")) {
			this.setProdTecnica(p, valor);
		}
		else if(atributo.toLowerCase().equals("patentes")) {
			this.setPatentes(p, valor);
		}
	}
	
	private void setPatentes(Projeto p, String valor) throws NumberFormatException, ValidacaoException {
		if(p instanceof PED) {
			((PED) p).setProdutividade(Produtividade.PATENTES, valor);
		}
		if(p instanceof PET) {
			((PET) p).setProdutividade(Produtividade.PATENTES, valor);
		}
		
	}

	private void setProdTecnica(Projeto p, String valor) throws NumberFormatException, ValidacaoException {
		if(p instanceof PED) {
			((PED) p).setProdutividade(Produtividade.PRODTECNICA, valor);
		}
		if(p instanceof PET) {
			((PET) p).setProdutividade(Produtividade.PRODTECNICA, valor);
		}
	}

	private void setProdAcademica(Projeto p, String valor) throws NumberFormatException, ValidacaoException {
		if(p instanceof PED) {
			((PED) p).setProdutividade(Produtividade.PRODACADEMICA, valor);
		}
		if(p instanceof PET) {
			((PET) p).setProdutividade(Produtividade.PRODACADEMICA, valor);
		}
	}

	private void setCategoria(Projeto p, String valor) throws ValidacaoException {
		if(p instanceof PED) {
			((PED) p).setCategoria(valor);
		}
	}

	private void setRendimento(Projeto p, String valor) throws NumberFormatException, ValidacaoException {
		if(p instanceof Monitoria) {
			((Monitoria) p).setRendimento(Integer.parseInt(valor));
		}
		if(p instanceof PET) {
			((PET) p).setRendimento(Integer.parseInt(valor));
		}
	}

	private void setPeriodo(Projeto p, String valor) throws ValidacaoException {
		if(p instanceof Monitoria) {
			((Monitoria) p).setPeriodo(valor);
		}
	}

	private void setDisciplina(Projeto p, String valor) throws ValidacaoException {
		if(p instanceof Monitoria) {
			((Monitoria) p).setDisciplina(valor);
		}
	}

	private void setImpacto(Projeto p, String valor) throws NumberFormatException, ValidacaoException {
		if(p instanceof Extensao) {
			((Extensao) p).setImpacto(Integer.parseInt(valor));
		}
		if(p instanceof PET) {
			((PET) p).setImpacto(Integer.parseInt(valor));
		}
	}

	public int getCodigoProjeto(String nome) throws NaoEncontradaException, ValidacaoException {
		try {
			Projeto p = this.recuperaProjeto(nome);
			return p.getCodigo();
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na obtencao de codigo de projeto: Projeto nao encontrado");
		}
	}

	public void addParticipacao(int codigoProjeto, Participacao participacao) throws NaoEncontradaException, ValidacaoException {
		Projeto p = this.recuperaProjeto(codigoProjeto);
		p.addicionaParticipacao(participacao);
	}
}