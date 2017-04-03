package myUtils;

import static myUtils.Validacao.*;

import java.io.Serializable;

import excecoes.ValidacaoException;

/**
 * Implementacao de um tipo periodo para usar dadas nossas necessidades
 * (Embora um pouco desnecessario, nao fica legal usando de string para representar este tipo).
 * 
 * @author Gabriel Fernandes
 */
public class Periodo implements Comparable<Periodo>, Serializable {
	private static final long serialVersionUID = 1L;
	private int ano;
	private int semestre;
	
	/**
	 * Recebe string em parametro ao ser instanciada,
	 * necessita de um formato de periodo valido.
	 * Formatos:aaaa.1
	 * 			aaaa.2
	 * 
	 * no qual, aaaa = um ano qualquer
	 * (sujeito a excecao dependendo do ano em questao).
	 * 
	 * @param periodo				string com o periodo
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public Periodo(String periodo) throws ValidacaoException {
		validaPeriodo(periodo);
		
		String[] nums = new String[periodo.length()];
		for (int i = 0; i < periodo.length(); i++) {
		    nums[i] = Character.toString(periodo.charAt(i));
		}
		
		int ano = Integer.parseInt(nums[0] + nums[1] + nums[2] + nums[3]);
		int semestre = Integer.parseInt(nums[5]);

		validaAno(ano);
		
		this.ano = ano;
		this.semestre = semestre;
	}
	
	/**
	 * Acessa ano referente ao periodo.
	 * 
	 * @return		inteiro do ano referente ao periodo
	 */
	public int getAno() {
		return this.ano;
	}
	
	/**
	 * Retorna 1 ou 2, dependendo do semestre do
	 * ano.
	 * 
	 * @return		inteiro, representando cada parte do ano(primeira ou segunda metade do ano).
	 */
	public int getSemestre() {
		return this.semestre;
	}

	/**
	 * Uso do ano + semestre para gerar os codigos hash.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + semestre;
		return result;
	}

	/**
	 * Uso do ano e semestre para definir se dois periodos sao iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Periodo other = (Periodo) obj;
		if (ano != other.ano)
			return false;
		if (semestre != other.semestre)
			return false;
		return true;
	}

	/**
	 * string no format:aaaa.1
	 * 					aaaa.2
	 */
	@Override
	public String toString() {
		return this.ano + "." + this.semestre;
	}

	/**
	 * Implementacao de metodo da interface
	 * comparable, para tornar possivel comparacoes entre
	 * objetos do tipo Periodo.
	 */
	@Override
	public int compareTo(Periodo periodo2) {
		if(this.ano > periodo2.getAno()) return 1;
		if(this.ano < periodo2.getAno()) return -1;
		if(this.semestre > periodo2.getSemestre()) return 1;
		if(this.semestre < periodo2.getSemestre()) return -1;
		return 0;
	}
}