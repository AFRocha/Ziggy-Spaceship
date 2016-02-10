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
	 * M�todo que elimina um elemento de jogo alterando a representa��o
	 * dos elems do jogo. 
	 * @param gd  - representa��o dos elems de jogo
	 */
	
	@Override
	public void execute(GameData gd) {
		gd.removeGameElement(ge);
	}
}
