package light3D;

import java.awt.Color;
import java.awt.Graphics2D;

public class MyObject {

	private Vector3D pos;
	private int size = 10;
	private Color color = Color.black;
	
	/**
	 * @param pos
	 */
	public MyObject(Vector3D pos) {
		super();
		this.pos = pos;
	}
	
	public MyObject (double x, double y, double z) {
		this(new Vector3D(x,y,z));
	}
	
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fillOval(0, 0, size, size);
	}

	protected Vector3D getPos() {
		return pos;
	}
}