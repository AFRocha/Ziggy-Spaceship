package ziggy.elements;

import ziggy.actions.ActionQueue;
import ziggy.actions.CreateElement;
import ziggy.actions.KillElement;
import ziggy.actions.PlaySound;
import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe Bullet
 * Classe que representa uma bala e extende MovingElement(que por sua vez
 * extende GameElement).
 */

public class Bullet extends MovingElement {

	/**
	 * Contador de passos de jogo
	 */

	int contador=0;

	/**
	 * Cria bala numa determinada posicao e direcção iniciais
	 * @param initialPos - Posicao inicial da bala
	 * @param direction - Direction inicial da bala
	 */

	public Bullet(Coord2D initialPos, double direction) {
		super (initialPos, 10,direction);	
	}

	/**
	 * Metodo step extendido da classe MovingElement
	 * @param aq - Acção para alterar o estado da bala consoante
	 * o numero de passos de jogo.
	 */

	@Override
	public void step(ActionQueue aq) {

		//Se passos de jogo for 50

		if(contador == 50){

			//Bala desaparece

			KillElement kill = new KillElement (this);
			aq.add(kill);

			//Adicionar explosão no local onde bala desapareceu

			Coord2D explosionCoord = this.getPosition();
			Explosion explosion = new Explosion (explosionCoord);
			CreateElement explosionElement = new CreateElement (explosion);
			aq.add(explosionElement);

			//Tocar musica do funeral da bala

			PlaySound som = new PlaySound ("Bullet-Dies.wav");
			aq.add(som);

		//Se passos de jogo não for 50

		}else{

			//Incrementa n de passos de jogo
			contador++;

			super.step(aq);
		}
	}

	/**
	 * Metodo collideWith extendido da classe MovingElement
	 * @param other - Elemento de jogo que a bala chocou
	 * @param aq - Acção para alterar o estado da bala consoante 
	 * com quem colidiu
	 */

	@Override
	public void collideWith(GameElement other, ActionQueue aq) {

		//Se a bala colidiu com uma estrela ou um fantasma

		if (other instanceof Star || other instanceof Ghost){

			//Bala volta para trás

			this.setDirection(this.getDirection()-180);
		}

		//Se a bala colidiu com um monstro

		if (other instanceof Monster){

			//Monstro desaparece

			KillElement kill = new KillElement (other);
			aq.add(kill);

			//Bala desaparece

			KillElement kill2 = new KillElement (this);
			aq.add(kill2);

			//Adiciona explosão no local onde bala desapareceu

			Coord2D explosionCoord = this.getPosition();
			Explosion explosion = new Explosion (explosionCoord);
			CreateElement explosionElement = new CreateElement (explosion);
			aq.add(explosionElement);

			//Adiciona fantasma no local onde monstro desapareceu

			Ghost ghost = new Ghost (other.getPosition());
			CreateElement ghostElement = new CreateElement (ghost);
			aq.add(ghostElement);

			//Toca musica de funeral de monstro

			PlaySound som = new PlaySound ("Monster-Dies.wav");
			aq.add(som);
		}
	}
}