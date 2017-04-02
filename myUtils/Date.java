package myUtils;

import static myUtils.Validacao.*;

import java.io.Serializable;

import excecoes.ValidacaoException;

/**
 * 
 * Classe implementada para representar um tipo de dado, no caso uma data para as necessidades do projeto
 * para manter um maior controle sobre esse tipo preferiu-se implementa-lo do que reusar de uma classe ja disponivel.
 * 
 * @author Gabriel Fernandes
 */
public class Date implements Comparable<Date>, Serializable {
	private static final long serialVersionUID = 1L;
	int dia;
	int mes;
	int ano;
	
	/**
	 * Construtor da classe.
	 * 
	 * @param date recebe a data
	 * @throws ValidacaoException caso o formato ou a data em si seja invalida
	 */
	public Date(String date) throws ValidacaoException {
		String dateForm = "\\d\\d/\\d\\d/\\d\\d\\d\\d";
		if(!date.matches(dateForm)) throw new ValidacaoException("Erro: Formato de data invalido");
		
		String[] nums = new String[date.length()];
		for (int i = 0; i < date.length(); i++) {
		    nums[i] = Character.toString(date.charAt(i));
		}
		
		int dia = Integer.parseInt(nums[0] + nums[1]);
		int mes = Integer.parseInt(nums[3] + nums[4]);
		int ano = Integer.parseInt(nums[6] + nums[7] + nums[8] + nums[9]);

		validaDia(dia);
		validaMes(mes);
		validaAno(ano);
		
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}
	
	/**
	 * Acesso ao atributo dia de uma
	 * data.
	 * 
	 * @return		dia da data
	 */
	public int getDia() {
		return this.dia;
	}
	
	/**
	 * Acesso ao atributo mes de uma
	 * data.
	 * 
	 * @return		mes da data
	 */
	public int getMes() {
		return this.mes;
	}
	
	/**
	 * Acesso ao atributo ano de uma
	 * data.
	 * 
	 * @return		ano da data
	 */
	public int getAno() {
		return this.ano;
	}

	/**
	 * Incrementa uma data ja existente,
	 * atualizando a data dada a quantidade de meses passados
	 * como argumento.
	 * 
	 * @param meses					meses a adicionar na data
	 * @throws ValidacaoException	caso quantidades de meses seja menor ou igual a zero
	 */
	public void addMeses(int meses) throws ValidacaoException {
		try {
			validaDuracao(meses);
		}
		catch(ValidacaoException e) {
			throw new ValidacaoException("Quantidade de meses nao pode ser menor ou igual a zero");
		}
		
		this.ano += meses/12;
		this.mes += meses%12;
	}

	/**
	 * Gera uma nova data, a partir de uma ja existente
	 * e de uma quantidade de meses, retorna nova data,
	 * nao altera a atual
	 * (utilizado principalmente para gerar datas de terminos a partir
	 * de um periodo de tempo).
	 * 
	 * @param duracao				duracao a incrementar na novaData
	 * @return						nova data
	 * @throws ValidacaoException	em caso de duracao for menor ou igual a zero
	 */
	public Date geraDataTermino(int duracao) throws ValidacaoException {
		Date novaData = new Date(this.toString());
		novaData.addMeses(duracao);
		return novaData;
	}
	
	/**
	 * Sobrescreve hashCode.
	 * Utiliza-se dia, mes e ano para geracao do codigo hash.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + dia;
		result = prime * result + mes;
		return result;
	}

	/**
	 * Sobrescreve equals.
	 * Caso necessite comparacao entre duas instancias de
	 * Date, para saber se sao a mesma data, verifica-se
	 * se possuem dia, mes e ano semlhantes, se sim, sao a mesma data.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (ano != other.ano)
			return false;
		if (dia != other.dia)
			return false;
		if (mes != other.mes)
			return false;
		return true;
	}

	/**
	 * Sobrescreve toString
	 * (Garante um 0 antes de dia ou mes, se este for menor que 10).
	 * Formato de data: "dd/mm/aaaa".
	 */
	@Override
	public String toString() {
		if(dia<10 && mes<10) return "0" + dia + "/" + "0" + mes + "/" + ano;
		if(dia<10) return "0" + dia + "/" + mes + "/" + ano;
		if(mes<10) return dia + "/" + "0" + mes + "/" + ano;
		return dia + "/" + mes + "/" + ano;
	}
	
	/**
	 * Sobrescreve toString.
	 * Formato de data: "aaaa-mm-dd"
	 * (disso vem o "AMD").
	 * 
	 */
	public String toStringAMD() { //no formato aaaa-mm-dd
		if(dia<10 && mes<10) return ano + "-" + "0" + mes + "-" + "0" + dia;
		if(dia<10) return ano + "-" + mes + "-" + "0" + dia;
		if(mes<10) return ano + "-" + "0" + mes + "-" + dia;
		return ano + "-" + mes + "-" + dia;
	}

	/**
	 * Implementacao de metodo da interface
	 * comparable, para tornar possivel comparacoes entre
	 * objetos do tipo Date.
	 */
	@Override
	public int compareTo(Date date) {
		if(this.ano > date.getAno()) return 1;
		if(this.ano < date.getAno()) return -1;
		if(this.mes > date.getMes()) return 1;
		if(this.mes < date.getMes()) return -1;
		if(this.dia > date.getDia()) return 1;
		if(this.dia < date.getDia()) return -1;
		return 0;
	}
}