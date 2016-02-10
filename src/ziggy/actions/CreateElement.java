package ziggy.actions;

import ziggy.core.GameData;
import ziggy.elements.GameElement;

/**
 * @author AndreFRocha
 * Classe CreateElement
 * Classe que cria um elemento, implementa Action.
 */

public class CreateElement implements Action {

	/**
	 * Elemento de jogo
	 */
	GameElement ge;

	/**
	 * Cria objeto CreateElement
	 * @param element - Elemento de jogo a criar
	 */

	public CreateElement(GameElement element) {
		ge = element;
	}

	/**
	 * M�todo que cria um elemento de jogo alterando a representa��o
	 * dos elems do jogo. 
	 * @param gd  - representa��o dos elems de jogo
	 */

	@Override
	public void execute(GameData gd) {
		gd.addGameElement(ge);

	}
}
