package ziggy.util;

/**
 * @author AndreFRocha
 * Classe Coord2D
 * Esta classe tem o objetivo de fazer operações
 * sobre coordenadas, como adicionar duas coord.,
 * representar textualmente uma coordenada,
 * mover uma coordenada através de uma determinada
 * distancia e direcção,  calcular distancia e direcção
 * entre duas coordenadas, e, finalmente, confirmar se
 * duas coordenadas são equivalentes. 
 */

public final class Coord2D {

	/**
	 * D2R - Função para converter graus para radianos
	 */

	public static final double D2R = Math.PI / 180;

	/**
	 * R2D - Função para converter radianos para graus
	 */

	public static final double R2D =  180 / Math.PI;

	/**
	 * CTOL - Tolerancia numerica para equivalencia entre coordenadas
	 */

	public static final double CTOL = 1e-06;

	/**
	 * Coordenadas imutaveis x e y
	 */

	private final double x,y;

	/**
	 * Constructor Coord2D - Cria objetos que representam coordenadas
	 * @param x - coordenada x
	 * @param y - coordenada y 
	 * 
	 */

	public Coord2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Devolve coordenada x
	 * @return double - coordenada x
	 */

	public double getX() {
		return x;
	}

	/**
	 * Devolve coordenada y
	 * @return double - coordenada y 
	 */

	public double getY() {
		return y;
	}

	/**
	 * Adiciona coordenada a coordenada actual
	 * @param other - coordenada
	 * @return Coord2D - Nova coordenada resultante da 
	 * adição das duas coordenadas
	 */

	public Coord2D add(Coord2D other) {
		Coord2D newCoord = new Coord2D (x+other.getX(), y+other.getY());
		return newCoord;
	}


	/**
	 * Devolve coordenada textual
	 * @return String - representação textual da coordenada: '(x,y)' 
	 */

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}


	/**
	 * Nova coordenada com distancia d e direcção a 
	 * das coordenadas actuais.
	 * @param d - Distancia
	 * @param a - Direcção
	 * @return Coord2D - Nova coordenada
	 */

	public Coord2D move(double d, double a) {
		double mx = d*Math.cos(a*D2R)+x;
		double my = d*Math.sin(a*D2R)+y;
		Coord2D newCoord = new Coord2D (mx,my);
		return newCoord;
	}

	/**
	 * Calcula distancia entre coordenada actual
	 * e coordenada C
	 * @param c - Coordenada c
	 * @return Double - distancia entre coordenada actual e
	 * coordenada c
	 */

	public double distanceTo(Coord2D c) {
		return Math.sqrt((c.getY()-y)*(c.getY()-y)+(c.getX()-x)*(c.getX()-x));
	}

	/**
	 * Calcula direcao entre coordenada actual
	 * e coordenada C
	 * @param c - Coordenada c
	 * @return Double - direccao entre coordenada actual
	 * e coordenada C
	 */

	public double directionTo(Coord2D c) {
		return Math.toDegrees(Math.atan2(c.getY()-y, c.getX()-x));
	}

	/**
	 * Testa se objeto o é uma coordenada assim como 
	 * a sua equivalencia com a coord actual
	 * @param o - Objecto O 
	 * @return Boolean - verdadeiro caso forem equivalentes
	 * falso caso contrário.
	 */

	@Override
	public boolean equals(Object o) {
		if(o instanceof Coord2D)
			if(((Coord2D) o).getX() - x <= CTOL && ((Coord2D) o).getY() - y <= CTOL)
				return true;
		return false;
	}
}
