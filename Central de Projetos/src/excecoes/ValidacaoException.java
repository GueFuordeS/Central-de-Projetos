package excecoes;

public class ValidacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ValidacaoException() {
		super("Erro na validacao");
	}
	
	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
}