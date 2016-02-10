package ziggy.elements;

import ziggy.actions.ActionQueue;
import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe Monster
 * Classe que representa todos os Monstros e extende
 * MovingElement(que por sua vez extende GameElement).
 */

public abstract class Monster extends MovingElement {

	/**
	 * Cria monstros numa determinada posicao, velocidade e direc��o iniciais
	 * @param pos - Posic�o inicial do monstro
	 * @param initialSpeed - Velocidade inicial do monstro
	 * @param initialDirection - Direccao inicial do monstro
	 */
	
	public Monster(Coord2D pos, double initialSpeed, double initialDirection) {

		//Cria um objeto monstro em GameElement com velocidade e direc��es
		//definidas em movingelement

		super(pos,initialSpeed,initialDirection);
	}

	/**
	 * Metodo collideWith extendido da classe MovingElement
	 * @param other - Elemento de jogo que o monstro chocou
	 * @param aq - Ac��o para alterar o estado do monstro consoante 
	 * com quem colidiu.
	 */

	@Override
	public void collideWith(GameElement other, ActionQueue aq) {
		
		//Se o monstro chocou com uma estrela, fantasma, explos�o ou outro
		//monstro
		
		if (other instanceof Star || other instanceof Ghost ||
				other instanceof Explosion || other instanceof Monster)
			
			//Monstro volta para tr�s
			
			this.setDirection(this.getDirection()-180);
	}
}
