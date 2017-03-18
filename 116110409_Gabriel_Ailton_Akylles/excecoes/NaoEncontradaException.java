package excecoes;

public class NaoEncontradaException extends Exception {

	private static final long serialVersionUID = 1L;

	public NaoEncontradaException() {
		super("Erro na consulta de pessoa: Pessoa nao encontrada");
	}
	
	public NaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}
