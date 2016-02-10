package ziggy.actions;

import ziggy.core.GameData;
import ziggy.elements.GameElement;

/**
 * @author AndreFRocha
 * Classe KillElement
 * Classe que elimina um elemento de jogo, implementa Action.
 */

public class KillElement implements Action {

	/**
	 * elemento de jogo
	 */

	GameElement ge;

	/**
	 * Cria objeto KillElement
	 * @param element - elemento de jogo
	 */
	
	public KillElement(GameElement element) {

		ge = element;

	}
	
	/**
	 * Método que elimina um elemento de jogo alterando a representação
	 * dos elems do jogo. 
	 * @param gd  - representação dos elems de jogo
	 */
	
	@Override
	public void execute(GameData gd) {
		gd.removeGameElement(ge);
	}
}
