package myUtils;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

/**
 * 
 * Classe implementada para representar um tipo de dado, no caso uma data para as necessidades do projeto
 * para manter um maior controle sobre esse tipo preferiu-se implementa-lo do que reusar de uma classe ja disponivel
 * 
 * @author Gabriel Fernandes
 *
 */
public class Date implements Comparable<Date> {
	int dia;
	int mes;
	int ano;
	
	/**
	 * Construtor da classe
	 * 
	 * @param date recebe a data
	 * @throws ValidacaoException caso o formato ou a data em si seja invalida
	 */
	public Date(String date) throws ValidacaoException {
		String dateForm = "\\d\\d/\\d\\d/\\d\\d\\d\\d";
		if(!date.matches(dateForm)) throw new ValidacaoException("Formato de data invalido");
		
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
	
	public int getDia() {
		return this.dia;
	}
	
	public int getMes() {
		return this.mes;
	}
	
	public int getAno() {
		return this.ano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + dia;
		result = prime * result + mes;
		return result;
	}

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

	@Override
	public String toString() {
		final String FIM_DE_LINHA = System.lineSeparator();
		return "Dia:" + " " + dia + FIM_DE_LINHA + "Mes:" + " " + mes + FIM_DE_LINHA + "Ano:" + " " + ano + FIM_DE_LINHA;
	}

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