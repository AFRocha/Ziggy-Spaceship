package ziggy.elements;

import ziggy.actions.ActionQueue;
import ziggy.actions.CreateElement;
import ziggy.actions.KillElement;
import ziggy.core.RNG;
import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe ChaseMonster
 * Classe que representa BrownianMonsters, extende Monstro(que por sua vez
 * extende MovingElement, que por sua vez extende GameElement).
 */

public class BrownianMonster extends Monster {
	
	/**
	 * Conta passos de jogo para engravidar monstro.
	 */
	int passos = 0;
	
	/**
	 * Conta passos de jogo para mudar de direccao e velocidade
	 * de monstro.
	 */
	
	int contador = 0;

	/**
	 * Cria BrownianMonster em determinada posicao
	 * @param initialPos - Posicao inicial da bala
	 */
	
	public BrownianMonster(Coord2D initialPos) {
		super(initialPos, Speed(), Direction());
	}
	
	/**
	 * Método que retorna numero aleatorio entre 0 e 10
	 * @return double - velocidade aleatoria
	 */
	public static double Speed(){
		return RNG.random(0,10);
	}
	
	/**
	 * Método que retorna numero aleatorio entre 0 e 360
	 * @return double - direccao aleatoria
	 */
	
	public static double Direction(){
		return RNG.random(0,360);
	}
	
	/**
	 * Metodo step extendido da classe MovingElement
	 * @param action - Acção para alterar o estado do brownianmonster consoante
	 * o numero de passos de jogo.
	 */
	
	@Override
	public void step(ActionQueue action) {
		
		//Se numero passos de jogo for 10
		
		if(contador == 10){
			contador = 0;
			
			//Renovar velocidade e direcção do monstro aleatoriamente.
			
			this.setSpeed(Speed());
			this.setDirection(Direction());
		}else{
			contador++;
		}
		
		//Se numero passos de jogo for 150
		
		if(passos == 150){
			passos++;
			
			//Monstro engravida
			
			this.changeIcon("BrownianMonster-pregnant.png");
		}else{
			
			//Se numero passos de jogo for 300
			
			if(passos == 300){
				passos=0;
				
				//Monstro desparece
				
				KillElement kill = new KillElement (this);
				action.add(kill);
				Coord2D brownianMonsterCoord = this.getPosition();
				for(int i = 0; i<=3; i++){
					
					//Sao adicionados 4 novos brownian monster na pos onde o
					//"pai" brownianmonster desapareceu
					
					BrownianMonster brownianMonster = new BrownianMonster(brownianMonsterCoord);
					CreateElement brownianMonsterElement = new CreateElement (brownianMonster);
					action.add(brownianMonsterElement);}
			}else{passos++;}
		}
		super.step(action);
	}
	
}


