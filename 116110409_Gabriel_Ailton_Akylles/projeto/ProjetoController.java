package projeto;

import java.util.HashSet;

public class ProjetoController {
	private HashSet<Projeto> projetos;
	
	public ProjetoController() {
		this.projetos = new HashSet<>();
	}
	
	public void cadastraProjeto() {
		this.projetos.add(null);
	}
}
