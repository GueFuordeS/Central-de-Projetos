package projeto;

import java.util.HashMap;

public class Despesa {
	private int valor;
	private HashMap<Categoria,Integer> categoria;

	public Despesa() {
		categoria = new HashMap<>();
	}
}
