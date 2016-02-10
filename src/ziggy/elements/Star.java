package ziggy.elements;

import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe Star
 * Classe que representa uma estrela e extende GameElement.
 */

public class Star extends GameElement {

	/**
	 * Cria uma estrla na posição inicial pos
	 * @param pos - coordenada inicial da estrela
	 */
	
	public Star(Coord2D pos) {
		//Cria um objeto estrela em GameElement
		super(pos);
	}

}
