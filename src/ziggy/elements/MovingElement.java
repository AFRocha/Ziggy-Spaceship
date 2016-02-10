package ziggy.elements;

import ziggy.actions.ActionQueue;
import ziggy.actions.ChangeDirection;
import ziggy.core.Constants;
import ziggy.core.Drawable;
import ziggy.util.Coord2D;

/**
 * Base class for moving elements.
 */
public abstract class MovingElement extends GameElement implements Constants, Drawable {

	/**
	 * Direction.
	 */
	private double direction;
	/**
	 * Speed.
	 */
	private double speed;

	/**
	 * Constructor.
	 * @param initialPos initial position
	 * @param initialSpeed initial speed
	 * @param initialDir initial direction
	 */
	protected MovingElement(Coord2D initialPos, double initialSpeed, double initialDir) {    
		super(initialPos);
		speed = initialSpeed;
		direction = initialDir;  
	}

	/**
	 * Get current speed.
	 * @return The current speed.
	 */
	public final double getSpeed() {
		return speed;
	}

	/**
	 * Get direction.
	 * @return The current direction.
	 */
	public final double getDirection() {
		return direction;
	}
	/**
	 * Change direction.
	 * @param d New direction.
	 */
	public final void setDirection(double d) {
		double nd = d - Math.floor(d/360) * 360;
		if (nd < 0 ) nd += 360;
		direction = nd;
	}

	/**
	 * Change speed.
	 * @param s New speed
	 */
	public final void setSpeed(double s) {
		speed = s;
	}

	/**
	 * Base step, for internal element actions.
	 * This base implementation does nothing.
	 * @param aq Action queue.
	 */
	public void step(ActionQueue aq) {
		Coord2D p = getPosition().move(speed, direction);
		double x = p.getX();
		double y = p.getY();
		if (x < 0) {
			x = AREA_LENGTH + x;
		} else if (x > AREA_LENGTH) {
			x = AREA_LENGTH - x;
		}
		if (y < 0) {
			y = AREA_LENGTH + y;
		} else if (y > AREA_LENGTH) {
			y = AREA_LENGTH - y;
		}
		setPosition(new Coord2D(x, y));
	}


	/**
	 * Default behavior for collision handling.
	 * @param other Other game element.
	 * @param aq Action queue.
	 */
	@Override
	public void collideWith(GameElement other, ActionQueue aq) {
		double direction = getPosition()
				.directionTo(other.getPosition()) + 180;
		aq.add(new ChangeDirection(this, direction));
	}
}
