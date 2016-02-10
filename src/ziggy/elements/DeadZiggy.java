package ziggy.elements;

import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe DeadZiggy
 * Classe que representa ziggy quando está morto (extende GameElement).
 */

public class DeadZiggy extends GameElement  {

	/**
	 * Cria ziggymorto na posição pos
	 * @param pos - coordenada do ziggymorto
	 */

	public DeadZiggy(Coord2D pos) {
		//Cria um objeto ziggymorto em GameElement
		super (pos);
	}
}
