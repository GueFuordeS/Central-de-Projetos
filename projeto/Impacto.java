package projeto;

import excecoes.ValidacaoException;

/**
 * Interface util para permitir chamadas polimorficas entre as classes que possuem o atributo impacto
 * (Projetos de extensao e PET)
 * 
 * @author Gabriel Fernandes
 */
public interface Impacto {
	int getImpacto();
	
	void setImpacto(int impacto) throws ValidacaoException;
}