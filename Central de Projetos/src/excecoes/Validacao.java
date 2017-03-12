package excecoes;

public class Validacao {
	
	public static void validaString(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro no cadastro de pessoa.");
		}
	}
	
	public static void validaEmail(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro no cadastro de pessoa: Email nulo ou vazio");
		}
		
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\."
        		+ "[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(string);
        if(!m.matches()) {
        	throw new ValidacaoException("Erro no cadastro de pessoa: Email invalido");
        }
	}
	
	public static void validaEmailUpdate(String string) throws ValidacaoException {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\."
        		+ "[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(string);
		
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro na atualizacao de pessoa: Email invalido");
		}
		else if(!m.matches()) {
			throw new ValidacaoException("Erro na atualizacao de pessoa: Email invalido");
		}
	}

	
	public static void validaNome(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro no cadastro de pessoa: Nome nulo ou vazio");
		}
	}
	
	public static void validaNomeUpdate(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro na atualizacao de pessoa: Nome nulo ou vazio");
		}
	}
	
	public static void validaObjetivo(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro: objetivo nao pode ser nulo ou vazio");
		}
	}
	
	public static void validaCpfUpdate(String cpf) throws ValidacaoException {
		if(cpf == null || cpf.trim().isEmpty()) {
			throw new ValidacaoException("Erro na atualizacao de pessoa: CPF nulo ou vazio");
		}
		
		String cpfForm = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d";
		
		if(!cpf.matches(cpfForm)) throw new ValidacaoException("Erro na atualizacao de pessoa: CPF invalido");
	} 
	
	public static void validaCpf(String cpf) throws ValidacaoException {
		if(cpf == null || cpf.trim().isEmpty()) {
			throw new ValidacaoException("Erro no cadastro de pessoa: CPF nulo ou vazio");
		}
		
		String cpfForm = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d";
		
		if(!cpf.matches(cpfForm)) throw new ValidacaoException("Erro no cadastro de pessoa: CPF invalido");
	} 
	
	public static void validaCpfGeneric(String cpf) throws ValidacaoException {
		if(cpf == null || cpf.trim().isEmpty()) {
			throw new ValidacaoException("CPF nulo ou vazio");
		}
		
		String cpfForm = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d";
		
		if(!cpf.matches(cpfForm)) throw new ValidacaoException("CPF invalido");
	} 
	
	public static void validaInt(int inteiro) throws ValidacaoException {
		if(inteiro <= 0) throw new ValidacaoException("Inteiro invalido para a operacao");
	}
	
	public static void validaData(int data) throws ValidacaoException {
		if(data <= 0) throw new ValidacaoException("Dia, mes ou ano nao podem ser 0");
	}
} 
		

