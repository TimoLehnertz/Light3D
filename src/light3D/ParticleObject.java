package light3D;

import java.awt.Color;

/**
 * 
 * @author Timo Lehnertz
 *
 */

public class ParticleObject extends MyObject {

	/**
	 * Use physics or disable
	 */
	boolean usePhisic;
	
	/**
	 * Lifetime in seconds
	 */
	double lifetime;
	
	/**
	 * Random lifetime
	 * 	0 -> exists for exacly its lifetime
	 * 	1 -> random time between lifetime and 0
	 */
	double randomLifeTime;
	
	/**
	 * Amount to be emitted per second
	 */
	double amount;
	
	
	public ParticleObject(double x, double y, double z) {
		super(x, y, z);
	}

	@Override
	public void render(BasicRenderer r) {
		r.circle(getWorldTransform().pos, Color.red, 100, false, true);
	}
}