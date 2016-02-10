package ziggy.elements;
import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe ChaseMonster
 * Classe que representa chasemonsters, extende Monstro(que por sua vez
 * extende MovingElement, que por sua vez extende GameElement) e implementa
 * Ziggyobserver.
 */

public class ChaseMonster extends Monster implements ZiggyObserver {
	
	/**
	 * Cria chasemonster na coordenada initialPos
	 * @param initialPos - Pos inicial do chasemonster
	 * @param speed - Velocidade do chasemonster
	 * @param ziggyPos - Pos do ziggy
	 */
	
	public ChaseMonster(Coord2D initialPos, double speed, Coord2D ziggyPos) {
		super(initialPos, speed, initialPos.directionTo(ziggyPos));

	}

	/**
	 * Metodo que actualiza a direcção de chasemonster
	 * consoante a posicao de Ziggy
	 * @param pos
	 */
	
	@Override
	public void onZiggyUpdate(Coord2D pos) {
		this.setDirection((this.getPosition()).directionTo(pos));
	}
}
