package ziggy.elements;

import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe BombMonster
 * Classe que representa BombMonsters, extende Monstro(que por sua vez
 * extende MovingElement, que por sua vez extende GameElement).
 */

public class BombMonster extends Monster{

	/**
	 * Cria monstros BombMonsters
	 * @param pos - POsicao inicial do monstro
	 * @param speed - Velocidade inicial do monstro
	 * @param direction - Direccao inicial do monstro
	 */
	public BombMonster(Coord2D pos, double speed, double direction) {
		super(pos, speed, direction);
	}
	
}
