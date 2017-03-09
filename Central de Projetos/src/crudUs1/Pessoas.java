package crudUs1;

public class Pessoas {
	private String cpf;
	private String nome;
	private String email;
	
	public Pessoas(String cpf, String nome, String email) throws Exception {
		if(cpf == null || cpf.trim().isEmpty()) {
			throw new Exception("O cpf não pode ser vazio.");
		}
		
		if(nome == null || nome.trim().isEmpty()) {
			throw new Exception("O nome não pode ser vazio.");
		}
		
		if(email == null || email.trim().isEmpty()) {
			throw new Exception("O email não pode ser vazio.");
		}
		
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
	}
	
	
}
