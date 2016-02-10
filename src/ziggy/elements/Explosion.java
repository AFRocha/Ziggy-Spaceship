package ziggy.elements;

import ziggy.actions.ActionQueue;
import ziggy.actions.KillElement;
import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe Explosion
 * Classe que representa uma explosão e extende GameElement.
 */

public class Explosion extends GameElement {

	/**
	 * Conta passos de jogo
	 */

	int contador = 0;

	/**
	 * Cria uma epxlosão na posição inicial pos
	 * @param position - coordenada inicial da explosão
	 */

	public Explosion(Coord2D position) {

		//Cria um objeto explosão em GameElement

		super(position);
	}

	/**
	 * Metodo step extendido da classe GameElement
	 * @param aq - Acção para alterar o estado da explosão consoante 
	 * o numero de passos de jogo.
	 */

	@Override
	public void step(ActionQueue aq) {
		//Se numero de passos de jogo for 100
		if(contador == 10){

			//Explosão desaparece

			KillElement kill = new KillElement (this);
			aq.add(kill);

		//Se numero de passos de jogo não for 100

		}else{

			//Incrementa um passo de jogo

			contador++;
		}
	}
}
