package ziggy.elements;

import ziggy.actions.ActionQueue;
import ziggy.actions.CreateElement;
import ziggy.actions.KillElement;
import ziggy.actions.PlaySound;
import ziggy.core.Command;
import ziggy.util.Coord2D;

/**
 * @author AndreFRocha
 * Classe Ziggy
 * Classe que representa o Ziggy e extende MovingElement(que por sua vez
 * extende GameElement).
 */

public class Ziggy extends MovingElement {

	/**
	 * Cria Ziggy na coordenada initialPos
	 * @param initialPos - Pos inicial do Ziggy
	 */
	
	public Ziggy(Coord2D initialPos) {
		super(initialPos, 0, 0);
	}

	/**
	 * Metodo que trata operações sobre a nave consoante o comando
	 * @param c - Comando para acelarar, travar, ou virar a nave
	 * @param aq - Acção para alterar o estado interno do elemento de jogo
	 */
	
	public void handleCommand(Command c, ActionQueue aq) {
		switch(c) {
		
		//Se o comando for acelarar
		
		case ACCELARATE:
			
			//Se a nave estiver parada, fazer barulho ao arrancar
			
			if(getSpeed()==0){
				PlaySound som = new PlaySound ("Spaceship-Starts-Moving.wav");
				aq.add(som);}
			
			//Se nave inferior a velocidade 10, aumentar velocidade
			
			if(getSpeed() < 10)
				setSpeed(getSpeed()+1);
			break;
		
		//Se o comando for travar
			
		case BRAKE:
			
			//Se nave estiver em andamento, diminuir velocidade
			
			if(getSpeed() > 0)
				setSpeed(getSpeed()-1);
			break;
			
		//Se o comando for virar a esquerda
			
		case TURN_LEFT:
			
			//Mudar direcção 10 graus a esquerda
			
			setDirection(getDirection()-10);
			break;
			
		//Se o comando for virar a direita
			
		case TURN_RIGHT:
			
			//Mudar direcçao 10 grausa direita
			
			setDirection(getDirection()+10);
			break; 
			
		//Se o comando for disparar
		case FIRE:	
			
			//Criar uma bala na pos e direcção do Ziggy
			
			MovingElement bulletElement = new Bullet (getPosition(),getDirection());
			CreateElement bullet = new CreateElement(bulletElement);
			aq.add(bullet);
			
			//Tocar som correspondente ao lançamento de tiro
			
			PlaySound som = new PlaySound ("Bullet-Fired.wav");
			aq.add(som);
			break;
		}
	}
	
	/**
	 * Metodo collideWith extendido da classe MovingElement
	 * @param other - Elemento de jogo que ziggy chocou
	 * @param aq - Acção para alterar o estado de ziggy consoante 
	 * com quem colidiu
	 */
	
	@Override
	public void collideWith(GameElement other, ActionQueue aq) {

		//Se ziggy colidiu com estrela
		
		if (other instanceof Star){
			
			//Estrela desaparece, diminuindo o n estrelas em jogo
			
			KillElement kill = new KillElement (other);
			aq.add(kill);
			
			//Toca som de estrela recolhida
			
			PlaySound som = new PlaySound ("Star-Dies.wav");
			aq.add(som);
		}

		//Se ziggy colidiu com um Monstro
		
		if (other instanceof Monster){
			
			//Eliminar ziggy do jogo
			
			KillElement kill = new KillElement (this);
			aq.add(kill);
			
			//Adicionar ziggyMorto no local onde ziggy morreu
			
			Coord2D deadZiggyCoord = this.getPosition();
			DeadZiggy deadZiggy = new DeadZiggy (deadZiggyCoord);
			CreateElement deadZigg = new CreateElement (deadZiggy);
			aq.add(deadZigg);
			
			//Adicionar explosão no local onde ziggy morreu ( = pos ziggymorto)
			
			Explosion ziggyExplosion = new Explosion (deadZiggyCoord);
			CreateElement ziggyExplosionElement = new CreateElement (ziggyExplosion);
			aq.add(ziggyExplosionElement);
			
			//Toca a musica do funeral de Ziggy
			
			PlaySound som = new PlaySound ("Ziggy-Dies.wav");
			aq.add(som);
		}

		//Se ziggy colidiu com uma explosão ou um fantasma
		
		if (other instanceof Explosion || other instanceof Ghost){
			
			//Ziggy volta para trás
			
			this.setDirection(this.getDirection()-180);
		}

	}




}
