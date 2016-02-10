package ziggy.actions;

import ziggy.core.GameData;
import ziggy.elements.MovingElement;

/**
 * @author AndreFRocha
 * Classe ChangeDirection
 * Classe que muda direccao de um elemento, implementa Action.
 */

public class ChangeDirection implements Action {

	/**
	 * Elemento de jogo
	 */
	
	MovingElement ge;
	
	/**
	 * Direccao
	 */
	
	double direction;
	
	
	/**
	 * Cria objeto ChangeDirection
	 * @param element  - elemento de jogo
	 * @param direction - direccao
	 */
	
	public ChangeDirection(MovingElement element, double direction) {
		ge = element;
		this.direction = direction;
	}
	
	/**
	 * M�todo que executa a mudanca de direc��o alterando a representa��o
	 * do elem de jogo em causa.
	 * @param gd  - representa��o dos elems de jogo
	 */

	@Override
	public void execute(GameData gd) {
		ge.setDirection(direction);
	}

}
