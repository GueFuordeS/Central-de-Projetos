package myUtils;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

public class Periodo implements Comparable<Periodo> {
	int ano;
	int semestre;
	
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
	
	public int getAno() {
		return this.ano;
	}
	
	public int getSemestre() {
		return this.semestre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + semestre;
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
		Periodo other = (Periodo) obj;
		if (ano != other.ano)
			return false;
		if (semestre != other.semestre)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.ano + "." + this.semestre;
	}

	@Override
	public int compareTo(Periodo periodo2) {
		if(this.ano > periodo2.getAno()) return 1;
		if(this.ano < periodo2.getAno()) return -1;
		if(this.semestre > periodo2.getSemestre()) return 1;
		if(this.semestre < periodo2.getSemestre()) return -1;
		return 0;
	}
}
