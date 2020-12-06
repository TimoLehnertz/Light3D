package light3D;

/**
 * 
 * @author Timo Lehnertz
 *
 */

public class Transform {

	public Vector3D pos;
	public Vector3D scale;
	public double zRot;
	public double xRot;
	
	public Transform() {
		this(new Vector3D(), Vector3D.SCALE_DEFAULT);
	}
	
	public Transform(Vector3D pos) {
		this(pos, Vector3D.SCALE_DEFAULT);
	}
	
	public Transform(Vector3D pos, Vector3D scale) {
		this(pos, scale, 0, 0);
	}
	
	/**
	 * @param pos
	 * @param scale
	 * @param zRot
	 */
	public Transform(Vector3D pos, Vector3D scale, double zRot, double xRot) {
		super();
		this.pos = pos;
		this.scale = scale;
		this.zRot = zRot;
		this.xRot = xRot;
	}
	
	public Transform appendSpace(Vector3D v) {
		return appendSpace(new Transform(v));
	}
	/**
	 * Not properly working for rotations
	 * @param t1
	 * @return
	 */
	public Transform appendSpace(Transform t1) {
		Transform t = t1.clone();
		
		/**
		 * Rotation
		 */
		t.pos.rotateZ(Math.toRadians(zRot));
		t.pos.rotateX(Math.toRadians(xRot));
		t.zRot += zRot;
		t.xRot += xRot;
		
		/**
		 * Scaling
		 */
		t.pos.multiply(scale);
		t.scale.multiply(scale);
		
		/**
		 * translation
		 */
		t.pos.add(pos);
		
		return t;
	}
	
	public Vector3D toSpace (Vector3D v) {
		return toSpace(new Transform(v, Vector3D.SCALE_DEFAULT)).pos;
	}
	
	/**
	 * Turn a Transform into this Transformations space
	 * @param b
	 * @return
	 */
	public Transform toSpace(Transform t1) {
		Transform t = t1.clone();
		
		/**
		 * translation
		 */
		t.pos.subtract(pos);
		
		/**
		 * Scaling
		 */
		t.pos.multiply(scale.getInverse());
		t.scale.multiply(scale.getInverse());
		
		/**
		 * Rotation
		 */
		t.pos.rotateZ(-Math.toRadians(zRot));
		t.pos.rotateX(-Math.toRadians(xRot));
		t.zRot -= zRot;
		t.xRot -= xRot;
		
		return t;
	}
	
	/**
	 * Getters / setters
	 */

	public Vector3D getPos() {
		return pos;
	}

	public void setPos(Vector3D pos) {
		this.pos = pos;
	}

	public Vector3D getScale() {
		return scale;
	}

	public void setScale(Vector3D scale) {
		this.scale = scale;
	}

	public double getzRot() {
		return zRot;
	}

	public void setzRot(double zRot) {
		this.zRot = zRot % 360;
	}

	public double getxRot() {
		return xRot;
	}

	public void setxRot(double xRot) {
		this.xRot = xRot;
	}
	
	public Transform getTransform() {
		return this;
	}
	
	@Override
	public Transform clone(){
		return new Transform(pos.clone(), scale.clone(), zRot, xRot);
	}
	
	@Override
	public String toString() {
		return "pos: " + pos + "\nscale: " + scale + "\nrot (zRot: " + zRot + " | xRot" + xRot + ")";
	}
}