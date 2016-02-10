package ziggy.elements;

import ziggy.actions.ActionQueue;
import ziggy.actions.KillElement;
import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe Ghost
 * Classe que representa um fantasma e extende GameElement.
 */

public class Ghost extends GameElement {
	
	/**
	 * Conta passos de jogo
	 */
	
	int contador=0;
	
	/**
	 * Cria um fantasma na posição inicial pos
	 * @param pos - coordenada inicial do fantasma
	 */
	
	public Ghost(Coord2D pos) {
		//Cria um objeto fantasma em GameElement
		super(pos);
	}
	
	/**
	 * Metodo step extendido da classe GameElement
	 * @param aq - Acção para alterar o estado do fantasma consoante 
	 * o numero de passos de jogo.
	 */

	@Override
	public void step(ActionQueue aq) {
		//Se numero de passos de jogo for 100
		if(contador == 100){
			
			//Fantasma desaparece
			
			KillElement kill = new KillElement (this);
			aq.add(kill);
			
		//Se numero de passos de jogo não for 100
			
		}else{
			
			//Incrementa um passo de jogo
			
			contador++;
		}
	}

}
