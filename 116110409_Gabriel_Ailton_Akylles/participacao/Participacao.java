package participacao;

import excecoes.ValidacaoException;
import myUtils.Date;
import static myUtils.Validacao.*;
import pessoa.Pessoa;
import projeto.Projeto;

public abstract class Participacao implements Comparable<Participacao> {

		private Pessoa pessoa;
		private Projeto projeto;
		private Date dataInicio;
		private int duracao;
		private int qntHoras;
		private double valorHora;
		
		public Participacao(Pessoa pessoa, Projeto projeto, String dataInicio, 
				int duracaoEmMeses, double valorDaHora, int qtdeHorasDedicadas) throws ValidacaoException {
			
			validaDuracao(duracaoEmMeses);
			validaQntHoras(qtdeHorasDedicadas);
			
			this.pessoa = pessoa;
			this.projeto = projeto;
			this.dataInicio = new Date(dataInicio);
			this.duracao = duracaoEmMeses;
			this.qntHoras = qtdeHorasDedicadas;
			this.valorHora = valorDaHora;
		}
		
		public Pessoa getPessoa() {
			return pessoa;
		}

		public void setPessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
		}

		public Projeto getProjeto() {
			return projeto;
		}

		public void setProjeto(Projeto projeto) {
			this.projeto = projeto;
		}

		public String getDataInicio() {
			return dataInicio.toString();
		}

		public void setDataInicio(String dataInicio) throws ValidacaoException {
			this.dataInicio = new Date(dataInicio);
		}

		public int getDuracaoEmMeses() {
			return duracao;
		}

		public void setDuracaoEmMeses(int duracaoEmMeses) throws ValidacaoException {
			validaDuracao(duracaoEmMeses);
			this.duracao = duracaoEmMeses;
		}

		public int getQtdeHorasDedicadas() {
			return qntHoras;
		}

		public void setQtdeHorasDedicadas(int qtdeHorasDedicadas) throws ValidacaoException {
			validaQntHoras(qtdeHorasDedicadas);
			this.qntHoras = qtdeHorasDedicadas;
		}

		public double getValorDaHora() {
			return valorHora;
		}

		public void setValorDaHora(double valorDaHora) throws ValidacaoException {
			validaValorHora(valorDaHora);
			this.valorHora = valorDaHora;
		}

		public int compareTo(Participacao part) {
			return this.getPessoa().getNome().compareTo(part.getPessoa().getNome());
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
			result = prime * result + duracao;
			result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
			result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
			result = prime * result + qntHoras;
			long temp;
			temp = Double.doubleToLongBits(valorHora);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			Participacao other = (Participacao) obj;
			if (dataInicio == null) {
				if (other.dataInicio != null)
					return false;
			} else if (!dataInicio.equals(other.dataInicio))
				return false;
			if (duracao != other.duracao)
				return false;
			if (pessoa == null) {
				if (other.pessoa != null)
					return false;
			} else if (!pessoa.equals(other.pessoa))
				return false;
			if (projeto == null) {
				if (other.projeto != null)
					return false;
			} else if (!projeto.equals(other.projeto))
				return false;
			if (qntHoras != other.qntHoras)
				return false;
			if (Double.doubleToLongBits(valorHora) != Double.doubleToLongBits(other.valorHora))
				return false;
			return true;
		}	
}
