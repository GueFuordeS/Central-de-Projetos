package myUtils;

/**
 * Classe de utilidade que eh responsavel por gerir e gerar os codigos
 * necessarios para identificar os projetos
 * 
 * @author Gabriel Fernandes
 */
public class Codigo {
	private static int codigo = 0;
	
	/**
	 * gera um novo codigo imediatamente maior que o anterior gerado,
	 * sendo possivel assim um codigo unico para cada projeto novo criado
	 * 
	 * @return um inteiro que representa o codigo unico
	 */
	public static int geraCodigo() {
		return codigo++;
	}
}
