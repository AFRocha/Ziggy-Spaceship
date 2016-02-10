package ziggy.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ziggy.core.GameData;
import ziggy.elements.BombMonster;
import ziggy.elements.BrownianMonster;
import ziggy.elements.ChaseMonster;
import ziggy.elements.GameElement;
import ziggy.elements.Star;
import ziggy.elements.AnchorMonster;

/**
 * @author AndreFRocha
 * Classe GameLevelReader
 * Esta classe tem o objetivo de ler o ficheiro de configura��o
 * de nivel, confirmar se est� bem configurado, e criar os elems
 * no jogo consoante a configura��o.
 */

public class GameLevelReader {

	/**
	 * @param s - ficheiro S
	 * @return Coord2D - coordenada lida do ficheiro
	 */
	
	private static Coord2D readCoord(Scanner s) {
		return new Coord2D(s.nextDouble(), s.nextDouble());
	}

	/**
	 * @param level - nivel de jogo
	 * @return GameData - devolve a representa�ao dos elems de jogo
	 * Este me'todo l� o ficheiro de configura��o de n�vel
	 * e cria os elementos no jogo em fun�ao da sua da sua pos inicial,
	 * assim como os passos para alguns elementos e/ou velocidade
	 * Lan�a excep�ao illegalconfiguration exception se ficheiro estiver
	 * mal configurado.
	 */

	public static GameData readGameLevel(int level) {
		try {
			
			//abrir o ficheiro de texto correspondente a nivel 'level'
			
			Scanner s = new Scanner(new File("levels/level_"+level+".txt"));
			try {
				s.useDelimiter(s.delimiter()+"(#.*\n)?");
				
				//Pos inicial da nave espacial Ziggy
				
				GameData data = new GameData(readCoord(s));
				
				//Enquanto existir linhas no ficheiro de configura��o
				
				while (s.hasNext()) {
					String elemType = s.next();
					switch (elemType){
					
					//Se elem for uma estrela, criar estrela na pos starCoord
					
					case "Star":
						Coord2D starCoord = new Coord2D (s.nextDouble(), s.nextDouble());
						GameElement star = new Star (starCoord);
						data.addGameElement(star);
						break;
						
					//Se elem for AnchorMonster cria monstro AnchorMonster
					// na pos anchorMonsterCoord, com n passos anchorMonsterSteps
					//e com direc��o inicial anchorMonsterDirection.
						
					case "AnchorMonster":
						Coord2D anchorMonsterCoord = new Coord2D (s.nextDouble(), s.nextDouble());
						int anchorMonsterSteps = s.nextInt();
						double anchorMonsterDirection = s.nextDouble();
						AnchorMonster anchorMonster = new AnchorMonster (anchorMonsterCoord,
								anchorMonsterSteps, anchorMonsterDirection);
						data.addGameElement(anchorMonster);
						break;
					
					//Se elem for BombMonster, cria monstro BombMonster 
					//na pos inicial bombMonsterCoord, com velocidade bombMonsterSpeed
					// e com direcao bombMonsterDIrection
						
					case "BombMonster":
						Coord2D bombMonsterCoord = new Coord2D (s.nextDouble(), s.nextDouble());
						double bombMonsterSpeed = s.nextDouble();
						double bombMonsterDirection = s.nextDouble();
						BombMonster bombMonster = new BombMonster (bombMonsterCoord,
								bombMonsterSpeed, bombMonsterDirection);
						data.addGameElement(bombMonster);
						break;	
						
					//Se elem for ChaseMonster, cria monstro ChaseMonster
					// na pos chaseMonsterCoord, com velocidade chaseMonsterSpeed
						
					case "ChaseMonster":
						Coord2D chaseMonsterCoord = new Coord2D (s.nextDouble(), s.nextDouble());
						double chaseMonsterSpeed = s.nextDouble();
						ChaseMonster chaseMonster = new ChaseMonster (chaseMonsterCoord,
								chaseMonsterSpeed,data.getZiggy().getPosition());
						data.addGameElement(chaseMonster);
						break;
						
					//Caso o elem for BrownianMonster, cria monstro Brownian
					// na pos inicial brownianMonsterCoord ( a velocidade e direc��o
					//ser�o aleatorios e configuradas em tempo de cria��o do objeto)
						
					case "BrownianMonster":
						Coord2D brownianMonsterCoord = new Coord2D (s.nextDouble(), s.nextDouble());
						BrownianMonster brownianMonster = new BrownianMonster (brownianMonsterCoord);
						data.addGameElement(brownianMonster);
						break;
					
					//Lan�amento da excep��o caso o ficheiro estiver mal configurado
					//ou configurado incorrectamente perante as regras de ocnfigura��o de nivel
						
					default:
						throw new IllegalConfigurationException("Configuracao invalida!");
					}

				}
				
				//Retornar representa��o dos elems do jogo
				
				return data;
			} 
			
			//Apos todas as opera��es, fechar o ficheiro de config de n�vel
			
			finally {
				s.close();
			}
		} 
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
