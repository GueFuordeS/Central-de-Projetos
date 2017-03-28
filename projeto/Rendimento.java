package projeto;

import excecoes.ValidacaoException;

/**
 * Interface util para permitir chamadas polimorficas entre as classes que possuem o atributo impacto
 * (Projetos de monitoria e PET)
 * 
 * @author Gabriel Fernandes
 */
public interface Rendimento {
	int getRendimento();
	
	void setRendimento(int rendimento) throws ValidacaoException;
}
