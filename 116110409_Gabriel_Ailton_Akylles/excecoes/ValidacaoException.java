package excecoes;

/**
 * Subclasse de exception criada para uma clareza maior de codigo,
 * dada as nossas necessidades em nosso sistema
 * 
 * @author Gabriel Fernandes
 */
public class ValidacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ValidacaoException() {
		super("Erro na validacao");
	}
	
	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
}