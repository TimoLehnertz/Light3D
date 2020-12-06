package light3D;

import java.awt.Color;

/**
 * 
 * @author Timo Lehnertz
 *
 */

public class ParticleObject extends MyObject {

	public ParticleObject(double x, double y, double z) {
		super(x, y, z);
	}

	@Override
	public void render(BasicRenderer r) {
		r.circle(getWorldTransform().pos, Color.red, 100, false, true);
	}
}