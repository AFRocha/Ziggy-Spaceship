package ziggy.elements;

import ziggy.actions.ActionQueue;
import ziggy.actions.KillElement;
import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe Explosion
 * Classe que representa uma explos�o e extende GameElement.
 */

public class Explosion extends GameElement {

	/**
	 * Conta passos de jogo
	 */

	int contador = 0;

	/**
	 * Cria uma epxlos�o na posi��o inicial pos
	 * @param position - coordenada inicial da explos�o
	 */

	public Explosion(Coord2D position) {

		//Cria um objeto explos�o em GameElement

		super(position);
	}

	/**
	 * Metodo step extendido da classe GameElement
	 * @param aq - Ac��o para alterar o estado da explos�o consoante 
	 * o numero de passos de jogo.
	 */

	@Override
	public void step(ActionQueue aq) {
		//Se numero de passos de jogo for 100
		if(contador == 10){

			//Explos�o desaparece

			KillElement kill = new KillElement (this);
			aq.add(kill);

		//Se numero de passos de jogo n�o for 100

		}else{

			//Incrementa um passo de jogo

			contador++;
		}
	}
}
