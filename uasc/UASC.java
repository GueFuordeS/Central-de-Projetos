package uasc;

public class UASC {
	private double receita;
	
	public UASC() {
		
	}
	
	public double getReceita() {
		return this.receita;
	}
	
	public void diminuiReceita(double valor) {
		this.receita -= valor;
	}
}