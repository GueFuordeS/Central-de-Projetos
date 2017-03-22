package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public abstract class Participacao {

		private Pessoa pessoa;
		private Projeto projeto;
		private String dataInicio;
		private int duracaoEmMeses;
		private int qtdeHorasDedicadas;
		private double valorDaHora;
		
		public Participacao(Pessoa pessoa, Projeto projeto, String dataInicio, int duracaoEmMeses, double valorDaHora, int qtdeHorasDedicadas) {
			super();
			this.pessoa = pessoa;
			this.projeto = projeto;
			this.dataInicio = dataInicio;
			this.duracaoEmMeses = duracaoEmMeses;
			this.qtdeHorasDedicadas = qtdeHorasDedicadas;
			this.valorDaHora = valorDaHora;
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
			return dataInicio;
		}

		public void setDataInicio(String dataInicio) {
			this.dataInicio = dataInicio;
		}

		public int getDuracaoEmMeses() {
			return duracaoEmMeses;
		}

		public void setDuracaoEmMeses(int duracaoEmMeses) {
			this.duracaoEmMeses = duracaoEmMeses;
		}

		public int getQtdeHorasDedicadas() {
			return qtdeHorasDedicadas;
		}

		public void setQtdeHorasDedicadas(int qtdeHorasDedicadas) {
			this.qtdeHorasDedicadas = qtdeHorasDedicadas;
		}

		public double getValorDaHora() {
			return valorDaHora;
		}

		public void setValorDaHora(double valorDaHora) {
			this.valorDaHora = valorDaHora;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
			result = prime * result + duracaoEmMeses;
			result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
			result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
			result = prime * result + qtdeHorasDedicadas;
			long temp;
			temp = Double.doubleToLongBits(valorDaHora);
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
			if (duracaoEmMeses != other.duracaoEmMeses)
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
			if (qtdeHorasDedicadas != other.qtdeHorasDedicadas)
				return false;
			if (Double.doubleToLongBits(valorDaHora) != Double.doubleToLongBits(other.valorDaHora))
				return false;
			return true;
		}	
}
