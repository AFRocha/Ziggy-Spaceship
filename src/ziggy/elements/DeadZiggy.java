package ziggy.elements;

import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe DeadZiggy
 * Classe que representa ziggy quando est� morto (extende GameElement).
 */

public class DeadZiggy extends GameElement  {

	/**
	 * Cria ziggymorto na posi��o pos
	 * @param pos - coordenada do ziggymorto
	 */

	public DeadZiggy(Coord2D pos) {
		//Cria um objeto ziggymorto em GameElement
		super (pos);
	}
}
