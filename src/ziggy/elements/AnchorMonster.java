package ziggy.elements;

import ziggy.actions.ActionQueue;
import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe AnchorMonster
 * Classe que representa AnchorMonsters, extende Monstro(que por sua vez
 * extende MovingElement, que por sua vez extende GameElement).
 */

public class AnchorMonster extends Monster  {
	
	/**
	 * Numero de passos que anchormonster pode dar
	 */
	
	int steps;
	
	/**
	 * Cria monstros anchormonster
	 * @param initial - posicao inicial do anchormonstert
	 * @param steps - quantos passos tem o anchormonster
	 * @param direction - direccao do anchormonster
	 */
	
	public AnchorMonster(Coord2D initial, int steps, double direction) {
		super(initial, 1, direction);
		this.steps = steps;
	}
	
	/**
	 * Metodo step extendido da classe MovingElement
	 * @param action - Acção para alterar o estado do anchormonster consoante
	 * o numero de passos de jogo.
	 */
	
	@Override
	public void step(ActionQueue action) {
		
		//Se n passos de anchormonster nao for 0, anchormonster dá mais um passo
		
		if(steps>0){
			
			//anchormonster tem menos um passo disponivel
			steps--;
			super.step(action);
		}
	}

}
