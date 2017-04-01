package myUtils;

import java.io.Serializable;

/**
 * Classe de utilidade que eh responsavel por gerir e gerar os codigos
 * necessarios para identificar os projetos
 * 
 * @author Gabriel Fernandes
 */
public class CodigoGerador implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int codigo;
	
	public CodigoGerador() {
		codigo = 0;
	}
	
	/**
	 * get do codigo, registra o numero de codigos ja gerado
	 * ate o dado momento
	 * 
	 * @return um inteiro do codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * gera um novo codigo imediatamente maior que o anterior gerado,
	 * sendo possivel assim um codigo unico para cada projeto novo criado
	 * 
	 * @return um inteiro que representa o codigo unico
	 */
	public int geraCodigo() {
		return ++codigo;
	}
}
