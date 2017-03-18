package excecoes;

public class Validacao {
	
	public static void validaString(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro no cadastro de pessoa.");
		}
	}
	
	public static void validaCpf(String cpf) throws ValidacaoException {
		if(cpf == null || cpf.trim().isEmpty()) {
			throw new ValidacaoException("Erro no cadastro de pessoa: CPF nulo ou vazio");
		}
		
		String cpfForm = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d";
		
		if(!cpf.matches(cpfForm)) throw new ValidacaoException("Erro no cadastro de pessoa: CPF nulo ou vazio");
	} 
} 
		

