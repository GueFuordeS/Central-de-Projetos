package validacao;

public class Validacao {
	public static void validaString(String string) throws Exception {
		if(string == null || string.trim().isEmpty()) {
			throw new Exception("Erro no cadastro de pessoa.");
		}
	}
	
	public static void validaCpf(String cpf) throws Exception {
		if(cpf == null || cpf.trim().isEmpty()) {
			throw new Exception("Erro no cadastro de pessoa: CPF nulo ou vazio");
		}
		
		if(cpf.length() != 14) throw new Exception("Erro no cadastro de pessoa: CPF invalido");
		
		if((cpf.charAt(3) != '.') || (cpf.charAt(7) != '.') || (cpf.charAt(11) != '-')) 
			throw new Exception("Erro no cadastro de pessoa: CPF invalido");
			
		cpf.split(".");
		for(int i = 0; i < 3; i++) {
			if(!Character.isDigit(cpf.charAt(i))) {
				throw new Exception("Erro no cadastro de pessoa: CPF invalido");
			}
		}
	} 
} 
		

