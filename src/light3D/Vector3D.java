package light3D;

/**
 * 
 * @author Timo Lehnertz
 *
 */

public class Vector3D {

	private static final double BEAUTIFY_MARGIN = 0.00000001;
	public static final Vector3D SCALE_DEFAULT = new Vector3D(1,1,1);
	
	public double x, y, z;

	public Vector3D() {
		this(0,0,0);
	}
	
	public Vector3D(double scale) {
		this(scale, scale, scale);
	}
	
	public Vector3D(Vector3D v) {
		this(v.x,v.y,v.z);
	}
	
	public Vector3D(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		beautify();
	}
	
	public Vector3D setVector(Vector3D b) {
		this.x = b.x;
		this.y = b.y;
		this.z = b.z;
		return beautify();
	}
	
	public static Vector3D getScale(double scale) {
		return new Vector3D(scale);
	}
	
	public double distanceFrom(Transform t) {
		return VMath.getDistance(this, t.pos);
	}
	
	public double distanceFrom(Vector3D v) {
		return VMath.getDistance(this, v);
	}
	
	public Vector3D add(Vector3D b) {
		return setVector(VMath.add(this, b));
	}
	
	public Vector3D subtract(Vector3D b) {
		return setVector(VMath.subtract(this, b));
	}
	
	public Vector3D multiply(Vector3D b) {
		return setVector(VMath.multiply(this, b));
	}
	
	public Vector3D divide(Vector3D b) {
		return setVector(VMath.divide(this, b));
	}
	
	public Vector3D divide(double b) {
		return setVector(VMath.divide(this, b));
	}
	
	public Vector3D rotateZ(double rot) {
		return setVector(VMath.rotateZ(this, rot));
	}
	
	public Vector3D rotateX(double rot) {
		return setVector(VMath.rotateX(this, rot));
	}
	
	public double getLength() {
		return VMath.getLength(this);
	}
	
	public Vector3D getInverse() {
		return VMath.getInverse(this);
	}
	
	public Vector3D invert() {
		 return setVector(VMath.getInverse(this));
	}

	private Vector3D beautify() {
		if(x < BEAUTIFY_MARGIN && x > -BEAUTIFY_MARGIN) {
			x = 0;
		}
		if(y < BEAUTIFY_MARGIN && y > -BEAUTIFY_MARGIN) {
			y = 0;
		}
		if(z < BEAUTIFY_MARGIN && z > -BEAUTIFY_MARGIN) {
			z = 0;
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "(" + x + " | " + y + " | " + z + ")";
	}
	
	@Override
	public Vector3D clone() {
		return new Vector3D(x,y,z);
	}
}