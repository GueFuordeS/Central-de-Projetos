package projeto;

import static excecoes.Validacao.*;

import excecoes.ValidacaoException;

/**
 * 
 * Classe responsavel por criar uma forma geral para projetos, uma abstracao para suas subclasses
 * 
 * @author Gabriel Fernandes
 *
 */
public abstract class Projeto {
	String nome;
	String objetivo;
	Date dataInicio;
	int duracao;
	
	public Projeto(String nome, String objetivo, String date, int duracao) throws ValidacaoException {
		validaNomeProjeto(nome);
		validaObjetivo(objetivo);
		validaInt(duracao);
		
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = new Date(date);
		this.duracao = duracao;
	}

	public String getNome() {
		return nome;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public int getDuracao() {
		return duracao;
	}
}
