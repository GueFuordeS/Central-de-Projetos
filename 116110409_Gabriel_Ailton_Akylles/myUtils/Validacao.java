package myUtils;

import excecoes.ValidacaoException;

/**
 * 
 * Classe de utilidade, por isso a necessidade de uso de metodos estaticos
 * 
 * @author Gabriel Fernandes
 *
 */
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

	public static void validaNomeDisciplina(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Nome de disciplina nao pode ser nulo ou vazio");
		}
	}
	
	public static void validaNomeProjeto(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro no cadastro de projeto: Nome nulo ou vazio");
		}
	}
	
	public static void validaNomeUpdate(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro na atualizacao de pessoa: Nome nulo ou vazio");
		}
	}
	
	public static void validaObjetivo(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro no cadastro de projeto: Objetivo nulo ou vazio");
		}
	}
	
	public static void validaObjetivoUpdate(String string) throws ValidacaoException {
		if(string == null || string.trim().isEmpty()) {
			throw new ValidacaoException("Erro na atualizacao de projeto: Objetivo nulo ou vazio");
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
			throw new ValidacaoException("Erro no cadastro de pessoa: CPF nulo ou vazio");
		}
		
		String cpfForm = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d";
		
		if(!cpf.matches(cpfForm)) throw new ValidacaoException("Erro no cadastro de pessoa: CPF invalido");
	} 
	
	public static void validaInt(int inteiro) throws ValidacaoException {
		if(inteiro <= 0) throw new ValidacaoException("Inteiro invalido para a operacao");
	}
	
	public static void validaValorHora(double valorHora) throws ValidacaoException {
		if(valorHora <= 0) throw new ValidacaoException("Erro na associacao de pessoa a projeto: Valor da hora invalido");
	}
	
	public static void validaQntHoras(int valorHora) throws ValidacaoException {
		if(valorHora <= 0) throw new ValidacaoException("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
	}
	
	public static void validaDuracao(int inteiro) throws ValidacaoException {
		if(inteiro <= 0) throw new ValidacaoException("Erro no cadastro de projeto: Duracao invalida");
	}
	
	public static void validaDia(int dia) throws ValidacaoException {
		if(dia < 1 || 31 < dia) throw new ValidacaoException("Erro: Formato de data invalido");
	}

	public static void validaMes(int mes) throws ValidacaoException {
		if(mes < 1 || 12 < mes) throw new ValidacaoException("Erro: Formato de data invalido");
	}

	public static void validaAno(int ano) throws ValidacaoException {
		if(ano < 1899 || 2299 < ano) throw new ValidacaoException("Erro: Formato de data invalido");
	}

	public static void validaImpacto(int impacto) throws ValidacaoException {
		if(impacto < 1 || 6 < impacto) {
			throw new ValidacaoException("Impacto social precisa ser entre 1 e 6");
		}
	}
	
	public static void validaPeriodo(String periodo) throws ValidacaoException {
		String periodoForm = "\\d\\d\\d\\d"+"."+"1";
		String periodoForm2 = "\\d\\d\\d\\d"+"."+"2";
		if(!periodo.matches(periodoForm) && !periodo.matches(periodoForm2)) {
			throw new ValidacaoException("Formato de periodo invalido");
		}
	}

	public static void validaRendimento(int rendimento) throws ValidacaoException {
		if(rendimento < 0 || 100 < rendimento) {
			throw new ValidacaoException("Rendimento precisa estar entre 0 e 100");
		}
	}
	
	public static void validaCategoria(String categoria) throws ValidacaoException {
		if(categoria == null || categoria.trim().isEmpty()) {
			throw new ValidacaoException("Categoria nao pode ser nula ou vazia");
		}
		else if(!categoria.equals("PIBIC") && !categoria.equals("PIBITI") && !categoria.equals("PIVIC")
				&& !categoria.equals("COOP")) {
			throw new ValidacaoException("Erro no cadastro de projeto: Categoria invalida");
		}
	}

	public static void validaProdAcademica(int produtividade) throws ValidacaoException {
		if(produtividade < 0) {
			throw new ValidacaoException("Erro no cadastro de projeto: Numero de producoes academicas invalido");
		}
	}
	
	public static void validaProdTecnica(int produtividade) throws ValidacaoException {
		if(produtividade < 0) {
			throw new ValidacaoException("Erro no cadastro de projeto: Numero de producoes tecnicas invalido");
		}
	}
	
	public static void validaPatentes(int produtividade) throws ValidacaoException {
		if(produtividade < 0) {
			throw new ValidacaoException("Erro no cadastro de projeto: Numero de patentes invalido");
		}
	}
	
} 