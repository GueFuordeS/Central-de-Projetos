package excecoes;

import java.io.IOException;

/**
 * Subclasse de IOException criada para uma clareza maior de codigo,
 * dada as nossas necessidades em nosso sistema
 * ES significa Entrada e Saida, para ser usada em possiveis excecoes ao
 * usar arquivos
 * 
 * @author Gabriel Fernandes
 */
public class ESException extends IOException {
	private static final long serialVersionUID = 1L;
	
	public ESException() {
		super("Erro ao estabelecer conexao com o arquivo");
	}
	
	public ESException(String mensagem) {
		super(mensagem);
	}
}